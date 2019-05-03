package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 10-09-2018
* */

import java.util.List;

public class SaveTreatmentTherapyDTO {

    private Long clinicLocationId;
    private Long userId;
    private boolean draftStatus;
    private Long visitTypeMasterId;
    private Long therapyPlanningId;
    private Long patientId;
    private Long clinicId;
    private Long caseId;
    private Long cmMasterDetailId;
    private Long patientAppointmentId;
    private String ipDcAdmissionNumber;
    private Long serviceCatalogueId;
    private int numberOfDays;
    private String instructions;
    private long periodicInterval; // repeat after
    private List<SaveTreatmentTherapyMedicineTypeDTO> saveTreatmentTherapyMedicineTypeDTOs;
    private List<SaveTreatmentTherapyMedicineDTO> saveTreatmentTherapyMedicineDTOs;

    public Long getTherapyPlanningId() {
        return therapyPlanningId;
    }

    public void setTherapyPlanningId(Long therapyPlanningId) {
        this.therapyPlanningId = therapyPlanningId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public long getPeriodicInterval() {
        return periodicInterval;
    }

    public void setPeriodicInterval(long periodicInterval) {
        this.periodicInterval = periodicInterval;
    }

    public List<SaveTreatmentTherapyMedicineTypeDTO> getSaveTreatmentTherapyMedicineTypeDTOs() {
        return saveTreatmentTherapyMedicineTypeDTOs;
    }

    public void setSaveTreatmentTherapyMedicineTypeDTOs(List<SaveTreatmentTherapyMedicineTypeDTO> saveTreatmentTherapyMedicineTypeDTOs) {
        this.saveTreatmentTherapyMedicineTypeDTOs = saveTreatmentTherapyMedicineTypeDTOs;
    }

    public List<SaveTreatmentTherapyMedicineDTO> getSaveTreatmentTherapyMedicineDTOs() {
        return saveTreatmentTherapyMedicineDTOs;
    }

    public void setSaveTreatmentTherapyMedicineDTOs(List<SaveTreatmentTherapyMedicineDTO> saveTreatmentTherapyMedicineDTOs) {
        this.saveTreatmentTherapyMedicineDTOs = saveTreatmentTherapyMedicineDTOs;
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

    public Long getCmMasterDetailId() {
        return cmMasterDetailId;
    }

    public void setCmMasterDetailId(Long cmMasterDetailId) {
        this.cmMasterDetailId = cmMasterDetailId;
    }
}
