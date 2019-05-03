package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbycliniclocation;

/*
* created by Brighty on 27-11-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDetailDTO;

import java.util.List;

public class GetPatientRefundByClinicLocationServiceResponse extends CommonServiceResponse {

    private List<PatientRefundDetailDTO> patientRefundDetailDTOS;

    public GetPatientRefundByClinicLocationServiceResponse() {

    }

    //constructor
    public GetPatientRefundByClinicLocationServiceResponse(List<PatientRefundDetailDTO> patientRefundDetailDTOS) {
        this.patientRefundDetailDTOS = patientRefundDetailDTOS;
    }

    //Getters and setters
    public List<PatientRefundDetailDTO> getPatientRefundDetailDTOS() {
        return patientRefundDetailDTOS;
    }

    public void setPatientRefundDetailDTOS(List<PatientRefundDetailDTO> patientRefundDetailDTOS) {
        this.patientRefundDetailDTOS = patientRefundDetailDTOS;
    }
}
