package com.erx.microservice.patientmanagement.service.patientsearch.patientcasesheet;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchFactory;
import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceRequest;
import com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Latha on 16/08/18.
 */

@Service
public class PatientCaseCountSearchServiceImpl implements PatientCaseCountSearchService {

    private final Logger log = LoggerFactory.getLogger(PatientCaseCountSearchServiceImpl.class);

    @Autowired
    PatientSearchFactory patientSearchFactory;

    @Override
    public PatientSearchServiceResponse execute(PatientSearchServiceRequest request) throws Exception {
        PatientSearchServiceResponse response = new PatientSearchServiceResponse();

        try {
            log.debug("Get PatientCaseCountSearch Results");

            //Create PatientSearchFactoryImpl and Get PatientSearchQueryBuilder based on type
            response.setPatientCaseCountSearchDTOList(patientSearchFactory.getPatientCaseCountSearch(request));

            response.SUCCESSFUL = true;
            response.setMessage("Found patients with case count Successfully");
            log.debug("Found patients with case count Successfully");
        } catch (Exception e) {
            response = new PatientSearchServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to find patient case count " + e.getMessage());
            log.error("Failed to find patient case count");
        }
        return response;
    }
}
