package com.erx.microservice.patientmanagement.service.casemanagement.getmedicinetreatmentbycaseid;

/*
* created by Latha on 27-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetMedicineTreatmentByCaseIdServiceRequest implements CommonServiceRequest {

    private Long caseId;

    //constructor

    public GetMedicineTreatmentByCaseIdServiceRequest(Long caseId) {
        this.caseId = caseId;
    }

    public GetMedicineTreatmentByCaseIdServiceRequest() {
    }

    //getters and setters

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
