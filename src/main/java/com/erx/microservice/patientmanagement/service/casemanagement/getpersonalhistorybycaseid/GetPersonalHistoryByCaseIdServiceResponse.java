package com.erx.microservice.patientmanagement.service.casemanagement.getpersonalhistorybycaseid;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.GetCmPersonalHistoryDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.GetComplaintsDTO;

import java.util.List;

public class GetPersonalHistoryByCaseIdServiceResponse extends CommonServiceResponse {

    private List<GetCmPersonalHistoryDTO> getCmPersonalHistoryDTOs;

    //constructor

    public GetPersonalHistoryByCaseIdServiceResponse(List<GetCmPersonalHistoryDTO> getCmPersonalHistoryDTOs) {
        this.getCmPersonalHistoryDTOs = getCmPersonalHistoryDTOs;
    }

    public GetPersonalHistoryByCaseIdServiceResponse() {
    }

    //getters and setters

    public List<GetCmPersonalHistoryDTO> getGetCmPersonalHistoryDTOs() {
        return getCmPersonalHistoryDTOs;
    }

    public void setGetCmPersonalHistoryDTOs(List<GetCmPersonalHistoryDTO> getCmPersonalHistoryDTOs) {
        this.getCmPersonalHistoryDTOs = getCmPersonalHistoryDTOs;
    }
}
