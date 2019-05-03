package com.erx.microservice.patientmanagement.service.casemanagement.savebkdgroupmedicine;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;

public class SaveBKDGroupMedicineServiceResponse extends CommonServiceResponse {

    private Long cmGroupMedicineId;

    //constructor
    public SaveBKDGroupMedicineServiceResponse(Long cmGroupMedicineId) {
        this.cmGroupMedicineId = cmGroupMedicineId;
    }

    public SaveBKDGroupMedicineServiceResponse() {
    }

    //getters and setters

    public Long getCmGroupMedicineId() {
        return cmGroupMedicineId;
    }

    public void setCmGroupMedicineId(Long cmGroupMedicineId) {
        this.cmGroupMedicineId = cmGroupMedicineId;
    }
}
