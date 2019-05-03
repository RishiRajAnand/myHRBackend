package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by Latha on 24-08-2018
* */

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cm_treatment")
public class CmTreatment extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cm_master_detail_id", nullable=false)
    @JsonIgnore
    private CmMasterDetails cmMasterDetails;

    @Column(name="pathya_pathya", nullable=true)
    private String pathyaPathya;

    @Column(name="take", nullable=true)
    private String take;

    @Column(name="take_more", nullable=true)
    private String takeMore;

    @Column(name="given_date", nullable=true)
    private LocalDateTime givenDate;

    @Column(name="shodhanam", nullable=true)
    private boolean shodhanam;

    @Column(name="shamanam", nullable=true)
    private boolean shamanam;

    @OneToMany(mappedBy = "cmTreatment",fetch = FetchType.LAZY)
    private List<CmTreatmentMedicineDetail> cmTreatmentMedicineDetails;

    public CmMasterDetails getCmMasterDetails() {
        return cmMasterDetails;
    }

    public void setCmMasterDetails(CmMasterDetails cmMasterDetails) {
        this.cmMasterDetails = cmMasterDetails;
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

    public List<CmTreatmentMedicineDetail> getCmTreatmentMedicineDetails() {
        return cmTreatmentMedicineDetails;
    }

    public void setCmTreatmentMedicineDetails(List<CmTreatmentMedicineDetail> cmTreatmentMedicineDetails) {
        this.cmTreatmentMedicineDetails = cmTreatmentMedicineDetails;
    }
}
