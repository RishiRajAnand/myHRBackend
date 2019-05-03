package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmTemplateRepository extends JpaRepository<CmTemplate,Long>{

    @Query("select ct from CmTemplate ct where ct.clinic.id = ?1 and ct.scienceOfMedicineId = ?2 and ct.recordStatus = 1")
    List<CmTemplate> findCmTemplateByClinicAndScienceOfMedicine(Long clinicId, Long scienceOfMedicineId);

    @Query("select ct from CmTemplate ct where ct.name = ?1 and ct.clinic.id = ?2 and ct.recordStatus = 1")
    CmTemplate findCmTemplateByName(String name, Long clinicId);
}
