package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.deleteroomconfigmaster;


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteRoomConfigMasterServiceRequest implements CommonServiceRequest {

    private Long roomConfigMasterId;

    public DeleteRoomConfigMasterServiceRequest(Long roomConfigMasterId) {
        this.roomConfigMasterId = roomConfigMasterId;
    }

    public Long getRoomConfigMasterId() {
        return roomConfigMasterId;
    }

    public void setRoomConfigMasterId(Long roomConfigMasterId) {
        this.roomConfigMasterId = roomConfigMasterId;
    }
}
