package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 27-08-2018
* */

import java.util.List;

public class CmTemplateMedicineDTO {

    private long cmTemplateMedicineId;
    private Long productCatalogueCommonDetailId;
    private String medicineName;
    private long numberOfDays;
    private String dosage;
    private List<DosageTimeDTO> dosageTimeDTOs;
    private String strength;
    private String instruction;
    private long uomId;
    private String uomName;
    private Long medicineGroupTypeLookupId;
    private Long cmDosageValueMappingId;
    private String dosageQuantity;
    private String totalQuantity;

    public long getCmTemplateMedicineId() {
        return cmTemplateMedicineId;
    }

    public void setCmTemplateMedicineId(long cmTemplateMedicineId) {
        this.cmTemplateMedicineId = cmTemplateMedicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public List<DosageTimeDTO> getDosageTimeDTOs() {
        return dosageTimeDTOs;
    }

    public void setDosageTimeDTOs(List<DosageTimeDTO> dosageTimeDTOs) {
        this.dosageTimeDTOs = dosageTimeDTOs;
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

    public long getUomId() {
        return uomId;
    }

    public void setUomId(long uomId) {
        this.uomId = uomId;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public Long getProductCatalogueCommonDetailId() {
        return productCatalogueCommonDetailId;
    }

    public void setProductCatalogueCommonDetailId(Long productCatalogueCommonDetailId) {
        this.productCatalogueCommonDetailId = productCatalogueCommonDetailId;
    }

    public Long getMedicineGroupTypeLookupId() {
        return medicineGroupTypeLookupId;
    }

    public void setMedicineGroupTypeLookupId(Long medicineGroupTypeLookupId) {
        this.medicineGroupTypeLookupId = medicineGroupTypeLookupId;
    }

    public Long getCmDosageValueMappingId() {
        return cmDosageValueMappingId;
    }

    public void setCmDosageValueMappingId(Long cmDosageValueMappingId) {
        this.cmDosageValueMappingId = cmDosageValueMappingId;
    }

    public String getDosageQuantity() {
        return dosageQuantity;
    }

    public void setDosageQuantity(String dosageQuantity) {
        this.dosageQuantity = dosageQuantity;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
