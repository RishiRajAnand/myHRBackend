package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.deletebedconfigmaster;

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteBedConfigMasterServiceRequest implements CommonServiceRequest {

    private Long bedConfigMasterId;

    public DeleteBedConfigMasterServiceRequest(Long bedConfigMasterId) {
        this.bedConfigMasterId = bedConfigMasterId;
    }

    public Long getBedConfigMasterId() {
        return bedConfigMasterId;
    }

    public void setBedConfigMasterId(Long bedConfigMasterId) {
        this.bedConfigMasterId = bedConfigMasterId;
    }
}
