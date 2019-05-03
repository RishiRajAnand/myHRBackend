package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydepartment;

/*
* created by Brighty on 04-01-2017
* */



import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterByClinicLocationDTO;

import java.util.List;

public class GetWardMastersByDepartmentServiceResponse extends CommonServiceResponse {

    private List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOs;

    //Getters and setters

    public GetWardMastersByDepartmentServiceResponse(List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOs) {
        this.wardMasterByClinicLocationDTOs = wardMasterByClinicLocationDTOs;
    }

    public GetWardMastersByDepartmentServiceResponse() {
    }

    //constructor

    public List<WardMasterByClinicLocationDTO> getWardMasterByClinicLocationDTOs() {
        return wardMasterByClinicLocationDTOs;
    }

    public void setWardMasterByClinicLocationDTOs(List<WardMasterByClinicLocationDTO> wardMasterByClinicLocationDTOs) {
        this.wardMasterByClinicLocationDTOs = wardMasterByClinicLocationDTOs;
    }
}
