package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cm_exam_samprapti_ghatakas")
public class CmExamntnSampraptiGhatakas extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "dosha_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData dosha;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "dooshya_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData dooshya;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "srotas_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData srotas;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "sroto_dushti_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData srotoDushti;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "roga_marga_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData rogaMarga;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "sadhya_asadhyata_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData sadhyaAsadhyata;

    public CmObservationCategoryData getDosha() {
        return dosha;
    }

    public void setDosha(CmObservationCategoryData dosha) {
        this.dosha = dosha;
    }

    public CmObservationCategoryData getDooshya() {
        return dooshya;
    }

    public void setDooshya(CmObservationCategoryData dooshya) {
        this.dooshya = dooshya;
    }

    public CmObservationCategoryData getSrotas() {
        return srotas;
    }

    public void setSrotas(CmObservationCategoryData srotas) {
        this.srotas = srotas;
    }

    public CmObservationCategoryData getSrotoDushti() {
        return srotoDushti;
    }

    public void setSrotoDushti(CmObservationCategoryData srotoDushti) {
        this.srotoDushti = srotoDushti;
    }

    public CmObservationCategoryData getRogaMarga() {
        return rogaMarga;
    }

    public void setRogaMarga(CmObservationCategoryData rogaMarga) {
        this.rogaMarga = rogaMarga;
    }

    public CmObservationCategoryData getSadhyaAsadhyata() {
        return sadhyaAsadhyata;
    }

    public void setSadhyaAsadhyata(CmObservationCategoryData sadhyaAsadhyata) {
        this.sadhyaAsadhyata = sadhyaAsadhyata;
    }
}
