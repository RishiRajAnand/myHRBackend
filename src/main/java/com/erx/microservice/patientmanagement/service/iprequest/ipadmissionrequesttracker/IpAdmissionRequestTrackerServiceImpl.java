package com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mkpatil on 28/12/17.
 */
@Service
public class IpAdmissionRequestTrackerServiceImpl implements IpAdmissionRequestTrackerService {

    private final Logger log = LoggerFactory.getLogger(IpAdmissionRequestTrackerServiceImpl.class);

    @Autowired
    IpAdmissionRequestTrackerFactory ipAdmissionRequestTrackerFactory;


    @Override
    public IpAdmissionRequestTrackerResponse execute(IpAdmissionRequestTrackerRequest request) throws Exception {

        IpAdmissionRequestTrackerResponse response = new IpAdmissionRequestTrackerResponse();

        try {
            log.debug("Get PatientSearch Results");

            //retrieve PatientSearchCriteria
            PatientSearchCriteria patientSearchCriteria = request.getPatientSearchCriteria();

            List<IpAdmissionRequestDTO> ipAdmissionRequestDTOs = ipAdmissionRequestTrackerFactory
                    .IpRequestSearch(patientSearchCriteria, request.getPageable());

            response.setIpAdmissionRequestDTOs(ipAdmissionRequestDTOs);

            response.SUCCESSFUL = true;
            response.setMessage("Found IpAdmissionRequests Successfully");
            log.debug("Found IpAdmissionRequests Successfully");
        } catch (Exception e) {
            response = new IpAdmissionRequestTrackerResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to find IpAdmissionRequests " + e.getMessage());
            log.error("Failed to find IpAdmissionRequests");
        }

        return response;
    }
}
