package com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasters;

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

/*
* created by Brighty on 16-11-2017
* */

public class GetBedTypeMastersServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    public GetBedTypeMastersServiceRequest(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getClinicLocationId() {

        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
