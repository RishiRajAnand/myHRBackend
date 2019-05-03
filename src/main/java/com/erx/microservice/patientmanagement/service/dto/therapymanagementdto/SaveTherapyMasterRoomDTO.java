package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 11-09-2018
* */

public class SaveTherapyMasterRoomDTO {

    private Long therapyMasterRoomDetailId;
    private Long roomDetailId;

    public Long getTherapyMasterRoomDetailId() {
        return therapyMasterRoomDetailId;
    }

    public void setTherapyMasterRoomDetailId(Long therapyMasterRoomDetailId) {
        this.therapyMasterRoomDetailId = therapyMasterRoomDetailId;
    }

    public Long getRoomDetailId() {
        return roomDetailId;
    }

    public void setRoomDetailId(Long roomDetailId) {
        this.roomDetailId = roomDetailId;
    }
}
