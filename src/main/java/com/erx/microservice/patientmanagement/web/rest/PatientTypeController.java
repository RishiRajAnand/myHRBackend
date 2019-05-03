package com.erx.microservice.patientmanagement.web.rest;


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.service.dto.patienttypedto.PatientTypeDTO;
import com.erx.microservice.patientmanagement.service.patienttype.createpatienttype.CreatePatientTypeService;
import com.erx.microservice.patientmanagement.service.patienttype.createpatienttype.CreatePatientTypeServiceRequest;
import com.erx.microservice.patientmanagement.service.patienttype.createpatienttype.CreatePatientTypeServiceResponse;
import com.erx.microservice.patientmanagement.service.patienttype.deletepatienttype.DeletePatientTypeService;
import com.erx.microservice.patientmanagement.service.patienttype.deletepatienttype.DeletePatientTypeServiceRequest;
import com.erx.microservice.patientmanagement.service.patienttype.deletepatienttype.DeletePatientTypeServiceResponse;
import com.erx.microservice.patientmanagement.service.patienttype.getallactivepatienttypesbycliniclocation.GetAllActivePatientTypesByClinicLocationService;
import com.erx.microservice.patientmanagement.service.patienttype.getallactivepatienttypesbycliniclocation.GetAllActivePatientTypesByClinicLocationServiceRequest;
import com.erx.microservice.patientmanagement.service.patienttype.getallactivepatienttypesbycliniclocation.GetAllActivePatientTypesByClinicLocationServiceResponse;
import com.erx.microservice.patientmanagement.service.patienttype.getallpatienttypesbycliniclocation.GetAllPatientTypesByClinicLocationService;
import com.erx.microservice.patientmanagement.service.patienttype.getallpatienttypesbycliniclocation.GetAllPatientTypesByClinicLocationServiceRequest;
import com.erx.microservice.patientmanagement.service.patienttype.getallpatienttypesbycliniclocation.GetAllPatientTypesByClinicLocationServiceResponse;
import com.erx.microservice.patientmanagement.service.patienttype.getpatienttypebyid.GetPatientTypeByIdService;
import com.erx.microservice.patientmanagement.service.patienttype.getpatienttypebyid.GetPatientTypeByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.patienttype.getpatienttypebyid.GetPatientTypeByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.patienttype.getpatienttypes.GetPatientTypesService;
import com.erx.microservice.patientmanagement.service.patienttype.getpatienttypes.GetPatientTypesServiceRequest;
import com.erx.microservice.patientmanagement.service.patienttype.getpatienttypes.GetPatientTypesServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* created by Brighty on 09-11-2017
* */

@RestController
@RequestMapping("/api")
public class PatientTypeController {

    private final Logger log = LoggerFactory.getLogger(PatientTypeController.class);
    @Autowired
    DeletePatientTypeService deletePatientTypeService;
    @Autowired
    private GetPatientTypesService getPatientTypesService;
    @Autowired
    private CreatePatientTypeService createPatientTypeService;
    @Autowired
    private GetPatientTypeByIdService getPatientTypeByIdService;
    @Autowired
    private GetAllActivePatientTypesByClinicLocationService getAllActivePatientTypesByClinicLocationService;
    @Autowired
    private GetAllPatientTypesByClinicLocationService getAllPatientTypesByClinicLocationService;

    //To get all PatientTypes as a list
    @RequestMapping(value = "/getPatientTypes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getPatientTypeMasters(@RequestParam Long clinicLocationId) {
        log.debug("REST request to get PatientTypes");
        GetPatientTypesServiceResponse response = new GetPatientTypesServiceResponse();
        try {
            GetPatientTypesServiceRequest request = new
                    GetPatientTypesServiceRequest(clinicLocationId);
            response = getPatientTypesService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("PatientTypes not retrieved successfully");
            log.error("PatientTypes not retrieved successfully");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To create PatientType
    @RequestMapping(value = "/createPatientType", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createPatientType(@RequestBody PatientTypeDTO patientTypeDTO) {
        log.debug("REST request to create PatientType");
        CreatePatientTypeServiceResponse response = new CreatePatientTypeServiceResponse();
        try {
            CreatePatientTypeServiceRequest request = new
                    CreatePatientTypeServiceRequest(patientTypeDTO);
            response = createPatientTypeService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("PatientType not created successfully");
            log.error("PatientType not created successfully");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get PatientType by id
    @RequestMapping(value = "/getPatientTypeById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getPatientTypeById(@RequestParam Long patientTypeMasterId) {
        log.debug("REST request to retrieve PatientType");
        GetPatientTypeByIdServiceResponse response = new GetPatientTypeByIdServiceResponse();
        try {
            GetPatientTypeByIdServiceRequest request = new
                    GetPatientTypeByIdServiceRequest(patientTypeMasterId);
            response = getPatientTypeByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("PatientType not retrieved successfully");
            log.error("PatientType not retrieved successfully");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get PatientType by clinicLocationId
    @RequestMapping(value = "/getAllPatientTypesByClinicLocation", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllActivePatientTypesByClinicLocation(@RequestParam Long clinicLocationId) {
        log.debug("REST request to retrieve PatientType");
        GetAllActivePatientTypesByClinicLocationServiceResponse response = null;
        try {
            GetAllActivePatientTypesByClinicLocationServiceRequest request = new
                    GetAllActivePatientTypesByClinicLocationServiceRequest(clinicLocationId);
            response = getAllActivePatientTypesByClinicLocationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("PatientType not retrieved successfully");
            log.error("PatientType not retrieved successfully");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To delete PatientTypeMaster
    @RequestMapping(value = "/deletePatientTypeMaster", method = RequestMethod.DELETE,
            produces = "application/json")
    public ResponseEntity deletePatientTypeMaster(@RequestParam Long patientTypeMasterId) {
        log.debug("request to delete patientType Master");
        DeletePatientTypeServiceResponse response = new DeletePatientTypeServiceResponse();
        try {
            DeletePatientTypeServiceRequest request = new DeletePatientTypeServiceRequest(patientTypeMasterId);
            response = deletePatientTypeService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage(Constants.PATIENT_TYPE__MASTER_DELETE_FAILURE);
            log.error("Failed to delete patientTypeMaster");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get PatientType by clinicLocationId
    @RequestMapping(value = "/getPatientTypeTracker", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllPatientTypesByClinicLocation(@RequestParam Long clinicLocationId) {
        log.debug("REST request to retrieve PatientType");
        GetAllPatientTypesByClinicLocationServiceResponse response = new
                GetAllPatientTypesByClinicLocationServiceResponse();
        try {
            GetAllPatientTypesByClinicLocationServiceRequest request = new
                    GetAllPatientTypesByClinicLocationServiceRequest(clinicLocationId);
            response = getAllPatientTypesByClinicLocationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("PatientType not retrieved successfully");
            log.error("PatientType not retrieved successfully");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }
}
