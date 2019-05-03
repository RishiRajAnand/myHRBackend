package com.erx.microservice.patientmanagement.service.casemanagement.getcmdosagevalue;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmDosageValueMappingDTO;

import java.util.List;

public class GetCmDosageValueServiceResponse extends CommonServiceResponse {

    private List<CmDosageValueMappingDTO> cmDosageValueMappingDTOs;

    //constructor


    public GetCmDosageValueServiceResponse(List<CmDosageValueMappingDTO> cmDosageValueMappingDTOs) {
        this.cmDosageValueMappingDTOs = cmDosageValueMappingDTOs;
    }

    public GetCmDosageValueServiceResponse() {
    }

    //getters and setters

    public List<CmDosageValueMappingDTO> getCmDosageValueMappingDTOs() {
        return cmDosageValueMappingDTOs;
    }

    public void setCmDosageValueMappingDTOs(List<CmDosageValueMappingDTO> cmDosageValueMappingDTOs) {
        this.cmDosageValueMappingDTOs = cmDosageValueMappingDTOs;
    }
}
