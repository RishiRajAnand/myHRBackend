package com.erx.microservice.patientmanagement.service.casemanagement.getbkdgroupmedicinesbyid;

/*
* created by Latha on 31-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmGroupMedicineMaster;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMedicineGroup;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmGroupMedicineMasterRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMedicineGroupRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.BKDGroupResponseDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.BkdGroupMedicineDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.DosageTimeDTO;
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

@Service("getBkdGroupMedicineByIdService")
public class GetBkdGroupMedicineByIdServiceImpl implements GetBkdGroupMedicineByIdService {

    private final Logger log = LoggerFactory.getLogger(GetBkdGroupMedicineByIdServiceImpl.class);

    @Autowired
    private CmGroupMedicineMasterRepository cmGroupMedicineMasterRepository;

    @Autowired
    private CmMedicineGroupRepository cmMedicineGroupRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public GetBkdGroupMedicineByIdServiceResponse execute(GetBkdGroupMedicineByIdServiceRequest request) throws ServiceException {
        GetBkdGroupMedicineByIdServiceResponse response = null;
        CmGroupMedicineMaster cmGroupMedicineMaster = new CmGroupMedicineMaster();
        BkdGroupMedicineDTO bkdGroupMedicineDTO = new BkdGroupMedicineDTO();
        List<CmMedicineGroup> cmMedicineGroups = new ArrayList<>();
        List<DosageTimeDTO> dosageTimeDTOS = new ArrayList<>();
        List<BKDGroupResponseDTO> bkdGroupResponseDTOs = new ArrayList<>();
        try {
            cmGroupMedicineMaster = cmGroupMedicineMasterRepository.findOne(request.getGroupMedicineId());
            if(cmGroupMedicineMaster != null)

                //repo call to get cm medicine groups
                cmMedicineGroups = cmMedicineGroupRepository.findCmMedicineByGroupMedicineId(cmGroupMedicineMaster.getId());

            bkdGroupMedicineDTO.setCmGroupMedicineMasterId(cmGroupMedicineMaster.getId());
            bkdGroupMedicineDTO.setDosageQuantity(cmGroupMedicineMaster.getStrength());
            bkdGroupMedicineDTO.setTotalQuantity(cmGroupMedicineMaster.getTotalQuantity());
            if(cmGroupMedicineMaster.getCmDosageValueMapping() != null) {
                bkdGroupMedicineDTO.setCmDosageValueMappingId(cmGroupMedicineMaster.getCmDosageValueMapping().getId());
            }

            if(cmGroupMedicineMaster.getDosageTime() != null) {
                //to frame dosage time dto
                JSONArray jsonArray = new JSONArray(cmGroupMedicineMaster.getDosageTime().toString());
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
                bkdGroupMedicineDTO.setDosageTimeDTOs(dosageTimeDTOS);
            }
            bkdGroupMedicineDTO.setGroupMedicineName(cmGroupMedicineMaster.getMedicineName());

            if(cmGroupMedicineMaster.getMedicineTypeId() != null) {
                bkdGroupMedicineDTO.setGroupMedicineTypeId(cmGroupMedicineMaster.getMedicineTypeId());
                //call to get medicine type name
                JSONObject jsonObjectMedicineTypeMaster = serviceGateway.getMedicineTypeById(cmGroupMedicineMaster.getMedicineTypeId());
                JSONObject medicineTypeMasterDTO = jsonObjectMedicineTypeMaster.getJSONObject("medicineTypeMasterDTO");
                bkdGroupMedicineDTO.setGroupMedicineTypeName(medicineTypeMasterDTO.getString("typeName"));
            }
            bkdGroupMedicineDTO.setInstructions(cmGroupMedicineMaster.getInstruction());
            bkdGroupMedicineDTO.setNumberOfDays(cmGroupMedicineMaster.getNumberOfDays());

            if(cmGroupMedicineMaster.getUomMasterId() != null) {
                bkdGroupMedicineDTO.setUomMasterId(cmGroupMedicineMaster.getUomMasterId());
                //call to get uom master
                JSONObject jsonObject = getUomMasterByUomMasterId(cmGroupMedicineMaster.getUomMasterId());
                if (jsonObject != null) {
                    bkdGroupMedicineDTO.setUomMasterName(jsonObject.getString("uomName"));
                }
            }
            //framing cm group medicine dto
            if(cmMedicineGroups != null){
                for(CmMedicineGroup cmMedicineGroup : cmMedicineGroups){
                    BKDGroupResponseDTO bkdGroupResponseDTO = new BKDGroupResponseDTO();
                    bkdGroupResponseDTO.setCmMedicineGroupId(cmMedicineGroup.getId());
                    bkdGroupResponseDTO.setProductCatalogueCommonDetailId(cmMedicineGroup.getProductCatalogueCommonDetailId());
                    //call to get product name
                    JSONObject productCatalogueCommonDetail =  serviceGateway.getProductCatalogueCommonDetailById(cmMedicineGroup.getProductCatalogueCommonDetailId());
                    if(productCatalogueCommonDetail != null) {
                        JSONObject productCatalogueDetailDTO = productCatalogueCommonDetail.getJSONObject("productCatalogueDetailDTO");
                        bkdGroupResponseDTO.setProductCatalogueCommonDetailName(productCatalogueDetailDTO.getString("itemName"));
                    }
                    if(cmMedicineGroup.getUomMasterId() != null) {
                        bkdGroupResponseDTO.setUomMasterId(cmMedicineGroup.getUomMasterId());
                        //call to get uom master
                        JSONObject jsonObjectUomMaster = getUomMasterByUomMasterId(cmMedicineGroup.getUomMasterId());
                        if (jsonObjectUomMaster != null) {
                            bkdGroupResponseDTO.setUomMasterName(jsonObjectUomMaster.getString("uomName"));
                        }
                    }
                    bkdGroupResponseDTO.setDosageQuantity(cmMedicineGroup.getComposition());
                    bkdGroupResponseDTO.setTotalQuantity(cmMedicineGroup.getTotalQuantity());
                    bkdGroupResponseDTO.setInstruction(cmMedicineGroup.getInstruction());
                    bkdGroupResponseDTOs.add(bkdGroupResponseDTO);
                }
                bkdGroupMedicineDTO.setBkdGroupResponseDTOs(bkdGroupResponseDTOs);
            }
            // setting the dto to response
            response = new GetBkdGroupMedicineByIdServiceResponse(bkdGroupMedicineDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved bkd medicine group details by id Successfully");
            log.debug("Retrieved bkd medicine group details by id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve bkd medicine group details by id");
            log.error("Failed to retrieve bkd medicine group details by id");
        }
        return response;
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
