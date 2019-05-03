package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 12-09-2018
* */

public class SaveTherapyTemplateMedicineDTO {

    private Long therapyTemplateMedicineId;
    private Long productCatalogueCommonDetailId;
    private Long medicineTypeMasterId;
    private String quantity;
    private Long uomMasterId;
    private String medicineInstructions;

    public Long getTherapyTemplateMedicineId() {
        return therapyTemplateMedicineId;
    }

    public void setTherapyTemplateMedicineId(Long therapyTemplateMedicineId) {
        this.therapyTemplateMedicineId = therapyTemplateMedicineId;
    }

    public Long getProductCatalogueCommonDetailId() {
        return productCatalogueCommonDetailId;
    }

    public void setProductCatalogueCommonDetailId(Long productCatalogueCommonDetailId) {
        this.productCatalogueCommonDetailId = productCatalogueCommonDetailId;
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
