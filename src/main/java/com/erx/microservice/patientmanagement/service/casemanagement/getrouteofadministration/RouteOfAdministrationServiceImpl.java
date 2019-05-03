package com.erx.microservice.patientmanagement.service.casemanagement.getrouteofadministration;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.RouteOfAdministration;
import com.erx.microservice.patientmanagement.repository.casemanagement.RouteOfAdministrationRepository;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.RouteOfAdministrationDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("routeOfAdministrationService")
public class RouteOfAdministrationServiceImpl implements RouteOfAdministrationService {

    private final Logger log = LoggerFactory.getLogger(RouteOfAdministrationServiceImpl.class);

    @Autowired
    private RouteOfAdministrationRepository routeOfAdministrationRepository;

    @Override
    public RouteOfAdministrationServiceResponse execute() throws ServiceException {

        List<RouteOfAdministrationDTO> routeOfAdministrationDTOs = new ArrayList<>();
        List<RouteOfAdministration> routeOfAdministrations = null;
        RouteOfAdministrationServiceResponse response = null;
        try {
            log.debug("Call to get route of administrations");
            // retrieve route of administration
            routeOfAdministrations = routeOfAdministrationRepository.findAll();
            if(routeOfAdministrations != null)
                for(RouteOfAdministration routeOfAdministration : routeOfAdministrations) {
                    RouteOfAdministrationDTO routeOfAdministrationDTO = new RouteOfAdministrationDTO();
                    routeOfAdministrationDTO.setRouteOfAdministrationId(routeOfAdministration.getId());
                    routeOfAdministrationDTO.setRouteOfAdministrationName(routeOfAdministration.getName());
                    routeOfAdministrationDTOs.add(routeOfAdministrationDTO);
                }
            // setting the dto to response
            response = new RouteOfAdministrationServiceResponse(routeOfAdministrationDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved route of administration details Successfully");
            log.debug("Retrieved route of administration details Successfully");
        } catch (Exception e) {
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve route of administration details");
            log.error("Failed to retrieve route of administration details");
        }

        return response;
    }
}
