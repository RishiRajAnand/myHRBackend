package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmastersbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto.RoomConfigurationMasterByIdDTO;

import java.util.List;

public class GetRoomConfigurationMasterByClinicLocationServiceResponse extends CommonServiceResponse {

    private List<RoomConfigurationMasterByIdDTO> roomConfigurationMasterByIdDTOs;

    public GetRoomConfigurationMasterByClinicLocationServiceResponse() {
    }

    public GetRoomConfigurationMasterByClinicLocationServiceResponse(List<RoomConfigurationMasterByIdDTO> roomConfigurationMasterByIdDTOs) {
        this.roomConfigurationMasterByIdDTOs = roomConfigurationMasterByIdDTOs;
    }

    public List<RoomConfigurationMasterByIdDTO> getRoomConfigurationMasterByIdDTOs() {

        return roomConfigurationMasterByIdDTOs;
    }

    public void setRoomConfigurationMasterByIdDTOs(List<RoomConfigurationMasterByIdDTO> roomConfigurationMasterByIdDTOs) {
        this.roomConfigurationMasterByIdDTOs = roomConfigurationMasterByIdDTOs;
    }
}
