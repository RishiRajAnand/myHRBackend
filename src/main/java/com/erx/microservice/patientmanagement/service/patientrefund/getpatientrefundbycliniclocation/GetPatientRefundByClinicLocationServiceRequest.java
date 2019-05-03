package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbycliniclocation;
/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetPatientRefundByClinicLocationServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    //constructor
    public GetPatientRefundByClinicLocationServiceRequest(Long clinicLocationId) {
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
