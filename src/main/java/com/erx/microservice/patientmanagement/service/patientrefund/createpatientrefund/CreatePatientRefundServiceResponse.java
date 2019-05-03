package com.erx.microservice.patientmanagement.service.patientrefund.createpatientrefund;

/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDetailDTO;

public class CreatePatientRefundServiceResponse extends CommonServiceResponse {

    private PatientRefundDetailDTO patientRefundDetailDTO;

    public CreatePatientRefundServiceResponse() {

    }

    //constructor
    public CreatePatientRefundServiceResponse(PatientRefundDetailDTO patientRefundDetailDTO) {
        this.patientRefundDetailDTO = patientRefundDetailDTO;
    }

    //getters and setters
    public PatientRefundDetailDTO getPatientRefundDetailDTO() {
        return patientRefundDetailDTO;
    }

    public void setPatientRefundDetailDTO(PatientRefundDetailDTO patientRefundDetailDTO) {
        this.patientRefundDetailDTO = patientRefundDetailDTO;
    }
}
