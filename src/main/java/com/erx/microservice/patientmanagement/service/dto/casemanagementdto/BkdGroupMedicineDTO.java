package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 31-08-2018
* */

import java.util.List;

public class BkdGroupMedicineDTO {

    private Long cmGroupMedicineMasterId;
    private Long groupMedicineTypeId;
    private String groupMedicineTypeName;
    private String groupMedicineName;
    private String dosageQuantity;
    private Long uomMasterId;
    private String uomMasterName;
    private Integer numberOfDays;
    private String instructions;
    private Long cmDosageValueMappingId;
    private List<DosageTimeDTO> dosageTimeDTOs;
    private List<BKDGroupResponseDTO> bkdGroupResponseDTOs;
    private String totalQuantity;

    public Long getCmGroupMedicineMasterId() {
        return cmGroupMedicineMasterId;
    }

    public void setCmGroupMedicineMasterId(Long cmGroupMedicineMasterId) {
        this.cmGroupMedicineMasterId = cmGroupMedicineMasterId;
    }

    public Long getGroupMedicineTypeId() {
        return groupMedicineTypeId;
    }

    public void setGroupMedicineTypeId(Long groupMedicineTypeId) {
        this.groupMedicineTypeId = groupMedicineTypeId;
    }

    public String getGroupMedicineTypeName() {
        return groupMedicineTypeName;
    }

    public void setGroupMedicineTypeName(String groupMedicineTypeName) {
        this.groupMedicineTypeName = groupMedicineTypeName;
    }

    public String getGroupMedicineName() {
        return groupMedicineName;
    }

    public void setGroupMedicineName(String groupMedicineName) {
        this.groupMedicineName = groupMedicineName;
    }

    public String getDosageQuantity() {
        return dosageQuantity;
    }

    public void setDosageQuantity(String dosageQuantity) {
        this.dosageQuantity = dosageQuantity;
    }

    public Long getUomMasterId() {
        return uomMasterId;
    }

    public void setUomMasterId(Long uomMasterId) {
        this.uomMasterId = uomMasterId;
    }

    public String getUomMasterName() {
        return uomMasterName;
    }

    public void setUomMasterName(String uomMasterName) {
        this.uomMasterName = uomMasterName;
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

    public List<DosageTimeDTO> getDosageTimeDTOs() {
        return dosageTimeDTOs;
    }

    public void setDosageTimeDTOs(List<DosageTimeDTO> dosageTimeDTOs) {
        this.dosageTimeDTOs = dosageTimeDTOs;
    }

    public List<BKDGroupResponseDTO> getBkdGroupResponseDTOs() {
        return bkdGroupResponseDTOs;
    }

    public void setBkdGroupResponseDTOs(List<BKDGroupResponseDTO> bkdGroupResponseDTOs) {
        this.bkdGroupResponseDTOs = bkdGroupResponseDTOs;
    }

    public Long getCmDosageValueMappingId() {
        return cmDosageValueMappingId;
    }

    public void setCmDosageValueMappingId(Long cmDosageValueMappingId) {
        this.cmDosageValueMappingId = cmDosageValueMappingId;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
