/*
 * @Copyright © 2017. eRx Solutions Pvt Ltd
 * @author john@erxindia.in
 * @project eRx
 * @version eRx v2.0
 * @module billing-microservice
 * @createdon 22-Nov-2017
 */
package com.erx.microservice.patientmanagement.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Raushan on 19/12/17.
 */
@Entity
@Table(name = "bm_package_catalogue")
public class PackageCatalogue extends BaseModel {

    @NotNull
    @Column(name = "package_name")
    private String packageName;
    @NotNull
    @Column(name = "package_code")
    private String packageCode;
    @NotNull
    @Column(name = "package_price")
    private double packagePrice;
    @NotNull
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @NotNull
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "bm_catalogue_category_master", nullable = false)
    private CatalogueCategory catalogueCategory;
    @Column(name = "status")
    private String status;
    @Column(name = "is_active")
    private Boolean active;
    @Column(name = "package_billing_type")
    private Boolean packageBillingType;
    @Column(name = "duration", nullable = false)
    private int duration;
    @Column(name = "lookup_package_type_val_id")
    private Long lookupPackageTypeValId;

    //Getters and Setters
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CatalogueCategory getCatalogueCategory() {
        return catalogueCategory;
    }

    public void setCatalogueCategory(CatalogueCategory catalogueCategory) {
        this.catalogueCategory = catalogueCategory;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getPackageBillingType() {
        return packageBillingType;
    }

    public void setPackageBillingType(Boolean packageBillingType) {
        this.packageBillingType = packageBillingType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Long getLookupPackageTypeValId() {
        return lookupPackageTypeValId;
    }

    public void setLookupPackageTypeValId(Long lookupPackageTypeValId) {
        this.lookupPackageTypeValId = lookupPackageTypeValId;
    }

}