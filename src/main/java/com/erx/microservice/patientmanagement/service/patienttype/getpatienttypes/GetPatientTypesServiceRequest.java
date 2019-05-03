package com.erx.microservice.patientmanagement.service.patienttype.getpatienttypes;


/*
* created by Brighty on 09-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetPatientTypesServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    //Constructor
    public GetPatientTypesServiceRequest(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
