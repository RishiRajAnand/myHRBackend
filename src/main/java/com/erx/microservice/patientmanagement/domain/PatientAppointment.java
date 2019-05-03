package com.erx.microservice.patientmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/*
* created by latha on 04-12-2018
* */

@Entity
@Table(name = "patient_appointment")
public class PatientAppointment extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = true)
    @JsonIgnore
    private Patient patient;

    @Column(name = "generated_pa_id")
    private String generatedPatId;

    @Column(name = "running_no")
    private long runningNo;

    @Column(name = "appointment_type")
    private String appointmentType;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "appointment_status")
    private String appointmentStatus;

    @Column(name = "comments")
    private String comments;
   /* private VisitTypeMaster visitTypeMaster;*/

    @Column(name = "bm_service_catalogue_id")
    private Long serviceCatalogueId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_slot_id", nullable = true)
    private DoctorSlot doctorSlot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_type_master_id", nullable = true)
    private VisitTypeMaster visitTypeMaster;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getGeneratedPatId() {
        return generatedPatId;
    }

    public void setGeneratedPatId(String generatedPatId) {
        this.generatedPatId = generatedPatId;
    }

    public long getRunningNo() {
        return runningNo;
    }

    public void setRunningNo(long runningNo) {
        this.runningNo = runningNo;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public DoctorSlot getDoctorSlot() {
        return doctorSlot;
    }

    public void setDoctorSlot(DoctorSlot doctorSlot) {
        this.doctorSlot = doctorSlot;
    }

    public VisitTypeMaster getVisitTypeMaster() {
        return visitTypeMaster;
    }

    public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
        this.visitTypeMaster = visitTypeMaster;
    }
}
