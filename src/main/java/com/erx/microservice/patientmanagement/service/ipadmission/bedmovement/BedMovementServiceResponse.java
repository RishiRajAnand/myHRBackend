package com.erx.microservice.patientmanagement.service.ipadmission.bedmovement;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.BedMovementDTO;

public class BedMovementServiceResponse extends CommonServiceResponse {
    private BedMovementDTO savedBedMovementDTO;
    //constructor

    public BedMovementServiceResponse(BedMovementDTO savedBedMovementDTO) {
        this.savedBedMovementDTO = savedBedMovementDTO;
    }

    public BedMovementServiceResponse() {

    }

    //getters and setters

    public BedMovementDTO getSavedBedMovementDTO() {
        return savedBedMovementDTO;
    }

    public void setSavedBedMovementDTO(BedMovementDTO savedBedMovementDTO) {
        this.savedBedMovementDTO = savedBedMovementDTO;
    }
}
