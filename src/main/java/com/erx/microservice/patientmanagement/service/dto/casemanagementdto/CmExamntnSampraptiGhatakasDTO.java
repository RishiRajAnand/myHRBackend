package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 06-10-2018
* */

public class CmExamntnSampraptiGhatakasDTO {

    private Long sampraptiId;
    private CmObservationCategoryDataDTO dosha;
    private CmObservationCategoryDataDTO dooshya;
    private CmObservationCategoryDataDTO srotas;
    private CmObservationCategoryDataDTO srotoDushti;
    private CmObservationCategoryDataDTO rogaMarga;
    private CmObservationCategoryDataDTO sadhyaAsadhyata;

    public Long getSampraptiId() {
        return sampraptiId;
    }

    public void setSampraptiId(Long sampraptiId) {
        this.sampraptiId = sampraptiId;
    }

    public CmObservationCategoryDataDTO getDosha() {
        return dosha;
    }

    public void setDosha(CmObservationCategoryDataDTO dosha) {
        this.dosha = dosha;
    }

    public CmObservationCategoryDataDTO getDooshya() {
        return dooshya;
    }

    public void setDooshya(CmObservationCategoryDataDTO dooshya) {
        this.dooshya = dooshya;
    }

    public CmObservationCategoryDataDTO getSrotas() {
        return srotas;
    }

    public void setSrotas(CmObservationCategoryDataDTO srotas) {
        this.srotas = srotas;
    }

    public CmObservationCategoryDataDTO getSrotoDushti() {
        return srotoDushti;
    }

    public void setSrotoDushti(CmObservationCategoryDataDTO srotoDushti) {
        this.srotoDushti = srotoDushti;
    }

    public CmObservationCategoryDataDTO getRogaMarga() {
        return rogaMarga;
    }

    public void setRogaMarga(CmObservationCategoryDataDTO rogaMarga) {
        this.rogaMarga = rogaMarga;
    }

    public CmObservationCategoryDataDTO getSadhyaAsadhyata() {
        return sadhyaAsadhyata;
    }

    public void setSadhyaAsadhyata(CmObservationCategoryDataDTO sadhyaAsadhyata) {
        this.sadhyaAsadhyata = sadhyaAsadhyata;
    }
}
