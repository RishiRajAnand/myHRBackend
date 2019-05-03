package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cm_personal_history")
public class CmPersonalHistory extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cm_master_detail_id", nullable=false)
    @JsonIgnore
    private CmMasterDetails cmMasterDetails;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "appetite_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData appetiteObservationData;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "bladder_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData bladderObservationData;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "sleep_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData sleepObservationData;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "bowel_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData bowelObservationData;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "diet_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData dietObservationData;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "habit_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData habitObservationData;

    public CmMasterDetails getCmMasterDetails() {
        return cmMasterDetails;
    }

    public void setCmMasterDetails(CmMasterDetails cmMasterDetails) {
        this.cmMasterDetails = cmMasterDetails;
    }

    public CmObservationCategoryData getAppetiteObservationData() {
        return appetiteObservationData;
    }

    public void setAppetiteObservationData(CmObservationCategoryData appetiteObservationData) {
        this.appetiteObservationData = appetiteObservationData;
    }

    public CmObservationCategoryData getBladderObservationData() {
        return bladderObservationData;
    }

    public void setBladderObservationData(CmObservationCategoryData bladderObservationData) {
        this.bladderObservationData = bladderObservationData;
    }

    public CmObservationCategoryData getSleepObservationData() {
        return sleepObservationData;
    }

    public void setSleepObservationData(CmObservationCategoryData sleepObservationData) {
        this.sleepObservationData = sleepObservationData;
    }

    public CmObservationCategoryData getBowelObservationData() {
        return bowelObservationData;
    }

    public void setBowelObservationData(CmObservationCategoryData bowelObservationData) {
        this.bowelObservationData = bowelObservationData;
    }

    public CmObservationCategoryData getDietObservationData() {
        return dietObservationData;
    }

    public void setDietObservationData(CmObservationCategoryData dietObservationData) {
        this.dietObservationData = dietObservationData;
    }

    public CmObservationCategoryData getHabitObservationData() {
        return habitObservationData;
    }

    public void setHabitObservationData(CmObservationCategoryData habitObservationData) {
        this.habitObservationData = habitObservationData;
    }
}
