package com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmInvestigationGetDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMedicineTreatmentResponseDTO;

import java.util.List;

public class GetInvestigationByCaseIdServiceResponse extends CommonServiceResponse {

    private CmInvestigationGetDTO cmInvestigationGetDTO;

    //constructor

    public GetInvestigationByCaseIdServiceResponse() {
    }

    public GetInvestigationByCaseIdServiceResponse(CmInvestigationGetDTO cmInvestigationGetDTO) {
        this.cmInvestigationGetDTO = cmInvestigationGetDTO;
    }

    //getters and setters

    public CmInvestigationGetDTO getCmInvestigationGetDTO() {
        return cmInvestigationGetDTO;
    }

    public void setCmInvestigationGetDTO(CmInvestigationGetDTO cmInvestigationGetDTO) {
        this.cmInvestigationGetDTO = cmInvestigationGetDTO;
    }
}
