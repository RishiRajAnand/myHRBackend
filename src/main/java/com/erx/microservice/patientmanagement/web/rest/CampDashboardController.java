package com.erx.microservice.patientmanagement.web.rest;

import com.erx.microservice.patientmanagement.service.camptracker.campregistrationSearch.CampRegistrationSearchService;
import com.erx.microservice.patientmanagement.service.camptracker.campregistrationSearch.CampRegistrationSearchServiceRequest;
import com.erx.microservice.patientmanagement.service.camptracker.campregistrationSearch.CampRegistrationSearchServiceResponse;
import com.erx.microservice.patientmanagement.service.camptracker.campregistrationsearchbydaterange.CampRegistrationSearchByDateRangeService;
import com.erx.microservice.patientmanagement.service.camptracker.campregistrationsearchbydaterange.CampRegistrationSearchByDateRangeServiceRequest;
import com.erx.microservice.patientmanagement.service.camptracker.campregistrationsearchbydaterange.CampRegistrationSearchByDateRangeServiceResponse;
import com.erx.microservice.patientmanagement.service.camptracker.deletecampregistration.DeleteCampRegistrationService;
import com.erx.microservice.patientmanagement.service.camptracker.deletecampregistration.DeleteCampRegistrationServiceRequest;
import com.erx.microservice.patientmanagement.service.camptracker.deletecampregistration.DeleteCampRegistrationServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
* created by Brighty on 06-12-2017
* */

@RestController
@RequestMapping("/api")
public class CampDashboardController {

    private final Logger log = LoggerFactory.getLogger(CampDashboardController.class);

    @Autowired
    private CampRegistrationSearchService campRegistrationSearchService;

    @Autowired
    private CampRegistrationSearchByDateRangeService campRegistrationSearchByDateRangeService;

    @Autowired
    private DeleteCampRegistrationService deleteCampRegistrationService;

    //To get all Camp Registration as a list
    @RequestMapping(value = "/campRegistrationSearch", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity campRegistrationSearch(@RequestBody CampRegistrationSearchServiceRequest request) {
        log.debug("REST request to get CampRegistrations");
        CampRegistrationSearchServiceResponse response = null;
        try {
            response = campRegistrationSearchService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Camp Registrations");
            log.error("Failed to retrieve Camp Registrations");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get all Camp Registrations By Date Range as a list
    @RequestMapping(value = "/campRegistrationSearchByDate", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity campRegistrationSearchByDate(@RequestBody CampRegistrationSearchByDateRangeServiceRequest request) {
        log.debug("REST request to get CampRegistrations By Date Range");
        CampRegistrationSearchByDateRangeServiceResponse response = null;
        try {
            response = campRegistrationSearchByDateRangeService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Camp Registrations By Date Range");
            log.error("Failed to retrieve Camp Registrations By Date Range");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To delete Camp Registration
    @RequestMapping(value = "/deleteCampRegistration", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteCampRegistration(@RequestParam Long patientId) {
        log.debug("REST request to delete CampRegistration");
        DeleteCampRegistrationServiceResponse response = null;
        try {
            DeleteCampRegistrationServiceRequest request = new DeleteCampRegistrationServiceRequest(patientId);
            response = deleteCampRegistrationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to delete Camp Registration");
            log.error("Failed to delete Camp Registration");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


}