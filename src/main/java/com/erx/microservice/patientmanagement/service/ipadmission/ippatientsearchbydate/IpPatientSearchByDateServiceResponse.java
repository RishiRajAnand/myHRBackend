package com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearchbydate;

/*
* created by Brighty on 30-11-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientDTO;

import java.util.List;

public class IpPatientSearchByDateServiceResponse extends CommonServiceResponse {

    private List<PatientDTO> patientDTOs;

    //getters and setters

    public IpPatientSearchByDateServiceResponse(List<PatientDTO> patientDTOs) {
        this.patientDTOs = patientDTOs;
    }

    public IpPatientSearchByDateServiceResponse() {
    }

    //constructor

    public List<PatientDTO> getPatientDTOs() {
        return patientDTOs;
    }

    public void setPatientDTOs(List<PatientDTO> patientDTOs) {
        this.patientDTOs = patientDTOs;
    }
}
