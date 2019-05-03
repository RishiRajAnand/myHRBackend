package com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward;

/*
* created by Brighty on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.BedConfigurationDTO;

import java.util.List;

public class GetBedDetailsByWardIdServiceResponse extends CommonServiceResponse {

    private List<BedConfigurationDTO> bedConfigurationDTOs;

    //Constructor
    public GetBedDetailsByWardIdServiceResponse(List<BedConfigurationDTO> bedConfigurationDTOs) {
        this.bedConfigurationDTOs = bedConfigurationDTOs;
    }

    public GetBedDetailsByWardIdServiceResponse() {
    }

    //Getters and setters
    public List<BedConfigurationDTO> getBedConfigurationDTOs() {
        return bedConfigurationDTOs;
    }

    public void setBedConfigurationDTOs(List<BedConfigurationDTO> bedConfigurationDTOs) {
        this.bedConfigurationDTOs = bedConfigurationDTOs;
    }
}
