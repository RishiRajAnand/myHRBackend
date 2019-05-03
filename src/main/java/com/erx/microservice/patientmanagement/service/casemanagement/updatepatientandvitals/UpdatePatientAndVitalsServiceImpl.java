package com.erx.microservice.patientmanagement.service.casemanagement.updatepatientandvitals;

/*
* created by Latha on 18-08-2018
* */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.repository.LookupValueRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.repository.VitalRepository;
import com.erx.microservice.patientmanagement.service.datautil.datavalidation.casemanagementdatavalidation.DataValidationService;
import com.erx.microservice.patientmanagement.service.mapper.VitalMapper;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("updatePatientAndVitalsService")
public class UpdatePatientAndVitalsServiceImpl implements UpdatePatientAndVitalsService {

    private final Logger log = LoggerFactory.getLogger(UpdatePatientAndVitalsServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VitalMapper vitalMapper;

    @Autowired
    private VitalRepository vitalRepository;

    @Autowired
    private LookupValueRepository lookupValueRepository;

    @Autowired
    private DataValidationService dataValidationService;

    @Override
    public UpdatePatientAndVitalsServiceResponse execute(UpdatePatientAndVitalsServiceRequest request) throws ServiceException {

        UpdatePatientAndVitalsServiceResponse response = null;
        Patient patient = new Patient();
        LookupValue maritalLookupValue = new LookupValue();
        LookupValue bloodGroupLookupValue = new LookupValue();
        LookupValue occupationLookupValue = new LookupValue();
        Vital vital = new Vital();
        Vital savedVitals = null;
        try {
            log.debug("call to update vital and patient details");
            if(request.getPatientVitalsRequestDTO().getPatientId() != null)
            patient = patientRepository.findOne(request.getPatientVitalsRequestDTO().getPatientId());
            if(patient != null)
                if(request.getPatientVitalsRequestDTO().getMaritalStatusId() != null) {
                    maritalLookupValue = lookupValueRepository.findOne(request.getPatientVitalsRequestDTO().getMaritalStatusId());
                    patient.setMaritalStatus(maritalLookupValue);
                }
                if(request.getPatientVitalsRequestDTO().getBloodGroupId() != null){
                    bloodGroupLookupValue = lookupValueRepository.findOne(request.getPatientVitalsRequestDTO().getBloodGroupId());
                    patient.setBloodGroup(bloodGroupLookupValue);
                 }
                if(request.getPatientVitalsRequestDTO().getOccupationId() != null){
                    occupationLookupValue = lookupValueRepository.findOne(request.getPatientVitalsRequestDTO().getOccupationId());
                    patient.setOccupation(occupationLookupValue);
                }
                patient.setAllergies(request.getPatientVitalsRequestDTO().getAllergies());
            Patient savedPatient = patientRepository.save(patient);
            if(request.getPatientVitalsRequestDTO().getVitalsDTO() != null){
                Vital vitals = dataValidationService.validateVitals(request.getPatientVitalsRequestDTO());
                if(vitals == null) {
                    // trimming the vitals data
                    if(request.getPatientVitalsRequestDTO().getVitalsDTO() != null) {
                        if(request.getPatientVitalsRequestDTO().getVitalsDTO().getBmi() != null) {
                            request.getPatientVitalsRequestDTO().getVitalsDTO().setBmi(request.getPatientVitalsRequestDTO().getVitalsDTO().getBmi().trim());
                        }
                        if(request.getPatientVitalsRequestDTO().getVitalsDTO().getBp() != null) {
                            request.getPatientVitalsRequestDTO().getVitalsDTO().setBp(request.getPatientVitalsRequestDTO().getVitalsDTO().getBp().trim());
                        }
                        if(request.getPatientVitalsRequestDTO().getVitalsDTO().getHead() != null) {
                            request.getPatientVitalsRequestDTO().getVitalsDTO().setHead(request.getPatientVitalsRequestDTO().getVitalsDTO().getHead().trim());
                        }
                        if(request.getPatientVitalsRequestDTO().getVitalsDTO().getTemperature() != null) {
                            request.getPatientVitalsRequestDTO().getVitalsDTO().setTemperature(request.getPatientVitalsRequestDTO().getVitalsDTO().getTemperature().trim());
                        }
                        if(request.getPatientVitalsRequestDTO().getVitalsDTO().getWeight() != null) {
                            request.getPatientVitalsRequestDTO().getVitalsDTO().setWeight(request.getPatientVitalsRequestDTO().getVitalsDTO().getWeight().trim());
                        }
                        if(request.getPatientVitalsRequestDTO().getVitalsDTO().getPulse() != null) {
                            request.getPatientVitalsRequestDTO().getVitalsDTO().setPulse(request.getPatientVitalsRequestDTO().getVitalsDTO().getPulse().trim());
                        }
                        if(request.getPatientVitalsRequestDTO().getVitalsDTO().getHeight() != null) {
                            request.getPatientVitalsRequestDTO().getVitalsDTO().setHeight(request.getPatientVitalsRequestDTO().getVitalsDTO().getHeight().trim());
                        }
                        //convert DTO to domain
                        vital = vitalMapper.vitalDTOToVital(request.getPatientVitalsRequestDTO().getVitalsDTO());
                        vital.setPatient(patient);
                        savedVitals = vitalRepository.save(vital);
                    }

                }
            }

            //create response
            response = new UpdatePatientAndVitalsServiceResponse(savedPatient.getId(),savedVitals.getId());
            response.SUCCESSFUL = true;
            response.setMessage("Updated patient and patient vital details successfully");
            log.debug("Updated patient and patient vital details successfully");
        } catch (Exception e) {
            //create response
            response = new UpdatePatientAndVitalsServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to update patient and patient vital details successfully");
            log.error("Failed to Update patient and patient vital details successfully");
        }
        return response;
    }
}
