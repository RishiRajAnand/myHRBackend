package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "tm_therapy_planning_medicine_type")
public class TherapyPlanningMedicineType extends BaseModel {


    @Column(name = "medicine_type_master_id")
    private Long medicineTypeId;

    @ManyToOne
    @JoinColumn(name = "tm_therapy_planning_id")
    private TherapyPlanning therapyPlanning;

    public Long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }

    public TherapyPlanning getTherapyPlanning() {
        return therapyPlanning;
    }

    public void setTherapyPlanning(TherapyPlanning therapyPlanning) {
        this.therapyPlanning = therapyPlanning;
    }
}
