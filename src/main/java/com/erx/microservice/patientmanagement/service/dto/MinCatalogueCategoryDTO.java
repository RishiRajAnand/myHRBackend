/*
 * @Copyright © 2017. eRx Solutions Pvt Ltd
 * @author john@erxindia.in
 * @project eRx
 * @version eRx v2.0
 * @module billing-microservice
 * @createdon 16-Nov-2017
 */
package com.erx.microservice.patientmanagement.service.dto;

import com.erx.microservice.patientmanagement.domain.ClinicLocation;

public class MinCatalogueCategoryDTO extends BaseModelDTO {
    private String categoryName;
    private ClinicLocation clinicLocation;

    //Getters and Setters

    public ClinicLocation getClinicLocation ( ) {
        return clinicLocation;
    }

    public void setClinicLocation (ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
    }

    public String getCategoryName ( ) {
        return categoryName;
    }

    public void setCategoryName (String categoryName) {
        this.categoryName = categoryName;
    }
}
