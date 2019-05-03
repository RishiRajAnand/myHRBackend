package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;

@Entity
@Table(name = "module_department_mapping")
public class ModuleDepartmentMapping extends BaseModel{

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_location_id")
    private ClinicLocation clinicLocation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentMaster department;

    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public DepartmentMaster getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentMaster department) {
        this.department = department;
    }
}
