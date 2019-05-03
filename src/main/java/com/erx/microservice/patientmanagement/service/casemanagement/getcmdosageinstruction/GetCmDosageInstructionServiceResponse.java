package com.erx.microservice.patientmanagement.service.casemanagement.getcmdosageinstruction;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmDosageInstructionDTO;

import java.util.List;

public class GetCmDosageInstructionServiceResponse extends CommonServiceResponse {

    private List<CmDosageInstructionDTO> cmDosageInstructionDTOs;

    //constructor


    public GetCmDosageInstructionServiceResponse(List<CmDosageInstructionDTO> cmDosageInstructionDTOs) {
        this.cmDosageInstructionDTOs = cmDosageInstructionDTOs;
    }

    public GetCmDosageInstructionServiceResponse() {
    }

    //getters and setters

    public List<CmDosageInstructionDTO> getCmDosageInstructionDTOs() {
        return cmDosageInstructionDTOs;
    }

    public void setCmDosageInstructionDTOs(List<CmDosageInstructionDTO> cmDosageInstructionDTOs) {
        this.cmDosageInstructionDTOs = cmDosageInstructionDTOs;
    }
}
