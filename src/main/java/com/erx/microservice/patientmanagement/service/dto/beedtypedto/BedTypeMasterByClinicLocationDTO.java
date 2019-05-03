package com.erx.microservice.patientmanagement.service.dto.beedtypedto;

/*
* created by Brighty on 16-11-2017
* */

public class BedTypeMasterByClinicLocationDTO {

    private Long id;

    private String bedTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }
}
