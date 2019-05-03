package com.erx.microservice.patientmanagement.service.dto;

import java.time.LocalDate;

public class SearchPatientRefundDTO {

    public String refundedBy;
    private Long patientId;
    private String patientName;
    private String MRN;
    private String mobileNumber;
    private Long id;
    private String refundNumber;
    private Long refundTypeId;
    private String refundTypeName;
    private double refundableAmount;
    private LocalDate refundedDate;

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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getRefundTypeId() {
        return refundTypeId;
    }

    public void setRefundTypeId(Long refundTypeId) {
        this.refundTypeId = refundTypeId;
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

    public String getRefundedBy() {
        return refundedBy;
    }

    public void setRefundedBy(String refundedBy) {
        this.refundedBy = refundedBy;
    }

    public LocalDate getRefundedDate() {
        return refundedDate;
    }

    public void setRefundedDate(LocalDate refundedDate) {
        this.refundedDate = refundedDate;
    }
}
