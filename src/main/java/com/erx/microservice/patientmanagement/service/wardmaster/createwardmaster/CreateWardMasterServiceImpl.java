package com.erx.microservice.patientmanagement.service.wardmaster.createwardmaster;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.domain.DepartmentMaster;
import com.erx.microservice.patientmanagement.domain.ErxStatus;
import com.erx.microservice.patientmanagement.domain.WardMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.ClinicLocationRepository;
import com.erx.microservice.patientmanagement.repository.DepartmentMasterRepository;
import com.erx.microservice.patientmanagement.repository.WardMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterDTO;
import com.erx.microservice.patientmanagement.service.mapper.WardMasterMapper;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("createWardMasterService")
public class CreateWardMasterServiceImpl implements CreateWardMasterService {

    private final Logger log = LoggerFactory.getLogger(CreateWardMasterServiceImpl.class);

    @Autowired
    private WardMasterRepository wardMasterRepository;

    @Autowired
    private WardMasterMapper wardMasterMapper;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Autowired
    private DepartmentMasterRepository departmentMasterRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    //constructor
    public CreateWardMasterServiceImpl(WardMasterRepository wardMasterRepository,
                                       WardMasterMapper wardMasterMapper,
                                       ClinicLocationRepository clinicLocationRepository,
                                       DepartmentMasterRepository departmentMasterRepository) {
        this.wardMasterRepository = wardMasterRepository;
        this.wardMasterMapper = wardMasterMapper;
        this.clinicLocationRepository = clinicLocationRepository;
        this.departmentMasterRepository = departmentMasterRepository;
    }

    @Override
    public CreateWardMasterServiceResponse execute(CreateWardMasterServiceRequest request) throws ServiceException {

        CreateWardMasterServiceResponse response = null;
        WardMaster wardMaster = null;
        WardMasterDTO wardMasterDTO = null;
        WardMaster savedWardMaster = null;
        ClinicLocation clinicLocation = null;
        DepartmentMaster departmentMaster = null;
        DepartmentMaster indentDepartment = null;
        DepartmentMaster ipRequestDepartment = null;
        String generatedWardCode =null;
        long wardMasterId = 0;
        String wardCode = null;
        try {
            log.debug("call to create WardMaster");
            if (request.getWardMasterDTO().getClinicLocationId() != 0 && request.getWardMasterDTO().getWardName() != null) {
                //retrieve the object from request
                wardMasterDTO = request.getWardMasterDTO();
                //Check the ward exist or not

                response = validateWardName(wardMasterDTO);
                if (response.SUCCESSFUL == false) {
                    return response;
                }

                //convert DTO to Domain
                wardMaster = wardMasterMapper.wardMasterDTOToWardMaster(wardMasterDTO);

                //set department
                departmentMaster = departmentMasterRepository.findOne(wardMasterDTO.getDepartmentId());
                wardMaster.setDepartment(departmentMaster);

                //set indentDepartment
                indentDepartment = departmentMasterRepository.findOne(wardMasterDTO.getIndentDepartmentId());
                wardMaster.setIndentDepartment(indentDepartment);

                //set ipRequestDepartment
                ipRequestDepartment = departmentMasterRepository.findOne(wardMasterDTO.getIpRequestDepartmentId());
                wardMaster.setIpRequestDepartment(ipRequestDepartment);


                if (wardMasterDTO.getId() == null || wardMasterDTO.getId() == 0) {
                    //retrieve and set clinicId,CurrentTblName and setCurrentClmName to DTO
                    Long clinicId = request.getClinicId();
                    //generate unique id
                    GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
                    GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
                    generateUniqueIDDTO.setCurrentTableName(ErxConstants.BTM_CURRENT_TABLE_NAME);
                    generateUniqueIDDTO.setCurrentColumnName(ErxConstants.BTM_CURRENT_COLUMN_NAME);
                    generateUniqueIDClinicDTO.setId(clinicId);
                    generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
                    //call generateUniqueID
                    generatedWardCode = serviceGateway.generateUniqueID(generateUniqueIDDTO);

                    if (generatedWardCode != null)
                        wardMaster.setWardCode(generatedWardCode);
                }


                //save WardMaster
                savedWardMaster = wardMasterRepository.save(wardMaster);

                //set the response variables;
                wardMasterId = savedWardMaster.getId();
                wardCode = savedWardMaster.getWardCode();
            }
            //create response
            response = new CreateWardMasterServiceResponse(wardMasterId, wardCode);
            response.SUCCESSFUL = true;
            response.setMessage("Created WardMaster Successfully with code : " + wardCode);
            log.debug("Created WardMaster Successfully with code : " + wardCode);
        } catch (Exception e) {
            //create response
            response = new CreateWardMasterServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to create WardMaster");
            log.error("Failed to create WardMaster");
        }
        return response;
    }

    public CreateWardMasterServiceResponse validateWardName(WardMasterDTO wardMasterDTO) throws ServiceException {

        CreateWardMasterServiceResponse response = null;
        WardMaster wardMaster = null;
        try {
            //create response
            response = new CreateWardMasterServiceResponse();
            response.SUCCESSFUL = true;
            if (wardMasterDTO.getWardName() != null) {
                //retrieve the object with the given name
                wardMaster = wardMasterRepository.validateDepartmentName(
                        wardMasterDTO.getClinicLocationId(), wardMasterDTO.getWardName());
                if (wardMaster != null) {
                    if (wardMasterDTO.getId() == null || wardMasterDTO.getId() == 0) {
                        response.SUCCESSFUL = false;
                        response.setMessage("Ward name already exists ");
                        response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                        response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
                        return response;
                    } else if (!wardMasterDTO.getWardCode().equalsIgnoreCase(wardMaster.getWardCode())) {
                        response.SUCCESSFUL = false;
                        response.setMessage("Ward name already exists ");
                        response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                        response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
                        return response;
                    }
                }
            }
            log.debug("Ward Name does not exists ");
        } catch (Exception e) {
            response = new CreateWardMasterServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Ward Name already exists " + e.getMessage());
            log.error("Ward Name exists" + e.getMessage());
            return response;
        }
        return response;
    }
}
