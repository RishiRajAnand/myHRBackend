package com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapymaster;

/*
* created by Latha on 10-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteTherapyMasterServiceRequest implements CommonServiceRequest {

    private Long therapyMasterId;

    //constructor

    public DeleteTherapyMasterServiceRequest(Long therapyMasterId) {
        this.therapyMasterId = therapyMasterId;
    }

    public DeleteTherapyMasterServiceRequest() {
    }

    //getters and setters
    public Long getTherapyMasterId() {
        return therapyMasterId;
    }

    public void setTherapyMasterId(Long therapyMasterId) {
        this.therapyMasterId = therapyMasterId;
    }
}
