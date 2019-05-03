package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 28-11-2017
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "clinic_location_detail")
public class ClinicLocationDetail extends BaseModel{

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_location_id")
    @JsonIgnore
    private ClinicLocation clinicLocation;

    @Column(name = "case_id_format", nullable = true)
    private String caseIdFormat;

    @Column(name = "initial_case_id", nullable = true)
    private String initialCaseId;

    @Column(name = "last_case_id", nullable = true)
    private String lastCaseId;

    @Column(name = "lab_order_format", nullable = true)
    private String labOrderFormat;

    @Column(name = "initial_lab_order", nullable = true)
    private String initialLabOrder;

    @Column(name = "last_lab_order", nullable = true)
    private String lastLabOrder;

    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public String getCaseIdFormat() {
        return caseIdFormat;
    }

    public void setCaseIdFormat(String caseIdFormat) {
        this.caseIdFormat = caseIdFormat;
    }

    public String getInitialCaseId() {
        return initialCaseId;
    }

    public void setInitialCaseId(String initialCaseId) {
        this.initialCaseId = initialCaseId;
    }

    public String getLastCaseId() {
        return lastCaseId;
    }

    public void setLastCaseId(String lastCaseId) {
        this.lastCaseId = lastCaseId;
    }

    public String getLabOrderFormat() {
        return labOrderFormat;
    }

    public void setLabOrderFormat(String labOrderFormat) {
        this.labOrderFormat = labOrderFormat;
    }

    public String getInitialLabOrder() {
        return initialLabOrder;
    }

    public void setInitialLabOrder(String initialLabOrder) {
        this.initialLabOrder = initialLabOrder;
    }

    public String getLastLabOrder() {
        return lastLabOrder;
    }

    public void setLastLabOrder(String lastLabOrder) {
        this.lastLabOrder = lastLabOrder;
    }
}
