package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tm_therapy_schedule")
public class TherapySchedule extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "tm_therapy_instance_id")
    private TherapyInstance therapyInstance;

    @Column(name = "schedule_date", nullable = false)
    private LocalDateTime scheduleDate;

    @Column(name = "therapy_schedule_start_time", nullable = false)
    private LocalDateTime scheduleStartTime;

    @Column(name = "therapy_schedule_end_time", nullable = false)
    private LocalDateTime scheduleEndTime;

    @ManyToOne
    @JoinColumn(name = "room_detail_id")
    private TherapyRoomDetails roomDetails;

    @Column(name="is_scheduled")
    private boolean scheduled;

    @Column(name="is_proposed")
    private boolean isProposed;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;

    public TherapySchedule() {
        this.scheduled = false;
    }

    public TherapyInstance getTherapyInstance() {
        return therapyInstance;
    }

    public void setTherapyInstance(TherapyInstance therapyInstance) {
        this.therapyInstance = therapyInstance;
    }

    public LocalDateTime getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LocalDateTime getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(LocalDateTime scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public LocalDateTime getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(LocalDateTime scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public TherapyRoomDetails getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(TherapyRoomDetails roomDetails) {
        this.roomDetails = roomDetails;
    }

    public boolean isScheduled() {
        return scheduled;
    }

    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

    public boolean isProposed() {
        return isProposed;
    }

    public void setProposed(boolean proposed) {
        isProposed = proposed;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
