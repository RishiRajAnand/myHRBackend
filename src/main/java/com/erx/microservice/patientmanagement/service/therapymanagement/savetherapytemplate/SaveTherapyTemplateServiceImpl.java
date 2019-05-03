package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapytemplate;

/*
* created by Latha on 07-09-2018
* */

import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplate;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMapping;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMedicine;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMedicineType;
import com.erx.microservice.patientmanagement.repository.ClinicRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateMappingRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateMedicineRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateMedicineTypeRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateRepository;
import com.erx.microservice.patientmanagement.service.datautil.datavalidation.casemanagementdatavalidation.DataValidationService;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.*;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("saveTherapyTemplateService")
public class SaveTherapyTemplateServiceImpl implements SaveTherapyTemplateService {

    private final Logger log = LoggerFactory.getLogger(SaveTherapyTemplateServiceImpl.class);

    @Autowired
    private TherapyTemplateRepository therapyTemplateRepository;

    @Autowired
    private DataValidationService dataValidationService;

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private TherapyTemplateMappingRepository therapyTemplateMappingRepository;

    @Autowired
    private TherapyTemplateMedicineRepository therapyTemplateMedicineRepository;

    @Autowired
    private TherapyTemplateMedicineTypeRepository therapyTemplateMedicineTypeRepository;

    @Override
    public SaveTherapyTemplateServiceResponse execute(SaveTherapyTemplateServiceRequest request) throws ServiceException {
        SaveTherapyTemplateServiceResponse response = null;
        TherapyTemplate therapyTemplate = new TherapyTemplate();
        TherapyTemplate savedTherapyTemplate = new TherapyTemplate();
        TherapyTemplateMapping therapyTemplateMapping = new TherapyTemplateMapping();
        TherapyTemplateMapping savedTherapyTemplateMapping = new TherapyTemplateMapping();
        List<TherapyTemplateMedicine> therapyTemplateMedicines = new ArrayList<>();
        List<TherapyTemplateMedicine> savedTherapyTemplateMedicines = new ArrayList<>();
        SaveTherapyTemplateResponseDTO saveTherapyTemplateResponseDTO = new SaveTherapyTemplateResponseDTO();
        List<TherapyTemplateMedicineType> therapyTemplateMedicineTypes = new ArrayList<>();
        List<TherapyTemplateMedicineType> savedTherapyTemplateMedicineTypes = new ArrayList<>();
        try {

            log.debug("Call to save therapy template");
            //validate whether group name is exist or not
            if(request.getSaveTherapyTemplateDTO().getTherapyTemplateId() == null) {
                response = dataValidationService.validateTherapyTemplateName(request.getSaveTherapyTemplateDTO().getName(), request.getSaveTherapyTemplateDTO().getClinicId());
                if (response.SUCCESSFUL == false) {
                    return response;
                }
            }
            //frame therapy template
            therapyTemplate = frameTherapyTemplate(request.getSaveTherapyTemplateDTO());
            if(therapyTemplate != null)

                //save cm template
                savedTherapyTemplate = therapyTemplateRepository.save(therapyTemplate);
            saveTherapyTemplateResponseDTO.setTherapyTemplateId(savedTherapyTemplate.getId());
            //frame therapy template mapping
            therapyTemplateMapping = frameTherapyTemplateMapping(savedTherapyTemplate,request.getSaveTherapyTemplateDTO().getSaveTherapyTemplateMappingDTO());

            if(therapyTemplateMapping != null)
                //save therapy template mapping

                savedTherapyTemplateMapping = therapyTemplateMappingRepository.save(therapyTemplateMapping);
            saveTherapyTemplateResponseDTO.setTherapyTemplateMappingId(savedTherapyTemplateMapping.getServiceCatalogueId());

            //frame therapy template medicine
            if(request.getSaveTherapyTemplateDTO().getSaveTherapyTemplateMappingDTO().getSaveTherapyTemplateMedicineDTOs() != null){
                therapyTemplateMedicines = frameTherapyTemplateMedicines(savedTherapyTemplateMapping, request.getSaveTherapyTemplateDTO().getSaveTherapyTemplateMappingDTO().getSaveTherapyTemplateMedicineDTOs());
                if(therapyTemplateMedicines != null)
                    //saving therapy template medicines
                    savedTherapyTemplateMedicines = therapyTemplateMedicineRepository.save(therapyTemplateMedicines);
                List<Long> therapyTemplateMedicineIds = new ArrayList<>();
                //setting the saved data to response dto
                for(TherapyTemplateMedicine therapyTemplateMedicine : savedTherapyTemplateMedicines){
                    Long therapyTemplateMedicineId = null;
                    therapyTemplateMedicineId = therapyTemplateMedicine.getId();
                    therapyTemplateMedicineIds.add(therapyTemplateMedicineId);
                    saveTherapyTemplateResponseDTO.setTherapyTemplateMedicineIds(therapyTemplateMedicineIds);
                }
            }

            //frame therapy Template Medicine Types
            if(request.getSaveTherapyTemplateDTO().getSaveTherapyTemplateMappingDTO().getSaveTherapyTemplateMedicineTypeDTOs() != null){
                therapyTemplateMedicineTypes = frameTherapyTemplateMedicineTypes(savedTherapyTemplateMapping, request.getSaveTherapyTemplateDTO().getSaveTherapyTemplateMappingDTO().getSaveTherapyTemplateMedicineTypeDTOs());
                if(therapyTemplateMedicineTypes != null)
                    //save therapy template medicine type
                    savedTherapyTemplateMedicineTypes = therapyTemplateMedicineTypeRepository.save(therapyTemplateMedicineTypes);
                List<Long> therapyTemplateMedicineTypeIds = new ArrayList<>();
                //setting the saved data to response dto
                for(TherapyTemplateMedicineType therapyTemplateMedicineType : savedTherapyTemplateMedicineTypes){
                    Long therapyTemplateMedicineTypeId = null;
                    therapyTemplateMedicineTypeId = therapyTemplateMedicineType.getId();
                    therapyTemplateMedicineTypeIds.add(therapyTemplateMedicineTypeId);
                    saveTherapyTemplateResponseDTO.setTherapyTemplateMedicineTypeIds(therapyTemplateMedicineTypeIds);
                }
            }

            //create response
            response = new SaveTherapyTemplateServiceResponse(saveTherapyTemplateResponseDTO);
            response.setMessage("Therapy template saved successfully");
            log.debug("Therapy template saved successfully");
        } catch (Exception e) {
            response = new SaveTherapyTemplateServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save Therapy template");
            response.setMessage(e.getMessage() + " so,Failed to save Therapy template");
        }
        return response;
    }

