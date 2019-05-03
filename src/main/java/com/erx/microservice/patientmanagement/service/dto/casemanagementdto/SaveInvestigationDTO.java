package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 17-09-2018
* */

import java.util.List;

public class SaveInvestigationDTO {

    private Long clinicId;
    private Long clinicLocationId;
    private Long userId;
    private Long userDepartmentId;
    private Long patientId;
    private boolean draftStatus;
    private Long visitTypeMasterId;
    private Long cmInvestigationId;
    private Long cmMasterId;
    private Long cmMasterDetailId;
    private Long patientAppointmentId;
    private String ipDcAdmissionNumber;
    private boolean finalDiagnosis;
    private Long provisionalDiagnosisMasterId;
    private Long acdMasterId;
    private Long icdMasterId;
    private String doctorSummary;
    private List<SaveInvestigationDetailDTO> saveInvestigationDetailDTOs;

    public Long getCmInvestigationId() {
        return cmInvestigationId;
    }

    public void setCmInvestigationId(Long cmInvestigationId) {
        this.cmInvestigationId = cmInvestigationId;
    }

    public Long getCmMasterId() {
        return cmMasterId;
    }

    public void setCmMasterId(Long cmMasterId) {
        this.cmMasterId = cmMasterId;
    }

    public Long getCmMasterDetailId() {
        return cmMasterDetailId;
    }

    public void setCmMasterDetailId(Long cmMasterDetailId) {
        this.cmMasterDetailId = cmMasterDetailId;
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

    public List<SaveInvestigationDetailDTO> getSaveInvestigationDetailDTOs() {
        return saveInvestigationDetailDTOs;
    }

    public void setSaveInvestigationDetailDTOs(List<SaveInvestigationDetailDTO> saveInvestigationDetailDTOs) {
        this.saveInvestigationDetailDTOs = saveInvestigationDetailDTOs;
    }

    public boolean isDraftStatus() {
        return draftStatus;
    }

    public void setDraftStatus(boolean draftStatus) {
        this.draftStatus = draftStatus;
    }

    public Long getVisitTypeMasterId() {
        return visitTypeMasterId;
    }

    public void setVisitTypeMasterId(Long visitTypeMasterId) {
        this.visitTypeMasterId = visitTypeMasterId;
    }

    public Long getPatientAppointmentId() {
        return patientAppointmentId;
    }

    public void setPatientAppointmentId(Long patientAppointmentId) {
        this.patientAppointmentId = patientAppointmentId;
    }

    public String getIpDcAdmissionNumber() {
        return ipDcAdmissionNumber;
    }

    public void setIpDcAdmissionNumber(String ipDcAdmissionNumber) {
        this.ipDcAdmissionNumber = ipDcAdmissionNumber;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getUserDepartmentId() {
        return userDepartmentId;
    }

    public void setUserDepartmentId(Long userDepartmentId) {
        this.userDepartmentId = userDepartmentId;
    }
}
