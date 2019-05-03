package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyTemplateRepository extends JpaRepository<TherapyTemplate,Long>{

    @Query("select tt from TherapyTemplate tt where tt.clinic.id = ?1 and tt.recordStatus = 1")
    List<TherapyTemplate> findTemplateByClinic(Long clinicId);

    @Query("select tt from TherapyTemplate tt where tt.name = ?1 and tt.clinic.id = ?2 and tt.recordStatus = 1")
    TherapyTemplate findCmTemplateByName(String name, Long clinicId);
}
