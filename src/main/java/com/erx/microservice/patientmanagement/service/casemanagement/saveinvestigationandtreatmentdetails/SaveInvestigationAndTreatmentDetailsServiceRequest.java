package com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigationandtreatmentdetails;

/*
* created by Latha on 01-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveInvestigationAndTreatmentDTO;

public class SaveInvestigationAndTreatmentDetailsServiceRequest implements CommonServiceRequest {

    private SaveInvestigationAndTreatmentDTO saveInvestigationAndTreatmentDTO;

    //constructor

    public SaveInvestigationAndTreatmentDetailsServiceRequest() {
    }

    public SaveInvestigationAndTreatmentDetailsServiceRequest(SaveInvestigationAndTreatmentDTO saveInvestigationAndTreatmentDTO) {
        this.saveInvestigationAndTreatmentDTO = saveInvestigationAndTreatmentDTO;
    }

    //getters and setters

    public SaveInvestigationAndTreatmentDTO getSaveInvestigationAndTreatmentDTO() {
        return saveInvestigationAndTreatmentDTO;
    }

    public void setSaveInvestigationAndTreatmentDTO(SaveInvestigationAndTreatmentDTO saveInvestigationAndTreatmentDTO) {
        this.saveInvestigationAndTreatmentDTO = saveInvestigationAndTreatmentDTO;
    }
}
