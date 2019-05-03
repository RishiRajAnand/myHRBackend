package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
 * created by Latha on 01-10-2018
 * */

public class BillingOrderServiceDTO {

    private Long id;
    private SearchServiceCatalogueDTO searchServiceCatalogueDTO;
    private Long serviceId;
    private int quantity;
    private Long lookupStatusValId;
    private String serviceStatus;
    private String remarks;
    private Long packagePatientItemDetailId;
    private double billingApplicablePercentage;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SearchServiceCatalogueDTO getSearchServiceCatalogueDTO() {
        return searchServiceCatalogueDTO;
    }

    public void setSearchServiceCatalogueDTO(SearchServiceCatalogueDTO searchServiceCatalogueDTO) {
        this.searchServiceCatalogueDTO = searchServiceCatalogueDTO;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
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

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getPackagePatientItemDetailId() {
        return packagePatientItemDetailId;
    }

    public void setPackagePatientItemDetailId(Long packagePatientItemDetailId) {
        this.packagePatientItemDetailId = packagePatientItemDetailId;
    }

    public double getBillingApplicablePercentage() {
        return billingApplicablePercentage;
    }

    public void setBillingApplicablePercentage(double billingApplicablePercentage) {
        this.billingApplicablePercentage = billingApplicablePercentage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
