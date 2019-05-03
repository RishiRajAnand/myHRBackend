package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cm_exam_sarva_srotopareeksha")
public class CmExamntnSarvaSrotoPareeksha extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cvs_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData cvs;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "rs_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData rs;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "pa_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData pa;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cns_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData cns;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "pr_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData pr;

    @Column(name="local_examination", nullable=true)
    private String localExamination;

    @Column(name="instruction", nullable=true)
    private String locoMotorSystem ;

    public CmObservationCategoryData getCvs() {
        return cvs;
    }

    public void setCvs(CmObservationCategoryData cvs) {
        this.cvs = cvs;
    }

    public CmObservationCategoryData getRs() {
        return rs;
    }

    public void setRs(CmObservationCategoryData rs) {
        this.rs = rs;
    }

    public CmObservationCategoryData getPa() {
        return pa;
    }

    public void setPa(CmObservationCategoryData pa) {
        this.pa = pa;
    }

    public CmObservationCategoryData getCns() {
        return cns;
    }

    public void setCns(CmObservationCategoryData cns) {
        this.cns = cns;
    }

    public CmObservationCategoryData getPr() {
        return pr;
    }

    public void setPr(CmObservationCategoryData pr) {
        this.pr = pr;
    }

    public String getLocalExamination() {
        return localExamination;
    }

    public void setLocalExamination(String localExamination) {
        this.localExamination = localExamination;
    }

    public String getLocoMotorSystem() {
        return locoMotorSystem;
    }

    public void setLocoMotorSystem(String locoMotorSystem) {
        this.locoMotorSystem = locoMotorSystem;
    }
}
