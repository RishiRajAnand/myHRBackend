package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytemplate;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTherapyMasterResponseDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTherapyTemplateResponseDTO;

import java.util.List;

public class SaveTherapyTemplateServiceResponse extends CommonServiceResponse {

     private SaveTherapyTemplateResponseDTO saveTherapyTemplateResponseDTO;

    //constructor
    public SaveTherapyTemplateServiceResponse() {
    }

    public SaveTherapyTemplateServiceResponse(SaveTherapyTemplateResponseDTO saveTherapyTemplateResponseDTO) {
        this.saveTherapyTemplateResponseDTO = saveTherapyTemplateResponseDTO;
    }
    //getters and setters

    public SaveTherapyTemplateResponseDTO getSaveTherapyTemplateResponseDTO() {
        return saveTherapyTemplateResponseDTO;
    }

    public void setSaveTherapyTemplateResponseDTO(SaveTherapyTemplateResponseDTO saveTherapyTemplateResponseDTO) {
        this.saveTherapyTemplateResponseDTO = saveTherapyTemplateResponseDTO;
    }
}
