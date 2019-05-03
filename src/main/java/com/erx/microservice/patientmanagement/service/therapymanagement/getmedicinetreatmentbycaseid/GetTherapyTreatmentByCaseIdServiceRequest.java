package com.erx.microservice.patientmanagement.service.therapymanagement.getmedicinetreatmentbycaseid;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetTherapyTreatmentByCaseIdServiceRequest implements CommonServiceRequest {

    private Long caseId;

    //constructor

    public GetTherapyTreatmentByCaseIdServiceRequest(Long caseId) {
        this.caseId = caseId;
    }

    public GetTherapyTreatmentByCaseIdServiceRequest() {
    }

    //getters and setters

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
