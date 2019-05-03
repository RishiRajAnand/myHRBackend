package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 06-10-2018
* */

public class CmExamntnAsthaVidhaPareekshaDTO {

    private Long asthaVidhaId;
    private CmObservationCategoryDataDTO akriti;
    private CmObservationCategoryDataDTO sabda;
    private CmObservationCategoryDataDTO netra;
    private CmObservationCategoryDataDTO sparsha;
    private CmObservationCategoryDataDTO mutra;
    private CmObservationCategoryDataDTO mala;
    private CmObservationCategoryDataDTO nadi;
    private CmObservationCategoryDataDTO jivha;

    public Long getAsthaVidhaId() {
        return asthaVidhaId;
    }

    public void setAsthaVidhaId(Long asthaVidhaId) {
        this.asthaVidhaId = asthaVidhaId;
    }

    public CmObservationCategoryDataDTO getAkriti() {
        return akriti;
    }

    public void setAkriti(CmObservationCategoryDataDTO akriti) {
        this.akriti = akriti;
    }

    public CmObservationCategoryDataDTO getSabda() {
        return sabda;
    }

    public void setSabda(CmObservationCategoryDataDTO sabda) {
        this.sabda = sabda;
    }

    public CmObservationCategoryDataDTO getNetra() {
        return netra;
    }

    public void setNetra(CmObservationCategoryDataDTO netra) {
        this.netra = netra;
    }

    public CmObservationCategoryDataDTO getSparsha() {
        return sparsha;
    }

    public void setSparsha(CmObservationCategoryDataDTO sparsha) {
        this.sparsha = sparsha;
    }

    public CmObservationCategoryDataDTO getMutra() {
        return mutra;
    }

    public void setMutra(CmObservationCategoryDataDTO mutra) {
        this.mutra = mutra;
    }

    public CmObservationCategoryDataDTO getMala() {
        return mala;
    }

    public void setMala(CmObservationCategoryDataDTO mala) {
        this.mala = mala;
    }

    public CmObservationCategoryDataDTO getNadi() {
        return nadi;
    }

    public void setNadi(CmObservationCategoryDataDTO nadi) {
        this.nadi = nadi;
    }

    public CmObservationCategoryDataDTO getJivha() {
        return jivha;
    }

    public void setJivha(CmObservationCategoryDataDTO jivha) {
        this.jivha = jivha;
    }
}
