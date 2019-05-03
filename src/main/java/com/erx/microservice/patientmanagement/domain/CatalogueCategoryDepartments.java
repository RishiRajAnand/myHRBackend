package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 07-12-17
* */

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bm_catalogue_category_department")
public class CatalogueCategoryDepartments extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "billing_catagory_master_id", nullable = false)
    private CatalogueCategory catalogueCategory;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentMaster department;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private CatalogueCategoryDepartments parentCatalogueCategoryDepartments;

    @OneToMany(mappedBy = "parentCatalogueCategoryDepartments", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Where(clause = "record_status = 1")
    private Set<CatalogueCategoryDepartments> childCatalogueCategoryDepartments;

    @OneToMany(mappedBy = "catalogueCategoryDepartments", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Where(clause = "record_status = 1")
    private Set<ServiceCatalogue> serviceCatalogues;


    //Getters and Setters
    public CatalogueCategory getCatalogueCategory() {
        return catalogueCategory;
    }

    public void setCatalogueCategory(CatalogueCategory catalogueCategory) {
        this.catalogueCategory = catalogueCategory;
    }

    public DepartmentMaster getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentMaster department) {
        this.department = department;
    }

    public CatalogueCategoryDepartments getParentCatalogueCategoryDepartments() {
        return parentCatalogueCategoryDepartments;
    }

    public void setParentCatalogueCategoryDepartments(CatalogueCategoryDepartments parentCatalogueCategoryDepartments) {
        this.parentCatalogueCategoryDepartments = parentCatalogueCategoryDepartments;
    }


    public Set<CatalogueCategoryDepartments> getChildCatalogueCategoryDepartments() {
        return childCatalogueCategoryDepartments;
    }

    public void setChildCatalogueCategoryDepartments(Set<CatalogueCategoryDepartments> childCatalogueCategoryDepartments) {
        this.childCatalogueCategoryDepartments = childCatalogueCategoryDepartments;
    }

    public Set<ServiceCatalogue> getServiceCatalogues() {
        return serviceCatalogues;
    }

    public void setServiceCatalogues(Set<ServiceCatalogue> serviceCatalogues) {
        this.serviceCatalogues = serviceCatalogues;
    }

}
