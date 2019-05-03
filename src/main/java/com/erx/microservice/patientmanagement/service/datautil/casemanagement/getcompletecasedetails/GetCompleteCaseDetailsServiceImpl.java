package com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails;

/*
 * created by Latha on 20-08-2018
 * */

import com.erx.microservice.patientmanagement.domain.UserDetail;
import com.erx.microservice.patientmanagement.domain.casemanagement.*;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanning;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanningMedicine;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanningMedicineType;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.UserDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.*;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyPlanningMedicineRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyPlanningMedicineTypeRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyPlanningRepository;
import com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationdetailsbycaseid.GetInvestigationDetailsByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.casemanagement.getmedicinetreatmentbycaseid.GetMedicineTreatmentByCaseIdServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.*;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentDetailsDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentMedicineDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentMedicineTypeDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentResponseDTO;
import com.erx.microservice.patientmanagement.service.mapper.casemanagement.CmMasterComplaintsMapper;
import com.erx.microservice.patientmanagement.service.mapper.casemanagement.CmMasterDetailsMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("getCompleteCaseDetailsService")
public class GetCompleteCaseDetailsServiceImpl implements GetCompleteCaseDetailsService{

    private final Logger log = LoggerFactory.getLogger(GetCompleteCaseDetailsServiceImpl.class);

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private CmMasterComplaintRepository cmMasterComplaintRepository;

    @Autowired
    private CmMasterComplaintsMapper cmMasterComplaintsMapper;

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Autowired
    private CmMasterDetailsMapper cmMasterDetailsMapper;

    @Autowired
    private CmTreatmentRepository cmTreatmentRepository;

    @Autowired
    private CmTreatmentMedicineDetailRepository cmTreatmentMedicineDetailRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Autowired
    private CmTreatmentGroupMedicineDetailRepository cmTreatmentGroupMedicineDetailRepository;

    @Autowired
    private TherapyPlanningRepository therapyPlanningRepository;

    @Autowired
    private TherapyPlanningMedicineRepository therapyPlanningMedicineRepository;

    @Autowired
    private TherapyPlanningMedicineTypeRepository therapyPlanningMedicineTypeRepository;

    @Autowired
    private CmInvestigationRepository cmInvestigationRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private CmPersonalHistoryRepository cmPersonalHistoryRepository;

    @Autowired
    private CmExamntnDetailRepository cmExamntnDetailRepository;

    @Autowired
    private CmExamntnDashavidhaRepository cmExamntnDashavidhaRepository;

    @Autowired
    private CmExamntnAsthaVidhaPareekshaRepository  cmExamntnAsthaVidhaPareekshaRepository;

    @Autowired
    private CmExamntnGeneralRepository cmExamntnGeneralRepository;

    @Autowired
    private CmExamntnSarvaSrotoPareekshaRepository cmExamntnSarvaSrotoPareekshaRepository;

    @Autowired
    private CmExamntnSampraptiGhatakasRepository cmExamntnSampraptiGhatakasRepository;

    @Autowired
    private CmInvestigationDetailRepository cmInvestigationDetailRepository;

    //get complaints tab based on caseId
    public GetComplaintsDTO getComplaints(Long caseId) {
        CmMaster cmMaster = new CmMaster();
        GetComplaintsDTO getComplaintsDTO = new GetComplaintsDTO();
        List<CmMasterComplaintDTO> cmMasterComplaintDTO = new ArrayList<>();
        List<CmMasterComplaint> cmMasterComplaint = new ArrayList<>();
        List<CmMasterDetailsDTO> cmMasterDetailsDTOs = new ArrayList<>();
        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        try {
            log.debug("Call to retrieve Complaints details");
            // retrieve case details based on case id
            cmMaster = cmMasterRepository.findOne(caseId);
            if(cmMaster != null)
                getComplaintsDTO.setPatientId(cmMaster.getPatient().getId());
                getComplaintsDTO.setCaseId(cmMaster.getId());
                getComplaintsDTO.setCaseNumber(cmMaster.getClinicCaseNumber());
                getComplaintsDTO.setChiefComplaints(cmMaster.getChiefComplaint());
            // retrieve case complaints based on case id
            cmMasterComplaint = cmMasterComplaintRepository.getCmMasterComplaintByCmMaster(cmMaster.getId());
            //convert the cmMaster complaints object into DTO
                if(cmMasterComplaint != null){
                    cmMasterComplaintDTO = cmMasterComplaintsMapper.cmMasterComplaintsToCmMasterComplaintDTOs(cmMasterComplaint);
                    getComplaintsDTO.setCmMasterComplaintDTOs(cmMasterComplaintDTO);
                }
            // retrieve case master details based on case id
            cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(cmMaster.getId());
            //convert the cmMaster details object into DTO
                if(cmMasterDetails != null){
                   // cmMasterDetailsDTOs = cmMasterDetailsMapper.cmMasterDetailsToCmMasterDetailsDTOs(cmMasterDetails);
                    for(CmMasterDetails cmMasterDetail : cmMasterDetails){
                        CmMasterDetailsDTO cmMasterDetailsDTO = new CmMasterDetailsDTO();
                        cmMasterDetailsDTO.setId(cmMasterDetail.getId());
                        cmMasterDetailsDTO.setDoctorNote(cmMasterDetail.getDoctorNote());
                        cmMasterDetailsDTO.setBmOrderId(cmMasterDetail.getBmOrderId());
                        JSONObject jsonObject = serviceGateway.getOrderDetailsByOrderId(cmMasterDetail.getBmOrderId());
                        if(jsonObject != null){
                            JSONObject billingOrderDTO = jsonObject.getJSONObject("billingOrderDTO");
                            cmMasterDetailsDTO.setBmOrderNumber(billingOrderDTO.getString("orderId"));
                        }
                        cmMasterDetailsDTO.setCaseCreatedDate(cmMasterDetail.getCaseCreatedDate());
                        cmMasterDetailsDTO.setPatientAppointmentId(cmMasterDetail.getPatientAppointmentId());
                        cmMasterDetailsDTOs.add(cmMasterDetailsDTO);
                    }
                    getComplaintsDTO.setCmMasterDetailsDTOs(cmMasterDetailsDTOs);
                }
            log.debug("Complaints details retrieved Successfully");
        } catch (Exception e) {
            log.error("Failed to retrieve complaints details:" + e.getMessage());
        }
        return getComplaintsDTO;
    }

    // method to get treatment medicines by case id
    @Override
    public List<CmMedicineTreatmentResponseDTO> getTreatmentMedicines(Long caseId) {
        List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs = null;
        List<CmTreatmentMedicineDetail> cmTreatmentMedicineDetails = new ArrayList<>();
        List<CmTreatment> cmTreatments = new ArrayList<>();
        List<CmTreatmentGroupDTO> cmTreatmentGroupDTOs = new ArrayList<>();
        List<CmIndividualMedicineTreatmentDTO> cmIndividualMedicineTreatmentDTOs = new ArrayList<>();
        long cmTreat = 0;

        try {
            log.debug("Call to get medicine treatment by case id");
            //find cm treatment details by case id
            cmTreatments = cmTreatmentRepository.findTreatmentByCmMasterId(caseId);
            if(cmTreatments != null)
                for(CmTreatment cmTreatment : cmTreatments){
                    if(cmTreat != cmTreatment.getId()) {
                        //find cm treatment medicine details by cm treatment by id
                        cmTreatmentMedicineDetails = cmTreatmentMedicineDetailRepository.findCmTreatmentMedicineByCmTreatment(cmTreatment.getId());
                        if(cmTreatmentMedicineDetails.size() > 0 ) {
                            if (cmTreatmentMedicineDetails != null) {
                                cmMedicineTreatmentResponseDTOs = new ArrayList<>();
                                CmMedicineTreatmentResponseDTO cmMedicineTreatmentResponseDTO = new CmMedicineTreatmentResponseDTO();
                                cmMedicineTreatmentResponseDTO.setMedicineTreatmentGivenDate(cmTreatment.getGivenDate());
                                //setting individual medicines
                                // cmIndividualMedicineTreatmentDTOs = frameCmTreatmentMedicine(cmTreatmentMedicineDetails);
                                List<CmTreatmentGroupMedicinesDTO> cmTreatmentGroupMedicinesDTOs = new ArrayList<>();
                                List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineDetails = new ArrayList<>();
                                CmIndividualMedicineTreatmentDTO cmIndividualMedicineTreatmentDTO = new CmIndividualMedicineTreatmentDTO();
                                for(CmTreatmentMedicineDetail cmTreatmentMedicineDetail : cmTreatmentMedicineDetails){
                                    cmTreatmentGroupMedicineDetails = cmTreatmentGroupMedicineDetailRepository.findCmTreatmentGroupByMedicineDetail(cmTreatmentMedicineDetail.getId());
                                    if(cmTreatmentGroupMedicineDetails.size() <= 0) {
                                        cmIndividualMedicineTreatmentDTO = frameCmTreatmentMedicineDTO(cmTreatmentMedicineDetail);
                                        cmIndividualMedicineTreatmentDTOs.add(cmIndividualMedicineTreatmentDTO);
                                    }else{
                                        // frame cm treatment group medicines
                                        CmTreatmentGroupDTO cmTreatmentGroupDTO = new CmTreatmentGroupDTO();
                                        // CmIndividualMedicineTreatmentDTO cmIndividualMedicineTreatmentDTO = new CmIndividualMedicineTreatmentDTO();
                                        cmIndividualMedicineTreatmentDTO = frameCmTreatmentMedicineDTO(cmTreatmentMedicineDetail);
                                        //setting the cm individual medicine to cm treatment group
                                        cmTreatmentGroupDTO.setCmIndividualMedicineGroupInfoDTO(cmIndividualMedicineTreatmentDTO);
                                        cmTreatmentGroupMedicinesDTOs = frameCmTreatmentGroupMedicines(cmTreatmentMedicineDetail.getId());
                                        cmTreatmentGroupDTO.setCmTreatmentGroupMedicinesDTOs(cmTreatmentGroupMedicinesDTOs);
                                        cmTreatmentGroupDTOs.add(cmTreatmentGroupDTO);
                                    }
                                }
                                if (cmIndividualMedicineTreatmentDTOs != null) {
                                    cmMedicineTreatmentResponseDTO.setCmIndividualMedicineTreatmentDTOs(cmIndividualMedicineTreatmentDTOs);
                                }
                                // frame cm treatment group medicines
                                // cmTreatmentGroupDTOs = frameCmTreatmentGroupMedicine(cmTreatmentMedicineDetails);
                                // List<CmTreatmentGroupMedicinesDTO> cmTreatmentGroupMedicinesDTOs = new ArrayList<>();
                                /*for (CmTreatmentMedicineDetail cmTreatmentMedicineDetail : cmTreatmentMedicineDetails) {
                                    CmTreatmentGroupDTO cmTreatmentGroupDTO = new CmTreatmentGroupDTO();
                                    CmIndividualMedicineTreatmentDTO cmIndividualMedicineTreatmentDTO = new CmIndividualMedicineTreatmentDTO();
                                    cmIndividualMedicineTreatmentDTO = frameCmTreatmentMedicineDTO(cmTreatmentMedicineDetail);
                                    //setting the cm individual medicine to cm treatment group
                                    cmTreatmentGroupDTO.setCmIndividualMedicineGroupInfoDTO(cmIndividualMedicineTreatmentDTO);
                                    cmTreatmentGroupMedicinesDTOs = frameCmTreatmentGroupMedicines(cmTreatmentMedicineDetail.getId());
                                    cmTreatmentGroupDTO.setCmTreatmentGroupMedicinesDTOs(cmTreatmentGroupMedicinesDTOs);
                                    cmTreatmentGroupDTOs.add(cmTreatmentGroupDTO);
                                }*/

                                if (cmTreatmentGroupDTOs != null) {
                                    cmMedicineTreatmentResponseDTO.setCmTreatmentGroupDTOs(cmTreatmentGroupDTOs);
                                }
                                cmMedicineTreatmentResponseDTOs.add(cmMedicineTreatmentResponseDTO);
                            }
                        }
                    }
                    cmTreat = cmTreatment.getId();
                }
            log.debug("TreatmentMedicine details retrieved Successfully");
        } catch (Exception e) {
            log.error("Failed to retrieve TreatmentMedicine details:" + e.getMessage());
        }
        return cmMedicineTreatmentResponseDTOs;
    }

