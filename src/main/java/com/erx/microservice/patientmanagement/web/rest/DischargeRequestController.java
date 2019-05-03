package com.erx.microservice.patientmanagement.web.rest;

/*
* created by Latha on 06-12-2018
* */

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker.DischargeRequestTrackerRequest;
import com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker.DischargeRequestTrackerResponse;
import com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker.DischargeRequestTrackerService;
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
public class DischargeRequestController {

    private final Logger log = LoggerFactory.getLogger(DischargeRequestController.class);

    @Autowired
    private DischargeRequestTrackerService dischargeRequestTrackerService;

    //To retrieve DischargeRequest tracker details
    @RequestMapping(value = "/dischargeRequestTracker", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity dischargeRequestTracker(@RequestBody PatientSearchCriteria patientSearchCriteria, Pageable pageable) {
        log.debug("REST request to retrieve DischargeRequest tracker details");
        DischargeRequestTrackerResponse response = new DischargeRequestTrackerResponse();
        try {
            DischargeRequestTrackerRequest request = new DischargeRequestTrackerRequest(patientSearchCriteria, pageable);
            response = dischargeRequestTrackerService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve DischargeRequest tracker details " + e.getMessage());
            log.error("Failed to retrieve DischargeRequest tracker details " + e.getMessage());
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

}
