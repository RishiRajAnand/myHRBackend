package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 24-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "route_of_administration")
public class RouteOfAdministration extends BaseModel{

    @Column(name="name", nullable = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
