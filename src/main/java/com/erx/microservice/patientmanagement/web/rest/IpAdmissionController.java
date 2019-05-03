package com.erx.microservice.patientmanagement.web.rest;

/*
* created by latha on 20-11-2017
* */


import com.erx.microservice.patientmanagement.service.dto.BedMovementDTO;
import com.erx.microservice.patientmanagement.service.dto.BedTransferDTO;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionDTO;
import com.erx.microservice.patientmanagement.service.ipadmission.allocatebed.AllocateBedService;
import com.erx.microservice.patientmanagement.service.ipadmission.allocatebed.AllocateBedServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.allocatebed.AllocateBedServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.BedDetailsByWardFactory;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.beddetailsbyward.GetBedDetailsByWardIdServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.bedmovement.BedMovementService;
import com.erx.microservice.patientmanagement.service.ipadmission.bedmovement.BedMovementServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.bedmovement.BedMovementServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.bedmovementdepartment.BedMovementDepartmentService;
import com.erx.microservice.patientmanagement.service.ipadmission.bedmovementdepartment.BedMovementDepartmentServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.bedmovementdepartment.BedMovementDepartmentServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.bedtransfer.BedTransferService;
import com.erx.microservice.patientmanagement.service.ipadmission.bedtransfer.BedTransferServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.bedtransfer.BedTransferServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.dischargeipadmission.DischargeIpAdmissionService;
import com.erx.microservice.patientmanagement.service.ipadmission.dischargeipadmission.DischargeIpAdmissionServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.dischargeipadmission.DischargeIpAdmissionServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.getallocatedbedetailsbyipadmissionid.GetAllocatedBedDetailsByIpAdmissionIdService;
import com.erx.microservice.patientmanagement.service.ipadmission.getallocatedbedetailsbyipadmissionid.GetAllocatedBedDetailsByIpAdmissionIdServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.getallocatedbedetailsbyipadmissionid.GetAllocatedBedDetailsByIpAdmissionIdServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.getbedmovementdetailsbyippatient.GetBedMovementDetailsByIpService;
import com.erx.microservice.patientmanagement.service.ipadmission.getbedmovementdetailsbyippatient.GetBedMovementDetailsByIpServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.getbedmovementdetailsbyippatient.GetBedMovementDetailsByIpServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.getbedtransferdetailsbyippatient.GetBedTransferDetailsByIpService;
import com.erx.microservice.patientmanagement.service.ipadmission.getbedtransferdetailsbyippatient.GetBedTransferDetailsByIpServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.getbedtransferdetailsbyippatient.GetBedTransferDetailsByIpServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.getbedttpeidforschedulerbyipadmissionid.GetBedTypeIdForSchedulerByIpAdmissionIdService;
import com.erx.microservice.patientmanagement.service.ipadmission.getbedttpeidforschedulerbyipadmissionid.GetBedTypeIdForSchedulerByIpAdmissionIdServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.getbedttpeidforschedulerbyipadmissionid.GetBedTypeIdForSchedulerByIpAdmissionIdServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.getcasedetailsbypatient.GetCaseDetailsByPatientService;
import com.erx.microservice.patientmanagement.service.ipadmission.getcasedetailsbypatient.GetCaseDetailsByPatientServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.getcasedetailsbypatient.GetCaseDetailsByPatientServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.getdoctordepartment.GetDoctorDepartmentService;
import com.erx.microservice.patientmanagement.service.ipadmission.getdoctordepartment.GetDoctorDepartmentServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.getdoctordepartment.GetDoctorDepartmentServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.getipadmissionnumberbycaseid.GetIPAdmissionNumberByCaseIdService;
import com.erx.microservice.patientmanagement.service.ipadmission.getipadmissionnumberbycaseid.GetIPAdmissionNumberByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.getipadmissionnumberbycaseid.GetIPAdmissionNumberByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearch.IpPatientSearchService;
import com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearch.IpPatientSearchServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearch.IpPatientSearchServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearchbydate.IpPatientSearchByDateService;
import com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearchbydate.IpPatientSearchByDateServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearchbydate.IpPatientSearchByDateServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.movetoactualbed.MoveToActualBedService;
import com.erx.microservice.patientmanagement.service.ipadmission.movetoactualbed.MoveToActualBedServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.movetoactualbed.MoveToActualBedServiceResponse;
import com.erx.microservice.patientmanagement.service.ipadmission.updatebedallocationstatus.UpdateBedAllocationStatusService;
import com.erx.microservice.patientmanagement.service.ipadmission.updatebedallocationstatus.UpdateBedAllocationStatusServiceRequest;
import com.erx.microservice.patientmanagement.service.ipadmission.updatebedallocationstatus.UpdateBedAllocationStatusServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class IpAdmissionController {

    private final Logger log = LoggerFactory.getLogger(IpAdmissionController.class);

    @Autowired
    private BedDetailsByWardFactory bedDetailsByWardFactory;

    @Autowired
    private AllocateBedService allocateBedService;

    @Autowired
    private GetDoctorDepartmentService getDoctorDepartmentService;

    @Autowired
    private IpPatientSearchService ipPatientSearchService;

    @Autowired
    private GetCaseDetailsByPatientService getCaseDetailsByPatientService;

    @Autowired
    private GetBedTransferDetailsByIpService getBedTransferDetailsByIpService;

    @Autowired
    private GetBedMovementDetailsByIpService getBedMovementDetailsByIpService;

    @Autowired
    private BedTransferService bedTransferService;

    @Autowired
    private BedMovementService bedMovementService;

    @Autowired
    private BedMovementDepartmentService bedMovementDepartmentService;

    @Autowired
    private MoveToActualBedService moveToActualBedService;

    @Autowired
    private IpPatientSearchByDateService ipPatientSearchByDateService;

    @Autowired
    private DischargeIpAdmissionService dischargeIpAdmissionService;

    @Autowired
    UpdateBedAllocationStatusService updateBedAllocationStatusService;

    @Autowired
    private GetBedTypeIdForSchedulerByIpAdmissionIdService getBedTypeIdForSchedulerByIpAdmissionIdService;

    @Autowired
    private GetAllocatedBedDetailsByIpAdmissionIdService getAllocatedBedDetailsByIpAdmissionIdService;

    @Autowired
    private GetIPAdmissionNumberByCaseIdService getIPAdmissionNumberByCaseIdService;

    //To get all Patients based on user input
    @RequestMapping(value = "/searchIpPatient", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity searchIpPatient(@RequestParam long clinicId, @RequestParam String searchParam,
                                          @RequestParam String searchValue, Pageable pageable) {
        log.debug("REST request to get Patients");
        IpPatientSearchServiceResponse response = null;
        try {
            IpPatientSearchServiceRequest request = new IpPatientSearchServiceRequest(clinicId, searchParam, searchValue, pageable);
            response = ipPatientSearchService.execute(request);
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

    //To get all Patients based on date range
    @RequestMapping(value = "/searchPatientByDateRange", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity searchPatientByDateRange(@RequestParam long clinicId, @RequestParam String startDate,
                                                   @RequestParam String endDate) {
        log.debug("REST request to get Patients");
        IpPatientSearchByDateServiceResponse response = null;
        try {
            IpPatientSearchByDateServiceRequest request = new IpPatientSearchByDateServiceRequest(clinicId, startDate, endDate);
            response = ipPatientSearchByDateService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Patients by date");
            log.error("Failed to retrieve Patients by date");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get room and bed details based on ward (for checking availability)
    @RequestMapping(value = "/getBedRoomDetail", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getBedDetails(@RequestParam(value = "wardId", required = false) Long wardId,
                                        @RequestParam(value = "isDaycare", required = true) boolean isDaycare,
                                        @RequestParam(value = "type", required = true) String type,
                                        @RequestParam(value = "doctorId", required = false) Long doctorId,
                                        @RequestParam(value = "clinicLocationId", required = false) Long clinicLocationId) {
        log.debug("REST request to get Bed details");
        GetBedDetailsByWardIdServiceResponse response = null;
        try {
            GetBedDetailsByWardIdServiceRequest request = new GetBedDetailsByWardIdServiceRequest
                    (wardId, isDaycare, type, doctorId, clinicLocationId);
            response = bedDetailsByWardFactory.getBedDetailsByWard(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Bed details");
            log.error("Failed to retrieve Bed details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To save the IpAdmission
    @PutMapping("/allocateBed")
    public ResponseEntity allocateBed(@RequestBody IpAdmissionDTO ipAdmissionDTO) {
        log.debug("REST request to save IpAdmission ");
        AllocateBedServiceResponse response = null;
        try {
            AllocateBedServiceRequest request = new AllocateBedServiceRequest(ipAdmissionDTO);
            response = allocateBedService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("IpAdmission is not saved successfully");
            log.error("IpAdmission is not saved successfully");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get department wise doctors
    @RequestMapping(value = "/getDoctorDepartment", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getDoctorDepartment(@RequestParam long clinicLocationID) {
        log.debug("REST request to get doctor and department details");
        GetDoctorDepartmentServiceResponse response = null;
        try {
            GetDoctorDepartmentServiceRequest request = new GetDoctorDepartmentServiceRequest(clinicLocationID);
            response = getDoctorDepartmentService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve doctor and department details");
            log.error("Failed to retrieve doctor and department details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To update the IpAdmission for transfer
    @PostMapping("/bedTransfer")
    public ResponseEntity bedTransfer(@RequestBody BedTransferDTO bedTransferDTO) {
        log.debug("REST request to update IpAdmission ");
        BedTransferServiceResponse response = null;
        try {
            BedTransferServiceRequest request = new BedTransferServiceRequest(bedTransferDTO);
            response = bedTransferService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to transfer Bed");
            log.error("Failed to transfer Bed");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To update the IpAdmission for movement
    @PostMapping("/bedMovement")
    public ResponseEntity bedMovement(@RequestBody BedMovementDTO bedMovementDTO) {
        log.debug("REST request to update IpAdmission ");
        BedMovementServiceResponse response = null;
        try {
            BedMovementServiceRequest request = new BedMovementServiceRequest(bedMovementDTO);
            response = bedMovementService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to move bed");
            log.error("Failed to move bed");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To move to department in bed movement
    @PostMapping("/bedMovementDepartment")
    public ResponseEntity bedMovementDepartment(@RequestBody BedMovementDTO bedMovementDTO) {
        log.debug("REST request to update IpAdmission ");
        BedMovementDepartmentServiceResponse response = null;
        try {
            BedMovementDepartmentServiceRequest request = new BedMovementDepartmentServiceRequest(bedMovementDTO);
            response = bedMovementDepartmentService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to move bed to department");
            log.error("Failed to move bed to department");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To move to actual bed from bed movement
    @PostMapping("/moveToActualBed")
    public ResponseEntity moveToActualBed(@RequestBody BedMovementDTO bedMovementDTO) {
        log.debug("REST request to update IpAdmission ");
        MoveToActualBedServiceResponse response = null;
        try {
            MoveToActualBedServiceRequest request = new MoveToActualBedServiceRequest(bedMovementDTO);
            response = moveToActualBedService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to move to actual bed");
            log.error("Failed to move to actual bed");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get cases by patient
    @RequestMapping(value = "/getCaseDetailByPatient", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getCaseDetailByPatient(@RequestParam Long patientId, @RequestParam Long clinicLocationId) {
        log.debug("REST request to get case details");
        GetCaseDetailsByPatientServiceResponse response = null;

        try {
            GetCaseDetailsByPatientServiceRequest request = new GetCaseDetailsByPatientServiceRequest(patientId, clinicLocationId);
            response = getCaseDetailsByPatientService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Case details");
            log.error("Failed to retrieve Case details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To get bed transfer details by patient
    @RequestMapping(value = "/getBedTransferByPatient", method = RequestMethod.GET)
    public ResponseEntity getBedTransferByIpPatient(@RequestParam Long ipAdmissionID) {
        log.debug("REST request to get bed transfer details");
        GetBedTransferDetailsByIpServiceResponse response = null;
        try {
            GetBedTransferDetailsByIpServiceRequest request = new GetBedTransferDetailsByIpServiceRequest(ipAdmissionID);
            response = getBedTransferDetailsByIpService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve all bed transfer details");
            log.error("Failed to retrieve all bed transfer details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get bed movement details by patient
    @RequestMapping(value = "/getBedMovementByPatient", method = RequestMethod.GET)
    public ResponseEntity getBedMovementByIpPatient(@RequestParam Long ipAdmissionID) {
        log.debug("REST request to get bed movement details");
        GetBedMovementDetailsByIpServiceResponse response = null;
        try {
            GetBedMovementDetailsByIpServiceRequest request = new GetBedMovementDetailsByIpServiceRequest(ipAdmissionID);
            response = getBedMovementDetailsByIpService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve all bed moved details");
            log.error("Failed to retrieve all bed moved details");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    //To discharge ip patient
    @RequestMapping(value = "/dischargeIpAdmission", method = RequestMethod.GET)
    public ResponseEntity dischargeIpAdmission(@RequestParam Long ipAdmissionId) {
        log.debug("REST request to discharge ip patient");
        DischargeIpAdmissionServiceResponse response = new DischargeIpAdmissionServiceResponse();
        try {
            DischargeIpAdmissionServiceRequest request = new DischargeIpAdmissionServiceRequest(ipAdmissionId);
            response = dischargeIpAdmissionService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to discharge ip patient " + e.getMessage());
            log.error("Failed to discharge ip patient " + e.getMessage());
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }

    @PutMapping("/updateBedAllocationStatus")
    public ResponseEntity updateBedAllocationStatus(@RequestBody UpdateBedAllocationStatusServiceRequest request){
        log.debug("REST request to update the bed status");
        UpdateBedAllocationStatusServiceResponse response =new UpdateBedAllocationStatusServiceResponse();
        try{

            response =updateBedAllocationStatusService.execute(request);
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        }
        catch(Exception e){
            response.SUCCESSFUL=false;
            response.setMessage("Failed to update the bed status ==>>" + e.getMessage());
            log.error("Failed to update the bed status ==>> " + e.getMessage());
        }
        if(response.SUCCESSFUL){
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
        }

    }


    //To get bed Type Id For Scheduler for given IpAdmissionId
    @RequestMapping(value = "/getBedTypeIdForSchedulerByIpAdmissionId", method = RequestMethod.GET)
    public ResponseEntity getBedTypeIdForSchedulerByIpAdmissionId(@RequestParam Long ipAdmissionId, @RequestParam String type,
                                                                  @RequestParam String cutOffTime) {
        log.debug("REST request to get bed Type Id For Scheduler for given IpAdmissionId");
        GetBedTypeIdForSchedulerByIpAdmissionIdServiceResponse response = null;
        try {
            GetBedTypeIdForSchedulerByIpAdmissionIdServiceRequest request = new GetBedTypeIdForSchedulerByIpAdmissionIdServiceRequest(ipAdmissionId,type,cutOffTime);
            response = getBedTypeIdForSchedulerByIpAdmissionIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve bed Type Id For Scheduler for given IpAdmissionId");
            log.error("Failed to retrieve bed Type Id For Scheduler for given IpAdmissionId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }



    //To get bed Type Id For Scheduler for given IpAdmissionId
    @RequestMapping(value = "/getAllocatedBedDetailsByIpAdmissionId", method = RequestMethod.GET)
    public ResponseEntity getAllocatedBedDetailsByIpAdmissionId(@RequestParam Long ipAdmissionID) {
        log.debug("REST request to get bed Type Id For Scheduler for given IpAdmissionId");
        GetAllocatedBedDetailsByIpAdmissionIdServiceResponse response = null;
        try {
            GetAllocatedBedDetailsByIpAdmissionIdServiceRequest request = new GetAllocatedBedDetailsByIpAdmissionIdServiceRequest(ipAdmissionID);
            response = getAllocatedBedDetailsByIpAdmissionIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve AllocatedBedDetails for given IpAdmissionId");
            log.error("Failed to retrieve AllocatedBedDetails for given IpAdmissionId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }


    //To get IPAdmissionNumber for given CaseId
    @RequestMapping(value = "/getIPAdmissionNumberByCaseId", method = RequestMethod.GET)
    public ResponseEntity getIPAdmissionNumberByCaseId(@RequestParam Long caseId) {
        log.debug("REST request to get IPAdmissionNumber for given CaseId");
        GetIPAdmissionNumberByCaseIdServiceResponse response = null;
        try {
            GetIPAdmissionNumberByCaseIdServiceRequest request = new GetIPAdmissionNumberByCaseIdServiceRequest(caseId);
            response = getIPAdmissionNumberByCaseIdService.execute(request);
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve IPAdmissionNumber for given CaseId");
            log.error("Failed to retrieve IPAdmissionNumber for given CaseId");
        }
        if (response.SUCCESSFUL)
            return ResponseEntity.ok().header(response.getMessage()).body(response);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .header(response.getMessage()).body(response);
    }
}
