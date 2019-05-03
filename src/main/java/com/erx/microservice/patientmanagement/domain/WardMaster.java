package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 28-11-2017
* */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ward_master")
public class WardMaster extends BaseModel {

    //@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @Column(name = "clinic_location_id", nullable = false)
    private Long clinicLocationId;

    @NotNull
    @Column(name = "ward_code")
    private String wardCode;

    @NotNull
    @Column(name = "ward_name")
    private String wardName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentMaster department;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "indent_department_id", nullable = true)
    private DepartmentMaster IndentDepartment;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ip_request_department_id", nullable = true)
    private DepartmentMaster IpRequestDepartment;

    @NotNull
    @Column(name = "status")
    private boolean status;

    public WardMaster() {
        this.status = true;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public DepartmentMaster getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentMaster department) {
        this.department = department;
    }

    public DepartmentMaster getIndentDepartment() {
        return IndentDepartment;
    }

    public void setIndentDepartment(DepartmentMaster indentDepartment) {
        IndentDepartment = indentDepartment;
    }

    public DepartmentMaster getIpRequestDepartment() {
        return IpRequestDepartment;
    }

    public void setIpRequestDepartment(DepartmentMaster ipRequestDepartment) {
        IpRequestDepartment = ipRequestDepartment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
