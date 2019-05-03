package com.erx.microservice.patientmanagement.service.dto.warddto;

/*
* created by Brighty on 16-11-2017
* */

public class WardMasterByIdDTO {

    private Long id;

    private Long clinicLocationId;

    private String wardCode;

    private String wardName;

    private Long departmentId;

    private String departmentName;

    private Long IndentDepartmentId;

    private String IndentDepartmentName;

    private Long IpRequestDepartmentId;

    private String IpRequestDepartmentName;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getIndentDepartmentId() {
        return IndentDepartmentId;
    }

    public void setIndentDepartmentId(Long indentDepartmentId) {
        IndentDepartmentId = indentDepartmentId;
    }

    public String getIndentDepartmentName() {
        return IndentDepartmentName;
    }

    public void setIndentDepartmentName(String indentDepartmentName) {
        IndentDepartmentName = indentDepartmentName;
    }

    public Long getIpRequestDepartmentId() {
        return IpRequestDepartmentId;
    }

    public void setIpRequestDepartmentId(Long ipRequestDepartmentId) {
        IpRequestDepartmentId = ipRequestDepartmentId;
    }

    public String getIpRequestDepartmentName() {
        return IpRequestDepartmentName;
    }

    public void setIpRequestDepartmentName(String ipRequestDepartmentName) {
        IpRequestDepartmentName = ipRequestDepartmentName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
