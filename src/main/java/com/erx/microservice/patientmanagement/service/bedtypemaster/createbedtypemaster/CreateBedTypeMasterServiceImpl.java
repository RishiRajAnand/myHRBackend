package com.erx.microservice.patientmanagement.service.bedtypemaster.createbedtypemaster;


import com.erx.microservice.patientmanagement.domain.BedTypeMaster;
import com.erx.microservice.patientmanagement.domain.ErxStatus;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.BedTypeMasterRepository;
import com.erx.microservice.patientmanagement.repository.ClinicLocationRepository;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterDTO;
import com.erx.microservice.patientmanagement.service.mapper.BedTypeMasterMapper;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import com.erx.microservice.patientmanagement.web.rest.util.BedTypeMasterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * created by Brighty on 17-11-2017
 * */

@Service("createBedTypeMasterService")
public class CreateBedTypeMasterServiceImpl implements CreateBedTypeMasterService {

    private final Logger log = LoggerFactory.getLogger(CreateBedTypeMasterServiceImpl.class);

    @Autowired
    private BedTypeMasterRepository bedTypeMasterRepository;

    @Autowired
    private BedTypeMasterMapper bedTypeMasterMapper;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    public CreateBedTypeMasterServiceImpl(BedTypeMasterRepository bedTypeMasterRepository,
                                          BedTypeMasterMapper bedTypeMasterMapper,
                                          ClinicLocationRepository clinicLocationRepository) {
        this.bedTypeMasterRepository = bedTypeMasterRepository;
        this.bedTypeMasterMapper = bedTypeMasterMapper;
        this.clinicLocationRepository = clinicLocationRepository;
    }


    @Override
    public CreateBedTypeMasterServiceResponse execute(CreateBedTypeMasterServiceRequest request) throws ServiceException {
        CreateBedTypeMasterServiceResponse response = null;
        BedTypeMaster bedTypeMaster = null;
        BedTypeMasterDTO bedTypeMasterDTO = null;
        Long clinicId = null;
        String bedTypeGeneratedCode=null;
        try {
            log.debug("Inside CreateBedTypeMasterServiceImpl to create/update BedTypeMaster");
            //check for mandatory fields
            if (request.getBedTypeMasterDTO().getBedTypeName() != null && !request.getBedTypeMasterDTO().getBedTypeName().isEmpty()
                    && request.getBedTypeMasterDTO().getClinicLocationId() != null && request.getClinicId() != null) {
                //convert dto to entity
                bedTypeMaster = bedTypeMasterMapper.bedTypeMasterDTOToBedTypeMaster(request.getBedTypeMasterDTO());
                //saving bedType details
                if (request.getBedTypeMasterDTO().getId() == null || request.getBedTypeMasterDTO().getId() == 0) {
                    response = checkDuplicateBedTypeName(request.getBedTypeMasterDTO()); //call method to check duplicate data
                    if (response.SUCCESSFUL) {
                        if (request.getBedTypeMasterDTO().getSequenceOrderNo() != null) {
                            response = checkSequenceOrderNoDuplication(request.getBedTypeMasterDTO()); //call method to check duplicate input
                            if (response.SUCCESSFUL) {
                                //generate unique id
                                clinicId = request.getClinicId();
                                GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
                                GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
                                generateUniqueIDDTO.setCurrentTableName(ErxConstants.BTM_CURRENT_TABLE_NAME);
                                generateUniqueIDDTO.setCurrentColumnName(ErxConstants.BTM_CURRENT_COLUMN_NAME);
                                generateUniqueIDClinicDTO.setId(clinicId);
                                generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
                                //call generateUniqueID
                                bedTypeGeneratedCode = serviceGateway.generateUniqueID(generateUniqueIDDTO);

                                //String uniqueId = uniqueIDResponse.getGeneratedId();
                                if (bedTypeGeneratedCode != null)
                                    bedTypeMaster.setBedTypeCode(bedTypeGeneratedCode);
                                //save BedTypeMaster and convert entity to dto
                                bedTypeMaster=bedTypeMasterRepository.save(bedTypeMaster);
                                bedTypeMasterDTO = bedTypeMasterMapper.bedTypeMasterToBedTypeMasterDTO(bedTypeMaster);

                                if (request.getBedTypeMasterDTO().getId() == null) {
                                    serviceGateway.mapNewBedTypeWithAllServices(bedTypeMaster.getId());
                                    serviceGateway.mapNewBedTypeWithAllMedicineTypes(bedTypeMaster.getId());
                                }
                                //construct  success response
                                return constructResponse(true, BedTypeMasterConstants.BED_TYPE_MASTER_SAVE_SUCCESS
                                        , bedTypeMasterDTO, 200);
                            } else //construct response for duplicate sequenceOrderNo
                                return response;
                        } else
                            return constructResponse(false, BedTypeMasterConstants.INVALID_SEQUENCE_ORDER_NO,
                                    bedTypeMasterDTO, 406);
                    } else {
                        return response;
                    }
                } else { //to update bedType details
                    //save BedTypeMaster and convert entity to dto and return
                    bedTypeMasterDTO = bedTypeMasterMapper.bedTypeMasterToBedTypeMasterDTO(bedTypeMasterRepository.save(bedTypeMaster));
                    return constructResponse(true, BedTypeMasterConstants.BED_TYPE_MASTER_UPDATE_SUCCESS,
                            bedTypeMasterDTO, 200);
                }

            } else //for invalid input construct response
                return constructResponse(false, BedTypeMasterConstants.INVALID_INPUT, bedTypeMasterDTO, 400);
        } catch (Exception e) {
            response = constructResponse(false, BedTypeMasterConstants.BED_TYPE_MASTER_SAVE_OR_UPDATE_SUCCESS,
                    bedTypeMasterDTO, 500);
            log.error(response.getMessage());
        }
        return response;
    }

