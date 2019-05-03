package com.erx.microservice.patientmanagement.service.casemanagement.getdischargedetailsbycaseid;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.UserDetail;
import com.erx.microservice.patientmanagement.domain.VisitTypeMaster;
import com.erx.microservice.patientmanagement.domain.Vital;
import com.erx.microservice.patientmanagement.domain.casemanagement.*;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanning;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanningMedicine;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.repository.UserDetailRepository;
import com.erx.microservice.patientmanagement.repository.VisitTypeMasterRepository;
import com.erx.microservice.patientmanagement.repository.VitalRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.*;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyPlanningMedicineRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyPlanningMedicineTypeRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyPlanningRepository;
import com.erx.microservice.patientmanagement.service.casemanagement.getcminvestigationdetailsbycaseid.GetInvestigationDetailsByCaseIdServiceRequest;
import com.erx.microservice.patientmanagement.service.datautil.casemanagement.getcompletecasedetails.GetCompleteCaseDetailsService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.*;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentDetailsDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.CmTherapyTreatmentMedicineDTO;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getDischargeDetailsByCaseIdService")
public class GetDischargeDetailsByCaseIdServiceImpl implements GetDischargeDetailsByCaseIdService {

    private final Logger log = LoggerFactory.getLogger(GetDischargeDetailsByCaseIdServiceImpl.class);

    @Autowired
    private TherapyPlanningRepository therapyPlanningRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Autowired
    private TherapyPlanningMedicineRepository therapyPlanningMedicineRepository;

    @Autowired
    private TherapyPlanningMedicineTypeRepository therapyPlanningMedicineTypeRepository;

    @Autowired
    private CmTreatmentMedicineDetailRepository cmTreatmentMedicineDetailRepository;

    @Autowired
    private CmTreatmentGroupMedicineDetailRepository cmTreatmentGroupMedicineDetailRepository;

    @Autowired
    private VitalRepository vitalRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

    @Autowired
    private GetCompleteCaseDetailsService getCompleteCaseDetails;

    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;

    @Autowired
    private CmDischargeRequestRepository cmDischargeRequestRepository;

