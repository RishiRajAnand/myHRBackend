package com.erx.microservice.patientmanagement.service.ipadmission.getbedmovementdetailsbyippatient;

/*
* created by Latha on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionBedMovedDTO;

import java.util.List;

public class GetBedMovementDetailsByIpServiceResponse extends CommonServiceResponse {

    private List<IpAdmissionBedMovedDTO> ipAdmissionBedMovedDTOs;
    //constructor

    public GetBedMovementDetailsByIpServiceResponse(List<IpAdmissionBedMovedDTO> ipAdmissionBedMovedDTOs) {
        this.ipAdmissionBedMovedDTOs = ipAdmissionBedMovedDTOs;
    }

    public GetBedMovementDetailsByIpServiceResponse() {
    }
    //getters and setters

    public List<IpAdmissionBedMovedDTO> getIpAdmissionBedMovedDTOs() {
        return ipAdmissionBedMovedDTOs;
    }

    public void setIpAdmissionBedMovedDTOs(List<IpAdmissionBedMovedDTO> ipAdmissionBedMovedDTOs) {
        this.ipAdmissionBedMovedDTOs = ipAdmissionBedMovedDTOs;
    }
}
