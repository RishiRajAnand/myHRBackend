package com.erx.microservice.patientmanagement.service.casemanagement.allcasesbypatientanddoctor;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.AllCasesDTO;

import java.util.List;

public class AllCasesByPatientAndDoctorServiceResponse extends CommonServiceResponse {

    private List<AllCasesDTO> allCasesDTOs;

    //constructor

    public AllCasesByPatientAndDoctorServiceResponse(List<AllCasesDTO> allCasesDTOs) {
        this.allCasesDTOs = allCasesDTOs;
    }

    public AllCasesByPatientAndDoctorServiceResponse() {
    }

    //getters and setters

    public List<AllCasesDTO> getAllCasesDTOs() {
        return allCasesDTOs;
    }

    public void setAllCasesDTOs(List<AllCasesDTO> allCasesDTOs) {
        this.allCasesDTOs = allCasesDTOs;
    }
}
