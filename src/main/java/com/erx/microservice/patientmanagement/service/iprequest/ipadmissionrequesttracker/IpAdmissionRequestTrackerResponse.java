package com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker;

/*
* created by Brighty on 11-06-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionRequestDTO;

import java.util.List;

public class IpAdmissionRequestTrackerResponse extends CommonServiceResponse {

    private List<IpAdmissionRequestDTO> ipAdmissionRequestDTOs;

    //Constructor

    public IpAdmissionRequestTrackerResponse(List<IpAdmissionRequestDTO> ipAdmissionRequestDTOs) {
        this.ipAdmissionRequestDTOs = ipAdmissionRequestDTOs;
    }

    public IpAdmissionRequestTrackerResponse() {
    }

    //Getters and setters

    public List<IpAdmissionRequestDTO> getIpAdmissionRequestDTOs() {
        return ipAdmissionRequestDTOs;
    }

    public void setIpAdmissionRequestDTOs(List<IpAdmissionRequestDTO> ipAdmissionRequestDTOs) {
        this.ipAdmissionRequestDTOs = ipAdmissionRequestDTOs;
    }
}
