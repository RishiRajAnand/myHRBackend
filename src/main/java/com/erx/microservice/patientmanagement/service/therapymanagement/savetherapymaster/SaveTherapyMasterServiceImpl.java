package com.erx.microservice.patientmanagement.service.therapymanagement.savetherapymaster;

/*
* created by Latha on 07-09-2018
* */

import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.therapymanagement.*;
import com.erx.microservice.patientmanagement.repository.ClinicRepository;
import com.erx.microservice.patientmanagement.repository.therapymanagement.*;
import com.erx.microservice.patientmanagement.service.datautil.datavalidation.casemanagementdatavalidation.DataValidationService;
import com.erx.microservice.patientmanagement.service.dto.therapymanagementdto.*;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("saveTherapyMasterService")
public class SaveTherapyMasterServiceImpl implements SaveTherapyMasterService {

    private final Logger log = LoggerFactory.getLogger(SaveTherapyMasterServiceImpl.class);

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private DataValidationService dataValidationService;

    @Autowired
    private TherapyMasterRepository therapyMasterRepository;

    @Autowired
    private TherapyMasterMedicineTypeRepository therapyMasterMedicineTypeRepository;

    @Autowired
    private TherapyMasterMedicineRepository therapyMasterMedicineRepository;

    @Autowired
    private TherapyMasterRoomDetailRepository therapyMasterRoomDetailRepository;

    @Autowired
    private TherapyMasterTherapistDetailRepository therapyMasterTherapistDetailRepository;

