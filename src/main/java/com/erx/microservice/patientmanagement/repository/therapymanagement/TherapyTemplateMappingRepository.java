package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyTemplateMappingRepository extends JpaRepository<TherapyTemplateMapping,Long>{

    @Query("select ttm from TherapyTemplateMapping ttm where ttm.therapyTemplate.id = ?1 and ttm.recordStatus = 1")
    List<TherapyTemplateMapping> findByTherapyTemplateId(Long therapyTemplateId);
}
