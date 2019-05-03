package com.erx.microservice.patientmanagement.service.casemanagement.gettherapyplanningIdsbyorderid;

/*
* created by Raushan on 25-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetTherapyPlanningIdsByOrderIdServiceRequest implements CommonServiceRequest {
    private Long orderId;
    //Constructor


    public GetTherapyPlanningIdsByOrderIdServiceRequest(Long orderId) {
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
