package com.erx.microservice.patientmanagement.service.wardmaster.deletewardmaster;


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class DeleteWardMasterServiceRequest implements CommonServiceRequest {

    private Long wardMasterId;

    public DeleteWardMasterServiceRequest(Long wardMasterId) {
        this.wardMasterId = wardMasterId;
    }

    public Long getWardMasterId() {
        return wardMasterId;
    }

    public void setWardMasterId(Long wardMasterId) {
        this.wardMasterId = wardMasterId;
    }
}
