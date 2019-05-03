package com.erx.microservice.patientmanagement.service.dto.patientreferraldto;

public class CreateReferralPatientMasterDTO {
    private Long Id;

    private String status;

    private String referralName;

    private String referralId;

    private double referralRate;

    private boolean discount;

    private String phoneNumber;

    private String refferalAddress;

    private Long pincodeId;

    private Long userDetailId;

    private Long referralTypeLookupValueId;

    private Long referralRateLookupValueId;

    private Long referralExternalLookupValueId;

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

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
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

    public Long getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(Long userDetailId) {
        this.userDetailId = userDetailId;
    }

    public Long getReferralTypeLookupValueId() {
        return referralTypeLookupValueId;
    }

    public void setReferralTypeLookupValueId(Long referralTypeLookupValueId) {
        this.referralTypeLookupValueId = referralTypeLookupValueId;
    }

    public Long getReferralRateLookupValueId() {
        return referralRateLookupValueId;
    }

    public void setReferralRateLookupValueId(Long referralRateLookupValueId) {
        this.referralRateLookupValueId = referralRateLookupValueId;
    }

    public Long getReferralExternalLookupValueId() {
        return referralExternalLookupValueId;
    }

    public void setReferralExternalLookupValueId(Long referralExternalLookupValueId) {
        this.referralExternalLookupValueId = referralExternalLookupValueId;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
