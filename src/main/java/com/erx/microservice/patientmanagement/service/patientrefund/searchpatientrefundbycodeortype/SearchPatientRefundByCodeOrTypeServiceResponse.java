package com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefundbycodeortype;

/*
* created by Brighty on 11-01-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDetailDTO;

import java.util.List;

public class SearchPatientRefundByCodeOrTypeServiceResponse extends CommonServiceResponse {

    private List<PatientRefundDetailDTO> patientRefundDetailDTOs;

    //Getters and setters

    public SearchPatientRefundByCodeOrTypeServiceResponse(List<PatientRefundDetailDTO> patientRefundDetailDTOs) {
        this.patientRefundDetailDTOs = patientRefundDetailDTOs;
    }

    public SearchPatientRefundByCodeOrTypeServiceResponse() {

    }

    //constructor

    public List<PatientRefundDetailDTO> getPatientRefundDetailDTOs() {
        return patientRefundDetailDTOs;
    }

    public void setPatientRefundDetailDTOs(List<PatientRefundDetailDTO> patientRefundDetailDTOs) {
        this.patientRefundDetailDTOs = patientRefundDetailDTOs;
    }
}
