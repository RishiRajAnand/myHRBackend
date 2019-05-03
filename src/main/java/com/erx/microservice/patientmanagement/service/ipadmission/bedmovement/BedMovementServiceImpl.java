package com.erx.microservice.patientmanagement.service.ipadmission.bedmovement;

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.service.dto.BedMovementDTO;
import com.erx.microservice.patientmanagement.service.mapper.IpAdmissionBedMovementMapper;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* created by Latha on 29-11-2017
* */
@Service("bedMovementService")

public class BedMovementServiceImpl implements BedMovementService {

    private final Logger log = LoggerFactory.getLogger(BedMovementServiceImpl.class);

    @Autowired
    IpAdmissionBedMovementMapper ipAdmissionBedMovementMapper;

    @Autowired
    IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Autowired
    IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository;

    @Autowired
    UserStaffRepository userStaffRepository;

    @Override
    public BedMovementServiceResponse execute(BedMovementServiceRequest request) throws ServiceException {

        BedMovementServiceResponse response = null;
        BedMovementDTO bedMovementDTO = null;
        BedMovementDTO savedBedMovementDTO = null;
        IpAdmissionBedMovement ipAdmissionBedMovement = new IpAdmissionBedMovement();
        IpAdmission ipAdmission = null;
        IpAdmission savedIpAdmission = null;
        UserDetail userDetail = null;
        BedConfigurationMaster bedMovedFrom = null;
        BedConfigurationMaster bedMovedTo = null;
        UserStaff userStaff = null;
        BedConfigurationMaster bedConfigurationMasterActual = null;
        BedConfigurationMaster savedBedConfigurationMasterActual = null;
        BedConfigurationMaster bedConfigurationMasterFrom = null;
        BedConfigurationMaster bedConfigurationMasterTo = null;
        IpAdmissionBedMovement savedIpAdmissionBedMovement = null;
        IpAdmissionBedMovement previousIpAdmissionBedMovement = null;
        IpAdmissionBedMovement savedPreviousIpAdmissionBedMovement = null;
        try {
            log.debug("To Move IpAdmission");
            //retrieve the Object from request
            bedMovementDTO = request.getBedMovementDTO();
            //set ipAdmission to ipAdmissionBedTransfer
            ipAdmission = ipAdmissionRepository.findOne(bedMovementDTO.getIpAdmissionID());
            if (bedMovementDTO.getIpAdmissionNotes() != null || bedMovementDTO.getUserDetailID() != null) {
                //set bedMaster to ipAdmission
                if (bedMovementDTO.getIpAdmissionNotes() != null) {
                    ipAdmission.setIpAdmissionNotes(bedMovementDTO.getIpAdmissionNotes());
                }
                //set UserDoctor in IpAdmission
                userDetail = userDetailRepository.findOne(bedMovementDTO.getUserDetailID());
                ipAdmission.setUserDetail(userDetail);
                savedIpAdmission = ipAdmissionRepository.save(ipAdmission);
                ipAdmissionBedMovement.setIpAdmission(savedIpAdmission);
            } else {
                ipAdmissionBedMovement.setIpAdmission(ipAdmission);
            }
            //set moved from bed to IpAdmissionBedMovement
            bedMovedFrom = bedConfigurationMasterRepository.findOne(bedMovementDTO.getFromBedMasterID());
            ipAdmissionBedMovement.setBedMovedFrom(bedMovedFrom);
            if (bedMovementDTO.getId() == null || bedMovementDTO.getId() == 0) {
                // finding the actual bed of the patient and assigning the bed to be available
                bedConfigurationMasterActual = bedConfigurationMasterRepository.findOne(ipAdmission.getBedMaster().getId());
                //allocate status of Moved is changing once the bed is Moved(Moved in bed master)
                bedConfigurationMasterActual.setAllocatedStatus("moved");
                savedBedConfigurationMasterActual = bedConfigurationMasterRepository.save(bedConfigurationMasterActual);
                // set moved to bed in IpAdmissionBedMovement
                bedMovedTo = bedConfigurationMasterRepository.findOne(bedMovementDTO.getToBedMasterID());
                ipAdmissionBedMovement.setBedMovedTo(bedMovedTo);
                // set transferred by to ipAdmissionBedTransfer
                userStaff = userStaffRepository.findOne(bedMovementDTO.getUserID());
                ipAdmissionBedMovement.setUserStaff(userStaff);
                //allocate status of occupied is changing once the bed is Moved(moved in bed master)
                bedMovedTo.setAllocatedStatus("occupied");
                bedConfigurationMasterTo = bedConfigurationMasterRepository.save(bedMovedTo);
                // set the current bed status
                ipAdmissionBedMovement.setCurrentBed(true);
                savedIpAdmissionBedMovement = ipAdmissionBedMovementRepository.save(ipAdmissionBedMovement);

            } else {
                //retrieve ipAdmissionBedMovement details by passing ID
                previousIpAdmissionBedMovement = ipAdmissionBedMovementRepository.findOne(bedMovementDTO.getId());
                if (previousIpAdmissionBedMovement.getBedMovedTo().getId() == ipAdmissionBedMovement.getIpAdmission().getBedMaster().getId()) {
                    previousIpAdmissionBedMovement.setCurrentBed(false);
                    savedPreviousIpAdmissionBedMovement = ipAdmissionBedMovementRepository.save(previousIpAdmissionBedMovement);
                    // finding the actual bed of the patient and assigning the bed to be available
                    bedConfigurationMasterActual = bedConfigurationMasterRepository.findOne(ipAdmission.getBedMaster().getId());
                    //allocate status of Moved is changing once the bed is Moved(Moved in bed master)
                    bedConfigurationMasterActual.setAllocatedStatus("moved");
                    savedBedConfigurationMasterActual = bedConfigurationMasterRepository.save(bedConfigurationMasterActual);
                    // set moved to bed in IpAdmissionBedMovement
                    bedMovedTo = bedConfigurationMasterRepository.findOne(bedMovementDTO.getToBedMasterID());
                    ipAdmissionBedMovement.setBedMovedTo(bedMovedTo);
                    // set transferred by to ipAdmissionBedTransfer
                    userStaff = userStaffRepository.findOne(bedMovementDTO.getUserID());
                    ipAdmissionBedMovement.setUserStaff(userStaff);
                    //allocate status of occupied is changing once the bed is Moved(moved in bed master)
                    bedMovedTo.setAllocatedStatus("occupied");
                    bedConfigurationMasterTo = bedConfigurationMasterRepository.save(bedMovedTo);
                    // set the current bed status
                    ipAdmissionBedMovement.setCurrentBed(true);
                    savedIpAdmissionBedMovement = ipAdmissionBedMovementRepository.save(ipAdmissionBedMovement);
                } else {
                    previousIpAdmissionBedMovement.setCurrentBed(false);
                    savedPreviousIpAdmissionBedMovement = ipAdmissionBedMovementRepository.save(previousIpAdmissionBedMovement);
                    // finding the actual bed of the patient and assigning the bed to be available
                    bedConfigurationMasterActual = bedConfigurationMasterRepository.findOne(bedMovementDTO.getFromBedMasterID());
                    //allocate status of Moved is changing once the bed is Moved(Moved in bed master)
                    bedConfigurationMasterActual.setAllocatedStatus("availability");
                    savedBedConfigurationMasterActual = bedConfigurationMasterRepository.save(bedConfigurationMasterActual);
                    // set moved to bed in IpAdmissionBedMovement
                    bedMovedTo = bedConfigurationMasterRepository.findOne(bedMovementDTO.getToBedMasterID());
                    ipAdmissionBedMovement.setBedMovedTo(bedMovedTo);
                    //allocate status of occupied is changing once the bed is Moved(moved in bed master)
                    bedMovedTo.setAllocatedStatus("occupied");
                    bedConfigurationMasterTo = bedConfigurationMasterRepository.save(bedMovedTo);
                    // set transferred by to ipAdmissionBedTransfer
                    userStaff = userStaffRepository.findOne(bedMovementDTO.getUserID());
                    ipAdmissionBedMovement.setUserStaff(userStaff);
                    // set the current bed status
                    ipAdmissionBedMovement.setCurrentBed(true);
                    savedIpAdmissionBedMovement = ipAdmissionBedMovementRepository.save(ipAdmissionBedMovement);
                }
            }

            //convert the saved object into DTO
            savedBedMovementDTO = ipAdmissionBedMovementMapper.ipAdmissionBedMovementToBedMovementDTO(savedIpAdmissionBedMovement);
            //create response
            response = new BedMovementServiceResponse(savedBedMovementDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Bed movement is Successfully");
            log.debug("Bed movement is Successfully");
        } catch (Exception e) {
            response = new BedMovementServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to move bed");
            log.error("Failed to move bed");
        }
        return response;
    }
}
