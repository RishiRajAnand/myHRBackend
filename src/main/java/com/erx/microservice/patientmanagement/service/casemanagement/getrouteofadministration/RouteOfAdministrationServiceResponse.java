package com.erx.microservice.patientmanagement.service.casemanagement.getrouteofadministration;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceResponse;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.RouteOfAdministrationDTO;

import java.util.List;

public class RouteOfAdministrationServiceResponse extends CommonServiceResponse {

   private List<RouteOfAdministrationDTO> routeOfAdministrationDTOs;

    //constructor

    public RouteOfAdministrationServiceResponse(List<RouteOfAdministrationDTO> routeOfAdministrationDTOs) {
        this.routeOfAdministrationDTOs = routeOfAdministrationDTOs;
    }

    public RouteOfAdministrationServiceResponse() {
    }

    //getters and setters

    public List<RouteOfAdministrationDTO> getRouteOfAdministrationDTOs() {
        return routeOfAdministrationDTOs;
    }

    public void setRouteOfAdministrationDTOs(List<RouteOfAdministrationDTO> routeOfAdministrationDTOs) {
        this.routeOfAdministrationDTOs = routeOfAdministrationDTOs;
    }
}
