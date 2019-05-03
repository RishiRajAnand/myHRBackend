package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyscheduledetailbytherapyid;

/*
* created by Raushan on 29-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyScheduleDetailDTO;


public class GetTherapyScheduleDetailByTherapyIdServiceResponse extends CommonServiceResponse {

    private TherapyScheduleDetailDTO therapyScheduleDetailDTO;

    //constructor

    public GetTherapyScheduleDetailByTherapyIdServiceResponse() {
    }

    public GetTherapyScheduleDetailByTherapyIdServiceResponse(TherapyScheduleDetailDTO therapyScheduleDetailDTO) {
        this.therapyScheduleDetailDTO = therapyScheduleDetailDTO;
    }

    //getters and setters


    public TherapyScheduleDetailDTO getTherapyScheduleDetailDTO() {
        return therapyScheduleDetailDTO;
    }

    public void setTherapyScheduleDetailDTO(TherapyScheduleDetailDTO therapyScheduleDetailDTO) {
        this.therapyScheduleDetailDTO = therapyScheduleDetailDTO;
    }
}
