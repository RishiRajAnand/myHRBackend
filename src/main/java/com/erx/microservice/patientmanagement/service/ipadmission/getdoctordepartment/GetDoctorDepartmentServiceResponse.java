package com.erx.microservice.patientmanagement.service.ipadmission.getdoctordepartment;

/*
* created by Brighty on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.DepartmentDTO;

import java.util.List;

public class GetDoctorDepartmentServiceResponse extends CommonServiceResponse {

    List<DepartmentDTO> departmentDTOs;

    //getters and setters

    public GetDoctorDepartmentServiceResponse() {
    }

    public GetDoctorDepartmentServiceResponse(List<DepartmentDTO> departmentDTOs) {

        this.departmentDTOs = departmentDTOs;
    }

    //constructor

    public List<DepartmentDTO> getDepartmentDTOs() {
        return departmentDTOs;
    }

    public void setDepartmentDTOs(List<DepartmentDTO> departmentDTOs) {
        this.departmentDTOs = departmentDTOs;
    }
}
