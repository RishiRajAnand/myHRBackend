package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyid;

/*
* created by Latha on 08-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyMasterGetAllDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyMasterGetDTO;
import org.springframework.data.domain.Page;


public class GetTherapyMasterByIdServiceResponse extends CommonServiceResponse {

    private TherapyMasterGetDTO therapyMasterGetDTO;

    //constructor

    public GetTherapyMasterByIdServiceResponse() {
    }

    public GetTherapyMasterByIdServiceResponse(TherapyMasterGetDTO therapyMasterGetDTO) {
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
