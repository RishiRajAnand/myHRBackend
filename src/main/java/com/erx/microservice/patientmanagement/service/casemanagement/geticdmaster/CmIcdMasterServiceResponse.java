package com.erx.microservice.patientmanagement.service.casemanagement.geticdmaster;

/*
* created by Latha on 18-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.AcdMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.IcdMasterDTO;

import java.util.List;

public class CmIcdMasterServiceResponse extends CommonServiceResponse {

    private List<IcdMasterDTO> icdMasterDTOs;

    //constructor

    public CmIcdMasterServiceResponse() {
    }

    public CmIcdMasterServiceResponse(List<IcdMasterDTO> icdMasterDTOs) {
        this.icdMasterDTOs = icdMasterDTOs;
    }

    //getters and setters

    public List<IcdMasterDTO> getIcdMasterDTOs() {
        return icdMasterDTOs;
    }

    public void setIcdMasterDTOs(List<IcdMasterDTO> icdMasterDTOs) {
        this.icdMasterDTOs = icdMasterDTOs;
    }
}
