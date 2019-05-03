package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 01-10-2018
* */

import java.util.List;

public class SaveInvestigationAndTreatmentDTO {

    private Long clinicId;
    private Long clinicLocationId;
    private Long userId;
    private Long userDepartmentId;
    private Long patientId;
    private Long bmOrderId;
    private boolean newOrderStatus; //says whether order is saving first time or order is in edit state
    private String bmOrderNumber;
    private boolean draftStatus;
    private Long visitTypeMasterId;
    private Long ipAdmissionId;
    private Long cmMasterId;
    private Long cmMasterDetailId;
    private Long patientAppointmentId;
    private String ipDcAdmissionNumber;
    private SaveCmInvestigationDetailDTO saveCmInvestigationDetailDTO;
    private List<SaveInvestigationDetailDTO> saveInvestigationDetailDTOs;
    private SaveCmMedicineTreatmentDTO saveCmMedicineTreatmentDTO;
    private List<SaveCaseMedicineDTO> saveCaseMedicineDTOs;
    private List<SaveTherapyPlanningDTO> saveTherapyPlanningDTOs;

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

    public boolean isNewOrderStatus() {
        return newOrderStatus;
    }

    public void setNewOrderStatus(boolean newOrderStatus) {
        this.newOrderStatus = newOrderStatus;
    }

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
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

    public Long getCmMasterId() {
        return cmMasterId;
    }

    public void setCmMasterId(Long cmMasterId) {
        this.cmMasterId = cmMasterId;
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

    public SaveCmInvestigationDetailDTO getSaveCmInvestigationDetailDTO() {
        return saveCmInvestigationDetailDTO;
    }

    public void setSaveCmInvestigationDetailDTO(SaveCmInvestigationDetailDTO saveCmInvestigationDetailDTO) {
        this.saveCmInvestigationDetailDTO = saveCmInvestigationDetailDTO;
    }

    public List<SaveInvestigationDetailDTO> getSaveInvestigationDetailDTOs() {
        return saveInvestigationDetailDTOs;
    }

    public void setSaveInvestigationDetailDTOs(List<SaveInvestigationDetailDTO> saveInvestigationDetailDTOs) {
        this.saveInvestigationDetailDTOs = saveInvestigationDetailDTOs;
    }

    public SaveCmMedicineTreatmentDTO getSaveCmMedicineTreatmentDTO() {
        return saveCmMedicineTreatmentDTO;
    }

    public void setSaveCmMedicineTreatmentDTO(SaveCmMedicineTreatmentDTO saveCmMedicineTreatmentDTO) {
        this.saveCmMedicineTreatmentDTO = saveCmMedicineTreatmentDTO;
    }

    public List<SaveCaseMedicineDTO> getSaveCaseMedicineDTOs() {
        return saveCaseMedicineDTOs;
    }

    public void setSaveCaseMedicineDTOs(List<SaveCaseMedicineDTO> saveCaseMedicineDTOs) {
        this.saveCaseMedicineDTOs = saveCaseMedicineDTOs;
    }

    public List<SaveTherapyPlanningDTO> getSaveTherapyPlanningDTOs() {
        return saveTherapyPlanningDTOs;
    }

    public void setSaveTherapyPlanningDTOs(List<SaveTherapyPlanningDTO> saveTherapyPlanningDTOs) {
        this.saveTherapyPlanningDTOs = saveTherapyPlanningDTOs;
    }

    public String getBmOrderNumber() {
        return bmOrderNumber;
    }

    public void setBmOrderNumber(String bmOrderNumber) {
        this.bmOrderNumber = bmOrderNumber;
    }

    public Long getCmMasterDetailId() {
        return cmMasterDetailId;
    }

    public void setCmMasterDetailId(Long cmMasterDetailId) {
        this.cmMasterDetailId = cmMasterDetailId;
    }

    public Long getUserDepartmentId() {
        return userDepartmentId;
    }

    public void setUserDepartmentId(Long userDepartmentId) {
        this.userDepartmentId = userDepartmentId;
    }

    public Long getIpAdmissionId() {
        return ipAdmissionId;
    }

    public void setIpAdmissionId(Long ipAdmissionId) {
        this.ipAdmissionId = ipAdmissionId;
    }
}
