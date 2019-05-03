package com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicinesbyid;

/*
* created by Latha on 31-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.BkdGroupMedicineDTO;

public class GetBkdGroupMedicineByIdServiceResponse extends CommonServiceResponse {

    private BkdGroupMedicineDTO bkdGroupMedicineDTO;

    //constructor

    public GetBkdGroupMedicineByIdServiceResponse() {
    }

    public GetBkdGroupMedicineByIdServiceResponse(BkdGroupMedicineDTO bkdGroupMedicineDTO) {
        this.bkdGroupMedicineDTO = bkdGroupMedicineDTO;
    }

    //getters and setters

    public BkdGroupMedicineDTO getBkdGroupMedicineDTO() {
        return bkdGroupMedicineDTO;
    }

    public void setBkdGroupMedicineDTO(BkdGroupMedicineDTO bkdGroupMedicineDTO) {
        this.bkdGroupMedicineDTO = bkdGroupMedicineDTO;
    }
}
