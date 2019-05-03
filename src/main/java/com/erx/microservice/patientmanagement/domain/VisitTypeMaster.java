package com.erx.microservice.patientmanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by latha on 26/12/17.
 */

@Entity
@Table(name = "visit_type_master")
public class VisitTypeMaster extends BaseModel {

    @Column(name = "visit_type", nullable = true)
    private String visitType;

    @Column(name = "clinic_location_id", nullable = true)
    private Long clinicLocationId;

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public Long getClinicLocationId() {
        return clinicLocationId;
    }

    public void setClinicLocationId(Long clinicLocationId) {
        this.clinicLocationId = clinicLocationId;
    }
}