    @Override
    public SaveTherapyMasterServiceResponse execute(SaveTherapyMasterServiceRequest request) throws ServiceException {
        SaveTherapyMasterServiceResponse response = null;
        TherapyMaster therapyMaster = new TherapyMaster();
        List<TherapyMasterMedicineType> therapyMasterMedicineTypes = new ArrayList<>();
        List<TherapyMasterMedicine> therapyMasterMedicines = new ArrayList<>();
        List<TherapyMasterRoomDetail> therapyMasterRoomDetails = new ArrayList<>();
        List<TherapyMasterTherapistDetail> therapyMasterTherapistDetails = new ArrayList<>();
        TherapyMaster savedTherapyMaster = null;
        List<TherapyMasterMedicineType> savedTherapyMasterMedicineTypes = null;
        List<TherapyMasterMedicine> savedTherapyMasterMedicines = null;
        List<TherapyMasterRoomDetail> savedTherapyMasterRoomDetails = new ArrayList<>();
        List<TherapyMasterTherapistDetail> savedTherapyMasterTherapistDetails = new ArrayList<>();
        SaveTherapyMasterResponseDTO saveTherapyMasterResponseDTO = new SaveTherapyMasterResponseDTO();
        try {
            log.debug("Call to save therapy master for a clinic");
            //validate whether therapy is exist or not
            if(request.getSaveTherapyMasterDTO().getTherapyMasterId() == null) {
                response = dataValidationService.validateTherapyMaster(request.getSaveTherapyMasterDTO().getServiceCatalogueId(), request.getSaveTherapyMasterDTO().getClinicId());
                if (response.SUCCESSFUL == false) {
                    return response;
                }
            }
            //frame therapy master object
            therapyMaster = frameTherapyMaster(request.getSaveTherapyMasterDTO());
            if(therapyMaster != null)
            //save therapy master
                savedTherapyMaster = therapyMasterRepository.save(therapyMaster);
            //frame therapy master medicine types
            if(request.getSaveTherapyMasterDTO().getSaveTherapyMasterMedicineTypeDTOs() != null) {
                therapyMasterMedicineTypes = frameTherapyMasterMedicineTypes(savedTherapyMaster, request.getSaveTherapyMasterDTO().getSaveTherapyMasterMedicineTypeDTOs());
                if(therapyMasterMedicineTypes != null)
                    //save therapy master medicine type
                    savedTherapyMasterMedicineTypes = therapyMasterMedicineTypeRepository.save(therapyMasterMedicineTypes);
                List<Long> therapyMasterMedicineTypeIds = new ArrayList<>();
                for(TherapyMasterMedicineType therapyMasterMedicineType : savedTherapyMasterMedicineTypes){
                    Long therapyMasterMedicineTypeId = null;
                    therapyMasterMedicineTypeId = therapyMasterMedicineType.getId();
                    therapyMasterMedicineTypeIds.add(therapyMasterMedicineTypeId);
                    saveTherapyMasterResponseDTO.setTherapyMasterMedicineTypeId(therapyMasterMedicineTypeIds);
                }
            }
            //frame therapy master medicine
            if(request.getSaveTherapyMasterDTO().getSaveTherapyMasterMedicineDTOs() != null){
                therapyMasterMedicines = frameTherapyMasterMedicines(savedTherapyMaster, request.getSaveTherapyMasterDTO().getSaveTherapyMasterMedicineDTOs());
                if(therapyMasterMedicines != null)
                    savedTherapyMasterMedicines = therapyMasterMedicineRepository.save(therapyMasterMedicines);
                List<Long> therapyMasterMedicineIds = new ArrayList<>();
                for(TherapyMasterMedicine therapyMasterMedicine : savedTherapyMasterMedicines){
                    Long therapyMasterMedicineId = null;
                    therapyMasterMedicineId = therapyMasterMedicine.getId();
                    therapyMasterMedicineIds.add(therapyMasterMedicineId);
                    saveTherapyMasterResponseDTO.setTherapyMasterMedicineId(therapyMasterMedicineIds);
                }
            }

            //frame therapy master room details
            if(request.getSaveTherapyMasterDTO().getSaveTherapyMasterRoomDTOs() != null) {
                therapyMasterRoomDetails = frameTherapyMasterRoomDetails(savedTherapyMaster, request.getSaveTherapyMasterDTO().getSaveTherapyMasterRoomDTOs());
                if(therapyMasterRoomDetails != null)
                    //save therapy master medicine type
                    savedTherapyMasterRoomDetails = therapyMasterRoomDetailRepository.save(therapyMasterRoomDetails);
                List<Long> therapyMasterRoomIds = new ArrayList<>();
                for(TherapyMasterRoomDetail therapyMasterRoomDetail : savedTherapyMasterRoomDetails){
                    Long therapyMasterRoomId = null;
                    therapyMasterRoomId = therapyMasterRoomDetail.getId();
                    therapyMasterRoomIds.add(therapyMasterRoomId);
                    saveTherapyMasterResponseDTO.setTherapyMasterRoomDetailId(therapyMasterRoomIds);
                }
            }

            //frame therapy master therapist details
            if(request.getSaveTherapyMasterDTO().getSaveTherapyMasterTherapistDTOs() != null) {
                therapyMasterTherapistDetails = frameTherapyMasterTherapistDetails(savedTherapyMaster, request.getSaveTherapyMasterDTO().getSaveTherapyMasterTherapistDTOs());
                if(therapyMasterTherapistDetails != null)
                    //save therapy master therapist details
                    savedTherapyMasterTherapistDetails = therapyMasterTherapistDetailRepository.save(therapyMasterTherapistDetails);
                List<Long> therapyMasterTherapistIds = new ArrayList<>();
                for(TherapyMasterTherapistDetail therapyMasterTherapistDetail : savedTherapyMasterTherapistDetails){
                    Long therapyMasterTherapistId = null;
                    therapyMasterTherapistId = therapyMasterTherapistDetail.getId();
                    therapyMasterTherapistIds.add(therapyMasterTherapistId);
                    saveTherapyMasterResponseDTO.setTherapyMasterTherapistDetailId(therapyMasterTherapistIds);
                }
            }

            //create response
            saveTherapyMasterResponseDTO.setTherapyMasterId(savedTherapyMaster.getId());
            response = new SaveTherapyMasterServiceResponse(saveTherapyMasterResponseDTO);
            response.setMessage("Therapy Master Config saved successfully");
            log.debug("Therapy Master Config saved successfully");
        } catch (Exception e) {
            response = new SaveTherapyMasterServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to save Therapy Master");
            response.setMessage(e.getMessage() + " so,Failed to save Therapy Master");
        }
        return response;
    }

