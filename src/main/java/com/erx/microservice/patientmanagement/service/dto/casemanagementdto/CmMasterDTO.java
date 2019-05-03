package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 20/08/18.
 */

public class CmMasterDTO {

    private Long caseId;
    private Long cmMasterDetailId;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getCmMasterDetailId() {
        return cmMasterDetailId;
    }

    public void setCmMasterDetailId(Long cmMasterDetailId) {
        this.cmMasterDetailId = cmMasterDetailId;
    }
}
