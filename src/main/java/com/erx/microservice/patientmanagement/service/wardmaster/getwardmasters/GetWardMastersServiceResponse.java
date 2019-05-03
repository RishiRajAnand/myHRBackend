package com.erx.microservice.patientmanagement.service.wardmaster.getwardmasters;

/*
* created by Brighty on 16-11-2017
* */



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterByClinicLocationDTO;

import java.util.List;

public class GetWardMastersServiceResponse extends CommonServiceResponse {

    private List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOS;

    public GetWardMastersServiceResponse() {
    }

    public GetWardMastersServiceResponse(List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOS) {
        this.wardMasterByClinicLocationDTOS = wardMasterByClinicLocationDTOS;
    }

    public List<WardMasterByClinicLocationDTO> getWardMasterByClinicLocationDTOS() {

        return wardMasterByClinicLocationDTOS;
    }

    public void setWardMasterByClinicLocationDTOS(List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOS) {
        this.wardMasterByClinicLocationDTOS = wardMasterByClinicLocationDTOS;
    }
}
