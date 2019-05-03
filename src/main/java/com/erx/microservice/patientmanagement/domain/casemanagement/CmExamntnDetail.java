package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
 * created by Latha on 06-10-2018
 * */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cm_exam_detail")
public class CmExamntnDetail extends BaseModel{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cm_master_detail_id", nullable=false)
    @JsonIgnore
    private CmMasterDetails cmMasterDetails;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cm_exam_type_id", nullable=false)
    @JsonIgnore
    private CmExamntnType cmExaminationType;

    @Column(name="cm_exam_id", nullable=false)
    private long cmExamId;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    public CmMasterDetails getCmMasterDetails() {
        return cmMasterDetails;
    }

    public void setCmMasterDetails(CmMasterDetails cmMasterDetails) {
        this.cmMasterDetails = cmMasterDetails;
    }

    public CmExamntnType getCmExaminationType() {
        return cmExaminationType;
    }

    public void setCmExaminationType(CmExamntnType cmExaminationType) {
        this.cmExaminationType = cmExaminationType;
    }

    public long getCmExamId() {
        return cmExamId;
    }

    public void setCmExamId(long cmExamId) {
        this.cmExamId = cmExamId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
