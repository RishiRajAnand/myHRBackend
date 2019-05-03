package com.erx.microservice.patientmanagement.service.casemanagement.savemedicinetemplate;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmDosageValueMapping;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplate;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplateGroupMedicineDetail;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplateMedicine;
import com.erx.microservice.patientmanagement.repository.ClinicRepository;
import com.erx.microservice.patientmanagement.repository.LookupValueRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmDosageValueMappingRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateGroupMedicineDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateMedicineRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateRepository;
import com.erx.microservice.patientmanagement.service.datautil.datavalidation.casemanagementdatavalidation.DataValidationService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveMedicineTemplateDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveTemplateGroupDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.TemplateGroupIndividualMedicineDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.TemplateMedicineDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("saveMedicineTemplateService")
public class SaveMedicineTemplateServiceImpl implements SaveMedicineTemplateService {

    private final Logger log = LoggerFactory.getLogger(SaveMedicineTemplateServiceImpl.class);

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private CmTemplateRepository cmTemplateRepository;

    @Autowired
    private CmDosageValueMappingRepository cmDosageValueMappingRepository;

    @Autowired
    private CmTemplateMedicineRepository cmTemplateMedicineRepository;

    @Autowired
    private CmTemplateGroupMedicineDetailRepository cmTemplateGroupMedicineDetailRepository;

    @Autowired
    private DataValidationService dataValidationService;

    @Autowired
    private LookupValueRepository lookupValueRepository;

    @Override
    public SaveMedicineTemplateServiceResponse execute(SaveMedicineTemplateServiceRequest request) throws ServiceException {
        SaveMedicineTemplateServiceResponse response = null;
        CmTemplate cmTemplate = new CmTemplate();
        CmTemplateMedicine cmTemplateMedicine = new CmTemplateMedicine();
        CmTemplate savedCmTemplate = new CmTemplate();
        List<CmTemplateGroupMedicineDetail> cmTemplateGroupMedicineDetails = new ArrayList<CmTemplateGroupMedicineDetail>();
        List<CmTemplateGroupMedicineDetail> savedCmTemplateGroupMedicineDetail = null;
        try {
            log.debug("call to save medicine template");
            if(request.getSaveMedicineTemplateDTO() != null || request.getSaveMedicineTemplateDTO().getSaveTemplateGroupDTO() != null
                    || request.getSaveMedicineTemplateDTO().getTemplateMedicineDTO() != null) {

                //validate whether group name is exist or not
                if(request.getSaveMedicineTemplateDTO().getCmTemplateId() == null) {
                    response = dataValidationService.validateMedicineTemplateName(request.getSaveMedicineTemplateDTO().getName(), request.getSaveMedicineTemplateDTO().getClinicId());
                    if (response.SUCCESSFUL == false) {
                        return response;
                    }
                }

                //frame cm template
                cmTemplate = frameCmTemplate(request.getSaveMedicineTemplateDTO());
                //save cm template
                savedCmTemplate = cmTemplateRepository.save(cmTemplate);
                if(savedCmTemplate != null && request.getSaveMedicineTemplateDTO().getTemplateMedicineDTO() != null) {
                    //frame template medicine
                    cmTemplateMedicine = frameCmTemplateMedicine(savedCmTemplate, request.getSaveMedicineTemplateDTO().getTemplateMedicineDTO());
                    //save cm template medicine
                    CmTemplateMedicine savedCmTemplateMedicines = saveCmTemplateMedicine(cmTemplateMedicine);
                }

                if(request.getSaveMedicineTemplateDTO().getSaveTemplateGroupDTO() != null) {
                /*    //frame template medicine
                    cmTemplateMedicine = frameCmTemplateMedicine(savedCmTemplate, request.getSaveMedicineTemplateDTO().getSaveTemplateGroupDTO().getTemplateGroupInfoMedicineDTO())*//*;
                    //save cm template medicine
                    CmTemplateMedicine savedCmTemplateMedicines = saveCmTemplateMedicine(cmTemplateMedicine);*/
                    //frame CmTemplateGroupMedicineDetail
                    cmTemplateGroupMedicineDetails = frameCmTemplateGroupMedicineDetails(savedCmTemplate, request.getSaveMedicineTemplateDTO().getSaveTemplateGroupDTO());
                }
                if(cmTemplateGroupMedicineDetails != null){
                    savedCmTemplateGroupMedicineDetail = new ArrayList<CmTemplateGroupMedicineDetail>();
                    savedCmTemplateGroupMedicineDetail = cmTemplateGroupMedicineDetailRepository.save(cmTemplateGroupMedicineDetails);
                }
            }
            //create response
            response = new SaveMedicineTemplateServiceResponse(savedCmTemplate.getId());
            response.setMessage("Medicine template saved successfully");
            log.debug("Medicine template saved successfully");
        } catch (Exception e) {
            response = new SaveMedicineTemplateServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save medicine template");
            response.setMessage(e.getMessage() + " so,Failed to save medicine template");
        }
        return response;
    }

