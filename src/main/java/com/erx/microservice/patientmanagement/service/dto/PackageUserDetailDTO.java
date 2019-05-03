package com.erx.microservice.patientmanagement.service.dto;
/**
 *  Created by Raushan on 19/12/17.
 */
public class PackageUserDetailDTO {
    private Long id;
    private Long UserDetailId;
    private String doctorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserDetailId() {
        return UserDetailId;
    }

    public void setUserDetailId(Long userDetailId) {
        UserDetailId = userDetailId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
