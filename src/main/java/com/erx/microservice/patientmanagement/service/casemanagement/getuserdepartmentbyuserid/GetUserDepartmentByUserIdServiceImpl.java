package com.erx.microservice.patientmanagement.service.casemanagement.getuserdepartmentbyuserid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.Module;
import com.erx.microservice.patientmanagement.domain.ModuleDepartmentMapping;
import com.erx.microservice.patientmanagement.domain.UserDepartment;
import com.erx.microservice.patientmanagement.domain.UserStaff;
import com.erx.microservice.patientmanagement.repository.ModuleDepartmentMappingRepository;
import com.erx.microservice.patientmanagement.repository.ModuleRepository;
import com.erx.microservice.patientmanagement.repository.UserDepartmentRepository;
import com.erx.microservice.patientmanagement.repository.UserStaffRepository;
import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("getUserDepartmentByUserIdService")
public class GetUserDepartmentByUserIdServiceImpl implements GetUserDepartmentByUserIdService {

    private final Logger log = LoggerFactory.getLogger(GetUserDepartmentByUserIdServiceImpl.class);

    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModuleDepartmentMappingRepository moduleDepartmentMappingRepository;

    @Autowired
    private UserDepartmentRepository userDepartmentRepository;

    @Override
    public GetUserDepartmentByUserIdServiceResponse execute(GetUserDepartmentByUserIdServiceRequest request) throws ServiceException {
        GetUserDepartmentByUserIdServiceResponse response = null;
        Module module = new Module();
        List<ModuleDepartmentMapping> moduleDepartmentMappings = new ArrayList<>();
        List<UserDepartment> userDepartments = new ArrayList<>();
        Long userDepartmentId = null;

        try {
            log.debug("Call to retrieve user department id by user id" + request.getUserId());
            //find module id for case management(44 is the module id of case sheet) by module code
            String caseModuleCode = PatientConstants.CASE_MODULE_CODE;
            module = moduleRepository.findByModuleCode(caseModuleCode);
            if(request.getUserId() != null && module != null)

                //find module departments by module id
                moduleDepartmentMappings = moduleDepartmentMappingRepository.findDepartmentByModuleId(module.getId());

            //find user department by user id
            userDepartments = userDepartmentRepository.getDepartmentByDoctor(request.getUserId());
            if (userDepartments != null && moduleDepartmentMappings != null) {
                for (UserDepartment userDepartment : userDepartments) {
                       boolean departmentId = false;
                    for(ModuleDepartmentMapping moduleDepartmentMapping : moduleDepartmentMappings){
                       if(userDepartment.getDepartment().getId().equals(moduleDepartmentMapping.getDepartment().getId())){
                           departmentId = true;
                           break;
                       }
                   }
                       if(departmentId == false){
                           continue;
                       }

                       if(departmentId == true) {
                           userDepartmentId = (long) 0;
                           userDepartmentId = userDepartment.getDepartment().getId();
                       }
                }
            }
            // setting the dto to response
            response = new GetUserDepartmentByUserIdServiceResponse(userDepartmentId);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved department id by user id Successfully");
            log.debug("Retrieved department id by user id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve department id by user id");
            log.error("Failed to get department id by user id" + e.getMessage());
        }
        return response;
    }
}
