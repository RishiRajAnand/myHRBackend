package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 30-05-2018
* */

import java.util.List;

public class SourceFrequencyWeb {

    private String clinicLocationId;

    private String timeOfTheDay;

    private List<Integer> day;

    private String cutOffOrSlot;

    private String patientId;

    //Getters and setters

    public String getTimeOfTheDay() {
        return timeOfTheDay;
    }

    public void setTimeOfTheDay(String timeOfTheDay) {
        this.timeOfTheDay = timeOfTheDay;
    }

    public List<Integer> getDay() {
        return day;
    }

    public void setDay(List<Integer> day) {
        this.day = day;
    }

    public String getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(String clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public String getCutOffOrSlot() {
        return cutOffOrSlot;
    }

    public void setCutOffOrSlot(String cutOffOrSlot) {
        this.cutOffOrSlot = cutOffOrSlot;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "SourceFrequencyWeb{" +
                "clinicLocationId='" + clinicLocationId + '\'' +
                ", timeOfTheDay='" + timeOfTheDay + '\'' +
                ", day=" + day +
                ", cutOffOrSlot='" + cutOffOrSlot + '\'' +
                ", patientId=" + patientId +
                '}';
    }
}
