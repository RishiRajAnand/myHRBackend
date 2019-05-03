package com.erx.microservice.patientmanagement.service.patienttype.getpatienttypebyid;


/*
* created by Brighty on 13-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetPatientTypeByIdServiceRequest implements CommonServiceRequest {

    private Long patientTypeId;

    //constructor
    public GetPatientTypeByIdServiceRequest(Long patientTypeId) {
        this.patientTypeId = patientTypeId;
    }

    //getters and setters
    public Long getPatientTypeId() {

        return patientTypeId;
    }

    public void setPatientTypeId(Long patientTypeId) {
        this.patientTypeId = patientTypeId;
    }
}
