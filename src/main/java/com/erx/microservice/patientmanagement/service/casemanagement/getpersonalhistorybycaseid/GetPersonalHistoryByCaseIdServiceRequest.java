package com.erx.microservice.patientmanagement.service.casemanagement.getpersonalhistorybycaseid;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetPersonalHistoryByCaseIdServiceRequest implements CommonServiceRequest {

    private Long caseId;

    //constructor

    public GetPersonalHistoryByCaseIdServiceRequest(Long caseId) {
        this.caseId = caseId;
    }

    public GetPersonalHistoryByCaseIdServiceRequest() {
    }

    //setters and getters

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
