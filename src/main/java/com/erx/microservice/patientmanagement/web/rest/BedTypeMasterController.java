package com.erx.microservice.patientmanagement.web.rest;


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.service.bedtypemaster.createbedtypemaster.CreateBedTypeMasterService;
import com.erx.microservice.patientmanagement.service.bedtypemaster.createbedtypemaster.CreateBedTypeMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.bedtypemaster.createbedtypemaster.CreateBedTypeMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.bedtypemaster.deletebedtypemaster.DeleteBedTypeMasterService;
import com.erx.microservice.patientmanagement.service.bedtypemaster.deletebedtypemaster.DeleteBedTypeMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.bedtypemaster.deletebedtypemaster.DeleteBedTypeMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbycliniclocation.GetAllBedTypeMastersByClinicLocationService;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbycliniclocation.GetAllBedTypeMastersByClinicLocationServiceRequest;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbycliniclocation.GetAllBedTypeMastersByClinicLocationServiceResponse;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbyisdaycare.GetAllBedTypeMastersByIsDayCareServiceResponse;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbyisdaycare.GetAllBedTypeMastersByIsDayCareService;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getallbedtypemastersbyisdaycare.GetAllBedTypeMastersByIsDayCareServiceRequest;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasterbyid.GetBedTypeMasterByIdService;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasterbyid.GetBedTypeMasterByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasterbyid.GetBedTypeMasterByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasters.GetBedTypeMastersService;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasters.GetBedTypeMastersServiceRequest;
import com.erx.microservice.patientmanagement.service.bedtypemaster.getbedtypemasters.GetBedTypeMastersServiceResponse;
import com.erx.microservice.patientmanagement.service.bedtypemaster.swapbedtypemastersequenceorderbybedtypeids.SwapBedTypeSequenceOrderService;
import com.erx.microservice.patientmanagement.service.bedtypemaster.swapbedtypemastersequenceorderbybedtypeids.SwapBedTypeSequenceOrderServiceRequest;
import com.erx.microservice.patientmanagement.service.bedtypemaster.swapbedtypemastersequenceorderbybedtypeids.SwapBedTypeSequenceOrderServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeMasterDTO;
import com.erx.microservice.patientmanagement.service.dto.beedtypedto.BedTypeSwapSequenceOrderDTO;
import com.erx.microservice.patientmanagement.web.rest.util.BedTypeMasterConstants;
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
public class BedTypeMasterController {

    private final Logger log = LoggerFactory.getLogger(BedTypeMasterController.class);

    @Autowired
    private GetBedTypeMastersService getBedTypeMastersService;

    @Autowired
    private CreateBedTypeMasterService createBedTypeMasterService;

    @Autowired
    private GetBedTypeMasterByIdService getBedTypeMasterByIdService;

    @Autowired
    private GetAllBedTypeMastersByClinicLocationService getAllBedTypeMastersByClinicLocationService;

    @Autowired
    private GetAllBedTypeMastersByIsDayCareService getAllBedTypeMastersByIsDayCareService;

    @Autowired
    DeleteBedTypeMasterService deleteBedTypeMasterService;

    @Autowired
    private SwapBedTypeSequenceOrderService swapBedTypeSequenceOrderService;


