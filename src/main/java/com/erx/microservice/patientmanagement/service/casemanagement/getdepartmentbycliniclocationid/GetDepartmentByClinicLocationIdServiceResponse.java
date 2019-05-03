package com.erx.microservice.patientmanagement.service.casemanagement.getdepartmentbycliniclocationid;

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.ClinicLocationDTO;
import com.erx.microservice.patientmanagement.service.dto.DepartmentMasterDTO;

import java.util.ArrayList;
import java.util.List;

/*
 * created by Latha on 10-10-2018
 * */

public class GetDepartmentByClinicLocationIdServiceResponse extends CommonServiceResponse {

    private List<DepartmentMasterDTO> departmentMasterDTOs;

    //constructor

    public GetDepartmentByClinicLocationIdServiceResponse() {
    }

    public GetDepartmentByClinicLocationIdServiceResponse(List<DepartmentMasterDTO> departmentMasterDTOs) {
        this.departmentMasterDTOs = departmentMasterDTOs;
    }

    //getters and setters

    public List<DepartmentMasterDTO> getDepartmentMasterDTOs() {
        return departmentMasterDTOs;
    }

    public void setDepartmentMasterDTOs(List<DepartmentMasterDTO> departmentMasterDTOs) {
        this.departmentMasterDTOs = departmentMasterDTOs;
    }
}
