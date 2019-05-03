package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.Clinic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="tm_therapy_template")
public class TherapyTemplate extends BaseModel{

    @Column(name = "name", nullable = true)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    @JsonIgnore
    private Clinic clinic;

    @Column(name="is_deleted")
    private boolean deleteTherapy;

   /* @Column(name="special_instruction")
    private String specialInstruction;*/

    @Column(name="therapy_group")
    private String therapyGroup;

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

    public boolean isDeleteTherapy() {
        return deleteTherapy;
    }

    public void setDeleteTherapy(boolean deleteTherapy) {
        this.deleteTherapy = deleteTherapy;
    }

   /* public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }*/

    public String getTherapyGroup() {
        return therapyGroup;
    }

    public void setTherapyGroup(String therapyGroup) {
        this.therapyGroup = therapyGroup;
    }
}
