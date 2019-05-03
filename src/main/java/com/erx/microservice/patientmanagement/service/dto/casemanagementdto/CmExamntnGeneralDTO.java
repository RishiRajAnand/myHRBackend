package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 06-10-2018
* */

public class CmExamntnGeneralDTO {

    private Long generalId;
    private CmObservationCategoryDataDTO conjuctiva;
    private CmObservationCategoryDataDTO tongue;
    private CmObservationCategoryDataDTO nails;
    private CmObservationCategoryDataDTO pulse;
    private CmObservationCategoryDataDTO skin;
    private String bloodPressure;

    public Long getGeneralId() {
        return generalId;
    }

    public void setGeneralId(Long generalId) {
        this.generalId = generalId;
    }

    public CmObservationCategoryDataDTO getConjuctiva() {
        return conjuctiva;
    }

    public void setConjuctiva(CmObservationCategoryDataDTO conjuctiva) {
        this.conjuctiva = conjuctiva;
    }

    public CmObservationCategoryDataDTO getTongue() {
        return tongue;
    }

    public void setTongue(CmObservationCategoryDataDTO tongue) {
        this.tongue = tongue;
    }

    public CmObservationCategoryDataDTO getNails() {
        return nails;
    }

    public void setNails(CmObservationCategoryDataDTO nails) {
        this.nails = nails;
    }

    public CmObservationCategoryDataDTO getPulse() {
        return pulse;
    }

    public void setPulse(CmObservationCategoryDataDTO pulse) {
        this.pulse = pulse;
    }

    public CmObservationCategoryDataDTO getSkin() {
        return skin;
    }

    public void setSkin(CmObservationCategoryDataDTO skin) {
        this.skin = skin;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }
}
