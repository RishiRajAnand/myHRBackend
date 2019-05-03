package com.erx.microservice.patientmanagement.service.ipadmission.getbedttpeidforschedulerbyipadmissionid;

/*
* created by Raushan on 05-10-2018.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionBedTransferDTO;

import java.util.List;

public class GetBedTypeIdForSchedulerByIpAdmissionIdServiceResponse extends CommonServiceResponse {

    private Long bedTypeId;
    //constructor


    public GetBedTypeIdForSchedulerByIpAdmissionIdServiceResponse(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }

    public GetBedTypeIdForSchedulerByIpAdmissionIdServiceResponse() {
    }

// getters and setters


    public Long getBedTypeId() {
        return bedTypeId;
    }

    public void setBedTypeId(Long bedTypeId) {
        this.bedTypeId = bedTypeId;
    }
}
