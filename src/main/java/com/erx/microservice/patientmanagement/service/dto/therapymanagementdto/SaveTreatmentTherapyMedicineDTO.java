package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 10-09-2018
* */

public class SaveTreatmentTherapyMedicineDTO {

    private Long bmOrderId;
    private Long therapyPlanningMedicineId;
    private Long pmProdCatalogueCommonDetailsId;
    private Long productStatus; // 0- Not Changed, 1- Added, 2- Updated, 3- Deleted
    private Long medicineTypeMasterId;
    private Long uomMasterId;
    private String quantity;
    private String medicineInstructions;
    private String totalQuantity;

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public Long getTherapyPlanningMedicineId() {
        return therapyPlanningMedicineId;
    }

    public void setTherapyPlanningMedicineId(Long therapyPlanningMedicineId) {
        this.therapyPlanningMedicineId = therapyPlanningMedicineId;
    }

    public Long getPmProdCatalogueCommonDetailsId() {
        return pmProdCatalogueCommonDetailsId;
    }

    public void setPmProdCatalogueCommonDetailsId(Long pmProdCatalogueCommonDetailsId) {
        this.pmProdCatalogueCommonDetailsId = pmProdCatalogueCommonDetailsId;
    }

    public Long getUomMasterId() {
        return uomMasterId;
    }

    public void setUomMasterId(Long uomMasterId) {
        this.uomMasterId = uomMasterId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMedicineInstructions() {
        return medicineInstructions;
    }

    public void setMedicineInstructions(String medicineInstructions) {
        this.medicineInstructions = medicineInstructions;
    }

    public Long getMedicineTypeMasterId() {
        return medicineTypeMasterId;
    }

    public void setMedicineTypeMasterId(Long medicineTypeMasterId) {
        this.medicineTypeMasterId = medicineTypeMasterId;
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
