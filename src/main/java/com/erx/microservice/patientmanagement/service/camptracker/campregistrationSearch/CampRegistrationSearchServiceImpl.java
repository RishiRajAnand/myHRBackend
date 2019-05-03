package com.erx.microservice.patientmanagement.service.camptracker.campregistrationSearch;

/*
* created by Brighty on 07-12-17
* */

import com.erx.microservice.patientmanagement.domain.AddressInfo;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.repository.AddressInfoRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.service.dto.CampRegistrationDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service("campRegistrationSearchService")
public class CampRegistrationSearchServiceImpl implements CampRegistrationSearchService {

    private final Logger log = LoggerFactory.getLogger(CampRegistrationSearchServiceImpl.class);

    @Autowired
    private AddressInfoRepository addressInfoRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public CampRegistrationSearchServiceResponse execute(CampRegistrationSearchServiceRequest request) throws ServiceException {

        CampRegistrationSearchServiceResponse response = null;
        List<Object[]> results = null;
        Patient patient = null;
        AddressInfo addressInfo = null;
        List<CampRegistrationDTO> campRegistrationDTOs = new ArrayList<>();
        try {
            log.debug("Call to Search CampRegistration");
            if (request.getClinicId() != null & request.getSearchParam() != null & request.getSearchValue() != null) {
                //retrieve the searchParam from request
                String searchParam = request.getSearchParam();
                switch (searchParam) {
                    case "phoneNumber":
                        //retrieve objects
                        //results = patientRepository.getCampPatientByPhoneNumber(request.getClinicId(), request.getSearchValue());
                        //set the list of PatientDTO
                        for (Object[] resultsDetail : results) {
                            //create PatientDTO object
                            CampRegistrationDTO campRegistrationDTO = new CampRegistrationDTO();

                            patient = (Patient) resultsDetail[0];

                            //set patient details
                            campRegistrationDTO.setPatientId(patient.getId());
                            campRegistrationDTO.setClinicId(patient.getClinic().getId());
                            //campRegistrationDTO.setCampRegistrationNumber(patient.getCampRegistrationNumber());
                            campRegistrationDTO.setFullName(patient.getPatientName());
                            LocalDateTime now = LocalDateTime.now();
                            int age = Period.between(patient.getDateOfBirth(), now.toLocalDate()).getYears();
                            campRegistrationDTO.setAge(age);
                            campRegistrationDTO.setGender(patient.getGender());
                            campRegistrationDTO.setMobileNumber(patient.getMobileNumber());
                            campRegistrationDTO.setMrn(patient.getPatientIdExternal());
                            campRegistrationDTO.setPatientRegisteredDate(patient.getPatientRegisteredDate());

                            if (resultsDetail[1] != null) {
                                addressInfo = (AddressInfo) resultsDetail[1];

                                if (addressInfo.getCity() != null) {
                                    campRegistrationDTO.setCity(addressInfo.getCity().getName());
                                }
                            }

                            //add to list
                            campRegistrationDTOs.add(campRegistrationDTO);
                        }
                        break;
                    case "mrn":
                        //retrieve objects
                        //results = patientRepository.getCampPatientByMRN(request.getClinicId(), request.getSearchValue());
                        //set the list of PatientDTO
                        for (Object[] resultsDetail : results) {
                            //create PatientDTO object
                            CampRegistrationDTO campRegistrationDTO = new CampRegistrationDTO();

                            patient = (Patient) resultsDetail[0];

                            //set patient details
                            campRegistrationDTO.setPatientId(patient.getId());
                            campRegistrationDTO.setClinicId(patient.getClinic().getId());
                            //campRegistrationDTO.setCampRegistrationNumber(patient.getCampRegistrationNumber());
                            campRegistrationDTO.setFullName(patient.getPatientName());
                            LocalDateTime now = LocalDateTime.now();
                            int age = Period.between(patient.getDateOfBirth(), now.toLocalDate()).getYears();
                            campRegistrationDTO.setAge(age);
                            campRegistrationDTO.setGender(patient.getGender());
                            campRegistrationDTO.setMobileNumber(patient.getMobileNumber());
                            campRegistrationDTO.setMrn(patient.getPatientIdExternal());
                            campRegistrationDTO.setPatientRegisteredDate(patient.getPatientRegisteredDate());

                            if (resultsDetail[1] != null) {
                                addressInfo = (AddressInfo) resultsDetail[1];

                                if (addressInfo.getCity() != null) {
                                    campRegistrationDTO.setCity(addressInfo.getCity().getName());
                                }
                            }

                            //add to list
                            campRegistrationDTOs.add(campRegistrationDTO);
                        }
                        break;
                    case "crn":
                        //retrieve objects
                        //results = patientRepository.getCampPatientByCRN(request.getClinicId(), request.getSearchValue());
                        //set the list of PatientDTO
                        for (Object[] resultsDetail : results) {
                            //create PatientDTO object
                            CampRegistrationDTO campRegistrationDTO = new CampRegistrationDTO();

                            patient = (Patient) resultsDetail[0];

                            //set patient details
                            campRegistrationDTO.setPatientId(patient.getId());
                            campRegistrationDTO.setClinicId(patient.getClinic().getId());
                            //campRegistrationDTO.setCampRegistrationNumber(patient.getCampRegistrationNumber());
                            campRegistrationDTO.setFullName(patient.getPatientName());
                            LocalDateTime now = LocalDateTime.now();
                            int age = Period.between(patient.getDateOfBirth(), now.toLocalDate()).getYears();
                            campRegistrationDTO.setAge(age);
                            campRegistrationDTO.setGender(patient.getGender());
                            campRegistrationDTO.setMobileNumber(patient.getMobileNumber());
                            campRegistrationDTO.setMrn(patient.getPatientIdExternal());
                            campRegistrationDTO.setPatientRegisteredDate(patient.getPatientRegisteredDate());

                            if (resultsDetail[1] != null) {
                                addressInfo = (AddressInfo) resultsDetail[1];

                                if (addressInfo.getCity() != null) {
                                    campRegistrationDTO.setCity(addressInfo.getCity().getName());
                                }
                            }

                            //add to list
                            campRegistrationDTOs.add(campRegistrationDTO);
                        }
                        break;
                    case "location":
                        //retrieve objects
                        //results = patientRepository.getCampPatientByLocation(request.getClinicId(), request.getSearchValue());
                        //set the list of PatientDTO
                        for (Object[] resultsDetail : results) {
                            //create PatientDTO object
                            CampRegistrationDTO campRegistrationDTO = new CampRegistrationDTO();

                            patient = (Patient) resultsDetail[0];

                            //set patient details
                            campRegistrationDTO.setPatientId(patient.getId());
                            campRegistrationDTO.setClinicId(patient.getClinic().getId());
                            //campRegistrationDTO.setCampRegistrationNumber(patient.getCampRegistrationNumber());
                            campRegistrationDTO.setFullName(patient.getPatientName());
                            LocalDateTime now = LocalDateTime.now();
                            int age = Period.between(patient.getDateOfBirth(), now.toLocalDate()).getYears();
                            campRegistrationDTO.setAge(age);
                            campRegistrationDTO.setGender(patient.getGender());
                            campRegistrationDTO.setMobileNumber(patient.getMobileNumber());
                            campRegistrationDTO.setMrn(patient.getPatientIdExternal());
                            campRegistrationDTO.setPatientRegisteredDate(patient.getPatientRegisteredDate());

                            if (resultsDetail[1] != null) {
                                addressInfo = (AddressInfo) resultsDetail[1];

                                if (addressInfo.getCity() != null) {
                                    campRegistrationDTO.setCity(addressInfo.getCity().getName());
                                }
                            }

                            //add to list
                            campRegistrationDTOs.add(campRegistrationDTO);
                        }
                        break;
                    case "all":
                        //retrieve objects
                        //results = patientRepository.getCampPatientByClinic(request.getClinicId());
                        //set the list of PatientDTO
                        for (Object[] resultsDetail : results) {
                            //create PatientDTO object
                            CampRegistrationDTO campRegistrationDTO = new CampRegistrationDTO();

                            patient = (Patient) resultsDetail[0];

                            //set patient details
                            campRegistrationDTO.setPatientId(patient.getId());
                            campRegistrationDTO.setClinicId(patient.getClinic().getId());
                            //campRegistrationDTO.setCampRegistrationNumber(patient.getCampRegistrationNumber());
                            campRegistrationDTO.setFullName(patient.getPatientName());
                            LocalDateTime now = LocalDateTime.now();
                            int age = Period.between(patient.getDateOfBirth(), now.toLocalDate()).getYears();
                            campRegistrationDTO.setAge(age);
                            campRegistrationDTO.setGender(patient.getGender());
                            campRegistrationDTO.setMobileNumber(patient.getMobileNumber());
                            campRegistrationDTO.setMrn(patient.getPatientIdExternal());
                            campRegistrationDTO.setPatientRegisteredDate(patient.getPatientRegisteredDate());

                            if (resultsDetail[1] != null) {
                                addressInfo = (AddressInfo) resultsDetail[1];

                                if (addressInfo.getCity() != null) {
                                    campRegistrationDTO.setCity(addressInfo.getCity().getName());
                                }
                            }

                            //add to list
                            campRegistrationDTOs.add(campRegistrationDTO);
                        }
                        break;
                    default:
                        log.debug("No Patient details Found");
                }
                response = new CampRegistrationSearchServiceResponse(campRegistrationDTOs);
                response.SUCCESSFUL = true;
                response.setMessage("Retrieved all Camp Registrations");
                log.debug("Retrieved all Camp Registrations");
            }
        } catch (Exception e) {
            response = new CampRegistrationSearchServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve all Camp Registrations");
            log.error("Failed to retrieve all Camp Registrations");
        }
        return response;
    }
}
