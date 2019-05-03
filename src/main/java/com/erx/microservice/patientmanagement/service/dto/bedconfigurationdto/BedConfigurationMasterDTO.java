package com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto;

/*
* created by Brighty on 16-11-2017
* */

public class BedConfigurationMasterDTO {

    private Long id;

    private long clinicLocationId;

    private String bedCode;

    private String bedNumber;

    private Long bedTypeMasterId;

    private Long wardMasterId;

    private Long roomConfigurationMasterId;

    private boolean status;

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

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
