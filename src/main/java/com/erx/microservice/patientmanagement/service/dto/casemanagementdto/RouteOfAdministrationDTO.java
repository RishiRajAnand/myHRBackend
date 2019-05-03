package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 20-08-2018
* */

public class RouteOfAdministrationDTO {

    private Long routeOfAdministrationId;
    private String routeOfAdministrationName;

    public Long getRouteOfAdministrationId() {
        return routeOfAdministrationId;
    }

    public void setRouteOfAdministrationId(Long routeOfAdministrationId) {
        this.routeOfAdministrationId = routeOfAdministrationId;
    }

    public String getRouteOfAdministrationName() {
        return routeOfAdministrationName;
    }

    public void setRouteOfAdministrationName(String routeOfAdministrationName) {
        this.routeOfAdministrationName = routeOfAdministrationName;
    }
}
