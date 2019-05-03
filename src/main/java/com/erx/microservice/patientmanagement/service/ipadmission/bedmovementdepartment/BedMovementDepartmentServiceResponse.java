package com.erx.microservice.patientmanagement.service.ipadmission.bedmovementdepartment;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.BedMovementDTO;

public class BedMovementDepartmentServiceResponse extends CommonServiceResponse {

    private BedMovementDTO savedBedMovementDTO;

    //constructor

    public BedMovementDepartmentServiceResponse(BedMovementDTO savedBedMovementDTO) {
        this.savedBedMovementDTO = savedBedMovementDTO;
    }

    public BedMovementDepartmentServiceResponse() {

    }


    // getters and setters

    public BedMovementDTO getSavedBedMovementDTO() {
        return savedBedMovementDTO;
    }

    public void setSavedBedMovementDTO(BedMovementDTO savedBedMovementDTO) {
        this.savedBedMovementDTO = savedBedMovementDTO;
    }
}
