package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 26-08-2018
* */

import java.util.List;

public class SaveBKDGroupMedicineDTO {

    private Long clinicId;
    private Long cmGroupMedicineMasterId;
    private Long medicineTypeId;
    private Long uomMasterId;
    private String medicineName;
    private Long cmDosageValueMappingId;
    private String strength;
    private String instruction;
    private Integer numberOfDays;
    private List<DosageTimeDTO> dosageTimeDTOs;
    private List<BKDGroupDTO> bkdGroupDTOs;
    private String totalQuantity;


    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getCmGroupMedicineMasterId() {
        return cmGroupMedicineMasterId;
    }

    public void setCmGroupMedicineMasterId(Long cmGroupMedicineMasterId) {
        this.cmGroupMedicineMasterId = cmGroupMedicineMasterId;
    }

    public Long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
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

    public Long getCmDosageValueMappingId() {
        return cmDosageValueMappingId;
    }

    public void setCmDosageValueMappingId(Long cmDosageValueMappingId) {
        this.cmDosageValueMappingId = cmDosageValueMappingId;
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

    public List<DosageTimeDTO> getDosageTimeDTOs() {
        return dosageTimeDTOs;
    }

    public void setDosageTimeDTOs(List<DosageTimeDTO> dosageTimeDTOs) {
        this.dosageTimeDTOs = dosageTimeDTOs;
    }

    public List<BKDGroupDTO> getBkdGroupDTOs() {
        return bkdGroupDTOs;
    }

    public void setBkdGroupDTOs(List<BKDGroupDTO> bkdGroupDTOs) {
        this.bkdGroupDTOs = bkdGroupDTOs;
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
