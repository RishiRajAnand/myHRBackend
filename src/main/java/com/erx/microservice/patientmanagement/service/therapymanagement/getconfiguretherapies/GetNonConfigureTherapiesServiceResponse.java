package com.erx.microservice.patientmanagement.service.therapymanagement.getconfiguretherapies;

/*
* created by Latha on 27-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.NonConfigureTherapiesDTO;

import java.util.List;


public class GetNonConfigureTherapiesServiceResponse extends CommonServiceResponse {

    private List<NonConfigureTherapiesDTO> nonConfigureTherapiesDTOs;

    //constructor

    public GetNonConfigureTherapiesServiceResponse() {
    }

    public GetNonConfigureTherapiesServiceResponse(List<NonConfigureTherapiesDTO> nonConfigureTherapiesDTOs) {
        this.nonConfigureTherapiesDTOs = nonConfigureTherapiesDTOs;
    }

    //getters and setters

    public List<NonConfigureTherapiesDTO> getNonConfigureTherapiesDTOs() {
        return nonConfigureTherapiesDTOs;
    }

    public void setNonConfigureTherapiesDTOs(List<NonConfigureTherapiesDTO> nonConfigureTherapiesDTOs) {
        this.nonConfigureTherapiesDTOs = nonConfigureTherapiesDTOs;
    }
}
