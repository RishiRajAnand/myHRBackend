package com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetreatment;

/*
* created by Latha on 25-08-2018
* */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.domain.casemanagement.*;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.repository.casemanagement.*;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.*;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service("saveMedicineTreatmentService")
public class SaveMedicineTreatmentServiceImpl implements SaveMedicineTreatmentService {

    private final Logger log = LoggerFactory.getLogger(SaveMedicineTreatmentServiceImpl.class);

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;

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

    @Override
    public SaveMedicineTreatmentServiceResponse execute(SaveMedicineTreatmentServiceRequest request) throws ServiceException {
        SaveMedicineTreatmentServiceResponse response = null;
        CmTreatment cmTreatment = new CmTreatment();
        CmTreatment savedCmTreatment = new CmTreatment();
        CmTreatmentMedicineDetail cmTreatmentMedicineDetail = new CmTreatmentMedicineDetail();
        CmTreatmentMedicineDetail savedCmTreatmentMedicineDetail = new CmTreatmentMedicineDetail();
        List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineDetail = new ArrayList<CmTreatmentGroupMedicineDetail>();
        List<CmTreatmentGroupMedicineDetail> savedCmTreatmentGroupMedicineDetail = null;
        SaveMedicineResponseDTO saveMedicineResponseDTO = null;
        try {
            log.debug("call to save medicine treatment");
            if(request.getSaveMedicineDTO().getPathyaPathyaDTO() != null || request.getSaveMedicineDTO().getTreatmentPrincipleDTO() != null
                    || request.getSaveMedicineDTO().getSaveGroupDTO() != null || request.getSaveMedicineDTO() != null){
                //save cm treatment
                cmTreatment = frameCmTreatment(request.getSaveMedicineDTO());
                if(cmTreatment != null)
                    savedCmTreatment = cmTreatmentRepository.save(cmTreatment);

                //save cm treatment medicine detail
                if(request.getSaveMedicineDTO().getSaveIndividualDTO() != null) {
                    cmTreatmentMedicineDetail = frameCmTreatmentMedicineDetail(savedCmTreatment, request.getSaveMedicineDTO().getSaveIndividualDTO());
                    if(cmTreatmentMedicineDetail != null)
                    // calling method to save cm treatment medicine detail
                    savedCmTreatmentMedicineDetail = saveCmTreatmentMedicineDetail(cmTreatmentMedicineDetail);
                }

                //save cm treatment group medicine detail
                if(request.getSaveMedicineDTO().getSaveGroupDTO() != null)
                cmTreatmentGroupMedicineDetail = frameCmTreatmentGroupMedicineDetail(savedCmTreatment, request.getSaveMedicineDTO().getSaveGroupDTO());
                if(cmTreatmentGroupMedicineDetail != null){
                    savedCmTreatmentGroupMedicineDetail = new ArrayList<CmTreatmentGroupMedicineDetail>();
                    savedCmTreatmentGroupMedicineDetail = cmTreatmentGroupMedicineDetailRepository.save(cmTreatmentGroupMedicineDetail);
                }
                // setting response object
                saveMedicineResponseDTO = new SaveMedicineResponseDTO();
                saveMedicineResponseDTO.setCmTreatmentId(cmTreatment.getId());
                saveMedicineResponseDTO.setCmTreatmentMedicineDetailId(savedCmTreatmentMedicineDetail.getId());
            }
            //create response
            response = new SaveMedicineTreatmentServiceResponse(saveMedicineResponseDTO);
            response.setMessage("Case sheet medicine treatment saved successfully");
            log.debug("Case sheet medicine treatment saved successfully");
        } catch (Exception e) {
            response = new SaveMedicineTreatmentServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save case sheet medicine treatment");
            response.setMessage(e.getMessage() + " so,Failed to save case sheet medicine treatment");
        }
        return response;
    }

    //method to frame cm treatment
    private CmTreatment frameCmTreatment(SaveMedicineDTO saveMedicineDTO) {
        CmMasterDetails savedCmMasterDetails = new CmMasterDetails();
        CmMaster cmMaster = new CmMaster();
        CmTreatment cmTreatment = new CmTreatment();
        try {
            if (saveMedicineDTO.getCmMasterId() != null) {
                cmMaster = cmMasterRepository.findOne(saveMedicineDTO.getCmMasterId());
                // retrieve cm master details based on cm master id
                if(cmMaster != null)
                /*    savedCmMasterDetails.setCmMaster(cmMaster);
                savedCmMasterDetails.setCaseCreatedDate(LocalDateTime.now());
                if(saveMedicineDTO.getPatientAppointmentId() != null){
                    savedCmMasterDetails.setPatientAppointmentId(saveMedicineDTO.getPatientAppointmentId());
                }*/
                savedCmMasterDetails = cmMasterDetailsRepository.findOne(saveMedicineDTO.getCmMasterDetailId());
            }
            if(savedCmMasterDetails != null)
            // setting the user entered values in cm treatment object
            if(saveMedicineDTO.getCmTreatmentId() == null) {
                cmTreatment = frameCmTreatmentObj(savedCmMasterDetails,saveMedicineDTO);
            }
            /*else{
                cmTreatment = cmTreatmentRepository.findOne(saveMedicineDTO.getCmTreatmentId());
                cmTreatment = frameCmTreatmentObj(savedCmMasterDetails,saveMedicineDTO);
            }*/
        } catch (Exception e) {
            log.error("failed to frame cm treatment object" + e.getMessage());
        }
        return cmTreatment;
    }

