package com.erx.microservice.patientmanagement.service.ipadmission.getcasedetailsbypatient;

/*
* created by Latha on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientCaseDTO;

import java.util.List;

public class GetCaseDetailsByPatientServiceResponse extends CommonServiceResponse {

    private List<PatientCaseDTO> patientCaseDTOs;

    //constructor

    public GetCaseDetailsByPatientServiceResponse(List<PatientCaseDTO> patientCaseDTOs) {
        this.patientCaseDTOs = patientCaseDTOs;
    }

    public GetCaseDetailsByPatientServiceResponse() {

    }

    //getters and setters

    public List<PatientCaseDTO> getPatientCaseDTOs() {
        return patientCaseDTOs;
    }

    public void setPatientCaseDTOs(List<PatientCaseDTO> patientCaseDTOs) {
        this.patientCaseDTOs = patientCaseDTOs;
    }
}
