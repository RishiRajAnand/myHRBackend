package com.erx.microservice.patientmanagement.service.dto.casemanagementdto;

/*
* created by Latha on 02-09-2018
* */

import java.time.LocalDateTime;

public class CmPathyaPathyaDTO {

    private Long cmTreatmentId;
    private Long cmMasterDetailsId;
    private String pathyaPathya;
    private String take;
    private String takeMore;
    private LocalDateTime givenDate;
    private boolean shodhanam;
    private boolean shamanam;

    public Long getCmTreatmentId() {
        return cmTreatmentId;
    }

    public void setCmTreatmentId(Long cmTreatmentId) {
        this.cmTreatmentId = cmTreatmentId;
    }

    public Long getCmMasterDetailsId() {
        return cmMasterDetailsId;
    }

    public void setCmMasterDetailsId(Long cmMasterDetailsId) {
        this.cmMasterDetailsId = cmMasterDetailsId;
    }

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

    public boolean isShodhanam() {
        return shodhanam;
    }

    public void setShodhanam(boolean shodhanam) {
        this.shodhanam = shodhanam;
    }

    public boolean isShamanam() {
        return shamanam;
    }

    public void setShamanam(boolean shamanam) {
        this.shamanam = shamanam;
    }
}
