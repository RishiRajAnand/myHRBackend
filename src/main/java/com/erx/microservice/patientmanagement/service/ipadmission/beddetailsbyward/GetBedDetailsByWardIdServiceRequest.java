package com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward;

/*
* created by Brighty on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;

public class GetBedDetailsByWardIdServiceRequest implements CommonServiceRequest {

    private Long wardId;

    private boolean isDaycare;

    private String type;//GENERAL,DOCTORBEDS

    private Long doctorId;

    private Long clinicLocationId;

    //Constructor
    public GetBedDetailsByWardIdServiceRequest(Long wardId, boolean isDaycare, String type, Long doctorId, Long clinicLocationId) {
        this.wardId = wardId;
        this.isDaycare = isDaycare;
        this.type = type;
        this.doctorId = doctorId;
        this.clinicLocationId = clinicLocationId;
    }

    //Getters and setters
    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public boolean isDaycare() {
        return isDaycare;
    }

    public void setDaycare(boolean daycare) {
        isDaycare = daycare;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
