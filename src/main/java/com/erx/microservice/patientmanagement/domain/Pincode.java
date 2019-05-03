package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.Valid;

/**
 * Created by Latha on 27/10/17.
 */
@Entity
@Table(name = "pincode")
public class Pincode extends BaseModel {

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    @Valid
    private City city;

    @Column(name = "pin", nullable = false)
    private String pin;

    @Column(name = "description", nullable = true)
    private String description;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
