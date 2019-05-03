package com.erx.microservice.patientmanagement.domain.casemanagement;
/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.Clinic;

import javax.persistence.*;

@Entity
@Table(name = "cm_observation_category_data")
public class CmObservationCategoryData extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "clinic_id", nullable=true)
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cm_observation_category_id", nullable=false)
    private CmObservationCategory cmObservationCategory;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cm_observation_data_id", nullable=false)
    private CmObservationData cmObservationData;

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public CmObservationCategory getCmObservationCategory() {
        return cmObservationCategory;
    }

    public void setCmObservationCategory(CmObservationCategory cmObservationCategory) {
        this.cmObservationCategory = cmObservationCategory;
    }

    public CmObservationData getCmObservationData() {
        return cmObservationData;
    }

    public void setCmObservationData(CmObservationData cmObservationData) {
        this.cmObservationData = cmObservationData;
    }
}
