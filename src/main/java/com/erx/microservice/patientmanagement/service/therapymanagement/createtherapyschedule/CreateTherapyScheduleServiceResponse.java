package com.erx.microservice.patientmanagement.service.therapymanagement.createtherapyschedule;



/*
* created by Raushan on 27-11-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeDTO;

public class CreateTherapyScheduleServiceResponse extends CommonServiceResponse {
    private String status;
    //Constructor

    public CreateTherapyScheduleServiceResponse() {
    }

    public CreateTherapyScheduleServiceResponse(String status) {
        this.status = status;
    }
    //getter and setter

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
