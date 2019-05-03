package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 17-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cm_investigation")
public class CmInvestigation extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cm_master_detail_id", nullable=false)
    @JsonIgnore
    private CmMasterDetails cmMasterDetails;

    @Column(name="lab_order_number", nullable=true)
    private String labOrderNumber;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name="doctor_summary", nullable=true)
    private String doctorSummary;

    @Column(name="is_final_diagnosis", nullable=true)
    private boolean finalDiagnosis;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "provisional_diagnosis_master_id", nullable=true)
    private ProvisionalDiagnosisMaster provisionalDiagnosisMaster;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "acd_master_id", nullable=true)
    private CmAcdMaster cmAcdMaster;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "icd_master_id", nullable=true)
    private CmIcdMaster cmIcdMaster;

    @OneToMany(mappedBy="cmInvestigation")
    private List<CmInvestigationDetail> cmInvestigationDetails;

    public CmMasterDetails getCmMasterDetails() {
        return cmMasterDetails;
    }

    public void setCmMasterDetails(CmMasterDetails cmMasterDetails) {
        this.cmMasterDetails = cmMasterDetails;
    }

    public String getLabOrderNumber() {
        return labOrderNumber;
    }

    public void setLabOrderNumber(String labOrderNumber) {
        this.labOrderNumber = labOrderNumber;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getDoctorSummary() {
        return doctorSummary;
    }

    public void setDoctorSummary(String doctorSummary) {
        this.doctorSummary = doctorSummary;
    }

    public boolean isFinalDiagnosis() {
        return finalDiagnosis;
    }

    public void setFinalDiagnosis(boolean finalDiagnosis) {
        this.finalDiagnosis = finalDiagnosis;
    }

    public ProvisionalDiagnosisMaster getProvisionalDiagnosisMaster() {
        return provisionalDiagnosisMaster;
    }

    public void setProvisionalDiagnosisMaster(ProvisionalDiagnosisMaster provisionalDiagnosisMaster) {
        this.provisionalDiagnosisMaster = provisionalDiagnosisMaster;
    }

    public CmAcdMaster getCmAcdMaster() {
        return cmAcdMaster;
    }

    public void setCmAcdMaster(CmAcdMaster cmAcdMaster) {
        this.cmAcdMaster = cmAcdMaster;
    }

    public CmIcdMaster getCmIcdMaster() {
        return cmIcdMaster;
    }

    public void setCmIcdMaster(CmIcdMaster cmIcdMaster) {
        this.cmIcdMaster = cmIcdMaster;
    }

    public List<CmInvestigationDetail> getCmInvestigationDetails() {
        return cmInvestigationDetails;
    }

    public void setCmInvestigationDetails(List<CmInvestigationDetail> cmInvestigationDetails) {
        this.cmInvestigationDetails = cmInvestigationDetails;
    }
}
