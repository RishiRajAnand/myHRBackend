package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 11-09-2018
* */

public class CmTherapyTreatmentMedicineTypeDTO {

    private Long therapyPlanningMedicineTypeId;
    private Long medicineTypeId;
    private String medicineTypeName;

    public Long getTherapyPlanningMedicineTypeId() {
        return therapyPlanningMedicineTypeId;
    }

    public void setTherapyPlanningMedicineTypeId(Long therapyPlanningMedicineTypeId) {
        this.therapyPlanningMedicineTypeId = therapyPlanningMedicineTypeId;
    }

    public Long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }

    public String getMedicineTypeName() {
        return medicineTypeName;
    }

    public void setMedicineTypeName(String medicineTypeName) {
        this.medicineTypeName = medicineTypeName;
    }
}
