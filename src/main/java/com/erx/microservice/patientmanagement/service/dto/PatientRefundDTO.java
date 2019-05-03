package com.erx.microservice.patientmanagement.service.dto;
/*
* created by Brighty on 27-11-17
* */

import java.time.LocalDateTime;

public class PatientRefundDTO {

    private Long id;

    private Long userId;

    private Long clinicLocationId;

    private String refundNumber;

    private LocalDateTime refundDate;

    private Long patientId;

    private Long refundTypeId;

    private Long depositAccountId;

    private double refundableAmount;

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

    public Long getRefundTypeId() {
        return refundTypeId;
    }

    public void setRefundTypeId(Long refundTypeId) {
        this.refundTypeId = refundTypeId;
    }

    public double getRefundableAmount() {
        return refundableAmount;
    }

    public void setRefundableAmount(double refundableAmount) {
        this.refundableAmount = refundableAmount;
    }

    public Long getDepositAccountId() {
        return depositAccountId;
    }

    public void setDepositAccountId(Long depositAccountId) {
        this.depositAccountId = depositAccountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
