package com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydepartment;

/*
* created by Brighty on 04-01-2017
* */



import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardByDepartmentInputDTO;

import java.util.List;

public class GetWardMastersByDepartmentServiceRequest implements CommonServiceRequest {

    private List<WardByDepartmentInputDTO> wardByDepartmentInputDTOs;

    //Getters and setters

    public GetWardMastersByDepartmentServiceRequest(List<WardByDepartmentInputDTO> wardByDepartmentInputDTOs) {
        this.wardByDepartmentInputDTOs = wardByDepartmentInputDTOs;
    }

    public List<WardByDepartmentInputDTO> getWardByDepartmentInputDTOs() {
        return wardByDepartmentInputDTOs;
    }

    //constructor

    public void setWardByDepartmentInputDTOs(List<WardByDepartmentInputDTO> wardByDepartmentInputDTOs) {
        this.wardByDepartmentInputDTOs = wardByDepartmentInputDTOs;
    }
}
