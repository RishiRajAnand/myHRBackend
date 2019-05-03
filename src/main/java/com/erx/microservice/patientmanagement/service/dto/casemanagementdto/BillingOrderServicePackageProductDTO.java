package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
 * created by Latha on 01-10-2018
 * */

public class BillingOrderServicePackageProductDTO {

    private Long id;

    private Long servicePackageProductId; //service or package or product id

    private String code;

    private String name;

    private String category;

    private String type;

    private double revisedPrice;

    private double gst;

    private Long departmentId; //department mapping table id

    private String departmentName;

    private int quantity;

    private Long lookupStatusValId;

    private String remarks;

    private String status;

    private boolean packageApplicable;

    private String packageName;

    private Long OrderProductAddStockDetailId;

    private String OrderProductAddStockDetailPaymentStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServicePackageProductId() {
        return servicePackageProductId;
    }

    public void setServicePackageProductId(Long servicePackageProductId) {
        this.servicePackageProductId = servicePackageProductId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRevisedPrice() {
        return revisedPrice;
    }

    public void setRevisedPrice(double revisedPrice) {
        this.revisedPrice = revisedPrice;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getLookupStatusValId() {
        return lookupStatusValId;
    }

    public void setLookupStatusValId(Long lookupStatusValId) {
        this.lookupStatusValId = lookupStatusValId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPackageApplicable() {
        return packageApplicable;
    }

    public void setPackageApplicable(boolean packageApplicable) {
        this.packageApplicable = packageApplicable;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Long getOrderProductAddStockDetailId() {
        return OrderProductAddStockDetailId;
    }

    public void setOrderProductAddStockDetailId(Long orderProductAddStockDetailId) {
        OrderProductAddStockDetailId = orderProductAddStockDetailId;
    }

    public String getOrderProductAddStockDetailPaymentStatus() {
        return OrderProductAddStockDetailPaymentStatus;
    }

    public void setOrderProductAddStockDetailPaymentStatus(String orderProductAddStockDetailPaymentStatus) {
        OrderProductAddStockDetailPaymentStatus = orderProductAddStockDetailPaymentStatus;
    }
}
