package com.erx.microservice.patientmanagement.service.casemanagement.getprovisionaldiagnosismaster;

/*
* created by Latha on 18-09-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.ProvisionalDiagnosisMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.RouteOfAdministrationDTO;

import java.util.List;

public class ProvisionalDiagnosisMasterServiceResponse extends CommonServiceResponse {

    private List<ProvisionalDiagnosisMasterDTO> provisionalDiagnosisMasterDTOs;

    //constructor

    public ProvisionalDiagnosisMasterServiceResponse() {
    }

    public ProvisionalDiagnosisMasterServiceResponse(List<ProvisionalDiagnosisMasterDTO> provisionalDiagnosisMasterDTOs) {
        this.provisionalDiagnosisMasterDTOs = provisionalDiagnosisMasterDTOs;
    }

    //getters and setters

    public List<ProvisionalDiagnosisMasterDTO> getProvisionalDiagnosisMasterDTOs() {
        return provisionalDiagnosisMasterDTOs;
    }

    public void setProvisionalDiagnosisMasterDTOs(List<ProvisionalDiagnosisMasterDTO> provisionalDiagnosisMasterDTOs) {
        this.provisionalDiagnosisMasterDTOs = provisionalDiagnosisMasterDTOs;
    }
}
