/*
 * @Copyright © 2018. eRx Solutions Pvt Ltd
 * @author john@erxindia.in
 * @project eRx
 * @version eRx v2.0
 * @module billing-microservice
 * @createdon 11-Jan-2018
 */
package com.erx.microservice.patientmanagement.service.dto;

public class IdNameDTO  implements Comparable<IdNameDTO>{

    private Long id;
    private String name;

    public Long getId ( ) {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName ( ) {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Override
    public int compareTo(IdNameDTO idNameDTO) {
        return Long.compare(idNameDTO.getId(), this.getId());
    }
}
