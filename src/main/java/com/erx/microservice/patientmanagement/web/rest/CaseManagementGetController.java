package com.erx.microservice.patientmanagement.web.rest;

/*
 * created by Latha on 18-08-2018
 * */

import com.erx.microservice.patientmanagement.service.casemanagement.allcasesbypatientanddoctor.AllCasesByPatientAndDoctorService;
import com.erx.microservice.patientmanagement.service.casemanagement.allcasesbypatientanddoctor.AllCasesByPatientAndDoctorServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.allcasesbypatientanddoctor.AllCasesByPatientAndDoctorServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getacdmaster.CmAcdMasterService;
import com.erx.microservice.patientmanagement.service.casemanagement.getacdmaster.CmAcdMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicinesbyid.GetBkdGroupMedicineByIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicinesbyid.GetBkdGroupMedicineByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicinesbyid.GetBkdGroupMedicineByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getcaseidbyorderid.GetCaseIdByOrderIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getcaseidbyorderid.GetCaseIdByOrderIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getcaseidbyorderid.GetCaseIdByOrderIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicines.GetBkdGroupMedicineService;
import com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicines.GetBkdGroupMedicineServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicines.GetBkdGroupMedicineServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getcliniclocationsbyclinic.GetClinicLocationsByClinicService;
import com.erx.microservice.patientmanagement.service.casemanagement.getcliniclocationsbyclinic.GetClinicLocationsByClinicServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getcliniclocationsbyclinic.GetClinicLocationsByClinicServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getclinicpreference.GetClinicPreferenceByClinicService;
import com.erx.microservice.patientmanagement.service.casemanagement.getclinicpreference.GetClinicPreferenceByClinicServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getclinicpreference.GetClinicPreferenceByClinicServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getcmdosageinstruction.GetCmDosageInstructionService;
import com.erx.microservice.patientmanagement.service.casemanagement.getcmdosageinstruction.GetCmDosageInstructionServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getcmdosagevalue.GetCmDosageValueService;
import com.erx.microservice.patientmanagement.service.casemanagement.getcmdosagevalue.GetCmDosageValueServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationbycaseid.GetInvestigationByCaseIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationbycaseid.GetInvestigationByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationbycaseid.GetInvestigationByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationdetailsbycaseid.GetInvestigationDetailsByCaseIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationdetailsbycaseid.GetInvestigationDetailsByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationdetailsbycaseid.GetInvestigationDetailsByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplate.GetCmMedicineTemplateService;
import com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplate.GetCmMedicineTemplateServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplate.GetCmMedicineTemplateServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplatebytemplateid.GetCmMedicineTemplateByIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplatebytemplateid.GetCmMedicineTemplateByIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplatebytemplateid.GetCmMedicineTemplateByIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getcomplaintsbycaseid.GetComplaintsByCaseIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getcomplaintsbycaseid.GetComplaintsByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getcomplaintsbycaseid.GetComplaintsByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getdepartmentbycliniclocationid.GetDepartmentByClinicLocationIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getdepartmentbycliniclocationid.GetDepartmentByClinicLocationIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getdepartmentbycliniclocationid.GetDepartmentByClinicLocationIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getdischargedetailsbycaseid.GetDischargeDetailsByCaseIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getdischargedetailsbycaseid.GetDischargeDetailsByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getdischargedetailsbycaseid.GetDischargeDetailsByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getdoctorsbydepartmentid.GetDoctorsByDepartmentIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getdoctorsbydepartmentid.GetDoctorsByDepartmentIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getdoctorsbydepartmentid.GetDoctorsByDepartmentIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getexaminationbycaseid.GetExaminationByCaseIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getexaminationbycaseid.GetExaminationByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getexaminationbycaseid.GetExaminationByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getgynaecandobshistorybylookup.GetGynaecHistoryByLookupValueService;
import com.erx.microservice.patientmanagement.service.casemanagement.getgynaecandobshistorybylookup.GetGynaecHistoryByLookupValueServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getgynaecandobshistorybylookup.GetGynaecHistoryByLookupValueServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.geticdmaster.CmIcdMasterService;
import com.erx.microservice.patientmanagement.service.casemanagement.geticdmaster.CmIcdMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getitembyorderid.GetItemByOrderIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getitembyorderid.GetItemByOrderIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getitembyorderid.GetItemByOrderIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getmedicinetreatmentbycaseid.GetMedicineTreatmentByCaseIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getmedicinetreatmentbycaseid.GetMedicineTreatmentByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getmedicinetreatmentbycaseid.GetMedicineTreatmentByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getpathyapathyabycmtreatmentid.GetPathyaPathyaByCmTreatmentIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getpathyapathyabycmtreatmentid.GetPathyaPathyaByCmTreatmentIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getpathyapathyabycmtreatmentid.GetPathyaPathyaByCmTreatmentIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getpersonalhistorybycaseid.GetPersonalHistoryByCaseIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getpersonalhistorybycaseid.GetPersonalHistoryByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getpersonalhistorybycaseid.GetPersonalHistoryByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getprovisionaldiagnosismaster.ProvisionalDiagnosisMasterService;
import com.erx.microservice.patientmanagement.service.casemanagement.getprovisionaldiagnosismaster.ProvisionalDiagnosisMasterServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getrouteofadministration.RouteOfAdministrationService;
import com.erx.microservice.patientmanagement.service.casemanagement.getrouteofadministration.RouteOfAdministrationServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.gettherapyplanningIdsbyorderid.GetTherapyPlanningIdsByOrderIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.gettherapyplanningIdsbyorderid.GetTherapyPlanningIdsByOrderIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.gettherapyplanningIdsbyorderid.GetTherapyPlanningIdsByOrderIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.getuserdepartmentbyuserid.GetUserDepartmentByUserIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.getuserdepartmentbyuserid.GetUserDepartmentByUserIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getuserdepartmentbyuserid.GetUserDepartmentByUserIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.viewpatientpdfbycaseid.ViewPatientPdfByCaseIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.viewpatientpdfbycaseid.ViewPatientPdfByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.viewpatientpdfbycaseid.ViewPatientPdfByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.casemanagement.viewpdfbycaseid.ViewPdfByCaseIdService;
import com.erx.microservice.patientmanagement.service.casemanagement.viewpdfbycaseid.ViewPdfByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.viewpdfbycaseid.ViewPdfByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.AllCasesRequestDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.ViewPdfRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CaseManagementGetController {

    private final Logger log = LoggerFactory.getLogger(CaseManagementGetController.class);

    @Autowired
    private AllCasesByPatientAndDoctorService allCasesByPatientAndDoctorService;

    @Autowired
    private GetComplaintsByCaseIdService getComplaintsByCaseIdService;

    @Autowired
    private GetGynaecHistoryByLookupValueService getGynaecHistoryByLookupValueService;

    @Autowired
    private GetCmDosageValueService getCmDosageValueService;

    @Autowired
    private GetCmDosageInstructionService getCmDosageInstructionService;

    @Autowired
    private RouteOfAdministrationService routeOfAdministrationService;

    @Autowired
    private GetCmMedicineTemplateService getCmMedicineTemplateService;

    @Autowired
    private GetCmMedicineTemplateByIdService getCmMedicineTemplateByIdService;

    @Autowired
    private GetMedicineTreatmentByCaseIdService getMedicineTreatmentByCaseIdService;

    @Autowired
    private GetCaseIdByOrderIdService getCaseIdByOrderIdService;

    @Autowired
    private GetBkdGroupMedicineService getBkdGroupMedicineService;

    @Autowired
    private GetBkdGroupMedicineByIdService getBkdGroupMedicineByIdService;

    @Autowired
    private GetPathyaPathyaByCmTreatmentIdService getPathyaPathyaByCmTreatmentIdService;

    @Autowired
    private ProvisionalDiagnosisMasterService provisionalDiagnosisMasterService;

    @Autowired
    private CmAcdMasterService cmAcdMasterService;

    @Autowired
    private CmIcdMasterService cmIcdMasterService;

    @Autowired
    private GetInvestigationByCaseIdService getInvestigationByCaseIdService;

    @Autowired
    private GetInvestigationDetailsByCaseIdService getInvestigationDetailsByCaseIdService;

    @Autowired
    private GetTherapyPlanningIdsByOrderIdService getTherapyPlanningIdsByOrderIdService;

    @Autowired
    private GetItemByOrderIdService getItemByOrderIdService;

    @Autowired
    private ViewPdfByCaseIdService viewPdfByCaseIdService;

    @Autowired
    private ViewPatientPdfByCaseIdService viewPatientPdfByCaseIdService;

    @Autowired
    private GetDischargeDetailsByCaseIdService getDischargeDetailsByCaseIdService;

    @Autowired
    private GetPersonalHistoryByCaseIdService getPersonalHistoryByCaseIdService;

    @Autowired
    private GetClinicPreferenceByClinicService getClinicPreferenceByClinicService;

    @Autowired
    private GetExaminationByCaseIdService getExaminationByCaseIdService;

    @Autowired
    private GetClinicLocationsByClinicService getClinicLocationsByClinicService;

    @Autowired
    private GetUserDepartmentByUserIdService getUserDepartmentByUserIdService;

    @Autowired
    private GetDepartmentByClinicLocationIdService getDepartmentByClinicLocationIdService;

    @Autowired
    private GetDoctorsByDepartmentIdService getDoctorsByDepartmentIdService;

    // To get patient case sheet details
    @PostMapping(value = "/getAllCasesByPatientAndDoctor", produces = "application/json")
    public ResponseEntity getAllCasesByPatientAndDoctor(@RequestBody AllCasesRequestDTO allCasesRequestDTO, Pageable pageable) {
        log.debug("REST request to Get all case details by patient and doctor");
        AllCasesByPatientAndDoctorServiceResponse response = new AllCasesByPatientAndDoctorServiceResponse();
        try {
            AllCasesByPatientAndDoctorServiceRequest request = new AllCasesByPatientAndDoctorServiceRequest(allCasesRequestDTO, pageable);
            if(request.getAllCasesRequestDTO().getPatientId() != null )
            response = allCasesByPatientAndDoctorService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Patient case details By patientId");
            log.error("Failed to retrieve Patient case details By patientId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get all drop details history details
    @GetMapping(value = "/getAllDropDownsByLookupValueId", produces = "application/json")
    public ResponseEntity getAllDropDownsByLookupValueId(@RequestParam Long clinicId, Long LookupValueId) {
        log.debug("REST request to Get all Gynaec History and obs history By lookup value id");
        GetGynaecHistoryByLookupValueServiceResponse response = new GetGynaecHistoryByLookupValueServiceResponse();
        try {
            GetGynaecHistoryByLookupValueServiceRequest request = new GetGynaecHistoryByLookupValueServiceRequest(clinicId, LookupValueId);
            response = getGynaecHistoryByLookupValueService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve all Gynaec History and obs history ByItemType");
            log.error("Failed to retrieve all Gynaec History and obs history ByItemType");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get complaint case details
    @GetMapping(value = "/getComplaintsByCaseId", produces = "application/json")
    public ResponseEntity getComplaintsByCaseId(@RequestParam Long caseId) {
        log.debug("REST request to Get complaints by case id");
        GetComplaintsByCaseIdServiceResponse response = new GetComplaintsByCaseIdServiceResponse();
        try {
            GetComplaintsByCaseIdServiceRequest request = new GetComplaintsByCaseIdServiceRequest(caseId);
            response = getComplaintsByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve complaints details By caseId");
            log.error("Failed to retrieve complaints details By caseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    // To get cm dosage value mapping
    @GetMapping(value = "/getCmDosageValueMapping", produces = "application/json")
    public ResponseEntity getCmDosageValueMapping() {
        log.debug("REST request to Get cm dosage values");
        GetCmDosageValueServiceResponse response = new GetCmDosageValueServiceResponse();
        try {
            response = getCmDosageValueService.execute();
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm dosage value details");
            log.error("Failed to retrieve cm dosage value details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get cm dosage instruction
    @GetMapping(value = "/getCmDosageInstructionDetails", produces = "application/json")
    public ResponseEntity getCmDosageInstructionDetails() {
        log.debug("REST request to Get cm dosage instruction");
        GetCmDosageInstructionServiceResponse response = new GetCmDosageInstructionServiceResponse();
        try {
            response = getCmDosageInstructionService.execute();
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm dosage instruction details");
            log.error("Failed to retrieve cm dosage instruction details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get route of administration
    @GetMapping(value = "/getRouteOfAdministrationDetails", produces = "application/json")
    public ResponseEntity getRouteOfAdministrationDetails() {
        log.debug("REST request to Get route of administration");
        RouteOfAdministrationServiceResponse response = new RouteOfAdministrationServiceResponse();
        try {
            response = routeOfAdministrationService.execute();
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve route of administration details");
            log.error("Failed to retrieve route of administration details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get medicine templates
    @GetMapping(value = "/getMedicineTemplates", produces = "application/json")
    public ResponseEntity getMedicineTemplates(@RequestParam Long clinicId, @RequestParam Long scienceOfMedicineId) {
        log.debug("REST request to Get medicine template");
        GetCmMedicineTemplateServiceResponse response = new GetCmMedicineTemplateServiceResponse();
        try {
            GetCmMedicineTemplateServiceRequest request = new GetCmMedicineTemplateServiceRequest(clinicId,scienceOfMedicineId);
            if(clinicId != null || scienceOfMedicineId != null)
            response = getCmMedicineTemplateService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve medicine templates");
            log.error("Failed to retrieve medicine templates");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get medicine template details by template id
    @GetMapping(value = "/getMedicineTemplateByTemplateId", produces = "application/json")
    public ResponseEntity getMedicineTemplateByTemplateId(@RequestParam Long cmTemplateId) {
        log.debug("REST request to Get medicine template by id");
        GetCmMedicineTemplateByIdServiceResponse response = new GetCmMedicineTemplateByIdServiceResponse();
        try {
            GetCmMedicineTemplateByIdServiceRequest request = new GetCmMedicineTemplateByIdServiceRequest(cmTemplateId);
            if(cmTemplateId != null)
                response = getCmMedicineTemplateByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve medicine templates by id");
            log.error("Failed to retrieve medicine templates by id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get medicine treatment details by case id
    @GetMapping(value = "/getMedicineTreatmentByCaseId", produces = "application/json")
    public ResponseEntity getMedicineTreatmentByCaseId(@RequestParam Long caseId) {
        log.debug("REST request to Get medicine treatment by case id");
        GetMedicineTreatmentByCaseIdServiceResponse response = new GetMedicineTreatmentByCaseIdServiceResponse();
        try {
            GetMedicineTreatmentByCaseIdServiceRequest request = new GetMedicineTreatmentByCaseIdServiceRequest(caseId);
            if(caseId != null)
            response = getMedicineTreatmentByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve medicine treatment details By caseId");
            log.error("Failed to retrieve medicine treatment details By caseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get pathya pathya details by CaseId
    @GetMapping(value = "/getPathyaPathyaByCaseId", produces = "application/json")
    public ResponseEntity getPathyaPathyaByCaseId(@RequestParam Long caseId) {
        log.debug("REST request to Get pathya pathya by CaseId");
        GetPathyaPathyaByCmTreatmentIdServiceResponse response = new GetPathyaPathyaByCmTreatmentIdServiceResponse();
        try {
            GetPathyaPathyaByCmTreatmentIdServiceRequest request = new GetPathyaPathyaByCmTreatmentIdServiceRequest(caseId);
            if(caseId != null)
            response = getPathyaPathyaByCmTreatmentIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve pathya pathya details By CaseId");
            log.error("Failed to retrieve pathya pathya details By CaseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // to get caseId for given OrderId
    @GetMapping(value = "/getCaseIdByOrderId", produces = "application/json")
    public ResponseEntity getCaseIdByOrderId(@RequestParam Long orderId) {
        log.debug("REST request to get caseId for given OrderId");
        GetCaseIdByOrderIdServiceResponse response = new GetCaseIdByOrderIdServiceResponse();
        try {
            GetCaseIdByOrderIdServiceRequest request = new GetCaseIdByOrderIdServiceRequest(orderId);
            response = getCaseIdByOrderIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to get caseId for given OrderId");
            log.error("Failed to get caseId for given OrderId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get bkd group medicines
    @GetMapping(value = "/getBkdGroupMedicines", produces = "application/json")
    public ResponseEntity getBkdGroupMedicines(@RequestParam Long clinicId, Pageable pageable) {
        log.debug("REST request to Get bkd group medicines");
        GetBkdGroupMedicineServiceResponse response = new GetBkdGroupMedicineServiceResponse();
        try {
            GetBkdGroupMedicineServiceRequest request = new GetBkdGroupMedicineServiceRequest(clinicId, pageable);
            if(clinicId != null)
                response = getBkdGroupMedicineService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve bkd group medicines");
            log.error("Failed to retrieve bkd group medicines");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get bkd group medicines by id
    @GetMapping(value = "/getBkdGroupMedicinesById", produces = "application/json")
    public ResponseEntity getBkdGroupMedicinesById(@RequestParam Long clinicId, Long groupMedicineId) {
        log.debug("REST request to Get bkd group medicines by id");
        GetBkdGroupMedicineByIdServiceResponse response = new GetBkdGroupMedicineByIdServiceResponse();
        try {
            GetBkdGroupMedicineByIdServiceRequest request = new GetBkdGroupMedicineByIdServiceRequest(clinicId,groupMedicineId);
            if(groupMedicineId != null)
                response = getBkdGroupMedicineByIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve bkd group medicines by id");
            log.error("Failed to retrieve bkd group medicines by id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get provisional diagnosis master details
    @GetMapping(value = "/getProvisionalDiagnosisDetails", produces = "application/json")
    public ResponseEntity getProvisionalDiagnosisDetails() {
        log.debug("REST request to Get provisional diagnosis master");
        ProvisionalDiagnosisMasterServiceResponse response = new ProvisionalDiagnosisMasterServiceResponse();
        try {
            response = provisionalDiagnosisMasterService.execute();
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve provisional diagnosis master details");
            log.error("Failed to retrieve provisional diagnosis master details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get acd master details
    @GetMapping(value = "/getAcdMasterDetails", produces = "application/json")
    public ResponseEntity getAcdMasterDetails() {
        log.debug("REST request to Get acd master");
        CmAcdMasterServiceResponse response = new CmAcdMasterServiceResponse();
        try {
            response = cmAcdMasterService.execute();
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve acd master details");
            log.error("Failed to retrieve acd master details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get icd master details
    @GetMapping(value = "/getIcdMasterDetails", produces = "application/json")
    public ResponseEntity getIcdMasterDetails() {
        log.debug("REST request to Get icd master");
        CmIcdMasterServiceResponse response = new CmIcdMasterServiceResponse();
        try {
            response = cmIcdMasterService.execute();
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve icd master details");
            log.error("Failed to retrieve icd master details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    // To get cm investigation by case id
    @GetMapping(value = "/getCmInvestigationByCaseId", produces = "application/json")
    public ResponseEntity getCmInvestigationByCaseId(@RequestParam Long caseId) {
        log.debug("REST request to Get cm investigation by case id");
        GetInvestigationByCaseIdServiceResponse response = new GetInvestigationByCaseIdServiceResponse();
        try {
            GetInvestigationByCaseIdServiceRequest request = new GetInvestigationByCaseIdServiceRequest(caseId);
            if(caseId != null)
                response = getInvestigationByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm investigation By caseId");
            log.error("Failed to retrieve cm investigation By caseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get cm investigation details by case id
    @PostMapping(value = "/getCmInvestigationDetailsByCaseId", produces = "application/json")
    public ResponseEntity getCmInvestigationDetailsByCaseId(@RequestBody GetInvestigationDetailsByCaseIdServiceRequest request) {
        log.debug("REST request to Get cm investigation details by case id");
        GetInvestigationDetailsByCaseIdServiceResponse response = new GetInvestigationDetailsByCaseIdServiceResponse();
        try {
            if(request.getCaseId() != null && request.getClinicLocationId() != null && request.getPatientId() != null && request.getUserId() != null &&request.getClinicId() != null)
                response = getInvestigationDetailsByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve cm investigation details by caseId");
            log.error("Failed to retrieve cm investigation details by caseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get patient cTherapyPlanning Ids for given orderId
    @GetMapping(value = "/getTherapyPlanningIdsByOrderId", produces = "application/json")
    public ResponseEntity getAllCasesByPatientAndDoctor(@RequestParam Long orderId) {
        log.debug("REST request to Get TherapyPlanning Ids for given orderId ");
        GetTherapyPlanningIdsByOrderIdServiceResponse response = new GetTherapyPlanningIdsByOrderIdServiceResponse();
        try {
            GetTherapyPlanningIdsByOrderIdServiceRequest request = new GetTherapyPlanningIdsByOrderIdServiceRequest(orderId);
            if(request.getOrderId() != null )
                response = getTherapyPlanningIdsByOrderIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve TherapyPlanning Ids for given orderId");
            log.error("Failed to retrieve TherapyPlanning Ids for given orderId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get BillingOrder and status By OrderId
    @RequestMapping(value = "/getItemByOrderId", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getItemByOrderId(@RequestParam String orderId) {

        log.debug("REST request to get All BillingOrder for given orderId");
        GetItemByOrderIdServiceResponse response = new GetItemByOrderIdServiceResponse();
        try {
            GetItemByOrderIdServiceRequest request = new GetItemByOrderIdServiceRequest(orderId);
            response = getItemByOrderIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("BillingOrder Details are not retrieved successfully for given orderId");
            log.error("BillingOrder  Details are not retrieved successfully for given orderId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To view pdf case details
    @PostMapping(value = "/viewPdfByCaseId", produces = "application/json")
    public ResponseEntity viewPdfByCaseId(@RequestBody ViewPdfRequestDTO viewPdfRequestDTO) {
        log.debug("REST request to Get view pdf by case id");
        ViewPdfByCaseIdServiceResponse response = new ViewPdfByCaseIdServiceResponse();
        try {
            ViewPdfByCaseIdServiceRequest request = new ViewPdfByCaseIdServiceRequest(viewPdfRequestDTO);
            response = viewPdfByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve pdf details By caseId");
            log.error("Failed to retrieve pdf details By caseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To view patient pdf case details
    @PostMapping(value = "/viewPatientPdfByCaseId", produces = "application/json")
    public ResponseEntity viewPatientPdfByCaseId(@RequestBody ViewPdfRequestDTO viewPdfRequestDTO) {
        log.debug("REST request to Get view patient pdf by case id");
        ViewPatientPdfByCaseIdServiceResponse response = new ViewPatientPdfByCaseIdServiceResponse();
        try {
            ViewPatientPdfByCaseIdServiceRequest request = new ViewPatientPdfByCaseIdServiceRequest(viewPdfRequestDTO);
            response = viewPatientPdfByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve patient pdf details By caseId");
            log.error("Failed to retrieve patient pdf details By caseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get discharge case details by case id
    @PostMapping(value = "/getDischargeDetailsByCaseId", produces = "application/json")
    public ResponseEntity getDischargeDetailsByCaseId(@RequestBody GetDischargeDetailsByCaseIdServiceRequest request) {
        log.debug("REST request to Get discharge by case id");
        GetDischargeDetailsByCaseIdServiceResponse response = new GetDischargeDetailsByCaseIdServiceResponse();
        try {
            if(request.getCaseId() != null && request.getClinicId() != null && request.getPatientId() != null && request.getUserId() != null)

            response = getDischargeDetailsByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve discharge details By caseId");
            log.error("Failed to retrieve discharge details By caseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    // To get personal history case details by case id
    @GetMapping(value = "/getPersonalHistoryDetailsByCaseId", produces = "application/json")
    public ResponseEntity getPersonalHistoryDetailsByCaseId(@RequestParam Long caseId) {
        log.debug("REST request to Get personal history by case id");
        GetPersonalHistoryByCaseIdServiceResponse response = new GetPersonalHistoryByCaseIdServiceResponse();
        try {
            GetPersonalHistoryByCaseIdServiceRequest request = new GetPersonalHistoryByCaseIdServiceRequest(caseId);
            if(request.getCaseId() != null)
                response = getPersonalHistoryByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve personal history details By caseId");
            log.error("Failed to retrieve personal history details By caseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To clinic preference by clinic id
    @GetMapping(value = "/getClinicPreferenceByClinic", produces = "application/json")
    public ResponseEntity getClinicPreferenceByClinic(@RequestParam Long clinicId) {
        log.debug("REST request to Get clinic preference by clinic");
        GetClinicPreferenceByClinicServiceResponse response = new GetClinicPreferenceByClinicServiceResponse();
        try {
            GetClinicPreferenceByClinicServiceRequest request = new GetClinicPreferenceByClinicServiceRequest(clinicId);
            if(request.getClinicId() != null)
                response = getClinicPreferenceByClinicService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve clinic preference by clinic");
            log.error("Failed to retrieve clinic preference by clinic");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get examination case details by case id
    @GetMapping(value = "/getExaminationDetailsByCaseId", produces = "application/json")
    public ResponseEntity getExaminationDetailsByCaseId(@RequestParam Long caseId) {
        log.debug("REST request to Get examination by case id");
        GetExaminationByCaseIdServiceResponse response = new GetExaminationByCaseIdServiceResponse();
        try {
            GetExaminationByCaseIdServiceRequest request = new GetExaminationByCaseIdServiceRequest(caseId);
            if(request.getCaseId() != null)
                response = getExaminationByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve examination details By caseId");
            log.error("Failed to retrieve examination details By caseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

   // To get clinic locations by clinic id
    @GetMapping(value = "/getClinicLocationsByClinic", produces = "application/json")
    public ResponseEntity getClinicLocationsByClinic(@RequestParam Long clinicId) {
        log.debug("REST request to Get clinic location by clinic id");
        GetClinicLocationsByClinicServiceResponse response = new GetClinicLocationsByClinicServiceResponse();
        try {
            GetClinicLocationsByClinicServiceRequest request = new GetClinicLocationsByClinicServiceRequest(clinicId);
            if(request.getClinicId() != null)
                response = getClinicLocationsByClinicService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve clinic location details By clinic id");
            log.error("Failed to retrieve clinic location details By clinic id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get user department id by user id
    @GetMapping(value = "/getUserDepartmentIdByUserId", produces = "application/json")
    public ResponseEntity getUserDepartmentIdByUserId(@RequestParam Long userId) {
        log.debug("REST request to Get user department id by user id");
        GetUserDepartmentByUserIdServiceResponse response = new GetUserDepartmentByUserIdServiceResponse();
        try {
            GetUserDepartmentByUserIdServiceRequest request = new GetUserDepartmentByUserIdServiceRequest(userId);
            if(request.getUserId() != null)
                response = getUserDepartmentByUserIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve user department id by user id");
            log.error("Failed to retrieve user department id by user id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get department by clinic location id
    @GetMapping(value = "/getDepartmentByClinicLocationId", produces = "application/json")
    public ResponseEntity getDepartmentByClinicLocationId(@RequestParam Long clinicLocationId) {
        log.debug("REST request to Get department by clinic location id");
        GetDepartmentByClinicLocationIdServiceResponse response = new GetDepartmentByClinicLocationIdServiceResponse();
        try {
            GetDepartmentByClinicLocationIdServiceRequest request = new GetDepartmentByClinicLocationIdServiceRequest(clinicLocationId);
            if(request.getClinicLocationId() != null)
                response = getDepartmentByClinicLocationIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve department by clinic location id");
            log.error("Failed to retrieve department by clinic location id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get doctors by department id
    @GetMapping(value = "/getDoctorsByDepartmentId", produces = "application/json")
    public ResponseEntity getDoctorsByDepartmentId(@RequestParam Long departmentId, @RequestParam Long clinicId) {
        log.debug("REST request to Get doctors by department id");
        GetDoctorsByDepartmentIdServiceResponse response = new GetDoctorsByDepartmentIdServiceResponse();
        try {
            GetDoctorsByDepartmentIdServiceRequest request = new GetDoctorsByDepartmentIdServiceRequest(departmentId, clinicId);
            if(request.getDepartmentId() != null)
                response = getDoctorsByDepartmentIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve doctors by department id");
            log.error("Failed to retrieve doctors by id");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

}
