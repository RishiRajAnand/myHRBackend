package com.erx.microservice.patientmanagement.service.patientrefund.createpatientrefund;
/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDTO;

public class CreatePatientRefundServiceRequest implements CommonServiceRequest {

    private PatientRefundDTO patientRefundDTO;

    //constructor
    public CreatePatientRefundServiceRequest(PatientRefundDTO patientRefundDTO) {
        this.patientRefundDTO = patientRefundDTO;
    }

    //getters and setters
    public PatientRefundDTO getPatientRefundDTO() {
        return patientRefundDTO;
    }

    public void setPatientRefundDTO(PatientRefundDTO patientRefundDTO) {
        this.patientRefundDTO = patientRefundDTO;
    }
}
