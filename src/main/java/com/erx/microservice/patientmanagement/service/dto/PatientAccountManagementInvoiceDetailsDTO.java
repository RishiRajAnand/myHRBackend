package com.erx.microservice.patientmanagement.service.dto;

public class PatientAccountManagementInvoiceDetailsDTO {

    private String invoiceNumber;
    private String invoiceDate;
    private double invoiceAmount;
    private double discountValue;
    private double depositAdjusted;
    private double dueAmount;
    private String status;
    private double invoicePaidValue;

    //Getters and setters

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

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public double getDepositAdjusted() {
        return depositAdjusted;
    }

    public void setDepositAdjusted(double depositAdjusted) {
        this.depositAdjusted = depositAdjusted;
    }

    public double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getInvoicePaidValue() {
        return invoicePaidValue;
    }

    public void setInvoicePaidValue(double invoicePaidValue) {
        this.invoicePaidValue = invoicePaidValue;
    }
}
