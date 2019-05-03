package com.erx.microservice.patientmanagement.service.casemanagement.getcomplaintsbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetComplaintsByCaseIdServiceRequest implements CommonServiceRequest {

    private Long caseId;

    //constructor

    public GetComplaintsByCaseIdServiceRequest(Long caseId) {
        this.caseId = caseId;
    }

    public GetComplaintsByCaseIdServiceRequest() {
    }

    //setters and getters

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
