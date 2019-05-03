package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.LookupValue;

import javax.persistence.*;


@Entity
@Table(name = "cm_template_medicine_mapping")
public class CmTemplateMedicine extends BaseModel{

    @ManyToOne
    @JoinColumn(name="cm_medicine_template_id")
    private CmTemplate cmTemplate;

    @Column(name = "pm_prod_catalogue_common_details", nullable=true)
    private Long productCatalogueCommonDetailId;

    @Column(name = "num_days")
    private int numberOfDays;

    @ManyToOne
    @JoinColumn(name = "cm_dosage_value_mapping_id", nullable = true)
    private CmDosageValueMapping cmDosageValueMapping;

    @Column(name = "uom_master_id")
    private Long uomMasterId;

    @Column(name = "dosage_time")
    private String dosageTime;

    @Column(name = "strength")
    private String strength;

    @Column(name = "instruction")
    private String instruction;

    @Column(name = "description")
    private String description;

    @Column(name = "dosage_quantity", nullable = true)
    private String dosageQuantity;

    @OneToOne
    @JoinColumn(name = "medicine_group_type_lookup_id")
    private LookupValue medicineGroupType;

    @ManyToOne
    @JoinColumn(name = "route_of_administration_id", nullable = true)
    private RouteOfAdministration routeOfAdministration;

    @Column(name = "total_quantity", nullable = true)
    private String totalQuantity;


    public CmTemplate getCmTemplate() {
        return cmTemplate;
    }

    public void setCmTemplate(CmTemplate cmTemplate) {
        this.cmTemplate = cmTemplate;
    }

    public Long getProductCatalogueCommonDetailId() {
        return productCatalogueCommonDetailId;
    }

    public void setProductCatalogueCommonDetailId(Long productCatalogueCommonDetailId) {
        this.productCatalogueCommonDetailId = productCatalogueCommonDetailId;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public CmDosageValueMapping getCmDosageValueMapping() {
        return cmDosageValueMapping;
    }

    public void setCmDosageValueMapping(CmDosageValueMapping cmDosageValueMapping) {
        this.cmDosageValueMapping = cmDosageValueMapping;
    }

    public String getDosageTime() {
        return dosageTime;
    }

    public void setDosageTime(String dosageTime) {
        this.dosageTime = dosageTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LookupValue getMedicineGroupType() {
        return medicineGroupType;
    }

    public void setMedicineGroupType(LookupValue medicineGroupType) {
        this.medicineGroupType = medicineGroupType;
    }

    public RouteOfAdministration getRouteOfAdministration() {
        return routeOfAdministration;
    }

    public void setRouteOfAdministration(RouteOfAdministration routeOfAdministration) {
        this.routeOfAdministration = routeOfAdministration;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
