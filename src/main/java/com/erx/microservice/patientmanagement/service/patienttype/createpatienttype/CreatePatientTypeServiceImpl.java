package com.erx.microservice.patientmanagement.service.patienttype.createpatienttype;


import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.domain.ErxStatus;
import com.erx.microservice.patientmanagement.domain.PatientType;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.ClinicLocationRepository;
import com.erx.microservice.patientmanagement.repository.PatientTypeRepository;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeDTO;
import com.erx.microservice.patientmanagement.service.mapper.PatientTypeMapper;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* created by Brighty on 13-11-2017
* */

@Service("createPatientTypeService")
public class CreatePatientTypeServiceImpl implements CreatePatientTypeService {

    private final Logger log = LoggerFactory.getLogger(CreatePatientTypeServiceImpl.class);

    @Autowired
    private PatientTypeRepository patientTypeRepository;

    @Autowired
    private PatientTypeMapper patientTypeMapper;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    //constructor
    public CreatePatientTypeServiceImpl(PatientTypeRepository patientTypeRepository,
                                        PatientTypeMapper patientTypeMapper,
                                        ClinicLocationRepository clinicLocationRepository) {
        this.patientTypeRepository = patientTypeRepository;
        this.patientTypeMapper = patientTypeMapper;
        this.clinicLocationRepository = clinicLocationRepository;
    }

    @Override
    public CreatePatientTypeServiceResponse execute(CreatePatientTypeServiceRequest request) throws ServiceException {

        CreatePatientTypeServiceResponse response = null;
        PatientTypeDTO patientTypeMasterDTO = null;
        PatientType patientType = null;
        PatientTypeDTO savedPatientTypeMasterDTO = null;
        PatientType savedPatientType = null;
        ClinicLocation clinicLocation = null;
        try {
            log.debug("Request to create PatientType");
            if (request.getPatientTypeDTO().getPatientTypeName() != null &&
                    request.getPatientTypeDTO().getClinicLocationId() != 0) {
                //retrieve the DTO object from request
                patientTypeMasterDTO = request.getPatientTypeDTO();

                //Check this room exist or not
                if (patientTypeMasterDTO.getId() == null) {
                    response = validatePatientTypeName(patientTypeMasterDTO.getPatientTypeName(), patientTypeMasterDTO.getClinicLocationId());
                    if (response.SUCCESSFUL == false) {
                        return response;
                    }
                }

                //convert DTO to domain
                patientType = patientTypeMapper.patientTypeDTOToPatientType(patientTypeMasterDTO);
                //get the clinicLocation object
                clinicLocation = clinicLocationRepository.findOne(patientTypeMasterDTO.getClinicLocationId());
                //set the clinicLocation object
                //patientType.setClinicLocation(clinicLocation);


                if (patientTypeMasterDTO.getId() == null) {
                    //retrieve and set clinicId,CurrentTblName and setCurrentClmName to DTO
                    Long clinicId = clinicLocation.getClinic().getId();
                    //generate unique id
                    GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
                    GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
                    generateUniqueIDDTO.setCurrentTableName(ErxConstants.BTM_CURRENT_TABLE_NAME);
                    generateUniqueIDDTO.setCurrentColumnName(ErxConstants.BTM_CURRENT_COLUMN_NAME);
                    generateUniqueIDClinicDTO.setId(clinicId);
                    generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
                    //call generateUniqueID
                    String  patientTypeGeneratedCode = serviceGateway.generateUniqueID(generateUniqueIDDTO);

                    if (patientTypeGeneratedCode != null)
                        patientType.setPatientTypeCode(patientTypeGeneratedCode);
                }


                //save PatientType
                savedPatientType = patientTypeRepository.save(patientType);
                //convert domain to DTO
                savedPatientTypeMasterDTO = patientTypeMapper.patientTypeToPatientTypeDTO(savedPatientType);

                //map this PatientType with all the existing services
                if (patientTypeMasterDTO.getId() == null) {
                    serviceGateway.mapNewPatientTypeWithAllServices(savedPatientType.getId());
                    serviceGateway.mapNewPatientTypeWithAllMedicineTypes(savedPatientType.getId());
                }

                //create response
                response = new CreatePatientTypeServiceResponse(savedPatientTypeMasterDTO);
                response.SUCCESSFUL = true;
                response.setMessage("PatientType saved successfully");
                log.debug("PatientType saved successfully");
            }
        } catch (Exception e) {
            //create response
            response = new CreatePatientTypeServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("PatientType not saved successfully");
            log.error("PatientType not saved successfully : "+e.getMessage());
        }
        return response;
    }

    @Override
    public CreatePatientTypeServiceResponse validatePatientTypeName(String patientTypeName, Long clinicLocationId)
            throws ServiceException {

        CreatePatientTypeServiceResponse response = null;
        PatientType patientType = null;
        try {
            //create response
            response = new CreatePatientTypeServiceResponse();
            response.SUCCESSFUL = true;
            if (patientTypeName != null) {
                //retrieve the object with the given name
                patientType = patientTypeRepository.validatePatientTypeName(clinicLocationId, patientTypeName);
                if (patientType != null) {
                    response.SUCCESSFUL = false;
                    response.setMessage("PatientType Name already exists ");
                    response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                    response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
                    log.debug("PatientType Name already exists ");
                    return response;
                }
            }
            log.debug("PatientType Name does not exists ");
        } catch (Exception e) {
            response = new CreatePatientTypeServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("PatientType Name already exists " + e.getMessage());
            log.error("PatientType Name exists" + e.getMessage());
            return response;
        }
        return response;
    }
}
