package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmastersbycliniclocation;

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

import java.util.ArrayList;
import java.util.List;

@Service("getRoomConfigurationMasterByClinicLocationService")
public class GetRoomConfigurationMasterByClinicLocationServiceImpl implements GetRoomConfigurationMasterByClinicLocationService {

    private final Logger log = LoggerFactory.getLogger(GetRoomConfigurationMasterByClinicLocationServiceImpl.class);

    @Autowired
    private RoomConfigurationMasterRepository roomConfigurationMasterRepository;

    public GetRoomConfigurationMasterByClinicLocationServiceImpl(RoomConfigurationMasterRepository roomConfigurationMasterRepository) {
        this.roomConfigurationMasterRepository = roomConfigurationMasterRepository;
    }

    //constructor

    @Override
    public GetRoomConfigurationMasterByClinicLocationServiceResponse execute(GetRoomConfigurationMasterByClinicLocationServiceRequest request) throws ServiceException {

        GetRoomConfigurationMasterByClinicLocationServiceResponse response = null;
        List<RoomConfigurationMaster> roomConfigurationMasters;
        List<RoomConfigurationMasterByIdDTO> roomConfigurationMasterByIdDTOs = new ArrayList<>();
        try {
            log.debug("Call to Retrieve RoomConfigurationMasters");
            if (request.getClinicLocationId() != 0) {
                //retrieve the RoomConfigurationMasters
                roomConfigurationMasters = roomConfigurationMasterRepository.getRoomConfigurationMastersByClinicLocation(request.getClinicLocationId());
                //set values of DTO
                for (RoomConfigurationMaster roomConfigurationMaster : roomConfigurationMasters) {
                    RoomConfigurationMasterByIdDTO roomConfigurationMasterByIdDTO = new RoomConfigurationMasterByIdDTO();
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

                    //add to list
                    roomConfigurationMasterByIdDTOs.add(roomConfigurationMasterByIdDTO);
                }
            }
            //create response
            response = new GetRoomConfigurationMasterByClinicLocationServiceResponse(roomConfigurationMasterByIdDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved all Rooms");
            log.debug("Retrieved all RoomConfigurationMasters");
        } catch (Exception e) {
            //create response
            response = new GetRoomConfigurationMasterByClinicLocationServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Rooms");
            log.error("Failed to retrieve RoomConfigurationMasters");
        }
        return response;
    }
}
