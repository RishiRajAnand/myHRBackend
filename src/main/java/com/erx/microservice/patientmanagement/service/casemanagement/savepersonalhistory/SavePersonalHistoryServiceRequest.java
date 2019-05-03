package com.erx.microservice.patientmanagement.service.casemanagement.savepersonalhistory;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveCmPersonalHistoryDTO;

public class SavePersonalHistoryServiceRequest implements CommonServiceRequest {

    private SaveCmPersonalHistoryDTO saveCmPersonalHistoryDTO;

    //constructor

    public SavePersonalHistoryServiceRequest() {
    }

    public SavePersonalHistoryServiceRequest(SaveCmPersonalHistoryDTO saveCmPersonalHistoryDTO) {
        this.saveCmPersonalHistoryDTO = saveCmPersonalHistoryDTO;
    }

    //getters and setters

    public SaveCmPersonalHistoryDTO getSaveCmPersonalHistoryDTO() {
        return saveCmPersonalHistoryDTO;
    }

    public void setSaveCmPersonalHistoryDTO(SaveCmPersonalHistoryDTO saveCmPersonalHistoryDTO) {
        this.saveCmPersonalHistoryDTO = saveCmPersonalHistoryDTO;
    }
}
