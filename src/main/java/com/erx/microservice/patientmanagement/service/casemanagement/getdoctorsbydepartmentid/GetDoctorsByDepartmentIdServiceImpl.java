package com.erx.microservice.patientmanagement.service.casemanagement.getdoctorsbydepartmentid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.UserDepartment;
import com.erx.microservice.patientmanagement.domain.UserDetail;
import com.erx.microservice.patientmanagement.domain.UserStaff;
import com.erx.microservice.patientmanagement.repository.UserDepartmentRepository;
import com.erx.microservice.patientmanagement.repository.UserDetailRepository;
import com.erx.microservice.patientmanagement.repository.UserStaffRepository;
import com.erx.microservice.patientmanagement.service.dto.DoctorDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("getDoctorsByDepartmentIdService")
public class GetDoctorsByDepartmentIdServiceImpl implements GetDoctorsByDepartmentIdService {

    private final Logger log = LoggerFactory.getLogger(GetDoctorsByDepartmentIdServiceImpl.class);

    @Autowired
    private UserDepartmentRepository userDepartmentRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private UserStaffRepository userStaffRepository;

    @Override
    public GetDoctorsByDepartmentIdServiceResponse execute(GetDoctorsByDepartmentIdServiceRequest request) throws ServiceException {
        GetDoctorsByDepartmentIdServiceResponse response = null;
        List<UserDepartment> userDepartments = new ArrayList<>();
        List<DoctorDTO> doctorDTOs = new ArrayList<>();

        try {
            log.debug("Call to get doctors by department id" + request.getDepartmentId());
            //retrieve user departments by department id
            userDepartments = userDepartmentRepository.getDoctorForDepartment(request.getDepartmentId());
            if(userDepartments != null)
                for(UserDepartment userDepartment : userDepartments){
                    DoctorDTO doctorDTO = new DoctorDTO();
                    //find user by user id
                    UserStaff userStaff = userStaffRepository.findOne(userDepartment.getUserStaff().getId());
                    //find doctor id by user id
                    UserDetail userDetail = userDetailRepository.findDoctorByUserId(userDepartment.getUserStaff().getId(),request.getClinicId());
                    if(userDetail != null && userStaff != null){
                        doctorDTO.setDoctorID(userDetail.getId());
                        doctorDTO.setDoctorName("Dr."+userStaff.getFirstName()+ ' ' +userStaff.getLastName());
                        doctorDTO.setDoctorDepartmentName(userDepartment.getDepartment().getDepartmentName());
                        doctorDTO.setDoctorLocationName(userStaff.getPrimaryClinicLocation().getLocation().getName());
                    }
                    doctorDTOs.add(doctorDTO);
                }
            response = new GetDoctorsByDepartmentIdServiceResponse(doctorDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved doctor details by department id successfully");
            log.debug("Retrieved doctor details by department id successfully");
        } catch (Exception e) {
            response = new GetDoctorsByDepartmentIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve doctor details by department id");
            log.error("Failed to retrieve doctor details by department id");
        }

        return response;
    }
}
