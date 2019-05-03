package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 08-09-2018
* */

import java.util.List;

public class TherapyMasterGetDTO {

    private Long therapyMasterId;
    private Long serviceCatalogueId;
    private String therapyName;
    private Long therapyDepartmentId;
    private String therapyDepartmentName;
    private Long duration;
    private int numTherapist;
    private String instructions;
    private boolean medicineCharged;
    private List<TherapyMasterMedicineTypeGetDTO> therapyMasterMedicineTypeGetDTOs;
    private List<TherapyMasterMedicineGetDTO> therapyMasterMedicineGetDTOs;
    private List<TherapyMasterRoomDetailGetDTO> therapyMasterRoomDetailGetDTOs;
    private List<TherapyMasterTherapistDetailGetDTO> therapyMasterTherapistDetailGetDTOs;

    public Long getTherapyMasterId() {
        return therapyMasterId;
    }

    public void setTherapyMasterId(Long therapyMasterId) {
        this.therapyMasterId = therapyMasterId;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public String getTherapyName() {
        return therapyName;
    }

    public void setTherapyName(String therapyName) {
        this.therapyName = therapyName;
    }

    public Long getTherapyDepartmentId() {
        return therapyDepartmentId;
    }

    public void setTherapyDepartmentId(Long therapyDepartmentId) {
        this.therapyDepartmentId = therapyDepartmentId;
    }

    public String getTherapyDepartmentName() {
        return therapyDepartmentName;
    }

    public void setTherapyDepartmentName(String therapyDepartmentName) {
        this.therapyDepartmentName = therapyDepartmentName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public int getNumTherapist() {
        return numTherapist;
    }

    public void setNumTherapist(int numTherapist) {
        this.numTherapist = numTherapist;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public boolean isMedicineCharged() {
        return medicineCharged;
    }

    public void setMedicineCharged(boolean medicineCharged) {
        this.medicineCharged = medicineCharged;
    }

    public List<TherapyMasterMedicineTypeGetDTO> getTherapyMasterMedicineTypeGetDTOs() {
        return therapyMasterMedicineTypeGetDTOs;
    }

    public void setTherapyMasterMedicineTypeGetDTOs(List<TherapyMasterMedicineTypeGetDTO> therapyMasterMedicineTypeGetDTOs) {
        this.therapyMasterMedicineTypeGetDTOs = therapyMasterMedicineTypeGetDTOs;
    }

    public List<TherapyMasterMedicineGetDTO> getTherapyMasterMedicineGetDTOs() {
        return therapyMasterMedicineGetDTOs;
    }

    public void setTherapyMasterMedicineGetDTOs(List<TherapyMasterMedicineGetDTO> therapyMasterMedicineGetDTOs) {
        this.therapyMasterMedicineGetDTOs = therapyMasterMedicineGetDTOs;
    }

    public List<TherapyMasterRoomDetailGetDTO> getTherapyMasterRoomDetailGetDTOs() {
        return therapyMasterRoomDetailGetDTOs;
    }

    public void setTherapyMasterRoomDetailGetDTOs(List<TherapyMasterRoomDetailGetDTO> therapyMasterRoomDetailGetDTOs) {
        this.therapyMasterRoomDetailGetDTOs = therapyMasterRoomDetailGetDTOs;
    }

    public List<TherapyMasterTherapistDetailGetDTO> getTherapyMasterTherapistDetailGetDTOs() {
        return therapyMasterTherapistDetailGetDTOs;
    }

    public void setTherapyMasterTherapistDetailGetDTOs(List<TherapyMasterTherapistDetailGetDTO> therapyMasterTherapistDetailGetDTOs) {
        this.therapyMasterTherapistDetailGetDTOs = therapyMasterTherapistDetailGetDTOs;
    }
}
