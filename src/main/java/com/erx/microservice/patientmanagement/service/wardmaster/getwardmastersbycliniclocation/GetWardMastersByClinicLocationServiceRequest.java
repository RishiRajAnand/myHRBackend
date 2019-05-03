package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */



import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

import java.util.Optional;

public class GetWardMastersByClinicLocationServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    private Optional<Boolean> isActive;

    public GetWardMastersByClinicLocationServiceRequest(Long clinicLocationId, Optional<Boolean> isActive) {
        this.clinicLocationId = clinicLocationId;
        this.isActive = isActive;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Optional<Boolean> getIsActive() {
        return isActive;
    }

    public void setIsActive(Optional<Boolean> isActive) {
        this.isActive = isActive;
    }
}
