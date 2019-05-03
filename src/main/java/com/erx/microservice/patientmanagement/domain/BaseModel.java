package com.erx.microservice.patientmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/*
* created by Brighty on 09-11-2017
* */

@MappedSuperclass
public class BaseModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@Valid
    @NotNull
    @JsonProperty*/
    private Long id;

    @NotNull
    @Column(name = "record_status")
    private int recordStatus;

    @NotNull
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @NotNull
    @Column(name = "updated_by")
    private long updatedBy;

    @NotNull
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @JsonIgnore
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    public BaseModel() {
        this.recordStatus = 1;
        this.updatedBy = -1;
        this.updatedOn = LocalDateTime.now();
        if (id == null)
            this.createdBy = -1l;
        this.createdOn = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(int recordStatus) {
        this.recordStatus = recordStatus;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