    //To get all BedTypeMasters as a list
    @RequestMapping(value = "/getAllBedTypeMasters", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllBedTypeMasters(@RequestParam Long clinicLocationId) {
        log.debug("REST request to get CampMasters");
        GetBedTypeMastersServiceResponse response = null;
        try {
            GetBedTypeMastersServiceRequest request = new GetBedTypeMastersServiceRequest(clinicLocationId);
            response = getBedTypeMastersService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve BedTypeMasters");
            log.error("Failed to retrieve BedTypeMastersByIsDayCare");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To create BedTypeMaster
    @RequestMapping(value = "/createBedTypeMaster", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createBedTypeMaster(@RequestBody BedTypeMasterDTO bedTypeMasterDTO,
                                              @RequestParam Long clinicId) {
        log.debug("REST request to create BedTypeMaster");
        CreateBedTypeMasterServiceResponse response = null;
        try {
            CreateBedTypeMasterServiceRequest request = new CreateBedTypeMasterServiceRequest(bedTypeMasterDTO, clinicId);
            response = createBedTypeMasterService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to create BedTypeMaster");
            log.error("Failed to create BedTypeMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get BedTypeMaster by id
    @RequestMapping(value = "/getBedTypeMasterById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getCampMasterById(@RequestParam Long bedTypeMasterId) {
        log.debug("REST request to get BedTypeMaster");
        GetBedTypeMasterByIdServiceResponse response = null;
        try {
            GetBedTypeMasterByIdServiceRequest request = new GetBedTypeMasterByIdServiceRequest(bedTypeMasterId);
            response = getBedTypeMasterByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve BedTypeMaster");
            log.error("Failed to retrieve BedTypeMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get BedTypeMasters by ClinicLocation
    @RequestMapping(value = "/getBedTypeMastersByClinicLocation", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBedTypeMastersByClinicLocation(@RequestParam Long clinicLocationId,
                                                            @RequestParam(required = false) Optional<Boolean> isActive) {
        log.debug("REST request to get BedTypeMasters");
        GetAllBedTypeMastersByClinicLocationServiceResponse response = null;
        try {
            GetAllBedTypeMastersByClinicLocationServiceRequest request = new
                    GetAllBedTypeMastersByClinicLocationServiceRequest(clinicLocationId,
                    isActive);
            //     status.isPresent()&status.get().equalsIgnoreCase(MasterConstants.STATUS_ACTIVE)?true:false );
            response = getAllBedTypeMastersByClinicLocationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve BedTypeMasters");
            log.error("Failed to retrieve BedTypeMasters");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To delete BedTypeMaster
    @RequestMapping(value = "/deleteBedTypeMaster", method = RequestMethod.DELETE, produces = "application/json")

    public ResponseEntity deleteBedTypeMaster(@RequestParam Long bedTypeMasterId) {

        log.debug("request to delete BedType Master");

        DeleteBedTypeMasterServiceResponse response = new DeleteBedTypeMasterServiceResponse();

        try {

            DeleteBedTypeMasterServiceRequest request = new DeleteBedTypeMasterServiceRequest(bedTypeMasterId);
            response = deleteBedTypeMasterService.execute(request);

        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage(Constants.BED_TYPE_MASTER_DELETE_FAILURE);
            log.error("Failed to delete BedTypeMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //api to swap bedType sequenceOrderNos
    @PostMapping(value = "/swapBedTypeSequenceOrderByBedTypeIds")
    ResponseEntity swapBedTypeSequenceOrderByBedTypeIds(@RequestBody BedTypeSwapSequenceOrderDTO bedTypeSwapSequenceOrderDTO) {
        SwapBedTypeSequenceOrderServiceResponse response = null;

        log.debug(BedTypeMasterConstants.BED_TYPE_SWAP_CONTROLLER_REQUEST_MESSAGE);
        try {
            SwapBedTypeSequenceOrderServiceRequest request = new SwapBedTypeSequenceOrderServiceRequest(bedTypeSwapSequenceOrderDTO);
            response = swapBedTypeSequenceOrderService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage(BedTypeMasterConstants.BED_TYPE_SEQUENCE_ORDER_SWAP_FAILURE);
            response.setErrorMessage(BedTypeMasterConstants.BED_TYPE_SEQUENCE_ORDER_SWAP_FAILURE);
            log.error(BedTypeMasterConstants.BED_TYPE_SEQUENCE_ORDER_SWAP_FAILURE);
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(response.getMessage()).body(response);
    }
    //To get all getAllBedTypeMastersByIsDayCare as a list
    @RequestMapping(value = "/getAllBedTypeMastersByIsDayCare", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllBedTypeMastersByIsDayCare(@RequestParam Long clinicLocationId, @RequestParam Boolean isDayCare) {
        log.debug("REST request to get CampMasters");
        GetAllBedTypeMastersByIsDayCareServiceResponse response = null;
        try {
            GetAllBedTypeMastersByIsDayCareServiceRequest request = new GetAllBedTypeMastersByIsDayCareServiceRequest(clinicLocationId, isDayCare);
            response = getAllBedTypeMastersByIsDayCareService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve BedTypeMastersByByIsDayCare");
            log.error("Failed to retrieve BedTypeMastersByIsDayCare");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }



}