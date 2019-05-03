package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 01-10-2018
* */

public class SaveCmInvestigationDetailDTO {

    private Long cmInvestigationId;
    private boolean finalDiagnosis;
    private Long provisionalDiagnosisMasterId;
    private Long acdMasterId;
    private Long icdMasterId;
    private String doctorSummary;

    public Long getCmInvestigationId() {
        return cmInvestigationId;
    }

    public void setCmInvestigationId(Long cmInvestigationId) {
        this.cmInvestigationId = cmInvestigationId;
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

    public Long getAcdMasterId() {
        return acdMasterId;
    }

    public void setAcdMasterId(Long acdMasterId) {
        this.acdMasterId = acdMasterId;
    }

    public Long getIcdMasterId() {
        return icdMasterId;
    }

    public void setIcdMasterId(Long icdMasterId) {
        this.icdMasterId = icdMasterId;
    }

    public String getDoctorSummary() {
        return doctorSummary;
    }

    public void setDoctorSummary(String doctorSummary) {
        this.doctorSummary = doctorSummary;
    }
}
