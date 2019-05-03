package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 17-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "provisional_diagnosis_master")
public class ProvisionalDiagnosisMaster extends BaseModel{

    @Column(name="name", nullable=true)
    private String name;

    @Column(name="description", nullable=true)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
