package com.erx.microservice.patientmanagement.service.dto;

/*
* created by Brighty on 14-12-2017
* */

import java.util.List;

public class PatientAccountManagementDTO {

    private Long patientId;

    private String mrn;

    private String patientName;

    private String gender;

    private int age;

    private String mobileNumber;

    private String email;

    private List<AccountDetailDTO> accountDetailDTOs;

    //getters and setters

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AccountDetailDTO> getAccountDetailDTOs() {
        return accountDetailDTOs;
    }

    public void setAccountDetailDTOs(List<AccountDetailDTO> accountDetailDTOs) {
        this.accountDetailDTOs = accountDetailDTOs;
    }
}
