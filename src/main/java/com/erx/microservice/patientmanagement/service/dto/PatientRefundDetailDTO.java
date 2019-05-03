package com.erx.microservice.patientmanagement.service.dto;
/*
* created by Brighty on 27-11-17
* */

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientRefundDetailDTO {

    private Long id;

    private Long clinicLocationId;

    private Long userId;

    private String userName;

    private String refundNumber;

    private LocalDateTime refundDate;

    private Long patientId;

    private String patientName;

    private String MRN;

    private int age;

    private String gender;

    private String mobileNumber;

    private Long refundTypeId;

    private String refundTypeName;

    private Long depositAccountId;

    private String depositAccountName;

    private double depositAmount;

    private double refundableAmount;

    private LocalDate refundedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public void setClinicLocationId(long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public String getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(String refundNumber) {
        this.refundNumber = refundNumber;
    }

    public LocalDateTime getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(LocalDateTime refundDate) {
        this.refundDate = refundDate;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMRN() {
        return MRN;
    }

    public void setMRN(String MRN) {
        this.MRN = MRN;
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

    public long getRefundTypeId() {
        return refundTypeId;
    }

    public void setRefundTypeId(Long refundTypeId) {
        this.refundTypeId = refundTypeId;
    }

    public void setRefundTypeId(long refundTypeId) {
        this.refundTypeId = refundTypeId;
    }

    public String getRefundTypeName() {
        return refundTypeName;
    }

    public void setRefundTypeName(String refundTypeName) {
        this.refundTypeName = refundTypeName;
    }

    public Long getDepositAccountId() {
        return depositAccountId;
    }

    public void setDepositAccountId(Long depositAccountId) {
        this.depositAccountId = depositAccountId;
    }

    public String getDepositAccountName() {
        return depositAccountName;
    }

    public void setDepositAccountName(String depositAccountName) {
        this.depositAccountName = depositAccountName;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getRefundableAmount() {
        return refundableAmount;
    }

    public void setRefundableAmount(double refundableAmount) {
        this.refundableAmount = refundableAmount;
    }

    public LocalDate getRefundedDate() {
        return refundedDate;
    }

    public void setRefundedDate(LocalDate refundedDate) {
        this.refundedDate = refundedDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
