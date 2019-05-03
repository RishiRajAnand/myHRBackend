package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 07-12-17
* */

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bm_service_ordering_department")
public class ServiceOrderingDepartment extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "billing_catalouge_service_id")
    private ServiceCatalogue serviceCatalogue;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentMaster department;

    public ServiceCatalogue getServiceCatalogue() {
        return serviceCatalogue;
    }

    public void setServiceCatalogue(ServiceCatalogue serviceCatalogue) {
        this.serviceCatalogue = serviceCatalogue;
    }

    public DepartmentMaster getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentMaster department) {
        this.department = department;
    }
}
