package com.erx.microservice.patientmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;

/*
* created by Brighty on 09-11-2017
* */

@Entity
@Table(name = "clinic")
public class Clinic extends BaseModel {

    /*@Embedded
    @Valid
    private Address address;*/

    @Column(name = "name", length = 150, nullable = true)
    private String name;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @Valid
    @JsonIgnore
    private City city;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    @Valid
    @JsonIgnore
    private State state;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    @Valid
    @JsonIgnore
    private Country country;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pincode_id")
    @Valid
    @JsonIgnore
    private Pincode pincode;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    @Valid
    private Location location;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "administrator_id")
    @Valid
    @JsonIgnore
    private UserStaff administrator;
    @Column(nullable = false, name = "registered_at")
    private LocalDateTime registeredAt;
    @Column(name = "clinic_id", length = 4)
    private String clinicId;
    @Column(name = "logoformat", nullable = true)
    private String logoFormat;
    @Column(name = "registration_no", nullable = true)
    private String clinicRegistrationNo;
    @Column(name = "website_address", nullable = true)
    private String clinicWebsiteAddress;
    @Column(name = "sms_service_name", nullable = true)
    private String sms_service_name;
    @Column(length = 150, name = "address1")
    @NotBlank(message = "{address1.required}")
    private String address1;
    @Column(length = 150, name = "address2")
    private String address2;
    @OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "walkin_administrator_id")
    @JsonIgnore
    private UserStaff walkin_administrator;
    //  private ClinicType clinicType;

    @Column(name = "location_type")
    private String locationType;

    public Clinic() {
        this.registeredAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Pincode getPincode() {
        return pincode;
    }

    public void setPincode(Pincode pincode) {
        this.pincode = pincode;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /*public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }*/

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getLogoFormat() {
        return logoFormat;
    }

    public void setLogoFormat(String logoFormat) {
        this.logoFormat = logoFormat;
    }

    public String getClinicRegistrationNo() {
        return clinicRegistrationNo;
    }

    public void setClinicRegistrationNo(String clinicRegistrationNo) {
        this.clinicRegistrationNo = clinicRegistrationNo;
    }

    public String getClinicWebsiteAddress() {
        return clinicWebsiteAddress;
    }

    public void setClinicWebsiteAddress(String clinicWebsiteAddress) {
        this.clinicWebsiteAddress = clinicWebsiteAddress;
    }

    public String getSms_service_name() {
        return sms_service_name;
    }

    public void setSms_service_name(String sms_service_name) {
        this.sms_service_name = sms_service_name;
    }

    public UserStaff getAdministrator() {
        return administrator;
    }

    // getters & setter for walkin

    public void setAdministrator(UserStaff administrator) {
        this.administrator = administrator;
    }

    public UserStaff getWalkin_administrator() {
        return walkin_administrator;
    }

    public void setWalkin_administrator(UserStaff walkin_administrator) {
        this.walkin_administrator = walkin_administrator;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
}
