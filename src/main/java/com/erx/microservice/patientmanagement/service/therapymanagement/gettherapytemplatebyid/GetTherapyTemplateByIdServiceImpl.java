package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapytemplatebyid;

/*
* created by Latha on 27-08-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplate;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMapping;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMedicine;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMedicineType;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateMappingRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateMedicineRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateMedicineTypeRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyTemplateRepository;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyTemplateGetDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyTemplateMappingGetDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyTemplateMedicineGetDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.TherapyTemplateMedicineTypeGetDTO;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("getTherapyTemplateByIdService")
public class GetTherapyTemplateByIdServiceImpl implements GetTherapyTemplateByIdService {

    private final Logger log = LoggerFactory.getLogger(GetTherapyTemplateByIdServiceImpl.class);

    @Autowired
    private TherapyTemplateRepository therapyTemplateRepository;

    @Autowired
    private TherapyTemplateMappingRepository therapyTemplateMappingRepository;

    @Autowired
    private TherapyTemplateMedicineRepository therapyTemplateMedicineRepository;

    @Autowired
    private TherapyTemplateMedicineTypeRepository therapyTemplateMedicineTypeRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public GetTherapyTemplateByIdServiceResponse execute(GetTherapyTemplateByIdServiceRequest request) throws ServiceException {
        GetTherapyTemplateByIdServiceResponse response = null;
        TherapyTemplate therapyTemplate = new TherapyTemplate();
        TherapyTemplateGetDTO therapyTemplateGetDTO = new TherapyTemplateGetDTO();
        List<TherapyTemplateMappingGetDTO> therapyTemplateMappingGetDTOs = new ArrayList<>();
        List<TherapyTemplateMedicine> therapyTemplateMedicines = new ArrayList<>();
        List<TherapyTemplateMedicineType> therapyTemplateMedicineTypes = new ArrayList<>();
        List<TherapyTemplateMapping> therapyTemplateMappings = new ArrayList<>();
        try {
            log.debug("Call to get therapy template by id" + request.getTherapyTemplateId());
            //retrieve therapy template
            therapyTemplate = therapyTemplateRepository.findOne(request.getTherapyTemplateId());
            //frame therapy template dto
            if(therapyTemplate != null)
                    therapyTemplateGetDTO.setTherapyTemplateId(therapyTemplate.getId());
                    therapyTemplateGetDTO.setName(therapyTemplate.getName());
                    therapyTemplateGetDTO.setTherapyGroupName(therapyTemplate.getTherapyGroup());
                   // therapyTemplateGetDTO.setSpecialInstruction(therapyTemplate.getSpecialInstruction());

            //retrieve therapy template mapping

            therapyTemplateMappings = therapyTemplateMappingRepository.findByTherapyTemplateId(request.getTherapyTemplateId());

            if(therapyTemplateMappings != null){
                for(TherapyTemplateMapping therapyTemplateMapping : therapyTemplateMappings){
                    TherapyTemplateMappingGetDTO therapyTemplateMappingGetDTO = new TherapyTemplateMappingGetDTO();
                    therapyTemplateMappingGetDTO.setTherapyTemplateMappingId(therapyTemplateMapping.getId());
                    //call to get service name which is therapy name by service id
                    if(therapyTemplateMapping.getServiceCatalogueId() != null) {
                        JSONObject jsonServiceObject = serviceGateway.getServiceCatalogueByServiceId(therapyTemplateMapping.getServiceCatalogueId());
                        if (jsonServiceObject != null) {
                            JSONObject serviceObjectJSONObject = jsonServiceObject.getJSONObject("serviceCatalogueDTO");
                            therapyTemplateMappingGetDTO.setServiceCatalogueId(therapyTemplateMapping.getServiceCatalogueId());
                            therapyTemplateMappingGetDTO.setTherapyName(serviceObjectJSONObject.getString("serviceName"));
                        }
                    }
                    therapyTemplateMappingGetDTO.setInstructions(therapyTemplateMapping.getInstructions());
                    therapyTemplateMappingGetDTO.setNumberOfDays(therapyTemplateMapping.getNumberOfDays());

                    //frame therapy template medicine
                    List<TherapyTemplateMedicineGetDTO> therapyTemplateMedicineGetDTOs = new ArrayList<>();
                    //retrieve therapy template medicine
                    therapyTemplateMedicines = therapyTemplateMedicineRepository.findByTherapyTemplateMappingId(therapyTemplateMapping.getId());
                    if(therapyTemplateMedicines != null)
                    for(TherapyTemplateMedicine therapyTemplateMedicine :therapyTemplateMedicines) {
                        TherapyTemplateMedicineGetDTO therapyTemplateMedicineGetDTO = new TherapyTemplateMedicineGetDTO();
                        therapyTemplateMedicineGetDTO.setTherapyTemplateMedicineId(therapyTemplateMedicine.getId());
                        //call to get product name
                        JSONObject productCatalogueCommonDetail = serviceGateway.getProductCatalogueCommonDetailById(therapyTemplateMedicine.getProductCatalogueCommonDetailId());
                        if (productCatalogueCommonDetail != null) {
                            JSONObject productCatalogueDetailDTO = productCatalogueCommonDetail.getJSONObject("productCatalogueDetailDTO");
                            therapyTemplateMedicineGetDTO.setProductCatalogueCommonDetailId(therapyTemplateMedicine.getProductCatalogueCommonDetailId());
                            therapyTemplateMedicineGetDTO.setMedicineName(productCatalogueDetailDTO.getString("itemName"));
                        }
                        //call to get medicine type name
                        JSONObject jsonObjectMedicineTypeMaster = serviceGateway.getMedicineTypeById(therapyTemplateMedicine.getMedicineTypeId());
                        if (jsonObjectMedicineTypeMaster != null){
                            JSONObject medicineTypeMasterDTO = jsonObjectMedicineTypeMaster.getJSONObject("medicineTypeMasterDTO");
                            therapyTemplateMedicineGetDTO.setMedicineTypeId(therapyTemplateMedicine.getMedicineTypeId());
                            therapyTemplateMedicineGetDTO.setMedicineTypeName(medicineTypeMasterDTO.getString("typeName"));
                         }
                        therapyTemplateMedicineGetDTO.setQuantity(therapyTemplateMedicine.getQuantity());
                        therapyTemplateMedicineGetDTO.setMedicineInstructions(therapyTemplateMedicine.getMedicineInstructions());
                        //call to get uom master
                        if(therapyTemplateMedicine.getUomMasterId() != null){
                            JSONObject jsonObjectUomMaster = getUomMasterByUomMasterId(therapyTemplateMedicine.getUomMasterId());
                            if(jsonObjectUomMaster != null)
                            therapyTemplateMedicineGetDTO.setUomMasterId(therapyTemplateMedicine.getUomMasterId());
                            therapyTemplateMedicineGetDTO.setUomMasterName(jsonObjectUomMaster.getString("uomName"));
                        }
                        therapyTemplateMedicineGetDTOs.add(therapyTemplateMedicineGetDTO);
                    }
                    //frame therapy template medicine type
                    List<TherapyTemplateMedicineTypeGetDTO> therapyTemplateMedicineTypeGetDTOs = new ArrayList<>();
                    //retrieve therapy template medicine type
                    therapyTemplateMedicineTypes = therapyTemplateMedicineTypeRepository.findByTherapyTemplateMappingId(therapyTemplateMapping.getId());
                    if(therapyTemplateMedicineTypes != null)
                        for(TherapyTemplateMedicineType therapyTemplateMedicineType : therapyTemplateMedicineTypes){
                            TherapyTemplateMedicineTypeGetDTO therapyTemplateMedicineTypeGetDTO = new TherapyTemplateMedicineTypeGetDTO();
                            therapyTemplateMedicineTypeGetDTO.setTherapyTemplateMedicineTypeId(therapyTemplateMedicineType.getId());
                            //call to get medicine type name
                            JSONObject jsonObjectMedicineTypeMaster = serviceGateway.getMedicineTypeById(therapyTemplateMedicineType.getMedicineTypeId());
                            if(jsonObjectMedicineTypeMaster != null) {
                                JSONObject medicineTypeMasterDTO = jsonObjectMedicineTypeMaster.getJSONObject("medicineTypeMasterDTO");
                                therapyTemplateMedicineTypeGetDTO.setMedicineTypeId(therapyTemplateMedicineType.getMedicineTypeId());
                                therapyTemplateMedicineTypeGetDTO.setMedicineTypeName(medicineTypeMasterDTO.getString("typeName"));
                            }
                            therapyTemplateMedicineTypeGetDTOs.add(therapyTemplateMedicineTypeGetDTO);
                        }
                    therapyTemplateMappingGetDTO.setTherapyTemplateMedicineGetDTOs(therapyTemplateMedicineGetDTOs);
                    therapyTemplateMappingGetDTO.setTherapyTemplateMedicineTypeGetDTOs(therapyTemplateMedicineTypeGetDTOs);
                    therapyTemplateMappingGetDTOs.add(therapyTemplateMappingGetDTO);
                }
                therapyTemplateGetDTO.setTherapyTemplateMappingGetDTOs(therapyTemplateMappingGetDTOs);
            }
            // setting the dto to response
            response = new GetTherapyTemplateByIdServiceResponse(therapyTemplateGetDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved therapy template Successfully");
            log.debug("Retrieved therapy template Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy template");
            log.error("Failed to retrieve therapy template");
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
