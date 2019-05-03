package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="tm_therapy_master_room_detail")
public class TherapyMasterRoomDetail extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tm_therapy_master_id")
    @JsonIgnore
    private TherapyMaster therapyMaster;

    @Column(name = "room_detail_id")
    private Long roomDetailId;

    public TherapyMaster getTherapyMaster() {
        return therapyMaster;
    }

    public void setTherapyMaster(TherapyMaster therapyMaster) {
        this.therapyMaster = therapyMaster;
    }

    public Long getRoomDetailId() {
        return roomDetailId;
    }

    public void setRoomDetailId(Long roomDetailId) {
        this.roomDetailId = roomDetailId;
    }
}
