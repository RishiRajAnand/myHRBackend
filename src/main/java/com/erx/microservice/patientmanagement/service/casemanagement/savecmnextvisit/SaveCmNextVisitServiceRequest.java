package com.erx.microservice.patientmanagement.service.casemanagement.savecmnextvisit;

/*
* created by Latha on 10-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmNextVisitDTO;

public class SaveCmNextVisitServiceRequest implements CommonServiceRequest {

    private CmNextVisitDTO cmNextVisitDTO;

    //constructor

    public SaveCmNextVisitServiceRequest() {
    }

    public SaveCmNextVisitServiceRequest(CmNextVisitDTO cmNextVisitDTO) {
        this.cmNextVisitDTO = cmNextVisitDTO;
    }

    //getters and setters

    public CmNextVisitDTO getCmNextVisitDTO() {
        return cmNextVisitDTO;
    }

    public void setCmNextVisitDTO(CmNextVisitDTO cmNextVisitDTO) {
        this.cmNextVisitDTO = cmNextVisitDTO;
    }

}
