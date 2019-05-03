package com.erx.microservice.patientmanagement.service.wardmaster.createwardmaster;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class CreateWardMasterServiceResponse extends CommonServiceResponse {

    private long wardMasterId;

    private String wardCode;

    public CreateWardMasterServiceResponse() {
    }

    public CreateWardMasterServiceResponse(long wardMasterId, String wardCode) {
        this.wardMasterId = wardMasterId;
        this.wardCode = wardCode;
    }

    public long getWardMasterId() {

        return wardMasterId;
    }

    public void setWardMasterId(long wardMasterId) {
        this.wardMasterId = wardMasterId;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }
}
