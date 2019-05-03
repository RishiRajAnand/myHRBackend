package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplateGroupMedicineDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmTemplateGroupMedicineDetailRepository extends JpaRepository<CmTemplateGroupMedicineDetail,Long>{

    @Query("select ctgm from CmTemplateGroupMedicineDetail ctgm where ctgm.cmTemplateMedicine.id = ?1 and ctgm.recordStatus = 1 ")
    List<CmTemplateGroupMedicineDetail> findCmTemplateGroupMedicinesById(Long cmTemplateMedicineId);
}
