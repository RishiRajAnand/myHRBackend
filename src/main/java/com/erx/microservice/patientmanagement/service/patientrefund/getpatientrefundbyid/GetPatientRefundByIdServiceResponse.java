package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbyid;

/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDetailDTO;

public class GetPatientRefundByIdServiceResponse extends CommonServiceResponse {

    private PatientRefundDetailDTO patientRefundDetailDTO;

    public GetPatientRefundByIdServiceResponse() {

    }

    //constructor
    public GetPatientRefundByIdServiceResponse(PatientRefundDetailDTO patientRefundDetailDTO) {
        this.patientRefundDetailDTO = patientRefundDetailDTO;
    }

    //Getters and setters
    public PatientRefundDetailDTO getPatientRefundDetailDTO() {
        return patientRefundDetailDTO;
    }

    public void setPatientRefundDetailDTO(PatientRefundDetailDTO patientRefundDetailDTO) {
        this.patientRefundDetailDTO = patientRefundDetailDTO;
    }
}
