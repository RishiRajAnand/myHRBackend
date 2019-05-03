package com.erx.microservice.patientmanagement.web.rest;

import com.erx.microservice.patientmanagement.service.dto.patientreferraldto.CreateReferralPatientMasterDTO;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.createreferralpatientmaster.CreateReferralPatientMasterService;


import com.erx.microservice.patientmanagement.service.referralpatientmaster.createreferralpatientmaster.CreateReferralPatientMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.createreferralpatientmaster.CreateReferralPatientMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.deletereferralpatientmaster.DeleteReferralPatientMasterByIdService;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.deletereferralpatientmaster.DeleteReferralPatientMasterByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.deletereferralpatientmaster.DeleteReferralPatientMasterByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmaster.GetReferralPatientMasterService;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmaster.GetReferralPatientMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmaster.GetReferralPatientMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbydoctorname.GetReferralPatientMasterByDoctorNameService;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbydoctorname.GetReferralPatientMasterByDoctorNameServiceRequest;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbydoctorname.GetReferralPatientMasterByDoctorNameServiceResponse;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyid.GetReferralPatientMasterByIdService;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyid.GetReferralPatientMasterByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyid.GetReferralPatientMasterByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralid.GetReferralPatientMasterByReferralIdService;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralid.GetReferralPatientMasterByReferralIdServiceRequest;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralid.GetReferralPatientMasterByReferralIdServiceResponse;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralname.GetReferralPatientMasterByReferralNameService;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralname.GetReferralPatientMasterByReferralNameServiceRequest;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferralname.GetReferralPatientMasterByReferralNameServiceResponse;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferraltype.GetReferralPatientMasterByReferralTypeService;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferraltype.GetReferralPatientMasterByReferralTypeServiceRequest;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbyreferraltype.GetReferralPatientMasterByReferralTypeServiceResponse;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbystatus.GetReferralPatientMasterByStatusService;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbystatus.GetReferralPatientMasterByStatusServiceRequest;
import com.erx.microservice.patientmanagement.service.referralpatientmaster.getreferralpatientmasterbystatus.GetReferralPatientMasterByStatusServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReferralPatientMasterController {

    private final Logger log = LoggerFactory.getLogger(ReferralPatientMasterController.class);

    @Autowired
    private CreateReferralPatientMasterService createReferralPatientMasterService;

    @Autowired
    private GetReferralPatientMasterService getReferralPatientMasterService;

    @Autowired
    private GetReferralPatientMasterByReferralNameService getReferralPatientMasterByReferralNameService;

    @Autowired
    private GetReferralPatientMasterByReferralTypeService getReferralPatientMasterByReferralTypeService;

    @Autowired
    private GetReferralPatientMasterByReferralIdService getReferralPatientMasterByReferralIdService;

    @Autowired
    private GetReferralPatientMasterByStatusService getReferralPatientMasterByStatusService;

    @Autowired
    private GetReferralPatientMasterByDoctorNameService getReferralPatientMasterByDoctorNameService;

    @Autowired
    private DeleteReferralPatientMasterByIdService deleteReferralPatientMasterByIdService;

    @Autowired
    private GetReferralPatientMasterByIdService getReferralPatientMasterByIdService;

    //To create ReferralPatientMaster
    @RequestMapping(value = "/createReferralPatientMaster", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createReferralPatientMaster(@RequestBody CreateReferralPatientMasterDTO createReferralPatientMasterDTO) {
        log.debug("REST request to create ReferralPatientMaster");
        CreateReferralPatientMasterServiceResponse response = null;
        try {
            CreateReferralPatientMasterServiceRequest request = new CreateReferralPatientMasterServiceRequest(createReferralPatientMasterDTO);
            response = createReferralPatientMasterService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMaster not created successfully");
            log.error("ReferralPatientMaster not created successfully");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get all ReferralPatientMaster as a list for given clinicLocationId
    @RequestMapping(value = "/getReferralPatientMasters", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getReferralPatientMasters(@RequestParam long clinicLocationId) {

        log.debug("REST request to get All ReferralPatientMasters for given clinicLocationId");
        GetReferralPatientMasterServiceResponse response = null;
        try {
            GetReferralPatientMasterServiceRequest request = new GetReferralPatientMasterServiceRequest(clinicLocationId);
            response = getReferralPatientMasterService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMasters not retrieved successfully");
            log.error("ReferralPatientMasters not retrieved successfully");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get all ReferralPatientMaster as a list for given clinicLocationId and  referralId

    @RequestMapping(value = "/getReferralPatientMastersByReferralId", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getReferralPatientMastersByReferralId(@RequestParam long clinicLocationId, @RequestParam String referralId) {

        log.debug("REST request to get All ReferralPatientMasters for given referralId");
        GetReferralPatientMasterByReferralIdServiceResponse response = null;
        try {
            GetReferralPatientMasterByReferralIdServiceRequest request = new GetReferralPatientMasterByReferralIdServiceRequest(clinicLocationId, referralId);
            response = getReferralPatientMasterByReferralIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMasters not retrieved successfully for given referralId");
            log.error("ReferralPatientMasters not retrieved successfully for given referralId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get all ReferralPatientMaster as a list for given clinicLocationId and  referralName

    @RequestMapping(value = "/getReferralPatientMastersByReferralName", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getReferralPatientMastersByReferralName(@RequestParam long clinicLocationId, @RequestParam String referralName) {

        log.debug("REST request to get All ReferralPatientMasters for given referralName");
        GetReferralPatientMasterByReferralNameServiceResponse response = null;
        try {
            GetReferralPatientMasterByReferralNameServiceRequest request = new GetReferralPatientMasterByReferralNameServiceRequest(clinicLocationId, referralName);
            response = getReferralPatientMasterByReferralNameService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMasters not retrieved successfully for given referralName");
            log.error("ReferralPatientMasters not retrieved successfully for given referralName");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get all ReferralPatientMaster as a list for given clinicLocationId and  referralType

    @RequestMapping(value = "/getReferralPatientMastersByReferralType", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getReferralPatientMastersByReferralType(@RequestParam long clinicLocationId, @RequestParam String referralType) {

        log.debug("REST request to get All ReferralPatientMasters for given referralType");
        GetReferralPatientMasterByReferralTypeServiceResponse response = null;
        try {
            GetReferralPatientMasterByReferralTypeServiceRequest request = new GetReferralPatientMasterByReferralTypeServiceRequest(clinicLocationId, referralType);
            response = getReferralPatientMasterByReferralTypeService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMasters not retrieved successfully for given referralType");
            log.error("ReferralPatientMasters not retrieved successfully for given referralType");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get all ReferralPatientMaster as a list for given clinicLocationId and  Status
    @RequestMapping(value = "/getReferralPatientMastersByStatus", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getReferralPatientMastersByStatus(@RequestParam long clinicLocationId, @RequestParam String status) {

        log.debug("REST request to get All ReferralPatientMasters for given status");
        GetReferralPatientMasterByStatusServiceResponse response = null;
        try {
            GetReferralPatientMasterByStatusServiceRequest request = new GetReferralPatientMasterByStatusServiceRequest(clinicLocationId, status);
            response = getReferralPatientMasterByStatusService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMasters not retrieved successfully for given status");
            log.error("ReferralPatientMasters not retrieved successfully for given status");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get all ReferralPatientMaster as a list for given clinicLocationId and  doctorName
    @RequestMapping(value = "/getReferralPatientMastersByDoctorName", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getReferralPatientMastersByDoctorName(@RequestParam long clinicLocationId, @RequestParam String doctorName) {

        log.debug("REST request to get All ReferralPatientMasters for given doctorName");
        GetReferralPatientMasterByDoctorNameServiceResponse response = null;
        try {
            GetReferralPatientMasterByDoctorNameServiceRequest request = new GetReferralPatientMasterByDoctorNameServiceRequest(clinicLocationId, doctorName);
            response = getReferralPatientMasterByDoctorNameService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMasters not retrieved successfully for given doctorName");
            log.error("ReferralPatientMasters not retrieved successfully for given doctorName");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To delete ReferralPatientMaster
    @RequestMapping(value = "/deleteReferralPatientMasters", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity deleteReferralPatientMasters(@RequestParam long referralId) {

        log.debug("REST request to delete ReferralPatientMasters for given referralId");
        DeleteReferralPatientMasterByIdServiceResponse response = null;
        try {
            DeleteReferralPatientMasterByIdServiceRequest request = new DeleteReferralPatientMasterByIdServiceRequest(referralId);
            response = deleteReferralPatientMasterByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMasters not deleted successfully for given referralId");
            log.error("ReferralPatientMasters not retrieved deleted for given referralId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To  ReferralPatientMaster  for given  id
    @RequestMapping(value = "/getReferralPatientMastersById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getReferralPatientMastersById(@RequestParam Long referralId) {

        log.debug("REST request to get  ReferralPatientMasters for given referralId");
        GetReferralPatientMasterByIdServiceResponse response = null;
        try {
            GetReferralPatientMasterByIdServiceRequest request = new GetReferralPatientMasterByIdServiceRequest(referralId);
            response = getReferralPatientMasterByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("ReferralPatientMaster not retrieved successfully for given referralId");
            log.error("ReferralPatientMaster not retrieved successfully for given referralId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


}
