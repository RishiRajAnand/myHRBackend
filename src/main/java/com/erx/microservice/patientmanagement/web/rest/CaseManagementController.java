package com.erx.microservice.patientmanagement.web.rest;

/*
 * created by Latha on 18-08-2018
 * */

import com.erx.microservice.patientmanagement.service.casemanagement.deletebkdgroupmedicine.DeleteBkdGroupMedicineService;
import com.erx.microservice.patientmanagement.service.casemanagement.deletebkdgroupmedicine.DeleteBkdGroupMedicineServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.deletebkdgroupmedicine.DeleteBkdGroupMedicineServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetemplate.DeleteMedicineTemplateService;
import com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetemplate.DeleteMedicineTemplateServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetemplate.DeleteMedicineTemplateServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetreatment.DeleteMedicineTreatmentService;
import com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetreatment.DeleteMedicineTreatmentServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.deletemedicinetreatment.DeleteMedicineTreatmentServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savebkdgroupmedicine.SaveBKDGroupMedicineService;
import com.erx.microservice.patientmanagement.service.casemanagement.savebkdgroupmedicine.SaveBKDGroupMedicineServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savebkdgroupmedicine.SaveBKDGroupMedicineServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savecasedetailwithoutcasesheet.SaveCaseDetailWithoutCaseSheetService;
import com.erx.microservice.patientmanagement.service.casemanagement.savecasedetailwithoutcasesheet.SaveCaseDetailWithoutCaseSheetServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savecasedetailwithoutcasesheet.SaveCaseDetailWithoutCaseSheetServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savecasedischarge.SaveCaseDischargeService;
import com.erx.microservice.patientmanagement.service.casemanagement.savecasedischarge.SaveCaseDischargeServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savecasedischarge.SaveCaseDischargeServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savecasetransfer.SaveCaseTransferService;
import com.erx.microservice.patientmanagement.service.casemanagement.savecasetransfer.SaveCaseTransferServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savecasetransfer.SaveCaseTransferServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savecmnextvisit.SaveCmNextVisitService;
import com.erx.microservice.patientmanagement.service.casemanagement.savecmnextvisit.SaveCmNextVisitServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savecmnextvisit.SaveCmNextVisitServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savecomplaint.SaveComplaintService;
import com.erx.microservice.patientmanagement.service.casemanagement.savecomplaint.SaveComplaintServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savecomplaint.SaveComplaintServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.saveexamination.SaveExaminationService;
import com.erx.microservice.patientmanagement.service.casemanagement.saveexamination.SaveExaminationServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.saveexamination.SaveExaminationServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigation.SaveInvestigationService;
import com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigation.SaveInvestigationServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigation.SaveInvestigationServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigationandtreatmentdetails.SaveInvestigationAndTreatmentDetailsService;
import com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigationandtreatmentdetails.SaveInvestigationAndTreatmentDetailsServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigationandtreatmentdetails.SaveInvestigationAndTreatmentDetailsServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetemplate.SaveMedicineTemplateService;
import com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetemplate.SaveMedicineTemplateServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetemplate.SaveMedicineTemplateServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetreatment.SaveMedicineTreatmentService;
import com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetreatment.SaveMedicineTreatmentServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetreatment.SaveMedicineTreatmentServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.savepersonalhistory.SavePersonalHistoryService;
import com.erx.microservice.patientmanagement.service.casemanagement.savepersonalhistory.SavePersonalHistoryServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.savepersonalhistory.SavePersonalHistoryServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.updatepatientandvitals.UpdatePatientAndVitalsService;
import com.erx.microservice.patientmanagement.service.casemanagement.updatepatientandvitals.UpdatePatientAndVitalsServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.updatepatientandvitals.UpdatePatientAndVitalsServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveCaseDetailWithoutCaseSheetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CaseManagementController {

    private final Logger log = LoggerFactory.getLogger(CaseManagementController.class);

    @Autowired
    private UpdatePatientAndVitalsService updatePatientAndVitalsService;

    @Autowired
    private SaveComplaintService saveComplaintService;

    @Autowired
    private SaveMedicineTreatmentService saveMedicineTreatmentService;

    @Autowired
    private DeleteMedicineTreatmentService deleteMedicineTreatmentService;

    @Autowired
    private SaveBKDGroupMedicineService saveBKDGroupMedicineService;

    @Autowired
    private SaveMedicineTemplateService saveMedicineTemplateService;

    @Autowired
    private DeleteMedicineTemplateService deleteMedicineTemplateService;

    @Autowired
    private SaveCaseDetailWithoutCaseSheetService saveCaseDetailWithoutCaseSheetService;

    @Autowired
    private DeleteBkdGroupMedicineService deleteBkdGroupMedicineService;

    @Autowired
    private SaveInvestigationService saveInvestigationService;

    @Autowired
    private SaveInvestigationAndTreatmentDetailsService saveInvestigationAndTreatmentDetailsService;

    @Autowired
    private SaveCaseDischargeService saveCaseDischargeService;

    @Autowired
    private SavePersonalHistoryService savePersonalHistoryService;

    @Autowired
    private SaveExaminationService saveExaminationService;

    @Autowired
    private SaveCmNextVisitService saveCmNextVisitService;

    @Autowired
    private SaveCaseTransferService saveCaseTransferService;

    //To update patient and vitals details from case sheet
    @PostMapping(value = "/updatePatientVitalDetails", produces = "application/json")
    public ResponseEntity updatePatientVitalDetails(@RequestBody UpdatePatientAndVitalsServiceRequest request) {
        log.debug("REST request to update patient and vital details");
        UpdatePatientAndVitalsServiceResponse response = null;
        try {
            if(request.getPatientVitalsRequestDTO().getPatientId() != null)
            response = updatePatientAndVitalsService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to update patient and vital details");
            log.error("Failed to update patient and vital details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save complaints from case sheet
    @PostMapping(value = "/saveComplaints", produces = "application/json")
    public ResponseEntity saveComplaints(@RequestBody SaveComplaintServiceRequest request) {
        log.debug("REST request to save complaints and cm master");
        SaveComplaintServiceResponse response = null;
        try {
            if(request.getSaveComplaintsDTO() != null)
            response = saveComplaintService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save complaints of case");
            log.error("Failed to save complaints of case");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save personal history from case sheet
    @PostMapping(value = "/savePersonalHistory", produces = "application/json")
    public ResponseEntity savePersonalHistory(@RequestBody SavePersonalHistoryServiceRequest request) {
        log.debug("REST request to save personal history");
        SavePersonalHistoryServiceResponse response = null;
        try {
            if(request.getSaveCmPersonalHistoryDTO().getCmMasterId() != null && request.getSaveCmPersonalHistoryDTO().getClinicId() != null)
            response = savePersonalHistoryService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save personal history of case");
            log.error("Failed to save personal history of case");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save treatment(medicine) from case sheet
    @PostMapping(value = "/saveMedicineTreatment", produces = "application/json")
    public ResponseEntity saveMedicineTreatment(@RequestBody SaveMedicineTreatmentServiceRequest request) {
        log.debug("REST request to save medicine treatment");
        SaveMedicineTreatmentServiceResponse response = null;
        try {
            if(request.getSaveMedicineDTO().getCmMasterId() != null)
            response = saveMedicineTreatmentService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save medicine treatment of case");
            log.error("Failed to save medicine treatment of case");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To delete treatment(medicine) from case sheet
    @DeleteMapping(value = "/deleteMedicineTreatment", produces = "application/json")
    public ResponseEntity deleteMedicineTreatment(@RequestBody DeleteMedicineTreatmentServiceRequest request) {
        log.debug("REST request to delete medicine treatment");
        DeleteMedicineTreatmentServiceResponse response = null;
        try {
            if(request.getDeleteMedicineTreatmentDTO() != null)
                response = deleteMedicineTreatmentService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to delete medicine treatment of case");
            log.error("Failed to delete medicine treatment of case");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save bkd group medicine
    @PostMapping(value = "/saveBkdGroupMedicine", produces = "application/json")
    public ResponseEntity saveBkdGroupMedicine(@RequestBody SaveBKDGroupMedicineServiceRequest request) {
        log.debug("REST request to save bkd group medicine treatment");
        SaveBKDGroupMedicineServiceResponse response = null;
        try {
            if(request.getSaveBKDGroupMedicineDTO().getClinicId() != null)
                response = saveBKDGroupMedicineService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save bkd group medicine treatment");
            log.error("Failed to save bkd group medicine treatment");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save medicine template
    @PostMapping(value = "/saveMedicineTemplate", produces = "application/json")
    public ResponseEntity saveMedicineTemplate(@RequestBody SaveMedicineTemplateServiceRequest request) {
        log.debug("REST request to save medicine template");
        SaveMedicineTemplateServiceResponse response = null;
        try {
            if(request.getSaveMedicineTemplateDTO().getClinicId() != null)
                response = saveMedicineTemplateService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save medicine template");
            log.error("Failed to save medicine template");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To delete medicine template by id
    @DeleteMapping(value = "/deleteMedicineTemplateById", produces = "application/json")
    public ResponseEntity deleteMedicineTemplateById(@RequestParam Long cmTemplateId) {
        log.debug("REST request to delete medicine template by id ");
        DeleteMedicineTemplateServiceResponse response = null;
        try {
            DeleteMedicineTemplateServiceRequest request = new DeleteMedicineTemplateServiceRequest(cmTemplateId);
            if(request.getCmTemplateId() != null)
                response = deleteMedicineTemplateService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to delete medicine template by id");
            log.error("Failed to delete medicine template by id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save  CaseDetail Without CaseSheet
    @PostMapping(value = "/saveCaseDetailWithoutCaseSheet", produces = "application/json")
    public ResponseEntity saveCaseDetailWithoutCaseSheet(@RequestBody SaveCaseDetailWithoutCaseSheetDTO saveCaseDetailWithoutCaseSheetDTO) {
        log.debug("REST request to save CaseDetail Without CaseSheet");
        SaveCaseDetailWithoutCaseSheetServiceResponse response = null;
        try {

            if(saveCaseDetailWithoutCaseSheetDTO != null) {
                SaveCaseDetailWithoutCaseSheetServiceRequest request = new SaveCaseDetailWithoutCaseSheetServiceRequest(saveCaseDetailWithoutCaseSheetDTO);
                response = saveCaseDetailWithoutCaseSheetService.execute(request);
            }
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save  CaseDetail Without CaseSheet");
            log.error("Failed to save CaseDetail Without CaseSheet");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To delete bkd group medicine by id
    @DeleteMapping(value = "/deleteBkdGroupMedicineById", produces = "application/json")
    public ResponseEntity deleteBkdGroupMedicineById(@RequestParam Long cmGroupMedicineMasterId) {
        log.debug("REST request to delete bkd group medicine by id");
        DeleteBkdGroupMedicineServiceResponse response = null;
        try {
            DeleteBkdGroupMedicineServiceRequest request = new DeleteBkdGroupMedicineServiceRequest(cmGroupMedicineMasterId);
            if(request.getCmGroupMedicineMasterId() != null)
                response = deleteBkdGroupMedicineService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to delete bkd group medicine by id");
            log.error("Failed to delete bkd group medicine by id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To save cm investigation details
    @PostMapping(value = "/saveCmInvestigation", produces = "application/json")
    public ResponseEntity saveCmInvestigation(@RequestBody SaveInvestigationServiceRequest request) {
        log.debug("REST request to save cm investigation");
        SaveInvestigationServiceResponse response = null;
        try {
            if(request.getSaveInvestigationDTO().getCmMasterId() != null)
                response = saveInvestigationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save cm investigation");
            log.error("Failed to save cm investigation");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save cm investigation and medicine, therapy treatment details
    @PostMapping(value = "/saveInvestigationAndTreatmentDetails", produces = "application/json")
    public ResponseEntity saveInvestigationAndTreatmentDetails(@RequestBody SaveInvestigationAndTreatmentDetailsServiceRequest request) {
        log.debug("REST request to save investigation and treatment details");
        SaveInvestigationAndTreatmentDetailsServiceResponse response = null;
        try {
            if(request.getSaveInvestigationAndTreatmentDTO().getCmMasterId() != null)
                response = saveInvestigationAndTreatmentDetailsService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save investigation and treatment details");
            log.error("Failed to save investigation and treatment details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save discharge medicine, therapy treatment details
    @PostMapping(value = "/saveCaseDischarge", produces = "application/json")
    public ResponseEntity saveCaseDischarge(@RequestBody SaveCaseDischargeServiceRequest request) {
        log.debug("REST request to save discharge treatment details");
        SaveCaseDischargeServiceResponse response = null;
        try {
            if(request.getSaveDischargeDTO().getSaveInvestigationAndTreatmentDTO().getCmMasterId() != null)
                response = saveCaseDischargeService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save discharge treatment details");
            log.error("Failed to save discharge treatment details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save examination section of case sheet
    @PostMapping(value = "/saveExamination", produces = "application/json")
    public ResponseEntity saveExamination(@RequestBody SaveExaminationServiceRequest request) {
        log.debug("REST request to save examination details");
        SaveExaminationServiceResponse response = null;
        try {
            if(request.getSaveCmExaminationDTO().getCmMasterId() != null)
                response = saveExaminationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save examination details");
            log.error("Failed to save examination details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save next visit of case sheet
    @PostMapping(value = "/saveNextVisit", produces = "application/json")
    public ResponseEntity saveNextVisit(@RequestBody SaveCmNextVisitServiceRequest request) {
        log.debug("REST request to save next visit details");
        SaveCmNextVisitServiceResponse response = null;
        try {
            if(request.getCmNextVisitDTO().getCmMasterId() != null)
                response = saveCmNextVisitService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save next visit details");
            log.error("Failed to save next visit details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save case transfer in case sheet
    @PostMapping(value = "/saveCaseTransfer", produces = "application/json")
    public ResponseEntity saveCaseTransfer(@RequestBody SaveCaseTransferServiceRequest request) {
        log.debug("REST request to save transfer case details");
        SaveCaseTransferServiceResponse response = null;
        try {
            if(request.getCaseTransferRequestDTO().getCaseId() != null)
                response = saveCaseTransferService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save transfer case details");
            log.error("Failed to save transfer case details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }
}
