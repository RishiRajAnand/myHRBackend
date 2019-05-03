package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplate;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetTherapyTemplateServiceRequest implements CommonServiceRequest {

    private Long clinicId;

    //constructor

    public GetTherapyTemplateServiceRequest(Long clinicId) {
        this.clinicId = clinicId;
    }

    public GetTherapyTemplateServiceRequest() {
    }

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
}
