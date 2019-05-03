package com.erx.microservice.patientmanagement.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;

/*
* created by Brighty on 09-11-2017
* */

@Entity
@Table(name = "clinic_location")
public class ClinicLocation extends BaseModel {


    //@LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "is_default", nullable = true)
    private boolean defaultLocation;

    /* @Embedded
     @Valid
     private Address address;
 */
    @Column(length = 150, name = "address1")
    @NotBlank(message = "{address1.required}")
    private String address1;

    @Column(length = 150, name = "address2")
    private String address2;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    @Valid
    private City city;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id")
    @Valid
    private State state;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    @Valid
    private Country country;

    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "pincode_id")
    @Valid
    private Pincode pincode;

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "administrator_id", nullable = true)
    @Valid
    private UserStaff administrator;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(nullable = false, name = "registered_at")
    private LocalDateTime registeredAt;

    @Column(name = "code", nullable = true)
    private String code;

    @Column(name = "clinic_status", nullable = false)
    private String clinicStatus;

    @Column(name = "facility_type", nullable = false)
    private String facilityType;


    public ClinicLocation() {
        this.registeredAt = LocalDateTime.now();
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Location getLocation() {
        return location;
    }


   /* public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }*/

    public void setLocation(Location location) {
        this.location = location;
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

    public boolean isDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(boolean defaultLocation) {
        this.defaultLocation = defaultLocation;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClinicStatus() {
        return clinicStatus;
    }

    public void setClinicStatus(String clinicStatus) {
        this.clinicStatus = clinicStatus;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public UserStaff getAdministrator() {
        return administrator;
    }

    public void setAdministrator(UserStaff administrator) {
        this.administrator = administrator;
    }
}
