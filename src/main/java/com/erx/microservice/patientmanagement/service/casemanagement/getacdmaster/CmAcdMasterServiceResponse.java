package com.erx.microservice.patientmanagement.service.casemanagement.getacdmaster;

/*
* created by Latha on 18-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.AcdMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.ProvisionalDiagnosisMasterDTO;

import java.util.List;

public class CmAcdMasterServiceResponse extends CommonServiceResponse {

    private List<AcdMasterDTO> acdMasterDTOs;

    //constructor

    public CmAcdMasterServiceResponse() {
    }

    public CmAcdMasterServiceResponse(List<AcdMasterDTO> acdMasterDTOs) {
        this.acdMasterDTOs = acdMasterDTOs;
    }

    //getters and setters

    public List<AcdMasterDTO> getAcdMasterDTOs() {
        return acdMasterDTOs;
    }

    public void setAcdMasterDTOs(List<AcdMasterDTO> acdMasterDTOs) {
        this.acdMasterDTOs = acdMasterDTOs;
    }
}
