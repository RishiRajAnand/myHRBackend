package com.erx.microservice.patientmanagement.service.casemanagement.getdepartmentbycliniclocationid;

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

/*
 * created by Latha on 10-10-2018
 * */

public class GetDepartmentByClinicLocationIdServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    //constructor

    public GetDepartmentByClinicLocationIdServiceRequest() {
    }

    public GetDepartmentByClinicLocationIdServiceRequest(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    //getters and setters

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
