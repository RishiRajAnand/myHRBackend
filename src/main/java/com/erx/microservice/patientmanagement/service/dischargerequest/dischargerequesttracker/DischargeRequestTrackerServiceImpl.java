package com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.DischargeRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by latha on 06/12/18.
 */
@Service
public class DischargeRequestTrackerServiceImpl implements DischargeRequestTrackerService {

    private final Logger log = LoggerFactory.getLogger(DischargeRequestTrackerServiceImpl.class);

    @Autowired
    DischargeRequestTrackerFactory dischargeRequestTrackerFactory;


    @Override
    public DischargeRequestTrackerResponse execute(DischargeRequestTrackerRequest request) throws Exception {

        DischargeRequestTrackerResponse response = new DischargeRequestTrackerResponse();

        try {
            log.debug("Get PatientSearch Results");

            //retrieve PatientSearchCriteria
            PatientSearchCriteria patientSearchCriteria = request.getPatientSearchCriteria();

            List<DischargeRequestDTO> dischargeRequestDTOs = dischargeRequestTrackerFactory
                    .dischargeRequestSearch(patientSearchCriteria, request.getPageable());

            response.setDischargeRequestDTOs(dischargeRequestDTOs);

            response.SUCCESSFUL = true;
            response.setMessage("Found DischargeRequests Successfully");
            log.debug("Found DischargeRequests Successfully");
        } catch (Exception e) {
            response = new DischargeRequestTrackerResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to find DischargeRequests " + e.getMessage());
            log.error("Failed to find DischargeRequests");
        }
        return response;
    }
}
