package com.erx.microservice.patientmanagement.web.rest;

/*
 * created by Latha on 04-09-2018
 * */


import com.erx.microservice.patientmanagement.service.therapymanagement.getconfiguretherapies.GetNonConfigureTherapiesService;
import com.erx.microservice.patientmanagement.service.therapymanagement.getconfiguretherapies.GetNonConfigureTherapiesServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.getconfiguretherapies.GetNonConfigureTherapiesServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.getmedicinetreatmentbycaseid.GetTherapyTreatmentByCaseIdService;
import com.erx.microservice.patientmanagement.service.therapymanagement.getmedicinetreatmentbycaseid.GetTherapyTreatmentByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.getmedicinetreatmentbycaseid.GetTherapyTreatmentByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymaster.GetTherapyMasterService;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymaster.GetTherapyMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymaster.GetTherapyMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyid.GetTherapyMasterByIdService;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyid.GetTherapyMasterByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyid.GetTherapyMasterByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyserviceid.GetTherapyMasterByServiceIdService;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyserviceid.GetTherapyMasterByServiceIdServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyserviceid.GetTherapyMasterByServiceIdServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyrooms.GetTherapyRoomsService;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyrooms.GetTherapyRoomsServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyrooms.GetTherapyRoomsServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyscheduledetailbytherapyid.GetTherapyScheduleDetailByTherapyIdService;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyscheduledetailbytherapyid.GetTherapyScheduleDetailByTherapyIdServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapyscheduledetailbytherapyid.GetTherapyScheduleDetailByTherapyIdServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplate.GetTherapyTemplateService;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplate.GetTherapyTemplateServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplate.GetTherapyTemplateServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplatebyid.GetTherapyTemplateByIdService;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplatebyid.GetTherapyTemplateByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplatebyid.GetTherapyTemplateByIdServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TherapyMasterGetController {

    private final Logger log = LoggerFactory.getLogger(TherapyMasterGetController.class);

    @Autowired
    private GetTherapyMasterService getTherapyMasterService;

    @Autowired
    private GetTherapyRoomsService getTherapyRoomsService;

    @Autowired
    private GetTherapyMasterByIdService getTherapyMasterByIdService;

    @Autowired
    private GetTherapyMasterByServiceIdService getTherapyMasterByServiceIdService;

    @Autowired
    private GetTherapyTreatmentByCaseIdService getTherapyTreatmentByCaseIdService;

    @Autowired
    private GetTherapyTemplateService getTherapyTemplateService;

    @Autowired
    private GetTherapyTemplateByIdService getTherapyTemplateByIdService;

    @Autowired
    private GetNonConfigureTherapiesService getNonConfigureTherapiesService;

    @Autowired
    private GetTherapyScheduleDetailByTherapyIdService getTherapyScheduleDetailByTherapyIdService;

    // To get therapy master
    @GetMapping(value = "/getTherapyMaster", produces = "application/json")
    public ResponseEntity getTherapyMaster(@RequestParam Long clinicId, Pageable pageable) {
        log.debug("REST request to Get therapy master");
        GetTherapyMasterServiceResponse response = new GetTherapyMasterServiceResponse();
        try {
            GetTherapyMasterServiceRequest request = new GetTherapyMasterServiceRequest(clinicId, pageable);
            if(clinicId != null)
                response = getTherapyMasterService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy master");
            log.error("Failed to retrieve therapy master");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get therapy rooms
    @GetMapping(value = "/getTherapyRooms", produces = "application/json")
    public ResponseEntity getTherapyRooms(@RequestParam Long clinicLocationId) {
        log.debug("REST request to Get therapy rooms");
        GetTherapyRoomsServiceResponse response = new GetTherapyRoomsServiceResponse();
        try {
            GetTherapyRoomsServiceRequest request = new GetTherapyRoomsServiceRequest(clinicLocationId);
            if(clinicLocationId != null)
                response = getTherapyRoomsService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy rooms");
            log.error("Failed to retrieve therapy rooms");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get therapy master by id
    @GetMapping(value = "/getTherapyMasterById", produces = "application/json")
    public ResponseEntity getTherapyMasterById(@RequestParam Long clinicId, Long therapyMasterId) {
        log.debug("REST request to Get therapy master details by id");
        GetTherapyMasterByIdServiceResponse response = new GetTherapyMasterByIdServiceResponse();
        try {
            GetTherapyMasterByIdServiceRequest request = new GetTherapyMasterByIdServiceRequest(clinicId,therapyMasterId);
            if(clinicId != null && therapyMasterId != null)
                response = getTherapyMasterByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy master by id");
            log.error("Failed to retrieve therapy master by id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get therapy master by service catalogue id
    @GetMapping(value = "/getTherapyMasterByServiceId", produces = "application/json")
    public ResponseEntity getTherapyMasterByServiceId(@RequestParam Long clinicId, Long serviceCatalogueId) {
        log.debug("REST request to Get therapy master details by service catalogue id");
        GetTherapyMasterByServiceIdServiceResponse response = new GetTherapyMasterByServiceIdServiceResponse();
        try {
            GetTherapyMasterByServiceIdServiceRequest request = new GetTherapyMasterByServiceIdServiceRequest(clinicId,serviceCatalogueId);
            if(clinicId != null && serviceCatalogueId != null)
                response = getTherapyMasterByServiceIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy master by service catalogue id");
            log.error("Failed to retrieve therapy master by service catalogue id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get case sheet therapy treatment by case id
    @GetMapping(value = "/getTherapyTreatmentByCaseId", produces = "application/json")
    public ResponseEntity getMedicineTreatmentByCaseId(@RequestParam Long caseId) {
        log.debug("REST request to Get therapy treatment by case id");
        GetTherapyTreatmentByCaseIdServiceResponse response = new GetTherapyTreatmentByCaseIdServiceResponse();
        try {
            GetTherapyTreatmentByCaseIdServiceRequest request = new GetTherapyTreatmentByCaseIdServiceRequest(caseId);
            if(caseId != null)
                response = getTherapyTreatmentByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy treatment details By caseId");
            log.error("Failed to retrieve medicine therapy details By caseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get therapy templates
    @GetMapping(value = "/getTherapyTemplates", produces = "application/json")
    public ResponseEntity getTherapyTemplates(@RequestParam Long clinicId) {
        log.debug("REST request to Get therapy template");
        GetTherapyTemplateServiceResponse response = new GetTherapyTemplateServiceResponse();
        try {
            GetTherapyTemplateServiceRequest request = new GetTherapyTemplateServiceRequest(clinicId);
            if(clinicId != null)
                response = getTherapyTemplateService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy templates");
            log.error("Failed to retrieve therapy templates");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get therapy template details by template id
    @GetMapping(value = "/getTherapyTemplateByTemplateId", produces = "application/json")
    public ResponseEntity getTherapyTemplateByTemplateId(@RequestParam Long therapyTemplateId) {
        log.debug("REST request to Get therapy template by id");
        GetTherapyTemplateByIdServiceResponse response = new GetTherapyTemplateByIdServiceResponse();
        try {
            GetTherapyTemplateByIdServiceRequest request = new GetTherapyTemplateByIdServiceRequest(therapyTemplateId);
            if(therapyTemplateId != null)
                response = getTherapyTemplateByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy templates by id");
            log.error("Failed to retrieve therapy templates by id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get the non configure therapies
    @GetMapping(value = "/getNonConfigureTherapies", produces = "application/json")
    public ResponseEntity getNonConfigureTherapies(@RequestParam Long moduleSectionMasterId,
                                                                    @RequestParam Long clinicId, @RequestParam Long clinicLocationId) {
        log.debug("REST request to get the non configure therapies for the ModuleSectionMaster and Clinic");
        GetNonConfigureTherapiesServiceResponse response = new GetNonConfigureTherapiesServiceResponse();
        try {
            GetNonConfigureTherapiesServiceRequest request = new GetNonConfigureTherapiesServiceRequest(moduleSectionMasterId, clinicId, clinicLocationId);
            response = getNonConfigureTherapiesService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to get the non configure therapies for the ModuleSectionMaster and Clinic" + e.getMessage());
            log.error("Failed to get the non configure therapies for the ModuleSectionMaster and Clinic " + e.getMessage());
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get therapy schedule details by Therapy id
    @PostMapping(value = "/getTherapyScheduleDetailByTherapyId", produces = "application/json")
    public ResponseEntity getTherapyScheduleDetailByTherapyId(@RequestBody  GetTherapyScheduleDetailByTherapyIdServiceRequest request) {
        log.debug("REST request to get therapy schedule details by Therapy id");
        GetTherapyScheduleDetailByTherapyIdServiceResponse response = new GetTherapyScheduleDetailByTherapyIdServiceResponse();
        try {
            response = getTherapyScheduleDetailByTherapyIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy schedule details by Therapy id " + e.getMessage());
            log.error("Failed to retrieve therapy schedule details by Therapy id " + e.getMessage());
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


}
