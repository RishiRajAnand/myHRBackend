package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 11-09-2018
* */

public class CmTherapyTreatmentMedicineDTO {

    private Long therapyPlanningMedicineId;
    private Long bmOrderId;
    private Long pmProdCatalogueCommonDetailsId;
    private String medicineName;
    private Long medicineTypeId;
    private String medicineTypeName;
    private String quantity;
    private Long uomMasterId;
    private String uomMasterName;
    private String medicineInstructions;

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

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Long getUomMasterId() {
        return uomMasterId;
    }

    public void setUomMasterId(Long uomMasterId) {
        this.uomMasterId = uomMasterId;
    }

    public String getUomMasterName() {
        return uomMasterName;
    }

    public void setUomMasterName(String uomMasterName) {
        this.uomMasterName = uomMasterName;
    }

    public String getMedicineInstructions() {
        return medicineInstructions;
    }

    public void setMedicineInstructions(String medicineInstructions) {
        this.medicineInstructions = medicineInstructions;
    }

    public Long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }

    public String getMedicineTypeName() {
        return medicineTypeName;
    }

    public void setMedicineTypeName(String medicineTypeName) {
        this.medicineTypeName = medicineTypeName;
    }
}
