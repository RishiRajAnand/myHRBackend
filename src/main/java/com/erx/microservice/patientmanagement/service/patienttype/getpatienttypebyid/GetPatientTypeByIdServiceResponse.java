package com.erx.microservice.patientmanagement.service.patienttype.getpatienttypebyid;



/*
* created by Brighty on 13-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeDTO;

public class GetPatientTypeByIdServiceResponse extends CommonServiceResponse {

    private PatientTypeDTO patientTypeDTO;

    //constructor
    public GetPatientTypeByIdServiceResponse(PatientTypeDTO patientTypeDTO) {
        this.patientTypeDTO = patientTypeDTO;
    }

    public GetPatientTypeByIdServiceResponse() {
    }

    //getters and setters
    public PatientTypeDTO getPatientTypeDTO() {

        return patientTypeDTO;
    }

    public void setPatientTypeDTO(PatientTypeDTO patientTypeDTO) {
        this.patientTypeDTO = patientTypeDTO;
    }
}
