package com.erx.microservice.patientmanagement.service.patienttype.getpatienttypebyid;


import com.erx.microservice.patientmanagement.domain.PatientType;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.PatientTypeRepository;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeDTO;
import com.erx.microservice.patientmanagement.service.mapper.PatientTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* created by Brighty on 13-11-2017
* */

@Service("getPatientTypeByIdService")
public class GetPatientTypeByIdServiceImpl implements GetPatientTypeByIdService {

    private final Logger log = LoggerFactory.getLogger(GetPatientTypeByIdServiceImpl.class);

    @Autowired
    private PatientTypeRepository patientTypeRepository;

    @Autowired
    private PatientTypeMapper patientTypeMapper;

    @Override
    public GetPatientTypeByIdServiceResponse execute(GetPatientTypeByIdServiceRequest request) throws ServiceException {

        log.debug("call to retrieve PatientType with id : " + request.getPatientTypeId());
        GetPatientTypeByIdServiceResponse response = null;
        PatientType patientType = null;
        PatientTypeDTO patientTypeDTO = null;
        try {
            if (request.getPatientTypeId() != 0) {
                //get the PatientTypeMasterObject
                patientType = patientTypeRepository.findOne(request.getPatientTypeId());
                //convert domain to DTO
                patientTypeDTO = patientTypeMapper.patientTypeToPatientTypeDTO(patientType);
                //set the clinicLocation
               // patientTypeDTO.getClinicLocation().setClinicLocationName(
                       // patientType.getClinicLocation().getLocation().getName());

                //create response
                response = new GetPatientTypeByIdServiceResponse(patientTypeDTO);
                response.SUCCESSFUL = true;
                response.setMessage("PatientType retrieved successfully");
                log.debug("PatientType retrieved successfully");
            }
        } catch (Exception e) {
            //create response
            response = new GetPatientTypeByIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("PatientType not retrieved successfully");
            log.error("PatientType not retrieved successfully");
        }
        return response;
    }
}
