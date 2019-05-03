package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 25-08-2018
* */

public class GroupIndividualMedicineDTO {

    private Long bmOrderId;
    private Long cmTreatmentGroupMedicineDetailId;
    private Long productCatalogueCommonDetailId;
    private Long productStatus; // 0- Not Changed, 1- Added, 2- Updated, 3- Deleted
    private Long uomMasterId;
    private String strength;
    private String composition; // quantity
    private String totalQuantity;
    private String instructions;

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public Long getCmTreatmentGroupMedicineDetailId() {
        return cmTreatmentGroupMedicineDetailId;
    }

    public void setCmTreatmentGroupMedicineDetailId(Long cmTreatmentGroupMedicineDetailId) {
        this.cmTreatmentGroupMedicineDetailId = cmTreatmentGroupMedicineDetailId;
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

    public Long getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Long productStatus) {
        this.productStatus = productStatus;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
