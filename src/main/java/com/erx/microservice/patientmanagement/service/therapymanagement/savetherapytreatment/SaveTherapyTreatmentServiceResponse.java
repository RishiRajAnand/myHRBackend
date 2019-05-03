package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytreatment;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyResponseDTO;

public class SaveTherapyTreatmentServiceResponse extends CommonServiceResponse {

    private SaveTreatmentTherapyResponseDTO saveTreatmentTherapyResponseDTO;

    //constructor

    public SaveTherapyTreatmentServiceResponse() {
    }

    public SaveTherapyTreatmentServiceResponse(SaveTreatmentTherapyResponseDTO saveTreatmentTherapyResponseDTO) {
        this.saveTreatmentTherapyResponseDTO = saveTreatmentTherapyResponseDTO;
    }

    //getters and setters

    public SaveTreatmentTherapyResponseDTO getSaveTreatmentTherapyResponseDTO() {
        return saveTreatmentTherapyResponseDTO;
    }

    public void setSaveTreatmentTherapyResponseDTO(SaveTreatmentTherapyResponseDTO saveTreatmentTherapyResponseDTO) {
        this.saveTreatmentTherapyResponseDTO = saveTreatmentTherapyResponseDTO;
    }
}
