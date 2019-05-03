package com.erx.microservice.patientmanagement.service.therapymanagement.deletetherapymaster;

/*
* created by Latha on 10-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.*;
import com.erx.microservice.patientmanagement.repository.therapymanagement.*;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("deleteTherapyMasterService")
public class DeleteTherapyMasterServiceImpl implements DeleteTherapyMasterService {

    private final Logger log = LoggerFactory.getLogger(DeleteTherapyMasterServiceImpl.class);

    @Autowired
    private TherapyMasterRepository therapyMasterRepository;

    @Autowired
    private TherapyMasterMedicineRepository therapyMasterMedicineRepository;

    @Autowired
    private TherapyMasterMedicineTypeRepository therapyMasterMedicineTypeRepository;

    @Autowired
    private TherapyMasterTherapistDetailRepository therapyMasterTherapistDetailRepository;

    @Autowired
    private TherapyMasterRoomDetailRepository therapyMasterRoomDetailRepository;


    @Override
    public DeleteTherapyMasterServiceResponse execute(DeleteTherapyMasterServiceRequest request) throws ServiceException {
        DeleteTherapyMasterServiceResponse response = null;
        TherapyMaster therapyMaster = new TherapyMaster();
        List<TherapyMasterMedicine> therapyMasterMedicines = new ArrayList<>();
        List<TherapyMasterMedicineType> therapyMasterMedicineTypes = new ArrayList<>();
        List<TherapyMasterRoomDetail> therapyMasterRoomDetails = new ArrayList<>();
        List<TherapyMasterTherapistDetail> therapyMasterTherapistDetails = new ArrayList<>();
        try {
            log.debug("Call to delete therapy master detail");

            //find therapy master room detail
            therapyMasterRoomDetails = therapyMasterRoomDetailRepository.findByTherapyMasterId(request.getTherapyMasterId());
            if(therapyMasterRoomDetails != null) {
                //delete therapy master room detail
                for (TherapyMasterRoomDetail therapyMasterRoomDetail : therapyMasterRoomDetails) {
                    therapyMasterRoomDetail.setRecordStatus(0);
                    therapyMasterRoomDetailRepository.save(therapyMasterRoomDetail);
                }
            }
            //find therapy master therapist detail
            therapyMasterTherapistDetails = therapyMasterTherapistDetailRepository.findByTherapyMasterId(request.getTherapyMasterId());
            if(therapyMasterTherapistDetails != null) {
                //delete therapy master therapist detail
                for (TherapyMasterTherapistDetail therapyMasterTherapistDetail : therapyMasterTherapistDetails) {
                    therapyMasterTherapistDetail.setRecordStatus(0);
                    therapyMasterTherapistDetailRepository.save(therapyMasterTherapistDetail);
                }
            }
            //find therapy master medicine
            therapyMasterMedicines = therapyMasterMedicineRepository.findByTherapyMasterId(request.getTherapyMasterId());
            if(therapyMasterMedicines != null) {
                //delete therapy master medicine
                for (TherapyMasterMedicine therapyMasterMedicine : therapyMasterMedicines) {
                    therapyMasterMedicine.setRecordStatus(0);
                    therapyMasterMedicineRepository.save(therapyMasterMedicine);
                }
            }
            //find therapy master medicine type
            therapyMasterMedicineTypes = therapyMasterMedicineTypeRepository.findByTherapyMasterId(request.getTherapyMasterId());
            if(therapyMasterMedicineTypes != null){
                //delete therapy master medicine type
                for(TherapyMasterMedicineType therapyMasterMedicineType : therapyMasterMedicineTypes){
                    therapyMasterMedicineType.setRecordStatus(0);
                    therapyMasterMedicineTypeRepository.save(therapyMasterMedicineType);
                }
            }
            //find therapy master by id
            therapyMaster = therapyMasterRepository.findOne(request.getTherapyMasterId());
            if(therapyMaster != null)
            //delete therapy master by id
                therapyMaster.setRecordStatus(0);
            therapyMasterRepository.save(therapyMaster);
            // create response
            response = new DeleteTherapyMasterServiceResponse();
            response.setMessage("Deleted the Therapy master detail successfully");
        } catch (Exception e) {
            response = new DeleteTherapyMasterServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to delete therapy master detail");
            response.setMessage(e.getMessage() + " so,Failed to delete therapy master detail");
        }
        return response;
    }
}
