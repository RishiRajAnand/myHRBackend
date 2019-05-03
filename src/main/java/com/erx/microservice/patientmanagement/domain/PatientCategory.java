package com.erx.microservice.patientmanagement.domain;

/*
* created by latha on 20-11-2017
* */

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "patient_category")
public class PatientCategory extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = true)
    @JsonIgnore
    private Clinic clinic;
    @Column(name = "type_name", nullable = false)
    private String typeName;
    @Column(name = "description", nullable = true)
    private String description;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