    //method to check duplicate sequenceOrderNo
    private CreateBedTypeMasterServiceResponse checkSequenceOrderNoDuplication(BedTypeMasterDTO bedTypeMasterDTO)
            throws ServiceException {
        CreateBedTypeMasterServiceResponse response = new CreateBedTypeMasterServiceResponse();
        response.SUCCESSFUL = true;
        int isExists = bedTypeMasterRepository.existsBySequenceOrderNo(bedTypeMasterDTO.getSequenceOrderNo(), bedTypeMasterDTO.getClinicLocationId());
        if (isExists > 0)
            return constructResponse(false, BedTypeMasterConstants.DUPLICATE_SEQUENCE_ORDER_NO + bedTypeMasterDTO.getSequenceOrderNo(),
                    bedTypeMasterDTO, ErxStatus.DUPLICATE_ERROR.getValue());
        else
            return response;
    }


    //method for duplicate data validation(bedTypeName)
    private CreateBedTypeMasterServiceResponse checkDuplicateBedTypeName(BedTypeMasterDTO bedTypeMasterDTO) throws ServiceException {
        CreateBedTypeMasterServiceResponse response = new CreateBedTypeMasterServiceResponse();
        response.SUCCESSFUL = true;
        //check duplicate bed type details exists or not
        boolean isBedTypeMasterExists = bedTypeMasterRepository.
                existsByBedTypeNameAndClinicLocationIdAndIsDaycareAndRecordStatus
                        (bedTypeMasterDTO.getBedTypeName(), bedTypeMasterDTO.getClinicLocationId(), bedTypeMasterDTO.isDaycare()
                                , 1);
        if (isBedTypeMasterExists) {
            //if existing bedTypeName same as input bedType then return error message
            return constructResponse(false, BedTypeMasterConstants.DUPLICATE_BED_TYPE
                            + bedTypeMasterDTO.getBedTypeName(), bedTypeMasterDTO,
                    ErxStatus.DUPLICATE_ERROR.getValue());

        }
        return response;
    }

    //method to construct response
    private CreateBedTypeMasterServiceResponse constructResponse(boolean isSuccess, String message,
                                                                 BedTypeMasterDTO bedTypeMasterDTO, int errorCode) throws ServiceException {
        CreateBedTypeMasterServiceResponse response = null;
        log.debug(message);
        if (isSuccess) { //set bedTypeMasterDTO when isSuccess is true
            response = new CreateBedTypeMasterServiceResponse(bedTypeMasterDTO);
            response.SUCCESSFUL = isSuccess;
            response.setMessage(message);
        } else {
            response = new CreateBedTypeMasterServiceResponse();
            response.SUCCESSFUL = isSuccess;
            response.setMessage(message);
            response.setErrorCode(errorCode);
            response.setErrorMessage(message);
        }
        return response;
    }
}