package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasterbyid;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto.RoomConfigurationMasterByIdDTO;

public class GetRoomConfigurationMasterByIdServiceResponse extends CommonServiceResponse {

    private RoomConfigurationMasterByIdDTO roomConfigurationMasterByIdDTO;

    public GetRoomConfigurationMasterByIdServiceResponse() {
    }

    public GetRoomConfigurationMasterByIdServiceResponse(RoomConfigurationMasterByIdDTO roomConfigurationMasterByIdDTO) {
        this.roomConfigurationMasterByIdDTO = roomConfigurationMasterByIdDTO;
    }

    public RoomConfigurationMasterByIdDTO getRoomConfigurationMasterByIdDTO() {

        return roomConfigurationMasterByIdDTO;
    }

    public void setRoomConfigurationMasterByIdDTO(RoomConfigurationMasterByIdDTO roomConfigurationMasterByIdDTO) {
        this.roomConfigurationMasterByIdDTO = roomConfigurationMasterByIdDTO;
    }
}
