package com.erx.microservice.patientmanagement.service.therapymanagement.gettherapymasterbyid;

/*
* created by Latha on 04-09-2018
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


@Service("getTherapyMasterByIdService")
public class GetTherapyMasterByIdServiceImpl implements GetTherapyMasterByIdService {

    private final Logger log = LoggerFactory.getLogger(GetTherapyMasterByIdServiceImpl.class);

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

    @Autowired
    private TherapyMasterRoomDetailRepository therapyMasterRoomDetailRepository;

    @Autowired
    private TherapyMasterTherapistDetailRepository therapyMasterTherapistDetailRepository;

    @Autowired
    private UserStaffRepository userStaffRepository;

    @Override
    public GetTherapyMasterByIdServiceResponse execute(GetTherapyMasterByIdServiceRequest request) throws ServiceException {
        GetTherapyMasterByIdServiceResponse response = null;
        TherapyMaster therapyMaster = new TherapyMaster();
        TherapyMasterGetDTO therapyMasterGetDTO = new TherapyMasterGetDTO();
        List<TherapyMasterMedicineTypeGetDTO> therapyMasterMedicineTypeGetDTOs = new ArrayList<>();
        List<TherapyMasterMedicineGetDTO> therapyMasterMedicineGetDTOs = new ArrayList<>();
        List<TherapyMasterRoomDetailGetDTO> therapyMasterRoomDetailGetDTOs = new ArrayList<>();
        List<TherapyMasterTherapistDetailGetDTO> therapyMasterTherapistDetailGetDTOs = new ArrayList<>();
        try {
            log.debug("Call to retrieve all therapy master detail for the clinic and therapy master id" + request.getClinicId(),request.getTherapyMasterId());
            //retrieve the therapy master details
            therapyMaster = therapyMasterRepository.findByClinicAndId(request.getClinicId(),request.getTherapyMasterId());
            if(therapyMaster != null)
                therapyMasterGetDTO.setTherapyMasterId(therapyMaster.getId());
            //call to get service name which is therapy name by service id
            JSONObject jsonServiceObject = serviceGateway.getServiceCatalogueByServiceId(therapyMaster.getServiceCatalogueId());
            JSONObject serviceObjectJSONObject = jsonServiceObject.getJSONObject("serviceCatalogueDTO");
            if(serviceObjectJSONObject != null) {
                therapyMasterGetDTO.setServiceCatalogueId(therapyMaster.getServiceCatalogueId());
                therapyMasterGetDTO.setTherapyName(serviceObjectJSONObject.getString("serviceName"));
            }
            //call to get therapy department id
            JSONObject jsonObject = serviceGateway.getDepartmentByDepartmentById(therapyMaster.getTherapyDepartmentId());
            JSONObject departmentMaster = jsonObject.getJSONObject("departmentMasterDetailsDTO");
            if(departmentMaster != null) {
                therapyMasterGetDTO.setTherapyDepartmentId(therapyMaster.getTherapyDepartmentId());
                therapyMasterGetDTO.setTherapyDepartmentName(departmentMaster.getString("departmentName"));
            }
            therapyMasterGetDTO.setInstructions(therapyMaster.getInstructions());
            therapyMasterGetDTO.setDuration(therapyMaster.getDuration());
            therapyMasterGetDTO.setMedicineCharged(therapyMaster.isMedicineCharged());
            therapyMasterGetDTO.setNumTherapist(therapyMaster.getNumTherapist());
            //frame therapy master medicine dto
            therapyMasterMedicineGetDTOs = frameTherapyMasterMedicineGetDTO(therapyMaster);
            if(therapyMasterMedicineGetDTOs != null) {
                therapyMasterGetDTO.setTherapyMasterMedicineGetDTOs(therapyMasterMedicineGetDTOs);
            }
            //frame therapy master medicine type dto
            therapyMasterMedicineTypeGetDTOs = frameTherapyMasterMedicineTypeGetDTO(therapyMaster);
            if(therapyMasterMedicineTypeGetDTOs != null) {
                therapyMasterGetDTO.setTherapyMasterMedicineTypeGetDTOs(therapyMasterMedicineTypeGetDTOs);
            }

            //frame therapy master room detail dto
            therapyMasterRoomDetailGetDTOs = frameTherapyMasterRoomDetailGetDTO(therapyMaster);
            if(therapyMasterRoomDetailGetDTOs != null) {
                therapyMasterGetDTO.setTherapyMasterRoomDetailGetDTOs(therapyMasterRoomDetailGetDTOs);
            }

            //frame therapy master therapist detail dto
            therapyMasterTherapistDetailGetDTOs = frameTherapyMasterTherapistDetailGetDTO(therapyMaster);
            if(therapyMasterTherapistDetailGetDTOs != null) {
                therapyMasterGetDTO.setTherapyMasterTherapistDetailGetDTOs(therapyMasterTherapistDetailGetDTOs);
            }

            response = new GetTherapyMasterByIdServiceResponse(therapyMasterGetDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved therapy master details by id Successfully");
            log.debug("Retrieved therapy master details by id Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve therapy master details by id");
            log.error("Failed to retrieve therapy master details by id");
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
                    JSONObject uomMaster = jsonObject.getJSONObject("uomMaster");
                    if(uomMaster != null){
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

    //method to frame TherapyMaster room detail Get DTO
    private List<TherapyMasterRoomDetailGetDTO> frameTherapyMasterRoomDetailGetDTO(TherapyMaster therapyMaster) {
        List<TherapyMasterRoomDetailGetDTO> therapyMasterRoomDetailGetDTOs = new ArrayList<>();
        TherapyRoomDetails therapyRoomDetails = new TherapyRoomDetails();
        try {
            log.debug("Call to frame therapy medicine type get dto");
            // find therapy master room detail by therapy master
            List<TherapyMasterRoomDetail> therapyMasterRoomDetails = therapyMasterRoomDetailRepository.findByTherapyMasterId(therapyMaster.getId());
            if (therapyMasterRoomDetails != null)
                for (TherapyMasterRoomDetail therapyMasterRoomDetail : therapyMasterRoomDetails) {
                    TherapyMasterRoomDetailGetDTO therapyMasterRoomDetailGetDTO = new TherapyMasterRoomDetailGetDTO();
                    therapyMasterRoomDetailGetDTO.setTherapyMasterRoomDetailId(therapyMasterRoomDetail.getId());
                    // to find room details
                    therapyRoomDetails = therapyRoomDetailsRepository.findOne(therapyMasterRoomDetail.getRoomDetailId());
                    if(therapyRoomDetails != null){
                        if(therapyRoomDetails.getTherapyRoomMaster() != null)
                            therapyMasterRoomDetailGetDTO.setRoomDetailId(therapyMasterRoomDetail.getRoomDetailId());
                            therapyMasterRoomDetailGetDTO.setRoomName(therapyRoomDetails.getTherapyRoomMaster().getName());
                    }
                    therapyMasterRoomDetailGetDTOs.add(therapyMasterRoomDetailGetDTO);
                }
        } catch (Exception e) {
            log.error("Failed to frame therapy master room detail dto" + e.getMessage());
        }
        return therapyMasterRoomDetailGetDTOs;
    }

    //method to frame TherapyMaster therapist detail Get DTO
    private List<TherapyMasterTherapistDetailGetDTO> frameTherapyMasterTherapistDetailGetDTO(TherapyMaster therapyMaster) {
        List<TherapyMasterTherapistDetailGetDTO> therapyMasterTherapistDetailGetDTOs = new ArrayList<>();
        UserStaff userStaff = new UserStaff();
        try {
            log.debug("Call to frame therapy medicine type get dto");
            // find therapy master therapist detail by therapy master
            List<TherapyMasterTherapistDetail> therapyMasterTherapistDetails = therapyMasterTherapistDetailRepository.findByTherapyMasterId(therapyMaster.getId());
            if (therapyMasterTherapistDetails != null)
                for (TherapyMasterTherapistDetail therapyMasterTherapistDetail : therapyMasterTherapistDetails) {
                    TherapyMasterTherapistDetailGetDTO therapyMasterTherapistDetailGetDTO = new TherapyMasterTherapistDetailGetDTO();
                    therapyMasterTherapistDetailGetDTO.setTherapyMasterTherapistDetailId(therapyMasterTherapistDetail.getId());
                    // to find user
                    userStaff = userStaffRepository.findOne(therapyMasterTherapistDetail.getUserId());
                    if(userStaff != null){
                        if(userStaff != null)
                            therapyMasterTherapistDetailGetDTO.setUserId(userStaff.getId());
                        therapyMasterTherapistDetailGetDTO.setUserName(userStaff.getFirstName() + " " + userStaff.getLastName());
                    }
                    therapyMasterTherapistDetailGetDTOs.add(therapyMasterTherapistDetailGetDTO);
                }
        } catch (Exception e) {
            log.error("Failed to frame therapy master therapist detail dto" + e.getMessage());
        }
        return therapyMasterTherapistDetailGetDTOs;
    }
}
