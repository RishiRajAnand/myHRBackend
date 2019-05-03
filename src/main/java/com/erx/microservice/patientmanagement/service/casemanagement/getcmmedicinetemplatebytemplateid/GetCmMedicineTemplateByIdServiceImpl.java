package com.erx.microservice.patientmanagement.service.casemanagement.getcmmedicinetemplatebytemplateid;

/*
* created by Latha on 27-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplate;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplateGroupMedicineDetail;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplateMedicine;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateGroupMedicineDetailRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateMedicineRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmTemplateRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.*;
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

@Service("getCmMedicineTemplateByIdService")
public class GetCmMedicineTemplateByIdServiceImpl implements GetCmMedicineTemplateByIdService {

    private final Logger log = LoggerFactory.getLogger(GetCmMedicineTemplateByIdServiceImpl.class);

    @Autowired
    private CmTemplateRepository cmTemplateRepository;

    @Autowired
    private CmTemplateMedicineRepository cmTemplateMedicineRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Autowired
    private CmTemplateGroupMedicineDetailRepository cmTemplateGroupMedicineDetailRepository;

    @Override
    public GetCmMedicineTemplateByIdServiceResponse execute(GetCmMedicineTemplateByIdServiceRequest request) throws ServiceException {
        GetCmMedicineTemplateByIdServiceResponse response = null;
        CmTemplate cmTemplate = new CmTemplate();
        CmTemplateResponseDTO cmTemplateResponseDTO = new CmTemplateResponseDTO();
        List<CmTemplateMedicine> cmTemplateMedicines = new ArrayList<>();
        List<CmTemplateMedicineDTO> cmTemplateMedicineDTOs = new ArrayList<CmTemplateMedicineDTO>();
        List<CmTemplateGroupMedicineDTO> cmTemplateGroupMedicineDTOs = new ArrayList<CmTemplateGroupMedicineDTO>();
        try {
            log.debug("call to get cm medicine template by template id" + request.getCmTemplateId());
            cmTemplate = cmTemplateRepository.findOne(request.getCmTemplateId());
            if(cmTemplate != null)
                cmTemplateResponseDTO.setCmTemplateId(cmTemplate.getId());
                cmTemplateResponseDTO.setName(cmTemplate.getName());
                cmTemplateResponseDTO.setInstruction(cmTemplate.getInstructions());
             // find cm template medicine by cm template id
            cmTemplateMedicines = cmTemplateMedicineRepository.findCmTemplateMedicineByCmTemplate(cmTemplate.getId());
          //  cmTemplateMedicineDTOs = frameCmTemplateMedicine(cmTemplateMedicines);
            List<CmTemplateGroupMedicineDetail> cmTemplateGroupMedicineDetails = new ArrayList<>();
            List<CmTemplateGroupMedicinesDTO> cmTemplateGroupMedicinesDTOs = new ArrayList<>();
            for(CmTemplateMedicine cmTemplateMedicine : cmTemplateMedicines){
                cmTemplateGroupMedicineDetails = cmTemplateGroupMedicineDetailRepository.findCmTemplateGroupMedicinesById(cmTemplateMedicine.getId());
                CmTemplateMedicineDTO cmTemplateMedicineDTO = new CmTemplateMedicineDTO();
                if(cmTemplateGroupMedicineDetails.size() <= 0){
                    cmTemplateMedicineDTO = frameCmTemplateMedicineDTO(cmTemplateMedicine);
                    cmTemplateMedicineDTOs.add(cmTemplateMedicineDTO);
                }else {
                    //frame cm template medicine group
                    CmTemplateGroupMedicineDTO cmTemplateGroupMedicineDTO = new CmTemplateGroupMedicineDTO();
                    cmTemplateMedicineDTO = frameCmTemplateMedicineDTO(cmTemplateMedicine);
                    cmTemplateGroupMedicineDTO.setCmTemplateMedicineGroupInfoDTO(cmTemplateMedicineDTO);
                    cmTemplateGroupMedicinesDTOs = frameCmTemplateGroupMedicines(cmTemplateMedicine.getId());
                    cmTemplateGroupMedicineDTO.setCmTemplateGroupMedicinesDTOs(cmTemplateGroupMedicinesDTOs);
                    cmTemplateGroupMedicineDTOs.add(cmTemplateGroupMedicineDTO);

                }
            }
            //setting individual medicines of a template
            if(cmTemplateMedicineDTOs != null) {
                cmTemplateResponseDTO.setCmTemplateMedicineDTOs(cmTemplateMedicineDTOs);
            }

            if(cmTemplateGroupMedicineDTOs != null){
                cmTemplateResponseDTO.setCmTemplateGroupMedicineDTOs(cmTemplateGroupMedicineDTOs);
            }
        // setting the dto to response
        response = new GetCmMedicineTemplateByIdServiceResponse(cmTemplateResponseDTO);
        response.SUCCESSFUL = true;
        response.setMessage("Retrieved cm medicine template by id Successfully");
        log.debug("Retrieved cm medicine template by id Successfully");
    } catch (Exception e) {
        response.SUCCESSFUL = false;
        response.setMessage("Failed to retrieve cm medicine template by id");
        log.error("Failed to retrieve cm medicine template by id");
    }
        return response;
    }

    //method to frame cm template medicine dto
    private CmTemplateMedicineDTO frameCmTemplateMedicineDTO(CmTemplateMedicine cmTemplateMedicine) {
        CmTemplateMedicineDTO cmTemplateMedicineDTO = new CmTemplateMedicineDTO();
        List<DosageTimeDTO> dosageTimeDTOS = new ArrayList<>();
        try {
            cmTemplateMedicineDTO.setCmTemplateMedicineId(cmTemplateMedicine.getId());
            if(cmTemplateMedicine.getCmDosageValueMapping() != null) {
                cmTemplateMedicineDTO.setDosage(cmTemplateMedicine.getCmDosageValueMapping().getCmDosageMaster().getDosageName());
                cmTemplateMedicineDTO.setCmDosageValueMappingId(cmTemplateMedicine.getCmDosageValueMapping().getId());
            }
            if(cmTemplateMedicine.getDosageTime() != null) {
                // to frame dosage time dto
                JSONArray jsonArray = new JSONArray(cmTemplateMedicine.getDosageTime().toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    DosageTimeDTO dosageTimeDTO = new DosageTimeDTO();
                    dosageTimeDTO.setSequenceNo(jsonObject.getLong("sequenceNo"));
                    if (jsonObject.getString("dosageInstructionId") != null) {
                        dosageTimeDTO.setDosageInstructionId(jsonObject.getString("dosageInstructionId"));
                    }
                    dosageTimeDTO.setDosageInstructionName(jsonObject.getString("dosageInstructionName"));
                    dosageTimeDTO.setTime(jsonObject.getString("time"));
                    dosageTimeDTOS.add(dosageTimeDTO);
                }
                cmTemplateMedicineDTO.setDosageTimeDTOs(dosageTimeDTOS);
            }
            cmTemplateMedicineDTO.setInstruction(cmTemplateMedicine.getInstruction());
            cmTemplateMedicineDTO.setProductCatalogueCommonDetailId(cmTemplateMedicine.getProductCatalogueCommonDetailId());
            //call to get product name
            JSONObject productCatalogueCommonDetail = serviceGateway.getProductCatalogueCommonDetailById(cmTemplateMedicine.getProductCatalogueCommonDetailId());
            if (productCatalogueCommonDetail != null) {
                JSONObject productCatalogueDetailDTO = productCatalogueCommonDetail.getJSONObject("productCatalogueDetailDTO");
                cmTemplateMedicineDTO.setMedicineName(productCatalogueDetailDTO.getString("itemName"));
            }
            cmTemplateMedicineDTO.setNumberOfDays(cmTemplateMedicine.getNumberOfDays());
            cmTemplateMedicineDTO.setStrength(cmTemplateMedicine.getStrength());
            if(cmTemplateMedicine.getUomMasterId() != null) {
                cmTemplateMedicineDTO.setUomId(cmTemplateMedicine.getUomMasterId());
                //call to get uom master
                JSONObject jsonObjectUomMaster = getUomMasterByUomMasterId(cmTemplateMedicine.getUomMasterId());
                if (jsonObjectUomMaster != null) {
                    cmTemplateMedicineDTO.setUomName(jsonObjectUomMaster.getString("uomName"));
                }
            }
            if(cmTemplateMedicine.getMedicineGroupType() != null) {
                cmTemplateMedicineDTO.setMedicineGroupTypeLookupId(cmTemplateMedicine.getMedicineGroupType().getId());
            }
            cmTemplateMedicineDTO.setDosageQuantity(cmTemplateMedicine.getDosageQuantity());
            cmTemplateMedicineDTO.setTotalQuantity(cmTemplateMedicine.getTotalQuantity());
        } catch (JSONException e) {
            log.error("failed to frame cm template medicine" + e.getMessage());
        }
        return cmTemplateMedicineDTO;
    }

    //method to frame cm template group medicine
    private List<CmTemplateGroupMedicinesDTO> frameCmTemplateGroupMedicines(Long cmTemplateMedicineMappingId) {
        List<CmTemplateGroupMedicinesDTO> cmTemplateGroupMedicinesDTOs = new ArrayList<>();
        List<CmTemplateGroupMedicineDetail> cmTemplateGroupMedicineDetails = new ArrayList<>();
        try {
            cmTemplateGroupMedicineDetails = cmTemplateGroupMedicineDetailRepository.findCmTemplateGroupMedicinesById(cmTemplateMedicineMappingId);
            if(cmTemplateGroupMedicineDetails != null){
                for(CmTemplateGroupMedicineDetail cmTemplateGroupMedicineDetail : cmTemplateGroupMedicineDetails){
                    CmTemplateGroupMedicinesDTO cmTemplateGroupMedicinesDTO = new CmTemplateGroupMedicinesDTO();
                    cmTemplateGroupMedicinesDTO.setCmTemplateGroupMedicineId(cmTemplateGroupMedicineDetail.getId());
                    cmTemplateGroupMedicinesDTO.setProductCatalogueCommonDetailId(cmTemplateGroupMedicineDetail.getProductCatalogueCommonDetailId());
                    //call to get product name
                    JSONObject productCatalogueCommonDetail = serviceGateway.getProductCatalogueCommonDetailById(cmTemplateGroupMedicineDetail.getProductCatalogueCommonDetailId());
                    if (productCatalogueCommonDetail != null) {
                        JSONObject productCatalogueDetailDTO = productCatalogueCommonDetail.getJSONObject("productCatalogueDetailDTO");
                        cmTemplateGroupMedicinesDTO.setProductCatalogueCommonDetailName(productCatalogueDetailDTO.getString("itemName"));
                    }
                    cmTemplateGroupMedicinesDTO.setComposition(cmTemplateGroupMedicineDetail.getComposition());
                    cmTemplateGroupMedicinesDTO.setTotalQuantity(cmTemplateGroupMedicineDetail.getTotalQuantity());
                    cmTemplateGroupMedicinesDTO.setInstructions(cmTemplateGroupMedicineDetail.getInstructions());
                    cmTemplateGroupMedicinesDTO.setStrength(cmTemplateGroupMedicineDetail.getStrength());
                    cmTemplateGroupMedicinesDTO.setUomId(cmTemplateGroupMedicineDetail.getUomMasterId());
                    //call to get uom master
                    JSONObject jsonObjectUomMaster = getUomMasterByUomMasterId(cmTemplateGroupMedicineDetail.getUomMasterId());
                    if(jsonObjectUomMaster != null){
                        cmTemplateGroupMedicinesDTO.setUomName(jsonObjectUomMaster.getString("uomName"));
                        cmTemplateGroupMedicinesDTOs.add(cmTemplateGroupMedicinesDTO);
                    }
                }
            }
        } catch (JSONException e) {
            log.error("failed to frame cm template group medicine" + e.getMessage());
        }
        return cmTemplateGroupMedicinesDTOs;
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
