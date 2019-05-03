package com.erx.microservice.patientmanagement.service.casemanagement.getpathyapathyabycmtreatmentid;

/*
* created by Latha on 02-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetPathyaPathyaByCmTreatmentIdServiceRequest implements CommonServiceRequest {

    private Long caseId;

    //constructor

    public GetPathyaPathyaByCmTreatmentIdServiceRequest(Long caseId) {
        this.caseId = caseId;
    }

    public GetPathyaPathyaByCmTreatmentIdServiceRequest() {
    }

    //getters and setters

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
