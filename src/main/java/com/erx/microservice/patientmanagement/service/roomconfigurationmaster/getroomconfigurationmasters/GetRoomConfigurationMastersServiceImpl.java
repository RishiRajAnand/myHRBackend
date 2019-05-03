package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasters;

/*
* created by Brighty on 16-11-2017
* */


import com.erx.microservice.patientmanagement.domain.RoomConfigurationMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.RoomConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto.RoomConfigurationMasterByClinicLocationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getRoomConfigurationMastersService")
public class GetRoomConfigurationMastersServiceImpl implements GetRoomConfigurationMastersService {

    private final Logger log = LoggerFactory.getLogger(GetRoomConfigurationMastersServiceImpl.class);

    @Autowired
    private RoomConfigurationMasterRepository roomConfigurationMasterRepository;

    public GetRoomConfigurationMastersServiceImpl(RoomConfigurationMasterRepository roomConfigurationMasterRepository) {
        this.roomConfigurationMasterRepository = roomConfigurationMasterRepository;
    }

    //constructor

    @Override
    public GetRoomConfigurationMastersServiceResponse execute(GetRoomConfigurationMastersServiceRequest request) throws ServiceException {

        log.debug("call to get all the RoomConfigurationMasters for the given clinicLocation");
        GetRoomConfigurationMastersServiceResponse response = null;
        List<RoomConfigurationMaster> roomConfigurationMasters = null;
        List<RoomConfigurationMasterByClinicLocationDTO> roomConfigurationMasterByClinicLocationDTOs = new ArrayList<>();

        try {
            if (request.getClinicLocationId() != 0) {
                //retrieve the list of BedTypeMaster
                if(request.getIsActive().isPresent())
                    roomConfigurationMasters = roomConfigurationMasterRepository.findRoomConfigurationMastersByClinicLocationIdAndStatus(
                            request.getClinicLocationId(),request.getIsActive().get());
                else
                    roomConfigurationMasters = roomConfigurationMasterRepository.getRoomConfigurationMastersByClinicLocation(request.getClinicLocationId());
                //set the values in BedTypeMasterByClinicLocationDTO list
                for (RoomConfigurationMaster roomConfigurationMaster : roomConfigurationMasters) {
                    //create BedTypeMasterByClinicLocationDTO object
                    RoomConfigurationMasterByClinicLocationDTO roomConfigurationMasterByClinicLocationDTO = new RoomConfigurationMasterByClinicLocationDTO();
                    //set the values
                    roomConfigurationMasterByClinicLocationDTO.setId(roomConfigurationMaster.getId());
                    roomConfigurationMasterByClinicLocationDTO.setRoomNumber(roomConfigurationMaster.getRoomNumber());

                    //add to the list
                    roomConfigurationMasterByClinicLocationDTOs.add(roomConfigurationMasterByClinicLocationDTO);
                }
            }
            //create response
            response = new GetRoomConfigurationMastersServiceResponse(roomConfigurationMasterByClinicLocationDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved all the RoomConfigurationMasters for the given clinicLocation");
            log.debug("Retrieved all the RoomConfigurationMasters for the given clinicLocation");
        } catch (Exception e) {
            //create response
            response = new GetRoomConfigurationMastersServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Not retrieved all the RoomConfigurationMasters for the given clinicLocation");
            log.error("Not retrieved all the RoomConfigurationMasters for the given clinicLocation");
        }
        return response;
    }
}
