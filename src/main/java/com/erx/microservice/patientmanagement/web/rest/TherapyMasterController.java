package com.erx.microservice.patientmanagement.web.rest;

/*
 * created by Latha on 04-09-2018
 * */


import com.erx.microservice.patientmanagement.service.casemanagement.savetherapycheckincheckouttime.SaveTherapyCheckInCheckOutTimeService;
import com.erx.microservice.patientmanagement.service.casemanagement.savetherapycheckincheckouttime.SaveTherapyCheckInCheckOutTimeServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savetherapycheckincheckouttime.SaveTherapyCheckInCheckOutTimeServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savetherapymedicinedetail.SaveTherapyMedicineDetailService;
import com.erx.microservice.patientmanagement.service.casemanagement.savetherapymedicinedetail.SaveTherapyMedicineDetailServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savetherapymedicinedetail.SaveTherapyMedicineDetailServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.createtherapyschedule.CreateTherapyScheduleService;
import com.erx.microservice.patientmanagement.service.therapymanagement.createtherapyschedule.CreateTherapyScheduleServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.createtherapyschedule.CreateTherapyScheduleServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapymaster.DeleteTherapyMasterService;
import com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapymaster.DeleteTherapyMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapymaster.DeleteTherapyMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapytemplate.DeleteTherapyTemplateService;
import com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapytemplate.DeleteTherapyTemplateServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapytemplate.DeleteTherapyTemplateServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.deletetreatmenttherapy.DeleteTreatmentTherapyService;
import com.erx.microservice.patientmanagement.service.therapymanagement.deletetreatmenttherapy.DeleteTreatmentTherapyServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.deletetreatmenttherapy.DeleteTreatmentTherapyServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapymaster.SaveTherapyMasterService;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapymaster.SaveTherapyMasterServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapymaster.SaveTherapyMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytemplate.SaveTherapyTemplateService;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytemplate.SaveTherapyTemplateServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytemplate.SaveTherapyTemplateServiceResponse;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytreatment.SaveTherapyTreatmentService;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytreatment.SaveTherapyTreatmentServiceRequest;
import com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytreatment.SaveTherapyTreatmentServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TherapyMasterController {

    private final Logger log = LoggerFactory.getLogger(TherapyMasterController.class);

    @Autowired
    private SaveTherapyMasterService saveTherapyMasterService;

    @Autowired
    private DeleteTherapyMasterService deleteTherapyMasterService;

    @Autowired
    private SaveTherapyTreatmentService saveTherapyTreatmentService;

    @Autowired
    private DeleteTreatmentTherapyService deleteTreatmentTherapyService;

    @Autowired
    private SaveTherapyTemplateService saveTherapyTemplateService;

    @Autowired
    private DeleteTherapyTemplateService deleteTherapyTemplateService;

    @Autowired
    private SaveTherapyMedicineDetailService saveTherapyMedicineDetailService;

    @Autowired
    private SaveTherapyCheckInCheckOutTimeService saveTherapyCheckInCheckOutTimeService;

    @Autowired
    private CreateTherapyScheduleService createTherapyScheduleService;



    //To save treatment(therapy) from case sheet
    @RequestMapping(value = "/saveTherapyTreatment", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveTherapyTreatment(@RequestBody SaveTherapyTreatmentServiceRequest request) {
        log.debug("REST request to save therapy treatment");
        SaveTherapyTreatmentServiceResponse response = null;
        try {
            if((request.getSaveTreatmentTherapyDTO().getCaseId() != null) &&  (request.getSaveTreatmentTherapyDTO().getPatientId() != null))
                response = saveTherapyTreatmentService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save therapy treatment of case");
            log.error("Failed to save therapy treatment of case");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

   //To save therapy configuration(therapy master)
    @RequestMapping(value = "/saveTherapyMaster", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveTherapyMaster(@RequestBody SaveTherapyMasterServiceRequest request) {
        log.debug("REST request to save therapy master");
        SaveTherapyMasterServiceResponse response = null;
        try {
            if((request.getSaveTherapyMasterDTO().getClinicId() != null) && (request.getSaveTherapyMasterDTO().getServiceCatalogueId() != null))
                response = saveTherapyMasterService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save therapy master");
            log.error("Failed to save therapy master");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To delete therapy master by id
    @DeleteMapping(value = "/deleteTherapyMasterById", produces = "application/json")
    public ResponseEntity deleteTherapyMasterById(@RequestParam Long therapyMasterId) {
        log.debug("REST request to delete therapy master by id");
        DeleteTherapyMasterServiceResponse response = null;
        try {
            DeleteTherapyMasterServiceRequest request = new DeleteTherapyMasterServiceRequest(therapyMasterId);
            if(request.getTherapyMasterId() != null)
                response = deleteTherapyMasterService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to delete therapy master by id");
            log.error("Failed to delete therapy master by id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To delete TherapyTreatment by id
    @DeleteMapping(value = "/deleteTherapyTreatmentById", produces = "application/json")
    public ResponseEntity deleteTherapyTreatmentById(@RequestParam Long therapyPlanningId) {
        log.debug("REST request to delete therapy treatment by id");
        DeleteTreatmentTherapyServiceResponse response = null;
        try {
            DeleteTreatmentTherapyServiceRequest request = new DeleteTreatmentTherapyServiceRequest(therapyPlanningId);
            if(request.getTherapyPlanningId() != null)
                response = deleteTreatmentTherapyService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to delete therapy treatment by id");
            log.error("Failed to delete therapy treatment by id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save therapy template
    @PostMapping(value = "/saveTherapyTemplate", produces = "application/json")
    public ResponseEntity saveTherapyTemplate(@RequestBody SaveTherapyTemplateServiceRequest request) {
        log.debug("REST request to save therapy template");
        SaveTherapyTemplateServiceResponse response = null;
        try {
            if(request.getSaveTherapyTemplateDTO().getClinicId() != null && request.getSaveTherapyTemplateDTO().getName() != null)
                response = saveTherapyTemplateService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save therapy template");
            log.error("Failed to save therapy template");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To delete therapy template by id
    @DeleteMapping(value = "/deleteTherapyTemplateById", produces = "application/json")
    public ResponseEntity deleteTherapyTemplateById(@RequestParam Long therapyTemplateId) {
        log.debug("REST request to delete therapy template by id ");
        DeleteTherapyTemplateServiceResponse response = null;
        try {
            DeleteTherapyTemplateServiceRequest request = new DeleteTherapyTemplateServiceRequest(therapyTemplateId);
            if(request.getTherapyTemplateId() != null)
                response = deleteTherapyTemplateService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to delete therapy template by id");
            log.error("Failed to delete therapy template by id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To save Case Medicine Detail
    @PostMapping(value = "/saveTherapyMedicineDetail", produces = "application/json")
    public ResponseEntity saveTherapyMedicineDetail(@RequestBody SaveTherapyMedicineDetailServiceRequest request) {
        log.debug("REST request to save Case Medicine Detail");
        SaveTherapyMedicineDetailServiceResponse response = null;
        try {
            if(request.getSaveTherapyPlanningDTOs() != null && !request.getSaveTherapyPlanningDTOs().isEmpty())
                response = saveTherapyMedicineDetailService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save Case Medicine Detail");
            log.error("Failed to save Case Medicine Detail");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To save Therapy CheckIn CheckOut Time
    @PostMapping(value = "/saveTherapyCheckInCheckOutTime", produces = "application/json")
    public ResponseEntity saveTherapyCheckInCheckOutTime(@RequestBody SaveTherapyCheckInCheckOutTimeServiceRequest request) {
        log.debug("REST request to save Therapy CheckIn CheckOut Time");
        SaveTherapyCheckInCheckOutTimeServiceResponse response = null;
        try {
                response = saveTherapyCheckInCheckOutTimeService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save Therapy CheckIn CheckOut Time ");
            log.error("Failed to save Therapy CheckIn CheckOut Time");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To Create TherapySchedule
    @PostMapping(value = "/createTherapySchedule", produces = "application/json")
    public ResponseEntity createTherapySchedule(@RequestBody CreateTherapyScheduleServiceRequest request) {
        log.debug("REST request to Create TherapySchedule");
        CreateTherapyScheduleServiceResponse response = null;
        try {
            response = createTherapyScheduleService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to Create TherapySchedule ");
            log.error("Failed to Create TherapySchedule ");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }
}