    //method to frame therapy master
    private TherapyMaster frameTherapyMaster(SaveTherapyMasterDTO saveTherapyMasterDTO) {
        TherapyMaster therapyMaster = new TherapyMaster();
        Clinic clinic = new Clinic();
        try {
            log.debug("framing therapy master");
            // get clinic by clinic id
            clinic = clinicRepository.findOne(saveTherapyMasterDTO.getClinicId());

            if(saveTherapyMasterDTO.getTherapyMasterId() != null){
                // find therapy master by id
                therapyMaster = therapyMasterRepository.findOne(saveTherapyMasterDTO.getTherapyMasterId());
                therapyMaster = frameTherapyMasterObj(therapyMaster, saveTherapyMasterDTO);
            }else{
                if(clinic != null)
                    therapyMaster = frameTherapyMasterObj(therapyMaster, saveTherapyMasterDTO);
                    therapyMaster.setClinic(clinic);
                therapyMaster.setTherapyDepartmentId(saveTherapyMasterDTO.getTherapyDepartmentId());
            }
        } catch (Exception e) {
           log.error("Failed to frame therapy master" + e.getMessage());
        }
        return therapyMaster;
    }

    //method to frame therapy master obj
    private TherapyMaster frameTherapyMasterObj(TherapyMaster therapyMaster, SaveTherapyMasterDTO saveTherapyMasterDTO) {
        try {
            log.debug("call to frame therapy master obj");
            therapyMaster.setServiceCatalogueId(saveTherapyMasterDTO.getServiceCatalogueId());
            therapyMaster.setInstructions(saveTherapyMasterDTO.getInstructions());
            therapyMaster.setDuration(saveTherapyMasterDTO.getDuration());
            therapyMaster.setMedicineCharged(saveTherapyMasterDTO.isMedicineCharged());
            therapyMaster.setNumTherapist(saveTherapyMasterDTO.getNumTherapist());
        } catch (Exception e) {
            log.error("failed to frame therapy master obj" + e.getMessage());
        }
        return therapyMaster;
    }

