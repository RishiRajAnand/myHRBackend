package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasterbyid;

/*
* created by Brighty on 17-11-2017
* */


import com.erx.microservice.patientmanagement.domain.RoomConfigurationMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.RoomConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto.RoomConfigurationMasterByIdDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("getRoomConfigurationMasterByIdService")
public class GetRoomConfigurationMasterByIdServiceImpl implements GetRoomConfigurationMasterByIdService {

    private final Logger log = LoggerFactory.getLogger(GetRoomConfigurationMasterByIdServiceImpl.class);

    @Autowired
    private RoomConfigurationMasterRepository roomConfigurationMasterRepository;

    public GetRoomConfigurationMasterByIdServiceImpl(RoomConfigurationMasterRepository roomConfigurationMasterRepository) {
        this.roomConfigurationMasterRepository = roomConfigurationMasterRepository;
    }

    //constructor

    @Override
    public GetRoomConfigurationMasterByIdServiceResponse execute(GetRoomConfigurationMasterByIdServiceRequest request) throws ServiceException {

        GetRoomConfigurationMasterByIdServiceResponse response = null;
        RoomConfigurationMaster roomConfigurationMaster = null;
        RoomConfigurationMasterByIdDTO roomConfigurationMasterByIdDTO = new RoomConfigurationMasterByIdDTO();
        try {
            log.debug("Call to retrieve RoomConfigurationMaster with id : " + request.getRoomConfigurationMasterId());
            if (request.getRoomConfigurationMasterId() != 0) {
                //retrieve RoomConfigurationMaster
                roomConfigurationMaster = roomConfigurationMasterRepository.findOne(request.getRoomConfigurationMasterId());
                //set roomConfigurationMasterByIdDTO
                roomConfigurationMasterByIdDTO.setId(roomConfigurationMaster.getId());
                roomConfigurationMasterByIdDTO.setRoomCode(roomConfigurationMaster.getRoomCode());
                roomConfigurationMasterByIdDTO.setRoomNumber(roomConfigurationMaster.getRoomNumber());
                roomConfigurationMasterByIdDTO.setRoomName(roomConfigurationMaster.getRoomName());
                roomConfigurationMasterByIdDTO.setStatus(roomConfigurationMaster.isStatus());
                roomConfigurationMasterByIdDTO.setClinicLocationId(roomConfigurationMaster.getClinicLocationId());
                roomConfigurationMasterByIdDTO.setBedTypeMasterId(roomConfigurationMaster.getBedTypeMaster().getId());
                roomConfigurationMasterByIdDTO.setBedTypeMasterName(roomConfigurationMaster.getBedTypeMaster().getBedTypeName());
                roomConfigurationMasterByIdDTO.setWardMasterId(roomConfigurationMaster.getWardMaster().getId());
                roomConfigurationMasterByIdDTO.setWardMasterName(roomConfigurationMaster.getWardMaster().getWardName());
            }
            //create response
            response = new GetRoomConfigurationMasterByIdServiceResponse(roomConfigurationMasterByIdDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Room successfully with Number : " + roomConfigurationMaster.getRoomNumber());
            log.debug("Retrieved RoomMasterConfiguration successfully with Number : " + roomConfigurationMaster.getRoomNumber());
        } catch (Exception e) {
            //create response
            response = new GetRoomConfigurationMasterByIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Room");
            log.error("Failed to retrieve RoomMasterConfiguration");
        }
        return response;
    }
}
