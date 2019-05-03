package com.erx.microservice.patientmanagement.domain;

/*
* created by Latha on 06-12-2017
* */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "patient_type")
public class PatientType extends BaseModel {

    // @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @Column(name = "clinic_location_id", nullable = false)
    private Long clinicLocationId;

    @NotNull
    @Size(min = 3, max = 45)
    @Column(name = "patient_type_code")
    private String patientTypeCode;

    @NotNull
    @Size(min = 3, max = 255)
    @Column(name = "patient_type_name")
    private String patientTypeName;

    @Column(name = "status")
    private boolean status;

    @NotNull
    @Column(name = "sequence_order")
    private long sequenceOrder;

    @NotNull
    @Column(name = "patient_type_value")
    private double patientTypeValue;

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public String getPatientTypeCode() {
        return patientTypeCode;
    }

    public void setPatientTypeCode(String patientTypeCode) {
        this.patientTypeCode = patientTypeCode;
    }

    public String getPatientTypeName() {
        return patientTypeName;
    }

    public void setPatientTypeName(String patientTypeName) {
        this.patientTypeName = patientTypeName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getSequenceOrder() {
        return sequenceOrder;
    }

    public void setSequenceOrder(long sequenceOrder) {
        this.sequenceOrder = sequenceOrder;
    }

    public double getPatientTypeValue() {
        return patientTypeValue;
    }

    public void setPatientTypeValue(double patientTypeValue) {
        this.patientTypeValue = patientTypeValue;
    }
}
