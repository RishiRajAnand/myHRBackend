package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 06/10/18.
 */

public class CmPersonalHistoryDTO {

    private Long cmMasterDetailId;
    private Long cmPersonalHistoryId;

    public Long getCmMasterDetailId() {
        return cmMasterDetailId;
    }

    public void setCmMasterDetailId(Long cmMasterDetailId) {
        this.cmMasterDetailId = cmMasterDetailId;
    }

    public Long getCmPersonalHistoryId() {
        return cmPersonalHistoryId;
    }

    public void setCmPersonalHistoryId(Long cmPersonalHistoryId) {
        this.cmPersonalHistoryId = cmPersonalHistoryId;
    }
}
