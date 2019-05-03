package com.erx.microservice.patientmanagement.service.casemanagement.savepersonalhistory;

/*
* created by Latha on 21-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmPersonalHistoryDTO;

public class SavePersonalHistoryServiceResponse extends CommonServiceResponse {

    private CmPersonalHistoryDTO cmPersonalHistoryDTO;

    //constructor

    public SavePersonalHistoryServiceResponse() {
    }

    public SavePersonalHistoryServiceResponse(CmPersonalHistoryDTO cmPersonalHistoryDTO) {
        this.cmPersonalHistoryDTO = cmPersonalHistoryDTO;
    }

    //getters and setters

    public CmPersonalHistoryDTO getCmPersonalHistoryDTO() {
        return cmPersonalHistoryDTO;
    }

    public void setCmPersonalHistoryDTO(CmPersonalHistoryDTO cmPersonalHistoryDTO) {
        this.cmPersonalHistoryDTO = cmPersonalHistoryDTO;
    }
}
