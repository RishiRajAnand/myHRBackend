package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "tm_therapy_planning_medicine")
public class TherapyPlanningMedicine extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "tm_therapy_planning_id")
    private TherapyPlanning therapyPlanning;

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

    @Column(nullable=true, name="bm_order_id")
    private Long bmOrderId;

    @Column(name = "total_quantity", nullable = true)
    private String totalQuantity;

    public Long getProductCatalogueCommonDetailId() {
        return productCatalogueCommonDetailId;
    }

    public void setProductCatalogueCommonDetailId(Long productCatalogueCommonDetailId) {
        this.productCatalogueCommonDetailId = productCatalogueCommonDetailId;
    }
    public TherapyPlanning getTherapyPlanning() {
        return therapyPlanning;
    }

    public void setTherapyPlanning(TherapyPlanning therapyPlanning) {
        this.therapyPlanning = therapyPlanning;
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
