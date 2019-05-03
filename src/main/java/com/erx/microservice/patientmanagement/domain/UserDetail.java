package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 28-11-2017
* */

import javax.persistence.*;

@Entity
@Table(name = "user_detail")
public class UserDetail extends BaseModel {

    @Column(nullable = false, length = 50, name = "registration_no")
    private String registration_No;

    @Column(name = "is_primary_contact")
    private boolean primaryContact;

    //  private Speciality speciality;
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private UserStaff userStaff;

    @Column(name = "is_doctor", nullable = false)
    private boolean isDoctor;

    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public String getRegistration_No() {
        return registration_No;
    }

    public void setRegistration_No(String registration_No) {
        this.registration_No = registration_No;
    }

    public boolean isPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(boolean primaryContact) {
        this.primaryContact = primaryContact;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public UserStaff getUserStaff() {
        return userStaff;
    }

    public void setUserStaff(UserStaff userStaff) {
        this.userStaff = userStaff;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }
}
