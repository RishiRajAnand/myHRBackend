package com.erx.microservice.patientmanagement.service.casemanagement.getcliniclocationsbyclinic;

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

/*
 * created by Latha on 10-10-2018
 * */

public class GetClinicLocationsByClinicServiceRequest implements CommonServiceRequest {

    private Long clinicId;

    //constructor

    public GetClinicLocationsByClinicServiceRequest() {
    }

    public GetClinicLocationsByClinicServiceRequest(Long clinicId) {
        this.clinicId = clinicId;
    }

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
}
