package com.erx.microservice.patientmanagement.service.ipadmission.allocatebed;

/*
 * created by Latha on 29-11-2017
 * */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterReopenDetail;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterReopenDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.IpAdmissionCaseMappingRepository;
import com.erx.microservice.patientmanagement.service.dto.*;
import com.erx.microservice.patientmanagement.service.dto.patientalldetailsdto.PatientAllDetailsDTO;
import com.erx.microservice.patientmanagement.service.mapper.IpAdmissionCaseMappingMapper;
import com.erx.microservice.patientmanagement.service.mapper.IpAdmissionMapper;
import com.erx.microservice.patientmanagement.service.patientalldetails.SavePatientAllDetailsService;
import com.erx.microservice.patientmanagement.service.patientalldetails.SavePatientAllDetailsServiceRequest;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbypatientid.PatientSearchQueryBuilderByPatientId;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbypatientid.PatientSearchQueryBuilderByPatientIdImpl;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service("allocateBedService")
public class AllocateBedServiceImpl implements AllocateBedService {

    private final Logger log = LoggerFactory.getLogger(AllocateBedServiceImpl.class);

    static final int MINUTES_PER_HOUR = 60;
    static final int SECONDS_PER_MINUTE = 60;
    static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    @Autowired
    IpAdmissionMapper ipAdmissionMapper;

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Autowired
    ServiceGateway serviceGateway;

    @Autowired
    IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    CmMasterRepository cmMasterRepository;

    @Autowired
    IpAdmissionCaseMappingRepository ipAdmissionCaseMappingRepository;

    @Autowired
    IpAdmissionCaseMappingMapper ipAdmissionCaseMappingMapper;

    @Autowired
    private ClinicLocationRepository clinicLocationRepository;

    @Autowired
    private IpAdmissionRequestRepository ipAdmissionRequestRepository;

    @Autowired
    private CmMasterReopenDetailRepository cmMasterReopenDetailRepository;
    @Autowired
    private PatientSearchQueryBuilderByPatientId patientSearchQueryBuilderByPatientId;
    @Autowired
    private SavePatientAllDetailsService savePatientAllDetailsService;

    public AllocateBedServiceImpl(IpAdmissionMapper ipAdmissionMapper, ClinicRepository clinicRepository,
                                  PatientRepository patientRepository, UserDetailRepository userDetailRepository,
                                  BedConfigurationMasterRepository bedConfigurationMasterRepository,
                                  ServiceGateway serviceGateway, IpAdmissionRepository ipAdmissionRepository,
                                  CmMasterRepository cmMasterRepository, IpAdmissionCaseMappingRepository ipAdmissionCaseMappingRepository,
                                  IpAdmissionCaseMappingMapper ipAdmissionCaseMappingMapper) {
        this.ipAdmissionMapper = ipAdmissionMapper;
        this.clinicRepository = clinicRepository;
        this.patientRepository = patientRepository;
        this.userDetailRepository = userDetailRepository;
        this.bedConfigurationMasterRepository = bedConfigurationMasterRepository;
        this.serviceGateway = serviceGateway;
        this.ipAdmissionRepository = ipAdmissionRepository;
        this.cmMasterRepository = cmMasterRepository;
        this.ipAdmissionCaseMappingRepository = ipAdmissionCaseMappingRepository;
        this.ipAdmissionCaseMappingMapper = ipAdmissionCaseMappingMapper;
    }

