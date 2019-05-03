package com.erx.microservice.patientmanagement.service.dto;

import java.time.LocalDate;


public class PatientRefundMinimalDTO {
    Long id;

    private String refundNumber;

    private LocalDate refundedDate;

    private String refundTypeName;

    private double refundableAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(String refundNumber) {
        this.refundNumber = refundNumber;
    }

    public LocalDate getRefundedDate() {
        return refundedDate;
    }

    public void setRefundedDate(LocalDate refundedDate) {
        this.refundedDate = refundedDate;
    }

    public String getRefundTypeName() {
        return refundTypeName;
    }

    public void setRefundTypeName(String refundTypeName) {
        this.refundTypeName = refundTypeName;
    }

    public double getRefundableAmount() {
        return refundableAmount;
    }

    public void setRefundableAmount(double refundableAmount) {
        this.refundableAmount = refundableAmount;
    }
}
