package com.erx.microservice.patientmanagement.service.casemanagement.getcliniclocationsbyclinic;

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.ClinicLocationDTO;

import java.util.List;

/*
 * created by Latha on 10-10-2018
 * */

public class GetClinicLocationsByClinicServiceResponse extends CommonServiceResponse {

    private List<ClinicLocationDTO> clinicLocationDTOs;

    //constructor

    public GetClinicLocationsByClinicServiceResponse() {
    }

    public GetClinicLocationsByClinicServiceResponse(List<ClinicLocationDTO> clinicLocationDTOs) {
        this.clinicLocationDTOs = clinicLocationDTOs;
    }

    //getters and setters

    public List<ClinicLocationDTO> getClinicLocationDTOs() {
        return clinicLocationDTOs;
    }

    public void setClinicLocationDTOs(List<ClinicLocationDTO> clinicLocationDTOs) {
        this.clinicLocationDTOs = clinicLocationDTOs;
    }
}
