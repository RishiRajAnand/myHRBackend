package com.erx.microservice.patientmanagement.web.rest;


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardByDepartmentInputDTO;
import com.erx.microservice.patientmanagement.service.dto.warddto.WardMasterDTO;
import com.erx.microservice.patientmanagement.service.wardmaster.createwardmaster.CreateWardMasterService;
import com.erx.microservice.patientmanagement.service.wardmaster.createwardmaster.CreateWardMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.wardmaster.createwardmaster.CreateWardMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.wardmaster.deletewardmaster.DeleteWardMasterService;
import com.erx.microservice.patientmanagement.service.wardmaster.deletewardmaster.DeleteWardMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.wardmaster.deletewardmaster.DeleteWardMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmasterbyid.GetWardMasterByIdService;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmasterbyid.GetWardMasterByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmasterbyid.GetWardMasterByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmasters.GetWardMastersService;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmasters.GetWardMastersServiceRequest;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmasters.GetWardMastersServiceResponse;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbycliniclocation.GetWardMastersByClinicLocationService;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbycliniclocation.GetWardMastersByClinicLocationServiceRequest;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbycliniclocation.GetWardMastersByClinicLocationServiceResponse;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydaycareservice.GetWardMastersByDaycareService;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydaycareservice.GetWardMastersByDaycareServiceRequest;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydaycareservice.GetWardMastersByDaycareServiceResponse;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydepartment.GetWardMastersByDepartmentService;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydepartment.GetWardMastersByDepartmentServiceRequest;
import com.erx.microservice.patientmanagement.service.wardmaster.getwardmastersbydepartment.GetWardMastersByDepartmentServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
* created by Brighty on 16-11-2017
* */

@RestController
@RequestMapping("/api")
public class WardMasterController {

    private final Logger log = LoggerFactory.getLogger(WardMasterController.class);
    @Autowired
    DeleteWardMasterService deleteWardMasterService;
    @Autowired
    private GetWardMastersService getWardMastersService;
    @Autowired
    private CreateWardMasterService createWardMasterService;
    @Autowired
    private GetWardMasterByIdService getWardMasterByIdService;
    @Autowired
    private GetWardMastersByClinicLocationService getWardMastersByClinicLocationService;
    @Autowired
    private GetWardMastersByDepartmentService getWardMastersByDepartmentService;
    @Autowired
    private GetWardMastersByDaycareService getWardMastersByDaycareService;

    //To get all WardMasters as a list
    @RequestMapping(value = "/getAllWardMasters", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllWardMasters(@RequestParam Long clinicLocationId) {
        log.debug("REST request to get WardMaster");
        GetWardMastersServiceResponse response = null;
        try {
            GetWardMastersServiceRequest request = new GetWardMastersServiceRequest(clinicLocationId);
            response = getWardMastersService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve WardMasters");
            log.error("Failed to retrieve WardMasters");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To create WardMaster
    @RequestMapping(value = "/createWardMaster", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createWardMaster(@RequestBody WardMasterDTO wardMasterDTO, @RequestParam Long clinicId) {
        log.debug("REST request to create WardMaster");
        CreateWardMasterServiceResponse response = null;
        try {
            CreateWardMasterServiceRequest request = new CreateWardMasterServiceRequest(wardMasterDTO, clinicId);
            response = createWardMasterService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to create WardMaster");
            log.error("Failed to create WardMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get WardMaster by id
    @RequestMapping(value = "/getWardMasterById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getWardMasterById(@RequestParam Long wardMasterId) {
        log.debug("REST request to get WardMaster");
        GetWardMasterByIdServiceResponse response = null;
        try {
            GetWardMasterByIdServiceRequest request = new GetWardMasterByIdServiceRequest(wardMasterId);
            response = getWardMasterByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve WardMaster");
            log.error("Failed to retrieve WardMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get WardMasters by ClinicLocation
    @RequestMapping(value = "/getWardMasterByClinicLocation", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getWardMasterByClinicLocation(@RequestParam Long clinicLocationId,
                                                        @RequestParam(required = false) Optional<Boolean> isActive) {
        log.debug("REST request to get WardMasters");
        GetWardMastersByClinicLocationServiceResponse response = null;
        try {
            GetWardMastersByClinicLocationServiceRequest request = new
                    GetWardMastersByClinicLocationServiceRequest(clinicLocationId, isActive);
            response = getWardMastersByClinicLocationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve WardMasters");
            log.error("Failed to retrieve WardMasters");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get all WardMasters as a list by Department
    @RequestMapping(value = "/getAllWardMastersByDepartment", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getAllWardMastersByDepartment(@RequestBody List<WardByDepartmentInputDTO> wardByDepartmentInputDTOs) {
        log.debug("REST request to get WardMasters By Department");
        GetWardMastersByDepartmentServiceResponse response = null;
        try {
            GetWardMastersByDepartmentServiceRequest request = new GetWardMastersByDepartmentServiceRequest(wardByDepartmentInputDTOs);
            response = getWardMastersByDepartmentService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve WardMasters By Department");
            log.error("Failed to retrieve WardMasters By Department");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get all WardMasters as a list by Daycare value
    @RequestMapping(value = "/getWardMastersByDaycare", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getWardMastersByDaycare(@RequestParam Long clinicLocationId, @RequestParam boolean isDaycare) {
        log.debug("REST request to get WardMaster by daycare is " + isDaycare);
        GetWardMastersByDaycareServiceResponse response = null;
        try {
            GetWardMastersByDaycareServiceRequest request = new GetWardMastersByDaycareServiceRequest(clinicLocationId, isDaycare);
            response = getWardMastersByDaycareService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve WardMasters by daycare is " + isDaycare);
            log.error("Failed to retrieve WardMasters by daycare is " + isDaycare);
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }



    //To delete WardMaster
    @RequestMapping(value = "/deleteWardMaster", method = RequestMethod.DELETE, produces = "application/json")

    public ResponseEntity deleteWardMaster(@RequestParam Long wardMasterId) {

        log.debug("request to delete Ward Master");

        DeleteWardMasterServiceResponse response = new DeleteWardMasterServiceResponse();

        try {

            DeleteWardMasterServiceRequest request = new DeleteWardMasterServiceRequest(wardMasterId);
            response = deleteWardMasterService.execute(request);

        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage(Constants.WARD_MASTER_DELETE_FAILURE);
            log.error("Failed to delete WardMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }







}