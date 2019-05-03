package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 07-12-17
* */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bm_service_bed_type_rate_plan")
public class ServiceBedTypeRatePlan extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "billing_catalouge_service_id", nullable = false)
    private ServiceCatalogue serviceCatalogue;

    @ManyToOne
    @JoinColumn(name = "patient_type_id", nullable = false)
    private PatientType patientType;

    @ManyToOne
    @JoinColumn(name = "bed_type_id", nullable = false)
    private BedTypeMaster bedType;

    @NotNull
    @Column(name = "rate")
    private double rate;

    @NotNull
    @Column(name = "charges")
    private double charges;


    public ServiceCatalogue getServiceCatalogue() {
        return serviceCatalogue;
    }

    public void setServiceCatalogue(ServiceCatalogue serviceCatalogue) {
        this.serviceCatalogue = serviceCatalogue;
    }

    public PatientType getPatientType() {
        return patientType;
    }

    public void setPatientType(PatientType patientType) {
        this.patientType = patientType;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public BedTypeMaster getBedType() {
        return bedType;
    }

    public void setBedType(BedTypeMaster bedType) {
        this.bedType = bedType;
    }
}
