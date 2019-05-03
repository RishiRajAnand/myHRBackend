package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 07-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "room_detail")
public class TherapyRoomDetails extends BaseModel{

    @OneToOne
    @JoinColumn(name = "room_master_id", nullable = false)
    @JsonIgnore
    private TherapyRoomMaster therapyRoomMaster;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    @JsonIgnore
    private TherapyRoomType therapyRoomType;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "bed_capacity")
    private long bedCapacity;

    @Column(name = "room_price")
    private String roomPrice;

    @Column(name = "room_availability_status")
    private String roomAvailabilityStatus;

    public TherapyRoomMaster getTherapyRoomMaster() {
        return therapyRoomMaster;
    }

    public void setTherapyRoomMaster(TherapyRoomMaster therapyRoomMaster) {
        this.therapyRoomMaster = therapyRoomMaster;
    }

    public TherapyRoomType getTherapyRoomType() {
        return therapyRoomType;
    }

    public void setTherapyRoomType(TherapyRoomType therapyRoomType) {
        this.therapyRoomType = therapyRoomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public long getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(long bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomAvailabilityStatus() {
        return roomAvailabilityStatus;
    }

    public void setRoomAvailabilityStatus(String roomAvailabilityStatus) {
        this.roomAvailabilityStatus = roomAvailabilityStatus;
    }
}
