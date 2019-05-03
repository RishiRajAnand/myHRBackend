package com.erx.microservice.patientmanagement.service.ipadmission.bedtransfer;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.BedTransferDTO;

public class BedTransferServiceResponse extends CommonServiceResponse {
    private BedTransferDTO bedTransferDTO;

    // constructor

    public BedTransferServiceResponse(BedTransferDTO bedTransferDTO) {
        this.bedTransferDTO = bedTransferDTO;
    }

    public BedTransferServiceResponse() {

    }

    //getters and setters

    public BedTransferDTO getBedTransferDTO() {
        return bedTransferDTO;
    }

    public void setBedTransferDTO(BedTransferDTO bedTransferDTO) {
        this.bedTransferDTO = bedTransferDTO;
    }
}
