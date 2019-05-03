package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasters;

/*
* created by Brighty on 20-11-2017
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetBedConfigurationMastersServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    public GetBedConfigurationMastersServiceRequest(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
