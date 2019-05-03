package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import javax.persistence.*;

@Entity
@Table(name="tm_therapy_template_mapping")
public class TherapyTemplateMapping extends BaseModel{

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name="tm_therapy_template_id")
    private TherapyTemplate therapyTemplate;

    @Column(name = "bm_service_catalogue_id")
    private Long serviceCatalogueId;

    @Column(name="num_days")
    private int numberOfDays;

    @Column(name="instructions")
    private String instructions;

    public TherapyTemplate getTherapyTemplate() {
        return therapyTemplate;
    }

    public void setTherapyTemplate(TherapyTemplate therapyTemplate) {
        this.therapyTemplate = therapyTemplate;
    }

    public Long getServiceCatalogueId() {
        return serviceCatalogueId;
    }

    public void setServiceCatalogueId(Long serviceCatalogueId) {
        this.serviceCatalogueId = serviceCatalogueId;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
