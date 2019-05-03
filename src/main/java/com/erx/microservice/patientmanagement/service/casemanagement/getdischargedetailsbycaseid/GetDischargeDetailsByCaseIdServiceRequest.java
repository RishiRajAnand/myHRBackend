package com.erx.microservice.patientmanagement.service.casemanagement.getdischargedetailsbycaseid;

/*
* created by Latha on 05-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetDischargeDetailsByCaseIdServiceRequest implements CommonServiceRequest {

    private Long caseId;
    private Long patientId;
    private Long userId;
    private Long clinicId;
    private Long clinicLocationId;

    //constructor

    public GetDischargeDetailsByCaseIdServiceRequest() {
    }

    public GetDischargeDetailsByCaseIdServiceRequest(Long caseId, Long patientId, Long userId, Long clinicId, Long clinicLocationId) {
        this.caseId = caseId;
        this.patientId = patientId;
        this.userId = userId;
        this.clinicId = clinicId;
        this.clinicLocationId = clinicLocationId;
    }
    //setters and getters

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
