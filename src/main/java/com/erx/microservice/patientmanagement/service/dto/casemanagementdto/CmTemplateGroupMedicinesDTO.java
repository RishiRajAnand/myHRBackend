package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 27-08-2018
* */

public class CmTemplateGroupMedicinesDTO {

    private Long cmTemplateGroupMedicineId;
    private Long productCatalogueCommonDetailId;
    private String productCatalogueCommonDetailName;
    private Long uomId;
    private String uomName;
    private String strength;
    private String composition; // quantity
    private String instructions;
    private String totalQuantity;

    public Long getCmTemplateGroupMedicineId() {
        return cmTemplateGroupMedicineId;
    }

    public void setCmTemplateGroupMedicineId(Long cmTemplateGroupMedicineId) {
        this.cmTemplateGroupMedicineId = cmTemplateGroupMedicineId;
    }

    public Long getProductCatalogueCommonDetailId() {
        return productCatalogueCommonDetailId;
    }

    public void setProductCatalogueCommonDetailId(Long productCatalogueCommonDetailId) {
        this.productCatalogueCommonDetailId = productCatalogueCommonDetailId;
    }

    public String getProductCatalogueCommonDetailName() {
        return productCatalogueCommonDetailName;
    }

    public void setProductCatalogueCommonDetailName(String productCatalogueCommonDetailName) {
        this.productCatalogueCommonDetailName = productCatalogueCommonDetailName;
    }

    public Long getUomId() {
        return uomId;
    }

    public void setUomId(Long uomId) {
        this.uomId = uomId;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
