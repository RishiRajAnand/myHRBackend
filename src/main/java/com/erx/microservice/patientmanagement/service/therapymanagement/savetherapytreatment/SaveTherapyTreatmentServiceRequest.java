package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytreatment;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyDTO;

public class SaveTherapyTreatmentServiceRequest implements CommonServiceRequest {

    private SaveTreatmentTherapyDTO saveTreatmentTherapyDTO;

    //constructor

    public SaveTherapyTreatmentServiceRequest() {
    }

    public SaveTherapyTreatmentServiceRequest(SaveTreatmentTherapyDTO saveTreatmentTherapyDTO) {
        this.saveTreatmentTherapyDTO = saveTreatmentTherapyDTO;
    }

    //getters and setters

    public SaveTreatmentTherapyDTO getSaveTreatmentTherapyDTO() {
        return saveTreatmentTherapyDTO;
    }

    public void setSaveTreatmentTherapyDTO(SaveTreatmentTherapyDTO saveTreatmentTherapyDTO) {
        this.saveTreatmentTherapyDTO = saveTreatmentTherapyDTO;
    }
}
