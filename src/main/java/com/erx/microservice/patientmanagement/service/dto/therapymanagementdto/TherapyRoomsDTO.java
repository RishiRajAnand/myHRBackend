package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 07-09-2018
* */

public class TherapyRoomsDTO {

    private Long therapyRoomDetailId;
    private String therapyRoomName;

    public Long getTherapyRoomDetailId() {
        return therapyRoomDetailId;
    }

    public void setTherapyRoomDetailId(Long therapyRoomDetailId) {
        this.therapyRoomDetailId = therapyRoomDetailId;
    }

    public String getTherapyRoomName() {
        return therapyRoomName;
    }

    public void setTherapyRoomName(String therapyRoomName) {
        this.therapyRoomName = therapyRoomName;
    }
}
