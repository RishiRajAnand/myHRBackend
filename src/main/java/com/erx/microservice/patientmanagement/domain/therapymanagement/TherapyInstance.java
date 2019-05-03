package com.erx.microservice.patientmanagement.domain.therapymanagement;
/*
* created by raushan on 28-08-2018
* */
import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tm_therapy_instance")
public class TherapyInstance extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "tm_therapy_planning_id")
    private TherapyPlanning therapyPlanning;

    @Column(name = "doctor_remark")
    private String doctorRemark;
    /*  private String therapistRemark;*/

    @Column(name = "therapy_planning_status")
    private String therapyPlanningStatus;

    @Column(name = "billing_status")
    private String billingStatus;

    @Column(name = "therapist_status")
    private String therapistStatus;

    @Column(name = "performed_therapy_status")
    private String performTherapyStatus;

    @Column(name = "last_action")
    private String lastAction;

    @Column(name = "schedule_status")
    private String scheduleStatus;

    @OneToMany(mappedBy = "therapyInstance", cascade = CascadeType.REMOVE)
    private List<TherapyInstanceNextAction> nextAction;

    //private TherapySchedule therapySchedule;
    //private String doctorNotifyRemark;

    public TherapyInstance() {
        this.therapyPlanningStatus = "DONE";
        this.billingStatus = "NOT_PAID";
        this.therapistStatus = "NOT_ASSIGNED";
        this.performTherapyStatus = "NOT_DONE";
        this.doctorRemark = "";
    }

    public TherapyPlanning getTherapyPlanning() {
        return therapyPlanning;
    }

    public void setTherapyPlanning(TherapyPlanning therapyPlanning) {
        this.therapyPlanning = therapyPlanning;
    }

    public String getTherapyPlanningStatus() {
        return therapyPlanningStatus;
    }

    public void setTherapyPlanningStatus(String therapyPlanningStatus) {
        this.therapyPlanningStatus = therapyPlanningStatus;
    }

    public String getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(String billingStatus) {
        this.billingStatus = billingStatus;
    }

    public String getTherapistStatus() {
        return therapistStatus;
    }

    public void setTherapistStatus(String therapistStatus) {
        this.therapistStatus = therapistStatus;
    }

    public String getPerformTherapyStatus() {
        return performTherapyStatus;
    }

    public void setPerformTherapyStatus(String performTherapyStatus) {
        this.performTherapyStatus = performTherapyStatus;
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public List<TherapyInstanceNextAction> getNextAction() {
        return nextAction;
    }

    public void setNextAction(List<TherapyInstanceNextAction> nextAction) {
        this.nextAction = nextAction;
    }

    public String getDoctorRemark() {
        return doctorRemark;
    }

    public void setDoctorRemark(String doctorRemark) {
        this.doctorRemark = doctorRemark;
    }


}
