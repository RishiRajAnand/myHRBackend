package com.erx.microservice.patientmanagement.service.camptracker.deletecampregistration;

/*
* created by Brighty on 08-12-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteCampRegistrationServiceRequest implements CommonServiceRequest {

    private Long patientId;

    //getters and setters

    public DeleteCampRegistrationServiceRequest(Long patientId) {
        this.patientId = patientId;
    }

    public DeleteCampRegistrationServiceRequest() {
    }

    //constructor

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
