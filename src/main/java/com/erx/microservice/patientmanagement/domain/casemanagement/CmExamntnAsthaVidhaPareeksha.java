package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cm_exam_astha_vidha")
public class CmExamntnAsthaVidhaPareeksha extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "akriti_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData akriti;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "sabda_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData sabda;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "netra_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData netra;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "sparsha_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData sparsha;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "mutra_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData mutra;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "mala_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData mala;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "nadi_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData nadi;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "jivha_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData jivha;

    public CmObservationCategoryData getAkriti() {
        return akriti;
    }

    public void setAkriti(CmObservationCategoryData akriti) {
        this.akriti = akriti;
    }

    public CmObservationCategoryData getSabda() {
        return sabda;
    }

    public void setSabda(CmObservationCategoryData sabda) {
        this.sabda = sabda;
    }

    public CmObservationCategoryData getNetra() {
        return netra;
    }

    public void setNetra(CmObservationCategoryData netra) {
        this.netra = netra;
    }

    public CmObservationCategoryData getSparsha() {
        return sparsha;
    }

    public void setSparsha(CmObservationCategoryData sparsha) {
        this.sparsha = sparsha;
    }

    public CmObservationCategoryData getMutra() {
        return mutra;
    }

    public void setMutra(CmObservationCategoryData mutra) {
        this.mutra = mutra;
    }

    public CmObservationCategoryData getMala() {
        return mala;
    }

    public void setMala(CmObservationCategoryData mala) {
        this.mala = mala;
    }

    public CmObservationCategoryData getNadi() {
        return nadi;
    }

    public void setNadi(CmObservationCategoryData nadi) {
        this.nadi = nadi;
    }

    public CmObservationCategoryData getJivha() {
        return jivha;
    }

    public void setJivha(CmObservationCategoryData jivha) {
        this.jivha = jivha;
    }
}
