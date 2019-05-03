package com.erx.microservice.patientmanagement.service.casemanagement.savecasedischarge;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveDischargeDTO;

public class SaveCaseDischargeServiceRequest implements CommonServiceRequest {

    private SaveDischargeDTO saveDischargeDTO;

    //constructor

    public SaveCaseDischargeServiceRequest() {
    }

    public SaveCaseDischargeServiceRequest(SaveDischargeDTO saveDischargeDTO) {
        this.saveDischargeDTO = saveDischargeDTO;
    }

    //getters and setters

    public SaveDischargeDTO getSaveDischargeDTO() {
        return saveDischargeDTO;
    }

    public void setSaveDischargeDTO(SaveDischargeDTO saveDischargeDTO) {
        this.saveDischargeDTO = saveDischargeDTO;
    }
}
