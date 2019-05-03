package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbycliniclocation;

/*
* created by Brighty on 17-11-2017
* */



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterByIdDTO;

import java.util.List;

public class GetWardMastersByClinicLocationServiceResponse extends CommonServiceResponse {

    private List<WardMasterByIdDTO> wardMasterByIdDTOS;

    public GetWardMastersByClinicLocationServiceResponse() {
    }

    public GetWardMastersByClinicLocationServiceResponse(List<WardMasterByIdDTO> wardMasterByIdDTOS) {
        this.wardMasterByIdDTOS = wardMasterByIdDTOS;
    }

    public List<WardMasterByIdDTO> getWardMasterByIdDTOS() {

        return wardMasterByIdDTOS;
    }

    public void setWardMasterByIdDTOS(List<WardMasterByIdDTO> wardMasterByIdDTOS) {
        this.wardMasterByIdDTOS = wardMasterByIdDTOS;
    }
}