    //method to frame cm treatment obj
    private CmTreatment frameCmTreatmentObj(CmMasterDetails cmMasterDetails, SaveMedicineDTO saveMedicineDTO) {
        CmTreatment cmTreatment = new CmTreatment();
        try {
            if(cmMasterDetails != null)
            cmTreatment.setCmMasterDetails(cmMasterDetails);
            cmTreatment.setGivenDate(cmMasterDetails.getCaseCreatedDate());
            if(saveMedicineDTO.getPathyaPathyaDTO() != null) {
                cmTreatment.setPathyaPathya(saveMedicineDTO.getPathyaPathyaDTO().getPathyaPathya().trim());
            }
            if(saveMedicineDTO.getPathyaPathyaDTO() != null) {
                cmTreatment.setTake(saveMedicineDTO.getPathyaPathyaDTO().getTake().trim());
            }
            if(saveMedicineDTO.getPathyaPathyaDTO() != null) {
                cmTreatment.setTakeMore(saveMedicineDTO.getPathyaPathyaDTO().getTakeMore());
            }
            cmTreatment.setShamanam(saveMedicineDTO.getTreatmentPrincipleDTO().isShamanam());
            cmTreatment.setShodhanam(saveMedicineDTO.getTreatmentPrincipleDTO().isShodhanam());
        } catch (Exception e) {
            log.error("failed to frame cm treatment obj" + e.getMessage());
        }
        return cmTreatment;
    }

    //method to frame cm treatment medicine detail
    private CmTreatmentMedicineDetail frameCmTreatmentMedicineDetail(CmTreatment savedCmTreatment, SaveIndividualDTO saveIndividualDTO) {
        CmTreatmentMedicineDetail cmTreatmentMedicineDetail = new CmTreatmentMedicineDetail();

        try {
            if(saveIndividualDTO.getCmTreatmentMedicineDetailId() == null) {
                cmTreatmentMedicineDetail = frameCmTreatmentMedicineDetailObj(savedCmTreatment,saveIndividualDTO, cmTreatmentMedicineDetail);
            }else{
                cmTreatmentMedicineDetail = cmTreatmentMedicineDetailRepository.findOne(saveIndividualDTO.getCmTreatmentMedicineDetailId());
                cmTreatmentMedicineDetail = frameCmTreatmentMedicineDetailObj(savedCmTreatment,saveIndividualDTO, cmTreatmentMedicineDetail);
            }
        } catch (Exception e) {
            log.error("failed to frame cm treatment medicine detail" + e.getMessage());
        }
        return cmTreatmentMedicineDetail;
    }

    // method to frame cm treatment medicine detail obj
    private CmTreatmentMedicineDetail frameCmTreatmentMedicineDetailObj(CmTreatment savedCmTreatment, SaveIndividualDTO saveIndividualDTO,
                                                                        CmTreatmentMedicineDetail cmTreatmentMedicineDetail) {
        CmDosageValueMapping cmDosageValueMapping = new CmDosageValueMapping();
        RouteOfAdministration routeOfAdministration = new RouteOfAdministration();
        LookupValue lookupValue = new LookupValue();
        try {
            if(savedCmTreatment != null)
                cmTreatmentMedicineDetail.setCmTreatment(savedCmTreatment);
            cmTreatmentMedicineDetail.setDosageQuantity(saveIndividualDTO.getDosageQuantity());
            cmTreatmentMedicineDetail.setDosageTime(saveIndividualDTO.getDosageTimeDTOs().toString());
            cmTreatmentMedicineDetail.setInstructions(saveIndividualDTO.getInstructions());
            cmTreatmentMedicineDetail.setNumberOfDays(saveIndividualDTO.getNumberOfDays());
            cmTreatmentMedicineDetail.setProductCatalogueCommonDetailId(saveIndividualDTO.getProductCatalogueCommonDetailId());
            cmTreatmentMedicineDetail.setUomMasterId(saveIndividualDTO.getUomMasterId());
            if (saveIndividualDTO.getCmDosageValueMappingId() != null) {
                cmDosageValueMapping = cmDosageValueMappingRepository.findOne(saveIndividualDTO.getCmDosageValueMappingId());
                cmTreatmentMedicineDetail.setCmDosageValueMapping(cmDosageValueMapping);
            }
            lookupValue = lookupValueRepository.findOne(saveIndividualDTO.getMedicineGroupTypeId());
            cmTreatmentMedicineDetail.setMedicineGroupType(lookupValue);
            if (saveIndividualDTO.getRouteOfAdministrationId() != null) {
                routeOfAdministration = routeOfAdministrationRepository.findOne(saveIndividualDTO.getRouteOfAdministrationId());
                cmTreatmentMedicineDetail.setRouteOfAdministration(routeOfAdministration);
            }

        } catch (Exception e) {
            log.error("failed to frame cm treatment medicine detail obj" + e.getMessage());
        }
        return cmTreatmentMedicineDetail;
    }

