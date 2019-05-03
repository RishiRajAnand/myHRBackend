package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.Valid;

/**
 * Created by Latha on 27/10/17.
 */
@Entity
@Table(name = "location")
public class Location extends BaseModel {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "pincode_id")
    @Valid
    private Pincode pincode;
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    @Valid
    private City city;
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id")
    @Valid
    private State state;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Pincode getPincode() {
        return pincode;
    }

    public void setPincode(Pincode pincode) {
        this.pincode = pincode;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
