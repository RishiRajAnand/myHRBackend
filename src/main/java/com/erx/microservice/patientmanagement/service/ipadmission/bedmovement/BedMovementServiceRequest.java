package com.erx.microservice.patientmanagement.service.ipadmission.bedmovement;

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.BedMovementDTO;

/*
* created by Latha on 29-11-2017
* */
public class BedMovementServiceRequest implements CommonServiceRequest {
    private BedMovementDTO bedMovementDTO;

    //constructor

    public BedMovementServiceRequest(BedMovementDTO bedMovementDTO) {
        this.bedMovementDTO = bedMovementDTO;
    }

    // getters and setters

    public BedMovementDTO getBedMovementDTO() {
        return bedMovementDTO;
    }

    public void setBedMovementDTO(BedMovementDTO bedMovementDTO) {
        this.bedMovementDTO = bedMovementDTO;
    }
}
