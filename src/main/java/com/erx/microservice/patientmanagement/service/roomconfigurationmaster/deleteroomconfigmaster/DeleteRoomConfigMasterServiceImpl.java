package com.erx.microservice.patientmanagement.service.roomconfigurationmaster.deleteroomconfigmaster;


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.domain.RoomConfigurationMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.RoomConfigurationMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("deleteRoomConfigMasterService")
public class DeleteRoomConfigMasterServiceImpl implements DeleteRoomConfigMasterService {

    private final Logger log = LoggerFactory.getLogger(DeleteRoomConfigMasterServiceImpl.class);

    @Autowired
    RoomConfigurationMasterRepository roomConfigurationMasterRepository;

    DeleteRoomConfigMasterServiceResponse response;

    RoomConfigurationMaster roomConfigurationMaster;

    @Override
    public DeleteRoomConfigMasterServiceResponse execute(DeleteRoomConfigMasterServiceRequest request) throws ServiceException {

        log.debug("DeleteRoomConfigMasterServiceImpl ==> Call to room config Master");


        try {
            if (request.getRoomConfigMasterId() != null || request.getRoomConfigMasterId() != 0) {

                //find the ward object

                roomConfigurationMaster = roomConfigurationMasterRepository.findOne(request.getRoomConfigMasterId());

                if (roomConfigurationMaster != null && roomConfigurationMaster.getRecordStatus()!=0) {

                    roomConfigurationMaster.setRecordStatus(0);

                    //save updated WARD master

                    roomConfigurationMasterRepository.save(roomConfigurationMaster);

                    //create response
                    response = createResponse(true, Constants.ROOM_CONFIG_MASTER_DELETE_SUCCESS);
                    log.debug("DeleteRoomConfigMasterServiceImpl ==> SUCCESS ");
                    return response;


                }


            }

            //create response
            response = createResponse(true, Constants.ROOM_CONFIG_MASTER_DELETE_FAILURE_INVALID_INPUT);
            log.debug("DeleteRoomConfigMasterServiceImpl ==> FAILED due to invalid input ");
            return response;


        } catch (Exception e) {

            response = createResponse(true, Constants.ROOM_CONFIG_MASTER_DELETE_FAILURE);
            log.debug("DeleteRoomConfigMasterServiceImpl ==> FAILED  DUE TO EXCEPTION " + e);

        }

        return response;
    }


    private DeleteRoomConfigMasterServiceResponse createResponse(boolean successful, String message) {

        DeleteRoomConfigMasterServiceResponse response = new DeleteRoomConfigMasterServiceResponse();
        response.SUCCESSFUL = successful;
        response.setMessage(message);
        return response;
    }


}