    //method to frame cm template
    private CmTemplate frameCmTemplate(SaveMedicineTemplateDTO saveMedicineTemplateDTO) {
        CmTemplate cmTemplate = new CmTemplate();
        Clinic clinic = new Clinic();
        try {
            clinic = clinicRepository.findOne(saveMedicineTemplateDTO.getClinicId());
            if(saveMedicineTemplateDTO.getCmTemplateId() == null){
                cmTemplate = frameCmTemplateObj(saveMedicineTemplateDTO, cmTemplate);
                cmTemplate.setClinic(clinic);
                cmTemplate.setScienceOfMedicineId(saveMedicineTemplateDTO.getScienceOfMedicineId());
            }else{
                cmTemplate = cmTemplateRepository.findOne(saveMedicineTemplateDTO.getCmTemplateId());
                cmTemplate = frameCmTemplateObj(saveMedicineTemplateDTO, cmTemplate);
            }
        } catch (Exception e) {
            log.error("Failed to frame cm template" + e.getMessage());
        }
        return cmTemplate;
    }

    //method to frame cm template obj
    private CmTemplate frameCmTemplateObj(SaveMedicineTemplateDTO saveMedicineTemplateDTO, CmTemplate cmTemplate) {
        cmTemplate.setName(saveMedicineTemplateDTO.getName().trim());
        cmTemplate.setInstructions(saveMedicineTemplateDTO.getInstructions().trim());
        cmTemplate.setDescription(saveMedicineTemplateDTO.getDescription().trim());
        return cmTemplate;
    }

    //method to frame cm template medicine
    private CmTemplateMedicine frameCmTemplateMedicine(CmTemplate savedCmTemplate, TemplateMedicineDTO templateMedicineDTO) {
        CmTemplateMedicine cmTemplateMedicine = new CmTemplateMedicine();
        CmDosageValueMapping cmDosageValueMapping = null;
        try {
                if(templateMedicineDTO != null) {
                    if(templateMedicineDTO.getCmDosageValueMappingId() != null) {
                        cmDosageValueMapping = cmDosageValueMappingRepository.findOne(templateMedicineDTO.getCmDosageValueMappingId());
                    }
                    if (templateMedicineDTO.getTemplateMedicineId() == null) {
                        cmTemplateMedicine = frameCmTemplateMedicineObj(cmTemplateMedicine,templateMedicineDTO);
                        cmTemplateMedicine.setCmTemplate(savedCmTemplate);
                    } else {
                        cmTemplateMedicine = cmTemplateMedicineRepository.findOne(templateMedicineDTO.getTemplateMedicineId());
                        cmTemplateMedicine = frameCmTemplateMedicineObj(cmTemplateMedicine,templateMedicineDTO);
                    }
                    cmTemplateMedicine.setCmDosageValueMapping(cmDosageValueMapping);
                }
        } catch (Exception e) {
            log.error("failed to frame cm template medicine" +e.getMessage());
        }
        return cmTemplateMedicine;
    }

    // method to frame cm template medicine
    private CmTemplateMedicine frameCmTemplateMedicineObj(CmTemplateMedicine cmTemplateMedicine, TemplateMedicineDTO templateMedicineDTO) {
        LookupValue lookupValue = new LookupValue();
        try {
            cmTemplateMedicine.setDescription(templateMedicineDTO.getDescription().trim());
            cmTemplateMedicine.setDosageTime(templateMedicineDTO.getDosageTimeDTOs().toString());
            cmTemplateMedicine.setInstruction(templateMedicineDTO.getInstruction().trim());
            cmTemplateMedicine.setUomMasterId(templateMedicineDTO.getUomMasterId());
            cmTemplateMedicine.setNumberOfDays(templateMedicineDTO.getNumberOfDays());
            cmTemplateMedicine.setProductCatalogueCommonDetailId(templateMedicineDTO.getProductCatalogueCommonDetailId());
            cmTemplateMedicine.setStrength(templateMedicineDTO.getStrength());
            cmTemplateMedicine.setDosageQuantity(templateMedicineDTO.getDosageQuantity());
            cmTemplateMedicine.setTotalQuantity(templateMedicineDTO.getTotalQuantity());
            lookupValue = lookupValueRepository.findOne(templateMedicineDTO.getMedicineGroupTypeId());
            cmTemplateMedicine.setMedicineGroupType(lookupValue);
        } catch (Exception e) {
            log.error("failed to frame cm template obj");
        }
        return cmTemplateMedicine;
    }

