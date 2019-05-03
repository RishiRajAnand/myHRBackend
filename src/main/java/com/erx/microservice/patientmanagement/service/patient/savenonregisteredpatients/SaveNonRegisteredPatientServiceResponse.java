package com.erx.microservice.patientmanagement.service.patient.savenonregisteredpatients;
/*
 * created by Manisha on 16-07-2018
 * */
import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class SaveNonRegisteredPatientServiceResponse extends CommonServiceResponse {
    private Long patientId;

    public SaveNonRegisteredPatientServiceResponse() {
    }

    public SaveNonRegisteredPatientServiceResponse(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
