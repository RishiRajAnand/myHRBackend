package com.erx.microservice.patientmanagement.service.patient.convertnonregisteredtoregisteredpatient;
/*
created By Manisha on 08-10-2018
 */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class ConvertNonRegisteredToRegisterPatientServiceRequest implements CommonServiceRequest {
    private Long patientId;
    private Long clinicId;

    //constructor
    public ConvertNonRegisteredToRegisterPatientServiceRequest() {
    }

    public ConvertNonRegisteredToRegisterPatientServiceRequest(Long patientId, Long clinicId) {
        this.patientId = patientId;
        this.clinicId = clinicId;
    }

    //getter and setter


    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
}
