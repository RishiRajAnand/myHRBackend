package com.erx.microservice.patientmanagement.service.casemanagement.saveinvestigationandtreatmentdetails;

/*
* created by Latha on 01-10-2018
* */


import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.UserDetail;
import com.erx.microservice.patientmanagement.domain.casemanagement.*;
import com.erx.microservice.patientmanagement.domain.therapymanagement.*;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.LookupValueRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.repository.UserDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.*;
import com.erx.microservice.patientmanagement.repository.therapymanagement.*;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderInputDTO;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderOutputDTO;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderServiceDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.*;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyMedicineDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyMedicineTypeDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyResponseDTO;
import com.erx.microservice.patientmanagement.service.generatbillingorder.GenerateCaseSheetBillingOrderService;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service("saveInvestigationAndTreatmentDetailsService")
public class SaveInvestigationAndTreatmentDetailsServiceImpl implements SaveInvestigationAndTreatmentDetailsService {

    private final Logger log = LoggerFactory.getLogger(SaveInvestigationAndTreatmentDetailsServiceImpl.class);

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Autowired
    private ProvisionalDiagnosisMasterRepository provisionalDiagnosisMasterRepository;

    @Autowired
    private CmIcdMasterRepository cmIcdMasterRepository;

    @Autowired
    private CmAcdMasterRepository cmAcdMasterRepository;

    @Autowired
    private CmInvestigationRepository cmInvestigationRepository;

    @Autowired
    private CmInvestigationDetailRepository cmInvestigationDetailRepository;

    @Autowired
    private CmTreatmentRepository cmTreatmentRepository;

    @Autowired
    private CmDosageValueMappingRepository cmDosageValueMappingRepository;

    @Autowired
    private LookupValueRepository lookupValueRepository;

    @Autowired
    private RouteOfAdministrationRepository routeOfAdministrationRepository;

    @Autowired
    private CmTreatmentMedicineDetailRepository cmTreatmentMedicineDetailRepository;

    @Autowired
    private CmTreatmentGroupMedicineDetailRepository cmTreatmentGroupMedicineDetailRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TherapyPlanningRepository therapyPlanningRepository;

    @Autowired
    private TherapyPlanningMedicineRepository therapyPlanningMedicineRepository;

    @Autowired
    private TherapyPlanningMedicineTypeRepository therapyPlanningMedicineTypeRepository;

    @Autowired
    private ClinicPreferenceRepository clinicPreferenceRepository;

    @Autowired
    private TherapyInstanceRepository therapyInstanceRepository;

    @Autowired
    private TherapyInstanceNextActionRepository therapyInstanceNextActionRepository;

    @Autowired
    private TherapyInstanceStateRepository therapyInstanceStateRepository;

    @Autowired
    private TherapyScheduleRepository therapyScheduleRepository;

