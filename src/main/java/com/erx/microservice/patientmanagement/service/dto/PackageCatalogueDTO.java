/*
 * @Copyright © 2017. eRx Solutions Pvt Ltd
 * @author john@erxindia.in
 * @project eRx
 * @version eRx v2.0
 * @module billing-microservice
 * @createdon 22-Nov-2017
 */
package com.erx.microservice.patientmanagement.service.dto;

import java.time.LocalDateTime;
import java.util.Set;

/**
 *  Created by Raushan on 19/12/17.
 */
public class PackageCatalogueDTO {

    private Long id;
    private String packageName;
    private String packageCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

}
