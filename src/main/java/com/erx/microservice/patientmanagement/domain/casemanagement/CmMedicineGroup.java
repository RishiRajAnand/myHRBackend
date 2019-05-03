package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "medicine_group")
public class CmMedicineGroup extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "group_medicine_master_id")
    private CmGroupMedicineMaster groupMedicineMaster;

    @Column(name = "pm_prod_catalogue_common_details_id")
    private Long productCatalogueCommonDetailId;

    @Column(name = "uom_master_id")
    private Long uomMasterId;

    @Column(name = "strength", nullable = true)
    private String strength;

    @Column(name = "instruction", nullable = true)
    private String instruction;

    @Column(name="composition", nullable = true)
    private String composition; // dosage quantity

    @Column(name = "total_quantity", nullable = true)
    private String totalQuantity;


    public CmGroupMedicineMaster getGroupMedicineMaster() {
        return groupMedicineMaster;
    }

    public void setGroupMedicineMaster(CmGroupMedicineMaster groupMedicineMaster) {
        this.groupMedicineMaster = groupMedicineMaster;
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
