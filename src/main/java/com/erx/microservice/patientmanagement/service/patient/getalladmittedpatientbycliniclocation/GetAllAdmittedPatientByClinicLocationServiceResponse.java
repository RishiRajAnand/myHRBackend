package com.erx.microservice.patientmanagement.service.patient.getalladmittedpatientbycliniclocation;

/*
* created by Brighty on 30-05-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientDetailDTO;

import java.util.List;

public class GetAllAdmittedPatientByClinicLocationServiceResponse extends CommonServiceResponse{

    private List<PatientDetailDTO> patientDetailDTOs;

    //Constructor

    public GetAllAdmittedPatientByClinicLocationServiceResponse() {
    }

    public GetAllAdmittedPatientByClinicLocationServiceResponse(List<PatientDetailDTO> patientDetailDTOs) {
        this.patientDetailDTOs = patientDetailDTOs;
    }

    //Getters and setters

    public List<PatientDetailDTO> getPatientDetailDTOs() {
        return patientDetailDTOs;
    }

    public void setPatientDetailDTOs(List<PatientDetailDTO> patientDetailDTOs) {
        this.patientDetailDTOs = patientDetailDTOs;
    }
}
