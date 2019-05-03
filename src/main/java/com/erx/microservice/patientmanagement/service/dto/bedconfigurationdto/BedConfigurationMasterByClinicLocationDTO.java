package com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto;

/*
* created by Brighty on 20-11-2017
* */

public class BedConfigurationMasterByClinicLocationDTO {

    private Long id;

    private String bedNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }
}
