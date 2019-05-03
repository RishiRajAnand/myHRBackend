package com.erx.microservice.patientmanagement.service.dto;

/*
* created by Brighty on 14-12-2017
* */

public class AccountDetailDTO {

    private Long accountNameId;

    private String accountName;

    private double depositValue;

    private double invoicedValue;

    private double outstandingValue;

    private double depositBalanceValue;

    private double grossBalanceValue;

    private double depositAdjusted;

    //getters and setters

    public Long getAccountNameId() {
        return accountNameId;
    }

    public void setAccountNameId(Long accountNameId) {
        this.accountNameId = accountNameId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(double depositValue) {
        this.depositValue = depositValue;
    }

    public double getInvoicedValue() {
        return invoicedValue;
    }

    public void setInvoicedValue(double invoicedValue) {
        this.invoicedValue = invoicedValue;
    }

    public double getOutstandingValue() {
        return outstandingValue;
    }

    public void setOutstandingValue(double outstandingValue) {
        this.outstandingValue = outstandingValue;
    }

    public double getDepositBalanceValue() {
        return depositBalanceValue;
    }

    public void setDepositBalanceValue(double depositBalanceValue) {
        this.depositBalanceValue = depositBalanceValue;
    }

    public double getGrossBalanceValue() {
        return grossBalanceValue;
    }

    public void setGrossBalanceValue(double grossBalanceValue) {
        this.grossBalanceValue = grossBalanceValue;
    }

    public double getDepositAdjusted() {
        return depositAdjusted;
    }

    public void setDepositAdjusted(double depositAdjusted) {
        this.depositAdjusted = depositAdjusted;
    }
}
