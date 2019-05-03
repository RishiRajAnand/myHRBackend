package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 24-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "cm_dosage_instruction")
public class CmDosageInstruction extends BaseModel{

    @Column(name="dosage_instruction", nullable=true)
    private String dosageInstruction;

    @Column(name="description", nullable=true)
    private String description;

    @Column(name="dosage_default_time", nullable=true)
    private LocalTime dosageDefaultTime;

    public String getDosageInstruction() {
        return dosageInstruction;
    }

    public void setDosageInstruction(String dosageInstruction) {
        this.dosageInstruction = dosageInstruction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getDosageDefaultTime() {
        return dosageDefaultTime;
    }

    public void setDosageDefaultTime(LocalTime dosageDefaultTime) {
        this.dosageDefaultTime = dosageDefaultTime;
    }
}
