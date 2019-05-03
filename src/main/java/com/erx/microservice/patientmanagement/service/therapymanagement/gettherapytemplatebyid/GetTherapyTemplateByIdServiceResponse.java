package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplatebyid;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyTemplateGetDTO;

import java.util.List;

public class GetTherapyTemplateByIdServiceResponse extends CommonServiceResponse {

    private TherapyTemplateGetDTO therapyTemplateGetDTO;

    //constructor
    public GetTherapyTemplateByIdServiceResponse() {
    }

    public GetTherapyTemplateByIdServiceResponse(TherapyTemplateGetDTO therapyTemplateGetDTO) {
        this.therapyTemplateGetDTO = therapyTemplateGetDTO;
    }

    //getters and setters

    public TherapyTemplateGetDTO getTherapyTemplateGetDTO() {
        return therapyTemplateGetDTO;
    }

    public void setTherapyTemplateGetDTO(TherapyTemplateGetDTO therapyTemplateGetDTO) {
        this.therapyTemplateGetDTO = therapyTemplateGetDTO;
    }
}
