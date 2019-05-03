package com.erx.microservice.patientmanagement.service.casemanagement.updatepatientandvitals;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
public class UpdatePatientAndVitalsServiceResponse extends CommonServiceResponse {

    private Long patientId;
    private Long patientVitalId;

    //constructor

    public UpdatePatientAndVitalsServiceResponse(Long patientId, Long patientVitalId) {
        this.patientId = patientId;
        this.patientVitalId = patientVitalId;
    }

    public UpdatePatientAndVitalsServiceResponse() {
    }

    //getters and setters

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPatientVitalId() {
        return patientVitalId;
    }

    public void setPatientVitalId(Long patientVitalId) {
        this.patientVitalId = patientVitalId;
    }
}