    @Override
    public GetDischargeDetailsByCaseIdServiceResponse execute(GetDischargeDetailsByCaseIdServiceRequest request) throws ServiceException {
        GetDischargeDetailsByCaseIdServiceResponse response = null;
        DischargeDTO dischargeDTO = new DischargeDTO();
        CmDischargeRequest cmDischargeRequest = new CmDischargeRequest();
        List<TherapyPlanning> getAllCaseTherapy = new ArrayList<TherapyPlanning>();
        List<TherapyPlanning> getAllCaseTherapyDischarge = new ArrayList<TherapyPlanning>();
        List<CmTherapyTreatmentDetailsDTO> treatmentGivenDetailsOnAdmission = new ArrayList<>();
        List<CmTherapyTreatmentDetailsDTO> treatmentGivenDetailsOnDischarge = new ArrayList<>();
        List<CmMedicineTreatmentResponseDTO> medicineList = new ArrayList<>();
        List<CmMedicineTreatmentResponseDTO> dischargeMedicineList = new ArrayList<>();
        List<CmInvestigationDetailsGetDTO> cmInvestigationDetailsGetDTOs = new ArrayList<>();
        GetInvestigationDetailsByCaseIdServiceRequest getInvestigationDetailsByCaseIdServiceRequest = new GetInvestigationDetailsByCaseIdServiceRequest();
        //final and provisional diagnosis is pending
        List<Vital> vitalList = new ArrayList<Vital>();
        UserDetail userDetail = new UserDetail();
        CmMaster cmMaster = new CmMaster();
        List<IpAdmission> ipAdmissions = null;
        List<CmMasterDetails> cmMasterDetails = new ArrayList<>();
        List<CmMasterDetailsDTO> cmMasterDetailsDTOs = new ArrayList<>();
        DischargeGeneralExaminationBean conditionOnDischargeGeneralExaminationBean = new DischargeGeneralExaminationBean();
        DischargeGeneralExaminationBean conditionOnAdmissionGeneralExaminationBean = new DischargeGeneralExaminationBean();
        VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
        try {
            log.debug("Call to get discharge details by case id");
            //find doctor by user and clinic
            userDetail = userDetailRepository.findDoctorByUserId(request.getUserId(),request.getClinicId());
            //retrieve cm master by cm master id
            cmMaster = cmMasterRepository.findOne(request.getCaseId());
            //get ip admission by patientId
            ipAdmissions = ipAdmissionRepository.findIpByPatientId(request.getPatientId());

            getAllCaseTherapy = therapyPlanningRepository.getAllTherapyPlannedForCaseOnAdmission(request.getCaseId());
            getAllCaseTherapyDischarge = therapyPlanningRepository.getAllTherapyPlannedForCaseOnDischarge(request.getCaseId());

            // retrieve case master details based on case id
            cmMasterDetails = cmMasterDetailsRepository.getCmMasterDetailsByCmMaster(cmMaster.getId());

            //retrieve discharge request details based on case id
            cmDischargeRequest = cmDischargeRequestRepository.findDischargeDetailsByCaseId(cmMaster.getId());

            if(cmDischargeRequest != null){
                dischargeDTO.setDischargeDate(cmDischargeRequest.getDischargeRequestDate());
                dischargeDTO.setDischargeTime(cmDischargeRequest.getDischargeRequestTime());
            }

            //convert the cmMaster details object into DTO
            if(cmMasterDetails != null){
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
            }

            //framing therapy treatment details on admission
            if(getAllCaseTherapy != null){
                treatmentGivenDetailsOnAdmission = frameAllTherapyDetailsObj(getAllCaseTherapy);
            }
            //framing therapy treatment details on discharge
            if(getAllCaseTherapyDischarge != null){
                treatmentGivenDetailsOnDischarge = frameAllTherapyDetailsObj(getAllCaseTherapyDischarge);
            }

            medicineList = frameAdmissionMedicineTracker(request.getCaseId());
            dischargeMedicineList = frameDischargeMedicineTracker(request.getCaseId());
            vitalList = vitalRepository.getVitalDetailsByPatientId(request.getPatientId());

            //setting the object to discharge dto
            dischargeDTO.setCaseId(cmMaster.getId());
            dischargeDTO.setCaseNumber(cmMaster.getClinicCaseNumber());
            dischargeDTO.setChiefComplaint(cmMaster.getChiefComplaint());
            dischargeDTO.setConsultantDoctorName(userDetail.getUserStaff().getFirstName());

            if(cmMaster.getPatient() != null){
                    dischargeDTO.setPatientID(cmMaster.getPatient().getId());
                    dischargeDTO.setPatientName(cmMaster.getPatient().getPatientName());
                    dischargeDTO.setDateOfAdmission(cmMaster.getCaseCreatedDate());
                    dischargeDTO.setPatientDateOfBirth(cmMaster.getPatient().getDateOfBirth());
                    dischargeDTO.setGender(cmMaster.getPatient().getGender());
                    dischargeDTO.setMobileNumber(cmMaster.getPatient().getMobileNumber());
                    dischargeDTO.setMrdNumber(cmMaster.getPatient().getPatientIdExternal());
                List<VisitTypeMaster> visitTypeMasters = visitTypeMasterRepository.getVisitTypeMasterByClinicLocation(request.getClinicLocationId());
                if (visitTypeMasters != null) {
                    for (VisitTypeMaster typeMaster : visitTypeMasters) {
                        if (typeMaster.getVisitType().equalsIgnoreCase("OP")) {
                            dischargeDTO.setVisitTypeMasterId(typeMaster.getId());
                            dischargeDTO.setVisitType(typeMaster.getVisitType());
                        }
                    }
                }

                if(ipAdmissions != null) {
                    for (IpAdmission ipAdmission : ipAdmissions) {
                        dischargeDTO.setIpAdmissionId(ipAdmission.getId());
                        if(ipAdmission.getIpAdmissionNumber() != null){
                            dischargeDTO.setIpAdmissionNumber(ipAdmission.getIpAdmissionNumber());
                        }else{
                            dischargeDTO.setIpAdmissionNumber(ipAdmission.getDayCareAdmissionNumber());
                        }
                        if (ipAdmission.getVisitTypeMasterId() != null) {
                            visitTypeMaster = getVisitTypeMaster(ipAdmission.getVisitTypeMasterId());
                            dischargeDTO.setVisitType(visitTypeMaster.getVisitType());
                            dischargeDTO.setVisitTypeMasterId(visitTypeMaster.getId());
                        } else if (ipAdmission.getDayCareAdmissionNumber() != null) {
                            if (ipAdmission.getVisitTypeMasterId() != null) {
                                visitTypeMaster = getVisitTypeMaster(ipAdmission.getVisitTypeMasterId());
                                dischargeDTO.setVisitType(visitTypeMaster.getVisitType());
                                dischargeDTO.setVisitTypeMasterId(visitTypeMaster.getId());
                            }
                        }
                    }
                }

            }
                if (cmMaster.getCaseCompletedDate() != null) {
                    dischargeDTO.setDateOfDischarge(cmMaster.getCaseCompletedDate());
                }
                dischargeDTO.setTreatmentGivenDetailsOnAdmission(treatmentGivenDetailsOnAdmission);
                dischargeDTO.setTreatmentGivenDetailsOnDischarge(treatmentGivenDetailsOnDischarge);
                dischargeDTO.setInternalMedicineList(medicineList);
                dischargeDTO.setDischargeInternalMedicineList(dischargeMedicineList);

                if (cmMaster.getAdviceOnDischarge() != null) {
                    String advice = cmMaster.getAdviceOnDischarge();
                    dischargeDTO.setAdviceOnDischargeSummary(advice);
                }
           // code to take the last updated vital
            if (vitalList.size() != 0) {
                if (vitalList.get(0) != null) {
                    dischargeDTO
                            .setConditionOnAdmissionGeneralExaminationBean(conditionOnAdmissionGeneralExaminationBean);
                    if (vitalList.get(0).getWeight() != null) {
                        dischargeDTO.getConditionOnAdmissionGeneralExaminationBean()
                                .setWeight(vitalList.get(0).getWeight());
                    }
                    if (vitalList.get(0).getTemperature() != null) {
                        dischargeDTO.getConditionOnAdmissionGeneralExaminationBean()
                                .setTemperature(vitalList.get(0).getTemperature());
                    }
                }
                for (Vital vital : vitalList) {
                    int n = vitalList.size();
                    if (vitalList.get(n - 1) != null) {
                        dischargeDTO.setConditionOnDischargeGeneralExaminationBean(
                                conditionOnDischargeGeneralExaminationBean);
                        if (vital.getWeight() != null) {
                            dischargeDTO.getConditionOnDischargeGeneralExaminationBean()
                                    .setWeight(vital.getWeight());
                        }
                        if (vital.getTemperature() != null) {
                            dischargeDTO.getConditionOnDischargeGeneralExaminationBean()
                                    .setTemperature(vital.getTemperature());
                        }
                    }

                }
            }
            // setting the condition on admission  and condition on discharge for general examination is pending

            //forming request object for cm investigation details request to get the details
            getInvestigationDetailsByCaseIdServiceRequest.setCaseId(request.getCaseId());
            getInvestigationDetailsByCaseIdServiceRequest.setClinicId(request.getClinicId());
            getInvestigationDetailsByCaseIdServiceRequest.setClinicLocationId(request.getClinicLocationId());
            getInvestigationDetailsByCaseIdServiceRequest.setPatientId(request.getPatientId());
            getInvestigationDetailsByCaseIdServiceRequest.setUserId(request.getUserId());
            // retrieve cm investigation details by case id
            cmInvestigationDetailsGetDTOs = getCompleteCaseDetails.getCmInvestigationDetails(getInvestigationDetailsByCaseIdServiceRequest);
            if (!(cmInvestigationDetailsGetDTOs.isEmpty())) {
                int n = cmInvestigationDetailsGetDTOs.size();
                CmInvestigationDetailsGetDTO conditionOnAdmissionInvestigationMasterBean = cmInvestigationDetailsGetDTOs
                        .get(0);
                dischargeDTO.setConditionOnAdmissionInvestigationMasterBean(conditionOnAdmissionInvestigationMasterBean);
                CmInvestigationDetailsGetDTO conditionOnDischargeInvestigationMasterBean = cmInvestigationDetailsGetDTOs
                        .get(n - 1);
                dischargeDTO.setConditionOnDischargeInvestigationMasterBean(conditionOnDischargeInvestigationMasterBean);
            }
            dischargeDTO.setCmMasterDetailsDTOs(cmMasterDetailsDTOs);

            // setting the dto to response
            response = new GetDischargeDetailsByCaseIdServiceResponse(dischargeDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved discharge details by case id Successfully");
            log.debug("Retrieved discharge details by case id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve discharge details by case id");
            log.error("Failed to retrieve discharge Details by case id");
        }
        return response;
    }

    //method to frame medicine treatments for discharge on
    private List<CmMedicineTreatmentResponseDTO> frameDischargeMedicineTracker(Long caseId) {
        List<Object[]> caseTreatmentDetail = new ArrayList<Object[]>();
        List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs = null;
        try {
            log.debug("Call to frame discharge medicine tracker");
            caseTreatmentDetail = cmMasterRepository.getMedicinesForDischargeOn(caseId);
            if(caseTreatmentDetail != null)
                cmMedicineTreatmentResponseDTOs = getCmMedicineTreatmentResponseDTOs(caseTreatmentDetail);
        } catch (Exception e) {
            log.error("Failed to frame discharge medicine tracker" + e.getMessage());
        }
        return cmMedicineTreatmentResponseDTOs;
    }

    //method to frame medicine treatments for admission on
    private List<CmMedicineTreatmentResponseDTO> frameAdmissionMedicineTracker(Long caseId) {
        List<Object[]> caseTreatmentDetail = new ArrayList<Object[]>();
        List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs = null;

        try {
            log.debug("Call to frame admission medicine tracker");
            caseTreatmentDetail = cmMasterRepository.getMedicinesForAdmissionOn(caseId);
            if(caseTreatmentDetail != null)
                cmMedicineTreatmentResponseDTOs = getCmMedicineTreatmentResponseDTOs(caseTreatmentDetail);
        } catch (Exception e) {
            log.error("Failed to frame admission medicine tracker" + e.getMessage());
        }
        return cmMedicineTreatmentResponseDTOs;
    }

    //method to get medicine treatment response dto
    private List<CmMedicineTreatmentResponseDTO> getCmMedicineTreatmentResponseDTOs(List<Object[]> caseTreatmentDetail) {
        CmTreatment cmTreatment = new CmTreatment();
        List<CmMedicineTreatmentResponseDTO> cmMedicineTreatmentResponseDTOs = null;
        List<CmTreatmentMedicineDetail> cmTreatmentMedicineDetails = new ArrayList<>();
        List<CmTreatmentGroupDTO> cmTreatmentGroupDTOs = new ArrayList<>();
        List<CmIndividualMedicineTreatmentDTO> cmIndividualMedicineTreatmentDTOs = new ArrayList<>();
        long cmTreat = 0;
        try {
            log.debug("Call to get cm medicine treatment response");
            for (Object[] row : caseTreatmentDetail) {
                cmTreatment = (CmTreatment) row[0];
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

                            if (cmTreatmentGroupDTOs != null) {
                                cmMedicineTreatmentResponseDTO.setCmTreatmentGroupDTOs(cmTreatmentGroupDTOs);
                            }
                            cmMedicineTreatmentResponseDTOs.add(cmMedicineTreatmentResponseDTO);
                        }
                    }
                    cmTreat = cmTreatment.getId();
                }
            }
        } catch (Exception e) {
            log.error("Failed to get cm treatment response dto" + e.getMessage());
        }
        return cmMedicineTreatmentResponseDTOs;
    }

    //method frame cm treatment medicine dto
    private CmIndividualMedicineTreatmentDTO frameCmTreatmentMedicineDTO(CmTreatmentMedicineDetail cmTreatmentMedicineDetail) {
        CmIndividualMedicineTreatmentDTO cmIndividualMedicineTreatmentDTO = new CmIndividualMedicineTreatmentDTO();
        List<DosageTimeDTO> dosageTimeDTOS = new ArrayList<>();
        try {
            log.debug("Call to frame cm treatment medicine dto");
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
            log.debug("Call to frmae cm treatment group medicines");
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
                    if(cmTreatmentGroupMedicineDetail.getTotalQuantity() != null){
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

    //method to frame all therapy details obj
    private List<CmTherapyTreatmentDetailsDTO> frameAllTherapyDetailsObj(List<TherapyPlanning> getAllCaseTherapy) {
        List<CmTherapyTreatmentDetailsDTO> therapyList = new ArrayList<CmTherapyTreatmentDetailsDTO>();
        CmTherapyTreatmentDetailsDTO cmTherapyTreatmentDetailsDTO = null;
        List<TherapyPlanningMedicine> therapyPlanningMedicines = new ArrayList<>();

        try {
            log.debug("Call to frame all therapy details obj");
            for (TherapyPlanning therapyPlanning : getAllCaseTherapy) {
                cmTherapyTreatmentDetailsDTO = new CmTherapyTreatmentDetailsDTO();
                //retrieve service Details
                JSONObject jsonObject = serviceGateway.getServiceCatalogueById(therapyPlanning.getServiceCatalogueId());
                //retrieve serviceCatalogueObject
                String serviceCatalogueObject = null;
                if (jsonObject != null)
                    serviceCatalogueObject = String.valueOf(jsonObject.get("serviceName"));
                cmTherapyTreatmentDetailsDTO.setServiceCatalogueId(therapyPlanning.getServiceCatalogueId());
                cmTherapyTreatmentDetailsDTO.setTherapyName(serviceCatalogueObject);
                cmTherapyTreatmentDetailsDTO.setNumberOfDays(therapyPlanning.getNumberOfDays());
                therapyPlanningMedicines = therapyPlanningMedicineRepository.findByTherapyPlanningId(therapyPlanning.getId());

                List<CmTherapyTreatmentMedicineDTO> cmTherapyTreatmentMedicineDTOs = new ArrayList<>();

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
                therapyList.add(cmTherapyTreatmentDetailsDTO);
            }
        } catch (JSONException e) {
            log.error("Failed to frame all therapy details obj" + e.getMessage());
        }

        return therapyList;
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

    private VisitTypeMaster getVisitTypeMaster(Long visitTypeMasterId) {
        return visitTypeMasterRepository.findOne(visitTypeMasterId);
    }
}
