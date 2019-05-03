package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 20/08/18.
 */

public class CmMasterComplaintDTO {

    private String associatedComplaints;
    private String presentIllness;
    private String pastIllness;
    private String familyHistory;
    private String treatmentHistory;
    private String gynaecHistory;
    private String signsAndSymptoms;
    private String coital;
    private Integer menstruation;
    private Integer menopause;
    private CmObservationCategoryDataDTO deliveryModeObservationDataDTO;
    private CmObservationCategoryDataDTO ohObservationDataDTO;
    private CmObservationCategoryDataDTO msObservationDataDTO;
    private CmObservationCategoryDataDTO mcObservationDataDTO;
    private CmObservationCategoryDataDTO characteristicsObservationDataDTO;

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

    public CmObservationCategoryDataDTO getDeliveryModeObservationDataDTO() {
        return deliveryModeObservationDataDTO;
    }

    public void setDeliveryModeObservationDataDTO(CmObservationCategoryDataDTO deliveryModeObservationDataDTO) {
        this.deliveryModeObservationDataDTO = deliveryModeObservationDataDTO;
    }

    public CmObservationCategoryDataDTO getOhObservationDataDTO() {
        return ohObservationDataDTO;
    }

    public void setOhObservationDataDTO(CmObservationCategoryDataDTO ohObservationDataDTO) {
        this.ohObservationDataDTO = ohObservationDataDTO;
    }

    public CmObservationCategoryDataDTO getMsObservationDataDTO() {
        return msObservationDataDTO;
    }

    public void setMsObservationDataDTO(CmObservationCategoryDataDTO msObservationDataDTO) {
        this.msObservationDataDTO = msObservationDataDTO;
    }

    public CmObservationCategoryDataDTO getMcObservationDataDTO() {
        return mcObservationDataDTO;
    }

    public void setMcObservationDataDTO(CmObservationCategoryDataDTO mcObservationDataDTO) {
        this.mcObservationDataDTO = mcObservationDataDTO;
    }

    public CmObservationCategoryDataDTO getCharacteristicsObservationDataDTO() {
        return characteristicsObservationDataDTO;
    }

    public void setCharacteristicsObservationDataDTO(CmObservationCategoryDataDTO characteristicsObservationDataDTO) {
        this.characteristicsObservationDataDTO = characteristicsObservationDataDTO;
    }
}
