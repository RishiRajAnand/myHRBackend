package com.erx.microservice.patientmanagement.service.casemanagement.getcomplaintsbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.GetComplaintsDTO;

public class GetComplaintsByCaseIdServiceResponse extends CommonServiceResponse {

    private GetComplaintsDTO getComplaintsDTO;

    //constructor

    public GetComplaintsByCaseIdServiceResponse(GetComplaintsDTO getComplaintsDTO) {
        this.getComplaintsDTO = getComplaintsDTO;
    }

    public GetComplaintsByCaseIdServiceResponse() {
    }

    //getters and setters

    public GetComplaintsDTO getGetComplaintsDTO() {
        return getComplaintsDTO;
    }

    public void setGetComplaintsDTO(GetComplaintsDTO getComplaintsDTO) {
        this.getComplaintsDTO = getComplaintsDTO;
    }
}
