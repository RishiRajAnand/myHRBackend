package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasterbyid;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetRoomConfigurationMasterByIdServiceRequest implements CommonServiceRequest {

    private Long roomConfigurationMasterId;

    public GetRoomConfigurationMasterByIdServiceRequest(Long roomConfigurationMasterId) {
        this.roomConfigurationMasterId = roomConfigurationMasterId;
    }

    public Long getRoomConfigurationMasterId() {

        return roomConfigurationMasterId;
    }

    public void setRoomConfigurationMasterId(Long roomConfigurationMasterId) {
        this.roomConfigurationMasterId = roomConfigurationMasterId;
    }
}
