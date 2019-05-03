package com.erx.microservice.patientmanagement.service.ipadmission.movetoactualbed;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.BedMovementDTO;

public class MoveToActualBedServiceResponse extends CommonServiceResponse {

    private BedMovementDTO savedBedMovementDTO;

    //constructor
    public MoveToActualBedServiceResponse(BedMovementDTO savedBedMovementDTO) {
        this.savedBedMovementDTO = savedBedMovementDTO;
    }

    public MoveToActualBedServiceResponse() {

    }

    //getters and setters

    public BedMovementDTO getSavedBedMovementDTO() {
        return savedBedMovementDTO;
    }

    public void setSavedBedMovementDTO(BedMovementDTO savedBedMovementDTO) {
        this.savedBedMovementDTO = savedBedMovementDTO;
    }
}
