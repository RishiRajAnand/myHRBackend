package com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigation;

/*
* created by Latha on 17-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveComplaintsDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveInvestigationDTO;

public class SaveInvestigationServiceRequest implements CommonServiceRequest {

    private SaveInvestigationDTO saveInvestigationDTO;

    //constructor

    public SaveInvestigationServiceRequest() {
    }

    public SaveInvestigationServiceRequest(SaveInvestigationDTO saveInvestigationDTO) {
        this.saveInvestigationDTO = saveInvestigationDTO;
    }

    //getters and setters

    public SaveInvestigationDTO getSaveInvestigationDTO() {
        return saveInvestigationDTO;
    }

    public void setSaveInvestigationDTO(SaveInvestigationDTO saveInvestigationDTO) {
        this.saveInvestigationDTO = saveInvestigationDTO;
    }
}
