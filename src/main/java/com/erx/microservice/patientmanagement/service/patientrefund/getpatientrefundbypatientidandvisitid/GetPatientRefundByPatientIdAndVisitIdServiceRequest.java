package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbypatientidandvisitid;

/*
* created by Raushan on 06-02-2018
* */


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetPatientRefundByPatientIdAndVisitIdServiceRequest implements CommonServiceRequest {

    private Long patientId;
    private Long visitId;

    //Getters and setters

    //Constructor
    public GetPatientRefundByPatientIdAndVisitIdServiceRequest(Long patientId, Long visitId) {
        this.patientId = patientId;
        this.visitId = visitId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }
}
