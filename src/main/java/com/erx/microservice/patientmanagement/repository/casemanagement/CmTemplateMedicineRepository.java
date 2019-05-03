package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplateMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmTemplateMedicineRepository extends JpaRepository<CmTemplateMedicine,Long>{

    @Query("select ctm from CmTemplateMedicine ctm where ctm.cmTemplate.id = ?1 and ctm.recordStatus = 1 ")
    List<CmTemplateMedicine> findCmTemplateMedicineByCmTemplate(Long cmTemplateId);
}
