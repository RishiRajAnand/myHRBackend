package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 27-08-2018
* */

import java.util.List;

public class CmIndividualMedicineTreatmentDTO {

    private Long bmOrderId;
    private long cmTreatmentMedicineId;
    private Long productCatalogueCommonDetailId;
    private String medicineName;
    private long numberOfDays;
    private String dosageName;
    private List<DosageTimeDTO> dosageTimeDTOs;
    private String strength;
    private String instruction;
    private Long uomId;
    private String uomName;
    private Long medicineGroupTypeLookupId;
    private Long cmDosageValueMappingId;
    private String dosageQuantity;
    private String totalQuantity;

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public long getCmTreatmentMedicineId() {
        return cmTreatmentMedicineId;
    }

    public void setCmTreatmentMedicineId(long cmTreatmentMedicineId) {
        this.cmTreatmentMedicineId = cmTreatmentMedicineId;
    }

    public Long getProductCatalogueCommonDetailId() {
        return productCatalogueCommonDetailId;
    }

    public void setProductCatalogueCommonDetailId(Long productCatalogueCommonDetailId) {
        this.productCatalogueCommonDetailId = productCatalogueCommonDetailId;
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

    public String getDosageName() {
        return dosageName;
    }

    public void setDosageName(String dosageName) {
        this.dosageName = dosageName;
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

    public Long getUomId() {
        return uomId;
    }

    public void setUomId(Long uomId) {
        this.uomId = uomId;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
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
