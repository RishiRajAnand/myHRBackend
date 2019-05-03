package com.erx.microservice.patientmanagement.service.dto;

public class PatientAccountManagementDepositDetailsDTO {
    private String depositCode;
    private String depositDate;
    private double depositAmount;
    private double utilizedDeposit;
    private double depositBalance;

    public String getDepositCode() {
        return depositCode;
    }

    public void setDepositCode(String depositCode) {
        this.depositCode = depositCode;
    }

    public String getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(String depositDate) {
        this.depositDate = depositDate;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public double getUtilizedDeposit() {
        return utilizedDeposit;
    }

    public void setUtilizedDeposit(double utilizedDeposit) {
        this.utilizedDeposit = utilizedDeposit;
    }

    public double getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(double depositBalance) {
        this.depositBalance = depositBalance;
    }
}
