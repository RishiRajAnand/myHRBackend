package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 26-03-2018
* */

import com.erx.microservice.patientmanagement.domain.PatientCampRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientCampRegistrationRepository extends JpaRepository<PatientCampRegistration, Long> {

    @Query("select c from PatientCampRegistration c where c.patient.id = ?1 " +
            "and c.packageCatalogueId = ?2 and c.recordStatus = 1")
    List<PatientCampRegistration> findByPatientAndPackage(Long patientId, Long packageCatalogueId);

    @Query("select c.campRegistrationNumber from PatientCampRegistration c where c.id = ?1")
    String findCampRegistrationNumberById(Long patientCampRegistrationId);
}
