package com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto;

public class RoomConfigurationMasterByClinicLocationDTO {

    private  Long id;

    private String roomNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
