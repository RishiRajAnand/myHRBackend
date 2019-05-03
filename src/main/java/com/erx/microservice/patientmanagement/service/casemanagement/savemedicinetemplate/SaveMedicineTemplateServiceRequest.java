package com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetemplate;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveMedicineTemplateDTO;

public class SaveMedicineTemplateServiceRequest implements CommonServiceRequest {

    private SaveMedicineTemplateDTO saveMedicineTemplateDTO;

    //constructor

    public SaveMedicineTemplateServiceRequest(SaveMedicineTemplateDTO saveMedicineTemplateDTO) {
        this.saveMedicineTemplateDTO = saveMedicineTemplateDTO;
    }

    public SaveMedicineTemplateServiceRequest() {
    }

    //getters and setters

    public SaveMedicineTemplateDTO getSaveMedicineTemplateDTO() {
        return saveMedicineTemplateDTO;
    }

    public void setSaveMedicineTemplateDTO(SaveMedicineTemplateDTO saveMedicineTemplateDTO) {
        this.saveMedicineTemplateDTO = saveMedicineTemplateDTO;
    }
}
