package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.createroomconfigurationmaster;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.BedTypeMasterRepository;
import com.erx.microservice.patientmanagement.repository.ClinicLocationRepository;
import com.erx.microservice.patientmanagement.repository.RoomConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.repository.WardMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto.RoomConfigurationMasterDTO;
import com.erx.microservice.patientmanagement.service.mapper.RoomConfigurationMasterMapper;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("createRoomConfigurationMasterService")
public class CreateRoomConfigurationMasterServiceImpl implements CreateRoomConfigurationMasterService {

    private final Logger log = LoggerFactory.getLogger(CreateRoomConfigurationMasterServiceImpl.class);

    @Autowired
    private RoomConfigurationMasterRepository roomConfigurationMasterRepository;

    @Autowired
    private RoomConfigurationMasterMapper roomConfigurationMasterMapper;

    @Autowired
    private WardMasterRepository wardMasterRepository;

    @Autowired
    private BedTypeMasterRepository bedTypeMasterRepository;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    public CreateRoomConfigurationMasterServiceImpl(RoomConfigurationMasterRepository roomConfigurationMasterRepository,
                                                    RoomConfigurationMasterMapper roomConfigurationMasterMapper,
                                                    WardMasterRepository wardMasterRepository,
                                                    BedTypeMasterRepository bedTypeMasterRepository,
                                                    ClinicLocationRepository clinicLocationRepository) {
        this.roomConfigurationMasterRepository = roomConfigurationMasterRepository;
        this.roomConfigurationMasterMapper = roomConfigurationMasterMapper;
        this.wardMasterRepository = wardMasterRepository;
        this.bedTypeMasterRepository = bedTypeMasterRepository;
        this.clinicLocationRepository = clinicLocationRepository;
    }

    @Override
    public CreateRoomConfigurationMasterServiceResponse execute(CreateRoomConfigurationMasterServiceRequest request) throws ServiceException {

        CreateRoomConfigurationMasterServiceResponse response = null;
        RoomConfigurationMaster roomConfigurationMaster = null;
        RoomConfigurationMasterDTO roomConfigurationMasterDTO = null;
        RoomConfigurationMaster savedRoomConfigurationMaster = null;
        ClinicLocation clinicLocation = null;
        BedTypeMaster bedTypeMaster = null;
        WardMaster wardMaster = null;
        long RoomConfigurationMasterId = 0;
        String roomNumber = null;
        String roomConfigurationGeneratedCode=null;
        try {
            log.debug("Call to create RoomConfigurationMaster");
            if (request.getRoomConfigurationMasterDTO().getRoomNumber() != null &&
                    request.getRoomConfigurationMasterDTO().getClinicLocationId() != 0) {
                //retrieve the object from request
                roomConfigurationMasterDTO = request.getRoomConfigurationMasterDTO();

                //Check this room exist or not
                response = validateRoomNumber(roomConfigurationMasterDTO);
                if (response.SUCCESSFUL == false) {
                    return response;
                }

                //convert DTO to domain
                roomConfigurationMaster = roomConfigurationMasterMapper.
                        roomConfigurationMasterDTOToRoomConfigurationMaster(roomConfigurationMasterDTO);
                //set BedTypeMaster
                bedTypeMaster = bedTypeMasterRepository.findOne(roomConfigurationMasterDTO.getBedTypeMasterId());
                roomConfigurationMaster.setBedTypeMaster(bedTypeMaster);
                //set WardMaster
                wardMaster = wardMasterRepository.findOne(roomConfigurationMasterDTO.getWardMasterId());
                roomConfigurationMaster.setWardMaster(wardMaster);


                if (roomConfigurationMasterDTO.getId() == null || roomConfigurationMasterDTO.getId() == 0) {
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
                    roomConfigurationGeneratedCode = serviceGateway.generateUniqueID(generateUniqueIDDTO);

                    if (roomConfigurationGeneratedCode != null)
                        roomConfigurationMaster.setRoomCode(roomConfigurationGeneratedCode);
                }


                //save RoomConfigurationMaster
                savedRoomConfigurationMaster = roomConfigurationMasterRepository.save(roomConfigurationMaster);

                //set response variables
                RoomConfigurationMasterId = savedRoomConfigurationMaster.getId();
                roomNumber = savedRoomConfigurationMaster.getRoomNumber();
            }
            //create response
            response = new CreateRoomConfigurationMasterServiceResponse(RoomConfigurationMasterId, roomNumber, savedRoomConfigurationMaster.getRoomName());
            response.SUCCESSFUL = true;
            response.setMessage("Created Room with RoomNumber : " + roomNumber);
            log.debug("Created RoomConfigurationMaster with RoomNumber : " + roomNumber);
        } catch (Exception e) {
            //create response
            response = new CreateRoomConfigurationMasterServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to create Room");
            log.error("Failed to create RoomConfigurationMaster");
        }
        return response;
    }

    //constructor

    public CreateRoomConfigurationMasterServiceResponse validateRoomNumber
            (RoomConfigurationMasterDTO roomConfigurationMasterDTO) throws ServiceException {

        CreateRoomConfigurationMasterServiceResponse response = null;
        Optional<RoomConfigurationMaster> optionalRoomConfigurationMaster = null;
        RoomConfigurationMaster roomConfigurationMaster = null;
        try {
            //create response
            response = new CreateRoomConfigurationMasterServiceResponse();
            response.SUCCESSFUL = true;
            if (roomConfigurationMasterDTO.getRoomNumber() != null) {
                //retrieve the object with the given name
//                roomConfigurationMaster = roomConfigurationMasterRepository.validateRoomNumber
//                        (roomConfigurationMasterDTO.getClinicLocationId(), roomConfigurationMasterDTO.getRoomNumber());
                optionalRoomConfigurationMaster = roomConfigurationMasterRepository.
                        findFirst1ByClinicLocationIdAndRoomNumber(roomConfigurationMasterDTO.getClinicLocationId()
                                ,roomConfigurationMasterDTO.getRoomNumber());
                if (optionalRoomConfigurationMaster.isPresent()) {
                    roomConfigurationMaster=optionalRoomConfigurationMaster.get();
                    if (roomConfigurationMasterDTO.getId() == null || roomConfigurationMasterDTO.getId() == 0) {
                        response.SUCCESSFUL = false;
                        response.setMessage("Room Number already exists ");
                        response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                        response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
                        return response;
                    } else if (!roomConfigurationMasterDTO.getRoomCode().equalsIgnoreCase(roomConfigurationMaster.getRoomCode())) {
                        response.SUCCESSFUL = false;
                        response.setMessage("Room Number already exists ");
                        response.setErrorCode(ErxStatus.DUPLICATE_ERROR.getValue());
                        response.setErrorMessage(ErxStatus.DUPLICATE_ERROR.getStatusMessage());
                        return response;
                    }
                }
            }
            log.debug("Room Number does not exists ");
        } catch (Exception e) {
            response = new CreateRoomConfigurationMasterServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("error creating new room -->> " + e.getMessage());
            log.error("error creating new room -->>" + e.getMessage());
            return response;
        }
        return response;
    }
}
