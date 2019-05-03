package com.erx.microservice.patientmanagement.service.dto.patientreferraldto;

import com.erx.microservice.patientmanagement.service.dto.LookupValueDTO;

/*
* created by Raushan on 20-11-2017
* */
public class ReferralPatientMasterDTO {

    private Long Id;

    private String status;

    private String referralName;

    private String referralId;

    private double referralRate;

    private boolean discount;

    private String phoneNumber;

    private String refferalAddress;

  /*  private LookupValueDTO referralTypeLookupValue;

    private LookupValueDTO referralRateLookupValue;

    private LookupValueDTO referralExternalLookupValue;*/

    private Long clinicLocationId;
   //getter and setter
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

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


 /*   public LookupValueDTO getReferralTypeLookupValue() {
        return referralTypeLookupValue;
    }

    public void setReferralTypeLookupValue(LookupValueDTO referralTypeLookupValue) {
        this.referralTypeLookupValue = referralTypeLookupValue;
    }*/

   /* public LookupValueDTO getReferralRateLookupValue() {
        return referralRateLookupValue;
    }

    public void setReferralRateLookupValue(LookupValueDTO referralRateLookupValue) {
        this.referralRateLookupValue = referralRateLookupValue;
    }

    public LookupValueDTO getReferralExternalLookupValue() {
        return referralExternalLookupValue;
    }

    public void setReferralExternalLookupValue(LookupValueDTO referralExternalLookupValue) {
        this.referralExternalLookupValue = referralExternalLookupValue;
    }*/

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
