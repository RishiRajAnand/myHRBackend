package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymaster;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyMasterGetAllDTO;
import org.springframework.data.domain.Page;


public class GetTherapyMasterServiceResponse extends CommonServiceResponse {

    private Page<TherapyMasterGetAllDTO> therapyMasterGetAllDTOs;

    //constructor

    public GetTherapyMasterServiceResponse(Page<TherapyMasterGetAllDTO> therapyMasterGetAllDTOs) {
        this.therapyMasterGetAllDTOs = therapyMasterGetAllDTOs;
    }

    public GetTherapyMasterServiceResponse() {
    }

    //getters and setters

    public Page<TherapyMasterGetAllDTO> getTherapyMasterGetAllDTOs() {
        return therapyMasterGetAllDTOs;
    }

    public void setTherapyMasterGetAllDTOs(Page<TherapyMasterGetAllDTO> therapyMasterGetAllDTOs) {
        this.therapyMasterGetAllDTOs = therapyMasterGetAllDTOs;
    }
}
