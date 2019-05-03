package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 27-03-2018
* */

import javax.persistence.*;

@Entity
@Table(name = "patient_uh_identifier")
public class PatientUhIdentifier extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "uh_identifier_id", nullable=true)
    private UhIdentifier uhIdentifier;

    @Column(name="identifier_number", nullable=true)
    private String identifier_number;

    @Column(name="identifier_image_format", nullable=true)
    private String identifierImageFormat;

    //Getters and setters

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public UhIdentifier getUhIdentifier() {
        return uhIdentifier;
    }

    public void setUhIdentifier(UhIdentifier uhIdentifier) {
        this.uhIdentifier = uhIdentifier;
    }

    public String getIdentifier_number() {
        return identifier_number;
    }

    public void setIdentifier_number(String identifier_number) {
        this.identifier_number = identifier_number;
    }

    public String getIdentifierImageFormat() {
        return identifierImageFormat;
    }

    public void setIdentifierImageFormat(String identifierImageFormat) {
        this.identifierImageFormat = identifierImageFormat;
    }
}
