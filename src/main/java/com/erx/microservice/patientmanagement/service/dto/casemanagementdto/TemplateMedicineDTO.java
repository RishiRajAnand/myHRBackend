package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 26-08-2018
* */

import java.util.List;

public class TemplateMedicineDTO {

    private Long templateMedicineId;
    private Long productCatalogueCommonDetailId;
    private int numberOfDays;
    private Long cmDosageValueMappingId;
    private Long uomMasterId;
    private List<DosageTimeDTO> dosageTimeDTOs;
    private String strength;
    private String instruction;
    private String description;
    private Long medicineGroupTypeId;
    private String medicineGroupTypeName;
    private String dosageQuantity;
    private String totalQuantity;

    public Long getTemplateMedicineId() {
        return templateMedicineId;
    }

    public void setTemplateMedicineId(Long templateMedicineId) {
        this.templateMedicineId = templateMedicineId;
    }

    public Long getProductCatalogueCommonDetailId() {
        return productCatalogueCommonDetailId;
    }

    public void setProductCatalogueCommonDetailId(Long productCatalogueCommonDetailId) {
        this.productCatalogueCommonDetailId = productCatalogueCommonDetailId;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Long getCmDosageValueMappingId() {
        return cmDosageValueMappingId;
    }

    public void setCmDosageValueMappingId(Long cmDosageValueMappingId) {
        this.cmDosageValueMappingId = cmDosageValueMappingId;
    }

    public List<DosageTimeDTO> getDosageTimeDTOs() {
        return dosageTimeDTOs;
    }

    public void setDosageTimeDTOs(List<DosageTimeDTO> dosageTimeDTOs) {
        this.dosageTimeDTOs = dosageTimeDTOs;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUomMasterId() {
        return uomMasterId;
    }

    public void setUomMasterId(Long uomMasterId) {
        this.uomMasterId = uomMasterId;
    }

    public Long getMedicineGroupTypeId() {
        return medicineGroupTypeId;
    }

    public void setMedicineGroupTypeId(Long medicineGroupTypeId) {
        this.medicineGroupTypeId = medicineGroupTypeId;
    }

    public String getMedicineGroupTypeName() {
        return medicineGroupTypeName;
    }

    public void setMedicineGroupTypeName(String medicineGroupTypeName) {
        this.medicineGroupTypeName = medicineGroupTypeName;
    }

    public String getDosageQuantity() {
        return dosageQuantity;
    }

    public void setDosageQuantity(String dosageQuantity) {
        this.dosageQuantity = dosageQuantity;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