    @Autowired
    private GenerateCaseSheetBillingOrderService generateCaseSheetBillingOrderService;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public SaveInvestigationAndTreatmentDetailsServiceResponse execute(SaveInvestigationAndTreatmentDetailsServiceRequest request) throws ServiceException {
        SaveInvestigationAndTreatmentDetailsServiceResponse response = null;
        CmInvestigation cmInvestigation = new CmInvestigation();
        List<CmInvestigationDetail> cmInvestigationDetails = new ArrayList<>();
        CmMaster cmMaster = new CmMaster();
        CmMasterDetails savedCmMasterDetails = new CmMasterDetails();
        CmTreatment cmTreatment = new CmTreatment();
        List<CmTreatmentMedicineDetail> cmTreatmentMedicineDetails = new ArrayList<>();
        TherapyPlanning therapyPlanning = new TherapyPlanning();
        List<TherapyPlanningMedicineType> therapyPlanningMedicineTypes = new ArrayList<>();
        List<TherapyPlanningMedicine> therapyPlanningMedicines = new ArrayList<>();
        TherapyPlanning savedTherapyPlanning = null;
        List<TherapyPlanningMedicineType> savedTherapyPlanningMedicineTypes = null;
        List<TherapyPlanningMedicine> savedTherapyPlanningMedicines = new ArrayList<>();
        List<String> resultList = null;
        CreateBillingOrderOutputDTO createBillingOrderOutputDTO = new CreateBillingOrderOutputDTO();
        List<TherapyPlanning> therapyPlannings = new ArrayList<>();
        SaveCmInvestigationResponseDTO saveCmInvestigationResponseDTO = new SaveCmInvestigationResponseDTO();
        SaveMedicineResponseDTO saveMedicineResponseDTO = new SaveMedicineResponseDTO();
        SaveTreatmentTherapyResponseDTO saveTreatmentTherapyResponseDTO = new SaveTreatmentTherapyResponseDTO();
        try {
            log.debug("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Call to save investigation and treatment details");
            if(request.getSaveInvestigationAndTreatmentDTO().getSaveTherapyPlanningDTOs() != null || request.getSaveInvestigationAndTreatmentDTO().getSaveCaseMedicineDTOs() != null
                    || request.getSaveInvestigationAndTreatmentDTO().getSaveInvestigationDetailDTOs() != null || request.getSaveInvestigationAndTreatmentDTO().getSaveCmMedicineTreatmentDTO() != null
                    || request.getSaveInvestigationAndTreatmentDTO().getSaveCmInvestigationDetailDTO() != null){

                //find cm master by cm master id
                cmMaster = cmMasterRepository.findOne(request.getSaveInvestigationAndTreatmentDTO().getCmMasterId());
                //retrieve cm master details
                savedCmMasterDetails = cmMasterDetailsRepository.findOne(request.getSaveInvestigationAndTreatmentDTO().getCmMasterDetailId());
                       /* saveCmMasterDetails(cmMaster,request.getSaveInvestigationAndTreatmentDTO().getPatientAppointmentId());*/

            }
            //save cm investigation and cm investigation details
            if(request.getSaveInvestigationAndTreatmentDTO().getSaveCmInvestigationDetailDTO() != null ||
                    request.getSaveInvestigationAndTreatmentDTO().getSaveInvestigationDetailDTOs() != null){
                //save cm investigation
                cmInvestigation = saveCmInvestigation(request.getSaveInvestigationAndTreatmentDTO().getSaveCmInvestigationDetailDTO(), savedCmMasterDetails);
                saveCmInvestigationResponseDTO.setCmInvestigationId(cmInvestigation.getId());
                //save cm investigation details
                if(cmInvestigation != null){
                    cmInvestigationDetails = saveCmInvestigationDetail(cmInvestigation,request.getSaveInvestigationAndTreatmentDTO().getSaveInvestigationDetailDTOs(),
                            request.getSaveInvestigationAndTreatmentDTO().getClinicId());
                    //setting the dto in response
                    List<Long> cmInvestigationDetailIds = new ArrayList<>();
                    for(CmInvestigationDetail cmInvestigationDetail : cmInvestigationDetails){
                        Long cmInvestigationDetailId = null;
                        cmInvestigationDetailId = cmInvestigationDetail.getId();
                        cmInvestigationDetailIds.add(cmInvestigationDetailId);
                        saveCmInvestigationResponseDTO.setCmInvestigationDetailId(cmInvestigationDetailIds);
                    }
                }
            }
            //save pathya pathya and cm medicine and group medicine treatment
            if(request.getSaveInvestigationAndTreatmentDTO().getSaveCmMedicineTreatmentDTO() != null) {
                if (request.getSaveInvestigationAndTreatmentDTO().getSaveCmMedicineTreatmentDTO().getPathyaPathyaDTO() != null ||
                        request.getSaveInvestigationAndTreatmentDTO().getSaveCmMedicineTreatmentDTO().getTreatmentPrincipleDTO() != null
                        || request.getSaveInvestigationAndTreatmentDTO().getSaveCaseMedicineDTOs() != null ||
                        request.getSaveInvestigationAndTreatmentDTO().getSaveCmMedicineTreatmentDTO() != null) {

                    //save cm treatment
                    cmTreatment = saveCmTreatment(request.getSaveInvestigationAndTreatmentDTO(), request.getSaveInvestigationAndTreatmentDTO().getSaveCmMedicineTreatmentDTO(), savedCmMasterDetails);
                    saveMedicineResponseDTO.setCmTreatmentId(cmTreatment.getId());
                    //save cm treatment medicine detail and group medicine detail
                    if (request.getSaveInvestigationAndTreatmentDTO().getSaveCaseMedicineDTOs() != null) {
                        // calling method to frame and save cm treatment medicine detail and cm treatment group medicine
                        cmTreatmentMedicineDetails = saveCmTreatmentMedicineDetails(cmTreatment, request.getSaveInvestigationAndTreatmentDTO().getSaveCaseMedicineDTOs());

                        //setting the dto in response
                        List<Long> cmTreatmentMedicineDetailIds = new ArrayList<>();
                        List<Long> cmTreatmentGroupMedicineDetailIds = new ArrayList<>();
                        for (CmTreatmentMedicineDetail cmTreatmentMedicineDetail : cmTreatmentMedicineDetails) {
                            Long cmTreatmentMedicineDetailId = null;
                            cmTreatmentMedicineDetailId = cmTreatmentMedicineDetail.getId();
                            cmTreatmentMedicineDetailIds.add(cmTreatmentMedicineDetailId);
                            saveMedicineResponseDTO.setCmTreatmentMedicineDetailIds(cmTreatmentMedicineDetailIds);
                            if (cmTreatmentMedicineDetail.getCmTreatmentGroupMedicineList() != null) {
                                for (CmTreatmentGroupMedicineDetail cmTreatmentGroupMedicineDetail : cmTreatmentMedicineDetail.getCmTreatmentGroupMedicineList()) {
                                    Long cmTreatmentGroupMedicineDetailId = null;
                                    cmTreatmentGroupMedicineDetailId = cmTreatmentGroupMedicineDetail.getId();
                                    cmTreatmentGroupMedicineDetailIds.add(cmTreatmentGroupMedicineDetailId);
                                    saveMedicineResponseDTO.setCmTreatmentGroupMedicineDetailIds(cmTreatmentGroupMedicineDetailIds);
                                }
                            }
                        }

                    }
                }
            }
            // save therapy planning from case sheet
            if(request.getSaveInvestigationAndTreatmentDTO().getSaveTherapyPlanningDTOs() != null){
                for (SaveTherapyPlanningDTO saveTherapyPlanningDTO : request.getSaveInvestigationAndTreatmentDTO().getSaveTherapyPlanningDTOs()) {
                    //frame therapy master object
                    therapyPlanning = frameTherapyPlanning(request.getSaveInvestigationAndTreatmentDTO(),saveTherapyPlanningDTO, savedCmMasterDetails);
                    if (therapyPlanning != null)
                        //save therapy planning
                        savedTherapyPlanning = therapyPlanningRepository.save(therapyPlanning);
                    saveTreatmentTherapyResponseDTO.setTherapyPlanningId(savedTherapyPlanning.getId());
                    //frame therapy planning medicine types
                    if (saveTherapyPlanningDTO.getSaveTreatmentTherapyMedicineTypeDTOs() != null) {
                        therapyPlanningMedicineTypes = frameTherapyPlanningMedicineTypes(savedTherapyPlanning, saveTherapyPlanningDTO.getSaveTreatmentTherapyMedicineTypeDTOs());
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
                    if (saveTherapyPlanningDTO.getSaveTreatmentTherapyMedicineDTOs() != null) {
                        therapyPlanningMedicines = frameTherapyPlanningMedicines(savedTherapyPlanning, saveTherapyPlanningDTO.getSaveTreatmentTherapyMedicineDTOs());
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
                        resultList = generateTherapyInstances(therapyPlanning, request.getSaveInvestigationAndTreatmentDTO().getClinicId());
                        if (resultList == null) {
                            if (saveTherapyPlanningDTO.getTherapyPlanningId() != null) {
                                String updateTherapyStatus = setUpdateTherapyDates(therapyPlanning, request.getSaveInvestigationAndTreatmentDTO().getClinicId());
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
                    therapyPlannings.add(savedTherapyPlanning);
                }

            }

            // generating order for new case
            if(request.getSaveInvestigationAndTreatmentDTO().isNewOrderStatus() == true && request.getSaveInvestigationAndTreatmentDTO().isDraftStatus() == false){
                // generating the order for given investigation and medicine and therapy
                List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOList = new ArrayList<CreateBillingOrderServiceDTO>();
                List<CreateBillingOrderServiceDTO> investigationServices = new ArrayList<CreateBillingOrderServiceDTO>();
                List<CreateBillingOrderServiceDTO> medicineItems = new ArrayList<CreateBillingOrderServiceDTO>();
                List<CreateBillingOrderServiceDTO> therapyPlannedList = new ArrayList<CreateBillingOrderServiceDTO>();
              if(cmInvestigationDetails.size() > 0 || cmTreatmentMedicineDetails.size() > 0 || therapyPlannings.size() > 0 || savedTherapyPlanningMedicines.size() > 0 ){
                  //frame investigation services for order generation
                  investigationServices = frameInvestigationServices(cmInvestigationDetails);
                  //frame medicines for order generation
                  medicineItems = frameMedicineItems(cmTreatmentMedicineDetails);
                  //frame therapy and therapy medicines for order generation
                  therapyPlannedList = frameAllTherapyPlannedForCase(therapyPlannings);

                  for(CreateBillingOrderServiceDTO billingOrderServiceDTO : investigationServices){
                      createBillingOrderServiceDTOList.add(billingOrderServiceDTO);
                  }
                  for(CreateBillingOrderServiceDTO createBillingOrderServiceDTO : therapyPlannedList){
                      createBillingOrderServiceDTOList.add(createBillingOrderServiceDTO);
                  }
                  for(CreateBillingOrderServiceDTO orderServiceDTO : medicineItems){
                      createBillingOrderServiceDTOList.add(orderServiceDTO);
                  }
                        CreateBillingOrderInputDTO createBillingOrderInputDTO = frameCreateBillingOrderInputDTO(request.getSaveInvestigationAndTreatmentDTO(),
                                createBillingOrderServiceDTOList);

                        //calling case sheet billing order service to generate order for cases
                       JSONObject jsonObject =  generateCaseSheetBillingOrderService.generateOrderForCase(createBillingOrderInputDTO);

                        if(jsonObject != null){
                            createBillingOrderOutputDTO.setBmOrderId(jsonObject.getLong("bmOrderId"));
                            createBillingOrderOutputDTO.setOrderNumber(jsonObject.getString("orderNumber"));
                        }
                        if(createBillingOrderOutputDTO.getOrderNumber() != null && createBillingOrderOutputDTO.getBmOrderId() != null){
                            //update cm investigation with order number
                            CmInvestigation updateCmInvestigation = updateCmInvestigation(createBillingOrderOutputDTO.getOrderNumber(),cmInvestigation.getId());
                            //update cm treatment and CmTreatmentGroupMedicineDetail with bm order id
                            for(CmTreatmentMedicineDetail cmTreatmentMedicineDetail : cmTreatmentMedicineDetails){
                                CmTreatmentMedicineDetail savedCmTreatmentMedicineDetail = cmTreatmentMedicineDetailRepository.findOne(cmTreatmentMedicineDetail.getId());
                                if(savedCmTreatmentMedicineDetail != null)
                                savedCmTreatmentMedicineDetail.setBmOrderId(createBillingOrderOutputDTO.getBmOrderId());
                                List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineDetails = new ArrayList<>();
                                cmTreatmentGroupMedicineDetails = cmTreatmentGroupMedicineDetailRepository.findCmTreatmentGroupByMedicineDetail(cmTreatmentMedicineDetail.getId());
                                if(cmTreatmentGroupMedicineDetails != null){
                                    for(CmTreatmentGroupMedicineDetail cmTreatmentGroupMedicineDetail : cmTreatmentGroupMedicineDetails){
                                        CmTreatmentGroupMedicineDetail savedCmTreatmentGroupMedicineDetail = cmTreatmentGroupMedicineDetailRepository.findOne(cmTreatmentGroupMedicineDetail.getId());
                                        if(savedCmTreatmentGroupMedicineDetail != null)
                                        savedCmTreatmentGroupMedicineDetail.setBmOrderId(createBillingOrderOutputDTO.getBmOrderId());
                                        savedCmTreatmentGroupMedicineDetail = cmTreatmentGroupMedicineDetailRepository.save(savedCmTreatmentGroupMedicineDetail);
                                    }
                                }
                                savedCmTreatmentMedicineDetail = cmTreatmentMedicineDetailRepository.save(savedCmTreatmentMedicineDetail);
                            }
                            //update therapy planning and therapy planning medicine with bm order id
                            for(TherapyPlanning getTherapyPlanning : therapyPlannings){
                                TherapyPlanning findTherapyPlanning = therapyPlanningRepository.findOne(getTherapyPlanning.getId());
                                if(findTherapyPlanning != null)
                                    findTherapyPlanning.setBmOrderId(createBillingOrderOutputDTO.getBmOrderId());
                                List<TherapyPlanningMedicine> getTherapyPlanningMedicines = new ArrayList<>();
                                getTherapyPlanningMedicines = therapyPlanningMedicineRepository.findByTherapyPlanningId(getTherapyPlanning.getId());
                                    if(getTherapyPlanningMedicines != null){
                                        for(TherapyPlanningMedicine therapyPlanningMedicine : getTherapyPlanningMedicines){
                                            TherapyPlanningMedicine savedTherapyPlanningMedicine = therapyPlanningMedicineRepository.findOne(therapyPlanningMedicine.getId());
                                            if(savedTherapyPlanningMedicine != null)
                                                savedTherapyPlanningMedicine.setBmOrderId(createBillingOrderOutputDTO.getBmOrderId());
                                            savedTherapyPlanningMedicine = therapyPlanningMedicineRepository.save(savedTherapyPlanningMedicine);
                                        }
                                    }
                                findTherapyPlanning = therapyPlanningRepository.save(findTherapyPlanning);
                            }
                            CmMasterDetails savedCmMasterDetail = saveCmMasterDetailsOrder(cmMaster, createBillingOrderOutputDTO.getBmOrderId());
                        }

                }
                 //generating the order for update case
                //checking else condition when order is not null and updating the order with edited details in case sheet
            }else if(request.getSaveInvestigationAndTreatmentDTO().isNewOrderStatus() == false && request.getSaveInvestigationAndTreatmentDTO().isDraftStatus() == false){
                            if(request.getSaveInvestigationAndTreatmentDTO().getSaveInvestigationDetailDTOs() != null || request.getSaveInvestigationAndTreatmentDTO().getSaveCaseMedicineDTOs() != null
                                    || request.getSaveInvestigationAndTreatmentDTO().getSaveTherapyPlanningDTOs() != null){

                                UpdateCaseOrderDTO updateCaseOrderDTO = new UpdateCaseOrderDTO();
                                //setting the request to update case order dto to call the update order api
                                updateCaseOrderDTO.setOrderId(request.getSaveInvestigationAndTreatmentDTO().getBmOrderId());
                                updateCaseOrderDTO.setOrderNumber(request.getSaveInvestigationAndTreatmentDTO().getBmOrderNumber());
                                updateCaseOrderDTO.setSaveInvestigationDetailDTOs(request.getSaveInvestigationAndTreatmentDTO().getSaveInvestigationDetailDTOs());
                                updateCaseOrderDTO.setSaveCaseMedicineDTOs(request.getSaveInvestigationAndTreatmentDTO().getSaveCaseMedicineDTOs());
                                updateCaseOrderDTO.setSaveTherapyPlanningDTOs(request.getSaveInvestigationAndTreatmentDTO().getSaveTherapyPlanningDTOs());

                                //calling case sheet billing order service to update order for cases
                                JSONObject jsonObject =  generateCaseSheetBillingOrderService.updateOrderForCase(updateCaseOrderDTO);
                                if(jsonObject != null){
                                    createBillingOrderOutputDTO.setBmOrderId(jsonObject.getLong("orderId"));
                                    createBillingOrderOutputDTO.setOrderNumber(jsonObject.getString("orderNumber"));
                                }
                            }
                    }

            //create response
            response = new SaveInvestigationAndTreatmentDetailsServiceResponse(createBillingOrderOutputDTO.getBmOrderId(),createBillingOrderOutputDTO.getOrderNumber(),
                    saveCmInvestigationResponseDTO,saveMedicineResponseDTO,saveTreatmentTherapyResponseDTO);
            response.setMessage("Case sheet investigation, medicine and therapy treatment saved successfully with order generation");
            log.debug("Case sheet investigation, medicine and therapy treatment saved successfully with order generation");
        } catch (Exception e) {
            response = new SaveInvestigationAndTreatmentDetailsServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save Case sheet investigation, medicine and therapy treatment with order generation");
            response.setMessage(e.getMessage() + " so,Failed to save Case sheet investigation,medicine and therapy treatment with order generation");
        }
        return response;
    }

  /*  //method to save cm master details
    private CmMasterDetails saveCmMasterDetails(CmMaster cmMaster, Long patientAppointmentId) {
        CmMasterDetails cmMasterDetails = new CmMasterDetails();
        try {
            log.debug("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Call to Save CmMasterDetails");
            if(cmMaster.getCaseCreatedDate() != null){
                cmMasterDetails.setCaseCreatedDate(cmMaster.getCaseCreatedDate());
            }else {
                cmMasterDetails.setCaseCreatedDate(LocalDateTime.now());
            }
            if (patientAppointmentId != null) {
                cmMasterDetails.setPatientAppointmentId(patientAppointmentId);
            }
            cmMasterDetails.setCmMaster(cmMaster);
            cmMasterDetails = cmMasterDetailsRepository.save(cmMasterDetails);
        } catch (Exception e) {
           log.error("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Failed to save CmMasterDetails" + e.getMessage());
        }
        return cmMasterDetails;
    }*/

    //method to save cm investigation
    private CmInvestigation saveCmInvestigation(SaveCmInvestigationDetailDTO saveCmInvestigationDetailDTO, CmMasterDetails savedCmMasterDetails) {
        CmInvestigation cmInvestigation = new CmInvestigation();
        CmInvestigation savedCmInvestigation = new CmInvestigation();
        ProvisionalDiagnosisMaster provisionalDiagnosisMaster = new ProvisionalDiagnosisMaster();
        CmAcdMaster cmAcdMaster = new CmAcdMaster();
        CmIcdMaster cmIcdMaster = new CmIcdMaster();
        try {
            log.debug("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Call to frame cm investigation");
            if (savedCmMasterDetails != null) {
                    // setting the user entered values in cm treatment object
                    if(saveCmInvestigationDetailDTO.getCmInvestigationId() == null) {
                        cmInvestigation.setCmMasterDetails(savedCmMasterDetails);
                        cmInvestigation.setCreatedDate(savedCmMasterDetails.getCaseCreatedDate());
                        cmInvestigation.setFinalDiagnosis(saveCmInvestigationDetailDTO.isFinalDiagnosis());
                        cmInvestigation.setDoctorSummary(saveCmInvestigationDetailDTO.getDoctorSummary());
                        //  cmInvestigation.setLabOrderNumber(); saved during order generation
                        if(saveCmInvestigationDetailDTO.getProvisionalDiagnosisMasterId() != null){
                            provisionalDiagnosisMaster = provisionalDiagnosisMasterRepository.findOne(saveCmInvestigationDetailDTO.getProvisionalDiagnosisMasterId());
                            if(provisionalDiagnosisMaster != null)
                                cmInvestigation.setProvisionalDiagnosisMaster(provisionalDiagnosisMaster);
                        }
                        if(saveCmInvestigationDetailDTO.getAcdMasterId() != null){
                            cmAcdMaster = cmAcdMasterRepository.findOne(saveCmInvestigationDetailDTO.getAcdMasterId());
                            if(cmAcdMaster != null)
                                cmInvestigation.setCmAcdMaster(cmAcdMaster);
                        }
                        if(saveCmInvestigationDetailDTO.getIcdMasterId() != null){
                            cmIcdMaster = cmIcdMasterRepository.findOne(saveCmInvestigationDetailDTO.getIcdMasterId());
                            if(cmIcdMaster != null)
                                cmInvestigation.setCmIcdMaster(cmIcdMaster);
                        }
                    }
                savedCmInvestigation = cmInvestigationRepository.save(cmInvestigation);
            }
        } catch (Exception e) {
            log.error("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Failed to frame cm investigation" + e.getMessage());
        }
        return savedCmInvestigation;
    }

    //method to save  cm investigation details
    private List<CmInvestigationDetail> saveCmInvestigationDetail(CmInvestigation cmInvestigation, List<SaveInvestigationDetailDTO> saveInvestigationDetailDTOs, Long clinicId) {
        List<CmInvestigationDetail> cmInvestigationDetails = new ArrayList<>();
        List<CmInvestigationDetail> savedCmInvestigationDetails = new ArrayList<>();
        try {
            log.debug("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Call to frame and save cm investigation details");
            for(SaveInvestigationDetailDTO saveInvestigationDetailDTO : saveInvestigationDetailDTOs){
                CmInvestigationDetail cmInvestigationDetail = new CmInvestigationDetail();
                UserDetail userDetail = userDetailRepository.findDoctorByUserId(saveInvestigationDetailDTO.getUserId(), clinicId);
                cmInvestigationDetail.setCmInvestigation(cmInvestigation);
                cmInvestigationDetail.setServiceCatalogueId(saveInvestigationDetailDTO.getServiceCatalogueId());
                if(userDetail != null) {
                    cmInvestigationDetail.setAddedBy(userDetail.getId());//added by
                }
                cmInvestigationDetail.setAddedOn(saveInvestigationDetailDTO.getAddedOn());
                cmInvestigationDetail.setPerformedBy(saveInvestigationDetailDTO.getUserId());//performedBy
                cmInvestigationDetail.setTestedOn(saveInvestigationDetailDTO.getTestedOn());
                cmInvestigationDetail.setInvestigationNotes(saveInvestigationDetailDTO.getInvestigationNotes());
                cmInvestigationDetails.add(cmInvestigationDetail);
            }
            //save cm investigation details
            savedCmInvestigationDetails = cmInvestigationDetailRepository.save(cmInvestigationDetails);
        } catch (Exception e) {
            log.error("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Failed to frame and save Cm investigation details" + e.getMessage());
        }
        return savedCmInvestigationDetails;
    }

    //method to save cm treatment
    private CmTreatment saveCmTreatment(SaveInvestigationAndTreatmentDTO saveInvestigationAndTreatmentDTO, SaveCmMedicineTreatmentDTO saveCmMedicineTreatmentDTO,
                                        CmMasterDetails savedCmMasterDetails) {
        CmTreatment savedCmTreatment = new CmTreatment();
        CmTreatment cmTreatment = new CmTreatment();
        try {
            log.debug("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Call to frame and save CmTreatment details");
            if(savedCmMasterDetails != null)
                // setting the user entered values in cm treatment object
                if(saveCmMedicineTreatmentDTO.getCmTreatmentId() == null) {
                    cmTreatment.setCmMasterDetails(savedCmMasterDetails);
                    cmTreatment.setGivenDate(savedCmMasterDetails.getCaseCreatedDate());
                    if(saveCmMedicineTreatmentDTO.getPathyaPathyaDTO() != null) {
                        cmTreatment.setPathyaPathya(saveCmMedicineTreatmentDTO.getPathyaPathyaDTO().getPathyaPathya().trim());
                    }
                    if(saveCmMedicineTreatmentDTO.getPathyaPathyaDTO() != null) {
                        cmTreatment.setTake(saveCmMedicineTreatmentDTO.getPathyaPathyaDTO().getTake().trim());
                    }
                    if(saveCmMedicineTreatmentDTO.getPathyaPathyaDTO() != null) {
                        cmTreatment.setTakeMore(saveCmMedicineTreatmentDTO.getPathyaPathyaDTO().getTakeMore());
                    }
                    if(saveCmMedicineTreatmentDTO.getTreatmentPrincipleDTO() != null) {
                        cmTreatment.setShamanam(saveCmMedicineTreatmentDTO.getTreatmentPrincipleDTO().isShamanam());
                        cmTreatment.setShodhanam(saveCmMedicineTreatmentDTO.getTreatmentPrincipleDTO().isShodhanam());
                    }

                    savedCmTreatment = cmTreatmentRepository.save(cmTreatment);
                }
        } catch (Exception e) {
            log.error("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Failed to frame and save cm treatment object" + e.getMessage());
        }
     return savedCmTreatment;
    }

    //method to frame and save cm treatment medicine detail along with group medicines
    private List<CmTreatmentMedicineDetail> saveCmTreatmentMedicineDetails(CmTreatment cmTreatment, List<SaveCaseMedicineDTO> saveCaseMedicineDTOs) {
        CmTreatmentMedicineDetail cmTreatmentMedicineDetail = null;
        CmTreatmentMedicineDetail savedCmTreatmentMedicineDetail = null;
        CmTreatmentGroupMedicineDetail cmTreatmentGroupMedicineDetail = null;
        CmTreatmentGroupMedicineDetail savedCmTreatmentGroupMedicineDetail = null;
        List<CmTreatmentMedicineDetail> cmTreatmentMedicineDetails = new ArrayList<>();
        List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineDetails = new ArrayList<>();
        try {
            log.debug("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Call to Frame cm treatment medicine detail");
            for (SaveCaseMedicineDTO saveCaseMedicineDTO : saveCaseMedicineDTOs) {
                cmTreatmentMedicineDetail = new CmTreatmentMedicineDetail();
                savedCmTreatmentMedicineDetail = new CmTreatmentMedicineDetail();
                if (saveCaseMedicineDTO.getCmTreatmentMedicineDetailId() == null) {
                    cmTreatmentMedicineDetail = frameCmTreatmentMedicineDetailObj(cmTreatment, saveCaseMedicineDTO, cmTreatmentMedicineDetail);
                } else {
                    cmTreatmentMedicineDetail = cmTreatmentMedicineDetailRepository.findOne(saveCaseMedicineDTO.getCmTreatmentMedicineDetailId());
                    cmTreatmentMedicineDetail = frameCmTreatmentMedicineDetailObj(cmTreatment, saveCaseMedicineDTO, cmTreatmentMedicineDetail);
                }
                //saving the cm treatment medicine detail
                savedCmTreatmentMedicineDetail = cmTreatmentMedicineDetailRepository.save(cmTreatmentMedicineDetail);

                //saving group medicine if exists
                if(saveCaseMedicineDTO.getGroupIndividualMedicineDTOs() != null){
                   // cmTreatmentGroupMedicineDetail = new CmTreatmentGroupMedicineDetail();
                    savedCmTreatmentGroupMedicineDetail = new CmTreatmentGroupMedicineDetail();
                    for(GroupIndividualMedicineDTO groupIndividualMedicineDTO : saveCaseMedicineDTO.getGroupIndividualMedicineDTOs()){
                        cmTreatmentGroupMedicineDetail = new CmTreatmentGroupMedicineDetail();
                        // checking whether the group medicine is exist or not, if exist frame the object and save
                        if(groupIndividualMedicineDTO.getCmTreatmentGroupMedicineDetailId() != null){
                            cmTreatmentGroupMedicineDetail = cmTreatmentGroupMedicineDetailRepository.findOne(groupIndividualMedicineDTO.getCmTreatmentGroupMedicineDetailId());
                            cmTreatmentGroupMedicineDetail = frameCmTreatmentGroupMedicineDetail(cmTreatmentMedicineDetail,groupIndividualMedicineDTO, cmTreatmentGroupMedicineDetail);
                        }else {
                            cmTreatmentGroupMedicineDetail = frameCmTreatmentGroupMedicineDetail(cmTreatmentMedicineDetail,groupIndividualMedicineDTO, cmTreatmentGroupMedicineDetail);
                        }
                        //saving the cm treatment medicine group detail
                        savedCmTreatmentGroupMedicineDetail = cmTreatmentGroupMedicineDetailRepository.save(cmTreatmentGroupMedicineDetail);
                        cmTreatmentGroupMedicineDetails.add(savedCmTreatmentGroupMedicineDetail);
                    }

                }
                cmTreatmentMedicineDetails.add(savedCmTreatmentMedicineDetail);
            }
        } catch (Exception e) {
            log.error("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Failed to frame cm treatment medicine detail" + e.getMessage());
        }
        return cmTreatmentMedicineDetails;
    }


    // method to frame cm treatment medicine detail obj
    private CmTreatmentMedicineDetail frameCmTreatmentMedicineDetailObj(CmTreatment savedCmTreatment, SaveCaseMedicineDTO saveCaseMedicineDTO,
                                                                        CmTreatmentMedicineDetail cmTreatmentMedicineDetail) {
        CmDosageValueMapping cmDosageValueMapping = new CmDosageValueMapping();
        RouteOfAdministration routeOfAdministration = new RouteOfAdministration();
        LookupValue lookupValue = new LookupValue();
        try {
            log.debug("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Call to frame cm treatment detail obj");
            if(savedCmTreatment != null)
                cmTreatmentMedicineDetail.setCmTreatment(savedCmTreatment);
            cmTreatmentMedicineDetail.setDosageQuantity(saveCaseMedicineDTO.getDosageQuantity());
            cmTreatmentMedicineDetail.setTotalQuantity(saveCaseMedicineDTO.getTotalQuantity());
            cmTreatmentMedicineDetail.setDosageTime(saveCaseMedicineDTO.getDosageTimeDTOs().toString());
            cmTreatmentMedicineDetail.setInstructions(saveCaseMedicineDTO.getInstructions());
            cmTreatmentMedicineDetail.setNumberOfDays(saveCaseMedicineDTO.getNumberOfDays());
            cmTreatmentMedicineDetail.setProductCatalogueCommonDetailId(saveCaseMedicineDTO.getProductCatalogueCommonDetailId());
            cmTreatmentMedicineDetail.setUomMasterId(saveCaseMedicineDTO.getUomMasterId());
            if (saveCaseMedicineDTO.getCmDosageValueMappingId() != null) {
                cmDosageValueMapping = cmDosageValueMappingRepository.findOne(saveCaseMedicineDTO.getCmDosageValueMappingId());
                cmTreatmentMedicineDetail.setCmDosageValueMapping(cmDosageValueMapping);
            }
            lookupValue = lookupValueRepository.findOne(saveCaseMedicineDTO.getMedicineGroupTypeId());
            cmTreatmentMedicineDetail.setMedicineGroupType(lookupValue);
            if (saveCaseMedicineDTO.getRouteOfAdministrationId() != null) {
                routeOfAdministration = routeOfAdministrationRepository.findOne(saveCaseMedicineDTO.getRouteOfAdministrationId());
                cmTreatmentMedicineDetail.setRouteOfAdministration(routeOfAdministration);
            }

        } catch (Exception e) {
            log.error("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Failed to frame cm treatment medicine detail obj" + e.getMessage());
        }
        return cmTreatmentMedicineDetail;
    }

    //method to frame cm treatment group medicine detail
    private CmTreatmentGroupMedicineDetail frameCmTreatmentGroupMedicineDetail(CmTreatmentMedicineDetail cmTreatmentMedicineDetail,
                                                                               GroupIndividualMedicineDTO groupIndividualMedicineDTO, CmTreatmentGroupMedicineDetail cmTreatmentGroupMedicineDetail) {
        try {
            log.debug("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Call to frame cm treatment group detail obj");
            cmTreatmentGroupMedicineDetail.setCmTreatmentMedicineList(cmTreatmentMedicineDetail);
            cmTreatmentGroupMedicineDetail.setTotalQuantity(groupIndividualMedicineDTO.getTotalQuantity());
            cmTreatmentGroupMedicineDetail.setComposition(groupIndividualMedicineDTO.getComposition());
            cmTreatmentGroupMedicineDetail.setInstructions(groupIndividualMedicineDTO.getInstructions());
            cmTreatmentGroupMedicineDetail.setProductCatalogueCommonDetailId(groupIndividualMedicineDTO.getProductCatalogueCommonDetailId());
            cmTreatmentGroupMedicineDetail.setStrength(groupIndividualMedicineDTO.getStrength());
            cmTreatmentGroupMedicineDetail.setUomMasterId(groupIndividualMedicineDTO.getUomMasterId());
        } catch (Exception e) {
            log.error("SaveInvestigationAndTreatmentDetailsServiceImpl ----> Failed to frame cm treatment group medicine detail obj" + e.getMessage());
        }
        return cmTreatmentGroupMedicineDetail;
    }

    // method to frame therapy planning
    private TherapyPlanning frameTherapyPlanning(SaveInvestigationAndTreatmentDTO saveInvestigationAndTreatmentDTO, SaveTherapyPlanningDTO saveTreatmentTherapyDTO, CmMasterDetails savedCmMasterDetails) {
        TherapyPlanning therapyPlanning = new TherapyPlanning();
        Patient patient = new Patient();
        CmMaster cmMaster = new CmMaster();
        try {
            log.debug("framing therapy planning");
            //get patient by patient id
            patient = patientRepository.findOne(saveInvestigationAndTreatmentDTO.getPatientId());

            //get case master by case id
            cmMaster = cmMasterRepository.findOne(saveInvestigationAndTreatmentDTO.getCmMasterId());

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
    private TherapyPlanning frameTherapyPlanningObj(TherapyPlanning therapyPlanning, SaveTherapyPlanningDTO saveTreatmentTherapyDTO) {
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
            therapyPlanningMedicine.setTotalQuantity(saveTreatmentTherapyMedicineDTO.getTotalQuantity());
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

    //method to frame investigation services for order creation
    private List<CreateBillingOrderServiceDTO> frameInvestigationServices(List<CmInvestigationDetail> cmInvestigationDetails) {
        List<CreateBillingOrderServiceDTO> serviceCatalogues = new ArrayList<CreateBillingOrderServiceDTO>();
        try {
            log.debug("SaveTherapyTreatmentServiceImpl -----> frameInvestigationServices");
            for(CmInvestigationDetail cmInvestigationDetail : cmInvestigationDetails){
                CreateBillingOrderServiceDTO createBillingOrderServiceDTO = new CreateBillingOrderServiceDTO();
                createBillingOrderServiceDTO.setServiceId(cmInvestigationDetail.getServiceCatalogueId());
                createBillingOrderServiceDTO.setType("Service");
                createBillingOrderServiceDTO.setQuantity(1);
                serviceCatalogues.add(createBillingOrderServiceDTO);
            }
        } catch (Exception e) {
            log.error("SaveTherapyTreatmentServiceImpl -----> frameInvestigationServices" + e.getMessage());
        }
        return serviceCatalogues;
    }

    //method to frame cm medicines and group medicines
    private List<CreateBillingOrderServiceDTO> frameMedicineItems(List<CmTreatmentMedicineDetail> cmTreatmentMedicineDetails) {
        List<CreateBillingOrderServiceDTO> medicineItems = new ArrayList<CreateBillingOrderServiceDTO>();
        try {
            log.debug("SaveTherapyTreatmentServiceImpl -----> frameMedicineItems");
            for (CmTreatmentMedicineDetail cmTreatmentMedicineDetail : cmTreatmentMedicineDetails) {
                CreateBillingOrderServiceDTO serviceDTO = new CreateBillingOrderServiceDTO();
                serviceDTO.setType("Product");
                serviceDTO.setServiceId(cmTreatmentMedicineDetail.getProductCatalogueCommonDetailId());
                serviceDTO.setQuantity(Integer.parseInt(cmTreatmentMedicineDetail.getTotalQuantity()));
                medicineItems.add(serviceDTO);
                List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineDetails = new ArrayList<>();
                cmTreatmentGroupMedicineDetails = cmTreatmentGroupMedicineDetailRepository.findCmTreatmentGroupByMedicineDetail(cmTreatmentMedicineDetail.getId());
                if(cmTreatmentGroupMedicineDetails != null){
                    for(CmTreatmentGroupMedicineDetail cmTreatmentGroupMedicineDetail : cmTreatmentGroupMedicineDetails){
                        CreateBillingOrderServiceDTO serviceGroupDTO = new CreateBillingOrderServiceDTO();
                        serviceGroupDTO.setType("Product");
                        serviceGroupDTO.setServiceId(cmTreatmentGroupMedicineDetail.getProductCatalogueCommonDetailId());
                        serviceGroupDTO.setQuantity(Integer.parseInt(cmTreatmentGroupMedicineDetail.getTotalQuantity()));
                        medicineItems.add(serviceGroupDTO);
                    }
                }
            }
        } catch (NumberFormatException e) {
            log.error("SaveTherapyTreatmentServiceImpl -----> frameMedicineItems" + e.getMessage());
        }
        return medicineItems;
    }

    //method to frame therapy services and therapy medicines
    private List<CreateBillingOrderServiceDTO> frameAllTherapyPlannedForCase(List<TherapyPlanning> therapyPlannings) {

        List<CreateBillingOrderServiceDTO> serviceCatalogues = new ArrayList<CreateBillingOrderServiceDTO>();
        try {
            log.debug("SaveTherapyTreatmentServiceImpl -----> frameAllTherapyPlannedForCase");
            for (TherapyPlanning therapyPlanning : therapyPlannings) {
                CreateBillingOrderServiceDTO billingOrderServiceDTO = new CreateBillingOrderServiceDTO();
                billingOrderServiceDTO.setType("Service");
                billingOrderServiceDTO.setServiceId(therapyPlanning.getServiceCatalogueId());
                billingOrderServiceDTO.setQuantity((int) therapyPlanning.getNumberOfDays());
                serviceCatalogues.add(billingOrderServiceDTO);
                List<TherapyPlanningMedicine> therapyPlanningMedicines = new ArrayList<>();
                therapyPlanningMedicines = therapyPlanningMedicineRepository.findByTherapyPlanningId(therapyPlanning.getId());
                if(therapyPlanningMedicines != null){
                    for(TherapyPlanningMedicine therapyPlanningMedicine : therapyPlanningMedicines){
                        CreateBillingOrderServiceDTO billingOrderMedicineServiceDTO = new CreateBillingOrderServiceDTO();
                        billingOrderMedicineServiceDTO.setType("Product");
                        billingOrderMedicineServiceDTO.setServiceId(therapyPlanningMedicine.getProductCatalogueCommonDetailId());
                        billingOrderMedicineServiceDTO.setQuantity(Integer.parseInt(therapyPlanningMedicine.getTotalQuantity()));
                        billingOrderMedicineServiceDTO.setServiceCatalogueId(therapyPlanning.getServiceCatalogueId());
                        serviceCatalogues.add(billingOrderMedicineServiceDTO);
                    }
                }
            }
        } catch (Exception e) {
            log.error("SaveTherapyTreatmentServiceImpl -----> frameAllTherapyPlannedForCase" + e.getMessage());
        }
        return serviceCatalogues;
    }

    //method to frame create billing order input dto
    private CreateBillingOrderInputDTO frameCreateBillingOrderInputDTO(SaveInvestigationAndTreatmentDTO saveInvestigationAndTreatmentDTO,
                                                                       List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOList) {
        CreateBillingOrderInputDTO createBillingOrderInputDTO = null;
        try {
            log.debug("SaveTherapyTreatmentServiceImpl -----> frameCreateBillingOrderInputDTO");
            createBillingOrderInputDTO = new CreateBillingOrderInputDTO();
            createBillingOrderInputDTO.setClinicId(saveInvestigationAndTreatmentDTO.getClinicId());
            createBillingOrderInputDTO.setClinicLocationId(saveInvestigationAndTreatmentDTO.getClinicLocationId());
            createBillingOrderInputDTO.setPatientId(saveInvestigationAndTreatmentDTO.getPatientId());
            createBillingOrderInputDTO.setOrderBy(saveInvestigationAndTreatmentDTO.getUserId());
            createBillingOrderInputDTO.setDepartmentId(saveInvestigationAndTreatmentDTO.getUserDepartmentId());
            createBillingOrderInputDTO.setPatientAppointmentId(saveInvestigationAndTreatmentDTO.getPatientAppointmentId());
            createBillingOrderInputDTO.setIpDcAdmissionNumber(saveInvestigationAndTreatmentDTO.getIpDcAdmissionNumber());
            createBillingOrderInputDTO.setIpAdmissionId(saveInvestigationAndTreatmentDTO.getIpAdmissionId());
            createBillingOrderInputDTO.setVisitTypeMasterId(saveInvestigationAndTreatmentDTO.getVisitTypeMasterId());
            createBillingOrderInputDTO.setType("CASE");
            createBillingOrderInputDTO.setCreateBillingOrderServiceDTOList(createBillingOrderServiceDTOList);
        } catch (Exception e) {
            log.error("SaveTherapyTreatmentServiceImpl -----> frameCreateBillingOrderInputDTO" + e.getMessage());
        }
        return createBillingOrderInputDTO;
    }

    //method to update the cm master detail with order id
    private CmMasterDetails saveCmMasterDetailsOrder(CmMaster cmMaster, Long bmOrderId) {
        CmMasterDetails saveCmMasterDetails = new CmMasterDetails();
        List<CmMasterDetails> cmMasterDetails = new ArrayList<CmMasterDetails>();
        try {
            log.debug("SaveTherapyTreatmentServiceImpl == > saveCmMasterDetailsOrder");
            cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(cmMaster.getId());
            int n = cmMasterDetails.size();
            CmMasterDetails conditionOnAdmissionGeneralExamination = (CmMasterDetails) cmMasterDetails.get(0);
            CmMasterDetails cmMasterDetail = (CmMasterDetails) cmMasterDetails.get(n - 1);
            if (bmOrderId != 0) {
                cmMasterDetail.setBmOrderId(bmOrderId);
            }
            saveCmMasterDetails = cmMasterDetailsRepository.save(cmMasterDetail);
        } catch (Exception e) {
            log.error("SaveTherapyTreatmentServiceImpl == > saveCmMasterDetailsOrder failed == > " + e);
        }
        return saveCmMasterDetails;
    }

    //method to update cm investigation
    private CmInvestigation updateCmInvestigation(String orderNumber, Long cmInvestigationId) {
        CmInvestigation cmInvestigation = new CmInvestigation();
        CmInvestigation saveCmInvestigation = new CmInvestigation();
        try {
            log.debug("SaveTherapyTreatmentServiceImpl == > updateCmInvestigation");
            cmInvestigation = cmInvestigationRepository.findOne(cmInvestigationId);
            if(cmInvestigation != null)
                cmInvestigation.setLabOrderNumber(orderNumber);
                saveCmInvestigation = cmInvestigationRepository.save(cmInvestigation);
        } catch (Exception e) {
            log.error("SaveTherapyTreatmentServiceImpl == > updateCmInvestigation failed == > " + e);
        }
        return saveCmInvestigation;
    }

}
