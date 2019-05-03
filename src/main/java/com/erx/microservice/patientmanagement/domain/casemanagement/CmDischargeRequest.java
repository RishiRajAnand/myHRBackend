package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
* created by latha on 06-10-2018
* */


import com.erx.microservice.patientmanagement.domain.BaseModel;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cm_discharge_request")
public class CmDischargeRequest extends BaseModel{

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cm_master_id")
    private CmMaster cmMaster;

    @NotNull
    @Column(name = "discharge_request_number")
    private String dischargeRequestNumber;

    // @NotNull
    @Column(name = "discharge_request_date")
    private LocalDate dischargeRequestDate;

   // @NotNull
    @Column(name = "discharge_request_time")
    private LocalTime dischargeRequestTime;

    public CmMaster getCmMaster() {
        return cmMaster;
    }

    public void setCmMaster(CmMaster cmMaster) {
        this.cmMaster = cmMaster;
    }

    public String getDischargeRequestNumber() {
        return dischargeRequestNumber;
    }

    public void setDischargeRequestNumber(String dischargeRequestNumber) {
        this.dischargeRequestNumber = dischargeRequestNumber;
    }

    public LocalDate getDischargeRequestDate() {
        return dischargeRequestDate;
    }

    public void setDischargeRequestDate(LocalDate dischargeRequestDate) {
        this.dischargeRequestDate = dischargeRequestDate;
    }

    public LocalTime getDischargeRequestTime() {
        return dischargeRequestTime;
    }

    public void setDischargeRequestTime(LocalTime dischargeRequestTime) {
        this.dischargeRequestTime = dischargeRequestTime;
    }
}
