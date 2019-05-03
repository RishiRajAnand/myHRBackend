package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.LookupValue;

import javax.persistence.*;

@Entity
@Table(name = "cm_observation_category")
public class CmObservationCategory extends BaseModel{

    @OneToOne
    @JoinColumn(name = "lookup_value_id")
    private LookupValue lookupValue;

    @Column(name="category_name", nullable=false)
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public LookupValue getLookupValue() {
        return lookupValue;
    }

    public void setLookupValue(LookupValue lookupValue) {
        this.lookupValue = lookupValue;
    }
}
