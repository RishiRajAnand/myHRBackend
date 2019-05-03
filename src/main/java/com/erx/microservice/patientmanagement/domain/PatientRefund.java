package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by brighty on 22-11-17.
 */

@Entity
@Table(name = "patient_refund")
public class PatientRefund extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "clinic_location_id", nullable = false)
    private ClinicLocation clinicLocation;

    @NotNull
    @Column(name = "refund_number")
    private String refundNumber;

    @NotNull
    @Column(name = "refund_date")
    private LocalDateTime refundDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "refund_type_id", nullable = false)
    private LookupValue refundType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_name_id", nullable = true)
    private VisitTypeMaster accountName;

    @NotNull
    @Column(name = "refundable_amount")
    private double refundableAmount;

    //Constructor
    public PatientRefund() {
        this.refundDate = LocalDateTime.now();
    }

    //Getters and setters
    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
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

    public LookupValue getRefundType() {
        return refundType;
    }

    public void setRefundType(LookupValue refundType) {
        this.refundType = refundType;
    }

    public VisitTypeMaster getAccountName() {
        return accountName;
    }

    public void setAccountName(VisitTypeMaster accountName) {
        this.accountName = accountName;
    }

    public double getRefundableAmount() {
        return refundableAmount;
    }

    public void setRefundableAmount(double refundableAmount) {
        this.refundableAmount = refundableAmount;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
