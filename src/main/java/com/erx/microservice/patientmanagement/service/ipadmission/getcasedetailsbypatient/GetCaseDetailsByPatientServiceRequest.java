package com.erx.microservice.patientmanagement.service.ipadmission.getcasedetailsbypatient;

/*
* created by Latha on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetCaseDetailsByPatientServiceRequest implements CommonServiceRequest {

    private Long patientId;
    private Long clinicLocationId;

    //constructor

    public GetCaseDetailsByPatientServiceRequest(Long patientId, Long clinicLocationId) {
        this.patientId = patientId;
        this.clinicLocationId = clinicLocationId;
    }

    //getters and setters


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
}
