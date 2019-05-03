package com.erx.microservice.patientmanagement.service.dto.therapymanagementdto;

/*
* created by Latha on 04-09-2018
* */

public class SaveTherapyMasterMedicineTypeDTO {

    private Long therapyMasterMedicineTypeId;
    private Long medicineTypeMasterId;

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
}
