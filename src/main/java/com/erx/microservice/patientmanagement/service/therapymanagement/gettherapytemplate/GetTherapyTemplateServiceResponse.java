package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplate;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyTemplateGetDTO;

import java.util.List;

public class GetTherapyTemplateServiceResponse extends CommonServiceResponse {

    private List<TherapyTemplateGetDTO> therapyTemplateGetDTOs;

    //constructor
    public GetTherapyTemplateServiceResponse() {
    }

    public GetTherapyTemplateServiceResponse(List<TherapyTemplateGetDTO> therapyTemplateGetDTOs) {
        this.therapyTemplateGetDTOs = therapyTemplateGetDTOs;
    }

    //getters and setters

    public List<TherapyTemplateGetDTO> getTherapyTemplateGetDTOs() {
        return therapyTemplateGetDTOs;
    }

    public void setTherapyTemplateGetDTOs(List<TherapyTemplateGetDTO> therapyTemplateGetDTOs) {
        this.therapyTemplateGetDTOs = therapyTemplateGetDTOs;
    }
}
