package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbyid;

/*
* created by Brighty on 17-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto.BedConfigurationMasterByIdDTO;


public class GetBedConfigurationMasterByIdServiceResponse extends CommonServiceResponse {

    private BedConfigurationMasterByIdDTO bedConfigurationMasterByIdDTO;

    public GetBedConfigurationMasterByIdServiceResponse(BedConfigurationMasterByIdDTO bedConfigurationMasterByIdDTO) {
        this.bedConfigurationMasterByIdDTO = bedConfigurationMasterByIdDTO;
    }

    public GetBedConfigurationMasterByIdServiceResponse() {
    }

    public BedConfigurationMasterByIdDTO getBedConfigurationMasterByIdDTO() {

        return bedConfigurationMasterByIdDTO;
    }

    public void setBedConfigurationMasterByIdDTO(BedConfigurationMasterByIdDTO bedConfigurationMasterByIdDTO) {
        this.bedConfigurationMasterByIdDTO = bedConfigurationMasterByIdDTO;
    }
}
