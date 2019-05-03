package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/**
 * Created by Latha on 18/09/18.
 */

public class AcdMasterDTO {

    private Long acdMasterId;
    private String name;
    private String description;

    public Long getAcdMasterId() {
        return acdMasterId;
    }

    public void setAcdMasterId(Long acdMasterId) {
        this.acdMasterId = acdMasterId;
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
