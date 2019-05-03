package com.erx.microservice.patientmanagement.domain;

/*
* created by latha on 04-12-2018
* */

import javax.persistence.*;

@Entity
@Table(name = "patient_camp_registration")
public class PatientCampRegistration extends BaseModel {

    @Column(name = "camp_registration_number")
    private String campRegistrationNumber;

    //@ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "package_catalogue_id")
    private Long packageCatalogueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "case_id")
    private String caseId;

    //Getters and setters

    public String getCampRegistrationNumber() {
        return campRegistrationNumber;
    }

    public void setCampRegistrationNumber(String campRegistrationNumber) {
        this.campRegistrationNumber = campRegistrationNumber;
    }

    public Long getPackageCatalogueId() {
        return packageCatalogueId;
    }

    public void setPackageCatalogueId(Long packageCatalogueId) {
        this.packageCatalogueId = packageCatalogueId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
}
