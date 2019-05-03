package com.erx.microservice.patientmanagement.service.casemanagement.savecomplaint;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmMasterDTO;

public class SaveComplaintServiceResponse extends CommonServiceResponse {

    private CmMasterDTO cmMasterDTO;

    //constructor

    public SaveComplaintServiceResponse(CmMasterDTO cmMasterDTO) {
        this.cmMasterDTO = cmMasterDTO;
    }

    public SaveComplaintServiceResponse() {
    }

    //getters and setters

    public CmMasterDTO getCmMasterDTO() {
        return cmMasterDTO;
    }

    public void setCmMasterDTO(CmMasterDTO cmMasterDTO) {
        this.cmMasterDTO = cmMasterDTO;
    }
}
