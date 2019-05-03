package com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapytemplate;

/*
* created by Latha on 14-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteTherapyTemplateServiceRequest implements CommonServiceRequest {

    private Long therapyTemplateId;

    //constructor

    public DeleteTherapyTemplateServiceRequest() {
    }

    public DeleteTherapyTemplateServiceRequest(Long therapyTemplateId) {
        this.therapyTemplateId = therapyTemplateId;
    }

    //getters and setters

    public Long getTherapyTemplateId() {
        return therapyTemplateId;
    }

    public void setTherapyTemplateId(Long therapyTemplateId) {
        this.therapyTemplateId = therapyTemplateId;
    }
}
