package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 12-09-2018
* */

public class SaveTherapyTemplateMedicineTypeDTO {

    private Long therapyTemplateMedicineTypeId;
    private Long medicineTypeId;

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
}
