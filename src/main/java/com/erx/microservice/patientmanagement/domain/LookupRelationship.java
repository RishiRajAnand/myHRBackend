package com.erx.microservice.patientmanagement.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lookup_relationship")
public class LookupRelationship extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "primary_lookup_value_id", nullable = false)
    private LookupValue primaryLookupValueId;
    @ManyToOne
    @JoinColumn(name = "child_lookup_value_id", nullable = false)
    private LookupValue childLookupValueId;

    public LookupValue getPrimaryLookupValueId() {
        return primaryLookupValueId;
    }

    public void setPrimaryLookupValueId(LookupValue primaryLookupValueId) {
        this.primaryLookupValueId = primaryLookupValueId;
    }

    public LookupValue getChildLookupValueId() {
        return childLookupValueId;
    }

    public void setChildLookupValueId(LookupValue childLookupValueId) {
        this.childLookupValueId = childLookupValueId;
    }
}
