package com.erx.microservice.patientmanagement.service.casemanagement.savetherapymedicinedetail;

/*
* created by raushan on 24-10-2018
* */


import com.erx.microservice.patientmanagement.config.Constants;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanning;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanningMedicine;
import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyPlanningMedicineType;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterDetailsRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyPlanningMedicineRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.TherapyPlanningMedicineTypeRepository;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderInputDTO;
import com.erx.microservice.patientmanagement.service.dto.casebillingorderdto.CreateBillingOrderServiceDTO;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.SaveTherapyPlanningDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyMedicineDTO;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.SaveTreatmentTherapyMedicineTypeDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaveTherapyMedicineDetailServiceImpl implements SaveTherapyMedicineDetailService {

    private final Logger log = LoggerFactory.getLogger(SaveTherapyMedicineDetailService.class);

    @Autowired
    private TherapyPlanningMedicineTypeRepository therapyPlanningMedicineTypeRepository;

    @Autowired
    private TherapyPlanningMedicineRepository therapyPlanningMedicineRepository;

    @Autowired
    private CmMasterDetailsRepository cmMasterDetailsRepository;
    @Autowired
    private ServiceGateway serviceGateway;


    @Override
    public SaveTherapyMedicineDetailServiceResponse execute(SaveTherapyMedicineDetailServiceRequest request) throws ServiceException {
        log.debug("Call to save Case Medicine Detail");
        SaveTherapyMedicineDetailServiceResponse response = null;
        List<SaveTherapyPlanningDTO> saveTherapyPlanningDTOs = null;
        List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOsList = new ArrayList<>();
        CreateBillingOrderInputDTO createBillingOrderInputDTO = null;

        try {

            saveTherapyPlanningDTOs = request.getSaveTherapyPlanningDTOs();
            for (SaveTherapyPlanningDTO saveTherapyPlanningDTO : saveTherapyPlanningDTOs) {
                Long therapyPlanningId = saveTherapyPlanningDTO.getTherapyPlanningId();
                TherapyPlanning therapyPlanning = new TherapyPlanning();
                therapyPlanning.setId(therapyPlanningId);
                // saving therapy medicine types
                saveTherapyPlanningMedicineTypes(therapyPlanning, saveTherapyPlanningDTO.getSaveTreatmentTherapyMedicineTypeDTOs());
                List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOs = saveTherapyPlanningMedicines(therapyPlanning,
                        saveTherapyPlanningDTO.getSaveTreatmentTherapyMedicineDTOs(),
                        saveTherapyPlanningDTO.getServiceCatalogueId());
                if (!createBillingOrderServiceDTOs.isEmpty()) {
                    createBillingOrderServiceDTOsList.addAll(createBillingOrderServiceDTOs);
                }
            }
            if (!createBillingOrderServiceDTOsList.isEmpty()) {
                createBillingOrderInputDTO = new CreateBillingOrderInputDTO();
                Long orderId = cmMasterDetailsRepository.getOrderIdByCaseId(request.getCaseId());
                createBillingOrderInputDTO.setId(orderId);
                createBillingOrderInputDTO.setCreateBillingOrderServiceDTOList(createBillingOrderServiceDTOsList);
                createBillingOrderInputDTO.setType(Constants.THERAPY_MEDICINE_CONSTANT);
                createBillingOrderInputDTO.setOrderBy(request.getUserId());
                serviceGateway.updateTherapyMedicine(createBillingOrderInputDTO);
            }

            //create response
            response = new SaveTherapyMedicineDetailServiceResponse();
            response.setMessage("Case Medicine Detail saved successfully");
            log.debug("Case Medicine Detail saved successfully");
        } catch (Exception e) {
            response = new SaveTherapyMedicineDetailServiceResponse();
            response.SUCCESSFUL = false;
            log.error("Failed to save Case Medicine Detail" + e.getMessage());
            response.setMessage("Failed to save Case Medicine Detail" + e.getMessage());
        }

        return response;
    }

    // saving therapy medicine types
    private void saveTherapyPlanningMedicineTypes(TherapyPlanning therapyPlanning,
                                                  List<SaveTreatmentTherapyMedicineTypeDTO> saveTreatmentTherapyMedicineTypeDTOs) {

        List<TherapyPlanningMedicineType> therapyPlanningMedicineTypes = null;
        List<Long> therapyPlanningMedicineTypeIds = null;
        try {
            log.debug("call to frame therapy planning medicine type");
            therapyPlanningMedicineTypes = new ArrayList<>();
            //deleting existing therapyPlanningMedicineTypeIds
            therapyPlanningMedicineTypeIds = therapyPlanningMedicineTypeRepository.
                    getTherapyPlanningMedicineTypeIdsByPlanningId(therapyPlanning.getId());
            for (Long therapyPlanningMedicineTypeId : therapyPlanningMedicineTypeIds) {
                therapyPlanningMedicineTypeRepository.delete(therapyPlanningMedicineTypeId);
            }

            for (SaveTreatmentTherapyMedicineTypeDTO saveTreatmentTherapyMedicineTypeDTO : saveTreatmentTherapyMedicineTypeDTOs) {
                TherapyPlanningMedicineType therapyPlanningMedicineType = new TherapyPlanningMedicineType();
                //saving
                therapyPlanningMedicineType.setTherapyPlanning(therapyPlanning);
                therapyPlanningMedicineType.setMedicineTypeId(saveTreatmentTherapyMedicineTypeDTO.getMedicineTypeMasterId());
                therapyPlanningMedicineTypes.add(therapyPlanningMedicineType);
            }
            if (therapyPlanningMedicineTypes != null) {
                therapyPlanningMedicineTypeRepository.save(therapyPlanningMedicineTypes);
            }
        } catch (Exception e) {
            log.error("Failed to save therapy planning medicine type" + e.getMessage());
        }
    }

    //save therapy planning medicine
    private List<CreateBillingOrderServiceDTO> saveTherapyPlanningMedicines(TherapyPlanning therapyPlanning,
                                                                            List<SaveTreatmentTherapyMedicineDTO> saveTreatmentTherapyMedicineDTOs, Long serviceId) {
        List<TherapyPlanningMedicine> therapyPlanningMedicines = null;
        List<CreateBillingOrderServiceDTO> createBillingOrderServiceDTOList = new ArrayList<>();
        try {
            log.debug("call to save therapy planning medicine");
            therapyPlanningMedicines = new ArrayList<>();

            for (SaveTreatmentTherapyMedicineDTO saveTreatmentTherapyMedicineDTO : saveTreatmentTherapyMedicineDTOs) {
                CreateBillingOrderServiceDTO createBillingOrderServiceDTO = new CreateBillingOrderServiceDTO();
                if (saveTreatmentTherapyMedicineDTO.getProductStatus() == 0) {
                    continue;
                } else if (saveTreatmentTherapyMedicineDTO.getProductStatus() == 3) {
                    therapyPlanningMedicineRepository.delete(saveTreatmentTherapyMedicineDTO.getTherapyPlanningMedicineId());
                    createBillingOrderServiceDTO.setServiceId(saveTreatmentTherapyMedicineDTO.getPmProdCatalogueCommonDetailsId());
                    createBillingOrderServiceDTO.setType(Constants.PRODUCT_DELETE_CONSTANT);

                } else {
                    TherapyPlanningMedicine therapyPlanningMedicine = new TherapyPlanningMedicine();
                    therapyPlanningMedicine.setId(saveTreatmentTherapyMedicineDTO.getTherapyPlanningMedicineId());
                    therapyPlanningMedicine.setProductCatalogueCommonDetailId(saveTreatmentTherapyMedicineDTO.getPmProdCatalogueCommonDetailsId());
                    therapyPlanningMedicine.setMedicineTypeId(saveTreatmentTherapyMedicineDTO.getMedicineTypeMasterId());
                    therapyPlanningMedicine.setQuantity(saveTreatmentTherapyMedicineDTO.getQuantity());
                    therapyPlanningMedicine.setUomMasterId(saveTreatmentTherapyMedicineDTO.getUomMasterId());
                    therapyPlanningMedicine.setMedicineInstructions(saveTreatmentTherapyMedicineDTO.getMedicineInstructions());
                    therapyPlanningMedicine.setTherapyPlanning(therapyPlanning);
                    therapyPlanningMedicines.add(therapyPlanningMedicine);
                    createBillingOrderServiceDTO.setServiceId(saveTreatmentTherapyMedicineDTO.getPmProdCatalogueCommonDetailsId());
                    int quantity = Integer.parseInt(saveTreatmentTherapyMedicineDTO.getQuantity());
                    createBillingOrderServiceDTO.setQuantity(quantity);
                    if (saveTreatmentTherapyMedicineDTO.getProductStatus() == 2) {
                        createBillingOrderServiceDTO.setType(Constants.PRODUCT_UPDATE_CONSTANT);
                    } else {
                        createBillingOrderServiceDTO.setType(Constants.PRODUCT_TYPE);
                    }
                    createBillingOrderServiceDTO.setServiceCatalogueId(serviceId);

                }
                createBillingOrderServiceDTOList.add(createBillingOrderServiceDTO);
            }
            therapyPlanningMedicineRepository.save(therapyPlanningMedicines);
        } catch (Exception e) {
            log.error("Failed to save therapy medicine Detail " + e.getMessage());
        }
        return createBillingOrderServiceDTOList;

    }


}
