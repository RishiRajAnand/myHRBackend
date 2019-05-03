package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 06-10-2018
* */

public class CmExamntnSarvaSrotoPareekshaDTO {

    private Long sarvaSrotoId;
    private CmObservationCategoryDataDTO cvs;
    private CmObservationCategoryDataDTO rs;
    private CmObservationCategoryDataDTO pa;
    private CmObservationCategoryDataDTO cns;
    private CmObservationCategoryDataDTO pr;
    private String localExamination;
    private String locoMotorSystem ;

    public Long getSarvaSrotoId() {
        return sarvaSrotoId;
    }

    public void setSarvaSrotoId(Long sarvaSrotoId) {
        this.sarvaSrotoId = sarvaSrotoId;
    }

    public CmObservationCategoryDataDTO getCvs() {
        return cvs;
    }

    public void setCvs(CmObservationCategoryDataDTO cvs) {
        this.cvs = cvs;
    }

    public CmObservationCategoryDataDTO getRs() {
        return rs;
    }

    public void setRs(CmObservationCategoryDataDTO rs) {
        this.rs = rs;
    }

    public CmObservationCategoryDataDTO getPa() {
        return pa;
    }

    public void setPa(CmObservationCategoryDataDTO pa) {
        this.pa = pa;
    }

    public CmObservationCategoryDataDTO getCns() {
        return cns;
    }

    public void setCns(CmObservationCategoryDataDTO cns) {
        this.cns = cns;
    }

    public CmObservationCategoryDataDTO getPr() {
        return pr;
    }

    public void setPr(CmObservationCategoryDataDTO pr) {
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
