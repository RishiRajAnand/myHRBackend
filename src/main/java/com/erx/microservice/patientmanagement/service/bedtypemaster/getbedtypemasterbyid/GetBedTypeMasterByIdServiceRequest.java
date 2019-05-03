package com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasterbyid;

/*
* created by Brighty on 17-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetBedTypeMasterByIdServiceRequest implements CommonServiceRequest {

    private Long bedTypeMasterId;

    public GetBedTypeMasterByIdServiceRequest(Long bedTypeMasterId) {
        this.bedTypeMasterId = bedTypeMasterId;
    }

    public Long getBedTypeMasterId() {

        return bedTypeMasterId;
    }

    public void setBedTypeMasterId(Long bedTypeMasterId) {
        this.bedTypeMasterId = bedTypeMasterId;
    }
}
