package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 08-09-2018
* */

public class TherapyMasterMedicineTypeGetDTO {

    private Long therapyMasterMedicineTypeId;
    private Long medicineTypeMasterId;
    private String medicineTypeName;

    public Long getTherapyMasterMedicineTypeId() {
        return therapyMasterMedicineTypeId;
    }

    public void setTherapyMasterMedicineTypeId(Long therapyMasterMedicineTypeId) {
        this.therapyMasterMedicineTypeId = therapyMasterMedicineTypeId;
    }

    public Long getMedicineTypeMasterId() {
        return medicineTypeMasterId;
    }

    public void setMedicineTypeMasterId(Long medicineTypeMasterId) {
        this.medicineTypeMasterId = medicineTypeMasterId;
    }

    public String getMedicineTypeName() {
        return medicineTypeName;
    }

    public void setMedicineTypeName(String medicineTypeName) {
        this.medicineTypeName = medicineTypeName;
    }
}
