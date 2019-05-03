package com.erx.microservice.patientmanagement.service.camptracker.campregistrationSearch;

/*
* created by Brighty on 07-12-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.CampRegistrationDTO;

import java.util.List;

public class CampRegistrationSearchServiceResponse extends CommonServiceResponse {

    private List<CampRegistrationDTO> campRegistrationDTOs;

    //getters and setters

    public CampRegistrationSearchServiceResponse() {
    }

    public CampRegistrationSearchServiceResponse(List<CampRegistrationDTO> campRegistrationDTOs) {
        this.campRegistrationDTOs = campRegistrationDTOs;
    }

    //constructor

    public List<CampRegistrationDTO> getCampRegistrationDTOs() {
        return campRegistrationDTOs;
    }

    public void setCampRegistrationDTOs(List<CampRegistrationDTO> campRegistrationDTOs) {
        this.campRegistrationDTOs = campRegistrationDTOs;
    }
}
