package com.erx.microservice.patientmanagement.service.casemanagement.savebkdgroupmedicine;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmDosageValueMapping;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmGroupMedicineMaster;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMedicineGroup;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.ClinicRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmDosageValueMappingRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmGroupMedicineMasterRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMedicineGroupRepository;
import com.erx.microservice.patientmanagement.service.datautil.datavalidation.casemanagementdatavalidation.DataValidationService;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.BKDGroupDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveBKDGroupMedicineDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("saveBKDGroupMedicineService")
public class SaveBKDGroupMedicineServiceImpl implements SaveBKDGroupMedicineService {

    private final Logger log = LoggerFactory.getLogger(SaveBKDGroupMedicineServiceImpl.class);

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private CmDosageValueMappingRepository cmDosageValueMappingRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Autowired
    private CmGroupMedicineMasterRepository cmGroupMedicineMasterRepository;

    @Autowired
    private CmMedicineGroupRepository cmMedicineGroupRepository;

    @Autowired
    private DataValidationService dataValidationService;

    @Override
    public SaveBKDGroupMedicineServiceResponse execute(SaveBKDGroupMedicineServiceRequest request) throws ServiceException {
        SaveBKDGroupMedicineServiceResponse response = new SaveBKDGroupMedicineServiceResponse();
        CmGroupMedicineMaster cmGroupMedicineMaster = new CmGroupMedicineMaster();
        CmGroupMedicineMaster savedCmGroupMedicineMaster = new CmGroupMedicineMaster();
        List<CmMedicineGroup> cmMedicineGroups = new ArrayList<>();

        try {
            log.debug("call to save bkd group medicine");

            //validate whether group name is exist or not
            if(request.getSaveBKDGroupMedicineDTO().getCmGroupMedicineMasterId() == null) {
                response = dataValidationService.validateBkdGroupName(request.getSaveBKDGroupMedicineDTO().getMedicineName(), request.getSaveBKDGroupMedicineDTO().getClinicId());
                if (response.SUCCESSFUL == false) {
                    return response;
                }
            }
            //framing the cm group medicine master
            cmGroupMedicineMaster = frameCmGroupMedicineMaster(request.getSaveBKDGroupMedicineDTO());
            if(cmGroupMedicineMaster != null){
                // saving the group medicine master
                 savedCmGroupMedicineMaster = cmGroupMedicineMasterRepository.save(cmGroupMedicineMaster);
            }
            //framing the cm medicine group
            cmMedicineGroups = frameMedicineGroup(savedCmGroupMedicineMaster,request.getSaveBKDGroupMedicineDTO().getBkdGroupDTOs());
            if(cmMedicineGroups != null){
                List<CmMedicineGroup> savedCmMedicineGroups = cmMedicineGroupRepository.save(cmMedicineGroups);
            }
            //create response
            response = new SaveBKDGroupMedicineServiceResponse(savedCmGroupMedicineMaster.getId());
            response.setMessage("Case sheet medicine treatment saved successfully");
            log.debug("Case sheet medicine treatment saved successfully");
        } catch (Exception e) {
            response = new SaveBKDGroupMedicineServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save bkd medicine group");
            response.setMessage(e.getMessage() + " so,Failed to save bkd medicine group");
        }
        return response;
    }

    // method to frame cm group medicine master
    private CmGroupMedicineMaster frameCmGroupMedicineMaster(SaveBKDGroupMedicineDTO saveBKDGroupMedicineDTO) {
        CmGroupMedicineMaster cmGroupMedicineMaster = new CmGroupMedicineMaster();
        try {
            if(saveBKDGroupMedicineDTO.getCmGroupMedicineMasterId() == null) {
                cmGroupMedicineMaster = frameCmGroupMedicineMasterObj(saveBKDGroupMedicineDTO, cmGroupMedicineMaster);
            }else{
                cmGroupMedicineMaster = cmGroupMedicineMasterRepository.findOne(saveBKDGroupMedicineDTO.getCmGroupMedicineMasterId());
                cmGroupMedicineMaster = frameCmGroupMedicineMasterObj(saveBKDGroupMedicineDTO, cmGroupMedicineMaster);
            }
            Clinic clinic = clinicRepository.findOne(saveBKDGroupMedicineDTO.getClinicId());
            cmGroupMedicineMaster.setClinic(clinic);
            if (saveBKDGroupMedicineDTO.getCmDosageValueMappingId() != null) {
                CmDosageValueMapping cmDosageValueMapping = cmDosageValueMappingRepository.findOne(saveBKDGroupMedicineDTO.getCmDosageValueMappingId());
                cmGroupMedicineMaster.setCmDosageValueMapping(cmDosageValueMapping);
            }
            //call to get manufactureId
            Long manufactureId = serviceGateway.getManufactureId(clinic.getId());
            if (manufactureId != null) {
                cmGroupMedicineMaster.setManufacturerId(manufactureId);
            }

        } catch (Exception e) {
            log.error("failed to frame cm group medicine master" + e.getMessage());
        }
        return cmGroupMedicineMaster;
    }

