package com.erx.microservice.patientmanagement.service.dto;

public class PatientAccountManagementDepositBalanceDetailsDTO {
    private String invoiceNumber;
    private String invoiceDate;
    private double grossAmount;
    private double utilizedDepositAmount;
    private double depositBalanceAmount;

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public double getUtilizedDepositAmount() {
        return utilizedDepositAmount;
    }

    public void setUtilizedDepositAmount(double utilizedDepositAmount) {
        this.utilizedDepositAmount = utilizedDepositAmount;
    }

    public double getDepositBalanceAmount() {
        return depositBalanceAmount;
    }

    public void setDepositBalanceAmount(double depositBalanceAmount) {
        this.depositBalanceAmount = depositBalanceAmount;
    }
}
