package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 06-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;

@Entity
@Table(name="tm_therapy_master_medicine")
public class TherapyMasterMedicine extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "tm_therapy_master_id")
    private TherapyMaster therapyMaster;

    @Column(name = "pm_prod_catalogue_common_details")
    private Long productCatalogueCommonDetailId;

    @Column(name = "medicine_type_master_id")
    private Long medicineTypeId;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "uom_master_id")
    private Long uomMasterId;

    @Column(name = "medicine_instructions")
    private String medicineInstructions;

    public TherapyMaster getTherapyMaster() {
        return therapyMaster;
    }

    public void setTherapyMaster(TherapyMaster therapyMaster) {
        this.therapyMaster = therapyMaster;
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

    public Long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }
}
