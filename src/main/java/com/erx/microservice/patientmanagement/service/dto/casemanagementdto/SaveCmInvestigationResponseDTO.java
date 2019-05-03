package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 17-09-2018
* */

import java.util.List;

public class SaveCmInvestigationResponseDTO {

    private Long cmInvestigationId;
    private List<Long> cmInvestigationDetailId;

    public Long getCmInvestigationId() {
        return cmInvestigationId;
    }

    public void setCmInvestigationId(Long cmInvestigationId) {
        this.cmInvestigationId = cmInvestigationId;
    }

    public List<Long> getCmInvestigationDetailId() {
        return cmInvestigationDetailId;
    }

    public void setCmInvestigationDetailId(List<Long> cmInvestigationDetailId) {
        this.cmInvestigationDetailId = cmInvestigationDetailId;
    }
    
}
