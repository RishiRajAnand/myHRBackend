package com.erx.microservice.patientmanagement.web.rest;

/*
* created by latha on 06-11-2017
* */

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.patientsearch.CampPatientSearchService;
import com.erx.microservice.patientmanagement.service.patientsearch.NewPatientSearchService;
import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceRequest;
import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceResponse;
import com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet.PatientCaseCountSearchService;
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
public class PatientSearchController {

    private final Logger log = LoggerFactory.getLogger(PatientSearchController.class);

    @Autowired
    NewPatientSearchService patientSearchService;

    @Autowired
    CampPatientSearchService campPatientSearchService;

    @Autowired
    PatientCaseCountSearchService patientCaseCountSearchService;

    //To get all Patients based on user input
    @RequestMapping(value = "/searchPatients", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity searchPatients(@RequestBody PatientSearchCriteria patientSearchCriteria, Pageable pageable) {
        log.debug("REST request to get Patients");
        PatientSearchServiceResponse response = null;
        try {
            PatientSearchServiceRequest request = new PatientSearchServiceRequest(patientSearchCriteria, pageable);
            response = patientSearchService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Patients");
            log.error("Failed to retrieve Patients");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get all CampPatients based on user input
    @RequestMapping(value = "/searchCampPatients", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity searchCampPatients(@RequestBody PatientSearchCriteria patientSearchCriteria, Pageable pageable) {
        log.debug("REST request to get Camp Patients");
        PatientSearchServiceResponse response = null;
        try {
            PatientSearchServiceRequest request = new PatientSearchServiceRequest(patientSearchCriteria, pageable);
            response = campPatientSearchService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Camp Patients");
            log.error("Failed to retrieve Camp Patients");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get all Patients along with cases based on user input
    @RequestMapping(value = "/searchPatientWithCaseCount", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity searchPatientWithCaseCount(@RequestBody PatientSearchCriteria patientSearchCriteria, Pageable pageable) {
        log.debug("REST request to get patients with case count");
        PatientSearchServiceResponse response = null;
        try {
            PatientSearchServiceRequest request = new PatientSearchServiceRequest(patientSearchCriteria,pageable);
            response = patientCaseCountSearchService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve patients with case count");
            log.error("Failed to retrieve patients with case count");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }
}
