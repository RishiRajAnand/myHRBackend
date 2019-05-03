package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 25-08-2018
* */

import java.time.LocalDateTime;

public class PathyaPathyaDTO {

    private String pathyaPathya;
    private String take;
    private String takeMore;
    private LocalDateTime givenDate;

    public String getPathyaPathya() {
        return pathyaPathya;
    }

    public void setPathyaPathya(String pathyaPathya) {
        this.pathyaPathya = pathyaPathya;
    }

    public String getTake() {
        return take;
    }

    public void setTake(String take) {
        this.take = take;
    }

    public String getTakeMore() {
        return takeMore;
    }

    public void setTakeMore(String takeMore) {
        this.takeMore = takeMore;
    }

    public LocalDateTime getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(LocalDateTime givenDate) {
        this.givenDate = givenDate;
    }
}
