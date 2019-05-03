package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;
/*
* created by Raushan on 20-11-2017
* */

@Entity
@Table(name = "refferal_patient_master")
public class ReferralPatientMaster extends BaseModel {

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "referral_name", nullable = false)
    private String referralName;

    @Column(name = "referral_Id", nullable = false)
    private String referralId;

    @Column(name = "referral_rate")
    private double referralRate;

    @Column(name = "discount")
    private boolean discount;

    @Column(name = "refferal_phone_number", nullable = true)
    private String phoneNumber;

    @Column(name = "refferal_address", nullable = true)
    private String refferalAddress;

    //@ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "pincode_id", nullable = true)
    private Long pincodeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_detail_id", nullable = true)
    private UserDetail userDetail;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "referral_type_lookup_value_id", nullable = false)
    private LookupValue referralTypeLookupValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "referral_rate_lookup_value_id", nullable = false)
    private LookupValue referralRateLookupValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "referral_external_lookup_value_id", nullable = true)
    private LookupValue referralExternalLookupValue;

    // @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "clinic_location_id", nullable = false)
    private Long clinicLocationId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReferralName() {
        return referralName;
    }

    public void setReferralName(String referralName) {
        this.referralName = referralName;
    }

    public double getReferralRate() {
        return referralRate;
    }

    public void setReferralRate(double referralRate) {
        this.referralRate = referralRate;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRefferalAddress() {
        return refferalAddress;
    }

    public void setRefferalAddress(String refferalAddress) {
        this.refferalAddress = refferalAddress;
    }

    public Long getPincodeId() {
        return pincodeId;
    }

    public void setPincodeId(Long pincodeId) {
        this.pincodeId = pincodeId;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public LookupValue getReferralTypeLookupValue() {
        return referralTypeLookupValue;
    }

    public void setReferralTypeLookupValue(LookupValue referralTypeLookupValue) {
        this.referralTypeLookupValue = referralTypeLookupValue;
    }

    public LookupValue getReferralRateLookupValue() {
        return referralRateLookupValue;
    }

    public void setReferralRateLookupValue(LookupValue referralRateLookupValue) {
        this.referralRateLookupValue = referralRateLookupValue;
    }

    public LookupValue getReferralExternalLookupValue() {
        return referralExternalLookupValue;
    }

    public void setReferralExternalLookupValue(LookupValue referralExternalLookupValue) {
        this.referralExternalLookupValue = referralExternalLookupValue;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }
}
