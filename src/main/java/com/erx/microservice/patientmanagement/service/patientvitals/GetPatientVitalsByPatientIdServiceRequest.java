package com.erx.microservice.patientmanagement.service.patientvitals;


/*
* created by Latha on 17-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetPatientVitalsByPatientIdServiceRequest implements CommonServiceRequest {

    private Long patientId;
    private Long clinicLocationId;
    private Long clinicId;
    private Long userId;

    // constructor
    public GetPatientVitalsByPatientIdServiceRequest() {
    }

    public GetPatientVitalsByPatientIdServiceRequest(Long patientId, Long clinicLocationId, Long clinicId, Long userId) {
        this.patientId = patientId;
        this.clinicLocationId = clinicLocationId;
        this.clinicId = clinicId;
        this.userId = userId;
    }

    // getters and setters
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
