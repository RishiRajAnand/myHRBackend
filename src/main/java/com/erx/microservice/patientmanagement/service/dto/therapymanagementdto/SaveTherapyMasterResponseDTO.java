package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 07-09-2018
* */

import java.util.List;

public class SaveTherapyMasterResponseDTO {

    private Long therapyMasterId;
    private List<Long> therapyMasterMedicineTypeId;
    private List<Long> therapyMasterMedicineId;
    private List<Long> therapyMasterRoomDetailId;
    private List<Long> therapyMasterTherapistDetailId;

    public Long getTherapyMasterId() {
        return therapyMasterId;
    }

    public void setTherapyMasterId(Long therapyMasterId) {
        this.therapyMasterId = therapyMasterId;
    }

    public List<Long> getTherapyMasterMedicineTypeId() {
        return therapyMasterMedicineTypeId;
    }

    public void setTherapyMasterMedicineTypeId(List<Long> therapyMasterMedicineTypeId) {
        this.therapyMasterMedicineTypeId = therapyMasterMedicineTypeId;
    }

    public List<Long> getTherapyMasterMedicineId() {
        return therapyMasterMedicineId;
    }

    public void setTherapyMasterMedicineId(List<Long> therapyMasterMedicineId) {
        this.therapyMasterMedicineId = therapyMasterMedicineId;
    }

    public List<Long> getTherapyMasterRoomDetailId() {
        return therapyMasterRoomDetailId;
    }

    public void setTherapyMasterRoomDetailId(List<Long> therapyMasterRoomDetailId) {
        this.therapyMasterRoomDetailId = therapyMasterRoomDetailId;
    }

    public List<Long> getTherapyMasterTherapistDetailId() {
        return therapyMasterTherapistDetailId;
    }

    public void setTherapyMasterTherapistDetailId(List<Long> therapyMasterTherapistDetailId) {
        this.therapyMasterTherapistDetailId = therapyMasterTherapistDetailId;
    }
}
