package com.erx.microservice.patientmanagement.domain.casemanagement;

public enum OrderStatus {
    UNCHANGED(0), ADDED(1), UPDATED(2), DELETED(3);

    private int orderStatusValue;

    OrderStatus(int orderStatusValue) {
        this.orderStatusValue = orderStatusValue;
    }

    public int getOrderStatusValue() {
        return orderStatusValue;
    }

    public void setOrderStatusValue(int orderStatusValue) {
        this.orderStatusValue = orderStatusValue;
    }
}
