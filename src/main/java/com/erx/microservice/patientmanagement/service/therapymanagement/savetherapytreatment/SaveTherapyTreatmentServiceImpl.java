package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytreatment;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterDetails;
import com.erx.microservice.patientmanagement.domain.therapymanagement.*;
import com.erx.microservice.patientmanagement.repository.ClinicRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.ClinicPreferenceRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterDetailsRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.*;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyMedicineDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyMedicineTypeDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyResponseDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("saveTherapyTreatmentService")
public class SaveTherapyTreatmentServiceImpl implements SaveTherapyTreatmentService {

    private final Logger log = LoggerFactory.getLogger(SaveTherapyTreatmentServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private TherapyPlanningRepository therapyPlanningRepository;

    @Autowired
    private TherapyPlanningMedicineRepository therapyPlanningMedicineRepository;

    @Autowired
    private TherapyPlanningMedicineTypeRepository therapyPlanningMedicineTypeRepository;

    @Autowired
    private TherapyInstanceRepository therapyInstanceRepository;

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private ClinicPreferenceRepository clinicPreferenceRepository;

    @Autowired
    private TherapyInstanceNextActionRepository therapyInstanceNextActionRepository;

    @Autowired
    private TherapyScheduleRepository therapyScheduleRepository;

    @Autowired
    private TherapyInstanceStateRepository therapyInstanceStateRepository;


    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Override
    public SaveTherapyTreatmentServiceResponse execute(SaveTherapyTreatmentServiceRequest request) throws ServiceException {
        SaveTherapyTreatmentServiceResponse response = null;
        TherapyPlanning therapyPlanning = new TherapyPlanning();
        List<TherapyPlanningMedicineType> therapyPlanningMedicineTypes = new ArrayList<>();
        List<TherapyPlanningMedicine> therapyPlanningMedicines = new ArrayList<>();
        TherapyPlanning savedTherapyPlanning = null;
        List<TherapyPlanningMedicineType> savedTherapyPlanningMedicineTypes = null;
        List<TherapyPlanningMedicine> savedTherapyPlanningMedicines = null;
        SaveTreatmentTherapyResponseDTO saveTreatmentTherapyResponseDTO = new SaveTreatmentTherapyResponseDTO();
        List<String> resultList = null;
      //  String updateTherapyStatus = new String();
        try {
            log.debug("Call to save case therapy treatment for a clinic");

            //frame therapy master object
            therapyPlanning = frameTherapyPlanning(request.getSaveTreatmentTherapyDTO());
            if (therapyPlanning != null)
                //save therapy planning
                savedTherapyPlanning = therapyPlanningRepository.save(therapyPlanning);

            //frame therapy planning medicine types
            if (request.getSaveTreatmentTherapyDTO().getSaveTreatmentTherapyMedicineTypeDTOs() != null) {
                therapyPlanningMedicineTypes = frameTherapyPlanningMedicineTypes(savedTherapyPlanning, request.getSaveTreatmentTherapyDTO().getSaveTreatmentTherapyMedicineTypeDTOs());
                if (therapyPlanningMedicineTypes != null)
                    //save therapy planning medicine type
                    savedTherapyPlanningMedicineTypes = therapyPlanningMedicineTypeRepository.save(therapyPlanningMedicineTypes);

                //setting the id's in response
                List<Long> therapyPlanningMedicineTypeIds = new ArrayList<>();
                for (TherapyPlanningMedicineType therapyPlanningMedicineType : savedTherapyPlanningMedicineTypes) {
                    Long therapyPlanningMedicineTypeId = null;
                    therapyPlanningMedicineTypeId = therapyPlanningMedicineType.getId();
                    therapyPlanningMedicineTypeIds.add(therapyPlanningMedicineTypeId);
                    saveTreatmentTherapyResponseDTO.setTherapyPlanningMedicineTypeId(therapyPlanningMedicineTypeIds);
                }
            }

            //frame therapy planning medicine
            if (request.getSaveTreatmentTherapyDTO().getSaveTreatmentTherapyMedicineDTOs() != null) {
                therapyPlanningMedicines = frameTherapyPlanningMedicines(savedTherapyPlanning, request.getSaveTreatmentTherapyDTO().getSaveTreatmentTherapyMedicineDTOs());
                if (therapyPlanningMedicines != null)
                    savedTherapyPlanningMedicines = therapyPlanningMedicineRepository.save(therapyPlanningMedicines);

                //setting the id's in response
                List<Long> therapyPlanningMedicineIds = new ArrayList<>();
                for (TherapyPlanningMedicine therapyPlanningMedicine : savedTherapyPlanningMedicines) {
                    Long therapyPlanningMedicineId = null;
                    therapyPlanningMedicineId = therapyPlanningMedicine.getId();
                    therapyPlanningMedicineIds.add(therapyPlanningMedicineId);
                    saveTreatmentTherapyResponseDTO.setTherapyPlanningMedicineId(therapyPlanningMedicineIds);
                }
            }

            // save therapy instances
            if (therapyPlanning.getNumberOfDays() > 0) {
                resultList = generateTherapyInstances(therapyPlanning, request.getSaveTreatmentTherapyDTO().getClinicId());
                if (resultList == null) {
                    if (request.getSaveTreatmentTherapyDTO().getTherapyPlanningId() != null) {
                        String updateTherapyStatus = setUpdateTherapyDates(therapyPlanning, request.getSaveTreatmentTherapyDTO().getClinicId());
                        if (updateTherapyStatus.equalsIgnoreCase("Failed")) {
                            resultList = new ArrayList<String>();
                            resultList.add(therapyPlanning.getTherapyMaster().getTherapyName());
                            removeTherapyPlanning(therapyPlanning);
                        }
                    }
                }
            } else {
                resultList = new ArrayList<String>();
                removeTherapyPlanning(therapyPlanning);
            }

            //create response
            saveTreatmentTherapyResponseDTO.setTherapyPlanningId(savedTherapyPlanning.getId());
            response = new SaveTherapyTreatmentServiceResponse(saveTreatmentTherapyResponseDTO);
            response.setMessage("Case sheet therapy treatment saved successfully");
            log.debug("Case sheet therapy treatment saved successfully");
        } catch (Exception e) {
            response = new SaveTherapyTreatmentServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save Case sheet therapy treatment");
            response.setMessage(e.getMessage() + " so,Failed to save Case sheet therapy treatment");
        }
        return response;
    }

    // method to frame therapy planning
    private TherapyPlanning frameTherapyPlanning(SaveTreatmentTherapyDTO saveTreatmentTherapyDTO) {
        TherapyPlanning therapyPlanning = new TherapyPlanning();
        Patient patient = new Patient();
        CmMaster cmMaster = new CmMaster();
        CmMasterDetails savedCmMasterDetails = new CmMasterDetails();
        try {
            log.debug("framing therapy planning");
            //get patient by patient id
            patient = patientRepository.findOne(saveTreatmentTherapyDTO.getPatientId());

            //get case master by case id
            cmMaster = cmMasterRepository.findOne(saveTreatmentTherapyDTO.getCaseId());

            //retrieve cm master details
            savedCmMasterDetails = cmMasterDetailsRepository.findOne(saveTreatmentTherapyDTO.getCmMasterDetailId());

            if (saveTreatmentTherapyDTO.getTherapyPlanningId() != null) {
                // find therapy master by id
                therapyPlanning = therapyPlanningRepository.findOne(saveTreatmentTherapyDTO.getTherapyPlanningId());
                therapyPlanning = frameTherapyPlanningObj(therapyPlanning, saveTreatmentTherapyDTO);
            } else {
                if (patient != null && cmMaster != null && savedCmMasterDetails != null)
                    therapyPlanning = frameTherapyPlanningObj(therapyPlanning, saveTreatmentTherapyDTO);
                therapyPlanning.setPatient(patient);
                therapyPlanning.setCmMaster(cmMaster);
                therapyPlanning.setCmMasterDetails(savedCmMasterDetails);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy planning" + e.getMessage());
        }
        return therapyPlanning;
    }

    //method to frame therapy planning obj
    private TherapyPlanning frameTherapyPlanningObj(TherapyPlanning therapyPlanning, SaveTreatmentTherapyDTO saveTreatmentTherapyDTO) {
        try {
            log.debug("call to frame therapy planning obj");
            therapyPlanning.setServiceCatalogueId(saveTreatmentTherapyDTO.getServiceCatalogueId());
            therapyPlanning.setNumberOfDays(saveTreatmentTherapyDTO.getNumberOfDays());
            therapyPlanning.setInstructions(saveTreatmentTherapyDTO.getInstructions());
            //repeat after removed from front end
            // therapyPlanning.setPeriodicInterval(saveTreatmentTherapyDTO.getPeriodicInterval());
            therapyPlanning.setPeriodicInterval(0);
        } catch (Exception e) {
            log.error("failed to frame therapy planning obj" + e.getMessage());
        }
        return therapyPlanning;
    }

    //method to frame therapy planning medicine types
    private List<TherapyPlanningMedicineType> frameTherapyPlanningMedicineTypes(TherapyPlanning savedTherapyPlanning,
                                                                                List<SaveTreatmentTherapyMedicineTypeDTO> saveTreatmentTherapyMedicineTypeDTOs) {

        List<TherapyPlanningMedicineType> therapyPlanningMedicineTypes = null;
        try {
            log.debug("call to frame therapy planning medicine type");
            therapyPlanningMedicineTypes = new ArrayList<>();
            for (SaveTreatmentTherapyMedicineTypeDTO saveTreatmentTherapyMedicineTypeDTO : saveTreatmentTherapyMedicineTypeDTOs) {
                TherapyPlanningMedicineType therapyPlanningMedicineType = new TherapyPlanningMedicineType();
                if (saveTreatmentTherapyMedicineTypeDTO.getTherapyPlanningMedicineTypeId() != null) {
                    therapyPlanningMedicineType = therapyPlanningMedicineTypeRepository.findOne(saveTreatmentTherapyMedicineTypeDTO.getTherapyPlanningMedicineTypeId());
                    therapyPlanningMedicineType.setMedicineTypeId(saveTreatmentTherapyMedicineTypeDTO.getMedicineTypeMasterId());
                } else {
                    therapyPlanningMedicineType.setTherapyPlanning(savedTherapyPlanning);
                    therapyPlanningMedicineType.setMedicineTypeId(saveTreatmentTherapyMedicineTypeDTO.getMedicineTypeMasterId());
                }
                therapyPlanningMedicineTypes.add(therapyPlanningMedicineType);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy planning medicine type" + e.getMessage());
        }
        return therapyPlanningMedicineTypes;
    }

    //method to frame therapy planning medicine
    private List<TherapyPlanningMedicine> frameTherapyPlanningMedicines(TherapyPlanning savedTherapyPlanning,
                                                                        List<SaveTreatmentTherapyMedicineDTO> saveTreatmentTherapyMedicineDTOs) {
        List<TherapyPlanningMedicine> therapyPlanningMedicines = null;
        try {
            log.debug("call to frame therapy planning medicine");
            therapyPlanningMedicines = new ArrayList<>();
            for (SaveTreatmentTherapyMedicineDTO saveTreatmentTherapyMedicineDTO : saveTreatmentTherapyMedicineDTOs) {
                TherapyPlanningMedicine therapyPlanningMedicine = new TherapyPlanningMedicine();
                if (saveTreatmentTherapyMedicineDTO.getTherapyPlanningMedicineId() != null) {
                    therapyPlanningMedicine = therapyPlanningMedicineRepository.findOne(saveTreatmentTherapyMedicineDTO.getTherapyPlanningMedicineId());
                    therapyPlanningMedicine = frameTherapyPlanningMedicine(therapyPlanningMedicine, saveTreatmentTherapyMedicineDTO);
                } else {
                    therapyPlanningMedicine = frameTherapyPlanningMedicine(therapyPlanningMedicine, saveTreatmentTherapyMedicineDTO);
                    therapyPlanningMedicine.setTherapyPlanning(savedTherapyPlanning);
                }
                therapyPlanningMedicines.add(therapyPlanningMedicine);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy planning medicine" + e.getMessage());
        }
        return therapyPlanningMedicines;
    }

    // method to frame therapy planning medicine obj
    private TherapyPlanningMedicine frameTherapyPlanningMedicine(TherapyPlanningMedicine therapyPlanningMedicine,
                                                                 SaveTreatmentTherapyMedicineDTO saveTreatmentTherapyMedicineDTO) {
        try {
            therapyPlanningMedicine.setProductCatalogueCommonDetailId(saveTreatmentTherapyMedicineDTO.getPmProdCatalogueCommonDetailsId());
            therapyPlanningMedicine.setMedicineTypeId(saveTreatmentTherapyMedicineDTO.getMedicineTypeMasterId());
            therapyPlanningMedicine.setQuantity(saveTreatmentTherapyMedicineDTO.getQuantity());
            therapyPlanningMedicine.setUomMasterId(saveTreatmentTherapyMedicineDTO.getUomMasterId());
            therapyPlanningMedicine.setMedicineInstructions(saveTreatmentTherapyMedicineDTO.getMedicineInstructions());
        } catch (Exception e) {
            log.error("Failed to frame therapy planning medicine" + e.getMessage());
        }
        return therapyPlanningMedicine;
    }

    //method to generate therapy instances
    private List<String> generateTherapyInstances(TherapyPlanning therapyPlanning, Long clinicId) {
        String result = null;
        List<String> resultList = null;
        TherapyInstance therapyInstance = null;
        TherapyInstance orgTherapyInstance = null;
        try {
            log.debug("SaveTherapyTreatmentServiceImpl ---- > generateTherapyInstances");
            long numberOfInstances = therapyPlanning.getNumberOfDays();
            for (long index = 0; index < numberOfInstances; index++) {
                therapyInstance = new TherapyInstance();
                therapyInstance.setTherapyPlanning(therapyPlanning);
                String paymentPolicy= clinicPreferenceRepository.getPaymentPolicyByClinicId(clinicId);

                if (paymentPolicy.equalsIgnoreCase("NO_BILLING")) {
                    therapyInstance.setBillingStatus("PAID");
                }
                therapyInstance.setScheduleStatus("NOT_SCHEDULED");
                therapyInstance = getNextAction(therapyInstance, therapyPlanning.isWellness(), paymentPolicy);
                if (therapyInstance.getNextAction() != null && therapyInstance.getNextAction().size() != 0) {
                    result = "success";
                    orgTherapyInstance = therapyInstance;
                    therapyInstance.setDoctorRemark(therapyPlanning.getInstructions());
                    therapyInstance = therapyInstanceRepository.save(therapyInstance);
                    for (TherapyInstanceNextAction tempTherapyInstanceNextAction : orgTherapyInstance.getNextAction()) {
                        tempTherapyInstanceNextAction.setTherapyInstance(therapyInstance);
                        therapyInstanceNextActionRepository.save(tempTherapyInstanceNextAction);
                    }
                } else {
                    result = "failed";
                    resultList = new ArrayList<String>();
                    resultList.add(therapyPlanning.getTherapyMaster().getTherapyName());
                    log.debug("ERROR IN THERAPY INSTANCE STATE CALCULATION");
                }
            }
        } catch (Exception e) {
            log.error("SaveTherapyTreatmentServiceImpl ---- > generateTherapyInstances " + e);
        }
        return resultList;
    }

    //method to get next action
    public TherapyInstance getNextAction(TherapyInstance therapyInstance, boolean isWellness, String paymentPolicy) {
        String progressStatus = null;
        List<TherapyInstanceState> therapyInstanceState =null;
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

    //method to update therapy dates
    public String setUpdateTherapyDates(TherapyPlanning therapyPlanning, Long clinicId) {
        log.debug("SaveTherapyTreatmentServiceImpl -----> setUpdateTherapyDates");
        String status = "failed";
        long therapiesInterval;
        long instanceInterval;
        String checkDay;
        List<TherapyInstance> instanceList = null;
        List<TherapyPlanning> planningList = null;
        LocalDateTime therapyStartDate = null;
        LocalDateTime therapyStartOn = null;
        LocalDateTime instanceScheduleDate = LocalDateTime.now();
        String clinicHoliday;
        int latestPlan;
        try {
            planningList = therapyPlanningRepository.getTherapyPlanningByCaseAndService(therapyPlanning.getCmMaster().getId(),therapyPlanning.getServiceCatalogueId());

            therapyStartOn = getTherapyStartDate(therapyPlanning.getCmMaster());
            if (therapyStartOn != null) {
                if (planningList != null) {
                    if (planningList.size() > 0) {
                        if (planningList.get(0).getTherapyEndDate() != null) {
                            therapyStartDate = planningList.get(0).getTherapyEndDate().plusDays(1);
                        } else {
                            therapyStartDate = LocalDateTime.now().plusDays(1);
                        }
                    }
                }
                clinicHoliday = clinicPreferenceRepository.getCommonHoliday(clinicId);
                if (therapyStartDate.isBefore(LocalDateTime.now())) {
                    therapyStartDate = LocalDateTime.now().plusDays(1);
                }
                therapiesInterval = therapyPlanning.getTherapiesInterval();
                therapyStartDate = therapyStartDate.plusDays((int) therapiesInterval);
                therapyPlanning.setTherapyScheduleDate(therapyStartDate);
                therapyPlanning.setTherapyStartDate(therapyStartOn);
                therapyPlanning = therapyPlanningRepository.save(therapyPlanning);
                instanceScheduleDate = therapyStartDate;
                instanceInterval = therapyPlanning.getPeriodicInterval();
                int count = 0;
                instanceList = therapyInstanceRepository.findByTherapyPlanningId(therapyPlanning.getId());
                for (TherapyInstance tempTherapyInstance : instanceList) {
                    TherapySchedule therapySchedule = new TherapySchedule();
                    if (count == 0) {
                        checkDay = instanceScheduleDate.getDayOfWeek().toString();
                        if (checkDay.trim().equalsIgnoreCase(clinicHoliday)) {
                            instanceScheduleDate = instanceScheduleDate.plusDays(1);
                        }
                        therapySchedule.setScheduleDate(instanceScheduleDate);
                    } else {
                        instanceScheduleDate = instanceScheduleDate.plusDays((int) instanceInterval);

                        checkDay = instanceScheduleDate.getDayOfWeek().toString();
                        if (checkDay.trim().equalsIgnoreCase(clinicHoliday)) {
                            instanceScheduleDate = instanceScheduleDate.plusDays(1);
                        }
                        therapySchedule.setScheduleDate(instanceScheduleDate);
                    }
                    therapySchedule.setTherapyInstance(tempTherapyInstance);
                    therapySchedule = therapyScheduleRepository.save(therapySchedule);
                    count++;
                }
                therapyPlanning.setTherapyEndDate(instanceScheduleDate);
                therapyPlanning = therapyPlanningRepository.save(therapyPlanning);
            }

            status = "success";
        } catch (NumberFormatException e) {
            status = "failed";
            log.error("SaveTherapyTreatmentServiceImpl -----> setUpdateTherapyDates " + e);
        } catch (Exception e) {
            status = "failed";
            log.error("SaveTherapyTreatmentServiceImpl -----> setUpdateTherapyDates " + e);
        }
        return status;
    }

    //method to get therapy start date
    private LocalDateTime getTherapyStartDate(CmMaster cmMaster) {
        log.debug("SaveTherapyTreatmentServiceImpl -----> getTherapyStartDate");
        List<TherapyPlanning> therapyPlanningList = new ArrayList<TherapyPlanning>();
        LocalDateTime therapyStartOn = null;
        try {

            therapyPlanningList = therapyPlanningRepository.getPlanningByStart(cmMaster.getId());

            if(therapyPlanningList.size() > 0){
                therapyStartOn = therapyPlanningList.get(0).getTherapyStartDate();
            }
        } catch (Exception e) {
            log.error("SaveTherapyTreatmentServiceImpl -----> getTherapyStartDate " + e.toString());
        }
        return therapyStartOn;
    }

    // removing therapy planning
    public void removeTherapyPlanning(TherapyPlanning therapyPlanning) {
        List<TherapyInstance> therapyInstances = new ArrayList<>();
        try {
            //find therapy instances
            therapyInstances = therapyInstanceRepository.findByTherapyPlanningId(therapyPlanning.getId());
            if (therapyInstances != null) {
                for (TherapyInstance therapyInstance : therapyInstances) {
                    therapyInstanceRepository.delete(therapyInstance);
                }
            }
            therapyPlanningRepository.delete(therapyPlanning);
        } catch (Exception e) {
            log.error("SaveTherapyTreatmentServiceImpl -----> removeTherapyPlanning " + e.toString());
        }

    }

}
