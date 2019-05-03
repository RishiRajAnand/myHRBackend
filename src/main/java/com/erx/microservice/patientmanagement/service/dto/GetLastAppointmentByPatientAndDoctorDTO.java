package com.erx.microservice.patientmanagement.service.dto;

/*
* created by Brighty on 02-07-2018
* */

public class GetLastAppointmentByPatientAndDoctorDTO {

    private Long patientAppointmentId;

    private Long serviceId;

    private String serviceName;

    private String slotDate;

    private long duration; //duration = duration between slotDate to now.in days.

    //Getters and setters

    public Long getPatientAppointmentId() {
        return patientAppointmentId;
    }

    public void setPatientAppointmentId(Long patientAppointmentId) {
        this.patientAppointmentId = patientAppointmentId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(String slotDate) {
        this.slotDate = slotDate;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
