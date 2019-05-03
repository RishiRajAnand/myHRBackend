package com.erx.microservice.patientmanagement.service.wardmaster.getwardmasterbyid;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetWardMasterByIdServiceRequest implements CommonServiceRequest {

    private Long wardMasterId;

    public GetWardMasterByIdServiceRequest(Long wardMasterId) {
        this.wardMasterId = wardMasterId;
    }

    public Long getWardMasterId() {

        return wardMasterId;
    }

    public void setWardMasterId(Long wardMasterId) {
        this.wardMasterId = wardMasterId;
    }
}
