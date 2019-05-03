package com.erx.microservice.patientmanagement.service.wardmaster.getwardmasterbyid;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterByIdDTO;

public class GetWardMasterByIdServiceResponse extends CommonServiceResponse {

    private WardMasterByIdDTO wardMasterByIdDTO;

    public GetWardMasterByIdServiceResponse() {
    }

    public GetWardMasterByIdServiceResponse(WardMasterByIdDTO wardMasterByIdDTO) {
        this.wardMasterByIdDTO = wardMasterByIdDTO;
    }

    public WardMasterByIdDTO getWardMasterByIdDTO() {

        return wardMasterByIdDTO;
    }

    public void setWardMasterByIdDTO(WardMasterByIdDTO wardMasterByIdDTO) {
        this.wardMasterByIdDTO = wardMasterByIdDTO;
    }
}
