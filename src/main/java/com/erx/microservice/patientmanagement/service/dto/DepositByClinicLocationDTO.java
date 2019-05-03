package com.erx.microservice.patientmanagement.service.dto;

/**
 * Created by brighty on 23-11-17.
 */

public class DepositByClinicLocationDTO {

    private Long id;

    private String depositAccountName;

    private String depositTypeName;

    private double depositValue;

    private double utilizedValue;

    private double balanceValue;

    //Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepositAccountName() {
        return depositAccountName;
    }

    public void setDepositAccountName(String depositAccountName) {
        this.depositAccountName = depositAccountName;
    }

    public String getDepositTypeName() {
        return depositTypeName;
    }

    public void setDepositTypeName(String depositTypeName) {
        this.depositTypeName = depositTypeName;
    }

    public double getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(double depositValue) {
        this.depositValue = depositValue;
    }

    public double getUtilizedValue() {
        return utilizedValue;
    }

    public void setUtilizedValue(double utilizedValue) {
        this.utilizedValue = utilizedValue;
    }

    public double getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(double balanceValue) {
        this.balanceValue = balanceValue;
    }
}
