package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 04-09-2018
* */

import java.util.List;

public class SaveTherapyMasterDTO {

    private Long therapyMasterId;
    private Long clinicId;
    private Long serviceCatalogueId;
    private Long therapyDepartmentId;
    private Long duration;
    private int numTherapist;
    private String instructions;
    private boolean medicineCharged;
    private List<SaveTherapyMasterMedicineTypeDTO> saveTherapyMasterMedicineTypeDTOs;
    private List<SaveTherapyMasterMedicineDTO> saveTherapyMasterMedicineDTOs;
    private List<SaveTherapyMasterRoomDTO> saveTherapyMasterRoomDTOs;
    private List<SaveTherapyMasterTherapistDTO> saveTherapyMasterTherapistDTOs;

    public Long getTherapyMasterId() {
        return therapyMasterId;
    }

    public void setTherapyMasterId(Long therapyMasterId) {
        this.therapyMasterId = therapyMasterId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public Long getTherapyDepartmentId() {
        return therapyDepartmentId;
    }

    public void setTherapyDepartmentId(Long therapyDepartmentId) {
        this.therapyDepartmentId = therapyDepartmentId;
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

    public boolean isMedicineCharged() {
        return medicineCharged;
    }

    public void setMedicineCharged(boolean medicineCharged) {
        this.medicineCharged = medicineCharged;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<SaveTherapyMasterMedicineTypeDTO> getSaveTherapyMasterMedicineTypeDTOs() {
        return saveTherapyMasterMedicineTypeDTOs;
    }

    public void setSaveTherapyMasterMedicineTypeDTOs(List<SaveTherapyMasterMedicineTypeDTO> saveTherapyMasterMedicineTypeDTOs) {
        this.saveTherapyMasterMedicineTypeDTOs = saveTherapyMasterMedicineTypeDTOs;
    }

    public List<SaveTherapyMasterMedicineDTO> getSaveTherapyMasterMedicineDTOs() {
        return saveTherapyMasterMedicineDTOs;
    }

    public void setSaveTherapyMasterMedicineDTOs(List<SaveTherapyMasterMedicineDTO> saveTherapyMasterMedicineDTOs) {
        this.saveTherapyMasterMedicineDTOs = saveTherapyMasterMedicineDTOs;
    }

    public List<SaveTherapyMasterRoomDTO> getSaveTherapyMasterRoomDTOs() {
        return saveTherapyMasterRoomDTOs;
    }

    public void setSaveTherapyMasterRoomDTOs(List<SaveTherapyMasterRoomDTO> saveTherapyMasterRoomDTOs) {
        this.saveTherapyMasterRoomDTOs = saveTherapyMasterRoomDTOs;
    }

    public List<SaveTherapyMasterTherapistDTO> getSaveTherapyMasterTherapistDTOs() {
        return saveTherapyMasterTherapistDTOs;
    }

    public void setSaveTherapyMasterTherapistDTOs(List<SaveTherapyMasterTherapistDTO> saveTherapyMasterTherapistDTOs) {
        this.saveTherapyMasterTherapistDTOs = saveTherapyMasterTherapistDTOs;
    }
}
