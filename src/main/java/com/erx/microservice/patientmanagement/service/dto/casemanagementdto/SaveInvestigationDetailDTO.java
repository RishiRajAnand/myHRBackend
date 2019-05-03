package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 17-09-2018
* */


import java.time.LocalDateTime;

public class SaveInvestigationDetailDTO {

    private Long cmInvestigationDetailId;
    private Long bmOrderId;
    private Long serviceCatalogueId;
    private Long userId;//performedBy
    private Long userDetailId;//added by
    private LocalDateTime addedOn;
    private LocalDateTime testedOn;
    private String investigationNotes;

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public Long getCmInvestigationDetailId() {
        return cmInvestigationDetailId;
    }

    public void setCmInvestigationDetailId(Long cmInvestigationDetailId) {
        this.cmInvestigationDetailId = cmInvestigationDetailId;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(Long userDetailId) {
        this.userDetailId = userDetailId;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public LocalDateTime getTestedOn() {
        return testedOn;
    }

    public void setTestedOn(LocalDateTime testedOn) {
        this.testedOn = testedOn;
    }

    public String getInvestigationNotes() {
        return investigationNotes;
    }

    public void setInvestigationNotes(String investigationNotes) {
        this.investigationNotes = investigationNotes;
    }
}
