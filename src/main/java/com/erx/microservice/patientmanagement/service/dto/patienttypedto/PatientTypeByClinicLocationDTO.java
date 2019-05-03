package com.erx.microservice.patientmanagement.service.dto.patienttypedto;

/*
* created by Brighty on 09-11-2017
* */

public class PatientTypeByClinicLocationDTO {

    private Long id;

    private String patientTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientTypeName() {
        return patientTypeName;
    }

    public void setPatientTypeName(String patientTypeName) {
        this.patientTypeName = patientTypeName;
    }
}