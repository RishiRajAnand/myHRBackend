package com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplatebytemplateid;

/*
* created by Latha on 27-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmTemplateResponseDTO;

public class GetCmMedicineTemplateByIdServiceResponse extends CommonServiceResponse {

    private CmTemplateResponseDTO cmTemplateResponseDTO;

    //constructor

    public GetCmMedicineTemplateByIdServiceResponse(CmTemplateResponseDTO cmTemplateResponseDTO) {
        this.cmTemplateResponseDTO = cmTemplateResponseDTO;
    }

    public GetCmMedicineTemplateByIdServiceResponse() {
    }

    //getters and setters

    public CmTemplateResponseDTO getCmTemplateResponseDTO() {
        return cmTemplateResponseDTO;
    }

    public void setCmTemplateResponseDTO(CmTemplateResponseDTO cmTemplateResponseDTO) {
        this.cmTemplateResponseDTO = cmTemplateResponseDTO;
    }
}
