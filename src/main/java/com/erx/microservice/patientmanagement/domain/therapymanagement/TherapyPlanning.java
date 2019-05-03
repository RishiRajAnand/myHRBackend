package com.erx.microservice.patientmanagement.domain.therapymanagement;
/*
* created by raushan on 28-08-2018
* */
import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tm_therapy_planning")
public class TherapyPlanning extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "case_id")
    private CmMaster cmMaster;

    @ManyToOne
    @JoinColumn(name = "tm_therapy_master_id")
    private TherapyMaster therapyMaster;

    @Column(name = "bm_service_catalogue_id")
    private Long serviceCatalogueId;

    @Column(name = "num_days")
    private int numberOfDays;

    @Column(name = "num_therapies")
    private long numberOfTherapists;

    @Column(name = "quantity")
    private String medQuantity;

    @Column(name = "room_required")
    private boolean roomRequired;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "periodic_interval")
    private long periodicInterval; //repeat after

    @Column(name = "therapies_interval")
    private long therapiesInterval; //start after

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "therapy_start_from", nullable = false)
    private LocalDateTime therapyStartDate;

    @Column(name = "therapy_end_on", nullable = false)
    private LocalDateTime therapyEndDate;

    @Column(name = "therapy_schedule_from", nullable = false)
    private LocalDateTime therapyScheduleDate;

    @Column(name = "is_wellness")
    private boolean wellness;

    @OneToMany(mappedBy = "therapyPlanning")
    private List<TherapyPlanningMedicine> therapyPlanningMedicine;

    @Column(nullable=true, name="bm_order_id")
    private Long bmOrderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cm_master_detail_id", nullable=false)
    @JsonIgnore
    private CmMasterDetails cmMasterDetails;

    public TherapyPlanning() {
        this.numberOfTherapists = 0;
        this.roomRequired = false;
        this.periodicInterval = 1;
        this.therapiesInterval = 0;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public CmMaster getCmMaster() {
        return cmMaster;
    }

    public void setCmMaster(CmMaster cmMaster) {
        this.cmMaster = cmMaster;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public long getPeriodicInterval() {
        return periodicInterval;
    }

    public void setPeriodicInterval(long periodicInterval) {
        this.periodicInterval = periodicInterval;
    }

    public long getTherapiesInterval() {
        return therapiesInterval;
    }

    public void setTherapiesInterval(long therapiesInterval) {
        this.therapiesInterval = therapiesInterval;
    }

    public boolean isWellness() {
        return wellness;
    }

    public void setWellness(boolean wellness) {
        this.wellness = wellness;
    }

    public long getNumberOfTherapists() {
        return numberOfTherapists;
    }

    public void setNumberOfTherapists(long numberOfTherapists) {
        this.numberOfTherapists = numberOfTherapists;
    }

    public boolean isRoomRequired() {
        return roomRequired;
    }

    public void setRoomRequired(boolean roomRequired) {
        this.roomRequired = roomRequired;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public TherapyMaster getTherapyMaster() {
        return therapyMaster;
    }

    public void setTherapyMaster(TherapyMaster therapyMaster) {
        this.therapyMaster = therapyMaster;
    }

    public String getMedQuantity() {
        return medQuantity;
    }

    public void setMedQuantity(String medQuantity) {
        this.medQuantity = medQuantity;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public LocalDateTime getTherapyStartDate() {
        return therapyStartDate;
    }

    public void setTherapyStartDate(LocalDateTime therapyStartDate) {
        this.therapyStartDate = therapyStartDate;
    }

    public LocalDateTime getTherapyEndDate() {
        return therapyEndDate;
    }

    public void setTherapyEndDate(LocalDateTime therapyEndDate) {
        this.therapyEndDate = therapyEndDate;
    }

    public LocalDateTime getTherapyScheduleDate() {
        return therapyScheduleDate;
    }

    public void setTherapyScheduleDate(LocalDateTime therapyScheduleDate) {
        this.therapyScheduleDate = therapyScheduleDate;
    }

    public List<TherapyPlanningMedicine> getTherapyPlanningMedicine() {
        return therapyPlanningMedicine;
    }

    public void setTherapyPlanningMedicine(List<TherapyPlanningMedicine> therapyPlanningMedicine) {
        this.therapyPlanningMedicine = therapyPlanningMedicine;
    }

    public Long getBmOrderId() {
        return bmOrderId;
    }

    public void setBmOrderId(Long bmOrderId) {
        this.bmOrderId = bmOrderId;
    }

    public CmMasterDetails getCmMasterDetails() {
        return cmMasterDetails;
    }

    public void setCmMasterDetails(CmMasterDetails cmMasterDetails) {
        this.cmMasterDetails = cmMasterDetails;
    }
}
