package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 12-09-2018
* */

public class TherapyTemplateMedicineTypeGetDTO {

    private Long therapyTemplateMedicineTypeId;
    private Long medicineTypeId;
    private String medicineTypeName;

    public Long getTherapyTemplateMedicineTypeId() {
        return therapyTemplateMedicineTypeId;
    }

    public void setTherapyTemplateMedicineTypeId(Long therapyTemplateMedicineTypeId) {
        this.therapyTemplateMedicineTypeId = therapyTemplateMedicineTypeId;
    }

    public Long getMedicineTypeId() {
        return medicineTypeId;
    }

    public void setMedicineTypeId(Long medicineTypeId) {
        this.medicineTypeId = medicineTypeId;
    }

    public String getMedicineTypeName() {
        return medicineTypeName;
    }

    public void setMedicineTypeName(String medicineTypeName) {
        this.medicineTypeName = medicineTypeName;
    }
}
