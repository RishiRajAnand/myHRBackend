package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cm_exam_dashavidha")

public class CmExamntnDashavidha extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "prakruthi_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData prakruthi;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "satva_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData satva;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "sara_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData sara;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "samhanana_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData samhanana;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "aharashakthi_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData aharaShakthi;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "vyayama_shakthi_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData vyayamaShakthi;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "pramana_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData pramana;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "vayaha_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData vayaha;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "satmya_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData satmya;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "vikruti_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData vikruthi;

    public CmObservationCategoryData getPrakruthi() {
        return prakruthi;
    }

    public void setPrakruthi(CmObservationCategoryData prakruthi) {
        this.prakruthi = prakruthi;
    }

    public CmObservationCategoryData getSatva() {
        return satva;
    }

    public void setSatva(CmObservationCategoryData satva) {
        this.satva = satva;
    }

    public CmObservationCategoryData getSara() {
        return sara;
    }

    public void setSara(CmObservationCategoryData sara) {
        this.sara = sara;
    }

    public CmObservationCategoryData getSamhanana() {
        return samhanana;
    }

    public void setSamhanana(CmObservationCategoryData samhanana) {
        this.samhanana = samhanana;
    }

    public CmObservationCategoryData getAharaShakthi() {
        return aharaShakthi;
    }

    public void setAharaShakthi(CmObservationCategoryData aharaShakthi) {
        this.aharaShakthi = aharaShakthi;
    }

    public CmObservationCategoryData getVyayamaShakthi() {
        return vyayamaShakthi;
    }

    public void setVyayamaShakthi(CmObservationCategoryData vyayamaShakthi) {
        this.vyayamaShakthi = vyayamaShakthi;
    }

    public CmObservationCategoryData getPramana() {
        return pramana;
    }

    public void setPramana(CmObservationCategoryData pramana) {
        this.pramana = pramana;
    }

    public CmObservationCategoryData getVayaha() {
        return vayaha;
    }

    public void setVayaha(CmObservationCategoryData vayaha) {
        this.vayaha = vayaha;
    }

    public CmObservationCategoryData getSatmya() {
        return satmya;
    }

    public void setSatmya(CmObservationCategoryData satmya) {
        this.satmya = satmya;
    }

    public CmObservationCategoryData getVikruthi() {
        return vikruthi;
    }

    public void setVikruthi(CmObservationCategoryData vikruthi) {
        this.vikruthi = vikruthi;
    }
}
