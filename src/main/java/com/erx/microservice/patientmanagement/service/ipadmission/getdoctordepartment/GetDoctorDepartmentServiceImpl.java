package com.erx.microservice.patientmanagement.service.ipadmission.getdoctordepartment;

/*
* created by Brighty on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.domain.DepartmentMaster;
import com.erx.microservice.patientmanagement.domain.UserDepartment;
import com.erx.microservice.patientmanagement.repository.DepartmentMasterRepository;
import com.erx.microservice.patientmanagement.repository.UserDepartmentRepository;
import com.erx.microservice.patientmanagement.service.dto.DepartmentDTO;
import com.erx.microservice.patientmanagement.service.dto.DoctorDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getDoctorDepartmentService")
public class GetDoctorDepartmentServiceImpl implements GetDoctorDepartmentService {

    private final Logger log = LoggerFactory.getLogger(GetDoctorDepartmentServiceImpl.class);

    @Autowired
    private DepartmentMasterRepository departmentMasterRepository;

    @Autowired
    private UserDepartmentRepository userDepartmentRepository;

    @Override
    public GetDoctorDepartmentServiceResponse execute(GetDoctorDepartmentServiceRequest request) throws ServiceException {

        GetDoctorDepartmentServiceResponse response = null;
        List<DepartmentMaster> departmentMasters = null;
        List<DepartmentDTO> departmentDTOs = new ArrayList<>();
        List<UserDepartment> userDepartments = null;
        try {
            log.debug("Call to retrieve doctor and department");
            if (request.getClinicLocationId() != null) {
                //retrieve the departmentMasters for the clinicLocation id
                departmentMasters = departmentMasterRepository.findDepartmentMasterByClinicLocation(request.getClinicLocationId());
                for (DepartmentMaster departmentMaster : departmentMasters) {
                    //set DepartmentDTO object
                    DepartmentDTO departmentDTO = new DepartmentDTO();
                    //set the values
                    departmentDTO.setDepartmentID(departmentMaster.getId());
                    departmentDTO.setDepartmentName(departmentMaster.getDepartmentName());
                    //retrieve the doctors for the department
                    userDepartments = userDepartmentRepository.getDoctorForDepartment(departmentMaster.getId());
                    List<DoctorDTO> doctorDTOs = new ArrayList<>();
                    for (UserDepartment userDepartment : userDepartments) {
                        //create DoctorDTO object
                        DoctorDTO doctorDTO = new DoctorDTO();
                        //set the values
                        if (userDepartment.getUserStaff().getUserDetail() != null) {
                            doctorDTO.setDoctorID(userDepartment.getUserStaff().getUserDetail().getId());
                        }
                        doctorDTO.setDoctorName(userDepartment.getUserStaff().getFirstName() + " " + userDepartment.getUserStaff().getLastName());

                        //add to list
                        doctorDTOs.add(doctorDTO);
                    }
                    //set doctorDTOs in departmentDTO
                    departmentDTO.setDoctorDTOS(doctorDTOs);

                    //add to list
                    departmentDTOs.add(departmentDTO);
                }
            }
            response = new GetDoctorDepartmentServiceResponse(departmentDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved doctor and department details successfully");
            log.debug("Retrieved doctor and department details successfully");
        } catch (Exception e) {
            response = new GetDoctorDepartmentServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve doctor and department details");
            log.error("Failed to retrieve doctor and department details");
        }
        return response;
    }
}
