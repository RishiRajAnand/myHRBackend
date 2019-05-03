package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 10-10-2018
* */


import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cm_next_visit")
public class CmNextVisit extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "cm_master_id", nullable=true)
    @JsonIgnore
    private CmMaster cmMaster;

    @Column(name = "next_visit_date", nullable = true)
    private LocalDateTime nextVisitDate;

    @Column(name = "created_date", nullable = true)
    private LocalDateTime createdDate;

    @Column(name = "next_visit_status", nullable = true)
    private String nextVisitStatus;

    public CmMaster getCmMaster() {
        return cmMaster;
    }

    public void setCmMaster(CmMaster cmMaster) {
        this.cmMaster = cmMaster;
    }

    public LocalDateTime getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(LocalDateTime nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getNextVisitStatus() {
        return nextVisitStatus;
    }

    public void setNextVisitStatus(String nextVisitStatus) {
        this.nextVisitStatus = nextVisitStatus;
    }
}
