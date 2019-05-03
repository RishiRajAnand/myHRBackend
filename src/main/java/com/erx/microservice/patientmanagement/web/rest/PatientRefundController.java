package com.erx.microservice.patientmanagement.web.rest;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.PatientRefundDTO;
import com.erx.microservice.patientmanagement.service.patientrefund.createpatientrefund.CreatePatientRefundService;
import com.erx.microservice.patientmanagement.service.patientrefund.createpatientrefund.CreatePatientRefundServiceRequest;
import com.erx.microservice.patientmanagement.service.patientrefund.createpatientrefund.CreatePatientRefundServiceResponse;
import com.erx.microservice.patientmanagement.service.patientrefund.deletepatientrefundbyid.DeletePatientRefundByIdService;
import com.erx.microservice.patientmanagement.service.patientrefund.deletepatientrefundbyid.DeletePatientRefundByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.patientrefund.deletepatientrefundbyid.DeletePatientRefundByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbycliniclocation.GetPatientRefundByClinicLocationService;
import com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbycliniclocation.GetPatientRefundByClinicLocationServiceRequest;
import com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbycliniclocation.GetPatientRefundByClinicLocationServiceResponse;
import com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbyid.GetPatientRefundByIdService;
import com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbyid.GetPatientRefundByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbyid.GetPatientRefundByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbypatientidandvisitid.GetPatientRefundByPatientIdAndVisitIdService;
import com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbypatientidandvisitid.GetPatientRefundByPatientIdAndVisitIdServiceRequest;
import com.erx.microservice.patientmanagement.service.patientrefund.getpatientrefundbypatientidandvisitid.GetPatientRefundByPatientIdAndVisitIdServiceResponse;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.SearchPatientRefundService;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefund.SearchPatientRefundServiceRequest;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefundbycodeortype.SearchPatientRefundByCodeOrTypeService;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefundbycodeortype.SearchPatientRefundByCodeOrTypeServiceRequest;
import com.erx.microservice.patientmanagement.service.patientrefund.searchpatientrefundbycodeortype.SearchPatientRefundByCodeOrTypeServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* created by Brighty on 27-11-2017
* */
@RestController
@RequestMapping("/api")
public class PatientRefundController {

    private final Logger log = LoggerFactory.getLogger(PatientRefundController.class);

    @Autowired
    private CreatePatientRefundService createPatientRefundService;

    @Autowired
    private GetPatientRefundByIdService getPatientRefundByIdService;

    @Autowired
    private GetPatientRefundByClinicLocationService getPatientRefundByClinicLocationService;

    @Autowired
    private DeletePatientRefundByIdService deletePatientRefundByIdService;

    @Autowired
    private SearchPatientRefundByCodeOrTypeService searchPatientRefundByCodeOrTypeService;

    @Autowired
    private GetPatientRefundByPatientIdAndVisitIdService getPatientRefundByPatientIdAndVisitIdService;

    @Autowired
    private SearchPatientRefundService searchPatientRefundService;


    //To create Refund
    @RequestMapping(value = "/createRefund", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createRefund(@RequestBody PatientRefundDTO patientRefundDTO) {
        log.debug("REST request to create Refund");
        CreatePatientRefundServiceResponse response = null;
        try {
            CreatePatientRefundServiceRequest request = new CreatePatientRefundServiceRequest(patientRefundDTO);
            response = createPatientRefundService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save Refund");
            log.error("Failed to save Refund");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get Refund by id
    @RequestMapping(value = "/getRefundById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getRefundById(@RequestParam Long refundId) {
        log.debug("REST request to get Refund");
        GetPatientRefundByIdServiceResponse response = null;
        try {
            GetPatientRefundByIdServiceRequest request = new GetPatientRefundByIdServiceRequest(refundId);
            response = getPatientRefundByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refund");
            log.error("Failed to retrieve Refund");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get RefundPrint
    @RequestMapping(value = "/refundPrint", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity refundPrint(@RequestParam Long refundId) {
        log.debug("REST request to get Refund");
        GetPatientRefundByIdServiceResponse response = null;
        try {
            GetPatientRefundByIdServiceRequest request = new GetPatientRefundByIdServiceRequest(refundId);
            response = getPatientRefundByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refund");
            log.error("Failed to retrieve Refund");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get Refunds by ClinicLocation
    @RequestMapping(value = "/getRefundByClinicLocation", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getDepositMasterByClinicLocation(@RequestParam Long clinicLocationId) {
        log.debug("REST request to get DepositMasters");
        GetPatientRefundByClinicLocationServiceResponse response = null;
        try {
            GetPatientRefundByClinicLocationServiceRequest request = new
                    GetPatientRefundByClinicLocationServiceRequest(clinicLocationId);
            response = getPatientRefundByClinicLocationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refunds");
            log.error("Failed to retrieve Refunds");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To delete Refund by id
    @RequestMapping(value = "/deleteRefundById", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteRefundById(@RequestParam Long refundId) {
        log.debug("REST request to delete Refund");
        DeletePatientRefundByIdServiceResponse response = null;
        try {
            DeletePatientRefundByIdServiceRequest request = new DeletePatientRefundByIdServiceRequest(refundId);
            response = deletePatientRefundByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to delete Refund");
            log.error("Failed to delete Refund");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To retrieve Refund based on refundNumber or refundType
    @RequestMapping(value = "/searchRefundByCodeOrRefundType", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity searchRefundByCodeOrRefundType(@RequestBody SearchPatientRefundByCodeOrTypeServiceRequest request) {
        log.debug("REST request to retrieve Refund based on refundNumber or refundType");
        SearchPatientRefundByCodeOrTypeServiceResponse response = null;
        try {
            response = searchPatientRefundByCodeOrTypeService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refund based on refundNumber or refundType");
            log.error("Failed to retrieve Refund based on refundNumber or refundType");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //created by Raushan on 06-02-2018
    //To retrieve Refund for given patientId and visitId
    @RequestMapping(value = "/getPatientRefundByPatientIdAndVisitId", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getPatientRefundByPatientIdAndVisitId(@RequestParam Long patientId, @RequestParam Long visitId) {
        log.debug("REST request to retrieve Refund Detail for given patientId and visitId");
        GetPatientRefundByPatientIdAndVisitIdServiceResponse response = null;
        try {
            GetPatientRefundByPatientIdAndVisitIdServiceRequest request = new GetPatientRefundByPatientIdAndVisitIdServiceRequest(patientId, visitId);
            response = getPatientRefundByPatientIdAndVisitIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refund Detail for given patientId and visitId");
            log.error("Failed to retrieve Refund Detail for given patientId and visitId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //created by Raushan on 13-02-2018
    //search Refund Detail
    @RequestMapping(value = "/searchPatientRefund", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity searchPatientRefund(@RequestBody PatientSearchCriteria patientSearchCriteria) {
        log.debug("REST request to retrieve Refund Detail ");
        CommonServiceResponse response = null;
        try {
            SearchPatientRefundServiceRequest request = new SearchPatientRefundServiceRequest(patientSearchCriteria);
            response = searchPatientRefundService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Refund Detail ");
            log.error("Failed to retrieve Refund Detail ");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }
}