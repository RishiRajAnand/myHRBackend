package com.erx.microservice.patientmanagement.service.casemanagement.getdoctorsbydepartmentid;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetDoctorsByDepartmentIdServiceRequest implements CommonServiceRequest {

    private Long departmentId;

    private Long clinicId;


    //constructor

    public GetDoctorsByDepartmentIdServiceRequest() {
    }

    public GetDoctorsByDepartmentIdServiceRequest(Long departmentId, Long clinicId) {
        this.departmentId = departmentId;
        this.clinicId = clinicId;
    }

    //getters and setters


    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
}
