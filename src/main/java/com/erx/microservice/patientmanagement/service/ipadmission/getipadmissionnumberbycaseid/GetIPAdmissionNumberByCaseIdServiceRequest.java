package com.erx.microservice.patientmanagement.service.ipadmission.getipadmissionnumberbycaseid;

/*
* created by raushan on 18-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetIPAdmissionNumberByCaseIdServiceRequest implements CommonServiceRequest {

    private Long caseId;
    //Constructor

    public GetIPAdmissionNumberByCaseIdServiceRequest(Long caseId) {
        this.caseId = caseId;
    }

    //getter and setter


    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}