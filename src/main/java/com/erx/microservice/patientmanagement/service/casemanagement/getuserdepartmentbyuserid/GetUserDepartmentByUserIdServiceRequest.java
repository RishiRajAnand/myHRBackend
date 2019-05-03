package com.erx.microservice.patientmanagement.service.casemanagement.getuserdepartmentbyuserid;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetUserDepartmentByUserIdServiceRequest implements CommonServiceRequest {

    private Long userId;

    //constructor

    public GetUserDepartmentByUserIdServiceRequest(Long userId) {
        this.userId = userId;
    }

    public GetUserDepartmentByUserIdServiceRequest() {
    }

    //setters and getters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
