package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplatebyid;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetTherapyTemplateByIdServiceRequest implements CommonServiceRequest {

    private Long therapyTemplateId;

    //constructor

    public GetTherapyTemplateByIdServiceRequest() {
    }

    public GetTherapyTemplateByIdServiceRequest(Long therapyTemplateId) {
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
