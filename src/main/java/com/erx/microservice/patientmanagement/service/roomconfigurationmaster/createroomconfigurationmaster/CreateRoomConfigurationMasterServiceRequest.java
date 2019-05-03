package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.createroomconfigurationmaster;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto.RoomConfigurationMasterDTO;

public class CreateRoomConfigurationMasterServiceRequest implements CommonServiceRequest {

    private RoomConfigurationMasterDTO roomConfigurationMasterDTO;

    private Long clinicId;

    //Constructor

    public CreateRoomConfigurationMasterServiceRequest(RoomConfigurationMasterDTO roomConfigurationMasterDTO,
                                                       Long clinicId) {
        this.roomConfigurationMasterDTO = roomConfigurationMasterDTO;
        this.clinicId = clinicId;
    }

    //Getters and setters

    public RoomConfigurationMasterDTO getRoomConfigurationMasterDTO() {
        return roomConfigurationMasterDTO;
    }

    public void setRoomConfigurationMasterDTO(RoomConfigurationMasterDTO roomConfigurationMasterDTO) {
        this.roomConfigurationMasterDTO = roomConfigurationMasterDTO;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
}
