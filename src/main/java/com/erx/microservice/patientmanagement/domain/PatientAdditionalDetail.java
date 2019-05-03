package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 06-12-2017
* */

import javax.persistence.*;

@Entity
@Table(name = "patient_additional_details")
public class PatientAdditionalDetail extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "patient_type_id", nullable = true)
    private PatientType patientType;
    @ManyToOne
    @JoinColumn(name = "user_detail_id", nullable = true)
    private UserDetail userDetail;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientType getPatientType() {
        return patientType;
    }

    public void setPatientType(PatientType patientType) {
        this.patientType = patientType;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;

    }
}
