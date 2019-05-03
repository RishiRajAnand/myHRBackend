package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 25-08-2018
* */

import java.util.List;

public class SaveMedicineResponseDTO {

    private Long cmTreatmentId;
    private Long cmTreatmentMedicineDetailId;
    private Long cmTreatmentGroupMedicineDetailId;
    private List<Long> cmTreatmentMedicineDetailIds;
    private List<Long> cmTreatmentGroupMedicineDetailIds;

    //getters and setters

    public Long getCmTreatmentId() {
        return cmTreatmentId;
    }

    public void setCmTreatmentId(Long cmTreatmentId) {
        this.cmTreatmentId = cmTreatmentId;
    }

    public Long getCmTreatmentMedicineDetailId() {
        return cmTreatmentMedicineDetailId;
    }

    public void setCmTreatmentMedicineDetailId(Long cmTreatmentMedicineDetailId) {
        this.cmTreatmentMedicineDetailId = cmTreatmentMedicineDetailId;
    }

    public Long getCmTreatmentGroupMedicineDetailId() {
        return cmTreatmentGroupMedicineDetailId;
    }

    public void setCmTreatmentGroupMedicineDetailId(Long cmTreatmentGroupMedicineDetailId) {
        this.cmTreatmentGroupMedicineDetailId = cmTreatmentGroupMedicineDetailId;
    }

    public List<Long> getCmTreatmentMedicineDetailIds() {
        return cmTreatmentMedicineDetailIds;
    }

    public void setCmTreatmentMedicineDetailIds(List<Long> cmTreatmentMedicineDetailIds) {
        this.cmTreatmentMedicineDetailIds = cmTreatmentMedicineDetailIds;
    }

    public List<Long> getCmTreatmentGroupMedicineDetailIds() {
        return cmTreatmentGroupMedicineDetailIds;
    }

    public void setCmTreatmentGroupMedicineDetailIds(List<Long> cmTreatmentGroupMedicineDetailIds) {
        this.cmTreatmentGroupMedicineDetailIds = cmTreatmentGroupMedicineDetailIds;
    }
}
