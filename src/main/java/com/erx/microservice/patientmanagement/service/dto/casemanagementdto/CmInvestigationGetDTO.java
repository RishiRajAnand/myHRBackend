package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

import java.time.LocalDateTime;

/**
 * Created by Latha on 18/09/18.
 */

public class CmInvestigationGetDTO {

    private Long cmMasterDetailsId;
    private Long cmInvestigationId;
    private String labOrderNumber;
    private LocalDateTime createdDate;
    private String doctorSummary;
    private boolean finalDiagnosis;
    private Long provisionalDiagnosisMasterId;
    private String provisionalDiagnosisMasterName;
    private Long cmAcdMasterId;
    private String cmAcdMasterName;
    private String cmAcdMasterDescription;
    private Long cmIcdMasterId;
    private String cmIcdMasterName;
    private String cmIcdMasterDescription;

    public Long getCmMasterDetailsId() {
        return cmMasterDetailsId;
    }

    public void setCmMasterDetailsId(Long cmMasterDetailsId) {
        this.cmMasterDetailsId = cmMasterDetailsId;
    }

    public Long getCmInvestigationId() {
        return cmInvestigationId;
    }

    public void setCmInvestigationId(Long cmInvestigationId) {
        this.cmInvestigationId = cmInvestigationId;
    }

    public String getLabOrderNumber() {
        return labOrderNumber;
    }

    public void setLabOrderNumber(String labOrderNumber) {
        this.labOrderNumber = labOrderNumber;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getDoctorSummary() {
        return doctorSummary;
    }

    public void setDoctorSummary(String doctorSummary) {
        this.doctorSummary = doctorSummary;
    }

    public boolean isFinalDiagnosis() {
        return finalDiagnosis;
    }

    public void setFinalDiagnosis(boolean finalDiagnosis) {
        this.finalDiagnosis = finalDiagnosis;
    }

    public Long getProvisionalDiagnosisMasterId() {
        return provisionalDiagnosisMasterId;
    }

    public void setProvisionalDiagnosisMasterId(Long provisionalDiagnosisMasterId) {
        this.provisionalDiagnosisMasterId = provisionalDiagnosisMasterId;
    }

    public String getProvisionalDiagnosisMasterName() {
        return provisionalDiagnosisMasterName;
    }

    public void setProvisionalDiagnosisMasterName(String provisionalDiagnosisMasterName) {
        this.provisionalDiagnosisMasterName = provisionalDiagnosisMasterName;
    }

    public Long getCmAcdMasterId() {
        return cmAcdMasterId;
    }

    public void setCmAcdMasterId(Long cmAcdMasterId) {
        this.cmAcdMasterId = cmAcdMasterId;
    }

    public String getCmAcdMasterName() {
        return cmAcdMasterName;
    }

    public void setCmAcdMasterName(String cmAcdMasterName) {
        this.cmAcdMasterName = cmAcdMasterName;
    }

    public Long getCmIcdMasterId() {
        return cmIcdMasterId;
    }

    public void setCmIcdMasterId(Long cmIcdMasterId) {
        this.cmIcdMasterId = cmIcdMasterId;
    }

    public String getCmIcdMasterName() {
        return cmIcdMasterName;
    }

    public void setCmIcdMasterName(String cmIcdMasterName) {
        this.cmIcdMasterName = cmIcdMasterName;
    }

    public String getCmAcdMasterDescription() {
        return cmAcdMasterDescription;
    }

    public void setCmAcdMasterDescription(String cmAcdMasterDescription) {
        this.cmAcdMasterDescription = cmAcdMasterDescription;
    }

    public String getCmIcdMasterDescription() {
        return cmIcdMasterDescription;
    }

    public void setCmIcdMasterDescription(String cmIcdMasterDescription) {
        this.cmIcdMasterDescription = cmIcdMasterDescription;
    }
}
