package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cm_master_detail")

public class CmMasterDetails extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "cm_master_id", nullable=false)
    private CmMaster cmMaster;

    @Column(name = "case_created_date", nullable = true)
    private LocalDateTime caseCreatedDate;

    @Column(nullable=true, name="doctor_note")
    private String doctorNote;

    @Column(nullable=true, name="bm_order_id")
    private Long bmOrderId;

    @Column(nullable=true, name="patient_appointment_id")
    private Long patientAppointmentId;

    @OneToMany(mappedBy = "cmMasterDetails",fetch = FetchType.LAZY)
    private List<CmInvestigation> cmInvestigations;

    @OneToMany(mappedBy = "cmMasterDetails",fetch = FetchType.LAZY)
    private List<CmTreatment> cmTreatments;

    public CmMaster getCmMaster() {
        return cmMaster;
    }

    public void setCmMaster(CmMaster cmMaster) {
        this.cmMaster = cmMaster;
    }

    public LocalDateTime getCaseCreatedDate() {
        return caseCreatedDate;
    }

    public void setCaseCreatedDate(LocalDateTime caseCreatedDate) {
        this.caseCreatedDate = caseCreatedDate;
    }

    public String getDoctorNote() {
        return doctorNote;
    }

    public void setDoctorNote(String doctorNote) {
        this.doctorNote = doctorNote;
    }

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public Long getPatientAppointmentId() {
        return patientAppointmentId;
    }

    public void setPatientAppointmentId(Long patientAppointmentId) {
        this.patientAppointmentId = patientAppointmentId;
    }

    public List<CmInvestigation> getCmInvestigations() {
        return cmInvestigations;
    }

    public void setCmInvestigations(List<CmInvestigation> cmInvestigations) {
        this.cmInvestigations = cmInvestigations;
    }

    public List<CmTreatment> getCmTreatments() {
        return cmTreatments;
    }

    public void setCmTreatments(List<CmTreatment> cmTreatments) {
        this.cmTreatments = cmTreatments;
    }
}
