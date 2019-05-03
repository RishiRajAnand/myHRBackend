package com.erx.microservice.patientmanagement.service.casemanagement.getpathyapathyabycmtreatmentid;

/*
* created by Latha on 02-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmPathyaPathyaDTO;

public class GetPathyaPathyaByCmTreatmentIdServiceResponse extends CommonServiceResponse {

    private CmPathyaPathyaDTO cmPathyaPathyaDTO;

    //constructor

    public GetPathyaPathyaByCmTreatmentIdServiceResponse(CmPathyaPathyaDTO cmPathyaPathyaDTO) {
        this.cmPathyaPathyaDTO = cmPathyaPathyaDTO;
    }

    public GetPathyaPathyaByCmTreatmentIdServiceResponse() {
    }

    //getters and setters

    public CmPathyaPathyaDTO getCmPathyaPathyaDTO() {
        return cmPathyaPathyaDTO;
    }

    public void setCmPathyaPathyaDTO(CmPathyaPathyaDTO cmPathyaPathyaDTO) {
        this.cmPathyaPathyaDTO = cmPathyaPathyaDTO;
    }
}
