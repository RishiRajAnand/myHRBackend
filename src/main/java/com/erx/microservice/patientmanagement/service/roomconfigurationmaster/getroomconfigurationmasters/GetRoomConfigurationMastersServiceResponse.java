package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasters;

/*
* created by Brighty on 16-11-2017
* */



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto.RoomConfigurationMasterByClinicLocationDTO;

import java.util.List;

public class GetRoomConfigurationMastersServiceResponse extends CommonServiceResponse {

    private List<RoomConfigurationMasterByClinicLocationDTO> roomConfigurationMasterByClinicLocationDTOS;

    public GetRoomConfigurationMastersServiceResponse() {
    }

    public GetRoomConfigurationMastersServiceResponse(List<RoomConfigurationMasterByClinicLocationDTO> roomConfigurationMasterByClinicLocationDTOS) {
        this.roomConfigurationMasterByClinicLocationDTOS = roomConfigurationMasterByClinicLocationDTOS;
    }

    public List<RoomConfigurationMasterByClinicLocationDTO> getRoomConfigurationMasterByClinicLocationDTOS() {

        return roomConfigurationMasterByClinicLocationDTOS;
    }

    public void setRoomConfigurationMasterByClinicLocationDTOS(List<RoomConfigurationMasterByClinicLocationDTO> roomConfigurationMasterByClinicLocationDTOS) {
        this.roomConfigurationMasterByClinicLocationDTOS = roomConfigurationMasterByClinicLocationDTOS;
    }
}
