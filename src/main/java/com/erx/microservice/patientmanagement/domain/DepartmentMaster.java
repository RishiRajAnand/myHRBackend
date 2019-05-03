package com.erx.microservice.patientmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by latha on 29/11/17.
 */

@Entity
@Table(name = "department_master")
public class DepartmentMaster extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "clinic_location_id", nullable = false)
    private ClinicLocation clinicLocation;

    @NotNull
    @Column(name = "department_id")
    private String departmentId;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "status")
    private boolean status;

    @Column(name = "is_sub_department")
    private boolean isSubDepartment;

    @ManyToOne
    @JoinColumn(name = "parent_department_id", nullable = true)
    @JsonIgnore
    private DepartmentMaster parentDepartment;

    @Column(name = "have_bedType")
    private boolean haveBedType;

    @Transient
    private DepartmentMaster parentDepartmentMaster;

    //getters and setters
    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isHaveBedType() {
        return haveBedType;
    }

    public void setHaveBedType(boolean haveBedType) {
        this.haveBedType = haveBedType;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isSubDepartment() {
        return isSubDepartment;
    }

    public void setSubDepartment(boolean subDepartment) {
        isSubDepartment = subDepartment;
    }

    public DepartmentMaster getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(DepartmentMaster parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public DepartmentMaster getParentDepartmentMaster() {
        if (this.parentDepartment != null) {
            DepartmentMaster tempDepartmentMaster = new DepartmentMaster();
            tempDepartmentMaster.setId(this.parentDepartment.getId());
            tempDepartmentMaster.setDepartmentName(this.parentDepartment.getDepartmentName());
            return tempDepartmentMaster;
        }
        return parentDepartmentMaster;
    }

    public void setParentDepartmentMaster(DepartmentMaster parentDepartmentMaster) {
        this.parentDepartmentMaster = parentDepartmentMaster;
    }
}
