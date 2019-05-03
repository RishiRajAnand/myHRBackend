package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;

@Entity
@Table(name="tm_therapy_template_medicine")
public class TherapyTemplateMedicine extends BaseModel{

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="tm_therapy_template_mapping_id")
    private TherapyTemplateMapping therapyTemplateMapping;

    @Column(name = "medicine_type_master_id")
    private Long medicineTypeId;

    @Column(name = "pm_prod_catalogue_common_details")
    private Long productCatalogueCommonDetailId;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "uom_master_id")
    private Long uomMasterId;

    @Column(name = "medicine_instructions")
    private String medicineInstructions;

    public TherapyTemplateMapping getTherapyTemplateMapping() {
        return therapyTemplateMapping;
    }

    public void setTherapyTemplateMapping(TherapyTemplateMapping therapyTemplateMapping) {
        this.therapyTemplateMapping = therapyTemplateMapping;
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
