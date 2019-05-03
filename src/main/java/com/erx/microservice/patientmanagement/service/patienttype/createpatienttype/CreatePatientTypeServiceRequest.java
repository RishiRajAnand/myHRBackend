package com.erx.microservice.patientmanagement.service.patienttype.createpatienttype;



/*
* created by Brighty on 13-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeDTO;

public class CreatePatientTypeServiceRequest implements CommonServiceRequest {

    private PatientTypeDTO patientTypeDTO;

    public CreatePatientTypeServiceRequest(PatientTypeDTO patientTypeDTO) {
        this.patientTypeDTO = patientTypeDTO;
    }

    public PatientTypeDTO getPatientTypeDTO() {

        return patientTypeDTO;
    }

    public void setPatientTypeDTO(PatientTypeDTO patientTypeDTO) {
        this.patientTypeDTO = patientTypeDTO;
    }
}
