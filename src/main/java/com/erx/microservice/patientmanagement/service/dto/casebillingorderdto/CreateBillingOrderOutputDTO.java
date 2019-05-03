package com.erx.microservice.patientmanagement.service.dto.casebillingorderdto;

/*
* created by Latha on 25-09-2018
* */

import java.util.List;

public class CreateBillingOrderOutputDTO {

    private String orderNumber; // clinic auto generation number
    private Long bmOrderId; // auto generation id
    private List<Long> bmOrderServiceId;
    private List<Long> bmOrderProductId;
    private List<Long> bmOrderPackageId;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public List<Long> getBmOrderServiceId() {
        return bmOrderServiceId;
    }

    public void setBmOrderServiceId(List<Long> bmOrderServiceId) {
        this.bmOrderServiceId = bmOrderServiceId;
    }

    public List<Long> getBmOrderProductId() {
        return bmOrderProductId;
    }

    public void setBmOrderProductId(List<Long> bmOrderProductId) {
        this.bmOrderProductId = bmOrderProductId;
    }

    public List<Long> getBmOrderPackageId() {
        return bmOrderPackageId;
    }

    public void setBmOrderPackageId(List<Long> bmOrderPackageId) {
        this.bmOrderPackageId = bmOrderPackageId;
    }
}
