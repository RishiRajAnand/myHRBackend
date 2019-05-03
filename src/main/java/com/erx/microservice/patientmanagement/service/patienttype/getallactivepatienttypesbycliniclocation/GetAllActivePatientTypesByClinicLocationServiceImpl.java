package com.erx.microservice.patientmanagement.service.patienttype.getallactivepatienttypesbycliniclocation;


import com.erx.microservice.patientmanagement.domain.PatientType;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.PatientTypeRepository;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeDTO;
import com.erx.microservice.patientmanagement.service.mapper.PatientTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
* created by Brighty on 13-11-2017
* */

@Service("getAllActivePatientTypesByClinicLocationService")
public class GetAllActivePatientTypesByClinicLocationServiceImpl implements GetAllActivePatientTypesByClinicLocationService {

    private final Logger log = LoggerFactory.getLogger(GetAllActivePatientTypesByClinicLocationServiceImpl.class);

    @Autowired
    private PatientTypeRepository patientTypeRepository;

    @Autowired
    private PatientTypeMapper patientTypeMapper;

    @Override
    public GetAllActivePatientTypesByClinicLocationServiceResponse execute(GetAllActivePatientTypesByClinicLocationServiceRequest request) throws ServiceException {

        log.debug("call to retrieve all the PatientTypes for clinicLocation id : " + request.getClinicLocationId());
        GetAllActivePatientTypesByClinicLocationServiceResponse response = null;
        List<PatientType> patientTypes = null;
        List<PatientTypeDTO> patientTypeDTOs = new ArrayList<>();
        try {
            if (request.getClinicLocationId() != 0) {
                //retrieve the list of PatientTypes
                patientTypes = patientTypeRepository.getActivePatientTypesByClinicLocation(request.getClinicLocationId());
                //create list of DTO
                for (PatientType patientType : patientTypes) {
                    //convert domain to DTO
                    PatientTypeDTO patientTypeDTO = patientTypeMapper.patientTypeToPatientTypeDTO(patientType);
                    //set the clinicLocationName
                    //patientTypeDTO.getClinicLocation().setClinicLocationName(patientType.getClinicLocation().getLocation().getName());
                    //add DTO to the list
                    patientTypeDTOs.add(patientTypeDTO);
                }

                //create response
                response = new GetAllActivePatientTypesByClinicLocationServiceResponse(patientTypeDTOs);
                response.SUCCESSFUL = true;
                response.setMessage("Retrieved all PatientTypes successfully");
                log.debug("Retrieved all PatientTypes successfully");
            }
        } catch (Exception e) {
            //create response
            response = new GetAllActivePatientTypesByClinicLocationServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not retrieved all PatientTypes successfully");
            log.error("Not retrieved all PatientTypes successfully");
        }
        return response;
    }
}
