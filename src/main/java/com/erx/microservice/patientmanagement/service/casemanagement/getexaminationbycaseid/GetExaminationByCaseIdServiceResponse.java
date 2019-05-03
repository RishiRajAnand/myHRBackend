package com.erx.microservice.patientmanagement.service.casemanagement.getexaminationbycaseid;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.GetCmExaminationDTO;

import java.util.List;

public class GetExaminationByCaseIdServiceResponse extends CommonServiceResponse {

    private List<GetCmExaminationDTO> getCmExaminationDTOs;

    //constructor

    public GetExaminationByCaseIdServiceResponse() {
    }

    public GetExaminationByCaseIdServiceResponse(List<GetCmExaminationDTO> getCmExaminationDTOs) {
        this.getCmExaminationDTOs = getCmExaminationDTOs;
    }

    //getters and setters

    public List<GetCmExaminationDTO> getGetCmExaminationDTOs() {
        return getCmExaminationDTOs;
    }

    public void setGetCmExaminationDTOs(List<GetCmExaminationDTO> getCmExaminationDTOs) {
        this.getCmExaminationDTOs = getCmExaminationDTOs;
    }
}
