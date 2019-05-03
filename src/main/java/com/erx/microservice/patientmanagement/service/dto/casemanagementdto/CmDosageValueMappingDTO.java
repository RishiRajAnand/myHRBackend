package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 24/08/18.
 */

public class CmDosageValueMappingDTO {

    private Long cmDosageValueMappingId;
    private String dosageName;
    private String dosageValue;
    private int dosageNumber;
    private String patientInstructions;

    public Long getCmDosageValueMappingId() {
        return cmDosageValueMappingId;
    }

    public void setCmDosageValueMappingId(Long cmDosageValueMappingId) {
        this.cmDosageValueMappingId = cmDosageValueMappingId;
    }

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
    }

    public String getDosageValue() {
        return dosageValue;
    }

    public void setDosageValue(String dosageValue) {
        this.dosageValue = dosageValue;
    }

    public String getPatientInstructions() {
        return patientInstructions;
    }

    public void setPatientInstructions(String patientInstructions) {
        this.patientInstructions = patientInstructions;
    }

    public int getDosageNumber() {
        return dosageNumber;
    }

    public void setDosageNumber(int dosageNumber) {
        this.dosageNumber = dosageNumber;
    }
}
