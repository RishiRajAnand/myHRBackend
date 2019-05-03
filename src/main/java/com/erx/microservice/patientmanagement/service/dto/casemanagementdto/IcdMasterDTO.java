package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 18/09/18.
 */

public class IcdMasterDTO {

    private Long icdMasterId;
    private String name;
    private String description;

    public Long getIcdMasterId() {
        return icdMasterId;
    }

    public void setIcdMasterId(Long icdMasterId) {
        this.icdMasterId = icdMasterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
