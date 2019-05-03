package com.erx.microservice.patientmanagement.service.casemanagement.getexaminationbycaseid;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetExaminationByCaseIdServiceRequest implements CommonServiceRequest {

    private Long caseId;

    //constructor

    public GetExaminationByCaseIdServiceRequest(Long caseId) {
        this.caseId = caseId;
    }

    public GetExaminationByCaseIdServiceRequest() {
    }

    //setters and getters

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
