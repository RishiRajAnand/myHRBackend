package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Brighty on 19-06-2016
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cm_master_reopen_detail")
public class CmMasterReopenDetail extends BaseModel {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "cm_master_id")
    private CmMaster cmMaster;

    @Column(name = "case_completed_date")
    private LocalDateTime caseCompletedDate;

    @Column(name = "status")
    private String status;

    //Getters and setters

    public CmMaster getCmMaster() {
        return cmMaster;
    }

    public void setCmMaster(CmMaster cmMaster) {
        this.cmMaster = cmMaster;
    }

    public LocalDateTime getCaseCompletedDate() {
        return caseCompletedDate;
    }

    public void setCaseCompletedDate(LocalDateTime caseCompletedDate) {
        this.caseCompletedDate = caseCompletedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
