package com.erx.microservice.patientmanagement.web.rest;


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.service.dto.roomconfigurationdto.RoomConfigurationMasterDTO;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.createroomconfigurationmaster.CreateRoomConfigurationMasterService;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.createroomconfigurationmaster.CreateRoomConfigurationMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.createroomconfigurationmaster.CreateRoomConfigurationMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.deleteroomconfigmaster.DeleteRoomConfigMasterService;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.deleteroomconfigmaster.DeleteRoomConfigMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.deleteroomconfigmaster.DeleteRoomConfigMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasterbyid.GetRoomConfigurationMasterByIdService;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasterbyid.GetRoomConfigurationMasterByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasterbyid.GetRoomConfigurationMasterByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasters.GetRoomConfigurationMastersService;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasters.GetRoomConfigurationMastersServiceRequest;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmasters.GetRoomConfigurationMastersServiceResponse;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmastersbycliniclocation.GetRoomConfigurationMasterByClinicLocationService;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmastersbycliniclocation.GetRoomConfigurationMasterByClinicLocationServiceRequest;
import com.erx.microservice.patientmanagement.service.roomconfigurationmaster.getroomconfigurationmastersbycliniclocation.GetRoomConfigurationMasterByClinicLocationServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/*
* created by Brighty on 16-11-2017
* */

@RestController
@RequestMapping("/api")
public class RoomConfigurationMasterController {

    private final Logger log = LoggerFactory.getLogger(RoomConfigurationMasterController.class);
    @Autowired
    DeleteRoomConfigMasterService deleteRoomConfigMasterService;
    @Autowired
    private GetRoomConfigurationMastersService getRoomConfigurationMastersService;
    @Autowired
    private GetRoomConfigurationMasterByClinicLocationService getRoomConfigurationMasterByClinicLocationService;
    @Autowired
    private GetRoomConfigurationMasterByIdService getRoomConfigurationMasterByIdService;
    @Autowired
    private CreateRoomConfigurationMasterService createRoomConfigurationMasterService;

    //To get all RoomConfigurationMasters as a list
    @RequestMapping(value = "/getAllRoomConfigurationMasters", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllRoomConfigurationMasters(@RequestParam Long clinicLocationId,
                                                         @RequestParam(required = false)Optional<Boolean> isActive) {

        log.debug("REST request to get RoomConfigurationMasters");
        GetRoomConfigurationMastersServiceResponse response = null;
        try {
            GetRoomConfigurationMastersServiceRequest request = new GetRoomConfigurationMastersServiceRequest(clinicLocationId, isActive);
            response = getRoomConfigurationMastersService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve RoomConfigurationMasters");
            log.error("Failed to retrieve RoomConfigurationMasters");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To create RoomConfigurationMaster
    @RequestMapping(value = "/createRoomConfigurationMaster", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createRoomConfigurationMaster(@RequestBody RoomConfigurationMasterDTO roomConfigurationMasterDTO,
                                                        @RequestParam Long clinicId) {
        log.debug("REST request to create RoomConfigurationMaster");
        CreateRoomConfigurationMasterServiceResponse response = null;
        try {
            CreateRoomConfigurationMasterServiceRequest request = new
                    CreateRoomConfigurationMasterServiceRequest(roomConfigurationMasterDTO, clinicId);
            response = createRoomConfigurationMasterService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to create RoomConfigurationMaster");
            log.error("Failed to create RoomConfigurationMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get RoomConfigurationMaster by id
    @RequestMapping(value = "/getRoomConfigurationMasterById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getRoomConfigurationMasterById(@RequestParam Long roomConfigurationMasterId) {
        log.debug("REST request to get RoomConfigurationMaster");
        GetRoomConfigurationMasterByIdServiceResponse response = null;
        try {
            GetRoomConfigurationMasterByIdServiceRequest request = new
                    GetRoomConfigurationMasterByIdServiceRequest(roomConfigurationMasterId);
            response = getRoomConfigurationMasterByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve RoomConfigurationMaster");
            log.error("Failed to retrieve RoomConfigurationMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get RoomConfigurationMasters by ClinicLocation
    @RequestMapping(value = "/getRoomConfigurationMasterByClinicLocation", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getRoomConfigurationMasterByClinicLocation(@RequestParam Long clinicLocationId) {
        log.debug("REST request to get RoomConfigurationMasters");
        GetRoomConfigurationMasterByClinicLocationServiceResponse response = null;
        try {
            GetRoomConfigurationMasterByClinicLocationServiceRequest request = new
                    GetRoomConfigurationMasterByClinicLocationServiceRequest(clinicLocationId);
            response = getRoomConfigurationMasterByClinicLocationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve RoomConfigurationMasters");
            log.error("Failed to retrieve RoomConfigurationMasters");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }



    //To delete Room ConfigMaster
    @RequestMapping(value = "/deleteRoomConfigMaster", method = RequestMethod.DELETE, produces = "application/json")

    public ResponseEntity deleteRoomConfigMaster(@RequestParam Long roomConfigMasterId) {

        log.debug("request to delete room Config  Master");

        DeleteRoomConfigMasterServiceResponse response = new DeleteRoomConfigMasterServiceResponse();

        try {

            DeleteRoomConfigMasterServiceRequest request = new DeleteRoomConfigMasterServiceRequest(roomConfigMasterId);
            response = deleteRoomConfigMasterService.execute(request);

        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage(Constants.ROOM_CONFIG_MASTER_DELETE_FAILURE);
            log.error("Failed to delete room config Master");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }





}