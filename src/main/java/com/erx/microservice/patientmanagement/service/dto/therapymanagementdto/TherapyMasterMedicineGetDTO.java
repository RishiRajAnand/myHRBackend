package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 08-09-2018
* */

public class TherapyMasterMedicineGetDTO {

    private Long therapyMasterMedicineId;
    private Long pmProdCatalogueCommonDetailsId;
    private String medicineName;
    private Long medicineTypeMasterId;
    private String medicineTypeName;
    private Long uomMasterId;
    private String uomMasterName;
    private String quantity;
    private String medicineInstructions;

    public Long getTherapyMasterMedicineId() {
        return therapyMasterMedicineId;
    }

    public void setTherapyMasterMedicineId(Long therapyMasterMedicineId) {
        this.therapyMasterMedicineId = therapyMasterMedicineId;
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

    public String getMedicineTypeName() {
        return medicineTypeName;
    }

    public void setMedicineTypeName(String medicineTypeName) {
        this.medicineTypeName = medicineTypeName;
    }
}
