package com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplate;

/*
* created by Latha on 27-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmTemplateResponseDTO;

import java.util.List;

public class GetCmMedicineTemplateServiceResponse extends CommonServiceResponse {

    private List<CmTemplateResponseDTO> cmTemplateResponseDTOs;

    //constructor

    public GetCmMedicineTemplateServiceResponse(List<CmTemplateResponseDTO> cmTemplateResponseDTOs) {
        this.cmTemplateResponseDTOs = cmTemplateResponseDTOs;
    }

    public GetCmMedicineTemplateServiceResponse() {
    }

    //getters and setters

    public List<CmTemplateResponseDTO> getCmTemplateResponseDTOs() {
        return cmTemplateResponseDTOs;
    }

    public void setCmTemplateResponseDTOs(List<CmTemplateResponseDTO> cmTemplateResponseDTOs) {
        this.cmTemplateResponseDTOs = cmTemplateResponseDTOs;
    }
}
