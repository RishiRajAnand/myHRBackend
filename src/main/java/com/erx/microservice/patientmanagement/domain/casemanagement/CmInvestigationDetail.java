package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 17-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cm_investigation_detail")
public class CmInvestigationDetail extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cm_investigation_id", nullable=true)
    private CmInvestigation cmInvestigation;

    @Column(name = "bm_service_catalogue_id", nullable=true)
    private Long serviceCatalogueId;

    @Column(name = "added_by", nullable=true)
    private Long addedBy;//User detail id

    @Column(name="added_on", nullable=true)
    private LocalDateTime addedOn;

    @Column(name = "performed_by")
    private Long performedBy;//User id

    @Column(name="tested_on", nullable=true)
    private LocalDateTime testedOn;

    @Column(name="is_report_uploaded", nullable=true)
    private boolean isReportUploaded;

    @Column(name="investigation_notes", nullable=true)
    private String investigationNotes;

    @OneToMany(mappedBy="cmInvestigationDetail")
    private List<UploadedDocumentDetail> uploadedDocumentDetails;

    public CmInvestigation getCmInvestigation() {
        return cmInvestigation;
    }

    public void setCmInvestigation(CmInvestigation cmInvestigation) {
        this.cmInvestigation = cmInvestigation;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public Long getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Long addedBy) {
        this.addedBy = addedBy;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public Long getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(Long performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getTestedOn() {
        return testedOn;
    }

    public void setTestedOn(LocalDateTime testedOn) {
        this.testedOn = testedOn;
    }

    public boolean isReportUploaded() {
        return isReportUploaded;
    }

    public void setReportUploaded(boolean reportUploaded) {
        isReportUploaded = reportUploaded;
    }

    public String getInvestigationNotes() {
        return investigationNotes;
    }

    public void setInvestigationNotes(String investigationNotes) {
        this.investigationNotes = investigationNotes;
    }

    public List<UploadedDocumentDetail> getUploadedDocumentDetails() {
        return uploadedDocumentDetails;
    }

    public void setUploadedDocumentDetails(List<UploadedDocumentDetail> uploadedDocumentDetails) {
        this.uploadedDocumentDetails = uploadedDocumentDetails;
    }
}
