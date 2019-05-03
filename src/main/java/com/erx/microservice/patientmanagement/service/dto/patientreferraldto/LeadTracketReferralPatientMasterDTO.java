package com.erx.microservice.patientmanagement.service.dto.patientreferraldto;
/*
* created by Raushan on 20-11-2017
* */

public class LeadTracketReferralPatientMasterDTO {

    private Long id;

    private String status;

    private String referralName;

    private String referralId;

    private double referralRate;

    private String doctorName ;

    private String referralType ;

    private String referralRateType ;

   //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getReferralType() {
        return referralType;
    }

    public void setReferralType(String referralType) {
        this.referralType = referralType;
    }

    public String getReferralRateType() {
        return referralRateType;
    }

    public void setReferralRateType(String referralRateType) {
        this.referralRateType = referralRateType;
    }
}
