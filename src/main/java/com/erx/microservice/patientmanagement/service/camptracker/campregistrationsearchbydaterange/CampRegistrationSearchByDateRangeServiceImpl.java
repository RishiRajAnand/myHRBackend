package com.erx.microservice.patientmanagement.service.camptracker.campregistrationsearchbydaterange;

/*
* created by Brighty on 08-12-17
* */

import com.erx.microservice.patientmanagement.domain.AddressInfo;
import com.erx.microservice.patientmanagement.domain.Patient;
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

@Service("campRegistrationSearchByDateRangeService")
public class CampRegistrationSearchByDateRangeServiceImpl implements CampRegistrationSearchByDateRangeService {

    private final Logger log = LoggerFactory.getLogger(CampRegistrationSearchByDateRangeServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public CampRegistrationSearchByDateRangeServiceResponse execute(CampRegistrationSearchByDateRangeServiceRequest request) throws ServiceException {

        CampRegistrationSearchByDateRangeServiceResponse response = null;
        List<Object[]> results = null;
        Patient patient = null;
        AddressInfo addressInfo = null;
        List<CampRegistrationDTO> campRegistrationDTOs = new ArrayList<>();
        try {
            log.debug("Call to search Camp Registration by Date Range");
            if (request.getClinicId() != null & request.getStartDate() != null & request.getEndDate() != null) {
                //retrieve the dates
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");
                LocalDateTime startDate = LocalDateTime.parse(request.getStartDate());
                LocalDateTime endDate = LocalDateTime.parse(request.getEndDate());
                //retrieve objects
                //results = patientRepository.getCampPatientByDateRange(request.getClinicId(), startDate, endDate);
                //set the list of PatientDTO
                for (Object[] resultsDetail : results) {
                    //create PatientDTO object
                    CampRegistrationDTO campRegistrationDTO = new CampRegistrationDTO();
                    //retrieve patient
                    patient = (Patient) resultsDetail[0];

                    //set patient details
                    campRegistrationDTO.setPatientId(patient.getId());
                    //campRegistrationDTO.setCampRegistrationNumber(patient.getCampRegistrationNumber());
                    campRegistrationDTO.setFullName(patient.getPatientName());
                    LocalDateTime now = LocalDateTime.now();
                    int age = Period.between(patient.getDateOfBirth(), now.toLocalDate()).getYears();
                    campRegistrationDTO.setAge(age);
                    campRegistrationDTO.setGender(patient.getGender());
                    campRegistrationDTO.setMobileNumber(patient.getMobileNumber());
                    campRegistrationDTO.setMrn(patient.getPatientIdExternal());
                    campRegistrationDTO.setPatientRegisteredDate(patient.getPatientRegisteredDate());

                    //retrieve addressInfo
                    if (resultsDetail[1] != null) {
                        addressInfo = (AddressInfo) resultsDetail[1];

                        if (addressInfo.getCity() != null) {
                            campRegistrationDTO.setCity(addressInfo.getCity().getName());
                        }
                    }

                    //add to list
                    campRegistrationDTOs.add(campRegistrationDTO);
                }
                response = new CampRegistrationSearchByDateRangeServiceResponse(campRegistrationDTOs);
                response.SUCCESSFUL = true;
                response.setMessage("Retrieved Camp Registrations Based on Date Range Successfully");
                log.debug("Retrieved Camp Registrations Based on Date Range Successfully");
            }
        } catch (Exception e) {
            response = new CampRegistrationSearchByDateRangeServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Camp Registrations Based on Date Range");
            log.error("Failed to retrieve Camp Registrations Based on Date Range");
        }
        return response;
    }
}
