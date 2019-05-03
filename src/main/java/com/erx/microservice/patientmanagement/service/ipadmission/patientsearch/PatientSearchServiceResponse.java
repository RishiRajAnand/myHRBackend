package com.erx.microservice.patientmanagement.service.ipadmission.patientsearch;
/*
* created by Latha on 06-12-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientDTO;

import java.util.List;

public class PatientSearchServiceResponse extends CommonServiceResponse {

    private List<PatientDTO> patientDTOs;

    //getters and setters

    public PatientSearchServiceResponse(List<PatientDTO> patientDTOs) {
        this.patientDTOs = patientDTOs;
    }

    public PatientSearchServiceResponse() {
    }

    //constructor

    public List<PatientDTO> getPatientDTOs() {
        return patientDTOs;
    }

    public void setPatientDTOs(List<PatientDTO> patientDTOs) {
        this.patientDTOs = patientDTOs;
    }
}
