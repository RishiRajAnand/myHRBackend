package com.erx.microservice.patientmanagement.service.patientsearch;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mkpatil on 28/12/17.
 */
@Service
public class NewPatientSearchServiceImpl implements NewPatientSearchService {

    private final Logger log = LoggerFactory.getLogger(NewPatientSearchServiceImpl.class);

    @Autowired
    PatientSearchFactory patientSearchFactory;


    @Override
    public PatientSearchServiceResponse execute(PatientSearchServiceRequest request) throws Exception {

        PatientSearchServiceResponse response = new PatientSearchServiceResponse();

        try {
            log.debug("Get PatientSearch Results");

            //Create PatientSearchFactoryImpl


            PatientSearchCriteria patientSearchCriteria = request.getPatientSearchCriteria();

            //Get PatientSearchQueryBuilder based on type
            // PatientSearchQueryBuilder patientSearchQueryBuilder = patientSearchFactory.getPatientSearch(patientSearchCriteria.getSearchType());

            response.setPatientSearchDTOList(patientSearchFactory.getPatientSearch(patientSearchCriteria));

            response.SUCCESSFUL = true;
            response.setMessage("Found Patients Successfully");
            log.debug("Found Patients Successfully");
        } catch (Exception e) {
            response = new PatientSearchServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to find Patients " + e.getMessage());
            log.error("Failed to find Patients");
        }

        return response;
    }
}
