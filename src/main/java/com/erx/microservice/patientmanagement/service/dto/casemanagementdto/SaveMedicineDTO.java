package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 25-08-2018
* */

public class SaveMedicineDTO {

    private Long clinicId;
    private Long clinicLocationId;
    private Long userId;
    private Long userDepartmentId;
    private Long patientId;
    private boolean draftStatus;
    private Long visitTypeMasterId;
    private Long cmMasterId;
    private Long cmMasterDetailId;
    private Long patientAppointmentId;
    private String ipDcAdmissionNumber;
    private Long cmTreatmentId;
    private TreatmentPrincipleDTO treatmentPrincipleDTO;
    private PathyaPathyaDTO pathyaPathyaDTO;
    private SaveIndividualDTO saveIndividualDTO;
    private SaveGroupDTO saveGroupDTO;

    public Long getCmMasterId() {
        return cmMasterId;
    }

    public void setCmMasterId(Long cmMasterId) {
        this.cmMasterId = cmMasterId;
    }

    public Long getCmTreatmentId() {
        return cmTreatmentId;
    }

    public void setCmTreatmentId(Long cmTreatmentId) {
        this.cmTreatmentId = cmTreatmentId;
    }

    public Long getCmMasterDetailId() {
        return cmMasterDetailId;
    }

    public void setCmMasterDetailId(Long cmMasterDetailId) {
        this.cmMasterDetailId = cmMasterDetailId;
    }

    public TreatmentPrincipleDTO getTreatmentPrincipleDTO() {
        return treatmentPrincipleDTO;
    }

    public void setTreatmentPrincipleDTO(TreatmentPrincipleDTO treatmentPrincipleDTO) {
        this.treatmentPrincipleDTO = treatmentPrincipleDTO;
    }

    public PathyaPathyaDTO getPathyaPathyaDTO() {
        return pathyaPathyaDTO;
    }

    public void setPathyaPathyaDTO(PathyaPathyaDTO pathyaPathyaDTO) {
        this.pathyaPathyaDTO = pathyaPathyaDTO;
    }

    public SaveIndividualDTO getSaveIndividualDTO() {
        return saveIndividualDTO;
    }

    public void setSaveIndividualDTO(SaveIndividualDTO saveIndividualDTO) {
        this.saveIndividualDTO = saveIndividualDTO;
    }

    public SaveGroupDTO getSaveGroupDTO() {
        return saveGroupDTO;
    }

    public void setSaveGroupDTO(SaveGroupDTO saveGroupDTO) {
        this.saveGroupDTO = saveGroupDTO;
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
