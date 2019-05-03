package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.createbedconfigurationmaster;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class CreateBedConfigurationMasterServiceResponse extends CommonServiceResponse {

    private Long bedConfigurationMasterId;

    private String bedCode;

    public CreateBedConfigurationMasterServiceResponse(Long bedConfigurationMasterId, String bedCode) {
        this.bedConfigurationMasterId = bedConfigurationMasterId;
        this.bedCode = bedCode;
    }

    public CreateBedConfigurationMasterServiceResponse() {
    }

    public Long getBedConfigurationMasterId() {
        return bedConfigurationMasterId;
    }

    public void setBedConfigurationMasterId(Long bedConfigurationMasterId) {
        this.bedConfigurationMasterId = bedConfigurationMasterId;
    }

    public String getBedCode() {

        return bedCode;
    }

    public void setBedCode(String bedCode) {
        this.bedCode = bedCode;
    }
}
