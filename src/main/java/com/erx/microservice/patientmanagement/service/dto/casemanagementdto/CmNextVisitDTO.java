package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 10-10-2018
* */

import java.time.LocalDateTime;

public class CmNextVisitDTO {

    private Long clinicLocationId;
    private Long userId;
    private Long cmMasterId;
    private LocalDateTime nextVisitDate;

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCmMasterId() {
        return cmMasterId;
    }

    public void setCmMasterId(Long cmMasterId) {
        this.cmMasterId = cmMasterId;
    }

    public LocalDateTime getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(LocalDateTime nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }
}
