package com.erx.microservice.patientmanagement.service.casemanagement.getuserdepartmentbyuserid;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.GetCmPersonalHistoryDTO;

import java.util.List;

public class GetUserDepartmentByUserIdServiceResponse extends CommonServiceResponse {

    private Long userDepartmentId;

    //constructor

    public GetUserDepartmentByUserIdServiceResponse() {
    }

    public GetUserDepartmentByUserIdServiceResponse(Long userDepartmentId) {
        this.userDepartmentId = userDepartmentId;
    }

    //getters and setters


    public Long getUserDepartmentId() {
        return userDepartmentId;
    }

    public void setUserDepartmentId(Long userDepartmentId) {
        this.userDepartmentId = userDepartmentId;
    }
}
