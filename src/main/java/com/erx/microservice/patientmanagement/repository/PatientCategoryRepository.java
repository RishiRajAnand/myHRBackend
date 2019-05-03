package com.erx.microservice.patientmanagement.repository;


/*
* created by Latha on 03-11-17
* */

import com.erx.microservice.patientmanagement.domain.PatientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientCategoryRepository extends JpaRepository<PatientCategory, Long> {
    PatientCategory findByTypeName(String patientType);
}
