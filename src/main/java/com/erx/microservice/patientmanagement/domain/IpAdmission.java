package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 28-11-2017
* */


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ip_admission")
public class IpAdmission extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "ip_admission_number")
    private String ipAdmissionNumber;

    @Column(name = "daycare_admission_number")
    private String dayCareAdmissionNumber;

    @Column(name = "admission_on", nullable = false)
    private LocalDateTime admissionOn;

    @Column(name = "ip_admission_notes", nullable = true)
    private String ipAdmissionNotes;

    @Column(nullable = true, name = "discharged_on")
    private LocalDateTime dischargedOn;

    @ManyToOne
    @JoinColumn(name = "user_detail_id", nullable = true)
    private UserDetail userDetail;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "bed_id", nullable = true)
    private BedConfigurationMaster bedMaster;
    /*  @OneToOne(mappedBy = "ipAdmission", fetch = FetchType.EAGER)
      private IpAdmissionCaseMapping ipAdmissionCaseMapping;*/
    @Column(name = "ip_admission_status")
    private String ipAdmissionStatus;
    /* @OneToOne(mappedBy = "ipAdmission", fetch = FetchType.EAGER)
     private CM_Master cmMaster;*/
    @Column(name = "is_day_care")
    private boolean dayCare;

    @Column(name = "visit_type_master_id", nullable = true)
    private Long visitTypeMasterId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ipAdmission")
    private List<IpAdmissionBedMovement> ipAdmissionBedMovements;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "ipAdmission")
    private IpAdmissionCaseMapping ipAdmissionCaseMapping;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getIpAdmissionNumber() {
        return ipAdmissionNumber;
    }

    public void setIpAdmissionNumber(String ipAdmissionNumber) {
        this.ipAdmissionNumber = ipAdmissionNumber;
    }

    public String getDayCareAdmissionNumber() {
        return dayCareAdmissionNumber;
    }

    public void setDayCareAdmissionNumber(String dayCareAdmissionNumber) {
        this.dayCareAdmissionNumber = dayCareAdmissionNumber;
    }

    public LocalDateTime getAdmissionOn() {
        return admissionOn;
    }

    public void setAdmissionOn(LocalDateTime admissionOn) {
        this.admissionOn = admissionOn;
    }

    public String getIpAdmissionNotes() {
        return ipAdmissionNotes;
    }

    public void setIpAdmissionNotes(String ipAdmissionNotes) {
        this.ipAdmissionNotes = ipAdmissionNotes;
    }

    public LocalDateTime getDischargedOn() {
        return dischargedOn;
    }

    public void setDischargedOn(LocalDateTime dischargedOn) {
        this.dischargedOn = dischargedOn;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public BedConfigurationMaster getBedMaster() {
        return bedMaster;
    }

    public void setBedMaster(BedConfigurationMaster bedMaster) {
        this.bedMaster = bedMaster;
    }

    public String getIpAdmissionStatus() {
        return ipAdmissionStatus;
    }

    public void setIpAdmissionStatus(String ipAdmissionStatus) {
        this.ipAdmissionStatus = ipAdmissionStatus;
    }

    public boolean isDayCare() {
        return dayCare;
    }

    public void setDayCare(boolean dayCare) {
        this.dayCare = dayCare;
    }

    public Long getVisitTypeMasterId() {
        return visitTypeMasterId;
    }

    public void setVisitTypeMasterId(Long visitTypeMasterId) {
        this.visitTypeMasterId = visitTypeMasterId;
    }

    public List<IpAdmissionBedMovement> getIpAdmissionBedMovements() {
        return ipAdmissionBedMovements;
    }

    public void setIpAdmissionBedMovements(List<IpAdmissionBedMovement> ipAdmissionBedMovements) {
        this.ipAdmissionBedMovements = ipAdmissionBedMovements;
    }

    public IpAdmissionCaseMapping getIpAdmissionCaseMapping() {
        return ipAdmissionCaseMapping;
    }

    public void setIpAdmissionCaseMapping(IpAdmissionCaseMapping ipAdmissionCaseMapping) {
        this.ipAdmissionCaseMapping = ipAdmissionCaseMapping;
    }
}
