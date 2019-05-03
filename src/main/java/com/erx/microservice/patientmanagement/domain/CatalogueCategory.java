package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 07-12-17
* */

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "bm_catalogue_category_master")
public class CatalogueCategory extends BaseModel {

    @NotNull
    @Column(name = "category_name")
    private String categoryName;

    @NotNull
    @Column(name = "category_code")
    private String categoryCode;

    @ManyToOne
    @JoinColumn(name = "clinic_location_id")
    private ClinicLocation clinicLocation;

    @NotNull
    @Column(name = "is_active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private CatalogueCategory parentCatalogueCategory;

    @OneToMany(mappedBy = "catalogueCategory", fetch = FetchType.EAGER)
    @Where(clause = "parent_id IS NULL AND record_status = 1")
    private Set<CatalogueCategoryDepartments> categoryDepartments;

    @OneToMany(mappedBy = "catalogueCategory", fetch = FetchType.EAGER)
    @Where(clause = "category_department_id IS NULL AND record_status = 1")
    private Set<ServiceCatalogue> serviceCatalogues;

    @OneToMany(mappedBy = "catalogueCategory", fetch = FetchType.EAGER)
  //  @Where(clause = "category_department_id IS NULL AND record_status = 1")
    private Set<PackageCatalogue> packageCatalogues;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CatalogueCategoryType catalogueCategoryType;

    // Getters and Setters.

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CatalogueCategory getParentCatalogueCategory() {
        return parentCatalogueCategory;
    }

    public void setParentCatalogueCategory(CatalogueCategory parentCatalogueCategory) {
        this.parentCatalogueCategory = parentCatalogueCategory;
    }

    public Set<ServiceCatalogue> getServiceCatalogues() {
        return serviceCatalogues;
    }

    public void setServiceCatalogues(Set<ServiceCatalogue> serviceCatalogues) {
        this.serviceCatalogues = serviceCatalogues;
    }

    public Set<CatalogueCategoryDepartments> getCategoryDepartments() {
        return categoryDepartments;
    }

    public void setCategoryDepartments(Set<CatalogueCategoryDepartments> categoryDepartments) {
        this.categoryDepartments = categoryDepartments;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public CatalogueCategoryType getCatalogueCategoryType() {
        return catalogueCategoryType;
    }

    public void setCatalogueCategoryType(CatalogueCategoryType catalogueCategoryType) {
        this.catalogueCategoryType = catalogueCategoryType;
    }

    public Set<PackageCatalogue> getPackageCatalogues() {
        return packageCatalogues;
    }

    public void setPackageCatalogues(Set<PackageCatalogue> packageCatalogues) {
        this.packageCatalogues = packageCatalogues;
    }
}
