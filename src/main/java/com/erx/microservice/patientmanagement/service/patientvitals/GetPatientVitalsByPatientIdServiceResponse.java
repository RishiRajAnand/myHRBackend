package com.erx.microservice.patientmanagement.service.patientvitals;

/*
* created by Latha on 17-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientVitalsDTO;

public class GetPatientVitalsByPatientIdServiceResponse extends CommonServiceResponse {

    private PatientVitalsDTO patientVitalsDTO;

    //constructor

    public GetPatientVitalsByPatientIdServiceResponse(PatientVitalsDTO patientVitalsDTO) {
        this.patientVitalsDTO = patientVitalsDTO;
    }

    public GetPatientVitalsByPatientIdServiceResponse() {
    }

    // getters and setters
    public PatientVitalsDTO getPatientVitalsDTO() {
        return patientVitalsDTO;
    }

    public void setPatientVitalsDTO(PatientVitalsDTO patientVitalsDTO) {
        this.patientVitalsDTO = patientVitalsDTO;
    }
}
