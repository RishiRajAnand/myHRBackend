package com.erx.microservice.patientmanagement.service.camptracker.campregistrationsearchbydaterange;

/*
* created by Brighty on 08-12-17
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.CampRegistrationDTO;

import java.util.List;

public class CampRegistrationSearchByDateRangeServiceResponse extends CommonServiceResponse {

    private List<CampRegistrationDTO> campRegistrationDTOs;

    //getters and setters

    public CampRegistrationSearchByDateRangeServiceResponse(List<CampRegistrationDTO> campRegistrationDTOs) {
        this.campRegistrationDTOs = campRegistrationDTOs;
    }

    public CampRegistrationSearchByDateRangeServiceResponse() {
    }

    //constructor

    public List<CampRegistrationDTO> getCampRegistrationDTOs() {
        return campRegistrationDTOs;
    }

    public void setCampRegistrationDTOs(List<CampRegistrationDTO> campRegistrationDTOs) {
        this.campRegistrationDTOs = campRegistrationDTOs;
    }
}
