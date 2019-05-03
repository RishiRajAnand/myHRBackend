package com.erx.microservice.patientmanagement.service.ipadmission.getbedmovementdetailsbyippatient;

/*
 * created by Latha on 29-11-2017.
 * */

import com.erx.microservice.patientmanagement.domain.IpAdmissionBedMovement;
import com.erx.microservice.patientmanagement.repository.IpAdmissionBedMovementRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionBedMovedDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getBedMovementDetailsByIpService")
public class GetBedMovementDetailsByIpServiceImpl implements GetBedMovementDetailsByIpService {

    private final Logger log = LoggerFactory.getLogger(GetBedMovementDetailsByIpServiceImpl.class);
    @Autowired
    private IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository;
    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Override
    public GetBedMovementDetailsByIpServiceResponse execute(GetBedMovementDetailsByIpServiceRequest request) throws ServiceException {
        GetBedMovementDetailsByIpServiceResponse response = null;
        List<IpAdmissionBedMovement> ipAdmissionBedMovements = null;
        List<IpAdmissionBedMovedDTO> ipAdmissionBedMovedDTOs = new ArrayList<>();
        try {
            log.debug("Call to get bed transfer details for the given ip admission number");
            // retrivew the bed movement details
            if (request.getIpAdmissionID() != null) {
                Long ipAdmissionId = ipAdmissionRepository.findById(request.getIpAdmissionID());
                if (ipAdmissionId == null)
                    throw new Exception("ipAdmissionId does not exist");
                ipAdmissionBedMovements = ipAdmissionBedMovementRepository.getBedMovementDetails(request.getIpAdmissionID());
                if (!ipAdmissionBedMovements.isEmpty()) {
                    for (IpAdmissionBedMovement ipAdmissionBedMovement : ipAdmissionBedMovements) {
                        IpAdmissionBedMovedDTO ipAdmissionBedMovedDTO = new IpAdmissionBedMovedDTO();
                        // set the values
                        ipAdmissionBedMovedDTO.setIpBedMovementID(ipAdmissionBedMovement.getId());
                        ipAdmissionBedMovedDTO.setDateOfAdmission(ipAdmissionBedMovement.getIpAdmission().getAdmissionOn());
                        ipAdmissionBedMovedDTO.setIpAdmissionID(ipAdmissionBedMovement.getIpAdmission().getId());
                        if (ipAdmissionBedMovement.getIpAdmission().getIpAdmissionNumber() != null && ipAdmissionBedMovement.getIpAdmission().getIpAdmissionNumber() != "") {
                            ipAdmissionBedMovedDTO.setIpAdmissionNumber(ipAdmissionBedMovement.getIpAdmission().getIpAdmissionNumber());
                        }
                        if (ipAdmissionBedMovement.getIpAdmission().getDayCareAdmissionNumber() != null && ipAdmissionBedMovement.getIpAdmission().getDayCareAdmissionNumber() != "") {
                            ipAdmissionBedMovedDTO.setIpAdmissionNumber(ipAdmissionBedMovement.getIpAdmission().getDayCareAdmissionNumber());
                        }
                        if (ipAdmissionBedMovement.getIpAdmission().getPatient() != null) {
                            ipAdmissionBedMovedDTO.setPatientMRN(ipAdmissionBedMovement.getIpAdmission().getPatient().getPatientIdExternal());
                            ipAdmissionBedMovedDTO.setPatientName(ipAdmissionBedMovement.getIpAdmission().getPatient().getPatientName());
                            ipAdmissionBedMovedDTO.setGender(ipAdmissionBedMovement.getIpAdmission().getPatient().getGender());
                        }
                        ipAdmissionBedMovedDTO.setActualBedID(ipAdmissionBedMovement.getIpAdmission().getBedMaster().getId());
                        ipAdmissionBedMovedDTO.setActualBed(ipAdmissionBedMovement.getIpAdmission().getBedMaster().getBedNumber());
                        if (ipAdmissionBedMovement.getBedMovedDepartment() != null) {
                            ipAdmissionBedMovedDTO.setMovedDepartment(ipAdmissionBedMovement.getBedMovedDepartment().getDepartmentName());
                        }
                        ipAdmissionBedMovedDTO.setTransferDate(ipAdmissionBedMovement.getCreatedOn());
                        if (ipAdmissionBedMovement.getBedMovedFrom() != null) {
                            ipAdmissionBedMovedDTO.setFromBedID(ipAdmissionBedMovement.getBedMovedFrom().getId());
                            ipAdmissionBedMovedDTO.setFromBed(ipAdmissionBedMovement.getBedMovedFrom().getBedNumber());
                            if (ipAdmissionBedMovement.getBedMovedFrom().getWardMaster() != null) {
                                ipAdmissionBedMovedDTO.setFromWard(ipAdmissionBedMovement.getBedMovedFrom().getWardMaster().getWardName());
                                ipAdmissionBedMovedDTO.setToWard(ipAdmissionBedMovement.getBedMovedTo().getWardMaster().getWardName());
                            }

                            ipAdmissionBedMovedDTO.setToBedID(ipAdmissionBedMovement.getBedMovedTo().getId());
                            ipAdmissionBedMovedDTO.setToBed(ipAdmissionBedMovement.getBedMovedTo().getBedNumber());
                        }
                        //if actual bed equal to moved bed then set isActualBed to true else false
                        if (ipAdmissionBedMovement.getIpAdmission().getBedMaster().getId().equals(ipAdmissionBedMovement.getBedMovedTo().getId()))
                            ipAdmissionBedMovedDTO.setOriginalBed(true);
                        else
                            ipAdmissionBedMovedDTO.setOriginalBed(false);
                        ipAdmissionBedMovedDTO.setTransferredBy(ipAdmissionBedMovement.getUserStaff().getFirstName() + " " + ipAdmissionBedMovement.getUserStaff().getLastName());
                        ipAdmissionBedMovedDTO.setUserDepartment("NA");
                        // add details to list
                        ipAdmissionBedMovedDTOs.add(ipAdmissionBedMovedDTO);

                    }
                }

            }
            response = new GetBedMovementDetailsByIpServiceResponse(ipAdmissionBedMovedDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Bed moved details Successfully");
            log.debug("Retrieved bed moved Successfully");
        } catch (Exception e) {
            response = new GetBedMovementDetailsByIpServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Bed moved details : " + e.getMessage());
            log.error("Failed to retrieve bed moved details : " + e.getMessage());
        }
        return response;
    }
}