    @Transactional(rollbackFor = {Exception.class, ServiceException.class, RuntimeException.class})
    @Override
    public AllocateBedServiceResponse execute(AllocateBedServiceRequest request) throws ServiceException {

        IpAdmissionDTO ipAdmissionDTO = null;
        IpAdmissionDTO savedIpAdmissionDTO = null;
        IpAdmission ipAdmission = null;
        Clinic clinic = null;
        Patient patient = null;
        UserDetail userDetail = null;
        BedConfigurationMaster bedConfigurationMaster = null;
        BedConfigurationMaster savedBedConfigurationMaster = null;
        IpAdmission savedIpAdmission = null;
        CmMaster cmMaster = null;
        IpAdmissionCaseMapping ipAdmissionCaseMapping = null;
        IpAdmissionCaseMappingDTO ipAdmissionCaseMappingDTO = null;
        AllocateBedServiceResponse response = null;
        String IpAdmissionNumber = null;
        String DayCareAdmissionNumber = null;
        List<VisitTypeMasterDTO> visitTypeMasterDTOs = new ArrayList<VisitTypeMasterDTO>();
        String billNumber = null;
        Long billingInvoiceMasterId = null;
        String visitType = null;
        try {
            log.debug("To save IpAdmission");
            //retrieve the Object from request
            ipAdmissionDTO = request.getIpAdmissionDTO();
            //convert DTO to domain
            ipAdmission = ipAdmissionMapper.ipAdmissionDTOToIpAdmission(ipAdmissionDTO);
            //set Clinic in ipAdmission
            clinic = clinicRepository.findOne(ipAdmissionDTO.getClinicID());
            ipAdmission.setClinic(clinic);
            //set Patient in ipAdmission
            patient = patientRepository.findOne(ipAdmissionDTO.getPatientID());
            ipAdmission.setPatient(patient);
            //set UserDoctor in IpAdmission
            userDetail = userDetailRepository.findOne(ipAdmissionDTO.getUserDetailID());
            ipAdmission.setUserDetail(userDetail);
            //set bed in IpAdmission
            bedConfigurationMaster = bedConfigurationMasterRepository.findOne(ipAdmissionDTO.getBedMasterID());
            ipAdmission.setBedMaster(bedConfigurationMaster);
            GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
            GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
            if (bedConfigurationMaster.getClinicLocationId() != null) {
                visitTypeMasterDTOs = serviceGateway.getVisitTypeMaster(bedConfigurationMaster.getClinicLocationId());
            }
            if (ipAdmissionDTO.isDayCare()) {
                generateUniqueIDDTO.setCurrentTableName(ErxConstants.IP_CURRENT_TABLE_NAME);
                generateUniqueIDDTO.setCurrentColumnName(ErxConstants.DAY_CARE_CURRENT_COLUMN_NAME);
                generateUniqueIDClinicDTO.setId(ipAdmissionDTO.getClinicID());
                generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
                String generatedDayCareAdmissionNumber = serviceGateway.generateUniqueID(generateUniqueIDDTO);
                if (visitTypeMasterDTOs != null) {
                    for (VisitTypeMasterDTO typeMaster : visitTypeMasterDTOs) {
                        if (typeMaster.getVisitType().equalsIgnoreCase("Daycare")) {
                            ipAdmission.setVisitTypeMasterId(typeMaster.getId());
                            visitType = typeMaster.getVisitType();
                        }
                    }
                }
                ipAdmission.setDayCareAdmissionNumber(generatedDayCareAdmissionNumber);
                ipAdmission.setDayCare(ipAdmissionDTO.isDayCare());
            } else {
                //retrieve and set clinicId,CurrentTblName and setCurrentClmName to DTO
                generateUniqueIDDTO.setCurrentTableName(ErxConstants.IP_CURRENT_TABLE_NAME);
                generateUniqueIDDTO.setCurrentColumnName(ErxConstants.IP_CURRENT_COLUMN_NAME);
                generateUniqueIDClinicDTO.setId(ipAdmissionDTO.getClinicID());
                generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
                if (visitTypeMasterDTOs != null) {
                    for (VisitTypeMasterDTO typeMaster : visitTypeMasterDTOs) {
                        if (typeMaster.getVisitType().equalsIgnoreCase("IP")) {
                            ipAdmission.setVisitTypeMasterId(typeMaster.getId());
                            visitType = typeMaster.getVisitType();
                        }
                    }
                }
                if (ipAdmissionDTO.getId() == 0 || ipAdmissionDTO.getId() == null) {
                    //call generateUniqueID()
                    String generatedIpAdmissionNumber = serviceGateway.generateUniqueID(generateUniqueIDDTO);
                    //save the unique generated ipAdmissionNumber in IpAdmission
                    ipAdmission.setIpAdmissionNumber(generatedIpAdmissionNumber);
                }
            }
            //set ip admissionStatus and admitted on
            ipAdmission.setIpAdmissionStatus("Admitted");
            //  ipAdmission.setAdmissionOn(LocalDateTime.now());
            //save IpAdmission
            savedIpAdmission = ipAdmissionRepository.save(ipAdmission);
            // after allocating bed to the patient generate the bill number by calling save bill number api
            BillNumberRequestDTO billNumberRequestDTO = new BillNumberRequestDTO();
            billNumberRequestDTO.setClinicId(request.getIpAdmissionDTO().getClinicID());
            billNumberRequestDTO.setClinicLocationId(request.getIpAdmissionDTO().getClinicLocationId());
            billNumberRequestDTO.setUserId(request.getIpAdmissionDTO().getUserId());
            billNumberRequestDTO.setPatientId(request.getIpAdmissionDTO().getPatientID());
            billNumberRequestDTO.setVisitType(visitType);
            billNumberRequestDTO.setIpAdmissionId(savedIpAdmission.getId());
            //get invoice details
            JSONObject jsonObject = serviceGateway.saveBillingInvoiceMaster(billNumberRequestDTO);
            //extracting responseBody
            if (jsonObject != null) {
                //get the fields from jsonObject
                jsonObject = (JSONObject) jsonObject.get("billNumberResponseDTO");
                billNumber = jsonObject.getString("billNumber");
                billingInvoiceMasterId = jsonObject.getLong("invoiceMasterId");
            } else {
                log.error("Error-->AllocateBedServiceImpl-->Failed to generate bill number");
                throw new Exception("Failed to generate bill number");
            }
            //allocate status is changing once the bed is allocated(save in bed master)
            bedConfigurationMaster.setAllocatedStatus("occupied");
            savedBedConfigurationMaster = bedConfigurationMasterRepository.save(bedConfigurationMaster);
            //convert the saved object into DTO
            savedIpAdmissionDTO = ipAdmissionMapper.ipAdmissionToIpAdmissionDTO(savedIpAdmission);
            if (ipAdmissionDTO.isHasCase()) {
                savedIpAdmission.setIpAdmissionStatus("OP case assigned to IP");
                if (ipAdmissionDTO.getCaseMasterID() != null) {
                    cmMaster = cmMasterRepository.findOne(ipAdmissionDTO.getCaseMasterID());
                    // save ipAdmissionCaseMapping
                    ipAdmissionCaseMapping = saveIpAdmissionCaseMapping(savedIpAdmission, cmMaster);
                    //convert the saved object into DTO
                    ipAdmissionCaseMappingDTO = ipAdmissionCaseMappingMapper
                            .ipAdmissionCaseMappingToIpAdmissionCaseMappingDTO(ipAdmissionCaseMapping);
                }
            }

            //Update IpAdmissionRequest
            if (ipAdmissionDTO.getIPAdmissionRequestId() != null) {
                IpAdmissionRequest ipAdmissionRequest = ipAdmissionRequestRepository.findOne(ipAdmissionDTO.getIPAdmissionRequestId());
                if (ipAdmissionRequest != null) {
                    ipAdmissionRequest.setIpAdmission(savedIpAdmission);
                    ipAdmissionRequestRepository.save(ipAdmissionRequest);
                }
            }

            //Reopen case in case of readmit
            if (ipAdmissionDTO.getReAdmitIpAdmissionId() != null) {
                IpAdmissionCaseMapping foundIpAdmissionCaseMapping = ipAdmissionCaseMappingRepository.
                        getIpCaseDetails(ipAdmissionDTO.getReAdmitIpAdmissionId());
                if (foundIpAdmissionCaseMapping != null) {

                    CmMaster existingCase = foundIpAdmissionCaseMapping.getCmMaster();

                    //Update CmMasterReOpenDetail
                    CmMasterReopenDetail cmMasterReOpenDetail = new CmMasterReopenDetail();
                    cmMasterReOpenDetail.setCmMaster(existingCase);
                    cmMasterReOpenDetail.setCaseCompletedDate(existingCase.getCaseCompletedDate());
                    cmMasterReOpenDetail.setStatus("Reopened");
                    cmMasterReopenDetailRepository.save(cmMasterReOpenDetail);

                    //Update case
                    existingCase.setCaseStatus("Reopened");
                    existingCase.setCaseCompletedDate(null);
                    cmMasterRepository.save(existingCase);
                }
            }
            //method to save ip details in patient_all_details table
            saveIPAdmissionDetailsInPatientAllDetails(savedIpAdmission);

             /*
            // committing the scheduler
            //set the Scheduler
            ServiceCatalogueRecurringRulesDTO serviceCatalogueRecurringRulesDTO = null;
            Object recurringObject = null;
            JSONObject recurringRuleObject = serviceGateway.getServiceCatalogueRecurringRule
                    (bedConfigurationMaster.getClinicLocationId());
            if (recurringRuleObject != null) {
                recurringObject = recurringRuleObject.get("serviceCatalogueRecurringRulesDTO");
                if (recurringObject != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    serviceCatalogueRecurringRulesDTO = objectMapper.readValue(recurringObject.toString(),
                            ServiceCatalogueRecurringRulesDTO.class);

                    //start the scheduler
                    if (serviceCatalogueRecurringRulesDTO != null) {
                        //check if slot period and cutOff period both are null or not
                        if (serviceCatalogueRecurringRulesDTO.getSlotPeriod() == null && serviceCatalogueRecurringRulesDTO.getCutOffPeriod() == null) {
                            log.error("Error-->serviceCatalogueRecurringRules-->slotPeriod && cutOffPeriod is null");
                            throw new Exception("serviceCatalogueRecurringRules-->slotPeriod && cutOffPeriod is null");
                        }
                        if (serviceCatalogueRecurringRulesDTO.getSlotPeriod() != null) {
                            LocalTime slotPeriod = LocalTime.parse(serviceCatalogueRecurringRulesDTO.getSlotPeriod());
                            //set the scheduler
                            SourceFrequencyWeb sourceFrequencyWeb = new SourceFrequencyWeb();
                            sourceFrequencyWeb.setClinicLocationId(String.valueOf(serviceCatalogueRecurringRulesDTO.getClinicLocationId()));
                            LocalTime timeOfTheDay = LocalTime.of(0, 0);
                            if (ipAdmission.getAdmissionOn().equals(LocalDateTime.now()))
                                timeOfTheDay = ipAdmission.getAdmissionOn().toLocalTime();
                            while (timeOfTheDay.isBefore(LocalTime.now())) {
                                timeOfTheDay = timeOfTheDay.plusHours(slotPeriod.getHour()).plusMinutes(slotPeriod.getMinute());
                            }
                            sourceFrequencyWeb.setTimeOfTheDay(String.valueOf(timeOfTheDay));
                            sourceFrequencyWeb.setPatientId(String.valueOf(patient.getId()));
                            List<Integer> days = new ArrayList<>();
                            days.add(0);
                            days.add(1);
                            days.add(2);
                            days.add(3);
                            days.add(4);
                            days.add(5);
                            days.add(6);
                            sourceFrequencyWeb.setDay(days);
                            sourceFrequencyWeb.setCutOffOrSlot(PatientConstants.SLOT);
                            serviceGateway.setScheduler(sourceFrequencyWeb);
                            //calculate times and days and create order correspondingly for slot
                            GenerateOrderByAdmissionTimeDTO generateOrderByAdmissionTimeDTO = new GenerateOrderByAdmissionTimeDTO();
                            generateOrderByAdmissionTimeDTO.setAdmissionOn(String.valueOf(savedIpAdmission.getAdmissionOn()));
                            generateOrderByAdmissionTimeDTO.setClinicLocationId(bedConfigurationMaster.getClinicLocationId());
                            generateOrderByAdmissionTimeDTO.setIpAdmissionId(savedIpAdmission.getId());
                            generateOrderByAdmissionTimeDTO.setPatientId(savedIpAdmission.getPatient().getId());
                            generateOrderByAdmissionTimeDTO.setVisitTypeMasterId(savedIpAdmission.getVisitTypeMasterId());
                            generateOrderByAdmissionTimeDTO.setUserId(ipAdmissionDTO.getUserDetailID());

                            //call the service
                            JSONObject slotOrderCreationResponse = serviceGateway.generateSlotOrderByAdmissionTime(generateOrderByAdmissionTimeDTO);
                            if (slotOrderCreationResponse == null) {
                                log.error("Error-->AllocateBedServiceImpl-->Error while creating  slot order by admission time");
                                throw new Exception("Error while creating  slot order by admission time");
                            }
                        }//calculate times and days and create order correspondingly for cutOff
                        else if (serviceCatalogueRecurringRulesDTO.getCutOffPeriod() != null) {
                            GenerateOrderByAdmissionTimeDTO generateOrderByAdmissionTimeDTO = new GenerateOrderByAdmissionTimeDTO();
                            generateOrderByAdmissionTimeDTO.setAdmissionOn(String.valueOf(savedIpAdmission.getAdmissionOn()));
                            generateOrderByAdmissionTimeDTO.setClinicLocationId(bedConfigurationMaster.getClinicLocationId());
                            generateOrderByAdmissionTimeDTO.setIpAdmissionId(savedIpAdmission.getId());
                            generateOrderByAdmissionTimeDTO.setPatientId(savedIpAdmission.getPatient().getId());
                            generateOrderByAdmissionTimeDTO.setVisitTypeMasterId(savedIpAdmission.getVisitTypeMasterId());
                            generateOrderByAdmissionTimeDTO.setUserId(ipAdmissionDTO.getUserDetailID());
                            generateOrderByAdmissionTimeDTO.setBedTypeId(bedConfigurationMaster.getBedTypeMaster().getId());


                            //call the service
                            JSONObject cutOffOrderCreationResponse = serviceGateway.generateCutOffOrderByAdmissionTime(generateOrderByAdmissionTimeDTO);
                            if (cutOffOrderCreationResponse == null) {
                                log.error("Error-->AllocateBedServiceImpl-->Error while creating  cutOff order by admission time");
                                throw new Exception("Error while creating  cutOff order by admission time");
                            }
                        }
                    } else {
                        log.error("Error-->AllocateBedServiceImpl-->serviceCatalogueRecurringRulesDTO not found");
                        throw new Exception("serviceCatalogueRecurringRulesDTO not found");
                    }
                } else {
                    log.error("Error-->AllocateBedServiceImpl-->ServiceCatalogueRecurringRule-->recurringObject not found");
                    throw new Exception("recurringObject not found");
                }
            } else { //FROM table ip_admission_service_catalogue_recurring_rule table
                log.error("Error-->AllocateBedServiceImpl-->ip_admission_service_catalogue_recurring_rule table data not found");
                throw new Exception("IP Admission ServiceCatalogueRecurringRule data not found");
            }

           */
            //create response object
            IpAdmissionNumber = savedIpAdmission.getIpAdmissionNumber();
            DayCareAdmissionNumber = savedIpAdmission.getDayCareAdmissionNumber();
            //create response
            response = new AllocateBedServiceResponse(IpAdmissionNumber, DayCareAdmissionNumber);
            response.SUCCESSFUL = true;
            if (IpAdmissionNumber != null) {
                response.setMessage("Allocated bed for IpAdmission Successfully with IpAdmissionNumber : " + IpAdmissionNumber);
                log.debug("Allocated bed for IpAdmission Successfully with IpAdmissionNumber : " + IpAdmissionNumber);
            } else {
                response.setMessage("Allocated bed for IpAdmission Successfully with DayCareAdmissionNumber : " + DayCareAdmissionNumber);
                log.debug("Allocated bed for IpAdmission Successfully with DayCareAdmissionNumber : " + DayCareAdmissionNumber);
            }
        } catch (Exception e) {
            response = new AllocateBedServiceResponse();
            response.SUCCESSFUL = false;
            //response.setMessage("Failed to allocate Bed for IpAdmission");
            log.error(e.getMessage() + " so,Failed to allocate Bed for IpAdmission");
            response.setMessage(e.getMessage() + " so,Failed to allocate Bed for IpAdmission");
        }
        return response;
    }

