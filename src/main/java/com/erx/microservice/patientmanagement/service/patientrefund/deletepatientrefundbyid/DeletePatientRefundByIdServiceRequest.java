package com.erx.microservice.patientmanagement.service.patientrefund.deletepatientrefundbyid;

/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeletePatientRefundByIdServiceRequest implements CommonServiceRequest {

    private Long refundId;

    //constructor
    public DeletePatientRefundByIdServiceRequest(Long refundId) {
        this.refundId = refundId;
    }

    //getters and setters
    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }
}
