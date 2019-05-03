package com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetreatment;

/*
* created by Latha on 25-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveMedicineDTO;

public class SaveMedicineTreatmentServiceRequest implements CommonServiceRequest {

    private SaveMedicineDTO saveMedicineDTO;

    //constructor

    public SaveMedicineTreatmentServiceRequest(SaveMedicineDTO saveMedicineDTO) {
        this.saveMedicineDTO = saveMedicineDTO;
    }

    public SaveMedicineTreatmentServiceRequest() {
    }

//getters and setters

    public SaveMedicineDTO getSaveMedicineDTO() {
        return saveMedicineDTO;
    }

    public void setSaveMedicineDTO(SaveMedicineDTO saveMedicineDTO) {
        this.saveMedicineDTO = saveMedicineDTO;
    }
}
