package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 04-09-2018
* */

public class SaveTherapyMasterMedicineDTO {

    private Long therapyMasterMedicineId;
    private Long pmProdCatalogueCommonDetailsId;
    private Long medicineTypeMasterId;
    private Long uomMasterId;
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
}
