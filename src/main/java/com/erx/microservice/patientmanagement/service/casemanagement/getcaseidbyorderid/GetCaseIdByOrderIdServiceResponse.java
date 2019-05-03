package com.erx.microservice.patientmanagement.service.casemanagement.getcaseidbyorderid;



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

/*
* created by raushan on 28-08-2018
* */

public class GetCaseIdByOrderIdServiceResponse extends CommonServiceResponse {

    private Long caseId;

    //constructor


    public GetCaseIdByOrderIdServiceResponse(Long caseId) {
        this.caseId = caseId;
    }

    public GetCaseIdByOrderIdServiceResponse() {
    }

    //Getters and setters

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }
}
