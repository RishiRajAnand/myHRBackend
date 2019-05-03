package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 27-03-2018
* */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "uh_identifier")
public class UhIdentifier extends BaseModel {

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "availability_status", nullable = true)
    private Integer availabilityStatus;

    //getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(Integer availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
}
