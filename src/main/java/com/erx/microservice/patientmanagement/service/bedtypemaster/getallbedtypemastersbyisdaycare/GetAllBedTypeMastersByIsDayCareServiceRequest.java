package com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbyisdaycare;

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetAllBedTypeMastersByIsDayCareServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    private boolean isDayCare;

    public GetAllBedTypeMastersByIsDayCareServiceRequest(Long clinicLocationId, boolean isDayCare) {
        this.clinicLocationId = clinicLocationId;
        this.isDayCare = isDayCare;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public boolean isDayCare() {
        return isDayCare;
    }

    public void setDayCare(boolean dayCare) {
        isDayCare = dayCare;
    }
}
