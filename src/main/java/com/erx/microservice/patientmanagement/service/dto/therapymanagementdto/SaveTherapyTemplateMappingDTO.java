package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 12-09-2018
* */

import java.util.List;

public class SaveTherapyTemplateMappingDTO {

    private Long therapyTemplateMappingId;
    private Long serviceCatalogueId;
    private int numberOfDays;
    private String instructions;
    private List<SaveTherapyTemplateMedicineDTO> saveTherapyTemplateMedicineDTOs;
    private List<SaveTherapyTemplateMedicineTypeDTO> saveTherapyTemplateMedicineTypeDTOs;

    public Long getTherapyTemplateMappingId() {
        return therapyTemplateMappingId;
    }

    public void setTherapyTemplateMappingId(Long therapyTemplateMappingId) {
        this.therapyTemplateMappingId = therapyTemplateMappingId;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<SaveTherapyTemplateMedicineDTO> getSaveTherapyTemplateMedicineDTOs() {
        return saveTherapyTemplateMedicineDTOs;
    }

    public void setSaveTherapyTemplateMedicineDTOs(List<SaveTherapyTemplateMedicineDTO> saveTherapyTemplateMedicineDTOs) {
        this.saveTherapyTemplateMedicineDTOs = saveTherapyTemplateMedicineDTOs;
    }

    public List<SaveTherapyTemplateMedicineTypeDTO> getSaveTherapyTemplateMedicineTypeDTOs() {
        return saveTherapyTemplateMedicineTypeDTOs;
    }

    public void setSaveTherapyTemplateMedicineTypeDTOs(List<SaveTherapyTemplateMedicineTypeDTO> saveTherapyTemplateMedicineTypeDTOs) {
        this.saveTherapyTemplateMedicineTypeDTOs = saveTherapyTemplateMedicineTypeDTOs;
    }
}
