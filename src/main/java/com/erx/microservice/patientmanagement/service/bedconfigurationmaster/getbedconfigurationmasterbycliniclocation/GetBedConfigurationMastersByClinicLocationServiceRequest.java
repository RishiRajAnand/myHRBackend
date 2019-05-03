package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetBedConfigurationMastersByClinicLocationServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    public GetBedConfigurationMastersByClinicLocationServiceRequest(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getClinicLocationId() {

        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
