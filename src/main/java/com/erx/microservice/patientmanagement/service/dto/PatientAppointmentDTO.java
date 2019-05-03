package com.erx.microservice.patientmanagement.service.dto;

import java.time.LocalDateTime;

/**
 * Created by Latha on 05/01/18.
 */

public class PatientAppointmentDTO {

    private String visitId;
    private LocalDateTime vistedDate;
    private long serviceCatalogueId;
    private  String doctorName;

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public LocalDateTime getVistedDate() {
        return vistedDate;
    }

    public void setVistedDate(LocalDateTime vistedDate) {
        this.vistedDate = vistedDate;
    }

    public long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
