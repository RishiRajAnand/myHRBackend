package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyserviceid;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyMasterGetDTO;


public class GetTherapyMasterByServiceIdServiceResponse extends CommonServiceResponse {

    private TherapyMasterGetDTO therapyMasterGetDTO;

    //constructor

    public GetTherapyMasterByServiceIdServiceResponse() {
    }

    public GetTherapyMasterByServiceIdServiceResponse(TherapyMasterGetDTO therapyMasterGetDTO) {
        this.therapyMasterGetDTO = therapyMasterGetDTO;
    }

    //getters and setters

    public TherapyMasterGetDTO getTherapyMasterGetDTO() {
        return therapyMasterGetDTO;
    }

    public void setTherapyMasterGetDTO(TherapyMasterGetDTO therapyMasterGetDTO) {
        this.therapyMasterGetDTO = therapyMasterGetDTO;
    }
}
