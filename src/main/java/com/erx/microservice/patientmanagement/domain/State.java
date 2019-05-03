package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.Valid;

/**
 * Created by Latha on 27/10/17.
 */
@Entity
@Table(name = "state")
public class State extends BaseModel {

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    @Valid
    private Country country;
    @Column(name = "code", nullable = true)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = true)
    private String description;


    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
