package com.erx.microservice.patientmanagement.service.dto;

/**
 * Created by Latha on 30-11-2017.
 */
public class GenerateUniqueIDDTO {

    private String currentTableName;
    private String currentColumnName;
    private GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO;
    private Long ClinicLocationId;

    public String getCurrentTableName() {
        return currentTableName;
    }

    public void setCurrentTableName(String currentTableName) {
        this.currentTableName = currentTableName;
    }

    public String getCurrentColumnName() {
        return currentColumnName;
    }

    public void setCurrentColumnName(String currentColumnName) {
        this.currentColumnName = currentColumnName;
    }

    public GenerateUniqueIDClinicDTO getGenerateUniqueIDClinicDTO() {
        return generateUniqueIDClinicDTO;
    }

    public void setGenerateUniqueIDClinicDTO(GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO) {
        this.generateUniqueIDClinicDTO = generateUniqueIDClinicDTO;
    }

    public Long getClinicLocationId() {
        return ClinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        ClinicLocationId = clinicLocationId;
    }
}
