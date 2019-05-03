package com.erx.microservice.patientmanagement.service.casemanagement.getclinicpreference;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetClinicPreferenceByClinicServiceRequest implements CommonServiceRequest {

    private Long clinicId;

    //constructor

    public GetClinicPreferenceByClinicServiceRequest(Long clinicId) {
        this.clinicId = clinicId;
    }

    public GetClinicPreferenceByClinicServiceRequest() {
    }

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
}
