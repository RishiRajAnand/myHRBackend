package com.erx.microservice.patientmanagement.service.patientsearch;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Latha on 08/01/18.
 */

@Service
public class CampPatientSearchServiceImpl implements CampPatientSearchService {

    private final Logger log = LoggerFactory.getLogger(CampPatientSearchServiceImpl.class);

    @Autowired
    PatientSearchFactory patientSearchFactory;

    @Override
    public PatientSearchServiceResponse execute(PatientSearchServiceRequest request) throws Exception {
        PatientSearchServiceResponse response = new PatientSearchServiceResponse();

        try {
            log.debug("Get CampPatientSearch Results");

            //Create PatientSearchFactoryImpl


            PatientSearchCriteria patientSearchCriteria = request.getPatientSearchCriteria();

            //Get PatientSearchQueryBuilder based on type
            response.setCampRegistrationDTOList(patientSearchFactory.getCampPatientSearch(patientSearchCriteria));

            response.SUCCESSFUL = true;
            response.setMessage("Found Camp Patients Successfully");
            log.debug("Found Camp Patients Successfully");
        } catch (Exception e) {
            response = new PatientSearchServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to find Camp Patients " + e.getMessage());
            log.error("Failed to find Camp Patients");
        }

        return response;
    }
}
