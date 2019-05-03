package com.erx.microservice.patientmanagement.service.casemanagement.getdepartmentbycliniclocationid;

/*
 * created by Latha on 10-10-2018
 * */

import com.erx.microservice.patientmanagement.domain.DepartmentMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.DepartmentMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.DepartmentMasterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getDepartmentByClinicLocationIdService")
public class GetDepartmentByClinicLocationIdServiceImpl implements GetDepartmentByClinicLocationIdService {

    private final Logger log = LoggerFactory.getLogger(GetDepartmentByClinicLocationIdServiceImpl.class);

    @Autowired
    private DepartmentMasterRepository departmentMasterRepository;

    @Override
    public GetDepartmentByClinicLocationIdServiceResponse execute(GetDepartmentByClinicLocationIdServiceRequest request) throws ServiceException {
        GetDepartmentByClinicLocationIdServiceResponse response = null;
        List<DepartmentMaster> departmentMasters = null;
        List<DepartmentMasterDTO> departmentMasterDTOs = new ArrayList<>();

        try {
            log.debug("Call to get department by clinic location id" + request.getClinicLocationId());
            //retrieve the departmentMasters for the clinicLocation id
            departmentMasters = departmentMasterRepository.findDepartmentMasterByClinicLocation(request.getClinicLocationId());

            if(departmentMasters != null)
            for (DepartmentMaster departmentMaster : departmentMasters) {
                //set DepartmentDTO object
                DepartmentMasterDTO departmentMasterDTO = new DepartmentMasterDTO();
                //set the values
                departmentMasterDTO.setClinicLocationId(request.getClinicLocationId());
                departmentMasterDTO.setId(departmentMaster.getId());
                departmentMasterDTO.setDepartmentId(departmentMaster.getDepartmentId());
                departmentMasterDTO.setDepartmentName(departmentMaster.getDepartmentName());
                departmentMasterDTOs.add(departmentMasterDTO);
            }
            response = new GetDepartmentByClinicLocationIdServiceResponse(departmentMasterDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved department details by clinic location id successfully");
            log.debug("Retrieved department details by clinic location id successfully");

        } catch (Exception e) {
            response = new GetDepartmentByClinicLocationIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve department details by clinic location id");
            log.error("Failed to retrieve department details by clinic location id");
        }
        return response;
    }
}