package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 12-09-2018
* */

import java.util.List;

public class SaveTherapyTemplateResponseDTO {

    private Long therapyTemplateId;
    private Long therapyTemplateMappingId;
    private List<Long> therapyTemplateMedicineIds;
    private List<Long> therapyTemplateMedicineTypeIds;

    public Long getTherapyTemplateId() {
        return therapyTemplateId;
    }

    public void setTherapyTemplateId(Long therapyTemplateId) {
        this.therapyTemplateId = therapyTemplateId;
    }

    public Long getTherapyTemplateMappingId() {
        return therapyTemplateMappingId;
    }

    public void setTherapyTemplateMappingId(Long therapyTemplateMappingId) {
        this.therapyTemplateMappingId = therapyTemplateMappingId;
    }

    public List<Long> getTherapyTemplateMedicineIds() {
        return therapyTemplateMedicineIds;
    }

    public void setTherapyTemplateMedicineIds(List<Long> therapyTemplateMedicineIds) {
        this.therapyTemplateMedicineIds = therapyTemplateMedicineIds;
    }

    public List<Long> getTherapyTemplateMedicineTypeIds() {
        return therapyTemplateMedicineTypeIds;
    }

    public void setTherapyTemplateMedicineTypeIds(List<Long> therapyTemplateMedicineTypeIds) {
        this.therapyTemplateMedicineTypeIds = therapyTemplateMedicineTypeIds;
    }
}
