package com.erx.microservice.patientmanagement.service.dto.warddto;

/*
* created by Brighty on 05-01-2017
* */

public class WardByDepartmentInputDTO {

    private Long departmentId;

    private Long clinicLocationId;

    //Getters and setters

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
