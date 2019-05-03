package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 07-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "room_type")
public class TherapyRoomType extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    @JsonIgnore
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "clinic_location_id", nullable = true)
    @JsonIgnore
    private ClinicLocation clinicLocation;

    @Column(name="type_name")
    private String typeName;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }

    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
