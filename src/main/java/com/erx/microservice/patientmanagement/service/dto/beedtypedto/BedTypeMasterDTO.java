package com.erx.microservice.patientmanagement.service.dto.beedtypedto;

/*
 * created by Brighty on 16-11-2017
 * */

public class BedTypeMasterDTO {

    private Long id;

    private Long clinicLocationId;

    private String bedTypeCode;

    private String bedTypeName;

    private double rate;

    private boolean status;

    private boolean isDaycare;

    private Long sequenceOrderNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
