package com.erx.microservice.patientmanagement.service.ipadmission.bedtransfer;

/*
 * created by Latha on 29-11-2017
 * */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.service.dto.BedTransferDTO;
import com.erx.microservice.patientmanagement.service.dto.patientalldetailsdto.PatientAllDetailsDTO;
import com.erx.microservice.patientmanagement.service.mapper.IpAdmissionBedTransferMapper;
import com.erx.microservice.patientmanagement.service.patientalldetails.SavePatientAllDetailsService;
import com.erx.microservice.patientmanagement.service.patientalldetails.SavePatientAllDetailsServiceRequest;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bedTransferService")
public class BedTransferServiceImpl implements BedTransferService {
    private final Logger log = LoggerFactory.getLogger(BedTransferServiceImpl.class);

    @Autowired
    IpAdmissionBedTransferMapper ipAdmissionBedTransferMapper;

    @Autowired
    IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Autowired
    UserStaffRepository userStaffRepository;

    @Autowired
    IpAdmissionBedTransferRepository ipAdmissionBedTransferRepository;
    @Autowired
    IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository;
    @Autowired
    private SavePatientAllDetailsService savePatientAllDetailsService;


    public BedTransferServiceImpl(IpAdmissionBedTransferMapper ipAdmissionBedTransferMapper,
                                  IpAdmissionRepository ipAdmissionRepository, UserDetailRepository userDetailRepository,
                                  BedConfigurationMasterRepository bedConfigurationMasterRepository,
                                  UserStaffRepository userStaffRepository, IpAdmissionBedTransferRepository ipAdmissionBedTransferRepository,
                                  IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository) {
        this.ipAdmissionBedTransferMapper = ipAdmissionBedTransferMapper;
        this.ipAdmissionRepository = ipAdmissionRepository;
        this.userDetailRepository = userDetailRepository;
        this.bedConfigurationMasterRepository = bedConfigurationMasterRepository;
        this.userStaffRepository = userStaffRepository;
        this.ipAdmissionBedTransferRepository = ipAdmissionBedTransferRepository;
        this.ipAdmissionBedMovementRepository = ipAdmissionBedMovementRepository;
    }

    // constructor

