package com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker;

/*
* Created by latha on 06/12/18.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.DischargeRequestDTO;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionRequestDTO;

import java.util.List;

public class DischargeRequestTrackerResponse extends CommonServiceResponse {

    private List<DischargeRequestDTO> dischargeRequestDTOs;

    //Constructor

    public DischargeRequestTrackerResponse(List<DischargeRequestDTO> dischargeRequestDTOs) {
        this.dischargeRequestDTOs = dischargeRequestDTOs;
    }

    public DischargeRequestTrackerResponse() {
    }

    //Getters and setters

    public List<DischargeRequestDTO> getDischargeRequestDTOs() {
        return dischargeRequestDTOs;
    }

    public void setDischargeRequestDTOs(List<DischargeRequestDTO> dischargeRequestDTOs) {
        this.dischargeRequestDTOs = dischargeRequestDTOs;
    }
}
