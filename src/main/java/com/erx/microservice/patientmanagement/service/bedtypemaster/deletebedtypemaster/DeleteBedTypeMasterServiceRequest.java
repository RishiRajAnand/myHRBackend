package com.erx.microservice.patientmanagement.service.bedtypemaster.deletebedtypemaster;

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteBedTypeMasterServiceRequest implements CommonServiceRequest {

    private Long bedTypeId;

    public DeleteBedTypeMasterServiceRequest(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public Long getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }
}
