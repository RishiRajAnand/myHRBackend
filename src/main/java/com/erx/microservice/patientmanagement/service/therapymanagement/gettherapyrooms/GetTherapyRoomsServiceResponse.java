package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyrooms;

/*
* created by Latha on 07-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyRoomsDTO;

import java.util.List;

public class GetTherapyRoomsServiceResponse extends CommonServiceResponse {

   private List<TherapyRoomsDTO> therapyRoomsDTOs;

   //constructor


    public GetTherapyRoomsServiceResponse(List<TherapyRoomsDTO> therapyRoomsDTOs) {
        this.therapyRoomsDTOs = therapyRoomsDTOs;
    }

    public GetTherapyRoomsServiceResponse() {
    }

    //getters and setters

    public List<TherapyRoomsDTO> getTherapyRoomsDTOs() {
        return therapyRoomsDTOs;
    }

    public void setTherapyRoomsDTOs(List<TherapyRoomsDTO> therapyRoomsDTOs) {
        this.therapyRoomsDTOs = therapyRoomsDTOs;
    }
}
