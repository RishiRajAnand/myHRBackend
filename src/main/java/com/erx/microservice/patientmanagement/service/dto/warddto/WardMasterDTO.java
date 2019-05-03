package com.erx.microservice.patientmanagement.service.dto.warddto;

/*
* created by Brighty on 16-11-2017
* */

public class WardMasterDTO {

    private Long id;

    private Long clinicLocationId;

    private String wardCode;

    private String wardName;

    private Long departmentId;

    private Long indentDepartmentId;

    private Long ipRequestDepartmentId;

    private boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getIndentDepartmentId() {
        return indentDepartmentId;
    }

    public void setIndentDepartmentId(Long indentDepartmentId) {
        this.indentDepartmentId = indentDepartmentId;
    }

    public Long getIpRequestDepartmentId() {
        return ipRequestDepartmentId;
    }

    public void setIpRequestDepartmentId(Long ipRequestDepartmentId) {
        this.ipRequestDepartmentId = ipRequestDepartmentId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
