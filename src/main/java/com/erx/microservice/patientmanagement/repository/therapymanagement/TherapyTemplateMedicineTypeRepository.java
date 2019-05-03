package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 12-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyTemplateMedicineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyTemplateMedicineTypeRepository extends JpaRepository<TherapyTemplateMedicineType,Long>{

    @Query("select ttmt from TherapyTemplateMedicineType ttmt where ttmt.therapyTemplateMapping.id = ?1 and ttmt.recordStatus = 1")
    List<TherapyTemplateMedicineType> findByTherapyTemplateMappingId(Long id);
}
