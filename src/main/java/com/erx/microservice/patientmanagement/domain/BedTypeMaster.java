package com.erx.microservice.patientmanagement.domain;
/*
* created by Latha on 28-11-2017
* */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bed_type_master")
public class BedTypeMaster extends BaseModel {

    //@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @Column(name = "clinic_location_id", nullable = false)
    private Long clinicLocationId;

    @NotNull
    @Column(name = "bed_type_code")
    private String bedTypeCode;

    @NotNull
    @Column(name = "bed_type_name")
    private String bedTypeName;

    @NotNull
    @Column(name = "rate")
    private double rate;

    @NotNull
    @Column(name = "status")
    private boolean status;

    @NotNull
    @Column(name = "is_daycare")
    private boolean isDaycare;

    @NotNull
    @Column(name = "sequence_order",unique = true)
    private Long sequenceOrderNo;

    public BedTypeMaster() {
        this.status = true;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public String getBedTypeCode() {
        return bedTypeCode;
    }

    public void setBedTypeCode(String bedTypeCode) {
        this.bedTypeCode = bedTypeCode;
    }

    public String getBedTypeName() {
        return bedTypeName;
    }

    public void setBedTypeName(String bedTypeName) {
        this.bedTypeName = bedTypeName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDaycare() {
        return isDaycare;
    }

    public void setDaycare(boolean daycare) {
        isDaycare = daycare;
    }

    public Long getSequenceOrderNo() {
        return sequenceOrderNo;
    }

    public void setSequenceOrderNo(Long sequenceOrderNo) {
        this.sequenceOrderNo = sequenceOrderNo;
    }
}
