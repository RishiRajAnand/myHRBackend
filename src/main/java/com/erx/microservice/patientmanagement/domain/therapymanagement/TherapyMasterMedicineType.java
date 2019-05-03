package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="tm_therapy_master_medicine_type")
public class TherapyMasterMedicineType extends BaseModel{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tm_therapy_master_id")
    @JsonIgnore
    private TherapyMaster therapyMaster;

    @Column(name = "medicine_type_master_id")
    private Long medicineTypeId;

    public TherapyMaster getTherapyMaster() {
        return therapyMaster;
    }

    public void setTherapyMaster(TherapyMaster therapyMaster) {
        this.therapyMaster = therapyMaster;
    }

    public Long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }
}
