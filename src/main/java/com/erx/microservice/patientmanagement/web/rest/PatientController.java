package com.erx.microservice.patientmanagement.web.rest;

/*
 * created by latha on 06-11-2017
 * */

import com.erx.microservice.patientmanagement.service.dto.NonRegisteredPatientDTO;
import com.erx.microservice.patientmanagement.service.dto.patientalldetailsdto.PatientAllDetailsDTO;
import com.erx.microservice.patientmanagement.service.exportcampcsvfile.ExportCampCSVFileService;
import com.erx.microservice.patientmanagement.service.exportcampcsvfile.ExportCampCSVFileServiceRequest;
import com.erx.microservice.patientmanagement.service.exportcampcsvfile.ExportCampCSVFileServiceResponse;
import com.erx.microservice.patientmanagement.service.importcsvfile.ImportCampCSVFileService;
import com.erx.microservice.patientmanagement.service.importcsvfile.ImportCampCSVFileServiceRequest;
import com.erx.microservice.patientmanagement.service.importcsvfile.ImportCampCSVFileServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.patientsearch.PatientSearchService;
import com.erx.microservice.patientmanagement.service.ipadmission.patientsearch.PatientSearchServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.patientsearch.PatientSearchServiceResponse;
import com.erx.microservice.patientmanagement.service.patient.convertnonregisteredtoregisteredpatient.ConvertNonRegisteredToRegisterPatientService;
import com.erx.microservice.patientmanagement.service.patient.convertnonregisteredtoregisteredpatient.ConvertNonRegisteredToRegisterPatientServiceRequest;
import com.erx.microservice.patientmanagement.service.patient.convertnonregisteredtoregisteredpatient.ConvertNonRegisteredToRegisterPatientServiceResponse;
import com.erx.microservice.patientmanagement.service.patient.getalladmittedpatientbycliniclocation.GetAllAdmittedPatientByClinicLocationService;
import com.erx.microservice.patientmanagement.service.patient.getalladmittedpatientbycliniclocation.GetAllAdmittedPatientByClinicLocationServiceRequest;
import com.erx.microservice.patientmanagement.service.patient.getalladmittedpatientbycliniclocation.GetAllAdmittedPatientByClinicLocationServiceResponse;
import com.erx.microservice.patientmanagement.service.patient.getpatientipdetailsbypatientid.GetPatientIPDetailsByPatientIdService;
import com.erx.microservice.patientmanagement.service.patient.getpatientipdetailsbypatientid.GetPatientIPDetailsByPatientIdServiceRequest;
import com.erx.microservice.patientmanagement.service.patient.getpatientipdetailsbypatientid.GetPatientIPDetailsByPatientIdServiceResponse;
import com.erx.microservice.patientmanagement.service.patient.savenonregisteredpatients.SaveNonRegisteredPatientService;
import com.erx.microservice.patientmanagement.service.patient.savenonregisteredpatients.SaveNonRegisteredPatientServiceRequest;
import com.erx.microservice.patientmanagement.service.patient.savenonregisteredpatients.SaveNonRegisteredPatientServiceResponse;
import com.erx.microservice.patientmanagement.service.patientalldetails.SavePatientAllDetailsService;
import com.erx.microservice.patientmanagement.service.patientalldetails.SavePatientAllDetailsServiceRequest;
import com.erx.microservice.patientmanagement.service.patientalldetails.SavePatientAllDetailsServiceResponse;
import com.erx.microservice.patientmanagement.service.patientappointment.getlastappointmentbypatientanddoctor.GetLastAppointmentByPatientAndDoctorService;
import com.erx.microservice.patientmanagement.service.patientappointment.getlastappointmentbypatientanddoctor.GetLastAppointmentByPatientAndDoctorServiceRequest;
import com.erx.microservice.patientmanagement.service.patientappointment.getlastappointmentbypatientanddoctor.GetLastAppointmentByPatientAndDoctorServiceResponse;
import com.erx.microservice.patientmanagement.service.patientappointment.getpatientappointmentdetailbyidandvisittype.GetPatientAppointmentByIdAndVisitTypeService;
import com.erx.microservice.patientmanagement.service.patientappointment.getpatientappointmentdetailbyidandvisittype.GetPatientAppointmentByIdAndVisitTypeServiceRequest;
import com.erx.microservice.patientmanagement.service.patientappointment.getpatientappointmentdetailbyidandvisittype.GetPatientAppointmentByIdAndVisitTypeServiceResponse;
import com.erx.microservice.patientmanagement.service.patientappointment.getservicesbypatientappointment.GetServicesByPatientAppointmentRequest;
import com.erx.microservice.patientmanagement.service.patientappointment.getservicesbypatientappointment.GetServicesByPatientAppointmentResponse;
import com.erx.microservice.patientmanagement.service.patientappointment.getservicesbypatientappointment.GetServicesByPatientAppointmentService;
import com.erx.microservice.patientmanagement.service.patientvitals.GetPatientVitalsByPatientIdService;
import com.erx.microservice.patientmanagement.service.patientvitals.GetPatientVitalsByPatientIdServiceRequest;
import com.erx.microservice.patientmanagement.service.patientvitals.GetPatientVitalsByPatientIdServiceResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PatientController {

    private final Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    PatientSearchService patientSearchService;

    @Autowired
    private ImportCampCSVFileService importCampCSVFileService;

    @Autowired
    private ExportCampCSVFileService exportCampCSVFileService;

    @Autowired
    private GetServicesByPatientAppointmentService getServicesByPatientAppointmentService;

    @Autowired
    private GetPatientAppointmentByIdAndVisitTypeService getPatientAppointmentByIdAndVisitTypeService;

    @Autowired
    private GetAllAdmittedPatientByClinicLocationService getAllAdmittedPatientByClinicLocationService;

    @Autowired
    private GetPatientIPDetailsByPatientIdService getPatientIPDetailsByPatientIdService;

    @Autowired
    private GetLastAppointmentByPatientAndDoctorService getLastAppointmentByPatientAndDoctorService;

    @Autowired
    private SaveNonRegisteredPatientService saveNonRegisteredPatientService;

    @Autowired
    private GetPatientVitalsByPatientIdService getPatientVitalsByPatientIdService;

    @Autowired
    private ConvertNonRegisteredToRegisterPatientService convertNonRegisteredToRegisterPatientService;

    @Autowired
    private SavePatientAllDetailsService savePatientAllDetailsService;

    //To get all Patients based on user input
    @RequestMapping(value = "/searchPatient", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity searchPatient(@RequestParam long clinicId, @RequestParam String searchParam,
                                        @RequestParam String searchValue) {
        log.debug("REST request to get Patients");
        PatientSearchServiceResponse response = null;
        try {
            PatientSearchServiceRequest request = new PatientSearchServiceRequest(clinicId, searchParam, searchValue);
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

    //To import Patient Detail
    @RequestMapping(value = "/importPatientDetail", method = RequestMethod.POST)
    public ResponseEntity importPatientDetail(@RequestParam(required = true) Long clinicLocationId,
                                              @RequestParam(required = true) Long clinicId,
                                              @RequestParam(required = true) Long userId,
                                              @RequestBody(required = true) MultipartFile multipart) {
        log.debug("REST request to import Camp Patient Detail");
        ImportCampCSVFileServiceResponse response = new ImportCampCSVFileServiceResponse();
        try {
            ImportCampCSVFileServiceRequest request = new ImportCampCSVFileServiceRequest(clinicLocationId, clinicId, multipart, userId);
            response = importCampCSVFileService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to import Camp Patients Detail.");
            log.error("Failed to import Camp Patients Detail.");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To export Patient Detail
    @RequestMapping(value = "/exportCampPatientDetails", method = {RequestMethod.GET})
    public ResponseEntity exportCampPatientDetails(@RequestParam(required = true) Long clinicId, HttpServletResponse httpServletResponse) {
        log.debug("REST request to export Patient Detail");
        ExportCampCSVFileServiceResponse response = null;
        try {
            ExportCampCSVFileServiceRequest request = new ExportCampCSVFileServiceRequest(clinicId, httpServletResponse);
            response = exportCampCSVFileService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to export Patients Detail .");
            log.error("Failed to export Patients Detail .");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To retrieve service catalogue by patient appointment id
    @RequestMapping(value = "/getServiceCatalogueByPatientAppointmentId", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity serviceCatalogueByPatientAppointmentId(@RequestParam long patientAppointmentId) {
        log.debug("REST request to get service catalogue by patient appointment id");
        GetServicesByPatientAppointmentResponse response = null;
        try {
            GetServicesByPatientAppointmentRequest request = new GetServicesByPatientAppointmentRequest(patientAppointmentId);
            response = getServicesByPatientAppointmentService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve get service catalogue by patient appointment id .");
            log.error("Failed to retrieve get service catalogue by patient appointment id .");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To retrieve patient appointment detail for given patient appointment id and visit Type
    @RequestMapping(value = "/getPatientAppointmentByIdAndVisitType", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getPatientAppointmentByIdAndVisitType(@RequestParam long patientAppointmentId, @RequestParam String visitType) {
        log.debug("REST request to get  patient appointment detail for given id and visitType");
        GetPatientAppointmentByIdAndVisitTypeServiceResponse response = null;
        try {
            GetPatientAppointmentByIdAndVisitTypeServiceRequest request = new GetPatientAppointmentByIdAndVisitTypeServiceRequest(patientAppointmentId, visitType);
            response = getPatientAppointmentByIdAndVisitTypeService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve patient appointment detail for given id and visitType.");
            log.error("Failed to retrieve patient appointment detail for given id and visitType.");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To Get All Patient By ClinicLocation
    @RequestMapping(value = "/getAllAdmittedPatientByClinicLocation", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAllPatientByClinicLocation(@RequestParam long clinicLocationId) {
        log.debug("REST request to Get All Patient By ClinicLocation");
        GetAllAdmittedPatientByClinicLocationServiceResponse response = new GetAllAdmittedPatientByClinicLocationServiceResponse();
        try {
            GetAllAdmittedPatientByClinicLocationServiceRequest request = new
                    GetAllAdmittedPatientByClinicLocationServiceRequest(clinicLocationId);
            response = getAllAdmittedPatientByClinicLocationService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve All Patient By ClinicLocation");
            log.error("Failed to retrieve All Patient By ClinicLocation");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To Get Patient ip details By patientId
    @RequestMapping(value = "/getPatientIPDetailsByPatientId", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getPatientIPDetailsByPatientId(@RequestParam long patientId) {
        log.debug("REST request to Get Patient ip details By patientId");
        GetPatientIPDetailsByPatientIdServiceResponse response = new GetPatientIPDetailsByPatientIdServiceResponse();
        try {
            GetPatientIPDetailsByPatientIdServiceRequest request = new GetPatientIPDetailsByPatientIdServiceRequest(patientId);
            response = getPatientIPDetailsByPatientIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Patient ip details By patientId");
            log.error("Failed to retrieve Patient ip details By patientId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To Get Last Appointment By patientId and DoctorId
    @RequestMapping(value = "/getLastAppointmentByPatientAndDoctor", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getLastAppointmentByPatientAndDoctor(@RequestParam Long patientId, @RequestParam Long doctorId) {
        log.debug("REST request to Get Last Appointment By patientId and DoctorId");
        GetLastAppointmentByPatientAndDoctorServiceResponse response = new GetLastAppointmentByPatientAndDoctorServiceResponse();
        try {
            GetLastAppointmentByPatientAndDoctorServiceRequest request = new
                    GetLastAppointmentByPatientAndDoctorServiceRequest(patientId, doctorId);
            response = getLastAppointmentByPatientAndDoctorService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Last Appointment By patientId and DoctorId");
            log.error("Failed to retrieve Last Appointment By patientId and DoctorId " + e.getMessage());
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save or create non-registered patients
    @RequestMapping(value = "/saveNonRegisteredPatients", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveNonRegisteredPatients(@RequestBody NonRegisteredPatientDTO nonRegisteredPatientDTO) {
        log.debug("REST request to save non-registered patients");
        SaveNonRegisteredPatientServiceResponse response = null;
        try {
            SaveNonRegisteredPatientServiceRequest request = new SaveNonRegisteredPatientServiceRequest(nonRegisteredPatientDTO);
            response = saveNonRegisteredPatientService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save non-registered patient");
            log.error("Failed to save non-registered patient");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    // To get patient and patient vital details
    @RequestMapping(value = "/getPatientWithVitals", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getPatientWithVitals(@RequestParam long patientId, @RequestParam long clinicLocationId, @RequestParam long clinicId, @RequestParam long userId) {
        log.debug("REST request to Get Patient ip details By patientId");
        GetPatientVitalsByPatientIdServiceResponse response = new GetPatientVitalsByPatientIdServiceResponse();
        try {
            GetPatientVitalsByPatientIdServiceRequest request = new GetPatientVitalsByPatientIdServiceRequest(patientId, clinicLocationId, clinicId, userId);
            response = getPatientVitalsByPatientIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Patient vital details By patientId");
            log.error("Failed to retrieve Patient vital details By patientId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    /*
    created by Manisha on 09-10-2018
     */
    //To convert nonRegistered patient to Register patient service
    @RequestMapping(value = "/convertNonRegisteredToRegisterPatient", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity convertNonRegisteredPatientToRegistered(@RequestParam Long patientId, @RequestParam Long clinicId) {
        log.debug("REST request to convert non registered patient to register patient");
        ConvertNonRegisteredToRegisterPatientServiceResponse response = new ConvertNonRegisteredToRegisterPatientServiceResponse();
        try {
            ConvertNonRegisteredToRegisterPatientServiceRequest request = new ConvertNonRegisteredToRegisterPatientServiceRequest(patientId, clinicId);
            response = convertNonRegisteredToRegisterPatientService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to convert non registered patient to register patient " + e.getMessage());
            log.error("Failed to convert non registered patient to register patient " + e.getMessage());
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //created by Bahubali on 17-10-2018
    //api to save patient all details(ip details,patient details etc)
    @PostMapping("/savePatientAllDetails")
    public ResponseEntity savePatientAllDetails(@RequestBody PatientAllDetailsDTO patientAllDetailsDTO) {

        log.debug("Rest request to save patient all details");
        SavePatientAllDetailsServiceResponse response = new SavePatientAllDetailsServiceResponse();
        try {
            SavePatientAllDetailsServiceRequest request = new SavePatientAllDetailsServiceRequest(patientAllDetailsDTO);
            response = savePatientAllDetailsService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to save patient all details " + e.getMessage());
            log.error("Failed to save patient all details " + e.getMessage());
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header(response.getMessage()).body(response);
    }

}
