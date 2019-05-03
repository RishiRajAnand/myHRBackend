package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 24-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "cm_treatment_group_medicine_detail")
public class CmTreatmentGroupMedicineDetail extends BaseModel{

    @ManyToOne(fetch= FetchType.EAGER, optional = true)
    @JoinColumn(name = "cm_treatment_medicine_detail_id")
    private CmTreatmentMedicineDetail cmTreatmentMedicineList;

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

    @Column(nullable=true, name="bm_order_id")
    private Long bmOrderId;

    @Column(name = "total_quantity", nullable = true)
    private String totalQuantity;

    public CmTreatmentMedicineDetail getCmTreatmentMedicineList() {
        return cmTreatmentMedicineList;
    }

    public void setCmTreatmentMedicineList(CmTreatmentMedicineDetail cmTreatmentMedicineList) {
        this.cmTreatmentMedicineList = cmTreatmentMedicineList;
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

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
