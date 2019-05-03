package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 25-08-2018
* */

import java.util.List;

public class SaveCaseMedicineDTO {

    private Long bmOrderId;
    private Long cmTreatmentMedicineDetailId;
    private Long routeOfAdministrationId;
    private Long cmDosageValueMappingId;
    private Long productCatalogueCommonDetailId;
    private Long productStatus; // 0- Not Changed, 1- Added, 2- Updated, 3- Deleted
    private Long uomMasterId;
    private String dosageQuantity;
    private String totalQuantity;
    private List<DosageTimeDTO> dosageTimeDTOs;
    private Integer numberOfDays;
    private String instructions;
    private Long medicineGroupTypeId;
    private String medicineGroupTypeName;
    private List<GroupIndividualMedicineDTO> groupIndividualMedicineDTOs;

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public Long getCmTreatmentMedicineDetailId() {
        return cmTreatmentMedicineDetailId;
    }

    public void setCmTreatmentMedicineDetailId(Long cmTreatmentMedicineDetailId) {
        this.cmTreatmentMedicineDetailId = cmTreatmentMedicineDetailId;
    }

    public Long getRouteOfAdministrationId() {
        return routeOfAdministrationId;
    }

    public void setRouteOfAdministrationId(Long routeOfAdministrationId) {
        this.routeOfAdministrationId = routeOfAdministrationId;
    }

    public Long getCmDosageValueMappingId() {
        return cmDosageValueMappingId;
    }

    public void setCmDosageValueMappingId(Long cmDosageValueMappingId) {
        this.cmDosageValueMappingId = cmDosageValueMappingId;
    }

    public Long getProductCatalogueCommonDetailId() {
        return productCatalogueCommonDetailId;
    }

    public void setProductCatalogueCommonDetailId(Long productCatalogueCommonDetailId) {
        this.productCatalogueCommonDetailId = productCatalogueCommonDetailId;
    }

    public Long getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Long productStatus) {
        this.productStatus = productStatus;
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

    public List<DosageTimeDTO> getDosageTimeDTOs() {
        return dosageTimeDTOs;
    }

    public void setDosageTimeDTOs(List<DosageTimeDTO> dosageTimeDTOs) {
        this.dosageTimeDTOs = dosageTimeDTOs;
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

    public Long getMedicineGroupTypeId() {
        return medicineGroupTypeId;
    }

    public void setMedicineGroupTypeId(Long medicineGroupTypeId) {
        this.medicineGroupTypeId = medicineGroupTypeId;
    }

    public String getMedicineGroupTypeName() {
        return medicineGroupTypeName;
    }

    public void setMedicineGroupTypeName(String medicineGroupTypeName) {
        this.medicineGroupTypeName = medicineGroupTypeName;
    }

    public List<GroupIndividualMedicineDTO> getGroupIndividualMedicineDTOs() {
        return groupIndividualMedicineDTOs;
    }

    public void setGroupIndividualMedicineDTOs(List<GroupIndividualMedicineDTO> groupIndividualMedicineDTOs) {
        this.groupIndividualMedicineDTOs = groupIndividualMedicineDTOs;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
