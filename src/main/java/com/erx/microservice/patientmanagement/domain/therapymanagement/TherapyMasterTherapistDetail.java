package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="tm_therapy_master_therapist_detail")
public class TherapyMasterTherapistDetail extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tm_therapy_master_id")
    @JsonIgnore
    private TherapyMaster therapyMaster;

    @Column(name = "user_id")
    private Long userId;

    public TherapyMaster getTherapyMaster() {
        return therapyMaster;
    }

    public void setTherapyMaster(TherapyMaster therapyMaster) {
        this.therapyMaster = therapyMaster;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
