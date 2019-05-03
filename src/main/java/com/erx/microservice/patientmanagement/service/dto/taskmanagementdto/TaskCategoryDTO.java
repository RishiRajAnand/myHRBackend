package com.erx.microservice.patientmanagement.service.dto.taskmanagementdto;

import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskCategory;

import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the TaskCategory entity.
 */
public class TaskCategoryDTO {

    private Long id;

    private String code;

    private String name;

    private Long categoryTypeId;

    private Long contextTypeId;

    private Long parentId;

    private String clinicLocation;

    private Set<TaskCategoryDTO> children = new HashSet<>();


    public Set<TaskCategoryDTO> getChildren() {
        return children;
    }

    public void setChildren(Set<TaskCategoryDTO> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(Long lookupValueId) {
        this.categoryTypeId = lookupValueId;
    }

    public Long getContextTypeId() {
        return contextTypeId;
    }

    public void setContextTypeId(Long lookupValueId) {
        this.contextTypeId = lookupValueId;
    }


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(String clinicLocation) {
        this.clinicLocation = clinicLocation;
    }
}