    // to get view patient pdf details
    @Override
    public List<CmMedicineTreatmentResponseDTO> getPatientTreatmentMedicines(Long caseId) {
        List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs = null;
        List<CmTreatmentMedicineDetail> cmTreatmentMedicineDetails = new ArrayList<>();
        List<CmTreatment> cmTreatments = new ArrayList<>();
        List<CmTreatmentGroupDTO> cmTreatmentGroupDTOs = new ArrayList<>();
        List<CmIndividualMedicineTreatmentDTO> cmIndividualMedicineTreatmentDTOs = new ArrayList<>();
        long cmTreat = 0;

        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        long cmMasterDetailsId = (long) 0;
        List<Integer> listOfIds = new ArrayList<Integer>();

        try {
            log.debug("Call to get medicine treatment by case id");

            if(caseId != null)
                cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(caseId);
            if(cmMasterDetails != null)
                for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                    cmMasterDetailsId = cmMasterDetailsTemp.getId();
                    Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                    listOfIds.add(cmMasterDetailsIdINT);
                }
            Integer latestId = Collections.max(listOfIds);

            //find cm treatment details by cm master detail id
            cmTreatments = cmTreatmentRepository.findTreatmentByCmMasterDetailsId(Long.valueOf(latestId));
            if(cmTreatments != null)
                for(CmTreatment cmTreatment : cmTreatments){
                    if(cmTreat != cmTreatment.getId()) {
                        //find cm treatment medicine details by cm treatment by id
                        cmTreatmentMedicineDetails = cmTreatmentMedicineDetailRepository.findCmTreatmentMedicineByCmTreatment(cmTreatment.getId());
                        if(cmTreatmentMedicineDetails.size() > 0 ) {
                            if (cmTreatmentMedicineDetails != null) {
                                cmMedicineTreatmentResponseDTOs = new ArrayList<>();
                                CmMedicineTreatmentResponseDTO cmMedicineTreatmentResponseDTO = new CmMedicineTreatmentResponseDTO();
                                cmMedicineTreatmentResponseDTO.setMedicineTreatmentGivenDate(cmTreatment.getGivenDate());
                                //setting individual medicines
                                // cmIndividualMedicineTreatmentDTOs = frameCmTreatmentMedicine(cmTreatmentMedicineDetails);
                                List<CmTreatmentGroupMedicinesDTO> cmTreatmentGroupMedicinesDTOs = new ArrayList<>();
                                List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineDetails = new ArrayList<>();
                                CmIndividualMedicineTreatmentDTO cmIndividualMedicineTreatmentDTO = new CmIndividualMedicineTreatmentDTO();
                                for(CmTreatmentMedicineDetail cmTreatmentMedicineDetail : cmTreatmentMedicineDetails){
                                    cmTreatmentGroupMedicineDetails = cmTreatmentGroupMedicineDetailRepository.findCmTreatmentGroupByMedicineDetail(cmTreatmentMedicineDetail.getId());
                                    if(cmTreatmentGroupMedicineDetails.size() <= 0) {
                                        cmIndividualMedicineTreatmentDTO = frameCmTreatmentMedicineDTO(cmTreatmentMedicineDetail);
                                        cmIndividualMedicineTreatmentDTOs.add(cmIndividualMedicineTreatmentDTO);
                                    }else{
                                        // frame cm treatment group medicines
                                        CmTreatmentGroupDTO cmTreatmentGroupDTO = new CmTreatmentGroupDTO();
                                        // CmIndividualMedicineTreatmentDTO cmIndividualMedicineTreatmentDTO = new CmIndividualMedicineTreatmentDTO();
                                        cmIndividualMedicineTreatmentDTO = frameCmTreatmentMedicineDTO(cmTreatmentMedicineDetail);
                                        //setting the cm individual medicine to cm treatment group
                                        cmTreatmentGroupDTO.setCmIndividualMedicineGroupInfoDTO(cmIndividualMedicineTreatmentDTO);
                                        cmTreatmentGroupMedicinesDTOs = frameCmTreatmentGroupMedicines(cmTreatmentMedicineDetail.getId());
                                        cmTreatmentGroupDTO.setCmTreatmentGroupMedicinesDTOs(cmTreatmentGroupMedicinesDTOs);
                                        cmTreatmentGroupDTOs.add(cmTreatmentGroupDTO);
                                    }
                                }
                                if (cmIndividualMedicineTreatmentDTOs != null) {
                                    cmMedicineTreatmentResponseDTO.setCmIndividualMedicineTreatmentDTOs(cmIndividualMedicineTreatmentDTOs);
                                }
                                // frame cm treatment group medicines
                                // cmTreatmentGroupDTOs = frameCmTreatmentGroupMedicine(cmTreatmentMedicineDetails);
                                // List<CmTreatmentGroupMedicinesDTO> cmTreatmentGroupMedicinesDTOs = new ArrayList<>();
                                /*for (CmTreatmentMedicineDetail cmTreatmentMedicineDetail : cmTreatmentMedicineDetails) {
                                    CmTreatmentGroupDTO cmTreatmentGroupDTO = new CmTreatmentGroupDTO();
                                    CmIndividualMedicineTreatmentDTO cmIndividualMedicineTreatmentDTO = new CmIndividualMedicineTreatmentDTO();
                                    cmIndividualMedicineTreatmentDTO = frameCmTreatmentMedicineDTO(cmTreatmentMedicineDetail);
                                    //setting the cm individual medicine to cm treatment group
                                    cmTreatmentGroupDTO.setCmIndividualMedicineGroupInfoDTO(cmIndividualMedicineTreatmentDTO);
                                    cmTreatmentGroupMedicinesDTOs = frameCmTreatmentGroupMedicines(cmTreatmentMedicineDetail.getId());
                                    cmTreatmentGroupDTO.setCmTreatmentGroupMedicinesDTOs(cmTreatmentGroupMedicinesDTOs);
                                    cmTreatmentGroupDTOs.add(cmTreatmentGroupDTO);
                                }*/

                                if (cmTreatmentGroupDTOs != null) {
                                    cmMedicineTreatmentResponseDTO.setCmTreatmentGroupDTOs(cmTreatmentGroupDTOs);
                                }
                                cmMedicineTreatmentResponseDTOs.add(cmMedicineTreatmentResponseDTO);
                            }
                        }
                    }
                    cmTreat = cmTreatment.getId();
                }
            log.debug("TreatmentMedicine details retrieved Successfully");
        } catch (Exception e) {
            log.error("Failed to retrieve TreatmentMedicine details:" + e.getMessage());
        }
        return cmMedicineTreatmentResponseDTOs;
    }

    //method frame cm treatment medicine dto
    private CmIndividualMedicineTreatmentDTO frameCmTreatmentMedicineDTO(CmTreatmentMedicineDetail cmTreatmentMedicineDetail) {
        CmIndividualMedicineTreatmentDTO cmIndividualMedicineTreatmentDTO = new CmIndividualMedicineTreatmentDTO();
        List<DosageTimeDTO> dosageTimeDTOS = new ArrayList<>();
        try {
            cmIndividualMedicineTreatmentDTO.setCmTreatmentMedicineId(cmTreatmentMedicineDetail.getId());
            if(cmTreatmentMedicineDetail.getCmDosageValueMapping() != null){
                if(cmTreatmentMedicineDetail.getCmDosageValueMapping().getCmDosageMaster() != null)
                cmIndividualMedicineTreatmentDTO.setDosageName(cmTreatmentMedicineDetail.getCmDosageValueMapping().getCmDosageMaster().getDosageName());
                cmIndividualMedicineTreatmentDTO.setCmDosageValueMappingId(cmTreatmentMedicineDetail.getCmDosageValueMapping().getId());
            }
            // to frame dosage time dto
            JSONArray jsonArray = new JSONArray(cmTreatmentMedicineDetail.getDosageTime().toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DosageTimeDTO dosageTimeDTO = new DosageTimeDTO();
                dosageTimeDTO.setSequenceNo(jsonObject.getLong("sequenceNo"));
                if(jsonObject.getString("dosageInstructionId") != null) {
                    dosageTimeDTO.setDosageInstructionId(jsonObject.getString("dosageInstructionId"));
                }
                dosageTimeDTO.setDosageInstructionName(jsonObject.getString("dosageInstructionName"));
                dosageTimeDTO.setTime(jsonObject.getString("time"));
                dosageTimeDTOS.add(dosageTimeDTO);
            }
            cmIndividualMedicineTreatmentDTO.setDosageTimeDTOs(dosageTimeDTOS);
            cmIndividualMedicineTreatmentDTO.setInstruction(cmTreatmentMedicineDetail.getInstructions());
            cmIndividualMedicineTreatmentDTO.setProductCatalogueCommonDetailId(cmTreatmentMedicineDetail.getProductCatalogueCommonDetailId());
            cmIndividualMedicineTreatmentDTO.setDosageQuantity(cmTreatmentMedicineDetail.getDosageQuantity());
            if(cmTreatmentMedicineDetail.getTotalQuantity() != null) {
                cmIndividualMedicineTreatmentDTO.setTotalQuantity(cmTreatmentMedicineDetail.getTotalQuantity());
            }
            //call to get product name
            JSONObject productCatalogueCommonDetail = serviceGateway.getProductCatalogueCommonDetailById(cmTreatmentMedicineDetail.getProductCatalogueCommonDetailId());
            if (productCatalogueCommonDetail != null) {
                JSONObject productCatalogueDetailDTO = productCatalogueCommonDetail.getJSONObject("productCatalogueDetailDTO");
                cmIndividualMedicineTreatmentDTO.setMedicineName(productCatalogueDetailDTO.getString("itemName"));
            }
            cmIndividualMedicineTreatmentDTO.setNumberOfDays(cmTreatmentMedicineDetail.getNumberOfDays());
            cmIndividualMedicineTreatmentDTO.setUomId(cmTreatmentMedicineDetail.getUomMasterId());
            //call to get uom master
            JSONObject jsonObjectUomMaster = getUomMasterByUomMasterId(cmTreatmentMedicineDetail.getUomMasterId());
            if(jsonObjectUomMaster != null){
                cmIndividualMedicineTreatmentDTO.setUomName(jsonObjectUomMaster.getString("uomName"));
            }
            if(cmTreatmentMedicineDetail.getMedicineGroupType() != null)
                cmIndividualMedicineTreatmentDTO.setMedicineGroupTypeLookupId(cmTreatmentMedicineDetail.getMedicineGroupType().getId());
        } catch (JSONException e) {
            log.error("failed to frame cm treatment medicine" + e.getMessage());
        }
        return cmIndividualMedicineTreatmentDTO;
    }

    //method to frame cm treatment group medicine
    private List<CmTreatmentGroupMedicinesDTO> frameCmTreatmentGroupMedicines(Long cmTreatmentMedicineDetailId) {
        List<CmTreatmentGroupMedicinesDTO> cmTreatmentGroupMedicinesDTOs = new ArrayList<>();
        List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineDetails = new ArrayList<>();
        try {
            cmTreatmentGroupMedicineDetails = cmTreatmentGroupMedicineDetailRepository.findCmTreatmentGroupByMedicineDetail(cmTreatmentMedicineDetailId);
            if(cmTreatmentGroupMedicineDetails != null){
                for(CmTreatmentGroupMedicineDetail cmTreatmentGroupMedicineDetail : cmTreatmentGroupMedicineDetails){
                    CmTreatmentGroupMedicinesDTO cmTreatmentGroupMedicinesDTO = new CmTreatmentGroupMedicinesDTO();
                    cmTreatmentGroupMedicinesDTO.setCmTreatmentGroupMedicineId(cmTreatmentGroupMedicineDetail.getId());
                    cmTreatmentGroupMedicinesDTO.setProductCatalogueCommonDetailId(cmTreatmentGroupMedicineDetail.getProductCatalogueCommonDetailId());
                    //call to get product name
                    JSONObject productCatalogueCommonDetail = serviceGateway.getProductCatalogueCommonDetailById(cmTreatmentGroupMedicineDetail.getProductCatalogueCommonDetailId());
                    if (productCatalogueCommonDetail != null) {
                        JSONObject productCatalogueDetailDTO = productCatalogueCommonDetail.getJSONObject("productCatalogueDetailDTO");
                        cmTreatmentGroupMedicinesDTO.setProductCatalogueCommonDetailName(productCatalogueDetailDTO.getString("itemName"));
                    }
                    cmTreatmentGroupMedicinesDTO.setComposition(cmTreatmentGroupMedicineDetail.getComposition());
                    if(cmTreatmentGroupMedicineDetail.getTotalQuantity() != null) {
                        cmTreatmentGroupMedicinesDTO.setTotalQuantity(cmTreatmentGroupMedicineDetail.getTotalQuantity());
                    }
                    cmTreatmentGroupMedicinesDTO.setInstructions(cmTreatmentGroupMedicineDetail.getInstructions());
                    cmTreatmentGroupMedicinesDTO.setStrength(cmTreatmentGroupMedicineDetail.getStrength());
                    cmTreatmentGroupMedicinesDTO.setUomId(cmTreatmentGroupMedicineDetail.getUomMasterId());
                    //call to get uom master
                    JSONObject jsonObjectUomMaster = getUomMasterByUomMasterId(cmTreatmentGroupMedicineDetail.getUomMasterId());
                    if(jsonObjectUomMaster != null){
                        cmTreatmentGroupMedicinesDTO.setUomName(jsonObjectUomMaster.getString("uomName"));
                    }
                    cmTreatmentGroupMedicinesDTOs.add(cmTreatmentGroupMedicinesDTO);
                }
            }
        } catch (JSONException e) {
            log.error("failed to frame cm treatment group medicine" + e.getMessage());
        }
        return cmTreatmentGroupMedicinesDTOs;
    }

    // method to get treatment therapies by case id
    @Override
    public List<CmTherapyTreatmentResponseDTO> getTreatmentTherapies(Long caseId) {

        int count = 0;
        LocalDateTime therapyDate = LocalDateTime.now();
        LocalDateTime therapyDateTemp = LocalDateTime.now();
        List<CmTherapyTreatmentResponseDTO> cmTherapyTreatmentResponseDTOs = new ArrayList<>();
        CmTherapyTreatmentResponseDTO cmTherapyTreatmentResponseDTO = null;
        CmTherapyTreatmentDetailsDTO cmTherapyTreatmentDetailsDTO = null;
        List< CmTherapyTreatmentDetailsDTO> cmTherapyTreatmentDetailsDTOs = null;
        List<TherapyPlanning> therapyPlanningList = new ArrayList<TherapyPlanning>();
        try {
            log.debug("GetCompleteCaseDetailsServiceImpl == > getTreatmentTherapies !!!!");

            therapyPlanningList = therapyPlanningRepository.getPlanningDetailsByCase(caseId);
            for (TherapyPlanning therapyPlanning : therapyPlanningList) {
                therapyDate = therapyPlanning.getUpdatedOn();
                cmTherapyTreatmentDetailsDTO = new CmTherapyTreatmentDetailsDTO();
                if (count > 0) {
                    if (therapyDate.equals(therapyDateTemp)) {
                    } else {
                       // cmTherapyTreatmentResponseDTO = new CmTherapyTreatmentResponseDTO();
                        cmTherapyTreatmentDetailsDTOs = new ArrayList<>();
                    }
                } else {
                   // cmTherapyTreatmentResponseDTO = new CmTherapyTreatmentResponseDTO();
                    cmTherapyTreatmentDetailsDTOs = new ArrayList<>();
                }
                cmTherapyTreatmentDetailsDTO.setTherapyPlanningId(therapyPlanning.getId());
                cmTherapyTreatmentDetailsDTO.setNumberOfDays(therapyPlanning.getNumberOfDays());
                if (therapyPlanning.getInstructions() != null) {
                    cmTherapyTreatmentDetailsDTO.setInstructions(therapyPlanning.getInstructions());
                }
                cmTherapyTreatmentDetailsDTO.setPeriodicInterval(therapyPlanning.getPeriodicInterval());
                cmTherapyTreatmentDetailsDTO.setServiceCatalogueId(therapyPlanning.getServiceCatalogueId());
                //retrieve service Details
                JSONObject jsonObject = serviceGateway.getServiceCatalogueById(therapyPlanning.getServiceCatalogueId());
                //retrieve serviceCatalogueObject
                String serviceCatalogueObject = null;
                if (jsonObject != null)
                    serviceCatalogueObject = String.valueOf(jsonObject.get("serviceName"));
                cmTherapyTreatmentDetailsDTO.setTherapyName(serviceCatalogueObject);

                List<CmTherapyTreatmentMedicineDTO> cmTherapyTreatmentMedicineDTOs = new ArrayList<>();
                List<CmTherapyTreatmentMedicineTypeDTO> cmTherapyTreatmentMedicineTypeDTOs = new ArrayList<>();

                List<TherapyPlanningMedicine> therapyPlanningMedicines = new ArrayList<>();
                List<TherapyPlanningMedicineType> therapyPlanningMedicineTypes = new ArrayList<>();
                therapyPlanningMedicines = therapyPlanningMedicineRepository.findByTherapyPlanningId(therapyPlanning.getId());
                therapyPlanningMedicineTypes = therapyPlanningMedicineTypeRepository.findByTherapyPlanningId(therapyPlanning.getId());
                //frame therapy planning medicines
                if(therapyPlanningMedicines != null){
                    for(TherapyPlanningMedicine therapyPlanningMedicine : therapyPlanningMedicines){
                        CmTherapyTreatmentMedicineDTO cmTherapyTreatmentMedicineDTO = new CmTherapyTreatmentMedicineDTO();
                        cmTherapyTreatmentMedicineDTO.setTherapyPlanningMedicineId(therapyPlanningMedicine.getId());
                        cmTherapyTreatmentMedicineDTO.setPmProdCatalogueCommonDetailsId(therapyPlanningMedicine.getProductCatalogueCommonDetailId());
                        //call to get product name
                        JSONObject productCatalogueCommonDetail = serviceGateway.getProductCatalogueCommonDetailById(therapyPlanningMedicine.getProductCatalogueCommonDetailId());
                        if (productCatalogueCommonDetail != null) {
                            JSONObject productCatalogueDetailDTO = productCatalogueCommonDetail.getJSONObject("productCatalogueDetailDTO");
                            cmTherapyTreatmentMedicineDTO.setMedicineName(productCatalogueDetailDTO.getString("itemName"));
                        }
                        //call to get medicine type name
                        JSONObject jsonObjectMedicineTypeMaster = serviceGateway.getMedicineTypeById(therapyPlanningMedicine.getMedicineTypeId());
                        if(jsonObjectMedicineTypeMaster != null) {
                            JSONObject medicineTypeMasterDTO = jsonObjectMedicineTypeMaster.getJSONObject("medicineTypeMasterDTO");
                            cmTherapyTreatmentMedicineDTO.setMedicineTypeId(therapyPlanningMedicine.getMedicineTypeId());
                            cmTherapyTreatmentMedicineDTO.setMedicineTypeName(medicineTypeMasterDTO.getString("typeName"));
                        }
                        cmTherapyTreatmentMedicineDTO.setQuantity(therapyPlanningMedicine.getQuantity());
                        cmTherapyTreatmentMedicineDTO.setMedicineInstructions(therapyPlanningMedicine.getMedicineInstructions());
                        //call to get uom master
                        if(therapyPlanningMedicine.getUomMasterId() != null){
                            JSONObject jsonObjectUomMaster = getUomMasterByUomMasterId(therapyPlanningMedicine.getUomMasterId());
                        if(jsonObjectUomMaster != null)
                            cmTherapyTreatmentMedicineDTO.setUomMasterId(therapyPlanningMedicine.getUomMasterId());
                            cmTherapyTreatmentMedicineDTO.setUomMasterName(jsonObjectUomMaster.getString("uomName"));

                        }
                        cmTherapyTreatmentMedicineDTOs.add(cmTherapyTreatmentMedicineDTO);
                    }
                    cmTherapyTreatmentDetailsDTO.setCmTherapyTreatmentMedicineDTOs(cmTherapyTreatmentMedicineDTOs);
                }

                //frame therapy planning medicine types
                if(therapyPlanningMedicineTypes != null){
                    for(TherapyPlanningMedicineType therapyPlanningMedicineType : therapyPlanningMedicineTypes){
                        CmTherapyTreatmentMedicineTypeDTO cmTherapyTreatmentMedicineTypeDTO = new CmTherapyTreatmentMedicineTypeDTO();
                        cmTherapyTreatmentMedicineTypeDTO.setTherapyPlanningMedicineTypeId(therapyPlanningMedicineType.getId());
                        //call to get medicine type name
                        JSONObject jsonObjectMedicineTypeMaster = serviceGateway.getMedicineTypeById(therapyPlanningMedicineType.getMedicineTypeId());
                        if(jsonObjectMedicineTypeMaster != null) {
                            JSONObject medicineTypeMasterDTO = jsonObjectMedicineTypeMaster.getJSONObject("medicineTypeMasterDTO");
                            cmTherapyTreatmentMedicineTypeDTO.setMedicineTypeId(therapyPlanningMedicineType.getMedicineTypeId());
                            cmTherapyTreatmentMedicineTypeDTO.setMedicineTypeName(medicineTypeMasterDTO.getString("typeName"));
                        }
                        cmTherapyTreatmentMedicineTypeDTOs.add(cmTherapyTreatmentMedicineTypeDTO);
                    }
                    cmTherapyTreatmentDetailsDTO.setCmTherapyTreatmentMedicineTypeDTOs(cmTherapyTreatmentMedicineTypeDTOs);
                }

                cmTherapyTreatmentDetailsDTOs.add(cmTherapyTreatmentDetailsDTO);
                if (!therapyDate.equals(therapyDateTemp)) {
                    cmTherapyTreatmentResponseDTO = new CmTherapyTreatmentResponseDTO();
                    cmTherapyTreatmentResponseDTO.setTherapyDate(therapyDate);
                    cmTherapyTreatmentResponseDTO.setCmTherapyTreatmentDetailsDTOs(cmTherapyTreatmentDetailsDTOs);
                    cmTherapyTreatmentResponseDTOs.add(cmTherapyTreatmentResponseDTO);

                }
                therapyDateTemp = therapyDate;
                count++;

            }


        } catch (Exception e) {
            log.error("GetCompleteCaseDetailsServiceImpl == > getTreatmentTherapies failed == > " + e);
        }
        return cmTherapyTreatmentResponseDTOs;
    }

    // method to get treatment therapies by case id
    @Override
    public List<CmTherapyTreatmentResponseDTO> getPatientTreatmentTherapies(Long caseId) {

        int count = 0;
        LocalDateTime therapyDate = LocalDateTime.now();
        LocalDateTime therapyDateTemp = LocalDateTime.now();
        List<CmTherapyTreatmentResponseDTO> cmTherapyTreatmentResponseDTOs = new ArrayList<>();
        CmTherapyTreatmentResponseDTO cmTherapyTreatmentResponseDTO = null;
        CmTherapyTreatmentDetailsDTO cmTherapyTreatmentDetailsDTO = null;
        List< CmTherapyTreatmentDetailsDTO> cmTherapyTreatmentDetailsDTOs = null;
        List<TherapyPlanning> therapyPlanningList = new ArrayList<TherapyPlanning>();

        CmMaster cmMaster = new CmMaster();
        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        long cmMasterDetailsId = (long) 0;
        List<Integer> listOfIds = new ArrayList<Integer>();
        try {
            log.debug("GetCompleteCaseDetailsServiceImpl == > getPatientTreatmentTherapies !!!!");
            cmMaster = cmMasterRepository.findOne(caseId);
            if(cmMaster != null)
                cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(cmMaster.getId());
            if(cmMasterDetails != null)
                for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                    cmMasterDetailsId = cmMasterDetailsTemp.getId();
                    Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                    listOfIds.add(cmMasterDetailsIdINT);
                }
            Integer latestId = Collections.max(listOfIds);
            therapyPlanningList = therapyPlanningRepository.getPlanningDetailsByCmMasterDetailId(Long.valueOf(latestId));
            for (TherapyPlanning therapyPlanning : therapyPlanningList) {
                therapyDate = therapyPlanning.getUpdatedOn();
                cmTherapyTreatmentDetailsDTO = new CmTherapyTreatmentDetailsDTO();
                if (count > 0) {
                    if (therapyDate.equals(therapyDateTemp)) {
                    } else {
                        // cmTherapyTreatmentResponseDTO = new CmTherapyTreatmentResponseDTO();
                        cmTherapyTreatmentDetailsDTOs = new ArrayList<>();
                    }
                } else {
                    // cmTherapyTreatmentResponseDTO = new CmTherapyTreatmentResponseDTO();
                    cmTherapyTreatmentDetailsDTOs = new ArrayList<>();
                }
                cmTherapyTreatmentDetailsDTO.setTherapyPlanningId(therapyPlanning.getId());
                cmTherapyTreatmentDetailsDTO.setNumberOfDays(therapyPlanning.getNumberOfDays());
                if (therapyPlanning.getInstructions() != null) {
                    cmTherapyTreatmentDetailsDTO.setInstructions(therapyPlanning.getInstructions());
                }
                cmTherapyTreatmentDetailsDTO.setPeriodicInterval(therapyPlanning.getPeriodicInterval());
                cmTherapyTreatmentDetailsDTO.setServiceCatalogueId(therapyPlanning.getServiceCatalogueId());
                //retrieve service Details
                JSONObject jsonObject = serviceGateway.getServiceCatalogueById(therapyPlanning.getServiceCatalogueId());
                //retrieve serviceCatalogueObject
                String serviceCatalogueObject = null;
                if (jsonObject != null)
                    serviceCatalogueObject = String.valueOf(jsonObject.get("serviceName"));
                cmTherapyTreatmentDetailsDTO.setTherapyName(serviceCatalogueObject);

                List<CmTherapyTreatmentMedicineDTO> cmTherapyTreatmentMedicineDTOs = new ArrayList<>();
                List<CmTherapyTreatmentMedicineTypeDTO> cmTherapyTreatmentMedicineTypeDTOs = new ArrayList<>();

                List<TherapyPlanningMedicine> therapyPlanningMedicines = new ArrayList<>();
                List<TherapyPlanningMedicineType> therapyPlanningMedicineTypes = new ArrayList<>();
                therapyPlanningMedicines = therapyPlanningMedicineRepository.findByTherapyPlanningId(therapyPlanning.getId());
                therapyPlanningMedicineTypes = therapyPlanningMedicineTypeRepository.findByTherapyPlanningId(therapyPlanning.getId());
                //frame therapy planning medicines
                if(therapyPlanningMedicines != null){
                    for(TherapyPlanningMedicine therapyPlanningMedicine : therapyPlanningMedicines){
                        CmTherapyTreatmentMedicineDTO cmTherapyTreatmentMedicineDTO = new CmTherapyTreatmentMedicineDTO();
                        cmTherapyTreatmentMedicineDTO.setTherapyPlanningMedicineId(therapyPlanningMedicine.getId());
                        cmTherapyTreatmentMedicineDTO.setPmProdCatalogueCommonDetailsId(therapyPlanningMedicine.getProductCatalogueCommonDetailId());
                        //call to get product name
                        JSONObject productCatalogueCommonDetail = serviceGateway.getProductCatalogueCommonDetailById(therapyPlanningMedicine.getProductCatalogueCommonDetailId());
                        if (productCatalogueCommonDetail != null) {
                            JSONObject productCatalogueDetailDTO = productCatalogueCommonDetail.getJSONObject("productCatalogueDetailDTO");
                            cmTherapyTreatmentMedicineDTO.setMedicineName(productCatalogueDetailDTO.getString("itemName"));
                        }
                        //call to get medicine type name
                        JSONObject jsonObjectMedicineTypeMaster = serviceGateway.getMedicineTypeById(therapyPlanningMedicine.getMedicineTypeId());
                        if(jsonObjectMedicineTypeMaster != null) {
                            JSONObject medicineTypeMasterDTO = jsonObjectMedicineTypeMaster.getJSONObject("medicineTypeMasterDTO");
                            cmTherapyTreatmentMedicineDTO.setMedicineTypeId(therapyPlanningMedicine.getMedicineTypeId());
                            cmTherapyTreatmentMedicineDTO.setMedicineTypeName(medicineTypeMasterDTO.getString("typeName"));
                        }
                        cmTherapyTreatmentMedicineDTO.setQuantity(therapyPlanningMedicine.getQuantity());
                        cmTherapyTreatmentMedicineDTO.setMedicineInstructions(therapyPlanningMedicine.getMedicineInstructions());
                        //call to get uom master
                        if(therapyPlanningMedicine.getUomMasterId() != null){
                            JSONObject jsonObjectUomMaster = getUomMasterByUomMasterId(therapyPlanningMedicine.getUomMasterId());
                            if(jsonObjectUomMaster != null)
                                cmTherapyTreatmentMedicineDTO.setUomMasterId(therapyPlanningMedicine.getUomMasterId());
                            cmTherapyTreatmentMedicineDTO.setUomMasterName(jsonObjectUomMaster.getString("uomName"));

                        }
                        cmTherapyTreatmentMedicineDTOs.add(cmTherapyTreatmentMedicineDTO);
                    }
                    cmTherapyTreatmentDetailsDTO.setCmTherapyTreatmentMedicineDTOs(cmTherapyTreatmentMedicineDTOs);
                }

                //frame therapy planning medicine types
                if(therapyPlanningMedicineTypes != null){
                    for(TherapyPlanningMedicineType therapyPlanningMedicineType : therapyPlanningMedicineTypes){
                        CmTherapyTreatmentMedicineTypeDTO cmTherapyTreatmentMedicineTypeDTO = new CmTherapyTreatmentMedicineTypeDTO();
                        cmTherapyTreatmentMedicineTypeDTO.setTherapyPlanningMedicineTypeId(therapyPlanningMedicineType.getId());
                        //call to get medicine type name
                        JSONObject jsonObjectMedicineTypeMaster = serviceGateway.getMedicineTypeById(therapyPlanningMedicineType.getMedicineTypeId());
                        if(jsonObjectMedicineTypeMaster != null) {
                            JSONObject medicineTypeMasterDTO = jsonObjectMedicineTypeMaster.getJSONObject("medicineTypeMasterDTO");
                            cmTherapyTreatmentMedicineTypeDTO.setMedicineTypeId(therapyPlanningMedicineType.getMedicineTypeId());
                            cmTherapyTreatmentMedicineTypeDTO.setMedicineTypeName(medicineTypeMasterDTO.getString("typeName"));
                        }
                        cmTherapyTreatmentMedicineTypeDTOs.add(cmTherapyTreatmentMedicineTypeDTO);
                    }
                    cmTherapyTreatmentDetailsDTO.setCmTherapyTreatmentMedicineTypeDTOs(cmTherapyTreatmentMedicineTypeDTOs);
                }

                cmTherapyTreatmentDetailsDTOs.add(cmTherapyTreatmentDetailsDTO);
                if (!therapyDate.equals(therapyDateTemp)) {
                    cmTherapyTreatmentResponseDTO = new CmTherapyTreatmentResponseDTO();
                    cmTherapyTreatmentResponseDTO.setTherapyDate(therapyDate);
                    cmTherapyTreatmentResponseDTO.setCmTherapyTreatmentDetailsDTOs(cmTherapyTreatmentDetailsDTOs);
                    cmTherapyTreatmentResponseDTOs.add(cmTherapyTreatmentResponseDTO);

                }
                therapyDateTemp = therapyDate;
                count++;

            }

        } catch (Exception e) {
            log.error("GetCompleteCaseDetailsServiceImpl == > getPatientTreatmentTherapies failed == > " + e);
        }
        return cmTherapyTreatmentResponseDTOs;
    }

    //method to get cm investigations by case id
    @Override
    public CmInvestigationGetDTO getCmInvestigation(Long caseId) {
        CmMaster cmMaster = new CmMaster();
        long cmMasterDetailsId = (long) 0;
        List<Integer> listOfIds = new ArrayList<Integer>();
        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        CmInvestigation cmInvestigation = new CmInvestigation();
        CmInvestigationGetDTO cmInvestigationGetDTO = new CmInvestigationGetDTO();
        try {
            log.debug("Call to get cm investigation" + caseId);
            // get cm master by case id
            cmMaster = cmMasterRepository.findOne(caseId);
            if(cmMaster != null)
                cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(cmMaster.getId());
            if(cmMasterDetails != null)
            for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                cmMasterDetailsId = cmMasterDetailsTemp.getId();
                Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                listOfIds.add(cmMasterDetailsIdINT);
            }
            Integer latestId = Collections.max(listOfIds);
            for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                cmMasterDetailsId = cmMasterDetailsTemp.getId();
                Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                if (latestId.equals(cmMasterDetailsIdINT)) {
                    cmInvestigation = cmInvestigationRepository.findInvestigationByCmMasterDetailsId(cmMasterDetailsTemp.getId());
                    if(cmInvestigation != null)
                        cmInvestigationGetDTO.setCmMasterDetailsId(cmInvestigation.getCmMasterDetails().getId());
                        cmInvestigationGetDTO.setCmInvestigationId(cmInvestigation.getId());
                        cmInvestigationGetDTO.setCreatedDate(cmInvestigation.getCreatedDate());
                        cmInvestigationGetDTO.setLabOrderNumber(cmInvestigation.getLabOrderNumber());
                        if (cmInvestigation.getProvisionalDiagnosisMaster() != null) {
                            cmInvestigationGetDTO.setProvisionalDiagnosisMasterId(cmInvestigation.getProvisionalDiagnosisMaster().getId());
                            cmInvestigationGetDTO.setProvisionalDiagnosisMasterName(cmInvestigation.getProvisionalDiagnosisMaster().getName());
                        }
                        cmInvestigationGetDTO.setFinalDiagnosis(cmInvestigation.isFinalDiagnosis());
                        if (cmInvestigation.getDoctorSummary() != null) {
                            cmInvestigationGetDTO.setDoctorSummary(cmInvestigation.getDoctorSummary());
                        }

                        if (cmInvestigation.getCmAcdMaster()!= null) {
                            cmInvestigationGetDTO.setCmAcdMasterId(cmInvestigation.getCmAcdMaster().getId());
                            cmInvestigationGetDTO.setCmAcdMasterName(cmInvestigation.getCmAcdMaster().getAcdCode());
                            cmInvestigationGetDTO.setCmAcdMasterDescription(cmInvestigation.getCmAcdMaster().getAcdDescription());
                        }

                        if (cmInvestigation.getCmIcdMaster() != null) {
                            cmInvestigationGetDTO.setCmIcdMasterId(cmInvestigation.getCmIcdMaster().getId());
                            cmInvestigationGetDTO.setCmIcdMasterName(cmInvestigation.getCmIcdMaster().getIcdCode());
                            cmInvestigationGetDTO.setCmIcdMasterDescription(cmInvestigation.getCmIcdMaster().getIcdDescription());
                        }
                }
            }
        } catch (Exception e) {
            log.error("Failed to get cm investigation" + e.getMessage());
        }
        return cmInvestigationGetDTO;
    }

    //method to get cm investigations by case id for patient copy
    @Override
    public CmInvestigationGetDTO getPatientCmInvestigation(Long caseId) {
        CmMaster cmMaster = new CmMaster();
        long cmMasterDetailsId = (long) 0;
        List<Integer> listOfIds = new ArrayList<Integer>();
        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        CmInvestigation cmInvestigation = new CmInvestigation();
        CmInvestigationGetDTO cmInvestigationGetDTO = new CmInvestigationGetDTO();
        try {
            log.debug("Call to get cm investigation" + caseId);
            // get cm master by case id
            cmMaster = cmMasterRepository.findOne(caseId);
            if(cmMaster != null)
                cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(cmMaster.getId());
            if(cmMasterDetails != null)
                for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                    cmMasterDetailsId = cmMasterDetailsTemp.getId();
                    Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                    listOfIds.add(cmMasterDetailsIdINT);
                }
            Integer latestId = Collections.max(listOfIds);
                    //find investigation by cm master detail id
                    cmInvestigation = cmInvestigationRepository.findInvestigationByCmMasterDetailsId(Long.valueOf(latestId));
                    if(cmInvestigation != null)
                        cmInvestigationGetDTO.setCmMasterDetailsId(cmInvestigation.getCmMasterDetails().getId());
                    cmInvestigationGetDTO.setCmInvestigationId(cmInvestigation.getId());
                    cmInvestigationGetDTO.setCreatedDate(cmInvestigation.getCreatedDate());
                    cmInvestigationGetDTO.setLabOrderNumber(cmInvestigation.getLabOrderNumber());
                    if (cmInvestigation.getProvisionalDiagnosisMaster() != null) {
                        cmInvestigationGetDTO.setProvisionalDiagnosisMasterId(cmInvestigation.getProvisionalDiagnosisMaster().getId());
                        cmInvestigationGetDTO.setProvisionalDiagnosisMasterName(cmInvestigation.getProvisionalDiagnosisMaster().getName());
                    }
                    cmInvestigationGetDTO.setFinalDiagnosis(cmInvestigation.isFinalDiagnosis());
                    if (cmInvestigation.getDoctorSummary() != null) {
                        cmInvestigationGetDTO.setDoctorSummary(cmInvestigation.getDoctorSummary());
                    }

                    if (cmInvestigation.getCmAcdMaster()!= null) {
                        cmInvestigationGetDTO.setCmAcdMasterId(cmInvestigation.getCmAcdMaster().getId());
                        cmInvestigationGetDTO.setCmAcdMasterName(cmInvestigation.getCmAcdMaster().getAcdCode());
                        cmInvestigationGetDTO.setCmAcdMasterDescription(cmInvestigation.getCmAcdMaster().getAcdDescription());
                    }

                    if (cmInvestigation.getCmIcdMaster() != null) {
                        cmInvestigationGetDTO.setCmIcdMasterId(cmInvestigation.getCmIcdMaster().getId());
                        cmInvestigationGetDTO.setCmIcdMasterName(cmInvestigation.getCmIcdMaster().getIcdCode());
                        cmInvestigationGetDTO.setCmIcdMasterDescription(cmInvestigation.getCmIcdMaster().getIcdDescription());
                    }

        } catch (Exception e) {
            log.error("Failed to get cm investigation" + e.getMessage());
        }
        return cmInvestigationGetDTO;
    }

    //method to get cm investigations details by case id
    @Override
    public List<CmInvestigationDetailsGetDTO> getCmInvestigationDetails(GetInvestigationDetailsByCaseIdServiceRequest request) {
        UserDetail userDetail = new UserDetail();
        List<Object[]> patientAllInvestigationDetail = new ArrayList<Object[]>();
        String filePathId = new String();
        String filePath = new String();
        String filePathContentType = new String();
        long investigationId = (long) 0;
        long investigationDetailId = (long) 0;
        String labOrderNumber = new String();
        String labOrderCreatedDate = new String();
        String labTestedOn = new String();
        long serviceCatalogueId = (long) 0;
        String investigationNotes = new String();
        String doctorSummary = new String();
        String tempLabOrderNumber = new String();
        String tempDept = new String();
        String itemName = new String();
        String deptName = new String();
        String tempItemName = new String();
        List<String> invItemPaths = new ArrayList<String>();
        String itemStatus = new String();
        String deptEqualStatus = new String();
        String labOrderStatus = new String();
        CmInvestigationLabItemDetailGetDTO cmInvestigationLabItemDetailGetDTO = null;
        CmInvestigationLabDetailGetDTO cmInvestigationLabDetailGetDTO = null;
        List<CmInvestigationLabItemDetailGetDTO> cmInvestigationLabItemDetailGetDTOs = new ArrayList<CmInvestigationLabItemDetailGetDTO>();
        List<CmInvestigationLabDetailGetDTO> cmInvestigationLabDetailGetDTOs = new ArrayList<CmInvestigationLabDetailGetDTO>();
        List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs = new ArrayList<CmInvestigationDetailsGetDTO>();
        CmInvestigationDetailsGetDTO cmInvestigationDetailsGetDTO = null;
        int count = 0;
        try {
            log.debug("Call to get cm investigation by case id");
            //find doctor by user and clinic
            userDetail = userDetailRepository.findDoctorByUserId(request.getUserId(),request.getClinicId());
            if(userDetail != null)
            //retrieve investigation item details by doctor, patient,clinic and case
            patientAllInvestigationDetail = cmMasterRepository.getPatientAllInvestigationDetail
                    (request.getPatientId(),request.getCaseId(),request.getClinicLocationId(),userDetail.getId());

            deptEqualStatus = "not-equal";
            labOrderStatus = "not-equal";
            // setting the values to dto
            for (Object[] entity : patientAllInvestigationDetail) {
                filePathId = new String();
                investigationId = Long.valueOf(entity[0].toString());
                if (entity[1] != null) {
                    labOrderNumber = entity[1].toString();
                }
                if (entity[2] != null) {
                    labOrderCreatedDate = entity[2].toString();
                }
              //  labOrderCreatedDate = labOrderCreatedDate.substring(0, labOrderCreatedDate.length() - 2);
                if(entity[3] != null) {
                serviceCatalogueId = Long.valueOf(entity[3].toString());
                    JSONObject jsonServiceObject = serviceGateway.getServiceCatalogueByServiceId(serviceCatalogueId);
                    if (jsonServiceObject != null) {
                        JSONObject serviceObjectJSONObject = jsonServiceObject.getJSONObject("serviceCatalogueDTO");
                        itemName = serviceObjectJSONObject.getString("serviceName");
                        deptName = serviceObjectJSONObject.getString("departmentName");
                    }
                }
                if (entity[4] != null ){
                    filePathId = entity[4].toString();
                } else {
                    filePathId = "none";
                }
                if (entity[5] != null ){
                    filePath = entity[5].toString();
                }
                if(entity[6] != null){
                    investigationNotes = entity[6].toString();
                } else{
                    investigationNotes = "-";
                }
                if(entity[7] != null){
                    doctorSummary = entity[7].toString();
                }
                if(entity[8] != null){
                    investigationDetailId = Long.valueOf(entity[8].toString());
                }
                if(entity[9] != null){
                    filePathContentType = entity[9].toString();
                }
                if(entity[10] != null){
                    labTestedOn = entity[10].toString();
                }
                if (count > 0) {
                    if (labOrderNumber.equalsIgnoreCase(tempLabOrderNumber)) {
                        // item belongs to same lab order number
                        if(deptName.equalsIgnoreCase(tempDept)) {
                            // item belongs to same department
                            if (itemName.equalsIgnoreCase(tempItemName)) {
                                invItemPaths.add(filePathId);
                                itemStatus = "equal";
                            } else {
                                cmInvestigationLabItemDetailGetDTO = new CmInvestigationLabItemDetailGetDTO();
                                invItemPaths = new ArrayList<String>();
                                cmInvestigationLabItemDetailGetDTO.setInvestigationNotes(investigationNotes);
                                cmInvestigationLabItemDetailGetDTO.setServiceCatalogueId(serviceCatalogueId);
                                cmInvestigationLabItemDetailGetDTO.setInvestigationItemName(itemName);
                                cmInvestigationLabItemDetailGetDTO.setInvestigationItemPath(filePath);
                                cmInvestigationLabItemDetailGetDTO.setTestedOn(labTestedOn);
                                cmInvestigationLabItemDetailGetDTO.setInvestigationItemPathContentType(filePathContentType);
                                invItemPaths.add(filePathId);
                                itemStatus = "not-equal";
                            }
                            deptEqualStatus = "equal";

                        } else {
                            // item belongs to different department
                            cmInvestigationLabDetailGetDTO = new CmInvestigationLabDetailGetDTO();
                            cmInvestigationLabItemDetailGetDTOs = new ArrayList<CmInvestigationLabItemDetailGetDTO>();
                            if (itemName.equalsIgnoreCase(tempItemName)) {
                                invItemPaths.add(filePathId);
                                itemStatus = "equal";
                            } else {
                                cmInvestigationLabItemDetailGetDTO = new CmInvestigationLabItemDetailGetDTO();
                                invItemPaths = new ArrayList<String>();
                                cmInvestigationLabItemDetailGetDTO.setInvestigationNotes(investigationNotes);
                                cmInvestigationLabItemDetailGetDTO.setServiceCatalogueId(serviceCatalogueId);
                                cmInvestigationLabItemDetailGetDTO.setInvestigationItemName(itemName);
                                cmInvestigationLabItemDetailGetDTO.setInvestigationItemPath(filePath);
                                cmInvestigationLabItemDetailGetDTO.setTestedOn(labTestedOn);
                                cmInvestigationLabItemDetailGetDTO.setInvestigationItemPathContentType(filePathContentType);
                                invItemPaths.add(filePathId);
                                itemStatus = "not-equal";
                            }

                            deptEqualStatus = "not-equal";
                        }
                        labOrderStatus = "equal";
                    }else {
                        // item belongs to different lab order number
                        cmInvestigationLabItemDetailGetDTO = new CmInvestigationLabItemDetailGetDTO();
                        cmInvestigationDetailsGetDTO  = new CmInvestigationDetailsGetDTO();
                        cmInvestigationLabDetailGetDTO = new CmInvestigationLabDetailGetDTO();
                        cmInvestigationLabItemDetailGetDTOs = new ArrayList<CmInvestigationLabItemDetailGetDTO>();
                        cmInvestigationLabDetailGetDTOs = new ArrayList<CmInvestigationLabDetailGetDTO>();
                        invItemPaths = new ArrayList<String>();
                        //item mappings
                        cmInvestigationLabItemDetailGetDTO.setInvestigationNotes(investigationNotes);
                        cmInvestigationLabItemDetailGetDTO.setServiceCatalogueId(serviceCatalogueId);
                        cmInvestigationLabItemDetailGetDTO.setInvestigationItemName(itemName);
                        cmInvestigationLabItemDetailGetDTO.setInvestigationItemPath(filePath);
                        cmInvestigationLabItemDetailGetDTO.setTestedOn(labTestedOn);
                        cmInvestigationLabItemDetailGetDTO.setInvestigationItemPathContentType(filePathContentType);
                        invItemPaths.add(filePathId);
                        //master investigation mapping (lab order number)
                        cmInvestigationDetailsGetDTO.setCmInvestigationDetailId(investigationDetailId);
                        cmInvestigationDetailsGetDTO.setCmInvestigationId(investigationId);
                        cmInvestigationDetailsGetDTO.setLabOrderNumber(labOrderNumber);
                        cmInvestigationDetailsGetDTO.setLabOrderGivenDate(labOrderCreatedDate);
                        labOrderStatus = "not-equal";
                        itemStatus = "not-equal";
                    }
                } else {
                    //count == 0
                    cmInvestigationLabItemDetailGetDTO = new CmInvestigationLabItemDetailGetDTO();
                    cmInvestigationDetailsGetDTO  = new CmInvestigationDetailsGetDTO();
                    cmInvestigationLabDetailGetDTO = new CmInvestigationLabDetailGetDTO();
                    cmInvestigationLabDetailGetDTOs = new ArrayList<CmInvestigationLabDetailGetDTO>();
                    invItemPaths = new ArrayList<String>();
                    //item mappings
                    cmInvestigationLabItemDetailGetDTO.setInvestigationNotes(investigationNotes);
                    cmInvestigationLabItemDetailGetDTO.setServiceCatalogueId(serviceCatalogueId);
                    cmInvestigationLabItemDetailGetDTO.setInvestigationItemName(itemName);
                    cmInvestigationLabItemDetailGetDTO.setInvestigationItemPath(filePath);
                    cmInvestigationLabItemDetailGetDTO.setTestedOn(labTestedOn);
                    cmInvestigationLabItemDetailGetDTO.setInvestigationItemPathContentType(filePathContentType);
                    invItemPaths.add(filePathId);
                    //master investigation mapping (lab order number)
                    cmInvestigationDetailsGetDTO.setCmInvestigationDetailId(investigationDetailId);
                    cmInvestigationDetailsGetDTO.setCmInvestigationId(investigationId);
                    cmInvestigationDetailsGetDTO.setLabOrderNumber(labOrderNumber);
                    cmInvestigationDetailsGetDTO.setLabOrderGivenDate(labOrderCreatedDate);
                    deptEqualStatus = "not-equal";
                    itemStatus = "not-equal";
                }
                cmInvestigationLabItemDetailGetDTO.setInvestigationItemPaths(invItemPaths);
                if (itemStatus.equalsIgnoreCase("equal")){
                    cmInvestigationLabItemDetailGetDTOs.add(cmInvestigationLabItemDetailGetDTO);
                    cmInvestigationLabItemDetailGetDTOs.remove(cmInvestigationLabItemDetailGetDTOs.size() - 1);
                } else {
                    if (!cmInvestigationLabItemDetailGetDTOs.contains(cmInvestigationLabItemDetailGetDTO)){
                        cmInvestigationLabItemDetailGetDTOs.add(cmInvestigationLabItemDetailGetDTO);
                    }
                }
                cmInvestigationLabDetailGetDTO.setDepartment(deptName);
                //adding item to list
                cmInvestigationLabDetailGetDTO.setCmInvestigationLabItemDetailGetDTOs(cmInvestigationLabItemDetailGetDTOs);
                if (deptEqualStatus.equalsIgnoreCase("equal")){
                    cmInvestigationLabDetailGetDTOs.add(cmInvestigationLabDetailGetDTO);
                    cmInvestigationLabDetailGetDTOs.remove(cmInvestigationLabDetailGetDTOs.size() - 1);
                } else {
                    if (!cmInvestigationLabDetailGetDTOs.contains(cmInvestigationLabDetailGetDTO)){
                        cmInvestigationLabDetailGetDTOs.add(cmInvestigationLabDetailGetDTO);
                    }
                }
                cmInvestigationDetailsGetDTO.setCmInvestigationLabDetailGetDTOs(cmInvestigationLabDetailGetDTOs);
                if (!cmInvestigationDetailsGetDTOs.contains(cmInvestigationDetailsGetDTO)) {
                    cmInvestigationDetailsGetDTOs.add(cmInvestigationDetailsGetDTO);
                }
                tempLabOrderNumber = labOrderNumber;
                tempDept = deptName;
                tempItemName = itemName;
                count++;
                deptEqualStatus = "not-equal";
                labOrderStatus = "not-equal";
                itemStatus = "not-equal";
            }
        } catch (JSONException e) {
           log.error("Failed to get cm investigation by case id" + e.getMessage());
        }
        return cmInvestigationDetailsGetDTOs;
    }

    //method to get cm investigations details by case id for patient copy
    @Override
    public List<CmInvestigationDetailsGetDTO> getPatientCmInvestigationDetails(GetInvestigationDetailsByCaseIdServiceRequest request) {

        long investigationId = (long) 0;
        long investigationDetailId = (long) 0;
        String labOrderNumber = new String();
        String labOrderCreatedDate = new String();
        String labTestedOn = new String();
        long serviceCatalogueId = (long) 0;
        String investigationNotes = new String();
        String doctorSummary = new String();
        String tempLabOrderNumber = new String();
        String tempDept = new String();
        String itemName = new String();
        String deptName = new String();
        String tempItemName = new String();
        List<String> invItemPaths = new ArrayList<String>();
        String itemStatus = new String();
        String deptEqualStatus = new String();
        String labOrderStatus = new String();
        CmInvestigationLabItemDetailGetDTO cmInvestigationLabItemDetailGetDTO = null;
        CmInvestigationLabDetailGetDTO cmInvestigationLabDetailGetDTO = null;
        List<CmInvestigationLabItemDetailGetDTO> cmInvestigationLabItemDetailGetDTOs = new ArrayList<CmInvestigationLabItemDetailGetDTO>();
        List<CmInvestigationLabDetailGetDTO> cmInvestigationLabDetailGetDTOs = new ArrayList<CmInvestigationLabDetailGetDTO>();
        List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs = new ArrayList<CmInvestigationDetailsGetDTO>();
        CmInvestigationDetailsGetDTO cmInvestigationDetailsGetDTO = null;
        int count = 0;
        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        List<Integer> listOfIds = new ArrayList<Integer>();
        long cmMasterDetailsId = (long) 0;
        CmInvestigation cmInvestigation = new CmInvestigation();
        List<CmInvestigationDetail> cmInvestigationDetails = new ArrayList<>();
        try {
            log.debug("Call to get cm investigation by case id");
            //find cm master details by case id
            cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(request.getCaseId());

            for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                cmMasterDetailsId = cmMasterDetailsTemp.getId();
                Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                listOfIds.add(cmMasterDetailsIdINT);
            }
            Integer latestId = Collections.max(listOfIds);
            //retrieve cm investigation by cm master detail id
            cmInvestigation = cmInvestigationRepository.findInvestigationByCmMasterDetailsId(Long.valueOf(latestId));
            if(cmInvestigation != null)
            //retrieve cm investigation details by cm investigation id
            cmInvestigationDetails = cmInvestigationDetailRepository.findByCmInvestigationId(cmInvestigation.getId());
            deptEqualStatus = "not-equal";
            labOrderStatus = "not-equal";
            if(cmInvestigationDetails != null)
            // setting the values to dto
            for (CmInvestigationDetail cmInvestigationDetail : cmInvestigationDetails) {
                investigationId = Long.valueOf(cmInvestigation.getId());
                labOrderNumber = cmInvestigation.getLabOrderNumber();
                labOrderCreatedDate = cmInvestigation.getCreatedDate().toString();
                labTestedOn = cmInvestigationDetail.getTestedOn().toString();
                
               // labOrderCreatedDate = labOrderCreatedDate.substring(0, labOrderCreatedDate.length() - 2);
                    serviceCatalogueId = Long.valueOf(cmInvestigationDetail.getServiceCatalogueId());
                    JSONObject jsonServiceObject = serviceGateway.getServiceCatalogueByServiceId(serviceCatalogueId);
                    if (jsonServiceObject != null) {
                        JSONObject serviceObjectJSONObject = jsonServiceObject.getJSONObject("serviceCatalogueDTO");
                        itemName = serviceObjectJSONObject.getString("serviceName");
                        deptName = serviceObjectJSONObject.getString("departmentName");
                    }

                if(cmInvestigationDetail.getInvestigationNotes() != null){
                    investigationNotes = cmInvestigationDetail.getInvestigationNotes();
                } else{
                    investigationNotes = "-";
                }
                if(cmInvestigation.getDoctorSummary() != null){
                    doctorSummary = cmInvestigation.getDoctorSummary();
                }

                investigationDetailId = cmInvestigationDetail.getId();

                if (count > 0) {
                    if (labOrderNumber.equalsIgnoreCase(tempLabOrderNumber)) {
                        // item belongs to same lab order number
                        if(deptName.equalsIgnoreCase(tempDept)) {
                            // item belongs to same department
                            if (itemName.equalsIgnoreCase(tempItemName)) {
                                itemStatus = "equal";
                            } else {
                                cmInvestigationLabItemDetailGetDTO = new CmInvestigationLabItemDetailGetDTO();
                                invItemPaths = new ArrayList<String>();
                                cmInvestigationLabItemDetailGetDTO.setInvestigationNotes(investigationNotes);
                                cmInvestigationLabItemDetailGetDTO.setServiceCatalogueId(serviceCatalogueId);
                                cmInvestigationLabItemDetailGetDTO.setInvestigationItemName(itemName);
                                cmInvestigationLabItemDetailGetDTO.setTestedOn(labTestedOn);
                                itemStatus = "not-equal";
                            }
                            deptEqualStatus = "equal";

                        } else {
                            // item belongs to different department
                            cmInvestigationLabDetailGetDTO = new CmInvestigationLabDetailGetDTO();
                            cmInvestigationLabItemDetailGetDTOs = new ArrayList<CmInvestigationLabItemDetailGetDTO>();
                            if (itemName.equalsIgnoreCase(tempItemName)) {
                                itemStatus = "equal";
                            } else {
                                cmInvestigationLabItemDetailGetDTO = new CmInvestigationLabItemDetailGetDTO();
                                invItemPaths = new ArrayList<String>();
                                cmInvestigationLabItemDetailGetDTO.setInvestigationNotes(investigationNotes);
                                cmInvestigationLabItemDetailGetDTO.setServiceCatalogueId(serviceCatalogueId);
                                cmInvestigationLabItemDetailGetDTO.setInvestigationItemName(itemName);
                                cmInvestigationLabItemDetailGetDTO.setTestedOn(labTestedOn);
                                itemStatus = "not-equal";
                            }

                            deptEqualStatus = "not-equal";
                        }
                        labOrderStatus = "equal";
                    }else {
                        // item belongs to different lab order number
                        cmInvestigationLabItemDetailGetDTO = new CmInvestigationLabItemDetailGetDTO();
                        cmInvestigationDetailsGetDTO  = new CmInvestigationDetailsGetDTO();
                        cmInvestigationLabDetailGetDTO = new CmInvestigationLabDetailGetDTO();
                        cmInvestigationLabItemDetailGetDTOs = new ArrayList<CmInvestigationLabItemDetailGetDTO>();
                        cmInvestigationLabDetailGetDTOs = new ArrayList<CmInvestigationLabDetailGetDTO>();
                        invItemPaths = new ArrayList<String>();
                        //item mappings
                        cmInvestigationLabItemDetailGetDTO.setInvestigationNotes(investigationNotes);
                        cmInvestigationLabItemDetailGetDTO.setServiceCatalogueId(serviceCatalogueId);
                        cmInvestigationLabItemDetailGetDTO.setInvestigationItemName(itemName);
                        cmInvestigationLabItemDetailGetDTO.setTestedOn(labTestedOn);
                        //master investigation mapping (lab order number)
                        cmInvestigationDetailsGetDTO.setCmInvestigationDetailId(investigationDetailId);
                        cmInvestigationDetailsGetDTO.setCmInvestigationId(investigationId);
                        cmInvestigationDetailsGetDTO.setLabOrderNumber(labOrderNumber);
                        cmInvestigationDetailsGetDTO.setLabOrderGivenDate(labOrderCreatedDate);
                        labOrderStatus = "not-equal";
                        itemStatus = "not-equal";
                    }
                } else {
                    //count == 0
                    cmInvestigationLabItemDetailGetDTO = new CmInvestigationLabItemDetailGetDTO();
                    cmInvestigationDetailsGetDTO  = new CmInvestigationDetailsGetDTO();
                    cmInvestigationLabDetailGetDTO = new CmInvestigationLabDetailGetDTO();
                    cmInvestigationLabDetailGetDTOs = new ArrayList<CmInvestigationLabDetailGetDTO>();
                    invItemPaths = new ArrayList<String>();
                    //item mappings
                    cmInvestigationLabItemDetailGetDTO.setInvestigationNotes(investigationNotes);
                    cmInvestigationLabItemDetailGetDTO.setServiceCatalogueId(serviceCatalogueId);
                    cmInvestigationLabItemDetailGetDTO.setInvestigationItemName(itemName);
                    cmInvestigationLabItemDetailGetDTO.setTestedOn(labTestedOn);
                    //master investigation mapping (lab order number)
                    cmInvestigationDetailsGetDTO.setCmInvestigationDetailId(investigationDetailId);
                    cmInvestigationDetailsGetDTO.setCmInvestigationId(investigationId);
                    cmInvestigationDetailsGetDTO.setLabOrderNumber(labOrderNumber);
                    cmInvestigationDetailsGetDTO.setLabOrderGivenDate(labOrderCreatedDate);
                    deptEqualStatus = "not-equal";
                    itemStatus = "not-equal";
                }
                cmInvestigationLabItemDetailGetDTO.setInvestigationItemPaths(invItemPaths);
                if (itemStatus.equalsIgnoreCase("equal")){
                    cmInvestigationLabItemDetailGetDTOs.add(cmInvestigationLabItemDetailGetDTO);
                    cmInvestigationLabItemDetailGetDTOs.remove(cmInvestigationLabItemDetailGetDTOs.size() - 1);
                } else {
                    if (!cmInvestigationLabItemDetailGetDTOs.contains(cmInvestigationLabItemDetailGetDTO)){
                        cmInvestigationLabItemDetailGetDTOs.add(cmInvestigationLabItemDetailGetDTO);
                    }
                }
                cmInvestigationLabDetailGetDTO.setDepartment(deptName);
                //adding item to list
                cmInvestigationLabDetailGetDTO.setCmInvestigationLabItemDetailGetDTOs(cmInvestigationLabItemDetailGetDTOs);
                if (deptEqualStatus.equalsIgnoreCase("equal")){
                    cmInvestigationLabDetailGetDTOs.add(cmInvestigationLabDetailGetDTO);
                    cmInvestigationLabDetailGetDTOs.remove(cmInvestigationLabDetailGetDTOs.size() - 1);
                } else {
                    if (!cmInvestigationLabDetailGetDTOs.contains(cmInvestigationLabDetailGetDTO)){
                        cmInvestigationLabDetailGetDTOs.add(cmInvestigationLabDetailGetDTO);
                    }
                }
                cmInvestigationDetailsGetDTO.setCmInvestigationLabDetailGetDTOs(cmInvestigationLabDetailGetDTOs);
                if (!cmInvestigationDetailsGetDTOs.contains(cmInvestigationDetailsGetDTO)) {
                    cmInvestigationDetailsGetDTOs.add(cmInvestigationDetailsGetDTO);
                }
                tempLabOrderNumber = labOrderNumber;
                tempDept = deptName;
                tempItemName = itemName;
                count++;
                deptEqualStatus = "not-equal";
                labOrderStatus = "not-equal";
                itemStatus = "not-equal";
            }
        } catch (JSONException e) {
            log.error("Failed to get cm investigation by case id" + e.getMessage());
        }
        return cmInvestigationDetailsGetDTOs;
    }

    //method to retrieve pathya pathya

    @Override
    public CmPathyaPathyaDTO getPathyaPathya(Long caseId) {
        List<CmTreatment> cmTreatmentDetails = new ArrayList<CmTreatment>();
        CmPathyaPathyaDTO cmPathyaPathyaDTO = new CmPathyaPathyaDTO();
        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        List<Integer> listOfIds = new ArrayList<Integer>();
        long cmMasterDetailsId = (long) 0;

        try {
            log.debug("Call to get pathya pathya by case id");
            //find cm master details by case id
            cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(caseId);

            for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                cmMasterDetailsId = cmMasterDetailsTemp.getId();
                Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                listOfIds.add(cmMasterDetailsIdINT);
            }
            Integer latestId = Collections.max(listOfIds);
            for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                cmMasterDetailsId = cmMasterDetailsTemp.getId();
                Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                if (latestId.equals(cmMasterDetailsIdINT)) {
                    //find cm treatment by cm master details id
                    cmTreatmentDetails = cmTreatmentRepository.findTreatmentByCmMasterDetailsId(cmMasterDetailsTemp.getId());
                    for (CmTreatment cmTreatmentTemp : cmTreatmentDetails) {
                        if(cmTreatmentTemp != null)
                            cmPathyaPathyaDTO.setCmTreatmentId(cmTreatmentTemp.getId());
                        cmPathyaPathyaDTO.setCmMasterDetailsId(cmTreatmentTemp.getCmMasterDetails().getId());
                        cmPathyaPathyaDTO.setGivenDate(cmTreatmentTemp.getGivenDate());
                        cmPathyaPathyaDTO.setPathyaPathya(cmTreatmentTemp.getPathyaPathya());
                        cmPathyaPathyaDTO.setTake(cmTreatmentTemp.getTake());
                        cmPathyaPathyaDTO.setTakeMore(cmTreatmentTemp.getTakeMore());
                        cmPathyaPathyaDTO.setShamanam(cmTreatmentTemp.isShamanam());
                        cmPathyaPathyaDTO.setShodhanam(cmTreatmentTemp.isShodhanam());
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to get pathya pathya by case id" + e.getMessage());
        }
        return cmPathyaPathyaDTO;
    }

    //method to retrieve pathya pathya for patient copy
    @Override
    public CmPathyaPathyaDTO getPatientPathyaPathya(Long caseId) {
        List<CmTreatment> cmTreatmentDetails = new ArrayList<CmTreatment>();
        CmPathyaPathyaDTO cmPathyaPathyaDTO = new CmPathyaPathyaDTO();
        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        List<Integer> listOfIds = new ArrayList<Integer>();
        long cmMasterDetailsId = (long) 0;

        try {
            log.debug("Call to get pathya pathya by case id");
            //find cm master details by case id
            cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(caseId);

            for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                cmMasterDetailsId = cmMasterDetailsTemp.getId();
                Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                listOfIds.add(cmMasterDetailsIdINT);
            }
            Integer latestId = Collections.max(listOfIds);

            //find cm treatment by cm master details id
            cmTreatmentDetails = cmTreatmentRepository.findTreatmentByCmMasterDetailsId(Long.valueOf(latestId));
                    for (CmTreatment cmTreatmentTemp : cmTreatmentDetails) {
                        if(cmTreatmentTemp != null)
                            cmPathyaPathyaDTO.setCmTreatmentId(cmTreatmentTemp.getId());
                        cmPathyaPathyaDTO.setCmMasterDetailsId(cmTreatmentTemp.getCmMasterDetails().getId());
                        cmPathyaPathyaDTO.setGivenDate(cmTreatmentTemp.getGivenDate());
                        cmPathyaPathyaDTO.setPathyaPathya(cmTreatmentTemp.getPathyaPathya());
                        cmPathyaPathyaDTO.setTake(cmTreatmentTemp.getTake());
                        cmPathyaPathyaDTO.setTakeMore(cmTreatmentTemp.getTakeMore());
                        cmPathyaPathyaDTO.setShamanam(cmTreatmentTemp.isShamanam());
                        cmPathyaPathyaDTO.setShodhanam(cmTreatmentTemp.isShodhanam());
                    }

        } catch (Exception e) {
            log.error("Failed to get pathya pathya by case id" + e.getMessage());
        }
        return cmPathyaPathyaDTO;
    }

    //method to retrieve personal history details by case
    @Override
    public List<GetCmPersonalHistoryDTO> getPersonalHistory(Long caseId) {
        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        List<CmPersonalHistory> cmPersonalHistories = new ArrayList<>();
        long cmMasterDetailsId = (long) 0;
        List<Integer> listOfIds = new ArrayList<Integer>();
        List<GetCmPersonalHistoryDTO> personalHistoryDetails = new ArrayList<>();

        try {
            log.debug("Call to get personal history of case sheet for case number" + caseId);
            if(caseId != null){
            //find cm master details by cm master
            cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(caseId);

                for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                    cmMasterDetailsId = cmMasterDetailsTemp.getId();
                    Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                    listOfIds.add(cmMasterDetailsIdINT);
                }
                Integer latestId = Collections.max(listOfIds);
                for (CmMasterDetails cmMasterDetailsTemp : cmMasterDetails) {
                    cmMasterDetailsId = cmMasterDetailsTemp.getId();
                    Integer cmMasterDetailsIdINT = (int) (long) cmMasterDetailsId;
                    if (latestId.equals(cmMasterDetailsIdINT)) {
                        cmPersonalHistories = cmPersonalHistoryRepository.getCmPersonalHistoryByCmMasterDetailId(cmMasterDetailsTemp.getId());
                        if(cmPersonalHistories != null)
                        for(CmPersonalHistory cmPersonalHistory : cmPersonalHistories){
                            GetCmPersonalHistoryDTO getCmPersonalHistoryDTO = new GetCmPersonalHistoryDTO();

                            // setting appetite mode
                            if(cmPersonalHistory.getAppetiteObservationData() != null){
                                if (cmPersonalHistory.getAppetiteObservationData().getId() != null)
                                    getCmPersonalHistoryDTO.setAppetiteObservationDataDTO(new CmObservationCategoryDataDTO());
                                    getCmPersonalHistoryDTO.getAppetiteObservationDataDTO().setId(cmPersonalHistory.getAppetiteObservationData().getId());
                                    if (cmPersonalHistory.getAppetiteObservationData().getCmObservationData() != null) {
                                        getCmPersonalHistoryDTO.getAppetiteObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                        getCmPersonalHistoryDTO.getAppetiteObservationDataDTO().getCmObservationDataDTO().setDataName(cmPersonalHistory.getAppetiteObservationData().getCmObservationData().getDataName());
                                    }
                            }

                            // setting bladder mode
                            if(cmPersonalHistory.getBladderObservationData() != null){
                                if (cmPersonalHistory.getBladderObservationData().getId() != null)
                                    getCmPersonalHistoryDTO.setBladderObservationDataDTO(new CmObservationCategoryDataDTO());
                                getCmPersonalHistoryDTO.getBladderObservationDataDTO().setId(cmPersonalHistory.getBladderObservationData().getId());
                                if (cmPersonalHistory.getBladderObservationData().getCmObservationData() != null) {
                                    getCmPersonalHistoryDTO.getBladderObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                    getCmPersonalHistoryDTO.getBladderObservationDataDTO().getCmObservationDataDTO().setDataName(cmPersonalHistory.getBladderObservationData().getCmObservationData().getDataName());
                                }
                            }

                            // setting sleep mode
                            if(cmPersonalHistory.getSleepObservationData() != null){
                                if (cmPersonalHistory.getSleepObservationData().getId() != null)
                                    getCmPersonalHistoryDTO.setSleepObservationDataDTO(new CmObservationCategoryDataDTO());
                                getCmPersonalHistoryDTO.getSleepObservationDataDTO().setId(cmPersonalHistory.getSleepObservationData().getId());
                                if (cmPersonalHistory.getSleepObservationData().getCmObservationData() != null) {
                                    getCmPersonalHistoryDTO.getSleepObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                    getCmPersonalHistoryDTO.getSleepObservationDataDTO().getCmObservationDataDTO().setDataName(cmPersonalHistory.getSleepObservationData().getCmObservationData().getDataName());
                                }
                            }

                            // setting bowel mode
                            if(cmPersonalHistory.getBowelObservationData() != null){
                                if (cmPersonalHistory.getBowelObservationData().getId() != null)
                                    getCmPersonalHistoryDTO.setBowelObservationDataDTO(new CmObservationCategoryDataDTO());
                                getCmPersonalHistoryDTO.getBowelObservationDataDTO().setId(cmPersonalHistory.getBowelObservationData().getId());
                                if (cmPersonalHistory.getBowelObservationData().getCmObservationData() != null) {
                                    getCmPersonalHistoryDTO.getBowelObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                    getCmPersonalHistoryDTO.getBowelObservationDataDTO().getCmObservationDataDTO().setDataName(cmPersonalHistory.getBowelObservationData().getCmObservationData().getDataName());
                                }
                            }

                            // setting diet mode
                            if(cmPersonalHistory.getDietObservationData() != null){
                                if (cmPersonalHistory.getDietObservationData().getId() != null)
                                    getCmPersonalHistoryDTO.setDietObservationDataDTO(new CmObservationCategoryDataDTO());
                                getCmPersonalHistoryDTO.getDietObservationDataDTO().setId(cmPersonalHistory.getDietObservationData().getId());
                                if (cmPersonalHistory.getDietObservationData().getCmObservationData() != null) {
                                    getCmPersonalHistoryDTO.getDietObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                    getCmPersonalHistoryDTO.getDietObservationDataDTO().getCmObservationDataDTO().setDataName(cmPersonalHistory.getDietObservationData().getCmObservationData().getDataName());
                                }
                            }

                            // setting habit mode
                            if(cmPersonalHistory.getHabitObservationData() != null){
                                if (cmPersonalHistory.getHabitObservationData().getId() != null)
                                    getCmPersonalHistoryDTO.setHabitObservationDataDTO(new CmObservationCategoryDataDTO());
                                getCmPersonalHistoryDTO.getHabitObservationDataDTO().setId(cmPersonalHistory.getHabitObservationData().getId());
                                if (cmPersonalHistory.getHabitObservationData().getCmObservationData() != null) {
                                    getCmPersonalHistoryDTO.getHabitObservationDataDTO().setCmObservationDataDTO(new CmObservationDataDTO());
                                    getCmPersonalHistoryDTO.getHabitObservationDataDTO().getCmObservationDataDTO().setDataName(cmPersonalHistory.getHabitObservationData().getCmObservationData().getDataName());
                                }
                            }
                            personalHistoryDetails.add(getCmPersonalHistoryDTO);
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("Failed to retrieve personal history of case sheet" + e.getMessage() );
        }

        return personalHistoryDetails;
    }

    @Override
    public List<GetCmExaminationDTO> getExaminationDetails(Long caseId) {
        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        List<CmExamntnDetail> cmExaminationDetail = new ArrayList<CmExamntnDetail>();
        List<GetCmExaminationDTO> getCmExaminationDTOs = new ArrayList<>();
        String examinationType = new String();
        long examinationTypeId = (long) 0;
        long examId = (long) 0;
        int examCount = 0;
        boolean dashavidhaStatus = false;
        boolean asthaVidhaStatus = false;
        boolean generalStatus = false;
        boolean sarvaSrotoStatus = false;
        boolean sampraptiStatus = false;
        ExaminationDetailDTO examinationDetailDTO = null;
        AllExaminationDTO allExaminationDTO = null;
        List<AllExaminationDTO> allExaminationDTOs = null;
        CmExamntnDashavidha dashavidha = new CmExamntnDashavidha();
        CmExamntnAsthaVidhaPareeksha asthaVidha = new CmExamntnAsthaVidhaPareeksha();
        CmExamntnGeneral general = new CmExamntnGeneral();
        CmExamntnSarvaSrotoPareeksha sarvaSrotoPareeksha = new CmExamntnSarvaSrotoPareeksha();
        CmExamntnSampraptiGhatakas samprapti = new CmExamntnSampraptiGhatakas();
        CmExamntnDashavidhaDTO cmExamntnDashavidhaDTO = new CmExamntnDashavidhaDTO();
        CmExamntnAsthaVidhaPareekshaDTO cmExamntnAsthaVidhaPareekshaDTO = new CmExamntnAsthaVidhaPareekshaDTO();
        CmExamntnGeneralDTO cmExamntnGeneralDTO = new CmExamntnGeneralDTO();
        CmExamntnSarvaSrotoPareekshaDTO cmExamntnSarvaSrotoPareekshaDTO = new CmExamntnSarvaSrotoPareekshaDTO();
        CmExamntnSampraptiGhatakasDTO cmExamntnSampraptiGhatakasDTO = new CmExamntnSampraptiGhatakasDTO();

        try {
            log.debug("Call to get examination for a case sheet");
            if(caseId != null) {
                //find cm master details by cm master
                cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(caseId);
                for (CmMasterDetails cmMasterDetailTemp : cmMasterDetails) {
                    cmExaminationDetail = cmExamntnDetailRepository.getExamDetailsByCmMasterDetails(cmMasterDetailTemp.getId());
                    examinationDetailDTO = new ExaminationDetailDTO();
                    allExaminationDTO = new AllExaminationDTO();
                    GetCmExaminationDTO getCmExaminationDTO = new GetCmExaminationDTO();
                    for (CmExamntnDetail cmExamntnDetailTemp : cmExaminationDetail) {
                        examinationType = cmExamntnDetailTemp.getCmExaminationType().getTypeName();
                        examinationTypeId = cmExamntnDetailTemp.getCmExaminationType().getId();
                        examId = cmExamntnDetailTemp.getCmExamId();
                        if (examCount == 0) {
                            allExaminationDTOs = new ArrayList<>();
                            examinationDetailDTO = new ExaminationDetailDTO();
                            examinationDetailDTO.setDetailId(cmMasterDetailTemp.getId());
                            examinationDetailDTO.setExamDate(cmMasterDetailTemp.getCaseCreatedDate());
                            //for dashavidha
                            if (examinationType.equalsIgnoreCase("Dashavidha")) {
                                dashavidhaStatus = true;
                                dashavidha = cmExamntnDashavidhaRepository.findOne(examId);
                                cmExamntnDashavidhaDTO = new CmExamntnDashavidhaDTO();
                                cmExamntnDashavidhaDTO = frameCmExamDashavidha(dashavidha);
                            }
                            //for astha vidha
                            if(examinationType.equalsIgnoreCase("Astha Vidha Pareeksha")){
                                asthaVidhaStatus = true;
                                asthaVidha = cmExamntnAsthaVidhaPareekshaRepository.findOne(examId);
                                cmExamntnAsthaVidhaPareekshaDTO = new CmExamntnAsthaVidhaPareekshaDTO();
                                cmExamntnAsthaVidhaPareekshaDTO = frameAsthaVidha(asthaVidha);
                            }
                            //for general
                            if (examinationType.equalsIgnoreCase("General")) {
                                generalStatus = true;
                                general = cmExamntnGeneralRepository.findOne(examId);
                                cmExamntnGeneralDTO = new CmExamntnGeneralDTO();
                                cmExamntnGeneralDTO = frameGeneralExam(general);
                            }
                            //for sarva sroto
                            if (examinationType.equalsIgnoreCase("Sarva Sroto Pareeksha")) {
                                sarvaSrotoStatus = true;
                                sarvaSrotoPareeksha = cmExamntnSarvaSrotoPareekshaRepository.findOne(examId);
                                cmExamntnSarvaSrotoPareekshaDTO = new CmExamntnSarvaSrotoPareekshaDTO();
                                cmExamntnSarvaSrotoPareekshaDTO = frameSarvaSroto(sarvaSrotoPareeksha);
                            }
                            //for samprapti
                            if (examinationType.equalsIgnoreCase("Samprapti Ghatakas")) {
                                sampraptiStatus = true;
                                samprapti = cmExamntnSampraptiGhatakasRepository.findOne(examId);
                                cmExamntnSampraptiGhatakasDTO = new CmExamntnSampraptiGhatakasDTO();
                                cmExamntnSampraptiGhatakasDTO = frameSamprapti(samprapti);
                            }
                        }else{
                            // exam count > 0
                            //for dashavidha
                            if (examinationType.equalsIgnoreCase("Dashavidha")) {
                                dashavidhaStatus = true;
                                dashavidha = cmExamntnDashavidhaRepository.findOne(examId);
                                cmExamntnDashavidhaDTO = new CmExamntnDashavidhaDTO();
                                cmExamntnDashavidhaDTO = frameCmExamDashavidha(dashavidha);
                            }
                            //for astha vidha
                            if(examinationType.equalsIgnoreCase("Astha Vidha Pareeksha")){
                                asthaVidhaStatus = true;
                                asthaVidha = cmExamntnAsthaVidhaPareekshaRepository.findOne(examId);
                                cmExamntnAsthaVidhaPareekshaDTO = new CmExamntnAsthaVidhaPareekshaDTO();
                                cmExamntnAsthaVidhaPareekshaDTO = frameAsthaVidha(asthaVidha);
                            }
                            //for general
                            if (examinationType.equalsIgnoreCase("General")) {
                                generalStatus = true;
                                general = cmExamntnGeneralRepository.findOne(examId);
                                cmExamntnGeneralDTO = new CmExamntnGeneralDTO();
                                cmExamntnGeneralDTO = frameGeneralExam(general);
                            }
                            //for sarva sroto
                            if (examinationType.equalsIgnoreCase("Sarva Sroto Pareeksha")) {
                                sarvaSrotoStatus = true;
                                sarvaSrotoPareeksha = cmExamntnSarvaSrotoPareekshaRepository.findOne(examId);
                                cmExamntnSarvaSrotoPareekshaDTO = new CmExamntnSarvaSrotoPareekshaDTO();
                                cmExamntnSarvaSrotoPareekshaDTO = frameSarvaSroto(sarvaSrotoPareeksha);
                            }
                            //for samprapti
                            if (examinationType.equalsIgnoreCase("Samprapti Ghatakas")) {
                                sampraptiStatus = true;
                                samprapti = cmExamntnSampraptiGhatakasRepository.findOne(examId);
                                cmExamntnSampraptiGhatakasDTO = new CmExamntnSampraptiGhatakasDTO();
                                cmExamntnSampraptiGhatakasDTO = frameSamprapti(samprapti);
                            }
                        }
                        allExaminationDTOs.add(allExaminationDTO);
                        examinationDetailDTO.setAllExaminationDTOs(allExaminationDTOs);
                        getCmExaminationDTO.setExaminationDetailDTO(examinationDetailDTO);

                    }
                    if (!getCmExaminationDTOs.contains(examinationDetailDTO)) {
                        getCmExaminationDTOs.add(getCmExaminationDTO);
                    }
                   // exam loop over
                    examCount = 0;
                    if (dashavidhaStatus) {
                        allExaminationDTO.setCmExamntnDashavidhaDTO(cmExamntnDashavidhaDTO);
                    }
                    if (asthaVidhaStatus) {
                        allExaminationDTO.setCmExamntnAsthaVidhaPareekshaDTO(cmExamntnAsthaVidhaPareekshaDTO);
                    }
                    if (generalStatus) {
                        allExaminationDTO.setCmExamntnGeneralDTO(cmExamntnGeneralDTO);
                    }
                    if (sarvaSrotoStatus) {
                        allExaminationDTO.setCmExamntnSarvaSrotoPareekshaDTO(cmExamntnSarvaSrotoPareekshaDTO);
                    }
                    if (sampraptiStatus) {
                        allExaminationDTO.setCmExamntnSampraptiGhatakasDTO(cmExamntnSampraptiGhatakasDTO);
                    }

                    dashavidhaStatus = false;
                    asthaVidhaStatus = false;
                    sampraptiStatus = false;
                    generalStatus = false;
                    sarvaSrotoStatus = false;

                }


            }
        } catch (Exception e) {
            log.error("Failed to frame examination details for case sheet" + e.getMessage());
        }
        return getCmExaminationDTOs;
    }

    //method to frame cm exam dashavidha
    private CmExamntnDashavidhaDTO frameCmExamDashavidha(CmExamntnDashavidha dashavidha) {
        CmExamntnDashavidhaDTO cmExamntnDashavidhaDTO = new CmExamntnDashavidhaDTO();
        try {
            log.debug("Call to frame cm exam dasha vidha");
            cmExamntnDashavidhaDTO.setDashavidhaId(dashavidha.getId());
            // setting prakruthi mode
            if(dashavidha.getPrakruthi() != null){
                if (dashavidha.getPrakruthi().getId() != null)
                    cmExamntnDashavidhaDTO.setPrakruthi(new CmObservationCategoryDataDTO());
                cmExamntnDashavidhaDTO.getPrakruthi().setId(dashavidha.getPrakruthi().getId());
                if (dashavidha.getPrakruthi().getCmObservationData() != null) {
                    cmExamntnDashavidhaDTO.getPrakruthi().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnDashavidhaDTO.getPrakruthi().getCmObservationDataDTO().setDataName(dashavidha.getPrakruthi().getCmObservationData().getDataName());
                }
            }

            // setting satva mode
            if(dashavidha.getSatva() != null){
                if (dashavidha.getSatva().getId() != null)
                    cmExamntnDashavidhaDTO.setSatva(new CmObservationCategoryDataDTO());
                cmExamntnDashavidhaDTO.getSatva().setId(dashavidha.getSatva().getId());
                if (dashavidha.getSatva().getCmObservationData() != null) {
                    cmExamntnDashavidhaDTO.getSatva().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnDashavidhaDTO.getSatva().getCmObservationDataDTO().setDataName(dashavidha.getSatva().getCmObservationData().getDataName());
                }
            }

            // setting sara mode
            if(dashavidha.getSara() != null){
                if (dashavidha.getSara().getId() != null)
                    cmExamntnDashavidhaDTO.setSara(new CmObservationCategoryDataDTO());
                cmExamntnDashavidhaDTO.getSara().setId(dashavidha.getSara().getId());
                if (dashavidha.getSara().getCmObservationData() != null) {
                    cmExamntnDashavidhaDTO.getSara().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnDashavidhaDTO.getSara().getCmObservationDataDTO().setDataName(dashavidha.getSara().getCmObservationData().getDataName());
                }
            }

            // setting samhanana mode
            if(dashavidha.getSamhanana() != null){
                if (dashavidha.getSamhanana().getId() != null)
                    cmExamntnDashavidhaDTO.setSamhanana(new CmObservationCategoryDataDTO());
                cmExamntnDashavidhaDTO.getSamhanana().setId(dashavidha.getSamhanana().getId());
                if (dashavidha.getSamhanana().getCmObservationData() != null) {
                    cmExamntnDashavidhaDTO.getSamhanana().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnDashavidhaDTO.getSamhanana().getCmObservationDataDTO().setDataName(dashavidha.getSamhanana().getCmObservationData().getDataName());
                }
            }

            // setting aharaShakthi mode
            if(dashavidha.getAharaShakthi() != null){
                if (dashavidha.getAharaShakthi().getId() != null)
                    cmExamntnDashavidhaDTO.setAharaShakthi(new CmObservationCategoryDataDTO());
                cmExamntnDashavidhaDTO.getAharaShakthi().setId(dashavidha.getAharaShakthi().getId());
                if (dashavidha.getAharaShakthi().getCmObservationData() != null) {
                    cmExamntnDashavidhaDTO.getAharaShakthi().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnDashavidhaDTO.getAharaShakthi().getCmObservationDataDTO().setDataName(dashavidha.getAharaShakthi().getCmObservationData().getDataName());
                }
            }

            // setting vyayamaShakthi mode
            if(dashavidha.getVyayamaShakthi() != null){
                if (dashavidha.getVyayamaShakthi().getId() != null)
                    cmExamntnDashavidhaDTO.setVyayamaShakthi(new CmObservationCategoryDataDTO());
                cmExamntnDashavidhaDTO.getVyayamaShakthi().setId(dashavidha.getVyayamaShakthi().getId());
                if (dashavidha.getVyayamaShakthi().getCmObservationData() != null) {
                    cmExamntnDashavidhaDTO.getVyayamaShakthi().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnDashavidhaDTO.getVyayamaShakthi().getCmObservationDataDTO().setDataName(dashavidha.getVyayamaShakthi().getCmObservationData().getDataName());
                }
            }

            // setting pramana mode
            if(dashavidha.getPramana() != null){
                if (dashavidha.getPramana().getId() != null)
                    cmExamntnDashavidhaDTO.setPramana(new CmObservationCategoryDataDTO());
                cmExamntnDashavidhaDTO.getPramana().setId(dashavidha.getPramana().getId());
                if (dashavidha.getPramana().getCmObservationData() != null) {
                    cmExamntnDashavidhaDTO.getPramana().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnDashavidhaDTO.getPramana().getCmObservationDataDTO().setDataName(dashavidha.getPramana().getCmObservationData().getDataName());
                }
            }

            // setting vayaha mode
            if(dashavidha.getVayaha() != null){
                if (dashavidha.getVayaha().getId() != null)
                    cmExamntnDashavidhaDTO.setVayaha(new CmObservationCategoryDataDTO());
                cmExamntnDashavidhaDTO.getVayaha().setId(dashavidha.getVayaha().getId());
                if (dashavidha.getVayaha().getCmObservationData() != null) {
                    cmExamntnDashavidhaDTO.getVayaha().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnDashavidhaDTO.getVayaha().getCmObservationDataDTO().setDataName(dashavidha.getVayaha().getCmObservationData().getDataName());
                }
            }

            // setting satmya mode
            if(dashavidha.getSatmya() != null){
                if (dashavidha.getSatmya().getId() != null)
                    cmExamntnDashavidhaDTO.setSatmya(new CmObservationCategoryDataDTO());
                cmExamntnDashavidhaDTO.getSatmya().setId(dashavidha.getSatmya().getId());
                if (dashavidha.getSatmya().getCmObservationData() != null) {
                    cmExamntnDashavidhaDTO.getSatmya().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnDashavidhaDTO.getSatmya().getCmObservationDataDTO().setDataName(dashavidha.getSatmya().getCmObservationData().getDataName());
                }
            }

            // setting vikruthi mode
            if(dashavidha.getVikruthi() != null){
                if (dashavidha.getVikruthi().getId() != null)
                    cmExamntnDashavidhaDTO.setVikruthi(new CmObservationCategoryDataDTO());
                cmExamntnDashavidhaDTO.getVikruthi().setId(dashavidha.getVikruthi().getId());
                if (dashavidha.getVikruthi().getCmObservationData() != null) {
                    cmExamntnDashavidhaDTO.getVikruthi().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnDashavidhaDTO.getVikruthi().getCmObservationDataDTO().setDataName(dashavidha.getVikruthi().getCmObservationData().getDataName());
                }
            }
        } catch (Exception e) {
            log.error("Failed to frame cm exam dasha vidha" + e.getMessage());
        }
        return cmExamntnDashavidhaDTO;
    }

    //method to frame astha vidha pareeksha
    private CmExamntnAsthaVidhaPareekshaDTO frameAsthaVidha(CmExamntnAsthaVidhaPareeksha asthaVidha) {
        CmExamntnAsthaVidhaPareekshaDTO cmExamntnAsthaVidhaPareekshaDTO = new CmExamntnAsthaVidhaPareekshaDTO();
        try {
            log.debug("Call to frame astha vidha exam");
            cmExamntnAsthaVidhaPareekshaDTO.setAsthaVidhaId(asthaVidha.getId());
            // setting akriti mode
            if(asthaVidha.getAkriti() != null){
                if (asthaVidha.getAkriti().getId() != null)
                    cmExamntnAsthaVidhaPareekshaDTO.setAkriti(new CmObservationCategoryDataDTO());
                cmExamntnAsthaVidhaPareekshaDTO.getAkriti().setId(asthaVidha.getAkriti().getId());
                if (asthaVidha.getAkriti().getCmObservationData() != null) {
                    cmExamntnAsthaVidhaPareekshaDTO.getAkriti().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnAsthaVidhaPareekshaDTO.getAkriti().getCmObservationDataDTO().setDataName(asthaVidha.getAkriti().getCmObservationData().getDataName());
                }
            }

            // setting sabda mode
            if(asthaVidha.getSabda() != null){
                if (asthaVidha.getSabda().getId() != null)
                    cmExamntnAsthaVidhaPareekshaDTO.setSabda(new CmObservationCategoryDataDTO());
                cmExamntnAsthaVidhaPareekshaDTO.getSabda().setId(asthaVidha.getSabda().getId());
                if (asthaVidha.getSabda().getCmObservationData() != null) {
                    cmExamntnAsthaVidhaPareekshaDTO.getSabda().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnAsthaVidhaPareekshaDTO.getSabda().getCmObservationDataDTO().setDataName(asthaVidha.getSabda().getCmObservationData().getDataName());
                }
            }

            // setting netra mode
            if(asthaVidha.getNetra() != null){
                if (asthaVidha.getNetra().getId() != null)
                    cmExamntnAsthaVidhaPareekshaDTO.setNetra(new CmObservationCategoryDataDTO());
                cmExamntnAsthaVidhaPareekshaDTO.getNetra().setId(asthaVidha.getNetra().getId());
                if (asthaVidha.getNetra().getCmObservationData() != null) {
                    cmExamntnAsthaVidhaPareekshaDTO.getNetra().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnAsthaVidhaPareekshaDTO.getNetra().getCmObservationDataDTO().setDataName(asthaVidha.getNetra().getCmObservationData().getDataName());
                }
            }

            // setting sparsha mode
            if(asthaVidha.getSparsha() != null){
                if (asthaVidha.getSparsha().getId() != null)
                    cmExamntnAsthaVidhaPareekshaDTO.setSparsha(new CmObservationCategoryDataDTO());
                cmExamntnAsthaVidhaPareekshaDTO.getSparsha().setId(asthaVidha.getSparsha().getId());
                if (asthaVidha.getSparsha().getCmObservationData() != null) {
                    cmExamntnAsthaVidhaPareekshaDTO.getSparsha().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnAsthaVidhaPareekshaDTO.getSparsha().getCmObservationDataDTO().setDataName(asthaVidha.getSparsha().getCmObservationData().getDataName());
                }
            }

            // setting mutra mode
            if(asthaVidha.getMutra() != null){
                if (asthaVidha.getMutra().getId() != null)
                    cmExamntnAsthaVidhaPareekshaDTO.setMutra(new CmObservationCategoryDataDTO());
                cmExamntnAsthaVidhaPareekshaDTO.getMutra().setId(asthaVidha.getMutra().getId());
                if (asthaVidha.getMutra().getCmObservationData() != null) {
                    cmExamntnAsthaVidhaPareekshaDTO.getMutra().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnAsthaVidhaPareekshaDTO.getMutra().getCmObservationDataDTO().setDataName(asthaVidha.getMutra().getCmObservationData().getDataName());
                }
            }

            // setting mala mode
            if(asthaVidha.getMala() != null){
                if (asthaVidha.getMala().getId() != null)
                    cmExamntnAsthaVidhaPareekshaDTO.setMala(new CmObservationCategoryDataDTO());
                cmExamntnAsthaVidhaPareekshaDTO.getMala().setId(asthaVidha.getMala().getId());
                if (asthaVidha.getMala().getCmObservationData() != null) {
                    cmExamntnAsthaVidhaPareekshaDTO.getMala().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnAsthaVidhaPareekshaDTO.getMala().getCmObservationDataDTO().setDataName(asthaVidha.getMala().getCmObservationData().getDataName());
                }
            }

            // setting nadi mode
            if(asthaVidha.getNadi() != null){
                if (asthaVidha.getNadi().getId() != null)
                    cmExamntnAsthaVidhaPareekshaDTO.setNadi(new CmObservationCategoryDataDTO());
                cmExamntnAsthaVidhaPareekshaDTO.getNadi().setId(asthaVidha.getNadi().getId());
                if (asthaVidha.getNadi().getCmObservationData() != null) {
                    cmExamntnAsthaVidhaPareekshaDTO.getNadi().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnAsthaVidhaPareekshaDTO.getNadi().getCmObservationDataDTO().setDataName(asthaVidha.getNadi().getCmObservationData().getDataName());
                }
            }

            // setting jivha mode
            if(asthaVidha.getJivha() != null){
                if (asthaVidha.getJivha().getId() != null)
                    cmExamntnAsthaVidhaPareekshaDTO.setJivha(new CmObservationCategoryDataDTO());
                cmExamntnAsthaVidhaPareekshaDTO.getJivha().setId(asthaVidha.getJivha().getId());
                if (asthaVidha.getJivha().getCmObservationData() != null) {
                    cmExamntnAsthaVidhaPareekshaDTO.getJivha().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnAsthaVidhaPareekshaDTO.getJivha().getCmObservationDataDTO().setDataName(asthaVidha.getJivha().getCmObservationData().getDataName());
                }
            }
        } catch (Exception e) {
            log.error("Failed to frame astha vidha pareeksha" + e.getMessage());
        }
        return cmExamntnAsthaVidhaPareekshaDTO;
    }


    //method to frame general exam
    private CmExamntnGeneralDTO frameGeneralExam(CmExamntnGeneral general) {
        CmExamntnGeneralDTO cmExamntnGeneralDTO = new CmExamntnGeneralDTO();
        try {
            log.debug("Call to frame general examination");
            cmExamntnGeneralDTO.setGeneralId(general.getId());
            // setting conjuctiva mode
            if(general.getConjuctiva() != null){
                if (general.getConjuctiva().getId() != null)
                    cmExamntnGeneralDTO.setConjuctiva(new CmObservationCategoryDataDTO());
                cmExamntnGeneralDTO.getConjuctiva().setId(general.getConjuctiva().getId());
                if (general.getConjuctiva().getCmObservationData() != null) {
                    cmExamntnGeneralDTO.getConjuctiva().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnGeneralDTO.getConjuctiva().getCmObservationDataDTO().setDataName(general.getConjuctiva().getCmObservationData().getDataName());
                }
            }

            // setting tongue mode
            if(general.getTongue() != null){
                if (general.getTongue().getId() != null)
                    cmExamntnGeneralDTO.setTongue(new CmObservationCategoryDataDTO());
                cmExamntnGeneralDTO.getTongue().setId(general.getTongue().getId());
                if (general.getTongue().getCmObservationData() != null) {
                    cmExamntnGeneralDTO.getTongue().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnGeneralDTO.getTongue().getCmObservationDataDTO().setDataName(general.getTongue().getCmObservationData().getDataName());
                }
            }

            // setting nails mode
            if(general.getNails() != null){
                if (general.getNails().getId() != null)
                    cmExamntnGeneralDTO.setNails(new CmObservationCategoryDataDTO());
                cmExamntnGeneralDTO.getNails().setId(general.getNails().getId());
                if (general.getNails().getCmObservationData() != null) {
                    cmExamntnGeneralDTO.getNails().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnGeneralDTO.getNails().getCmObservationDataDTO().setDataName(general.getNails().getCmObservationData().getDataName());
                }
            }

            // setting pulse mode
            if(general.getPulse() != null){
                if (general.getPulse().getId() != null)
                    cmExamntnGeneralDTO.setPulse(new CmObservationCategoryDataDTO());
                cmExamntnGeneralDTO.getPulse().setId(general.getPulse().getId());
                if (general.getPulse().getCmObservationData() != null) {
                    cmExamntnGeneralDTO.getPulse().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnGeneralDTO.getPulse().getCmObservationDataDTO().setDataName(general.getPulse().getCmObservationData().getDataName());
                }
            }

            // setting skin mode
            if(general.getSkin() != null){
                if (general.getSkin().getId() != null)
                    cmExamntnGeneralDTO.setSkin(new CmObservationCategoryDataDTO());
                cmExamntnGeneralDTO.getSkin().setId(general.getSkin().getId());
                if (general.getSkin().getCmObservationData() != null) {
                    cmExamntnGeneralDTO.getSkin().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnGeneralDTO.getSkin().getCmObservationDataDTO().setDataName(general.getSkin().getCmObservationData().getDataName());
                }
            }
            //set blood Pressure
            if(general.getBloodPressure() != null){
                cmExamntnGeneralDTO.setBloodPressure(general.getBloodPressure());
            }
        } catch (Exception e) {
            log.error("Failed to form general examination" + e.getMessage());
        }
        return cmExamntnGeneralDTO;
    }

    //method to frame sarva sroto pareeksha
    private CmExamntnSarvaSrotoPareekshaDTO frameSarvaSroto(CmExamntnSarvaSrotoPareeksha sarvaSrotoPareeksha) {
        CmExamntnSarvaSrotoPareekshaDTO cmExamntnSarvaSrotoPareekshaDTO = new CmExamntnSarvaSrotoPareekshaDTO();
        try {
            log.debug("Call to frame sarva sroto pareeksha");
            cmExamntnSarvaSrotoPareekshaDTO.setSarvaSrotoId(sarvaSrotoPareeksha.getId());

            // setting cvs mode
            if(sarvaSrotoPareeksha.getCvs() != null){
                if (sarvaSrotoPareeksha.getCvs().getId() != null)
                    cmExamntnSarvaSrotoPareekshaDTO.setCvs(new CmObservationCategoryDataDTO());
                cmExamntnSarvaSrotoPareekshaDTO.getCvs().setId(sarvaSrotoPareeksha.getCvs().getId());
                if (sarvaSrotoPareeksha.getCvs().getCmObservationData() != null) {
                    cmExamntnSarvaSrotoPareekshaDTO.getCvs().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSarvaSrotoPareekshaDTO.getCvs().getCmObservationDataDTO().setDataName(sarvaSrotoPareeksha.getCvs().getCmObservationData().getDataName());
                }
            }

            // setting rs mode
            if(sarvaSrotoPareeksha.getRs() != null){
                if (sarvaSrotoPareeksha.getRs().getId() != null)
                    cmExamntnSarvaSrotoPareekshaDTO.setRs(new CmObservationCategoryDataDTO());
                cmExamntnSarvaSrotoPareekshaDTO.getRs().setId(sarvaSrotoPareeksha.getRs().getId());
                if (sarvaSrotoPareeksha.getRs().getCmObservationData() != null) {
                    cmExamntnSarvaSrotoPareekshaDTO.getRs().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSarvaSrotoPareekshaDTO.getRs().getCmObservationDataDTO().setDataName(sarvaSrotoPareeksha.getRs().getCmObservationData().getDataName());
                }
            }

            // setting pa mode
            if(sarvaSrotoPareeksha.getPa() != null){
                if (sarvaSrotoPareeksha.getPa().getId() != null)
                    cmExamntnSarvaSrotoPareekshaDTO.setPa(new CmObservationCategoryDataDTO());
                cmExamntnSarvaSrotoPareekshaDTO.getPa().setId(sarvaSrotoPareeksha.getPa().getId());
                if (sarvaSrotoPareeksha.getPa().getCmObservationData() != null) {
                    cmExamntnSarvaSrotoPareekshaDTO.getPa().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSarvaSrotoPareekshaDTO.getPa().getCmObservationDataDTO().setDataName(sarvaSrotoPareeksha.getPa().getCmObservationData().getDataName());
                }
            }

            // setting cns mode
            if(sarvaSrotoPareeksha.getCns() != null){
                if (sarvaSrotoPareeksha.getCns().getId() != null)
                    cmExamntnSarvaSrotoPareekshaDTO.setCns(new CmObservationCategoryDataDTO());
                cmExamntnSarvaSrotoPareekshaDTO.getCns().setId(sarvaSrotoPareeksha.getCns().getId());
                if (sarvaSrotoPareeksha.getCns().getCmObservationData() != null) {
                    cmExamntnSarvaSrotoPareekshaDTO.getCns().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSarvaSrotoPareekshaDTO.getCns().getCmObservationDataDTO().setDataName(sarvaSrotoPareeksha.getCns().getCmObservationData().getDataName());
                }
            }

            // setting pr mode
            if(sarvaSrotoPareeksha.getPr() != null){
                if (sarvaSrotoPareeksha.getPr().getId() != null)
                    cmExamntnSarvaSrotoPareekshaDTO.setPr(new CmObservationCategoryDataDTO());
                cmExamntnSarvaSrotoPareekshaDTO.getPr().setId(sarvaSrotoPareeksha.getPr().getId());
                if (sarvaSrotoPareeksha.getPr().getCmObservationData() != null) {
                    cmExamntnSarvaSrotoPareekshaDTO.getPr().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSarvaSrotoPareekshaDTO.getPr().getCmObservationDataDTO().setDataName(sarvaSrotoPareeksha.getPr().getCmObservationData().getDataName());
                }
            }

            if(sarvaSrotoPareeksha.getLocalExamination() != null){
                cmExamntnSarvaSrotoPareekshaDTO.setLocalExamination(sarvaSrotoPareeksha.getLocalExamination());
            }

            if(sarvaSrotoPareeksha.getLocoMotorSystem() != null){
                cmExamntnSarvaSrotoPareekshaDTO.setLocoMotorSystem(sarvaSrotoPareeksha.getLocoMotorSystem());
            }
        } catch (Exception e) {
            log.error("Failed to frame sarva sroto pareeksha" + e.getMessage());
        }
        return cmExamntnSarvaSrotoPareekshaDTO;
    }

    // method to frame samprapti ghatakas
    private CmExamntnSampraptiGhatakasDTO frameSamprapti(CmExamntnSampraptiGhatakas samprapti) {
        CmExamntnSampraptiGhatakasDTO cmExamntnSampraptiGhatakasDTO = new CmExamntnSampraptiGhatakasDTO();
        try {
            log.debug("Call to frame samprapti ghatakas");
            cmExamntnSampraptiGhatakasDTO.setSampraptiId(samprapti.getId());
            // setting dosha mode
            if(samprapti.getDosha() != null){
                if (samprapti.getDosha().getId() != null)
                    cmExamntnSampraptiGhatakasDTO.setDosha(new CmObservationCategoryDataDTO());
                cmExamntnSampraptiGhatakasDTO.getDosha().setId(samprapti.getDosha().getId());
                if (samprapti.getDosha().getCmObservationData() != null) {
                    cmExamntnSampraptiGhatakasDTO.getDosha().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSampraptiGhatakasDTO.getDosha().getCmObservationDataDTO().setDataName(samprapti.getDosha().getCmObservationData().getDataName());
                }
            }

            // setting dooshya mode
            if(samprapti.getDooshya() != null){
                if (samprapti.getDooshya().getId() != null)
                    cmExamntnSampraptiGhatakasDTO.setDooshya(new CmObservationCategoryDataDTO());
                cmExamntnSampraptiGhatakasDTO.getDooshya().setId(samprapti.getDooshya().getId());
                if (samprapti.getDooshya().getCmObservationData() != null) {
                    cmExamntnSampraptiGhatakasDTO.getDooshya().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSampraptiGhatakasDTO.getDooshya().getCmObservationDataDTO().setDataName(samprapti.getDooshya().getCmObservationData().getDataName());
                }
            }

            // setting srotas mode
            if(samprapti.getSrotas() != null){
                if (samprapti.getSrotas().getId() != null)
                    cmExamntnSampraptiGhatakasDTO.setSrotas(new CmObservationCategoryDataDTO());
                cmExamntnSampraptiGhatakasDTO.getSrotas().setId(samprapti.getSrotas().getId());
                if (samprapti.getSrotas().getCmObservationData() != null) {
                    cmExamntnSampraptiGhatakasDTO.getSrotas().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSampraptiGhatakasDTO.getSrotas().getCmObservationDataDTO().setDataName(samprapti.getSrotas().getCmObservationData().getDataName());
                }
            }

            // setting srotoDushti mode
            if(samprapti.getSrotoDushti() != null){
                if (samprapti.getSrotoDushti().getId() != null)
                    cmExamntnSampraptiGhatakasDTO.setSrotoDushti(new CmObservationCategoryDataDTO());
                cmExamntnSampraptiGhatakasDTO.getSrotoDushti().setId(samprapti.getSrotoDushti().getId());
                if (samprapti.getSrotoDushti().getCmObservationData() != null) {
                    cmExamntnSampraptiGhatakasDTO.getSrotoDushti().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSampraptiGhatakasDTO.getSrotoDushti().getCmObservationDataDTO().setDataName(samprapti.getSrotoDushti().getCmObservationData().getDataName());
                }
            }

            // setting rogaMarga mode
            if(samprapti.getRogaMarga() != null){
                if (samprapti.getRogaMarga().getId() != null)
                    cmExamntnSampraptiGhatakasDTO.setRogaMarga(new CmObservationCategoryDataDTO());
                cmExamntnSampraptiGhatakasDTO.getRogaMarga().setId(samprapti.getRogaMarga().getId());
                if (samprapti.getRogaMarga().getCmObservationData() != null) {
                    cmExamntnSampraptiGhatakasDTO.getRogaMarga().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSampraptiGhatakasDTO.getRogaMarga().getCmObservationDataDTO().setDataName(samprapti.getRogaMarga().getCmObservationData().getDataName());
                }
            }

            // setting sadhyaAsadhyata mode
            if(samprapti.getSadhyaAsadhyata() != null){
                if (samprapti.getSadhyaAsadhyata().getId() != null)
                    cmExamntnSampraptiGhatakasDTO.setSadhyaAsadhyata(new CmObservationCategoryDataDTO());
                cmExamntnSampraptiGhatakasDTO.getSadhyaAsadhyata().setId(samprapti.getSadhyaAsadhyata().getId());
                if (samprapti.getSadhyaAsadhyata().getCmObservationData() != null) {
                    cmExamntnSampraptiGhatakasDTO.getSadhyaAsadhyata().setCmObservationDataDTO(new CmObservationDataDTO());
                    cmExamntnSampraptiGhatakasDTO.getSadhyaAsadhyata().getCmObservationDataDTO().setDataName(samprapti.getSadhyaAsadhyata().getCmObservationData().getDataName());
                }
            }
        } catch (Exception e) {
            log.error("Failed to frame samprapti ghatakas" + e.getMessage());
        }
        return cmExamntnSampraptiGhatakasDTO;
    }

    //method to get uom master by uom master id
    private JSONObject getUomMasterByUomMasterId(Long uomMasterId) {
        JSONObject jsonObject =  serviceGateway.getUomMasterByUomMasterId(uomMasterId);
        JSONObject uomMaster = new JSONObject();
        try {
            uomMaster = jsonObject.getJSONObject("uomMaster");
        } catch (JSONException e) {
            log.error("failed to retrieve uom master by id" + e.getMessage());
        }
        return uomMaster;
    }
}
