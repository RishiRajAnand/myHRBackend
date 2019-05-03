package com.erx.microservice.patientmanagement.service.casemanagement.getcaseidbyorderid;


/*
* created by raushan on 28-08-2018
* */


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetCaseIdByOrderIdServiceRequest implements CommonServiceRequest {

    private Long orderId;

    //Constructor

    public GetCaseIdByOrderIdServiceRequest(Long orderId) {
        this.orderId = orderId;
    }
    //getter and setter

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
