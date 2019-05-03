/*
 * @Copyright © 2017. eRx Solutions Pvt Ltd
 * @author john@erxindia.in
 * @project eRx
 * @version eRx v2.0
 * @module billing-microservice
 * @createdon 13-Dec-2017
 */
package com.erx.microservice.patientmanagement.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BaseModelDTO {
    private Long id;
    @NotNull
    private int recordStatus;
    @NotNull
    private Long updatedBy;
    @NotNull
    private Long createdBy;
    @JsonIgnore
    private LocalDateTime updatedOn;
    @JsonIgnore
    private LocalDateTime createdOn;

    public BaseModelDTO() {
        this.createdBy = 1l;
        this.updatedBy = 1l;
        this.updatedOn = LocalDateTime.now();
        if(id == null)
        this.createdOn = LocalDateTime.now();
    }

    public Long getId ( ) {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public int getRecordStatus ( ) {
        return recordStatus;
    }

    public void setRecordStatus (int recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Long getUpdatedBy ( ) {
        return updatedBy;
    }

    public void setUpdatedBy (Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getCreatedBy ( ) {
        return createdBy;
    }

    public void setCreatedBy (Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedOn ( ) {
        return updatedOn;
    }

    public void setUpdatedOn (LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public LocalDateTime getCreatedOn ( ) {
        return createdOn;
    }

    public void setCreatedOn (LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
