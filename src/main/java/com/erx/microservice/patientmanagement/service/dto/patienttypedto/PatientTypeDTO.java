package com.erx.microservice.patientmanagement.service.dto.patienttypedto;

/*
* created by Brighty on 09-11-2017
* */

public class PatientTypeDTO {

    private Long id;

    // private ClinicLocationDTO clinicLocation;

    private String patientTypeCode;

    private String patientTypeName;

    private boolean status;

    private Long sequenceOrder;

    private double patientTypeValue;

    private Long clinicLocationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  /*  public ClinicLocationDTO getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocationDTO clinicLocation) {
        this.clinicLocation = clinicLocation;
    }*/

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

    public Long getSequenceOrder() {
        return sequenceOrder;
    }

    public void setSequenceOrder(Long sequenceOrder) {
        this.sequenceOrder = sequenceOrder;
    }

    public double getPatientTypeValue() {
        return patientTypeValue;
    }

    public void setPatientTypeValue(double patientTypeValue) {
        this.patientTypeValue = patientTypeValue;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
