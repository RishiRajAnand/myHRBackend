package com.erx.microservice.patientmanagement.service.casemanagement.getdischargedetailsbycaseid;

/*
* created by Latha on 05-10-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.DischargeDTO;

public class GetDischargeDetailsByCaseIdServiceResponse extends CommonServiceResponse {

    private DischargeDTO dischargeDTO;

    //constructor

    public GetDischargeDetailsByCaseIdServiceResponse() {
    }

    public GetDischargeDetailsByCaseIdServiceResponse(DischargeDTO dischargeDTO) {
        this.dischargeDTO = dischargeDTO;
    }

    //getters and setters

    public DischargeDTO getDischargeDTO() {
        return dischargeDTO;
    }

    public void setDischargeDTO(DischargeDTO dischargeDTO) {
        this.dischargeDTO = dischargeDTO;
    }
}
