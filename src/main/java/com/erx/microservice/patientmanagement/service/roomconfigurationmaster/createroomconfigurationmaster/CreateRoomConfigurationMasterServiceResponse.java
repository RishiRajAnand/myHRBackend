package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.createroomconfigurationmaster;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class CreateRoomConfigurationMasterServiceResponse extends CommonServiceResponse {

    private long roomConfigurationMasterId;

    private String roomNumber;

    private String roomName;

    public CreateRoomConfigurationMasterServiceResponse() {
    }

    public CreateRoomConfigurationMasterServiceResponse(long roomConfigurationMasterId, String roomNumber, String roomName) {
        roomConfigurationMasterId = roomConfigurationMasterId;
        this.roomNumber = roomNumber;
        this.roomName =  roomName;
    }

    public long getRoomConfigurationMasterId() {

        return roomConfigurationMasterId;
    }

    public void setRoomConfigurationMasterId(long roomConfigurationMasterId) {
        roomConfigurationMasterId = roomConfigurationMasterId;
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
}
