package com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigation;

/*
* created by Latha on 17-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveCmInvestigationResponseDTO;

public class SaveInvestigationServiceResponse extends CommonServiceResponse {

    private SaveCmInvestigationResponseDTO saveCmInvestigationResponseDTO;

    //constructor

    public SaveInvestigationServiceResponse() {
    }

    public SaveInvestigationServiceResponse(SaveCmInvestigationResponseDTO saveCmInvestigationResponseDTO) {
        this.saveCmInvestigationResponseDTO = saveCmInvestigationResponseDTO;
    }

    //getters and setters

    public SaveCmInvestigationResponseDTO getSaveCmInvestigationResponseDTO() {
        return saveCmInvestigationResponseDTO;
    }

    public void setSaveCmInvestigationResponseDTO(SaveCmInvestigationResponseDTO saveCmInvestigationResponseDTO) {
        this.saveCmInvestigationResponseDTO = saveCmInvestigationResponseDTO;
    }
}
