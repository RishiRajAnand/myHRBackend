package com.erx.microservice.patientmanagement.service.dto;
/*
 * created by Brighty on 5-12-17
 * */

import java.time.LocalDateTime;

public class CampRegistrationDTO {

    private Long patientId;

    private Long clinicId;

    private String campRegistrationNumber;

    private String mrn;

    private String fullName;

    private int age;

    private String gender;

    private String mobileNumber;

    private LocalDateTime patientRegisteredDate;

    private String city;

    private String campName;

    private String caseId;

    private String patientSalutation;

    //getters and setters

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getCampRegistrationNumber() {
        return campRegistrationNumber;
    }

    public void setCampRegistrationNumber(String campRegistrationNumber) {
        this.campRegistrationNumber = campRegistrationNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getPatientRegisteredDate() {
        return patientRegisteredDate;
    }

    public void setPatientRegisteredDate(LocalDateTime patientRegisteredDate) {
        this.patientRegisteredDate = patientRegisteredDate;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getPatientSalutation() {
        return patientSalutation;
    }

    public void setPatientSalutation(String patientSalutation) {
        this.patientSalutation = patientSalutation;
    }
}
