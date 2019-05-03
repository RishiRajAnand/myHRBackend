package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyserviceid;

/*
* created by Latha on 11-09-2018
* */

import com.erx.microservice.patientmanagement.domain.UserStaff;
import com.erx.microservice.patientmanagement.domain.therapymanagement.*;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.UserStaffRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.*;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.*;
import org.hibernate.service.spi.ServiceException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("getTherapyMasterByServiceIdService")
public class GetTherapyMasterByServiceIdServiceImpl implements GetTherapyMasterByServiceIdService {

    private final Logger log = LoggerFactory.getLogger(GetTherapyMasterByServiceIdServiceImpl.class);

    @Autowired
    private TherapyMasterRepository therapyMasterRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Autowired
    private TherapyRoomDetailsRepository therapyRoomDetailsRepository;

    @Autowired
    private TherapyMasterMedicineRepository therapyMasterMedicineRepository;

    @Autowired
    private TherapyMasterMedicineTypeRepository therapyMasterMedicineTypeRepository;

    @Override
    public GetTherapyMasterByServiceIdServiceResponse execute(GetTherapyMasterByServiceIdServiceRequest request) throws ServiceException {
        GetTherapyMasterByServiceIdServiceResponse response = null;
        TherapyMaster therapyMaster = new TherapyMaster();
        TherapyMasterGetDTO therapyMasterGetDTO = new TherapyMasterGetDTO();
        List<TherapyMasterMedicineTypeGetDTO> therapyMasterMedicineTypeGetDTOs = new ArrayList<>();
        List<TherapyMasterMedicineGetDTO> therapyMasterMedicineGetDTOs = new ArrayList<>();

        try {
            log.debug("Call to retrieve all therapy master detail for the clinic and therapy master id" + request.getClinicId(),request.getServiceCatalogueId());
            //retrieve the therapy master details
            therapyMaster = therapyMasterRepository.findByClinicAndService(request.getServiceCatalogueId(),request.getClinicId());
            if(therapyMaster != null) {
                therapyMasterGetDTO.setTherapyMasterId(therapyMaster.getId());
                //call to get service name which is therapy name by service id
                JSONObject jsonServiceObject = serviceGateway.getServiceCatalogueByServiceId(therapyMaster.getServiceCatalogueId());
                JSONObject serviceObjectJSONObject = jsonServiceObject.getJSONObject("serviceCatalogueDTO");
                if (serviceObjectJSONObject != null) {
                    therapyMasterGetDTO.setServiceCatalogueId(therapyMaster.getServiceCatalogueId());
                    therapyMasterGetDTO.setTherapyName(serviceObjectJSONObject.getString("serviceName"));
                }
                //call to get therapy department id
                if(therapyMaster.getTherapyDepartmentId() != null) {
                    JSONObject jsonObject = serviceGateway.getDepartmentByDepartmentById(therapyMaster.getTherapyDepartmentId());
                    JSONObject departmentMaster = jsonObject.getJSONObject("departmentMasterDetailsDTO");
                    if (departmentMaster != null) {
                        therapyMasterGetDTO.setTherapyDepartmentId(therapyMaster.getTherapyDepartmentId());
                        therapyMasterGetDTO.setTherapyDepartmentName(departmentMaster.getString("departmentName"));
                    }
                }
                therapyMasterGetDTO.setInstructions(therapyMaster.getInstructions());
                therapyMasterGetDTO.setDuration(therapyMaster.getDuration());
                therapyMasterGetDTO.setMedicineCharged(therapyMaster.isMedicineCharged());
                therapyMasterGetDTO.setNumTherapist(therapyMaster.getNumTherapist());
                //frame therapy master medicine dto
                therapyMasterMedicineGetDTOs = frameTherapyMasterMedicineGetDTO(therapyMaster);
                if (therapyMasterMedicineGetDTOs != null) {
                    therapyMasterGetDTO.setTherapyMasterMedicineGetDTOs(therapyMasterMedicineGetDTOs);
                }
                //frame therapy master medicine type dto
                therapyMasterMedicineTypeGetDTOs = frameTherapyMasterMedicineTypeGetDTO(therapyMaster);
                if (therapyMasterMedicineTypeGetDTOs != null) {
                    therapyMasterGetDTO.setTherapyMasterMedicineTypeGetDTOs(therapyMasterMedicineTypeGetDTOs);
                }
            }
            response = new GetTherapyMasterByServiceIdServiceResponse(therapyMasterGetDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved therapy master details by service id Successfully");
            log.debug("Retrieved therapy master details by service id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy master details by id");
            log.error("Failed to retrieve therapy master details by service id");
        }
        return response;
    }

    //method to frame therapy medicine get dto
    private List<TherapyMasterMedicineGetDTO> frameTherapyMasterMedicineGetDTO(TherapyMaster therapyMaster) {
        List<TherapyMasterMedicineGetDTO> therapyMasterMedicineGetDTOs = new ArrayList<>();
        try {
            log.debug("Call to frame therapy medicine get dto");
            // find therapy master medicine by therapy master
            List<TherapyMasterMedicine> therapyMasterMedicines = therapyMasterMedicineRepository.findByTherapyMasterId(therapyMaster.getId());
            if(therapyMasterMedicines != null)
                for(TherapyMasterMedicine therapyMasterMedicine : therapyMasterMedicines){
                    TherapyMasterMedicineGetDTO therapyMasterMedicineGetDTO = new TherapyMasterMedicineGetDTO();
                    therapyMasterMedicineGetDTO.setTherapyMasterMedicineId(therapyMasterMedicine.getId());
                    //call to get product name
                    JSONObject productCatalogueCommonDetail = serviceGateway.getProductCatalogueCommonDetailById(therapyMasterMedicine.getProductCatalogueCommonDetailId());
                    if (productCatalogueCommonDetail != null) {
                        therapyMasterMedicineGetDTO.setPmProdCatalogueCommonDetailsId(therapyMasterMedicine.getProductCatalogueCommonDetailId());
                        JSONObject productCatalogueDetailDTO = productCatalogueCommonDetail.getJSONObject("productCatalogueDetailDTO");
                        therapyMasterMedicineGetDTO.setMedicineName(productCatalogueDetailDTO.getString("itemName"));
                    }
                    //call to get medicine type name
                    JSONObject jsonObjectMedicineTypeMaster = serviceGateway.getMedicineTypeById(therapyMasterMedicine.getMedicineTypeId());
                    if(jsonObjectMedicineTypeMaster != null) {
                        JSONObject medicineTypeMasterDTO = jsonObjectMedicineTypeMaster.getJSONObject("medicineTypeMasterDTO");
                        therapyMasterMedicineGetDTO.setMedicineTypeMasterId(therapyMasterMedicine.getMedicineTypeId());
                        therapyMasterMedicineGetDTO.setMedicineTypeName(medicineTypeMasterDTO.getString("typeName"));
                    }
                    therapyMasterMedicineGetDTO.setQuantity(therapyMasterMedicine.getQuantity());
                    therapyMasterMedicineGetDTO.setMedicineInstructions(therapyMasterMedicine.getMedicineInstructions());
                    //call to get uom master
                    JSONObject jsonObject = serviceGateway.getUomMasterByUomMasterId(therapyMasterMedicine.getUomMasterId());
                    if(jsonObject != null){
                    JSONObject uomMaster = jsonObject.getJSONObject("uomMaster");
                        therapyMasterMedicineGetDTO.setUomMasterId(therapyMasterMedicine.getUomMasterId());
                        therapyMasterMedicineGetDTO.setUomMasterName(uomMaster.getString("uomName"));
                    }
                    therapyMasterMedicineGetDTOs.add(therapyMasterMedicineGetDTO);
            }
        } catch (JSONException e) {
            log.error("Failed to frame therapy master medicine dto" + e.getMessage());
        }
        return therapyMasterMedicineGetDTOs;
    }

    //method to frame TherapyMaster MedicineType GetDTO
    private List<TherapyMasterMedicineTypeGetDTO> frameTherapyMasterMedicineTypeGetDTO(TherapyMaster therapyMaster) {
        List<TherapyMasterMedicineTypeGetDTO> therapyMasterMedicineGetDTOs = new ArrayList<>();
        try {
            log.debug("Call to frame therapy medicine type get dto");
            // find therapy master medicine type by therapy master
            List<TherapyMasterMedicineType> therapyMasterMedicineTypes = therapyMasterMedicineTypeRepository.findByTherapyMasterId(therapyMaster.getId());
            if(therapyMasterMedicineTypes != null)
                for(TherapyMasterMedicineType therapyMasterMedicineType : therapyMasterMedicineTypes){
                    TherapyMasterMedicineTypeGetDTO therapyMasterMedicineTypeGetDTO = new TherapyMasterMedicineTypeGetDTO();
                    therapyMasterMedicineTypeGetDTO.setTherapyMasterMedicineTypeId(therapyMasterMedicineType.getId());
                    //call to get medicine type name
                    JSONObject jsonObjectMedicineTypeMaster = serviceGateway.getMedicineTypeById(therapyMasterMedicineType.getMedicineTypeId());
                    if(jsonObjectMedicineTypeMaster != null) {
                        JSONObject medicineTypeMasterDTO = jsonObjectMedicineTypeMaster.getJSONObject("medicineTypeMasterDTO");
                        therapyMasterMedicineTypeGetDTO.setMedicineTypeMasterId(therapyMasterMedicineType.getMedicineTypeId());
                        therapyMasterMedicineTypeGetDTO.setMedicineTypeName(medicineTypeMasterDTO.getString("typeName"));
                    }
                    therapyMasterMedicineGetDTOs.add(therapyMasterMedicineTypeGetDTO);
                }
        } catch (JSONException e) {
            log.error("Failed to frame therapy master medicine type dto" + e.getMessage());
        }
        return therapyMasterMedicineGetDTOs;
    }
}
