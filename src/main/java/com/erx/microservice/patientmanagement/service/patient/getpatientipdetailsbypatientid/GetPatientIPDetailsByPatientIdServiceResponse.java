package com.erx.microservice.patientmanagement.service.patient.getpatientipdetailsbypatientid;

/*
* created by Brighty on 30-05-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientDetailDTO;

import java.util.List;

public class GetPatientIPDetailsByPatientIdServiceResponse extends CommonServiceResponse{

    private PatientDetailDTO patientDetailDTO;

    //Constructor

    public GetPatientIPDetailsByPatientIdServiceResponse() {
    }

    //Getters and setters

    public PatientDetailDTO getPatientDetailDTO() {
        return patientDetailDTO;
    }

    public void setPatientDetailDTO(PatientDetailDTO patientDetailDTO) {
        this.patientDetailDTO = patientDetailDTO;
    }
}