    // method to save cm treatment medicine detail
    private CmTreatmentMedicineDetail saveCmTreatmentMedicineDetail(CmTreatmentMedicineDetail cmTreatmentMedicineDetail) {
        CmTreatmentMedicineDetail savedCmTreatmentMedicineDetail = null;
        try {
            log.debug("Call to save cm treatment medicine detail");
            savedCmTreatmentMedicineDetail = new CmTreatmentMedicineDetail();
            savedCmTreatmentMedicineDetail = cmTreatmentMedicineDetailRepository.save(cmTreatmentMedicineDetail);
        } catch (Exception e) {
            log.error("Failed to save cm treatment medicine detail" + e.getMessage());
        }
        return savedCmTreatmentMedicineDetail;
    }

    // method to frame cm treatment group medicine detail and save the cm treatment medicine detail
    private List<CmTreatmentGroupMedicineDetail> frameCmTreatmentGroupMedicineDetail(CmTreatment savedCmTreatment,
                                                                               SaveGroupDTO saveGroupDTO) {
        List<CmTreatmentGroupMedicineDetail> cmTreatmentGroupMedicineDetails = new ArrayList<CmTreatmentGroupMedicineDetail>();
        CmTreatmentMedicineDetail savedCmTreatmentMedicineDetails = new CmTreatmentMedicineDetail();
        try {
            // call to frame cm treatment medicine detail
            CmTreatmentMedicineDetail cmTreatmentMedicineDetail = frameCmTreatmentMedicineDetail(savedCmTreatment,
                    saveGroupDTO.getSaveGroupInfoDTO());
            // call to save cm treatment medicine detail
            if(cmTreatmentMedicineDetail != null)
                savedCmTreatmentMedicineDetails = saveCmTreatmentMedicineDetail(cmTreatmentMedicineDetail);
            // setting the cm treatment group medicine detail object
            for(GroupIndividualMedicineDTO groupIndividualMedicineDTO : saveGroupDTO.getGroupIndividualMedicineDTOs()){
                CmTreatmentGroupMedicineDetail cmTreatmentGroupMedicineDetail = new CmTreatmentGroupMedicineDetail();
                CmTreatmentGroupMedicineDetail retrieveCmTreatmentGroupMedicineDetail = new CmTreatmentGroupMedicineDetail();
                if(groupIndividualMedicineDTO.getCmTreatmentGroupMedicineDetailId() != null){
                    retrieveCmTreatmentGroupMedicineDetail = cmTreatmentGroupMedicineDetailRepository.findOne(groupIndividualMedicineDTO.getCmTreatmentGroupMedicineDetailId());
                    retrieveCmTreatmentGroupMedicineDetail.setComposition(groupIndividualMedicineDTO.getComposition());
                    retrieveCmTreatmentGroupMedicineDetail.setInstructions(groupIndividualMedicineDTO.getInstructions());
                    retrieveCmTreatmentGroupMedicineDetail.setProductCatalogueCommonDetailId(groupIndividualMedicineDTO.getProductCatalogueCommonDetailId());
                    retrieveCmTreatmentGroupMedicineDetail.setStrength(groupIndividualMedicineDTO.getStrength());
                    retrieveCmTreatmentGroupMedicineDetail.setUomMasterId(groupIndividualMedicineDTO.getUomMasterId());
                    cmTreatmentGroupMedicineDetails.add(retrieveCmTreatmentGroupMedicineDetail);
                }else {
                    cmTreatmentGroupMedicineDetail.setCmTreatmentMedicineList(savedCmTreatmentMedicineDetails);
                    cmTreatmentGroupMedicineDetail.setComposition(groupIndividualMedicineDTO.getComposition());
                    cmTreatmentGroupMedicineDetail.setInstructions(groupIndividualMedicineDTO.getInstructions());
                    cmTreatmentGroupMedicineDetail.setProductCatalogueCommonDetailId(groupIndividualMedicineDTO.getProductCatalogueCommonDetailId());
                    cmTreatmentGroupMedicineDetail.setStrength(groupIndividualMedicineDTO.getStrength());
                    cmTreatmentGroupMedicineDetail.setUomMasterId(groupIndividualMedicineDTO.getUomMasterId());
                    cmTreatmentGroupMedicineDetails.add(cmTreatmentGroupMedicineDetail);
                }
            }
        } catch (Exception e) {
            log.error("failed to frame cm treatment group medicine detail and save the cm treatment medicine detail" + e.getMessage());
        }
        return cmTreatmentGroupMedicineDetails;
    }
}
