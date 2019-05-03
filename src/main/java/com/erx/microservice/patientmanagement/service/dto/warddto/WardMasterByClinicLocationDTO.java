package com.erx.microservice.patientmanagement.service.dto.warddto;

/*
* created by Brighty on 16-11-2017
* */

public class WardMasterByClinicLocationDTO {

    private Long id;

    private String wardName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}
