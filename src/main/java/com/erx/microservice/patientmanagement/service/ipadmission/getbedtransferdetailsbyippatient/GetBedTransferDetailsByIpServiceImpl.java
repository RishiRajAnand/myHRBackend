package com.erx.microservice.patientmanagement.service.ipadmission.getbedtransferdetailsbyippatient;

/*
 * created by Latha on 29-11-2017.
 * */

import com.erx.microservice.patientmanagement.domain.IpAdmissionBedTransfer;
import com.erx.microservice.patientmanagement.repository.IpAdmissionBedTransferRepository;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionBedTransferDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getBedTransferDetailsByIpService")
public class GetBedTransferDetailsByIpServiceImpl implements GetBedTransferDetailsByIpService {

    private final Logger log = LoggerFactory.getLogger(GetBedTransferDetailsByIpServiceImpl.class);

    @Autowired
    private IpAdmissionBedTransferRepository ipAdmissionBedTransferRepository;

    @Override
    public GetBedTransferDetailsByIpServiceResponse execute(GetBedTransferDetailsByIpServiceRequest request) throws ServiceException {
        GetBedTransferDetailsByIpServiceResponse response = null;
        List<IpAdmissionBedTransferDTO> ipAdmissionBedTransferDTOs = new ArrayList<>();
        List<IpAdmissionBedTransfer> ipAdmissionBedTransfers = null;
        try {
            log.debug("Call to get bed transfer details for the given ip admission number");
            // retrieve the bed transfer details
            if (request.getIpAdmissionID() != null) {
                ipAdmissionBedTransfers = ipAdmissionBedTransferRepository.getBedTransferDetails(request.getIpAdmissionID());

                for (IpAdmissionBedTransfer ipAdmissionBedTransfer : ipAdmissionBedTransfers) {
                    IpAdmissionBedTransferDTO ipAdmissionBedTransferDTO = new IpAdmissionBedTransferDTO();
                    // set the values
                    ipAdmissionBedTransferDTO.setDateOfAdmission(ipAdmissionBedTransfer.getIpAdmission().getAdmissionOn());
                    ipAdmissionBedTransferDTO.setIpAdmissionID(ipAdmissionBedTransfer.getIpAdmission().getId());
                    if (ipAdmissionBedTransfer.getIpAdmission().getIpAdmissionNumber() != null && ipAdmissionBedTransfer.getIpAdmission().getIpAdmissionNumber() != "") {
                        ipAdmissionBedTransferDTO.setIpAdmissionNumber(ipAdmissionBedTransfer.getIpAdmission().getIpAdmissionNumber());
                    }
                    if (ipAdmissionBedTransfer.getIpAdmission().getDayCareAdmissionNumber() != null && ipAdmissionBedTransfer.getIpAdmission().getDayCareAdmissionNumber() != "") {
                        ipAdmissionBedTransferDTO.setIpAdmissionNumber(ipAdmissionBedTransfer.getIpAdmission().getDayCareAdmissionNumber());
                    }
                    if (ipAdmissionBedTransfer.getIpAdmission().getPatient() != null) {
                        ipAdmissionBedTransferDTO.setPatientMRN(ipAdmissionBedTransfer.getIpAdmission().getPatient().getPatientIdExternal());
                        ipAdmissionBedTransferDTO.setPatientName(ipAdmissionBedTransfer.getIpAdmission().getPatient().getPatientName());
                        ipAdmissionBedTransferDTO.setGender(ipAdmissionBedTransfer.getIpAdmission().getPatient().getGender());
                    }
                    ipAdmissionBedTransferDTO.setTransferDate(ipAdmissionBedTransfer.getCreatedOn());
                    ipAdmissionBedTransferDTO.setFromBedID(ipAdmissionBedTransfer.getBedTransferredFrom().getId());
                    ipAdmissionBedTransferDTO.setFromBed(ipAdmissionBedTransfer.getBedTransferredFrom().getBedNumber());
                    ipAdmissionBedTransferDTO.setFromWard(ipAdmissionBedTransfer.getBedTransferredFrom().getWardMaster().getWardName());
                    ipAdmissionBedTransferDTO.setToBedID(ipAdmissionBedTransfer.getBedTransferredTo().getId());
                    ipAdmissionBedTransferDTO.setToBed(ipAdmissionBedTransfer.getBedTransferredTo().getBedNumber());
                    ipAdmissionBedTransferDTO.setToWard(ipAdmissionBedTransfer.getBedTransferredTo().getWardMaster().getWardName());
                    ipAdmissionBedTransferDTO.setTransferredBy(ipAdmissionBedTransfer.getUserStaff().getFirstName() + " " + ipAdmissionBedTransfer.getUserStaff().getLastName());
                    ipAdmissionBedTransferDTO.setUserDepartment("NA");
                    //if actual bed is equal to transferred bed then set actualBed to true else false
                    if (ipAdmissionBedTransfer.getIpAdmission().getBedMaster().getId().equals(ipAdmissionBedTransfer.getBedTransferredTo().getId()))
                        ipAdmissionBedTransferDTO.setOriginalBed(true);
                    else
                        ipAdmissionBedTransferDTO.setOriginalBed(false);
                    // add details to list
                    ipAdmissionBedTransferDTOs.add(ipAdmissionBedTransferDTO);

                }
            }
            response = new GetBedTransferDetailsByIpServiceResponse(ipAdmissionBedTransferDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Bed transferred details Successfully");
            log.debug("Retrieved bed transferred Successfully");
        } catch (Exception e) {
            response = new GetBedTransferDetailsByIpServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Bed transfer details");
            log.error("Failed to retrieve bed transfer details");
        }
        return response;
    }
}
