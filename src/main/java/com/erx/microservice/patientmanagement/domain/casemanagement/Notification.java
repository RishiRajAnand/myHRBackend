package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 10-10-2018
* */

import com.erx.microservice.patientmanagement.domain.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "notification_type_master_id", nullable=false)
    private NotificationTypeMaster notification_type_master;

    @Column(name="notification_transaction_id", nullable=true)
    private long notification_transaction_id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable=false)
    private UserStaff user;

    @Column(name = "notification_date", nullable = false)
    private LocalDateTime notification_date;

    @Column(name = "notification_status", nullable = false)
    private String notification_status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "clinic_location_id", nullable=false)
    private ClinicLocation clinicLocation;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "user_detail_id", nullable=true)
    private UserDetail userDetail;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "patient_appointment_id", nullable=true)
    private PatientAppointment patientAppointment;

    public NotificationTypeMaster getNotification_type_master() {
        return notification_type_master;
    }

    public void setNotification_type_master(NotificationTypeMaster notification_type_master) {
        this.notification_type_master = notification_type_master;
    }

    public long getNotification_transaction_id() {
        return notification_transaction_id;
    }

    public void setNotification_transaction_id(long notification_transaction_id) {
        this.notification_transaction_id = notification_transaction_id;
    }

    public UserStaff getUser() {
        return user;
    }

    public void setUser(UserStaff user) {
        this.user = user;
    }

    public LocalDateTime getNotification_date() {
        return notification_date;
    }

    public void setNotification_date(LocalDateTime notification_date) {
        this.notification_date = notification_date;
    }

    public String getNotification_status() {
        return notification_status;
    }

    public void setNotification_status(String notification_status) {
        this.notification_status = notification_status;
    }

    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public PatientAppointment getPatientAppointment() {
        return patientAppointment;
    }

    public void setPatientAppointment(PatientAppointment patientAppointment) {
        this.patientAppointment = patientAppointment;
    }
}
