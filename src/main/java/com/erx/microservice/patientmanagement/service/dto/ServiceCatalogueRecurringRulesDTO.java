package com.erx.microservice.patientmanagement.service.dto;

/*
* created by Brighty on 04-06-2018
* */

public class ServiceCatalogueRecurringRulesDTO {

    private Long id;

    private Long clinicLocationId;

    private String slotPeriod;

    private String cutOffPeriod;

    private String admissionGracePeriod;

    private String dischargeGracePeriod;

    private String halfDayPeriod;

    //private boolean transferBillMaxStay;

    //private boolean transferBillHigherBedCharge;

    private Long userId;

    //Getters and setters

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

    public String getSlotPeriod() {
        return slotPeriod;
    }

    public void setSlotPeriod(String slotPeriod) {
        this.slotPeriod = slotPeriod;
    }

    public String getCutOffPeriod() {
        return cutOffPeriod;
    }

    public void setCutOffPeriod(String cutOffPeriod) {
        this.cutOffPeriod = cutOffPeriod;
    }

    public String getAdmissionGracePeriod() {
        return admissionGracePeriod;
    }

    public void setAdmissionGracePeriod(String admissionGracePeriod) {
        this.admissionGracePeriod = admissionGracePeriod;
    }

    public String getDischargeGracePeriod() {
        return dischargeGracePeriod;
    }

    public void setDischargeGracePeriod(String dischargeGracePeriod) {
        this.dischargeGracePeriod = dischargeGracePeriod;
    }

    public String getHalfDayPeriod() {
        return halfDayPeriod;
    }

    public void setHalfDayPeriod(String halfDayPeriod) {
        this.halfDayPeriod = halfDayPeriod;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
