package com.erx.microservice.patientmanagement.service.dto;

/**
 * Created by brighty on 09-11-2017.
 */

public class ClinicLocationDTO {

    private Long id;

    private String clinicLocationName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClinicLocationName() {
        return clinicLocationName;
    }

    public void setClinicLocationName(String clinicLocationName) {
        this.clinicLocationName = clinicLocationName;
    }
}
