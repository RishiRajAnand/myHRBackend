package com.erx.microservice.patientmanagement.service.ipadmission.allocatebed;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionDTO;

public class AllocateBedServiceRequest implements CommonServiceRequest {

    private IpAdmissionDTO ipAdmissionDTO;

    // constructor
    public AllocateBedServiceRequest(IpAdmissionDTO ipAdmissionDTO) {
        this.ipAdmissionDTO = ipAdmissionDTO;
    }

    //getters and setters
    public IpAdmissionDTO getIpAdmissionDTO() {
        return ipAdmissionDTO;
    }

    public void setIpAdmissionDTO(IpAdmissionDTO ipAdmissionDTO) {
        this.ipAdmissionDTO = ipAdmissionDTO;
    }
}
