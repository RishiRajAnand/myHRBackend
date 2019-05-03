package com.erx.microservice.patientmanagement.service.patienttype.getpatienttypes;



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeByClinicLocationDTO;

import java.util.List;

/*
* created by Brighty on 09-11-2017
* */

public class GetPatientTypesServiceResponse extends CommonServiceResponse {

    private List<PatientTypeByClinicLocationDTO> patientTypeByClinicLocationDTOs;

    //constructor
    public GetPatientTypesServiceResponse(List<PatientTypeByClinicLocationDTO>
                                                           patientTypeByClinicLocationDTOs) {
        this.patientTypeByClinicLocationDTOs = patientTypeByClinicLocationDTOs;
    }

    public GetPatientTypesServiceResponse() {
    }

    //Getters and setters
    public List<PatientTypeByClinicLocationDTO> getPatientTypeByClinicLocationDTOs() {

        return patientTypeByClinicLocationDTOs;
    }

    public void setPatientTypeMasterByClinicLocationDTOs(List<PatientTypeByClinicLocationDTO>
                                                                 patientTypeByClinicLocationDTOs) {
        this.patientTypeByClinicLocationDTOs = patientTypeByClinicLocationDTOs;
    }
}
