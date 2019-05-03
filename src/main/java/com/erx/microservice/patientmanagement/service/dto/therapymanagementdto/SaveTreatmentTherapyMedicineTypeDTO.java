package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 10-09-2018
* */

public class SaveTreatmentTherapyMedicineTypeDTO {

    private Long therapyPlanningMedicineTypeId;
    private Long medicineTypeMasterId;

    public Long getTherapyPlanningMedicineTypeId() {
        return therapyPlanningMedicineTypeId;
    }

    public void setTherapyPlanningMedicineTypeId(Long therapyPlanningMedicineTypeId) {
        this.therapyPlanningMedicineTypeId = therapyPlanningMedicineTypeId;
    }

    public Long getMedicineTypeMasterId() {
        return medicineTypeMasterId;
    }

    public void setMedicineTypeMasterId(Long medicineTypeMasterId) {
        this.medicineTypeMasterId = medicineTypeMasterId;
    }
}
