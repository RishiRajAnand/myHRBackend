package com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto;

/*
* created by Brighty on 16-11-2017
* */

public class RoomConfigurationMasterDTO {

    private Long id;

    private Long clinicLocationId;

    private String roomCode;

    private String roomName;

    private String roomNumber;

    private Long bedTypeMasterId;

    private Long wardMasterId;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
