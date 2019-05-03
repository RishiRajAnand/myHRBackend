package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 20-08-2018
* */

public class DeleteMedicineTreatmentDTO {

    private Long cmTreatmentMedicineDetailId;
    private Long cmTreatmentMedicineGroupId;

    //constructor

    public DeleteMedicineTreatmentDTO(Long cmTreatmentMedicineDetailId, Long cmTreatmentMedicineGroupId) {
        this.cmTreatmentMedicineDetailId = cmTreatmentMedicineDetailId;
        this.cmTreatmentMedicineGroupId = cmTreatmentMedicineGroupId;
    }

    public DeleteMedicineTreatmentDTO() {
    }

    //getters and setters

    public Long getCmTreatmentMedicineDetailId() {
        return cmTreatmentMedicineDetailId;
    }

    public void setCmTreatmentMedicineDetailId(Long cmTreatmentMedicineDetailId) {
        this.cmTreatmentMedicineDetailId = cmTreatmentMedicineDetailId;
    }

    public Long getCmTreatmentMedicineGroupId() {
        return cmTreatmentMedicineGroupId;
    }

    public void setCmTreatmentMedicineGroupId(Long cmTreatmentMedicineGroupId) {
        this.cmTreatmentMedicineGroupId = cmTreatmentMedicineGroupId;
    }
}
