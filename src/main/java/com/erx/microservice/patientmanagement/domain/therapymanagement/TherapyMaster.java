package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.ServiceCatalogue;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "tm_therapy_master")
public class TherapyMaster extends BaseModel{

    @Column(name="therapy_name")
    private String therapyName;

    @Column(name="therapy_flag")
    private String therapyFlag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    @JsonIgnore
    private Clinic clinic;

    @Column(name = "bm_service_cataloge_id", nullable = false)
    private Long serviceCatalogueId;

    @Column(name="duration", nullable = false)
    private Long duration;

    @Column(name="number_therapist")
    private int numTherapist;

    @Column(name="therapy_group")
    private String therapyGroup;

    @Column(name="instructions")
    private String instructions;

    @Column(name="is_medicine_charged")
    private boolean medicineCharged;

    @Column(name = "uom_master_id")
    private Long uomMasterId;

    @Column(name = "therapy_department_id", nullable = false)
    private Long therapyDepartmentId;


    @OneToMany(mappedBy = "therapyMaster", fetch = FetchType.EAGER)
    private List<TherapyMasterRoomDetail> therapyMasterRoomDetails;

    @OneToMany(mappedBy = "therapyMaster", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TherapyMasterTherapistDetail> therapyMasterTherapistDetails;

    public String getTherapyName() {
        return therapyName;
    }

    public void setTherapyName(String therapyName) {
        this.therapyName = therapyName;
    }

    public String getTherapyFlag() {
        return therapyFlag;
    }

    public void setTherapyFlag(String therapyFlag) {
        this.therapyFlag = therapyFlag;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public int getNumTherapist() {
        return numTherapist;
    }

    public void setNumTherapist(int numTherapist) {
        this.numTherapist = numTherapist;
    }

    public String getTherapyGroup() {
        return therapyGroup;
    }

    public void setTherapyGroup(String therapyGroup) {
        this.therapyGroup = therapyGroup;
    }

    public boolean isMedicineCharged() {
        return medicineCharged;
    }

    public void setMedicineCharged(boolean medicineCharged) {
        this.medicineCharged = medicineCharged;
    }

    public Long getUomMasterId() {
        return uomMasterId;
    }

    public void setUomMasterId(Long uomMasterId) {
        this.uomMasterId = uomMasterId;
    }

    public Long getTherapyDepartmentId() {
        return therapyDepartmentId;
    }

    public void setTherapyDepartmentId(Long therapyDepartmentId) {
        this.therapyDepartmentId = therapyDepartmentId;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<TherapyMasterRoomDetail> getTherapyMasterRoomDetails() {
        return therapyMasterRoomDetails;
    }

    public void setTherapyMasterRoomDetails(List<TherapyMasterRoomDetail> therapyMasterRoomDetails) {
        this.therapyMasterRoomDetails = therapyMasterRoomDetails;
    }

    public List<TherapyMasterTherapistDetail> getTherapyMasterTherapistDetails() {
        return therapyMasterTherapistDetails;
    }

    public void setTherapyMasterTherapistDetails(List<TherapyMasterTherapistDetail> therapyMasterTherapistDetails) {
        this.therapyMasterTherapistDetails = therapyMasterTherapistDetails;
    }
}
