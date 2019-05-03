package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.Clinic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "cm_medicine_template")
public class CmTemplate extends BaseModel{

    @Column(name="name", nullable=false)
    private String name;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="clinic_id",nullable=false)
    @JsonIgnore
    private Clinic clinic;

    @Column(name = "instruction")
    private String instructions;

    @Column(name = "science_of_medicine_id", nullable=false)
    private Long scienceOfMedicineId;

    @Column(name="description")
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }


    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    public Long getScienceOfMedicineId() {
        return scienceOfMedicineId;
    }

    public void setScienceOfMedicineId(Long scienceOfMedicineId) {
        this.scienceOfMedicineId = scienceOfMedicineId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
