package com.erx.microservice.patientmanagement.service.patienttype.deletepatienttype;


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeletePatientTypeServiceRequest implements CommonServiceRequest {

    private Long patientTypeId;

    public DeletePatientTypeServiceRequest(Long patientTypeId) {
        this.patientTypeId = patientTypeId;
    }

    public Long getPatientTypeId() {
        return patientTypeId;
    }

    public void setPatientTypeId(Long patientTypeId) {
        this.patientTypeId = patientTypeId;
    }
}
