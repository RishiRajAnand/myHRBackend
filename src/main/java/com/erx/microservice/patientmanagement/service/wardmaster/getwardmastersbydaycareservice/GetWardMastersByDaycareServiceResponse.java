package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydaycareservice;

/*
* created by Brighty on 10-01-18
* */



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterByClinicLocationDTO;

import java.util.List;

public class GetWardMastersByDaycareServiceResponse extends CommonServiceResponse {

    private List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOs;

    //Getters and setters

    public GetWardMastersByDaycareServiceResponse(List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOs) {
        this.wardMasterByClinicLocationDTOs = wardMasterByClinicLocationDTOs;
    }

    public GetWardMastersByDaycareServiceResponse() {

    }

    //constructor

    public List<WardMasterByClinicLocationDTO> getWardMasterByClinicLocationDTOs() {
        return wardMasterByClinicLocationDTOs;
    }

    public void setWardMasterByClinicLocationDTOs(List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOs) {
        this.wardMasterByClinicLocationDTOs = wardMasterByClinicLocationDTOs;
    }
}