    //method to save therapy template
    private TherapyTemplate frameTherapyTemplate(SaveTherapyTemplateDTO saveTherapyTemplateDTO) {
        TherapyTemplate therapyTemplate = new TherapyTemplate();
        Clinic clinic = new Clinic();
        try {
            log.debug("framing therapy template");
            // get clinic by clinic id
            clinic = clinicRepository.findOne(saveTherapyTemplateDTO.getClinicId());
            if(saveTherapyTemplateDTO.getTherapyTemplateId() != null){
                // find therapy template by id
                therapyTemplate = therapyTemplateRepository.findOne(saveTherapyTemplateDTO.getTherapyTemplateId());
                therapyTemplate = frameTherapyTemplateObj(therapyTemplate, saveTherapyTemplateDTO);
            }else{
                if(clinic != null)
                    therapyTemplate = frameTherapyTemplateObj(therapyTemplate, saveTherapyTemplateDTO);
                therapyTemplate.setClinic(clinic);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy template" + e.getMessage());
        }

        return therapyTemplate;
    }

    //method to frame therapy template obj
    private TherapyTemplate frameTherapyTemplateObj(TherapyTemplate therapyTemplate, SaveTherapyTemplateDTO saveTherapyTemplateDTO) {
        try {
            log.debug("Call to frameTherapyTemplateObj");
            therapyTemplate.setName(saveTherapyTemplateDTO.getName().trim());
            therapyTemplate.setTherapyGroup(saveTherapyTemplateDTO.getTherapyGroupName().trim());
           // therapyTemplate.setSpecialInstruction(saveTherapyTemplateDTO.getSpecialInstruction().trim());
        } catch (Exception e) {
            log.error("Failed to frame therapy template obj" + e.getMessage());
        }
        return therapyTemplate;
    }

    //method to frame therapy template mapping
    private TherapyTemplateMapping frameTherapyTemplateMapping(TherapyTemplate savedTherapyTemplate, SaveTherapyTemplateMappingDTO saveTherapyTemplateMappingDTO) {
        TherapyTemplateMapping therapyTemplateMapping = new TherapyTemplateMapping();
        try {
            log.debug("Call to frameTherapyTemplateMapping");
            if(saveTherapyTemplateMappingDTO.getTherapyTemplateMappingId() != null){
                therapyTemplateMapping = therapyTemplateMappingRepository.findOne(saveTherapyTemplateMappingDTO.getTherapyTemplateMappingId());
                therapyTemplateMapping = frameTherapyTemplateMappingObj(therapyTemplateMapping, saveTherapyTemplateMappingDTO);
            }else{
                therapyTemplateMapping = frameTherapyTemplateMappingObj(therapyTemplateMapping, saveTherapyTemplateMappingDTO);
                therapyTemplateMapping.setTherapyTemplate(savedTherapyTemplate);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy template mapping " + e.getMessage());
        }

        return therapyTemplateMapping;
    }

    //method to frame therapy template mapping obj
    private TherapyTemplateMapping frameTherapyTemplateMappingObj(TherapyTemplateMapping therapyTemplateMapping, SaveTherapyTemplateMappingDTO saveTherapyTemplateMappingDTO) {
        try {
            log.debug("Call to frameTherapyTemplateMapping obj");
            therapyTemplateMapping.setServiceCatalogueId(saveTherapyTemplateMappingDTO.getServiceCatalogueId());
            therapyTemplateMapping.setNumberOfDays(saveTherapyTemplateMappingDTO.getNumberOfDays());
            therapyTemplateMapping.setInstructions(saveTherapyTemplateMappingDTO.getInstructions().trim());
        } catch (Exception e) {
            log.error("Failed to frameTherapyTemplateMapping obj " + e.getMessage());
        }
        return therapyTemplateMapping;
    }

    //method to frame therapy template medicines
    private List<TherapyTemplateMedicine> frameTherapyTemplateMedicines(TherapyTemplateMapping savedTherapyTemplateMapping,
                                                                        List<SaveTherapyTemplateMedicineDTO> saveTherapyTemplateMedicineDTOs) {
        List<TherapyTemplateMedicine> therapyTemplateMedicines = null;
        try {
            log.debug("call to frame therapy template medicine");
            therapyTemplateMedicines = new ArrayList<>();
            for(SaveTherapyTemplateMedicineDTO saveTherapyTemplateMedicineDTO : saveTherapyTemplateMedicineDTOs){
                TherapyTemplateMedicine therapyTemplateMedicine = new TherapyTemplateMedicine();
                if(saveTherapyTemplateMedicineDTO.getTherapyTemplateMedicineId() != null){
                    therapyTemplateMedicine = therapyTemplateMedicineRepository.findOne(saveTherapyTemplateMedicineDTO.getTherapyTemplateMedicineId());
                    therapyTemplateMedicine = frameTherapyTemplateMedicine(therapyTemplateMedicine, saveTherapyTemplateMedicineDTO);
                }else{
                    therapyTemplateMedicine = frameTherapyTemplateMedicine(therapyTemplateMedicine, saveTherapyTemplateMedicineDTO);
                    therapyTemplateMedicine.setTherapyTemplateMapping(savedTherapyTemplateMapping);
                }
                therapyTemplateMedicines.add(therapyTemplateMedicine);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy template medicine" + e.getMessage());
        }
        return therapyTemplateMedicines;
    }

    // method to frame therapy template medicine obj
    private TherapyTemplateMedicine frameTherapyTemplateMedicine(TherapyTemplateMedicine therapyTemplateMedicine, SaveTherapyTemplateMedicineDTO saveTherapyTemplateMedicineDTO) {
        try {
            log.debug("call to frameTherapyTemplateMedicine");
            therapyTemplateMedicine.setProductCatalogueCommonDetailId(saveTherapyTemplateMedicineDTO.getProductCatalogueCommonDetailId());
            therapyTemplateMedicine.setMedicineTypeId(saveTherapyTemplateMedicineDTO.getMedicineTypeMasterId());
            therapyTemplateMedicine.setQuantity(saveTherapyTemplateMedicineDTO.getQuantity());
            therapyTemplateMedicine.setUomMasterId(saveTherapyTemplateMedicineDTO.getUomMasterId());
            therapyTemplateMedicine.setMedicineInstructions(saveTherapyTemplateMedicineDTO.getMedicineInstructions());
        } catch (Exception e) {
            log.error("Failed to frameTherapyTemplateMedicine" + e.getMessage());
        }
        return therapyTemplateMedicine;
    }

    //method to frame therapy template medicine types
    private List<TherapyTemplateMedicineType> frameTherapyTemplateMedicineTypes(TherapyTemplateMapping savedTherapyTemplateMapping,
                                                                                List<SaveTherapyTemplateMedicineTypeDTO> saveTherapyTemplateMedicineTypeDTOs) {
        List<TherapyTemplateMedicineType> therapyTemplateMedicineTypes = null;
        try {
            log.debug("call to frame therapy template medicine type");
            therapyTemplateMedicineTypes = new ArrayList<>();
            for (SaveTherapyTemplateMedicineTypeDTO saveTherapyTemplateMedicineTypeDTO : saveTherapyTemplateMedicineTypeDTOs) {
                TherapyTemplateMedicineType therapyTemplateMedicineType = new TherapyTemplateMedicineType();
                if (saveTherapyTemplateMedicineTypeDTO.getTherapyTemplateMedicineTypeId() != null) {
                    therapyTemplateMedicineType = therapyTemplateMedicineTypeRepository.findOne(saveTherapyTemplateMedicineTypeDTO.getTherapyTemplateMedicineTypeId());
                    therapyTemplateMedicineType.setMedicineTypeId(saveTherapyTemplateMedicineTypeDTO.getMedicineTypeId());
                } else {
                    therapyTemplateMedicineType.setTherapyTemplateMapping(savedTherapyTemplateMapping);
                    therapyTemplateMedicineType.setMedicineTypeId(saveTherapyTemplateMedicineTypeDTO.getMedicineTypeId());
                }
                therapyTemplateMedicineTypes.add(therapyTemplateMedicineType);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy template medicine type" + e.getMessage());
        }
        return therapyTemplateMedicineTypes;
    }
}
