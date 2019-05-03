package com.erx.microservice.patientmanagement.service.casemanagement.savecasedetailwithoutcasesheet;
/*
 * created by Raushan on 28-08-2018
 * */

import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.casemanagement.*;
import com.erx.microservice.patientmanagement.domain.therapymanagement.*;
import com.erx.microservice.patientmanagement.repository.ClinicLocationRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.*;
import com.erx.microservice.patientmanagement.repository.therapymanagement.*;
import com.erx.microservice.patientmanagement.service.casemanagement.savecomplaint.SaveComplaintService;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderServiceDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.IdQuantityDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveCaseDetailWithoutCaseSheetDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyMedicineDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyMedicineTypeDTO;
import com.erx.microservice.patientmanagement.service.util.CaseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaveCaseDetailWithoutCaseSheetServiceImpl implements SaveCaseDetailWithoutCaseSheetService {
    private static Logger log = LoggerFactory.getLogger(SaveCaseDetailWithoutCaseSheetServiceImpl.class);

    @Autowired
    private SaveComplaintService saveComplaintService;
    @Autowired
    private ClinicLocationDetailRepository clinicLocationDetailRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ClinicLocationRepository clinicLocationRepository;
    @Autowired
    private CmMasterRepository cmMasterRepository;
    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;
    @Autowired
    private TherapyPlanningRepository therapyPlanningRepository;
    @Autowired
    private TherapyInstanceNextActionRepository therapyInstanceNextActionRepository;
    @Autowired
    private TherapyInstanceRepository therapyInstanceRepository;
    @Autowired
    private ClinicPreferenceRepository clinicPreferenceRepository;
    @Autowired
    private TherapyInstanceStateRepository therapyInstanceStateRepository;
    @Autowired
    private TherapyPlanningMedicineRepository therapyPlanningMedicineRepository;
    @Autowired
    private TherapyPlanningMedicineTypeRepository therapyPlanningMedicineTypeRepository;
    @Autowired
    private TherapyMasterMedicineTypeRepository therapyMasterMedicineTypeRepository;
    @Autowired
    private TherapyMasterMedicineRepository therapyMasterMedicineRepository;
    @Autowired
    private TherapyMasterRepository therapyMasterRepository;


    @Override
    public SaveCaseDetailWithoutCaseSheetServiceResponse execute(SaveCaseDetailWithoutCaseSheetServiceRequest request) {
        SaveCaseDetailWithoutCaseSheetServiceResponse response = null;
        log.debug("Call to Save  Case Detail Without CaseSheet");
        SaveCaseDetailWithoutCaseSheetDTO saveCaseDetailWithoutCaseSheetDTO = null;
        ClinicLocationDetail clinicLocationDetail = null;
        String caseNumber = null;
        Patient patient = null;
        CmMaster cmMaster = null;
        ClinicLocation clinicLocation = null;
        Long clinicId = null;
        List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOs=new ArrayList<>();
        try {
            //retrieve request
            saveCaseDetailWithoutCaseSheetDTO = request.getSaveCaseDetailWithoutCaseSheetDTO();
            //validate input
            response = validateInput(saveCaseDetailWithoutCaseSheetDTO);
            if (!response.SUCCESSFUL) {
                log.debug("Invalid Input");
                return response;
            }
            //find clinic location detail which is case and lab order format by clinic location
            clinicLocationDetail = clinicLocationDetailRepository.getClinicLocationDetail(saveCaseDetailWithoutCaseSheetDTO.getClinicLocationId());
            caseNumber = saveComplaintService.generateCaseNumberByClinicLocation(clinicLocationDetail);
            patient = new Patient();
            patient.setId(saveCaseDetailWithoutCaseSheetDTO.getPatientId());
            clinicLocation = new ClinicLocation();
            clinicLocation.setId(saveCaseDetailWithoutCaseSheetDTO.getClinicLocationId());
            clinicId = clinicLocationRepository.findClinicIdByClinicLocationId(saveCaseDetailWithoutCaseSheetDTO.getClinicLocationId());
            System.out.println(clinicId);
            //saving cmMaster
            cmMaster = new CmMaster();
            cmMaster.setClinicLocation(clinicLocation);
            cmMaster.setPatient(patient);
            cmMaster.setClinicCaseNumber(caseNumber);
            cmMaster.setErxCaseNumber(caseNumber);
            cmMaster.setCaseStatus(CaseStatus.CASE_SAVED_STATUS_WITHOUT_CASE_SHEET);
            cmMaster.setCaseCreatedDate(LocalDateTime.now());
            cmMaster.setWellness(true);
            cmMaster.setIpCase(saveCaseDetailWithoutCaseSheetDTO.isIpCase());
            cmMaster.setChiefComplaint("No Reason");
            cmMaster.setScienceMedicineId(1L);
            cmMaster = cmMasterRepository.save(cmMaster);
            //saving CmMasterDetails
            CmMasterDetails cmMasterDetails = new CmMasterDetails();
            cmMasterDetails.setBmOrderId(saveCaseDetailWithoutCaseSheetDTO.getOrderId());
            cmMasterDetails.setCmMaster(cmMaster);
            cmMasterDetails.setCaseCreatedDate(LocalDateTime.now());
            cmMasterDetailsRepository.save(cmMasterDetails);

            for (IdQuantityDTO idQuantityDTO : saveCaseDetailWithoutCaseSheetDTO.getIdQuantityDTOs()) {
                TherapyPlanning therapyPlanning = new TherapyPlanning();
                Long therapyMasterId = therapyMasterRepository.
                        findTherapyMasterIdByServiceCatalogueIdAndClinicId(idQuantityDTO.getId(), clinicId);
                if (therapyMasterId != null) {
                    TherapyMaster therapyMaster = new TherapyMaster();
                    therapyMaster.setId(therapyMasterId);
                    therapyPlanning.setTherapyMaster(therapyMaster);
                }
                therapyPlanning.setCmMaster(cmMaster);
                therapyPlanning.setNumberOfDays(idQuantityDTO.getQuantity());
                therapyPlanning.setServiceCatalogueId(idQuantityDTO.getId());
                therapyPlanning.setPatient(patient);
                therapyPlanning = therapyPlanningRepository.save(therapyPlanning);
                if (therapyPlanning.getNumberOfDays() > 0) {
                    generateTherapyInstances(therapyPlanning, clinicId);

                }
                if (therapyMasterId != null) {
                    //saving medicineType
                    saveTherapyPlanningMedicineTypes(therapyPlanning, therapyMasterId);
                    //saving medicine
                    createBillingOrderServiceDTOs.addAll( saveTherapyPlanningMedicines(therapyPlanning,
                            idQuantityDTO.getId(), therapyMasterId));
                }
            }

            //create response
            response = new SaveCaseDetailWithoutCaseSheetServiceResponse(createBillingOrderServiceDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Case Detail without CaseSheet save successfully");
            log.debug("Case Detail without CaseSheet save successfully");
        } catch (Exception e) {
            response = new SaveCaseDetailWithoutCaseSheetServiceResponse();
            response.SUCCESSFUL = true;
            response.setMessage("Failed to save Case Detail without CaseSheet  ==>" + e.getMessage());
            log.debug("Failed to save Case Detail without CaseSheet");
        }
        return response;
    }


    private SaveCaseDetailWithoutCaseSheetServiceResponse validateInput(SaveCaseDetailWithoutCaseSheetDTO saveCaseDetailWithoutCaseSheetDTO) {
        SaveCaseDetailWithoutCaseSheetServiceResponse response = new SaveCaseDetailWithoutCaseSheetServiceResponse();
        response.SUCCESSFUL = false;
        String message = "Invalid input.Please provide mandatory fields";
        if (saveCaseDetailWithoutCaseSheetDTO.getIdQuantityDTOs() == null || saveCaseDetailWithoutCaseSheetDTO.getIdQuantityDTOs().isEmpty()) {
            response.setMessage(message);
        } else {
            for (IdQuantityDTO idQuantityDTO : saveCaseDetailWithoutCaseSheetDTO.getIdQuantityDTOs()) {
                if (idQuantityDTO.getId() == null || idQuantityDTO.getId() == 0) response.setMessage(message);
                if (idQuantityDTO.getQuantity() == 0) response.setMessage(message);
            }
        }
        if (saveCaseDetailWithoutCaseSheetDTO.getClinicLocationId() == null || saveCaseDetailWithoutCaseSheetDTO.getClinicLocationId() == 0)
            response.setMessage(message);
        else if (saveCaseDetailWithoutCaseSheetDTO.getOrderId() == null || saveCaseDetailWithoutCaseSheetDTO.getOrderId() == 0)
            response.setMessage(message);
        else if (saveCaseDetailWithoutCaseSheetDTO.getPatientId() == null || saveCaseDetailWithoutCaseSheetDTO.getPatientId() == 0)
            response.setMessage(message);
        else {
            response.SUCCESSFUL = true;
        }
        return response;
    }

    public void generateTherapyInstances(TherapyPlanning therapyPlanning, Long clinicId) {
        long numberOfInstances = therapyPlanning.getNumberOfDays();
        for (long index = 0; index < numberOfInstances; index++) {
            TherapyInstance therapyInstance = new TherapyInstance();
            therapyInstance.setTherapyPlanning(therapyPlanning);

            String paymentPolicy = clinicPreferenceRepository.getPaymentPolicyByClinicId(clinicId);
            if (paymentPolicy.equalsIgnoreCase("NO_BILLING")) {
                therapyInstance.setBillingStatus("PAID");
            } else {
                therapyInstance.setBillingStatus("NOT_PAID");
            }
            therapyInstance.setScheduleStatus("NOT_SCHEDULED");
            therapyInstance = getNextAction(therapyInstance, therapyPlanning.isWellness(), paymentPolicy);
            if (!therapyInstance.getNextAction().isEmpty()) {
                // result = "success";
                TherapyInstance orgTherapyInstance = therapyInstance;
                //therapyInstance.setDoctorRemark(therapyPlanning.getInstructions());
                therapyInstance = therapyInstanceRepository.save(therapyInstance);
                for (TherapyInstanceNextAction tempTherapyInstanceNextAction : orgTherapyInstance.getNextAction()) {
                    tempTherapyInstanceNextAction.setTherapyInstance(therapyInstance);
                    therapyInstanceNextActionRepository.save(tempTherapyInstanceNextAction);

                }
            }

        }
    }

    public TherapyInstance getNextAction(TherapyInstance therapyInstance, boolean isWellness, String paymentPolicy) {
        String progressStatus = null;
        List<TherapyInstanceState> therapyInstanceState = null;
        TherapyInstanceNextAction therapyInstanceNextAction = null;
        List<TherapyInstanceNextAction> therapyInstanceNextActionList = new ArrayList<TherapyInstanceNextAction>();
        therapyInstanceState = therapyInstanceStateRepository.getInstanceStateByPaymentPolicyAndWellness(paymentPolicy, isWellness);
        therapyInstance.setNextAction(therapyInstanceNextActionList);
        for (TherapyInstanceState tempTherapyInstanceState : therapyInstanceState) {
            progressStatus = "success";

            if (!(therapyInstance.getTherapyPlanningStatus().trim())
                    .equalsIgnoreCase(tempTherapyInstanceState.getTherapyPlanningStatus().trim())
                    && !(tempTherapyInstanceState.getTherapyPlanningStatus().trim()).equals("*")) {
                progressStatus = "failed";
            }

            if (!(therapyInstance.getBillingStatus().trim())
                    .equalsIgnoreCase(tempTherapyInstanceState.getBillingStatus().trim())
                    && !(tempTherapyInstanceState.getBillingStatus().trim()).equals("*")) {
                progressStatus = "failed";
            }


            if (!(therapyInstance.getScheduleStatus().trim())
                    .equalsIgnoreCase(tempTherapyInstanceState.getScheduleStatus().trim())
                    && !(tempTherapyInstanceState.getScheduleStatus().trim()).equals("*")) {
                progressStatus = "failed";
            }

            if (!(therapyInstance.getTherapistStatus().trim())
                    .equalsIgnoreCase(tempTherapyInstanceState.getTherapistStatus().trim())
                    && !(tempTherapyInstanceState.getTherapistStatus().trim()).equals("*")) {
                progressStatus = "failed";
            }


            if (!(therapyInstance.getPerformTherapyStatus().trim())
                    .equalsIgnoreCase(tempTherapyInstanceState.getPerformTherapyStatus().trim())
                    && !(tempTherapyInstanceState.getPerformTherapyStatus().trim()).equals("*")) {
                progressStatus = "failed";
            }

            if (progressStatus.equals("success")) {
                therapyInstanceNextAction = new TherapyInstanceNextAction();
                therapyInstanceNextAction.setTherapyInstance(therapyInstance);
                therapyInstanceNextAction.setNextAction(tempTherapyInstanceState.getNextAction());
                therapyInstanceNextActionList.add(therapyInstanceNextAction);
                therapyInstance.setNextAction(therapyInstanceNextActionList);
            }
        }
        return therapyInstance;
    }

    // saving therapy medicine types
    private void saveTherapyPlanningMedicineTypes(TherapyPlanning therapyPlanning, Long therapyMasterId) {
        List<TherapyPlanningMedicineType> therapyPlanningMedicineTypes = null;
        try {
            log.debug("call to save therapy planning medicine type");
            therapyPlanningMedicineTypes = new ArrayList<>();

            // find therapy master medicine type by therapy master
            List<Long> medicineTypeIds = therapyMasterMedicineTypeRepository.findMedicineTypeIdsByTherapyMasterId
                    (therapyMasterId);
            if (!medicineTypeIds.isEmpty())
                for (Long medicineTypeId : medicineTypeIds) {
                    TherapyPlanningMedicineType therapyPlanningMedicineType = new TherapyPlanningMedicineType();
                    therapyPlanningMedicineType.setTherapyPlanning(therapyPlanning);
                    therapyPlanningMedicineType.setMedicineTypeId(medicineTypeId);
                    therapyPlanningMedicineTypes.add(therapyPlanningMedicineType);
                }
            if (therapyPlanningMedicineTypes != null) {
                //saving
                therapyPlanningMedicineTypeRepository.save(therapyPlanningMedicineTypes);
            }
        } catch (Exception e) {
            log.error("Failed to save therapy medicine type" + e.getMessage());
        }
    }

    //save therapy planning medicine
    private List<CreateBillingOrderServiceDTO> saveTherapyPlanningMedicines(TherapyPlanning therapyPlanning,
                                                                            Long serviceId, Long therapyMasterId) {
        List<TherapyPlanningMedicine> therapyPlanningMedicines = null;
        List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOList = new ArrayList<>();
        List<Object[]> therapyMasterMedicineDetails = null;
        try {
            log.debug("call to save therapy planning medicine");
            therapyPlanningMedicines = new ArrayList<>();
            therapyMasterMedicineDetails = therapyMasterMedicineRepository.
                    findTherapyMasterMedicineDetailsByTherapyMasterId(therapyMasterId);
            if(!therapyMasterMedicineDetails.isEmpty()) {
                for (Object[] therapyMasterMedicineDetail : therapyMasterMedicineDetails) {
                    CreateBillingOrderServiceDTO createBillingOrderServiceDTO = new CreateBillingOrderServiceDTO();
                    Long productCatalogueCommonDetailId = (Long) therapyMasterMedicineDetail[0];
                    Long medicineTypeId = (Long) therapyMasterMedicineDetail[1];
                    String quantityStr = (String) therapyMasterMedicineDetail[2];
                    Long uomMasterId = (Long) therapyMasterMedicineDetail[3];
                    String medicineInstructions = (String) therapyMasterMedicineDetail[4];
                    TherapyPlanningMedicine therapyPlanningMedicine = new TherapyPlanningMedicine();
                    therapyPlanningMedicine.setProductCatalogueCommonDetailId(productCatalogueCommonDetailId);
                    therapyPlanningMedicine.setMedicineTypeId(medicineTypeId);
                    therapyPlanningMedicine.setQuantity(quantityStr);
                    therapyPlanningMedicine.setUomMasterId(uomMasterId);
                    therapyPlanningMedicine.setMedicineInstructions(medicineInstructions);
                    therapyPlanningMedicine.setTherapyPlanning(therapyPlanning);
                    therapyPlanningMedicines.add(therapyPlanningMedicine);
                    //setting createBillingOrderServiceDTO to create medicine Order
                    createBillingOrderServiceDTO.setServiceId(productCatalogueCommonDetailId);
                    int quantity = Integer.parseInt(quantityStr);
                    createBillingOrderServiceDTO.setQuantity(quantity);
                    createBillingOrderServiceDTO.setType(Constants.PRODUCT_TYPE);
                    createBillingOrderServiceDTO.setServiceCatalogueId(serviceId);
                    createBillingOrderServiceDTOList.add(createBillingOrderServiceDTO);
                }
                therapyPlanningMedicineRepository.save(therapyPlanningMedicines);
            }

        } catch (Exception e) {
            log.error("Failed to save therapy medicine Detail " + e.getMessage());
        }
        return createBillingOrderServiceDTOList;

    }
}
