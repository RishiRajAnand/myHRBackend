package com.erx.microservice.patientmanagement.service.casemanagement.getclinicpreference;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.CampRegistrationDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.ClinicPreferenceDTO;

import java.util.List;

public class GetClinicPreferenceByClinicServiceResponse extends CommonServiceResponse {

    private ClinicPreferenceDTO clinicPreferenceDTO;

    //constructor

    public GetClinicPreferenceByClinicServiceResponse(ClinicPreferenceDTO clinicPreferenceDTO) {
        this.clinicPreferenceDTO = clinicPreferenceDTO;
    }

    public GetClinicPreferenceByClinicServiceResponse() {
    }

    //getters and setters

    public ClinicPreferenceDTO getClinicPreferenceDTO() {
        return clinicPreferenceDTO;
    }

    public void setClinicPreferenceDTO(ClinicPreferenceDTO clinicPreferenceDTO) {
        this.clinicPreferenceDTO = clinicPreferenceDTO;
    }
}
