package com.erx.microservice.patientmanagement.service.ipadmission.getdoctordepartment;

/*
* created by Brighty on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetDoctorDepartmentServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    //getters and setters

    public GetDoctorDepartmentServiceRequest() {
    }

    public GetDoctorDepartmentServiceRequest(Long clinicLocationId) {

        this.clinicLocationId = clinicLocationId;
    }

    //constructor

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
