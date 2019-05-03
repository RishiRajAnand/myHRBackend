package com.erx.microservice.patientmanagement.service.patient.getpatientipdetailsbypatientid;

/*
* created by Brighty on 30-05-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetPatientIPDetailsByPatientIdServiceRequest implements CommonServiceRequest {

    private Long patientId;

    //Constructor

    public GetPatientIPDetailsByPatientIdServiceRequest(Long patientId) {
        this.patientId = patientId;
    }

    public GetPatientIPDetailsByPatientIdServiceRequest() {
    }

    //getters and setters

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
