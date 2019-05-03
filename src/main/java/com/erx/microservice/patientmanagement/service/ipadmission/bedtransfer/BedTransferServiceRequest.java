package com.erx.microservice.patientmanagement.service.ipadmission.bedtransfer;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.BedTransferDTO;

public class BedTransferServiceRequest implements CommonServiceRequest {

    private BedTransferDTO bedTransferDTO;

    //constructor

    public BedTransferServiceRequest(BedTransferDTO bedTransferDTO) {
        this.bedTransferDTO = bedTransferDTO;
    }

    //getters and setters


    public BedTransferDTO getBedTransferDTO() {
        return bedTransferDTO;
    }

    public void setBedTransferDTO(BedTransferDTO bedTransferDTO) {
        this.bedTransferDTO = bedTransferDTO;
    }
}
