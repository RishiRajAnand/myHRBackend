package com.erx.microservice.patientmanagement.service.patienttype.getpatienttypes;


import com.erx.microservice.patientmanagement.domain.PatientType;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.PatientTypeRepository;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeByClinicLocationDTO;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeDTO;
import com.erx.microservice.patientmanagement.service.mapper.PatientTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
* created by Brighty on 09-11-2017
* */

@Service("getAllPatientTypeMastersService")
public class GetPatientTypesServiceImpl implements GetPatientTypesService {

    private final Logger log = LoggerFactory.getLogger(GetPatientTypesServiceImpl.class);

    @Autowired
    private PatientTypeRepository patientTypeRepository;

    @Autowired
    private PatientTypeMapper patientTypeMapper;

    @Override
    public GetPatientTypesServiceResponse execute(GetPatientTypesServiceRequest request) throws ServiceException {

        GetPatientTypesServiceResponse response = null;
        List<PatientType> patientTypes = new ArrayList<PatientType>();
        List<PatientTypeDTO> patientTypeDTOs = new ArrayList<PatientTypeDTO>();
        List<PatientTypeByClinicLocationDTO> patientTypeByClinicLocationDTOs = new
                ArrayList<PatientTypeByClinicLocationDTO>();
        try {
            log.debug("retrieving all patientTypes for the clinicLocation having id : " + request.getClinicLocationId());
            if (request.getClinicLocationId() != 0) {
                //retrieve the PatientTypes for ClinicLocation
                patientTypes = patientTypeRepository.getActivePatientTypesByClinicLocation(request.getClinicLocationId());
                //convert domain to DTO
                patientTypeDTOs = patientTypeMapper.patientTypesToPatientTypeDTOs(patientTypes);
                //add it to the list of PatientTypeByClinicLocationDTO
                for (PatientTypeDTO patientTypeDTO : patientTypeDTOs) {
                    //create the object of PatientTypeByClinicLocationDTO
                    PatientTypeByClinicLocationDTO patientTypeByClinicLocationDTO = new PatientTypeByClinicLocationDTO();
                    //set the values
                    patientTypeByClinicLocationDTO.setId(patientTypeDTO.getId());
                    patientTypeByClinicLocationDTO.setPatientTypeName(patientTypeDTO.getPatientTypeName());
                    //add the object to list
                    patientTypeByClinicLocationDTOs.add(patientTypeByClinicLocationDTO);
                }
            }
            //create the response
            response = new GetPatientTypesServiceResponse(patientTypeByClinicLocationDTOs);
            response.setMessage("Retrieved all patientTypes for the clinicLocation having id : " + request.getClinicLocationId());
            response.SUCCESSFUL = true;
            log.debug("Retrieved all patientTypes for the clinicLocation having id : " + request.getClinicLocationId());

        } catch (Exception e) {
            //create the response
            response = new GetPatientTypesServiceResponse();
            response.SUCCESSFUL = false;
            log.error("Not retrieved all patientTypes for the clinicLocation having id : " + request.getClinicLocationId());
            response.setMessage("Not retrieved all patientTypes for the clinicLocation having id : " + request.getClinicLocationId());
        }
        return response;
    }
}