    //method to save cm template medicine
    private CmTemplateMedicine saveCmTemplateMedicine(CmTemplateMedicine cmTemplateMedicine) {
        CmTemplateMedicine savedCmTemplateMedicine = null;
        try {
            log.debug("Call to save cm template medicine detail");
            savedCmTemplateMedicine = new CmTemplateMedicine();
            savedCmTemplateMedicine = cmTemplateMedicineRepository.save(cmTemplateMedicine);
        } catch (Exception e) {
            log.error("Failed to save cm template medicine detail" + e.getMessage());
        }
        return savedCmTemplateMedicine;
    }

    //method to frame cm template group medicine details
    private List<CmTemplateGroupMedicineDetail> frameCmTemplateGroupMedicineDetails(CmTemplate savedCmTemplate,
                                                                                    SaveTemplateGroupDTO saveTemplateGroupDTO) {
        List<CmTemplateGroupMedicineDetail> cmTemplateGroupMedicineDetails = new ArrayList<CmTemplateGroupMedicineDetail>();
        CmTemplateMedicine savedCmTemplateMedicine = new CmTemplateMedicine();
        try {
            // call to frame cm template medicine detail
            CmTemplateMedicine cmTemplateMedicineDetail = frameCmTemplateMedicine(savedCmTemplate,
                    saveTemplateGroupDTO.getTemplateGroupInfoMedicineDTO());
            // call to save cm template medicine detail
            if(cmTemplateMedicineDetail != null)
                savedCmTemplateMedicine = saveCmTemplateMedicine(cmTemplateMedicineDetail);

            // setting the cm template group medicine detail object
            for(TemplateGroupIndividualMedicineDTO templateGroupIndividualMedicineDTO : saveTemplateGroupDTO.getTemplateGroupIndividualMedicineDTOs()){
                CmTemplateGroupMedicineDetail cmTemplateGroupMedicineDetail = new CmTemplateGroupMedicineDetail();
                CmTemplateGroupMedicineDetail retrieveCmTemplateGroupMedicineDetail = new CmTemplateGroupMedicineDetail();
                if(templateGroupIndividualMedicineDTO.getCmTemplateGroupMedicineId() != null){
                    retrieveCmTemplateGroupMedicineDetail = cmTemplateGroupMedicineDetailRepository.findOne(templateGroupIndividualMedicineDTO.getCmTemplateGroupMedicineId());
                    retrieveCmTemplateGroupMedicineDetail.setComposition(templateGroupIndividualMedicineDTO.getComposition());
                    retrieveCmTemplateGroupMedicineDetail.setTotalQuantity(templateGroupIndividualMedicineDTO.getTotalQuantity());
                    retrieveCmTemplateGroupMedicineDetail.setInstructions(templateGroupIndividualMedicineDTO.getInstructions());
                    retrieveCmTemplateGroupMedicineDetail.setProductCatalogueCommonDetailId(templateGroupIndividualMedicineDTO.getProductCatalogueCommonDetailId());
                    retrieveCmTemplateGroupMedicineDetail.setStrength(templateGroupIndividualMedicineDTO.getStrength());
                    retrieveCmTemplateGroupMedicineDetail.setUomMasterId(templateGroupIndividualMedicineDTO.getUomMasterId());
                    cmTemplateGroupMedicineDetails.add(retrieveCmTemplateGroupMedicineDetail);
                }else {
                    cmTemplateGroupMedicineDetail.setCmTemplateMedicine(cmTemplateMedicineDetail);
                    cmTemplateGroupMedicineDetail.setComposition(templateGroupIndividualMedicineDTO.getComposition());
                    cmTemplateGroupMedicineDetail.setTotalQuantity(templateGroupIndividualMedicineDTO.getTotalQuantity());
                    cmTemplateGroupMedicineDetail.setInstructions(templateGroupIndividualMedicineDTO.getInstructions());
                    cmTemplateGroupMedicineDetail.setProductCatalogueCommonDetailId(templateGroupIndividualMedicineDTO.getProductCatalogueCommonDetailId());
                    cmTemplateGroupMedicineDetail.setStrength(templateGroupIndividualMedicineDTO.getStrength());
                    cmTemplateGroupMedicineDetail.setUomMasterId(templateGroupIndividualMedicineDTO.getUomMasterId());
                    cmTemplateGroupMedicineDetails.add(cmTemplateGroupMedicineDetail);
                }
            }
        } catch (Exception e) {
            log.error("failed to frame cm template group medicine detail and save the cm template medicine detail" + e.getMessage());
        }
        return cmTemplateGroupMedicineDetails;
    }
}
