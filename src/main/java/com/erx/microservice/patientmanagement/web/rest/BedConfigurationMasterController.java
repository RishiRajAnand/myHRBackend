package com.erx.microservice.patientmanagement.web.rest;


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.createbedconfigurationmaster.CreateBedConfigurationMasterService;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.createbedconfigurationmaster.CreateBedConfigurationMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.createbedconfigurationmaster.CreateBedConfigurationMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.deletebedconfigmaster.DeleteBedConfigMasterService;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.deletebedconfigmaster.DeleteBedConfigMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.deletebedconfigmaster.DeleteBedConfigMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbycliniclocation.GetBedConfigurationMastersByClinicLocationService;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbycliniclocation.GetBedConfigurationMastersByClinicLocationServiceRequest;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbycliniclocation.GetBedConfigurationMastersByClinicLocationServiceResponse;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbyid.GetBedConfigurationMasterByIdService;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbyid.GetBedConfigurationMasterByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasterbyid.GetBedConfigurationMasterByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasters.GetBedConfigurationMastersService;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasters.GetBedConfigurationMastersServiceRequest;
import com.erx.microservice.patientmanagement.service.bedconfigurationmaster.getbedconfigurationmasters.GetBedConfigurationMastersServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.bedconfigurationdto.BedConfigurationMasterDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* created by Brighty on 16-11-2017
* */

@RestController
@RequestMapping("/api")
public class BedConfigurationMasterController {

    private final Logger log = LoggerFactory.getLogger(BedConfigurationMasterController.class);
    @Autowired
    DeleteBedConfigMasterService deleteBedConfigMasterService;
    @Autowired
    private GetBedConfigurationMastersService getBedConfigurationMastersService;
    @Autowired
    private GetBedConfigurationMasterByIdService getBedConfigurationMasterByIdService;
    @Autowired
    private CreateBedConfigurationMasterService createBedConfigurationMasterService;
    @Autowired
    private GetBedConfigurationMastersByClinicLocationService getBedConfigurationMastersByClinicLocationService;

    //To get all BedConfigurationMasters as a list
    @RequestMapping(value = "/getAllBedConfigurationMasters", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllBedConfigurationMasters(@RequestParam Long clinicLocationId) {
        log.debug("REST request to get BedConfigurationMasters");
        GetBedConfigurationMastersServiceResponse response = null;
        try {
            GetBedConfigurationMastersServiceRequest request = new GetBedConfigurationMastersServiceRequest(clinicLocationId);
            response = getBedConfigurationMastersService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve BedConfigurationMasters");
            log.error("Failed to retrieve BedConfigurationMasters");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To create BedConfigurationMaster
    @RequestMapping(value = "/createBedConfigurationMaster", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createBedConfigurationMaster(@RequestBody BedConfigurationMasterDTO bedConfigurationMasterDTO,
                                                       @RequestParam Long clinicId) {
        log.debug("REST request to create BedConfigurationMaster");
        CreateBedConfigurationMasterServiceResponse response = null;
        try {
            CreateBedConfigurationMasterServiceRequest request = new
                    CreateBedConfigurationMasterServiceRequest(bedConfigurationMasterDTO, clinicId);
            response = createBedConfigurationMasterService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to create BedConfigurationMaster");
            log.error("Failed to create BedConfigurationMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get BedConfigurationMaster by id
    @RequestMapping(value = "/getBedConfigurationMasterById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBedConfigurationMasterById(@RequestParam Long bedConfigurationMasterId) {
        log.debug("REST request to get BedConfigurationMaster");
        GetBedConfigurationMasterByIdServiceResponse response = null;
        try {
            GetBedConfigurationMasterByIdServiceRequest request = new GetBedConfigurationMasterByIdServiceRequest(bedConfigurationMasterId);
            response = getBedConfigurationMasterByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve BedConfigurationMaster");
            log.error("Failed to retrieve BedConfigurationMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get BedConfigurationMasters by ClinicLocation
    @RequestMapping(value = "/getBedConfigurationMasterByClinicLocation", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBedConfigurationMasterByClinicLocation(@RequestParam Long clinicLocationId) {
        log.debug("REST request to get BedConfigurationMasters");
        GetBedConfigurationMastersByClinicLocationServiceResponse response = null;
        try {
            GetBedConfigurationMastersByClinicLocationServiceRequest request = new
                    GetBedConfigurationMastersByClinicLocationServiceRequest(clinicLocationId);
            response = getBedConfigurationMastersByClinicLocationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve BedConfigurationMasters");
            log.error("Failed to retrieve BedConfigurationMasters");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }




    //To delete BedConfigMaster
    @RequestMapping(value = "/deleteBedConfigMaster", method = RequestMethod.DELETE, produces = "application/json")

    public ResponseEntity deleteBedConfigMaster(@RequestParam Long bedConfigMasterId) {

        log.debug("request to delete BedConfig Master");

        DeleteBedConfigMasterServiceResponse response = new DeleteBedConfigMasterServiceResponse();

        try {

            DeleteBedConfigMasterServiceRequest request = new DeleteBedConfigMasterServiceRequest(bedConfigMasterId);
            response = deleteBedConfigMasterService.execute(request);

        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage(Constants.BED_CONFIG_MASTER_DELETE_FAILURE);
            log.error("Failed to delete BedConfigMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }



}