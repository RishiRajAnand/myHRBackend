package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 10-09-2018
* */

import java.util.List;

public class SaveTreatmentTherapyResponseDTO {

    private Long therapyPlanningId;
    private List<Long> therapyPlanningMedicineTypeId;
    private List<Long> therapyPlanningMedicineId;

    public Long getTherapyPlanningId() {
        return therapyPlanningId;
    }

    public void setTherapyPlanningId(Long therapyPlanningId) {
        this.therapyPlanningId = therapyPlanningId;
    }

    public List<Long> getTherapyPlanningMedicineTypeId() {
        return therapyPlanningMedicineTypeId;
    }

    public void setTherapyPlanningMedicineTypeId(List<Long> therapyPlanningMedicineTypeId) {
        this.therapyPlanningMedicineTypeId = therapyPlanningMedicineTypeId;
    }

    public List<Long> getTherapyPlanningMedicineId() {
        return therapyPlanningMedicineId;
    }

    public void setTherapyPlanningMedicineId(List<Long> therapyPlanningMedicineId) {
        this.therapyPlanningMedicineId = therapyPlanningMedicineId;
    }
}
