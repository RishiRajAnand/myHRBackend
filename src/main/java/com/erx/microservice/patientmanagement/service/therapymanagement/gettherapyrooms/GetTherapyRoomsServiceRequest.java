package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyrooms;

/*
* created by Latha on 07-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;


public class GetTherapyRoomsServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;

    //constructor

    public GetTherapyRoomsServiceRequest(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public GetTherapyRoomsServiceRequest() {
    }

    //getters and setters

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
