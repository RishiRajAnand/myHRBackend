package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 07-12-17
* */

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "bm_service_catalogue")
public class ServiceCatalogue extends BaseModel {

    @NotNull
    @Column(name = "service_name")
    private String serviceName;

    @NotNull
    @Column(name = "service_code")
    private String serviceCode;

    @Column(name = "base_price")
    private Double basePrice;

    @ManyToOne
    @JoinColumn(name = "billing_catagory_master_id", nullable = true)
    private CatalogueCategory catalogueCategory;

    @ManyToOne
    @JoinColumn(name = "category_department_id", nullable = true)
    private CatalogueCategoryDepartments catalogueCategoryDepartments;

    @Column(name = "status")
    private String status;

    @Column(name = "is_active")
    private Boolean active;

    @Transient
    private double sellingPrice;

    @Transient
    private BedTypeMaster selectedBedType;

    @Transient
    private PatientType selectedPatientType;

    @OneToMany(mappedBy = "serviceCatalogue", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Where(clause = "record_status = 1")
    private Set<ServicePatientTypeRatePlan> servicePatientTypeRatePlans;

    @OneToMany(mappedBy = "serviceCatalogue", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Where(clause = "record_status = 1")
    private Set<ServiceBedTypeRatePlan> serviceBedTypeRatePlans;

    @OneToMany(mappedBy = "serviceCatalogue", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Where(clause = "record_status = 1")
    private Set<ServiceOrderingDepartment> serviceOrderingDepartments;

    //Getters and Setters
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public CatalogueCategory getCatalogueCategory() {
        return catalogueCategory;
    }

    public void setCatalogueCategory(CatalogueCategory catalogueCategory) {
        this.catalogueCategory = catalogueCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CatalogueCategoryDepartments getCatalogueCategoryDepartments() {
        return catalogueCategoryDepartments;
    }

    public void setCatalogueCategoryDepartments(CatalogueCategoryDepartments catalogueCategoryDepartments) {
        this.catalogueCategoryDepartments = catalogueCategoryDepartments;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<ServicePatientTypeRatePlan> getServicePatientTypeRatePlans() {
        return servicePatientTypeRatePlans;
    }

    public void setServicePatientTypeRatePlans(Set<ServicePatientTypeRatePlan> servicePatientTypeRatePlans) {
        this.servicePatientTypeRatePlans = servicePatientTypeRatePlans;
    }

    public Set<ServiceBedTypeRatePlan> getServiceBedTypeRatePlans() {
        return serviceBedTypeRatePlans;
    }

    public void setServiceBedTypeRatePlans(Set<ServiceBedTypeRatePlan> serviceBedTypeRatePlans) {
        this.serviceBedTypeRatePlans = serviceBedTypeRatePlans;
    }

    public Set<ServiceOrderingDepartment> getServiceOrderingDepartments() {
        return serviceOrderingDepartments;
    }

    public void setServiceOrderingDepartments(Set<ServiceOrderingDepartment> serviceOrderingDepartments) {
        this.serviceOrderingDepartments = serviceOrderingDepartments;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BedTypeMaster getSelectedBedType() {
        return selectedBedType;
    }

    public void setSelectedBedType(BedTypeMaster selectedBedType) {
        this.selectedBedType = selectedBedType;
    }

    public PatientType getSelectedPatientType() {
        return selectedPatientType;
    }

    public void setSelectedPatientType(PatientType selectedPatientType) {
        this.selectedPatientType = selectedPatientType;
    }
}

