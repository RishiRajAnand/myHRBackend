package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.Clinic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "group_medicine_master")
public class CmGroupMedicineMaster extends BaseModel{

    @ManyToOne(fetch= FetchType.LAZY, optional = true)
    @JoinColumn(name = "clinic_id")
    @JsonIgnore
    private Clinic clinic;

    @Column(name = "medicine_type_master_id")
    private Long medicineTypeId;

    @Column(name = "manufacturer_id")
    private Long manufacturerId;

    @Column(name = "uom_master_id", nullable = true)
    private Long uomMasterId;

    @Column(name = "medicine_name")
    private String medicineName;

    @ManyToOne
    @JoinColumn(name = "cm_dosage_value_mapping_id", nullable = true)
    private CmDosageValueMapping cmDosageValueMapping;

    @Column(name = "dosage_time", nullable = true)
    private String dosageTime;

    @Column(name = "strength", nullable = true)
    private String strength;

    @Column(name = "instruction", nullable = true)
    private String instruction;

    @Column(name = "num_days", nullable = true)
    private Integer numberOfDays;

    @Column(name = "total_quantity", nullable = true)
    private String totalQuantity;


    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }


    public Long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }


    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }


    public Long getUomMasterId() {
        return uomMasterId;
    }

    public void setUomMasterId(Long uomMasterId) {
        this.uomMasterId = uomMasterId;
    }


    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }


    public CmDosageValueMapping getCmDosageValueMapping() {
        return cmDosageValueMapping;
    }

    public void setCmDosageValueMapping(CmDosageValueMapping cmDosageValueMapping) {
        this.cmDosageValueMapping = cmDosageValueMapping;
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

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
