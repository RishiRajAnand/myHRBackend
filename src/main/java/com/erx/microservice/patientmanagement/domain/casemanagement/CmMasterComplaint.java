package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cm_complaint")

public class CmMasterComplaint extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cm_master_id", nullable=false)
    private CmMaster cmMaster;

    @Column(nullable=true, name="associated_complaint")
    private String associatedComplaints;

    @Column(nullable=true, name="present_illness")
    private String presentIllness;

    @Column(nullable=true, name="past_illness")
    private String pastIllness;

    @Column(nullable=true, name="family_history")
    private String familyHistory;

    @Column(nullable=true, name="treatment_history")
    private String treatmentHistory;

    @Column(nullable=true, name="gynaec_history")
    private String gynaecHistory;

    @Column(nullable=true, name="signs_symptoms")
    private String signsAndSymptoms;

    @Column(nullable=true, name="coital")
    private String coital;

    @Column(nullable=true, name="menstruation")
    private Integer menstruation;

    @Column(nullable=true, name="menopause")
    private Integer menopause;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "delivery_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData deliveryModeObservationData;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "oh_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData ohObservationData;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "ms_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData msObservationData;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "mc_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData mcObservationData;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "characteristic_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData characteristicsObservationData;

    public CmMaster getCmMaster() {
        return cmMaster;
    }

    public void setCmMaster(CmMaster cmMaster) {
        this.cmMaster = cmMaster;
    }

    public String getAssociatedComplaints() {
        return associatedComplaints;
    }

    public void setAssociatedComplaints(String associatedComplaints) {
        this.associatedComplaints = associatedComplaints;
    }

    public String getPresentIllness() {
        return presentIllness;
    }

    public void setPresentIllness(String presentIllness) {
        this.presentIllness = presentIllness;
    }

    public String getPastIllness() {
        return pastIllness;
    }

    public void setPastIllness(String pastIllness) {
        this.pastIllness = pastIllness;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getTreatmentHistory() {
        return treatmentHistory;
    }

    public void setTreatmentHistory(String treatmentHistory) {
        this.treatmentHistory = treatmentHistory;
    }

    public String getGynaecHistory() {
        return gynaecHistory;
    }

    public void setGynaecHistory(String gynaecHistory) {
        this.gynaecHistory = gynaecHistory;
    }

    public String getSignsAndSymptoms() {
        return signsAndSymptoms;
    }

    public void setSignsAndSymptoms(String signsAndSymptoms) {
        this.signsAndSymptoms = signsAndSymptoms;
    }

    public String getCoital() {
        return coital;
    }

    public void setCoital(String coital) {
        this.coital = coital;
    }

    public Integer getMenstruation() {
        return menstruation;
    }

    public void setMenstruation(Integer menstruation) {
        this.menstruation = menstruation;
    }

    public Integer getMenopause() {
        return menopause;
    }

    public void setMenopause(Integer menopause) {
        this.menopause = menopause;
    }

    public CmObservationCategoryData getDeliveryModeObservationData() {
        return deliveryModeObservationData;
    }

    public void setDeliveryModeObservationData(CmObservationCategoryData deliveryModeObservationData) {
        this.deliveryModeObservationData = deliveryModeObservationData;
    }

    public CmObservationCategoryData getOhObservationData() {
        return ohObservationData;
    }

    public void setOhObservationData(CmObservationCategoryData ohObservationData) {
        this.ohObservationData = ohObservationData;
    }

    public CmObservationCategoryData getMsObservationData() {
        return msObservationData;
    }

    public void setMsObservationData(CmObservationCategoryData msObservationData) {
        this.msObservationData = msObservationData;
    }

    public CmObservationCategoryData getMcObservationData() {
        return mcObservationData;
    }

    public void setMcObservationData(CmObservationCategoryData mcObservationData) {
        this.mcObservationData = mcObservationData;
    }

    public CmObservationCategoryData getCharacteristicsObservationData() {
        return characteristicsObservationData;
    }

    public void setCharacteristicsObservationData(CmObservationCategoryData characteristicsObservationData) {
        this.characteristicsObservationData = characteristicsObservationData;
    }
}
