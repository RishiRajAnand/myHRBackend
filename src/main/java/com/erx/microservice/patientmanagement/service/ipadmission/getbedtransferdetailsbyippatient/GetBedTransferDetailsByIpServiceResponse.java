package com.erx.microservice.patientmanagement.service.ipadmission.getbedtransferdetailsbyippatient;

/*
* created by Latha on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionBedTransferDTO;

import java.util.List;

public class GetBedTransferDetailsByIpServiceResponse extends CommonServiceResponse {

    private List<IpAdmissionBedTransferDTO> ipAdmissionBedTransferDTOs;
    //constructor

    public GetBedTransferDetailsByIpServiceResponse(List<IpAdmissionBedTransferDTO> ipAdmissionBedTransferDTOs) {
        this.ipAdmissionBedTransferDTOs = ipAdmissionBedTransferDTOs;
    }

    public GetBedTransferDetailsByIpServiceResponse() {
    }

// getters and setters

    public List<IpAdmissionBedTransferDTO> getIpAdmissionBedTransferDTOs() {
        return ipAdmissionBedTransferDTOs;
    }

    public void setIpAdmissionBedTransferDTOs(List<IpAdmissionBedTransferDTO> ipAdmissionBedTransferDTOs) {
        this.ipAdmissionBedTransferDTOs = ipAdmissionBedTransferDTOs;
    }
}
