package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 25-08-2018
* */

public class TemplateGroupIndividualMedicineDTO {

    private Long cmTemplateGroupMedicineId;
    private Long productCatalogueCommonDetailId;
    private Long uomMasterId;
    private String strength;
    private String composition; // quantity
    private String totalQuantity;
    private String instructions;

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

    public Long getUomMasterId() {
        return uomMasterId;
    }

    public void setUomMasterId(Long uomMasterId) {
        this.uomMasterId = uomMasterId;
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
