package com.erx.microservice.patientmanagement.service.casemanagement.updatepatientandvitals;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientVitalsRequestDTO;

public class UpdatePatientAndVitalsServiceRequest implements CommonServiceRequest {

    private PatientVitalsRequestDTO patientVitalsRequestDTO;

    //constructor

    public UpdatePatientAndVitalsServiceRequest(PatientVitalsRequestDTO patientVitalsRequestDTO) {
        this.patientVitalsRequestDTO = patientVitalsRequestDTO;
    }

    public UpdatePatientAndVitalsServiceRequest() {
    }

    //getters and setters

    public PatientVitalsRequestDTO getPatientVitalsRequestDTO() {
        return patientVitalsRequestDTO;
    }

    public void setPatientVitalsRequestDTO(PatientVitalsRequestDTO patientVitalsRequestDTO) {
        this.patientVitalsRequestDTO = patientVitalsRequestDTO;
    }
}
