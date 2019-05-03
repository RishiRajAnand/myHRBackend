package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyTemplateMedicineRepository extends JpaRepository<TherapyTemplateMedicine,Long>{

    @Query("select ttme from TherapyTemplateMedicine ttme where ttme.therapyTemplateMapping.id = ?1 and ttme.recordStatus = 1")
    List<TherapyTemplateMedicine> findByTherapyTemplateMappingId(Long therapyTemplateMappingId);
}
