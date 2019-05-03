package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbyid;

/*
* created by Brighty on 17-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetBedConfigurationMasterByIdServiceRequest implements CommonServiceRequest {

    private Long bedConfigurationMasterId;

    public GetBedConfigurationMasterByIdServiceRequest(Long bedConfigurationMasterId) {
        this.bedConfigurationMasterId = bedConfigurationMasterId;
    }

    public Long getBedConfigurationMasterId() {

        return bedConfigurationMasterId;
    }

    public void setBedConfigurationMasterId(Long bedConfigurationMasterId) {
        this.bedConfigurationMasterId = bedConfigurationMasterId;
    }
}
