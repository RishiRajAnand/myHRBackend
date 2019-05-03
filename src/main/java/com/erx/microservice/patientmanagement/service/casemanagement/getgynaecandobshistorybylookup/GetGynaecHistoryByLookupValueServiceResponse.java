package com.erx.microservice.patientmanagement.service.casemanagement.getgynaecandobshistorybylookup;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.CmObservationCategoryDataDTO;

import java.util.List;

public class GetGynaecHistoryByLookupValueServiceResponse extends CommonServiceResponse {

    private List<CmObservationCategoryDataDTO> cmObservationCategoryDataDTOs;

    //constructor


    public GetGynaecHistoryByLookupValueServiceResponse(List<CmObservationCategoryDataDTO> cmObservationCategoryDataDTOs) {
        this.cmObservationCategoryDataDTOs = cmObservationCategoryDataDTOs;
    }

    public GetGynaecHistoryByLookupValueServiceResponse() {
    }

    //getters and setters


    public List<CmObservationCategoryDataDTO> getCmObservationCategoryDataDTOs() {
        return cmObservationCategoryDataDTOs;
    }

    public void setCmObservationCategoryDataDTOs(List<CmObservationCategoryDataDTO> cmObservationCategoryDataDTOs) {
        this.cmObservationCategoryDataDTOs = cmObservationCategoryDataDTOs;
    }
}
