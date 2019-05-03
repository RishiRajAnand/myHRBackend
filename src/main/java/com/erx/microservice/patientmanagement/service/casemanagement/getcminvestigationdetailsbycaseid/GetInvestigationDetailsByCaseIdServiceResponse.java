package com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationdetailsbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmInvestigationDetailsGetDTO;

import java.util.List;


public class GetInvestigationDetailsByCaseIdServiceResponse extends CommonServiceResponse {

    private List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs;

    //constructor

    public GetInvestigationDetailsByCaseIdServiceResponse() {
    }

    public GetInvestigationDetailsByCaseIdServiceResponse(List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs) {
        this.cmInvestigationDetailsGetDTOs = cmInvestigationDetailsGetDTOs;
    }

    //getters and setters

    public List<CmInvestigationDetailsGetDTO> getCmInvestigationDetailsGetDTOs() {
        return cmInvestigationDetailsGetDTOs;
    }

    public void setCmInvestigationDetailsGetDTOs(List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs) {
        this.cmInvestigationDetailsGetDTOs = cmInvestigationDetailsGetDTOs;
    }
}
