package com.erx.microservice.patientmanagement.service.patienttype.getallactivepatienttypesbycliniclocation;



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeDTO;

import java.util.List;

/*
* created by Brighty on 13-11-2017
* */

public class GetAllActivePatientTypesByClinicLocationServiceResponse extends CommonServiceResponse {

    private List<PatientTypeDTO> patientTypeDTOs;

    public GetAllActivePatientTypesByClinicLocationServiceResponse() {
    }

    public GetAllActivePatientTypesByClinicLocationServiceResponse(List<PatientTypeDTO> patientTypeDTOs) {
        this.patientTypeDTOs = patientTypeDTOs;
    }

    public List<PatientTypeDTO> getPatientTypeDTOs() {
        return patientTypeDTOs;
    }

    public void setPatientTypeDTOs(List<PatientTypeDTO> patientTypeDTOs) {
        this.patientTypeDTOs = patientTypeDTOs;
    }
}
