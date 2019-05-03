package com.erx.microservice.patientmanagement.service.ipadmission.patientsearch;

/*
* created by Latha on 06-12-17
* */

import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.service.dto.PatientDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("patientSearchService")
public class PatientSearchServiceImpl implements PatientSearchService {

    private final Logger log = LoggerFactory.getLogger(PatientSearchServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public PatientSearchServiceResponse execute(PatientSearchServiceRequest request) throws ServiceException {

        PatientSearchServiceResponse response = null;
        List<Patient> patients = null;
        List<PatientDTO> patientDTOs = new ArrayList<>();
        Patient patient = new Patient();
        try {
            log.debug("Call to search patient by " + request.getSearchParam() + " for the value " + request.getSearchValue());
            if (request.getClinicId() != null & request.getSearchParam() != null & request.getSearchValue() != null) {
                //retrieve the searchParam from request
                String searchParam = request.getSearchParam();
                switch (searchParam) {
                    case "MRN":
                        //retrieve objects
                        patients = patientRepository.getPatientByMRN(request.getClinicId(), request.getSearchValue());
                        //set the list of PatientDTO
                        for (Patient patientDetail : patients) {
                            //create PatientDTO object
                            PatientDTO patientDTO = new PatientDTO();

                            //set the values
                            patientDTO.setPatientId(patientDetail.getId());
                            patientDTO.setPatientMRN(patientDetail.getPatientIdExternal());
                            patientDTO.setPatientName(patientDetail.getPatientName());
                            patientDTO.setGender(patientDetail.getGender());
                            //   patientDTO.setAge(patient.geta);
                            patientDTO.setMobileNumber(patientDetail.getMobileNumber());
                            if (patientDetail.getPatientAdditionalDetail() != null && patientDetail.getPatientAdditionalDetail().getPatientType() != null) {
                                patientDTO.setPatientType(patientDetail.getPatientAdditionalDetail().getPatientType().getPatientTypeName());
                            }
                            //add to list
                            patientDTOs.add(patientDTO);
                        }
                        break;

                    default:
                        //retrieve objects
                        patients = patientRepository.getPatientByClinic(request.getClinicId());
                        //set the list of PatientDTO
                        for (Patient patientDetail : patients) {
                            //create PatientDTO object
                            PatientDTO patientDTO = new PatientDTO();

                            //set the values
                            patientDTO.setPatientId(patientDetail.getId());
                            patientDTO.setPatientMRN(patientDetail.getPatientIdExternal());
                            patientDTO.setPatientName(patientDetail.getPatientName());
                            patientDTO.setGender(patientDetail.getGender());
                            //   patientDTO.setAge(patient.geta);
                            patientDTO.setMobileNumber(patientDetail.getMobileNumber());
                            if (patientDetail.getPatientAdditionalDetail() != null) {
                                patientDTO.setPatientType(patientDetail.getPatientAdditionalDetail().getPatientType().getPatientTypeName());
                            }
                            //add to list
                            patientDTOs.add(patientDTO);
                        }
                }
                response = new PatientSearchServiceResponse(patientDTOs);
                response.SUCCESSFUL = true;
                response.setMessage("Retrieved Patient details Successfully");
                log.debug("Retrieved Patient details Successfully");
            }

        } catch (Exception e) {
            response = new PatientSearchServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Patient details");
            log.error("Failed to retrieve Patient details");
        }
        return response;
    }

}
