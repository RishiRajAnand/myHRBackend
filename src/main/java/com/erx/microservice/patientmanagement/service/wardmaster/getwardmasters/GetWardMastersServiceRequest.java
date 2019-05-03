package com.erx.microservice.patientmanagement.service.wardmaster.getwardmasters;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetWardMastersServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    public GetWardMastersServiceRequest(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getClinicLocationId() {

        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
