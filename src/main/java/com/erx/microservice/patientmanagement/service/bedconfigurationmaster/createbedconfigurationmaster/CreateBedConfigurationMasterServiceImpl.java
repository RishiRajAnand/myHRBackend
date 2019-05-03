package com.erx.microservice.patientmanagement.service.bedconfigurationmaster.createbedconfigurationmaster;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto.BedConfigurationMasterDTO;
import com.erx.microservice.patientmanagement.service.mapper.BedConfigurationMasterMapper;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("createBedConfigurationMasterService")
public class CreateBedConfigurationMasterServiceImpl implements CreateBedConfigurationMasterService {

    private final Logger log = LoggerFactory.getLogger(CreateBedConfigurationMasterServiceImpl.class);

    @Autowired
    private BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Autowired
    private BedConfigurationMasterMapper bedConfigurationMasterMapper;

    @Autowired
    private BedTypeMasterRepository bedTypeMasterRepository;

    @Autowired
    private WardMasterRepository wardMasterRepository;

    @Autowired
    private RoomConfigurationMasterRepository roomConfigurationMasterRepository;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    public CreateBedConfigurationMasterServiceImpl(BedConfigurationMasterRepository bedConfigurationMasterRepository,
                                                   BedConfigurationMasterMapper bedConfigurationMasterMapper,
                                                   BedTypeMasterRepository bedTypeMasterRepository,
                                                   WardMasterRepository wardMasterRepository,
                                                   RoomConfigurationMasterRepository roomConfigurationMasterRepository,
                                                   ClinicLocationRepository clinicLocationRepository) {
        this.bedConfigurationMasterRepository = bedConfigurationMasterRepository;
        this.bedConfigurationMasterMapper = bedConfigurationMasterMapper;
        this.bedTypeMasterRepository = bedTypeMasterRepository;
        this.wardMasterRepository = wardMasterRepository;
        this.roomConfigurationMasterRepository = roomConfigurationMasterRepository;
        this.clinicLocationRepository = clinicLocationRepository;
    }

    @Override
    public CreateBedConfigurationMasterServiceResponse execute(CreateBedConfigurationMasterServiceRequest request) throws ServiceException {

        CreateBedConfigurationMasterServiceResponse response = null;
        BedConfigurationMaster bedConfigurationMaster = null;
        BedConfigurationMasterDTO bedConfigurationMasterDTO = null;
        BedConfigurationMaster savedBedConfigurationMaster = null;
        BedTypeMaster bedTypeMaster = null;
        WardMaster wardMaster = null;
        ClinicLocation clinicLocation = null;
        RoomConfigurationMaster roomConfigurationMaster = null;
        long bedConfigurationMasterId = 0;
        String bedCode = null;
        String generatedBedCodeId=null;
        try {
            log.debug("Call to create BedConfigurationMaster");
            if (request.getBedConfigurationMasterDTO().getBedNumber() != null
                    && request.getBedConfigurationMasterDTO().getBedTypeMasterId() != 0) {
                //retrieve the object
                bedConfigurationMasterDTO = request.getBedConfigurationMasterDTO();

                //Check for the duplication
                response = checkDuplication(bedConfigurationMasterDTO);
                if (!response.SUCCESSFUL) {
                    log.debug("Failed to save Bed.Bed already exist.");
                    return response;
                }

                //convert DTO to domain
                bedConfigurationMaster = bedConfigurationMasterMapper.bedConfigurationMasterDTOToBedConfigurationMaster(bedConfigurationMasterDTO);
                //set BedTypeMaster in bedConfigurationMaster
                bedTypeMaster = bedTypeMasterRepository.findOne(bedConfigurationMasterDTO.getBedTypeMasterId());
                bedConfigurationMaster.setBedTypeMaster(bedTypeMaster);
                //set WardMaster in bedConfigurationMaster
                wardMaster = wardMasterRepository.findOne(bedConfigurationMasterDTO.getWardMasterId());
                bedConfigurationMaster.setWardMaster(wardMaster);
                //set roomConfigurationMaster in bedConfigurationMaster
                roomConfigurationMaster = roomConfigurationMasterRepository.findOne(bedConfigurationMasterDTO.getRoomConfigurationMasterId());
                bedConfigurationMaster.setRoomConfigurationMaster(roomConfigurationMaster);


                if (bedConfigurationMasterDTO.getId() == null || bedConfigurationMasterDTO.getId() == 0) {
                    //retrieve and set clinicId,CurrentTblName and setCurrentClmName to DTO
                    Long clinicId = request.getClinicId();
                    GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
                    GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
                    generateUniqueIDDTO.setCurrentTableName(ErxConstants.BTM_CURRENT_TABLE_NAME);
                    generateUniqueIDDTO.setCurrentColumnName(ErxConstants.BTM_CURRENT_COLUMN_NAME);
                    generateUniqueIDClinicDTO.setId(clinicId);
                    generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
                    //call generateUniqueID
                    generatedBedCodeId = serviceGateway.generateUniqueID(generateUniqueIDDTO);

                    if (generatedBedCodeId != null)
                        bedConfigurationMaster.setBedCode(generatedBedCodeId);
                }


                //save bedConfigurationMaster
                savedBedConfigurationMaster = bedConfigurationMasterRepository.save(bedConfigurationMaster);

                //create response object
                bedConfigurationMasterId = savedBedConfigurationMaster.getId();
                bedCode = savedBedConfigurationMaster.getBedCode();
            }
            //create response
            response = new CreateBedConfigurationMasterServiceResponse(bedConfigurationMasterId, bedCode);
            response.SUCCESSFUL = true;
            response.setMessage("Created BedConfigurationMaster Successfully with BedCode : " + bedCode);
            log.debug("Created BedConfigurationMaster Successfully with BedCode : " + bedCode);
        } catch (Exception e) {
            //create response
            response = new CreateBedConfigurationMasterServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to create BedConfigurationMaster");
            log.error("Failed to create BedConfigurationMaster");
        }
        return response;
    }

    //constructor

    private CreateBedConfigurationMasterServiceResponse checkDuplication
            (BedConfigurationMasterDTO bedConfigurationMasterDTO) throws Exception {
        CreateBedConfigurationMasterServiceResponse response = new CreateBedConfigurationMasterServiceResponse();
        response.SUCCESSFUL = true;
        BedConfigurationMaster bedConfigurationMaster = bedConfigurationMasterRepository.checkDuplication
                (bedConfigurationMasterDTO.getClinicLocationId(), bedConfigurationMasterDTO.getBedNumber(), bedConfigurationMasterDTO.getBedTypeMasterId(),
                        bedConfigurationMasterDTO.getRoomConfigurationMasterId(), bedConfigurationMasterDTO.getWardMasterId());
        if (bedConfigurationMaster != null) {
            if (bedConfigurationMasterDTO.getId() == null || bedConfigurationMasterDTO.getId() == 0) {
                response.SUCCESSFUL = false;
                response.setMessage("Bed already exists ");
                response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
                return response;
            } else if (!bedConfigurationMaster.getBedCode().equalsIgnoreCase(bedConfigurationMasterDTO.getBedCode())) {
                response.SUCCESSFUL = false;
                response.setMessage("Bed already exists ");
                response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
                return response;
            }
        }
        return response;
    }
}
