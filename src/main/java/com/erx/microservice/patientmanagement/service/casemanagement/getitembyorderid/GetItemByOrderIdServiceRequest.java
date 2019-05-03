/*
* created by latha on 01-10-2018
* */
package com.erx.microservice.patientmanagement.service.casemanagement.getitembyorderid;


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetItemByOrderIdServiceRequest implements CommonServiceRequest {
    private String orderId;

    //Constructor

    public GetItemByOrderIdServiceRequest(String orderId) {
        this.orderId = orderId;
    }

    //getter and setter
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}