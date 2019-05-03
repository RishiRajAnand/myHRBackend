package com.erx.microservice.patientmanagement.service.therapymanagement.deletetreatmenttherapy;

/*
* created by Latha on 25-08-2018
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


@Service("deleteTreatmentTherapyService")
public class DeleteTreatmentTherapyServiceImpl implements DeleteTreatmentTherapyService {

    private final Logger log = LoggerFactory.getLogger(DeleteTreatmentTherapyServiceImpl.class);

    @Autowired
    private TherapyPlanningRepository therapyPlanningRepository;

    @Autowired
    private TherapyPlanningMedicineRepository therapyPlanningMedicineRepository;

    @Autowired
    private TherapyPlanningMedicineTypeRepository therapyPlanningMedicineTypeRepository;

    @Autowired
    private TherapyInstanceRepository therapyInstanceRepository;

    @Override
    public DeleteTreatmentTherapyServiceResponse execute(DeleteTreatmentTherapyServiceRequest request) throws ServiceException {
        DeleteTreatmentTherapyServiceResponse response = null;
        TherapyPlanning therapyPlanning = new TherapyPlanning();
        List<TherapyPlanningMedicine> therapyPlanningMedicines = new ArrayList<>();
        List<TherapyPlanningMedicineType> therapyPlanningMedicineTypes = new ArrayList<>();
        List<TherapyInstance> therapyInstances = new ArrayList<>();
        try {
            log.debug("Call to delete therapy planning detail");
            //find therapy planning medicine
            therapyPlanningMedicines = therapyPlanningMedicineRepository.findByTherapyPlanningId(request.getTherapyPlanningId());
            if(therapyPlanningMedicines != null) {
                //delete therapy planning medicine
                for (TherapyPlanningMedicine therapyPlanningMedicine : therapyPlanningMedicines) {
                    therapyPlanningMedicine.setRecordStatus(0);
                    therapyPlanningMedicineRepository.save(therapyPlanningMedicine);
                }
            }
            //find therapy planning medicine type
            therapyPlanningMedicineTypes = therapyPlanningMedicineTypeRepository.findByTherapyPlanningId(request.getTherapyPlanningId());
            if(therapyPlanningMedicineTypes != null){
                //delete therapy planning medicine type
                for(TherapyPlanningMedicineType therapyPlanningMedicineType : therapyPlanningMedicineTypes){
                    therapyPlanningMedicineType.setRecordStatus(0);
                    therapyPlanningMedicineTypeRepository.save(therapyPlanningMedicineType);
                }
            }

            //find therapy instances
            therapyInstances = therapyInstanceRepository.findByTherapyPlanningId(request.getTherapyPlanningId());

            if(therapyInstances != null){
                //delete therapy instances
                for(TherapyInstance therapyInstance : therapyInstances){
                    therapyInstance.setRecordStatus(0);
                    therapyInstanceRepository.save(therapyInstance);
                }
            }

            //find therapy planning by id
            therapyPlanning = therapyPlanningRepository.findOne(request.getTherapyPlanningId());
            if(therapyPlanning != null)
                //delete therapy planning by id
                therapyPlanning.setRecordStatus(0);
            therapyPlanningRepository.save(therapyPlanning);
            // create response
            response = new DeleteTreatmentTherapyServiceResponse();
            response.setMessage("Deleted the Therapy planning detail successfully");
        } catch (Exception e) {
            response = new DeleteTreatmentTherapyServiceResponse();
            response.SUCCESSFUL = false;
            log.error(e.getMessage() + " so,Failed to delete therapy planning detail");
            response.setMessage(e.getMessage() + " so,Failed to delete therapy planning detail");
        }
        return response;
    }
}
