package com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto;

/*
* created by Brighty on 17-11-2017
* */

public class BedConfigurationMasterByIdDTO {

    private Long id;

    private String bedCode;

    private String bedNumber;

    private Long bedTypeMasterId;

    private String bedTypeName;

    private Long wardMasterId;

    private String wardName;

    private Long roomConfigurationMasterId;

    private String roomNumber;

    private boolean status;

    private String allocatedStatus;

    private Long clinicLocationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Long getBedTypeMasterId() {
        return bedTypeMasterId;
    }

    public void setBedTypeMasterId(Long bedTypeMasterId) {
        this.bedTypeMasterId = bedTypeMasterId;
    }

    public Long getWardMasterId() {
        return wardMasterId;
    }

    public void setWardMasterId(Long wardMasterId) {
        this.wardMasterId = wardMasterId;
    }

    public Long getRoomConfigurationMasterId() {
        return roomConfigurationMasterId;
    }

    public void setRoomConfigurationMasterId(Long roomConfigurationMasterId) {
        this.roomConfigurationMasterId = roomConfigurationMasterId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBedCode() {
        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAllocatedStatus() {
        return allocatedStatus;
    }

    public void setAllocatedStatus(String allocatedStatus) {
        this.allocatedStatus = allocatedStatus;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