    //method to save ip admission details in patient_all_details_table
    private void saveIPAdmissionDetailsInPatientAllDetails(IpAdmission savedIpAdmission) {
        try {
            PatientAllDetailsDTO patientAllDetailsDTO = new PatientAllDetailsDTO();
            patientAllDetailsDTO.setContext(ErxConstants.PATIENT_IP_SAVE);
            patientAllDetailsDTO.setPatientId(savedIpAdmission.getPatient().getId());
            patientAllDetailsDTO.setIpAdmissionId(savedIpAdmission.getId());
            patientAllDetailsDTO.setIpAdmissionNumber(savedIpAdmission.getIpAdmissionNumber());
            VisitTypeMaster visitTypeMaster = patientSearchQueryBuilderByPatientId.getVisitTypeMaster
                    (savedIpAdmission.getVisitTypeMasterId());
            patientAllDetailsDTO.setPatientVisitTypeName(visitTypeMaster.getVisitType());
            patientAllDetailsDTO.setPatientVisitTypeMasterId(visitTypeMaster.getId());
            patientAllDetailsDTO.setRoomNumber(savedIpAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
            patientAllDetailsDTO.setBedNumber(savedIpAdmission.getBedMaster().getBedNumber());
            patientAllDetailsDTO.setWardName(savedIpAdmission.getBedMaster().getWardMaster().getWardName());
            patientAllDetailsDTO.setBedTypeId(savedIpAdmission.getBedMaster().getBedTypeMaster().getId());
            patientAllDetailsDTO.setBedTypeName(savedIpAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
            patientAllDetailsDTO.setBedNumber(savedIpAdmission.getBedMaster().getBedNumber());
            patientAllDetailsDTO.setDayCarePatient(savedIpAdmission.isDayCare());
            patientAllDetailsDTO.setIpAdmissionOn(savedIpAdmission.getAdmissionOn());
            SavePatientAllDetailsServiceRequest savePatientAllDetailsServiceRequest = new SavePatientAllDetailsServiceRequest(patientAllDetailsDTO);
            savePatientAllDetailsService.execute(savePatientAllDetailsServiceRequest);
        } catch (Exception e) {
            log.error("Error==>failed to save patient ip details in patient_all_details table");
        }

    }

    // method to save details in ipAdmissionCaseMapping
    public IpAdmissionCaseMapping saveIpAdmissionCaseMapping(IpAdmission savedIpAdmission, CmMaster cmMaster) {
        IpAdmissionCaseMapping ipAdmissionCaseMapping = new IpAdmissionCaseMapping();
        IpAdmissionCaseMapping ipAdmissionCaseMappingDetails = new IpAdmissionCaseMapping();
        log.debug("saveIpAdmissionCaseMapping");
        try {

            ipAdmissionCaseMappingDetails = ipAdmissionCaseMappingRepository.getIpCaseDetails(savedIpAdmission.getId());

            if (ipAdmissionCaseMappingDetails != null) {
                if (savedIpAdmission.getId() == ipAdmissionCaseMappingDetails.getIpAdmission().getId()) {
                    ipAdmissionCaseMappingDetails.getCmMaster().setIpCase(false);
                    ipAdmissionCaseMappingDetails.setCmMaster(cmMaster);
                    ipAdmissionCaseMappingRepository.save(ipAdmissionCaseMappingDetails);
                    ipAdmissionCaseMappingDetails.getCmMaster().setIpCase(true);
                    cmMasterRepository.save(ipAdmissionCaseMappingDetails.getCmMaster());
                }
            } else {
                ipAdmissionCaseMapping.setCmMaster(cmMaster);
                ipAdmissionCaseMapping.setIpAdmission(savedIpAdmission);
                ipAdmissionCaseMappingRepository.save(ipAdmissionCaseMapping);
                cmMaster.setIpCase(true);
                cmMasterRepository.save(cmMaster);
            }
        } catch (Exception e) {
            log.error("ipAdmissionCaseMappingSaveError ---> " + e);
        }

        return ipAdmissionCaseMapping;
    }
}
