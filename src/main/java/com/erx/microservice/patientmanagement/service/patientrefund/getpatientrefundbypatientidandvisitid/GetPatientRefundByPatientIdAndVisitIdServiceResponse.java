package com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbypatientidandvisitid;

/*
* created by Raushan on 06-02-2018
* */


import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundMinimalDTO;

import java.util.List;

public class GetPatientRefundByPatientIdAndVisitIdServiceResponse extends CommonServiceResponse {

    private List<PatientRefundMinimalDTO> patientRefundMinimalDTOs;

    //Getters and setters

    public GetPatientRefundByPatientIdAndVisitIdServiceResponse(List<PatientRefundMinimalDTO> patientRefundMinimalDTOs) {
        this.patientRefundMinimalDTOs = patientRefundMinimalDTOs;
    }

    public GetPatientRefundByPatientIdAndVisitIdServiceResponse() {

    }


    //constructor

    public List<PatientRefundMinimalDTO> getPatientRefundMinimalDTOs() {
        return patientRefundMinimalDTOs;
    }

    public void setPatientRefundMinimalDTOs(List<PatientRefundMinimalDTO> patientRefundMinimalDTOs) {
        this.patientRefundMinimalDTOs = patientRefundMinimalDTOs;
    }
}
