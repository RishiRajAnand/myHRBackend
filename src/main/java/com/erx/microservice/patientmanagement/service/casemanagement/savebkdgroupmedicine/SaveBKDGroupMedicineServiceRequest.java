package com.erx.microservice.patientmanagement.service.casemanagement.savebkdgroupmedicine;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveBKDGroupMedicineDTO;

public class SaveBKDGroupMedicineServiceRequest implements CommonServiceRequest {

    private SaveBKDGroupMedicineDTO saveBKDGroupMedicineDTO;

    //constructor

    public SaveBKDGroupMedicineServiceRequest(SaveBKDGroupMedicineDTO saveBKDGroupMedicineDTO) {
        this.saveBKDGroupMedicineDTO = saveBKDGroupMedicineDTO;
    }

    public SaveBKDGroupMedicineServiceRequest() {
    }

    //getters and setters

    public SaveBKDGroupMedicineDTO getSaveBKDGroupMedicineDTO() {
        return saveBKDGroupMedicineDTO;
    }

    public void setSaveBKDGroupMedicineDTO(SaveBKDGroupMedicineDTO saveBKDGroupMedicineDTO) {
        this.saveBKDGroupMedicineDTO = saveBKDGroupMedicineDTO;
    }
}
