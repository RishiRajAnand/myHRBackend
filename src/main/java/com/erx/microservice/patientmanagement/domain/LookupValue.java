package com.erx.microservice.patientmanagement.domain;

import javax.persistence.*;

@Entity
@Table(name = "lookup_value")
public class LookupValue extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "lookup_id", nullable = false)
    private Lookup lookupId;

    @Column(name = "lookup_value", nullable = false)
    private String name;

    public Lookup getLookupId() {
        return lookupId;
    }

    public void setLookupId(Lookup lookupId) {
        this.lookupId = lookupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
