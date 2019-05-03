package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cm_exam_general")
public class CmExamntnGeneral extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "conjuctiva_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData conjuctiva;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "tongue_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData tongue;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "nails_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData nails;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "pulse_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData pulse;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "skin_id", nullable=true)
    @JsonIgnore
    private CmObservationCategoryData skin;

    @Column(name="blood_pressure", nullable=true)
    private String bloodPressure;

    public CmObservationCategoryData getConjuctiva() {
        return conjuctiva;
    }

    public void setConjuctiva(CmObservationCategoryData conjuctiva) {
        this.conjuctiva = conjuctiva;
    }

    public CmObservationCategoryData getTongue() {
        return tongue;
    }

    public void setTongue(CmObservationCategoryData tongue) {
        this.tongue = tongue;
    }

    public CmObservationCategoryData getNails() {
        return nails;
    }

    public void setNails(CmObservationCategoryData nails) {
        this.nails = nails;
    }

    public CmObservationCategoryData getPulse() {
        return pulse;
    }

    public void setPulse(CmObservationCategoryData pulse) {
        this.pulse = pulse;
    }

    public CmObservationCategoryData getSkin() {
        return skin;
    }

    public void setSkin(CmObservationCategoryData skin) {
        this.skin = skin;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }
}
