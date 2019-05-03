package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytemplate;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTherapyMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTherapyTemplateDTO;

public class SaveTherapyTemplateServiceRequest implements CommonServiceRequest {

    private SaveTherapyTemplateDTO saveTherapyTemplateDTO;

    //constructor
    public SaveTherapyTemplateServiceRequest() {
    }

    public SaveTherapyTemplateServiceRequest(SaveTherapyTemplateDTO saveTherapyTemplateDTO) {
        this.saveTherapyTemplateDTO = saveTherapyTemplateDTO;
    }

    //getters and setters

    public SaveTherapyTemplateDTO getSaveTherapyTemplateDTO() {
        return saveTherapyTemplateDTO;
    }

    public void setSaveTherapyTemplateDTO(SaveTherapyTemplateDTO saveTherapyTemplateDTO) {
        this.saveTherapyTemplateDTO = saveTherapyTemplateDTO;
    }
}
