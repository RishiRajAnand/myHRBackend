package com.erx.microservice.patientmanagement.web.rest;

/*
* created by Brighty on 11-06-2018
* */

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker.IpAdmissionRequestTrackerRequest;
import com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker.IpAdmissionRequestTrackerResponse;
import com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker.IpAdmissionRequestTrackerService;
import com.erx.microservice.patientmanagement.service.iprequest.saveipadmissionrequest.SaveIpAdmissionRequestService;
import com.erx.microservice.patientmanagement.service.iprequest.saveipadmissionrequest.SaveIpAdmissionRequestServiceRequest;
import com.erx.microservice.patientmanagement.service.iprequest.saveipadmissionrequest.SaveIpAdmissionRequestServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IpAdmissionRequestController {

    private final Logger log = LoggerFactory.getLogger(IpAdmissionRequestController.class);

    @Autowired
    private SaveIpAdmissionRequestService saveIpAdmissionRequestService;

    @Autowired
    private IpAdmissionRequestTrackerService ipAdmissionRequestTrackerService;

    //To save IpAdmissionRequest
    @RequestMapping(value = "/saveIpAdmissionRequest", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity saveIpAdmissionRequest(@RequestBody SaveIpAdmissionRequestServiceRequest request) {
        log.debug("REST request to save IpAdmissionRequest");
        SaveIpAdmissionRequestServiceResponse response = new SaveIpAdmissionRequestServiceResponse();
        try {
            response = saveIpAdmissionRequestService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save IpAdmissionRequest " + e.getMessage());
            log.error("Failed to save IpAdmissionRequest " + e.getMessage());
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To retrieve IpAdmissionRequest tracker details
    @RequestMapping(value = "/ipAdmissionRequestTracker", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity ipAdmissionRequestTracker(@RequestBody PatientSearchCriteria patientSearchCriteria, Pageable pageable) {
        log.debug("REST request to retrieve IpAdmissionRequest tracker details");
        IpAdmissionRequestTrackerResponse response = new IpAdmissionRequestTrackerResponse();
        try {
            IpAdmissionRequestTrackerRequest request = new IpAdmissionRequestTrackerRequest(patientSearchCriteria, pageable);
            response = ipAdmissionRequestTrackerService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve IpAdmissionRequest tracker details " + e.getMessage());
            log.error("Failed to retrieve IpAdmissionRequest tracker details " + e.getMessage());
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }
}
