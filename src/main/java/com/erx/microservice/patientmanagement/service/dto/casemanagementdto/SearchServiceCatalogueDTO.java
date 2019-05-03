package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
 * created by Latha on 01-10-2018
 * */

public class SearchServiceCatalogueDTO {

    private String serviceName;
    private String serviceCode;
    private String type;
    private String categoryName;
    private Long departmentId;
    private String departmentName;
    private Double revisedPrice;
    private Double GST;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public Double getRevisedPrice() {
        return revisedPrice;
    }

    public void setRevisedPrice(Double revisedPrice) {
        this.revisedPrice = revisedPrice;
    }

    public Double getGST() {
        return GST;
    }

    public void setGST(Double GST) {
        this.GST = GST;
    }
}
