package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 24-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cm_treatment_medicine_detail")
public class CmTreatmentMedicineDetail extends BaseModel{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cm_treatment_id", nullable = false)
    @JsonIgnore
    private CmTreatment cmTreatment;

    @ManyToOne
    @JoinColumn(name = "route_of_administration_id", nullable = true)
    private RouteOfAdministration routeOfAdministration;

    @ManyToOne
    @JoinColumn(name = "cm_dosage_value_mapping_id", nullable = true)
    private CmDosageValueMapping cmDosageValueMapping;

    @Column(name = "pm_prod_catalogue_common_details", nullable = true)
    private Long productCatalogueCommonDetailId;

    @Column(name = "uom_master_id", nullable = true)
    private Long uomMasterId;

    @Column(name = "dosage_quantity", nullable = true)
    private String dosageQuantity;

    @Column(name = "dosage_time", nullable = true)
    private String dosageTime;

    @Column(name = "num_days", nullable = true)
    private Integer numberOfDays;

    @Column(name = "instruction", nullable = true)
    private String instructions;

    @OneToOne
    @JoinColumn(name = "medicine_group_type_lookup_id")
    private LookupValue medicineGroupType;

    @Column(nullable=true, name="bm_order_id")
    private Long bmOrderId;

    @OneToMany(mappedBy = "cmTreatmentMedicineList")
    private List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineList;

    @Column(name = "total_quantity", nullable = true)
    private String totalQuantity;

    public CmTreatment getCmTreatment() {
        return cmTreatment;
    }

    public void setCmTreatment(CmTreatment cmTreatment) {
        this.cmTreatment = cmTreatment;
    }

    public RouteOfAdministration getRouteOfAdministration() {
        return routeOfAdministration;
    }

    public void setRouteOfAdministration(RouteOfAdministration routeOfAdministration) {
        this.routeOfAdministration = routeOfAdministration;
    }

    public CmDosageValueMapping getCmDosageValueMapping() {
        return cmDosageValueMapping;
    }

    public void setCmDosageValueMapping(CmDosageValueMapping cmDosageValueMapping) {
        this.cmDosageValueMapping = cmDosageValueMapping;
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

    public String getDosageQuantity() {
        return dosageQuantity;
    }

    public void setDosageQuantity(String dosageQuantity) {
        this.dosageQuantity = dosageQuantity;
    }

    public String getDosageTime() {
        return dosageTime;
    }

    public void setDosageTime(String dosageTime) {
        this.dosageTime = dosageTime;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public LookupValue getMedicineGroupType() {
        return medicineGroupType;
    }

    public void setMedicineGroupType(LookupValue medicineGroupType) {
        this.medicineGroupType = medicineGroupType;
    }

    public List<CmTreatmentGroupMedicineDetail> getCmTreatmentGroupMedicineList() {
        return cmTreatmentGroupMedicineList;
    }

    public void setCmTreatmentGroupMedicineList(List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineList) {
        this.cmTreatmentGroupMedicineList = cmTreatmentGroupMedicineList;
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
