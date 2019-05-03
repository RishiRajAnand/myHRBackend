package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

public class NonConfigureTherapiesDTO {

    private Long serviceCatalogueId;

    private String serviceName;

    private Long departmentId;

    private String departmentName;

    private String catalogueCategoryName;

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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

    public String getCatalogueCategoryName() {
        return catalogueCategoryName;
    }

    public void setCatalogueCategoryName(String catalogueCategoryName) {
        this.catalogueCategoryName = catalogueCategoryName;
    }
}
