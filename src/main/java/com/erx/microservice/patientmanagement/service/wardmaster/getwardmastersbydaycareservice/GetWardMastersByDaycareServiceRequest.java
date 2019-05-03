package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydaycareservice;
/*
* created by Brighty on 10-01-18
* */


import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetWardMastersByDaycareServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    private boolean isDaycare;

    //Getters and setters

    public GetWardMastersByDaycareServiceRequest(Long clinicLocationId, boolean isDaycare) {
        this.clinicLocationId = clinicLocationId;
        this.isDaycare = isDaycare;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public boolean isDaycare() {
        return isDaycare;
    }

    //constructor

    public void setDaycare(boolean daycare) {
        isDaycare = daycare;
    }
}
