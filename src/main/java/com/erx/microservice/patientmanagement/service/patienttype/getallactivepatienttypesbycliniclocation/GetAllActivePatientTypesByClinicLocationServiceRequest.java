package com.erx.microservice.patientmanagement.service.patienttype.getallactivepatienttypesbycliniclocation;



/*
* created by Brighty on 13-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetAllActivePatientTypesByClinicLocationServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    public GetAllActivePatientTypesByClinicLocationServiceRequest(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getClinicLocationId() {

        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
