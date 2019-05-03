package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 26-08-2018
* */

public class BKDGroupDTO {

    private Long cmMedicineGroupId;
    private Long productCatalogueCommonDetailId;
    // private String strength;
    private String instruction;
    private String dosageQuantity; // composition
    private Long uomMasterId;
    private String totalQuantity;


    public Long getCmMedicineGroupId() {
        return cmMedicineGroupId;
    }

    public void setCmMedicineGroupId(Long cmMedicineGroupId) {
        this.cmMedicineGroupId = cmMedicineGroupId;
    }

    public Long getProductCatalogueCommonDetailId() {
        return productCatalogueCommonDetailId;
    }

    public void setProductCatalogueCommonDetailId(Long productCatalogueCommonDetailId) {
        this.productCatalogueCommonDetailId = productCatalogueCommonDetailId;
    }

    /*public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }*/

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDosageQuantity() {
        return dosageQuantity;
    }

    public void setDosageQuantity(String dosageQuantity) {
        this.dosageQuantity = dosageQuantity;
    }

    public Long getUomMasterId() {
        return uomMasterId;
    }

    public void setUomMasterId(Long uomMasterId) {
        this.uomMasterId = uomMasterId;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