    @Override
    public BedTransferServiceResponse execute(BedTransferServiceRequest request) throws ServiceException {

        BedTransferServiceResponse response = null;
        BedTransferDTO bedTransferDTO = null;
        BedTransferDTO savedBedTransferDTO = null;
        IpAdmissionBedTransfer ipAdmissionBedTransfer = null;
        IpAdmission ipAdmission = null;
        BedConfigurationMaster bedConfigurationMaster = null;
        BedConfigurationMaster bedConfigurationMasterFrom = null;
        UserDetail userDetail = null;
        UserStaff userStaff = null;
        IpAdmissionBedTransfer savedIpAdmissionBedTransfer = null;
        BedConfigurationMaster savedBedConfigurationMaster = null;
        BedConfigurationMaster savedBedConfigurationMasterFrom = null;

        try {
            log.debug("To Transfer IpAdmission");
            //retrieve the Object from request
            bedTransferDTO = request.getBedTransferDTO();
            //convert DTO to domain
            ipAdmissionBedTransfer = ipAdmissionBedTransferMapper.bedTransferDTOToIpAdmissionBedTransfer(bedTransferDTO);
            //set ipAdmission to ipAdmissionBedTransfer
            ipAdmission = ipAdmissionRepository.findOne(bedTransferDTO.getIpAdmissionID());
            // set bed transferred from to ipAdmissionBedTransfer
            ipAdmissionBedTransfer.setBedTransferredFrom(ipAdmission.getBedMaster());
            // finding the actual bed of the patient and assigning the bed to be available
            bedConfigurationMasterFrom = bedConfigurationMasterRepository.findOne(ipAdmission.getBedMaster().getId());
            //allocate status of occupied is changing once the bed is transferred(transfer in bed master)
            bedConfigurationMasterFrom.setAllocatedStatus("availability");
            savedBedConfigurationMasterFrom = bedConfigurationMasterRepository.save(bedConfigurationMasterFrom);
            if (bedTransferDTO.getIpAdmissionNotes() != null || bedTransferDTO.getUserDetailID() != null) {
                //set bedMaster to ipAdmission
                ipAdmission.setIpAdmissionNotes(bedTransferDTO.getIpAdmissionNotes());
                //set UserDoctor in IpAdmission
                userDetail = userDetailRepository.findOne(bedTransferDTO.getUserDetailID());
                ipAdmission.setUserDetail(userDetail);
            }
            //set bed in IpAdmission
            bedConfigurationMaster = bedConfigurationMasterRepository.findOne(bedTransferDTO.getBedMasterID());
            ipAdmission.setBedMaster(bedConfigurationMaster);
            IpAdmission savedIpAdmission = ipAdmissionRepository.save(ipAdmission);
            // set ipAdmission to ipAdmissionBedTransfer
            ipAdmissionBedTransfer.setIpAdmission(savedIpAdmission);
            // set bed transferred to in ipAdmissionBedTransfer
            ipAdmissionBedTransfer.setBedTransferredTo(bedConfigurationMaster);
            // set transferred by to ipAdmissionBedTransfer
            userStaff = userStaffRepository.findOne(bedTransferDTO.getUserID());
            ipAdmissionBedTransfer.setUserStaff(userStaff);
            // save IpAdmissionBedTransfer
            savedIpAdmissionBedTransfer = ipAdmissionBedTransferRepository.save(ipAdmissionBedTransfer);
            //allocate status is changing once the bed is allocated(save in bed master)
            bedConfigurationMaster.setAllocatedStatus("occupied");
            savedBedConfigurationMaster = bedConfigurationMasterRepository.save(bedConfigurationMaster);

            //get and set current bed to false
            List<IpAdmissionBedMovement> ipAdmissionBedMovements = ipAdmissionBedMovementRepository
                    .getBedMovementByCurrentBed(ipAdmission.getId());
            if (!ipAdmissionBedMovements.isEmpty()) {
                ipAdmissionBedMovementRepository.updateCurrentBedStatusByipAdmissionBedMovementIds(ipAdmissionBedMovements);

            }
            //call method to save bed transfer details in patient_all_details table
            saveBedTransferDetailsInPatientAllDetails(savedIpAdmission);
            //convert the saved object into DTO
            savedBedTransferDTO = ipAdmissionBedTransferMapper.ipAdmissionBedTransferToBedTransferDTO(savedIpAdmissionBedTransfer);
            //create response
            response = new BedTransferServiceResponse(savedBedTransferDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Transferred bed for IpAdmission Successfully");
            log.debug("Transferred bed for IpAdmission Successfully");
        } catch (Exception e) {
            response = new BedTransferServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to transfer Bed for IpAdmission");
            log.error("Failed to transfer Bed for IpAdmission");
        }
        return response;
    }

    //method to save bedTransfer details in patient_all_details_table
    private void saveBedTransferDetailsInPatientAllDetails(IpAdmission savedIpAdmission) {
        try {
            PatientAllDetailsDTO patientAllDetailsDTO = new PatientAllDetailsDTO();
            patientAllDetailsDTO.setContext(ErxConstants.PATIENT_BED_TRANSFER_UPDATE);
            patientAllDetailsDTO.setPatientId(savedIpAdmission.getPatient().getId());
            patientAllDetailsDTO.setRoomNumber(savedIpAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
            patientAllDetailsDTO.setBedNumber(savedIpAdmission.getBedMaster().getBedNumber());
            patientAllDetailsDTO.setWardName(savedIpAdmission.getBedMaster().getWardMaster().getWardName());
            patientAllDetailsDTO.setBedTypeId(savedIpAdmission.getBedMaster().getBedTypeMaster().getId());
            patientAllDetailsDTO.setBedTypeName(savedIpAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
            patientAllDetailsDTO.setBedNumber(savedIpAdmission.getBedMaster().getBedNumber());
            SavePatientAllDetailsServiceRequest savePatientAllDetailsServiceRequest = new
                    SavePatientAllDetailsServiceRequest(patientAllDetailsDTO);
            savePatientAllDetailsService.execute(savePatientAllDetailsServiceRequest);
        } catch (Exception e) {
            log.error("Error==>failed to save patient ip details in patient_all_details table");
        }
    }
}
