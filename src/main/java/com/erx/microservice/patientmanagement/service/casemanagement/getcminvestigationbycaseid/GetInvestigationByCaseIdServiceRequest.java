package com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationbycaseid;

/*
* created by Latha on 19-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetInvestigationByCaseIdServiceRequest implements CommonServiceRequest {

    private Long caseId;

    //constructor

    public GetInvestigationByCaseIdServiceRequest(Long caseId) {
        this.caseId = caseId;
    }

    public GetInvestigationByCaseIdServiceRequest() {
    }

    //getters and setters

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
