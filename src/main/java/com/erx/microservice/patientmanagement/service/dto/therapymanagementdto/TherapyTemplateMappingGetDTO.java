package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 12-09-2018
* */


import java.util.List;

public class TherapyTemplateMappingGetDTO {

    private Long therapyTemplateMappingId;
    private Long serviceCatalogueId;
    private String therapyName;
    private int numberOfDays;
    private String instructions;
    private List<TherapyTemplateMedicineGetDTO> therapyTemplateMedicineGetDTOs;
    private List<TherapyTemplateMedicineTypeGetDTO> therapyTemplateMedicineTypeGetDTOs;

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

    public String getTherapyName() {
        return therapyName;
    }

    public void setTherapyName(String therapyName) {
        this.therapyName = therapyName;
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

    public List<TherapyTemplateMedicineGetDTO> getTherapyTemplateMedicineGetDTOs() {
        return therapyTemplateMedicineGetDTOs;
    }

    public void setTherapyTemplateMedicineGetDTOs(List<TherapyTemplateMedicineGetDTO> therapyTemplateMedicineGetDTOs) {
        this.therapyTemplateMedicineGetDTOs = therapyTemplateMedicineGetDTOs;
    }

    public List<TherapyTemplateMedicineTypeGetDTO> getTherapyTemplateMedicineTypeGetDTOs() {
        return therapyTemplateMedicineTypeGetDTOs;
    }

    public void setTherapyTemplateMedicineTypeGetDTOs(List<TherapyTemplateMedicineTypeGetDTO> therapyTemplateMedicineTypeGetDTOs) {
        this.therapyTemplateMedicineTypeGetDTOs = therapyTemplateMedicineTypeGetDTOs;
    }
}
