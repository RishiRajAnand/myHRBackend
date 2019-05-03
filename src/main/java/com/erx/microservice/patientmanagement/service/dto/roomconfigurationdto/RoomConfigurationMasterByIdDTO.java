package com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto;

/*
* created by Brighty on 17-11-2017
* */

public class RoomConfigurationMasterByIdDTO {

    private Long id;

    private Long clinicLocationId;

    private String roomCode;

    private String roomNumber;

    private String roomName;

    private Long bedTypeMasterId;

    private String bedTypeMasterName;

    private Long wardMasterId;

    private String wardMasterName;

    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public long getBedTypeMasterId() {
        return bedTypeMasterId;
    }

    public void setBedTypeMasterId(long bedTypeMasterId) {
        this.bedTypeMasterId = bedTypeMasterId;
    }

    public Long getWardMasterId() {
        return wardMasterId;
    }

    public void setWardMasterId(Long wardMasterId) {
        this.wardMasterId = wardMasterId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBedTypeMasterName() {
        return bedTypeMasterName;
    }

    public void setBedTypeMasterName(String bedTypeMasterName) {
        this.bedTypeMasterName = bedTypeMasterName;
    }

    public String getWardMasterName() {
        return wardMasterName;
    }

    public void setWardMasterName(String wardMasterName) {
        this.wardMasterName = wardMasterName;
    }
}
