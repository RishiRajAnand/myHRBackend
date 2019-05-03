package com.erx.microservice.patientmanagement.service.ipadmission.getallocatedbedetailsbyipadmissionid;

/*
* created by Raushan on 06-10-2018.
* */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionTrackerDTO;
import com.erx.microservice.patientmanagement.service.dto.IpDischargeDetailDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientCaseDTO;
import com.erx.microservice.patientmanagement.service.mapper.casemanagement.CmMasterMapper;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllocatedBedDetailsByIpAdmissionIdServiceImpl implements GetAllocatedBedDetailsByIpAdmissionIdService {

    private final Logger log = LoggerFactory.getLogger(GetAllocatedBedDetailsByIpAdmissionIdServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserDepartmentRepository userDepartmentRepository;

    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;

    @Autowired
    private IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository;

    @Autowired
    private IpAdmissionBedTransferRepository ipAdmissionBedTransferRepository;

    @Autowired IpAdmissionRepository ipAdmissionRepository;



    @Override
    public GetAllocatedBedDetailsByIpAdmissionIdServiceResponse execute(GetAllocatedBedDetailsByIpAdmissionIdServiceRequest request) throws ServiceException {

        GetAllocatedBedDetailsByIpAdmissionIdServiceResponse response = null;
        List<CmMaster> cmMasters = null;
        List<PatientCaseDTO> patientCaseDTOs = new ArrayList<>();
        List<Object[]> ipAdmissions = null;
        List<IpAdmissionTrackerDTO> patientDTOs = new ArrayList<>();
        IpAdmissionBedMovement ipAdmissionBedMovement = null;
        List<UserDepartment> userDepartment = null;
        Page<IpAdmissionTrackerDTO> ipAdmissionTrackerDTOs = null;
        IpAdmissionTrackerDTO patientDTO = new IpAdmissionTrackerDTO();

        try {
            log.debug("Call to get AllocatedBedDetails BIpAdmissionId");
            if (request.getIpAdmissionId() != null) {
                //retrieve objects
                ipAdmissions = patientRepository.getIpAdmissionByIpAdmissionId
                        (request.getIpAdmissionId());
                //set the list of PatientDTO
                for (Object[] ipAdmissionDetail : ipAdmissions) {
                    //create PatientDTO object

                    IpAdmission ipAdmission = null;
                    Patient patient = null;
                    patient = (Patient) ipAdmissionDetail[0];

                    if (ipAdmissionDetail[1] != null) {
                        ipAdmission = (IpAdmission) ipAdmissionDetail[1];

                        patientDTO.setiPAdmissionId(ipAdmission.getId());
                        patientDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                        if (ipAdmission.getUserDetail() != null) {
                            if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                patientDTO.setDoctorId(ipAdmission.getUserDetail().getId());
                                patientDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName()
                                        + " " + ipAdmission.getUserDetail().getUserStaff().getLastName());
                                if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                    userDepartment = userDepartmentRepository.getDepartmentByDoctor
                                            (ipAdmission.getUserDetail().getUserStaff().getId());
                                }
                                if (userDepartment != null) {
                                    for (UserDepartment userDepartment1 : userDepartment) {
                                        patientDTO.setDepartmentId(userDepartment1.getDepartment().getId());
                                        patientDTO.setDepartmentName(userDepartment1.getDepartment().getDepartmentName());
                                    }
                                }
                            }
                        } else {
                            patientDTO.setDoctorName("-");
                        }
                        if (!(ipAdmission.getIpAdmissionNumber().equals(""))) {
                            patientDTO.setVisitId(ipAdmission.getIpAdmissionNumber());
                        } else if (ipAdmission.getDayCareAdmissionNumber() != null) {
                            patientDTO.setVisitId(ipAdmission.getDayCareAdmissionNumber());
                        } else {
                            patientDTO.setVisitId("-");
                        }
                        if (ipAdmission.getVisitTypeMasterId() != null) {
                            String visitType = visitTypeMasterRepository.findOne(ipAdmission.getVisitTypeMasterId()).getVisitType();
                            patientDTO.setVisitType(visitType);
                        }
                        patientDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                        patientDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                        patientDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                        patientDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                        patientDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                        List<IpAdmissionBedMovement> ipAdmissionBedMovements = ipAdmissionBedMovementRepository
                                .getBedMovementByCurrentBed(ipAdmission.getId());
                        if (!ipAdmissionBedMovements.isEmpty())
                            ipAdmissionBedMovement = ipAdmissionBedMovements.get(ipAdmissionBedMovements.size() - 1);
                        else ipAdmissionBedMovement = null;
                        if (ipAdmissionBedMovement != null) {
                            patientDTO.setIpAdmissionBedMovementId(ipAdmissionBedMovement.getId());
                            patientDTO.setBedMovedToId(ipAdmissionBedMovement.getBedMovedTo().getId());
                            //call method to check moved bed is same as actual bed
                            checkMovedBed(ipAdmission, ipAdmissionBedMovement, patientDTO);
                        } else
                            //call method to check transferred bed is same as actual bed
                            checkBedTransferredBed(ipAdmission, patientDTO);
                        patientDTO.setIpAdmissionNotes(ipAdmission.getIpAdmissionNotes());
                        patientDTO.setIpAdmissionStatus(PatientConstants.IP_STATUS_ADMITTED);
                    }

                    //set the values
                    patientDTO.setPatientId(patient.getId());
                    patientDTO.setPatientMRN(patient.getPatientIdExternal());
                    patientDTO.setPatientName(patient.getPatientName());
                    patientDTO.setGender(patient.getGender());

                    //set DisChargeDetails
                    List<IpDischargeDetailDTO> ipDischargeDetailDTOs = getDischargeDetails(patientDTO, patient);
                    patientDTO.setIpDischargeDetailDTOs(ipDischargeDetailDTOs);

                }

            }
            response = new GetAllocatedBedDetailsByIpAdmissionIdServiceResponse(patientDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Case details Successfully");
            log.debug("Retrieved Case Details Successfully");
        } catch (Exception e) {
            response = new GetAllocatedBedDetailsByIpAdmissionIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Case details");
            log.error("Failed to retrieve Case Details");
        }
        return response;
    }

    //method to check moved bed is same as actual bed
    private void checkMovedBed(IpAdmission ipAdmission, IpAdmissionBedMovement ipAdmissionBedMovement,
                               IpAdmissionTrackerDTO patientDTO) throws ServiceException {
        if (ipAdmission.getBedMaster().getId().equals(ipAdmissionBedMovement.getBedMovedTo().getId()))
            patientDTO.setOriginalBed(true);
        else
            patientDTO.setOriginalBed(false);
    }

    //method to check transferred bed is same as actual bed
    private void checkBedTransferredBed(IpAdmission ipAdmission, IpAdmissionTrackerDTO patientDTO) throws ServiceException {
        IpAdmissionBedTransfer ipAdmissionBedTransfer = ipAdmissionBedTransferRepository.
                getBedTransferDetailsIpAdmissionId(ipAdmission.getId());
        if (ipAdmissionBedTransfer != null) {
            if (ipAdmission.getBedMaster().getId().equals(ipAdmissionBedTransfer.getBedTransferredTo().getId()))
                patientDTO.setOriginalBed(true);
            else
                patientDTO.setOriginalBed(false);
        } else {
            patientDTO.setOriginalBed(true);
        }
    }

    private List<IpDischargeDetailDTO> getDischargeDetails(IpAdmissionTrackerDTO patientDTO, Patient patient) {

        List<IpDischargeDetailDTO> ipDischargeDetailDTOs = null;
        List<IpAdmission> dischargedIpAdmissions = ipAdmissionRepository
                .findDischargedIpDetailsByPatient(patient.getId(), patient.getClinic().getId());
        if (!dischargedIpAdmissions.isEmpty()) {
            ipDischargeDetailDTOs = new ArrayList<>();
            for (IpAdmission dischargeIpDetail : dischargedIpAdmissions) {
                IpDischargeDetailDTO ipDischargeDetailDTO = new IpDischargeDetailDTO();
                ipDischargeDetailDTO.setIpAdmissionId(dischargeIpDetail.getId());
                if (dischargeIpDetail.getIpAdmissionNumber() != null)
                    ipDischargeDetailDTO.setIpAdmissionNumber(dischargeIpDetail.getIpAdmissionNumber());
                else if (dischargeIpDetail.getDayCareAdmissionNumber() != null)
                    ipDischargeDetailDTO.setIpAdmissionNumber(dischargeIpDetail.getDayCareAdmissionNumber());
                ipDischargeDetailDTO.setPatientName(patientDTO.getPatientName());
                ipDischargeDetailDTO.setMrn(patientDTO.getPatientMRN());
                ipDischargeDetailDTO.setDateOfAdmission(dischargeIpDetail.getAdmissionOn());
                ipDischargeDetailDTO.setDateOfDischarge(dischargeIpDetail.getDischargedOn());
                if (dischargeIpDetail.getUserDetail() != null)
                    ipDischargeDetailDTO.setDoctorName(dischargeIpDetail.getUserDetail().getUserStaff().getFirstName() +
                            " " + dischargeIpDetail.getUserDetail().getUserStaff().getLastName());
                if (dischargeIpDetail.getIpAdmissionCaseMapping() != null) {
                    if (dischargeIpDetail.getIpAdmissionCaseMapping().getCmMaster() != null) {
                        ipDischargeDetailDTO.setChiefComplaint(dischargeIpDetail.getIpAdmissionCaseMapping()
                                .getCmMaster().getChiefComplaint());
                    }
                }
                ipDischargeDetailDTO.setStatus(PatientConstants.IP_STATUS_DISCHARGED);
                //add to List
                ipDischargeDetailDTOs.add(ipDischargeDetailDTO);
            }

        }
        return ipDischargeDetailDTOs;
    }

}
