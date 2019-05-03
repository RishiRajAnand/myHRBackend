package com.erx.microservice.patientmanagement.domain.casemanagement;

/*
 * created by Latha on 06-10-2018
 * */

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cm_exam_type")
public class CmExamntnType extends BaseModel{

    @Column(name="type_name", nullable=false)
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