    //method to save cm group medicine master obj
    private CmGroupMedicineMaster frameCmGroupMedicineMasterObj(SaveBKDGroupMedicineDTO saveBKDGroupMedicineDTO, CmGroupMedicineMaster cmGroupMedicineMaster) {
        CmGroupMedicineMaster cmGroupMedicineMasters = new CmGroupMedicineMaster();
        try {
            if(cmGroupMedicineMaster != null){
                cmGroupMedicineMasters.setId(cmGroupMedicineMaster.getId());
            }
            cmGroupMedicineMasters.setDosageTime(saveBKDGroupMedicineDTO.getDosageTimeDTOs().toString());
            cmGroupMedicineMasters.setInstruction(saveBKDGroupMedicineDTO.getInstruction());
            cmGroupMedicineMasters.setMedicineName(saveBKDGroupMedicineDTO.getMedicineName().trim());
            cmGroupMedicineMasters.setMedicineTypeId(saveBKDGroupMedicineDTO.getMedicineTypeId());
            cmGroupMedicineMasters.setStrength(saveBKDGroupMedicineDTO.getStrength());
            cmGroupMedicineMasters.setTotalQuantity(saveBKDGroupMedicineDTO.getTotalQuantity());
            cmGroupMedicineMasters.setUomMasterId(saveBKDGroupMedicineDTO.getUomMasterId());
            cmGroupMedicineMasters.setNumberOfDays(saveBKDGroupMedicineDTO.getNumberOfDays());
        } catch (Exception e) {
            log.error("failed to frame cm group medicine master obj" + e.getMessage());
        }
        return cmGroupMedicineMasters;
    }

    //method to frame medicine group
    private List<CmMedicineGroup> frameMedicineGroup(CmGroupMedicineMaster savedCmGroupMedicineMaster, List<BKDGroupDTO> bkdGroupDTOs) {
        List<CmMedicineGroup> cmMedicineGroups = new ArrayList<>();
        try {
            for(BKDGroupDTO bkdGroupDTO : bkdGroupDTOs){
                CmMedicineGroup cmMedicineGroup = new CmMedicineGroup();
                if(bkdGroupDTO.getCmMedicineGroupId() == null) {
                    cmMedicineGroup = frameCmMedicineGroupObj(bkdGroupDTO, cmMedicineGroup);
                }else{
                    cmMedicineGroup = cmMedicineGroupRepository.findOne(bkdGroupDTO.getCmMedicineGroupId());
                    if(cmMedicineGroup != null)
                        cmMedicineGroup = frameCmMedicineGroupObj(bkdGroupDTO, cmMedicineGroup);
                }
                cmMedicineGroup.setGroupMedicineMaster(savedCmGroupMedicineMaster);
                cmMedicineGroups.add(cmMedicineGroup);
            }
        } catch (Exception e) {
            log.error("Failed to frame medicine group" + e.getMessage());
        }

        return cmMedicineGroups;
    }

    //method to frame cm medicine group obj
    private CmMedicineGroup frameCmMedicineGroupObj(BKDGroupDTO bkdGroupDTO,CmMedicineGroup cmMedicineGroup) {
        CmMedicineGroup cmMedicineGroups = new CmMedicineGroup();
        try {
            if(cmMedicineGroup != null){
                cmMedicineGroups.setId(cmMedicineGroup.getId());
            }
            cmMedicineGroups.setComposition(bkdGroupDTO.getDosageQuantity());
            cmMedicineGroups.setInstruction(bkdGroupDTO.getInstruction());
            cmMedicineGroups.setProductCatalogueCommonDetailId(bkdGroupDTO.getProductCatalogueCommonDetailId());
            cmMedicineGroups.setUomMasterId(bkdGroupDTO.getUomMasterId());
            cmMedicineGroups.setTotalQuantity(bkdGroupDTO.getTotalQuantity());
           // cmMedicineGroup.setStrength(bkdGroupDTO.getStrength());
        } catch (Exception e) {
            log.error("failed to frame cm medicine group obj" + e.getMessage());
        }
        return cmMedicineGroups;
    }
}
