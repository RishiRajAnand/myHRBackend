package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "cm_template_group_medicine_detail")
public class CmTemplateGroupMedicineDetail extends BaseModel{

    @ManyToOne(fetch= FetchType.EAGER, optional = true)
    @JoinColumn(name = "cm_template_medicine_mapping_id")
    private CmTemplateMedicine cmTemplateMedicine;

    @Column(name = "pm_prod_catalogue_common_details", nullable=true)
    private Long productCatalogueCommonDetailId;

    @Column(name = "uom_master_id", nullable=true)
    private Long uomMasterId;

    @Column(name = "strength", nullable=true)
    private String strength;

    @Column(name = "composition", nullable=true)
    private String composition; // quantity

    @Column(name = "instruction", nullable=true)
    private String instructions;

    @Column(name = "total_quantity", nullable = true)
    private String totalQuantity;

    public CmTemplateMedicine getCmTemplateMedicine() {
        return cmTemplateMedicine;
    }

    public void setCmTemplateMedicine(CmTemplateMedicine cmTemplateMedicine) {
        this.cmTemplateMedicine = cmTemplateMedicine;
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
