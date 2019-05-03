package com.erx.microservice.patientmanagement.service.patient.getalladmittedpatientbycliniclocation;

/*
* created by Brighty on 30-05-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetAllAdmittedPatientByClinicLocationServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    //Constructor

    public GetAllAdmittedPatientByClinicLocationServiceRequest(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public GetAllAdmittedPatientByClinicLocationServiceRequest() {
    }

    //getters and setters

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
