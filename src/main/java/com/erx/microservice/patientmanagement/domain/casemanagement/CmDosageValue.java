package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 24-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cm_dosage_value")
public class CmDosageValue extends BaseModel{

    @Column(name="dosage_value", nullable=true)
    private String dosageValue;

    public String getDosageValue() {
        return dosageValue;
    }

    public void setDosageValue(String dosageValue) {
        this.dosageValue = dosageValue;
    }
}
