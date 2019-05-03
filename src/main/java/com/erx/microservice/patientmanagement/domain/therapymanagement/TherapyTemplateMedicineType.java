package com.erx.microservice.patientmanagement.domain.therapymanagement;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;

@Entity
@Table(name="tm_therapy_template_medicine_type")
public class TherapyTemplateMedicineType extends BaseModel{

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="tm_therapy_template_mapping_id")
    private TherapyTemplateMapping therapyTemplateMapping;

    @Column(name="medicine_type_id")
    private Long medicineTypeId;

    public TherapyTemplateMapping getTherapyTemplateMapping() {
        return therapyTemplateMapping;
    }

    public void setTherapyTemplateMapping(TherapyTemplateMapping therapyTemplateMapping) {
        this.therapyTemplateMapping = therapyTemplateMapping;
    }

    public Long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }
}
