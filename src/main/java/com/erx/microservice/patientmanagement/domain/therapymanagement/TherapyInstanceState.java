package com.erx.microservice.patientmanagement.domain.therapymanagement;

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tm_therapy_instances_status_master")
public class TherapyInstanceState extends BaseModel {

    @Column(name = "payment_policy")
    private String paymentPolicy;

    @Column(name = "is_wellness")
    private boolean wellness;

    @Column(name = "therapy_planning_status")
    private String therapyPlanningStatus;

    @Column(name = "billing_status")
    private String billingStatus;

    @Column(name = "schedule_status")
    private String scheduleStatus;

    @Column(name = "therapist_status")
    private String therapistStatus;

    @Column(name = "performed_therapy_status")
    private String performTherapyStatus;

    @Column(name = "last_action")
    private String lastAction;

    @Column(name = "next_action")
    private String nextAction;


    public String getPaymentPolicy() {
        return paymentPolicy;
    }

    public void setPaymentPolicy(String paymentPolicy) {
        this.paymentPolicy = paymentPolicy;
    }


    public boolean isWellness() {
        return wellness;
    }

    public void setWellness(boolean wellness) {
        this.wellness = wellness;
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


    public String getNextAction() {
        return nextAction;
    }

    public void setNextAction(String nextAction) {
        this.nextAction = nextAction;
    }


    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }
}
