package com.erx.microservice.patientmanagement.service.importcsvfile;

/*
* created by Brighty on 26-03-2018
* */

import com.erx.microservice.patientmanagement.service.CommonServiceRequest;
import org.springframework.web.multipart.MultipartFile;

public class ImportCampCSVFileServiceRequest implements CommonServiceRequest {

    private Long clinicLocationId;
    private Long clinicId;
    private MultipartFile  multipartFile;
    private  Long userId;

    //Constructor

    public ImportCampCSVFileServiceRequest() {
    }

    public ImportCampCSVFileServiceRequest(Long clinicLocationId, Long clinicId, MultipartFile multipartFile, Long userId) {
        this.clinicLocationId = clinicLocationId;
        this.clinicId = clinicId;
        this.multipartFile = multipartFile;
        this.userId = userId;
    }

    //getter and setter

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}