package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 07-12-17
* */

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address_info")
@Embeddable
public class AddressInfo extends BaseModel implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pincode_id", nullable = true)
    @JsonIgnore
    @Embedded
    private Pincode pincode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", nullable = true)
    @JsonIgnore
    @Embedded
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id", nullable = true)
    @JsonIgnore
    @Embedded
    private State state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id", nullable = true)
    @JsonIgnore
    @Embedded
    private Country country;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    @Column(name = "contact_address", nullable = true)
    private String contactAddress;

    //getters and setters

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }
}
