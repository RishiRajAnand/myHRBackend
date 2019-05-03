package com.erx.microservice.patientmanagement.service.dto;

/**
 * Created by brighty on 18/10/17.
 */

public class DepartmentMasterDTO {

    private Long id;

    private ClinicLocationDTO clinicLocation;

    private Long clinicLocationId;

    private String departmentId;

    private String departmentName;

    private boolean status;

    private boolean isSubDepartment;

    private DepartmentMasterDTO parentDepartmentMaster;

    private boolean haveBedType;

    private boolean hasInventory;

    private Long clinicId;

    private boolean isClinicLevel;

    private Long userId;

    //getters and setters

    public DepartmentMasterDTO getParentDepartmentMaster() {
        return parentDepartmentMaster;
    }

    public void setParentDepartmentMaster(DepartmentMasterDTO parentDepartmentMaster) {
        this.parentDepartmentMaster = parentDepartmentMaster;
    }

   /*private List<DepartmentIndentLocationDTO> departmentIndentLocations;

    private List<DepartmentIndentDepartmentDTO> departmentIndentDepartments;*/

    //getters and setters
    public boolean isHaveBedType() {
        return haveBedType;
    }

    public void setHaveBedType(boolean haveBedType) {
        this.haveBedType = haveBedType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isStatus() {
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

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }


  /*public List<DepartmentIndentLocationDTO> getDepartmentIndentLocations() {
        return departmentIndentLocations;
    }

    public void setDepartmentIndentLocations(List<DepartmentIndentLocationDTO> departmentIndentLocations) {
        this.departmentIndentLocations = departmentIndentLocations;
    }

    public List<DepartmentIndentDepartmentDTO> getDepartmentIndentDepartments() {
        return departmentIndentDepartments;
    }

    public void setDepartmentIndentDepartments(List<DepartmentIndentDepartmentDTO> departmentIndentDepartments) {
        this.departmentIndentDepartments = departmentIndentDepartments;
    }*/

    public ClinicLocationDTO getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocationDTO clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public boolean isHasInventory() {
        return hasInventory;
    }

    public void setHasInventory(boolean hasInventory) {
        this.hasInventory = hasInventory;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public boolean isClinicLevel() {
        return isClinicLevel;
    }

    public void setClinicLevel(boolean clinicLevel) {
        isClinicLevel = clinicLevel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
