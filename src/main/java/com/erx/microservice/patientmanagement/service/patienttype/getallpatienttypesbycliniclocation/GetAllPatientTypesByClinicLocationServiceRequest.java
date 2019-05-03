package com.erx.microservice.patientmanagement.service.patienttype.getallpatienttypesbycliniclocation;



/*
* created by Brighty on 13-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetAllPatientTypesByClinicLocationServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    public GetAllPatientTypesByClinicLocationServiceRequest(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getClinicLocationId() {

        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