    //method to frame therapy master medicine types
    private List<TherapyMasterMedicineType> frameTherapyMasterMedicineTypes(TherapyMaster savedTherapyMaster,
                                                                            List<SaveTherapyMasterMedicineTypeDTO> saveTherapyMasterMedicineTypeDTOs) {

        List<TherapyMasterMedicineType> therapyMasterMedicineTypes = null;
        try {
            log.debug("call to frame therapy master medicine type");
            therapyMasterMedicineTypes = new ArrayList<>();
            for(SaveTherapyMasterMedicineTypeDTO saveTherapyMasterMedicineTypeDTO : saveTherapyMasterMedicineTypeDTOs){
                TherapyMasterMedicineType therapyMasterMedicineType = new TherapyMasterMedicineType();
                if(saveTherapyMasterMedicineTypeDTO.getTherapyMasterMedicineTypeId() != null){
                    therapyMasterMedicineType = therapyMasterMedicineTypeRepository.findOne(saveTherapyMasterMedicineTypeDTO.getTherapyMasterMedicineTypeId());
                    therapyMasterMedicineType.setMedicineTypeId(saveTherapyMasterMedicineTypeDTO.getMedicineTypeMasterId());
                }else{
                    therapyMasterMedicineType.setTherapyMaster(savedTherapyMaster);
                    therapyMasterMedicineType.setMedicineTypeId(saveTherapyMasterMedicineTypeDTO.getMedicineTypeMasterId());
                }
                therapyMasterMedicineTypes.add(therapyMasterMedicineType);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy master medicine type" + e.getMessage());
        }
        return therapyMasterMedicineTypes;
    }

    //method to frame therapy master medicines
    private List<TherapyMasterMedicine> frameTherapyMasterMedicines(TherapyMaster savedTherapyMaster, List<SaveTherapyMasterMedicineDTO> saveTherapyMasterMedicineDTOs) {
        List<TherapyMasterMedicine> therapyMasterMedicines = null;
        try {
            log.debug("call to frame therapy master medicine");
            therapyMasterMedicines = new ArrayList<>();
            for(SaveTherapyMasterMedicineDTO saveTherapyMasterMedicineDTO : saveTherapyMasterMedicineDTOs){
                TherapyMasterMedicine therapyMasterMedicine = new TherapyMasterMedicine();
                if(saveTherapyMasterMedicineDTO.getTherapyMasterMedicineId() != null){
                    therapyMasterMedicine = therapyMasterMedicineRepository.findOne(saveTherapyMasterMedicineDTO.getTherapyMasterMedicineId());
                    therapyMasterMedicine = frameTherapyMasterMedicine(therapyMasterMedicine, saveTherapyMasterMedicineDTO);
                }else{
                    therapyMasterMedicine = frameTherapyMasterMedicine(therapyMasterMedicine, saveTherapyMasterMedicineDTO);
                    therapyMasterMedicine.setTherapyMaster(savedTherapyMaster);
                }
                therapyMasterMedicines.add(therapyMasterMedicine);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy master medicine" + e.getMessage());
        }
        return therapyMasterMedicines;
    }

    // method to frame therapy master medicine obj
    private TherapyMasterMedicine frameTherapyMasterMedicine(TherapyMasterMedicine therapyMasterMedicine, SaveTherapyMasterMedicineDTO saveTherapyMasterMedicineDTO) {
        try {
            therapyMasterMedicine.setProductCatalogueCommonDetailId(saveTherapyMasterMedicineDTO.getPmProdCatalogueCommonDetailsId());
            therapyMasterMedicine.setMedicineTypeId(saveTherapyMasterMedicineDTO.getMedicineTypeMasterId());
            therapyMasterMedicine.setQuantity(saveTherapyMasterMedicineDTO.getQuantity());
            therapyMasterMedicine.setUomMasterId(saveTherapyMasterMedicineDTO.getUomMasterId());
            therapyMasterMedicine.setMedicineInstructions(saveTherapyMasterMedicineDTO.getMedicineInstructions());
        } catch (Exception e) {
            log.error("Failed to frame therapy master medicine" + e.getMessage());
        }
        return therapyMasterMedicine;
    }

    private List<TherapyMasterRoomDetail> frameTherapyMasterRoomDetails(TherapyMaster savedTherapyMaster,
                                                                        List<SaveTherapyMasterRoomDTO> saveTherapyMasterRoomDTOs) {
        List<TherapyMasterRoomDetail> therapyMasterRoomDetails = null;
        try {
            log.debug("call to frame therapy master room detail");
            therapyMasterRoomDetails = new ArrayList<>();
            for (SaveTherapyMasterRoomDTO saveTherapyMasterRoomDTO : saveTherapyMasterRoomDTOs) {
                TherapyMasterRoomDetail therapyMasterRoomDetail = new TherapyMasterRoomDetail();
                if (saveTherapyMasterRoomDTO.getTherapyMasterRoomDetailId() != null) {
                    therapyMasterRoomDetail = therapyMasterRoomDetailRepository.findOne(saveTherapyMasterRoomDTO.getTherapyMasterRoomDetailId());
                    therapyMasterRoomDetail.setRoomDetailId(saveTherapyMasterRoomDTO.getRoomDetailId());
                } else {
                    therapyMasterRoomDetail.setTherapyMaster(savedTherapyMaster);
                    therapyMasterRoomDetail.setRoomDetailId(saveTherapyMasterRoomDTO.getRoomDetailId());
                }
                therapyMasterRoomDetails.add(therapyMasterRoomDetail);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy master room detail" + e.getMessage());
        }
        return therapyMasterRoomDetails;
    }

    private List<TherapyMasterTherapistDetail> frameTherapyMasterTherapistDetails(TherapyMaster savedTherapyMaster,
                                                                                  List<SaveTherapyMasterTherapistDTO> saveTherapyMasterTherapistDTOs) {
        List<TherapyMasterTherapistDetail> therapyMasterTherapistDetails = null;
        try {
            log.debug("call to frame therapy master therapist detail");
            therapyMasterTherapistDetails = new ArrayList<>();
            for (SaveTherapyMasterTherapistDTO saveTherapyMasterTherapistDTO : saveTherapyMasterTherapistDTOs) {
                TherapyMasterTherapistDetail therapyMasterTherapistDetail = new TherapyMasterTherapistDetail();
                if (saveTherapyMasterTherapistDTO.getTherapyMasterTherapistDetailId() != null) {
                    therapyMasterTherapistDetail = therapyMasterTherapistDetailRepository.findOne(saveTherapyMasterTherapistDTO.getTherapyMasterTherapistDetailId());
                    therapyMasterTherapistDetail.setUserId(saveTherapyMasterTherapistDTO.getUserId());
                } else {
                    therapyMasterTherapistDetail.setTherapyMaster(savedTherapyMaster);
                    therapyMasterTherapistDetail.setUserId(saveTherapyMasterTherapistDTO.getUserId());
                }
                therapyMasterTherapistDetails.add(therapyMasterTherapistDetail);
            }
        } catch (Exception e) {
            log.error("Failed to frame therapy master therapist detail" + e.getMessage());
        }
        return therapyMasterTherapistDetails;
    }
}
