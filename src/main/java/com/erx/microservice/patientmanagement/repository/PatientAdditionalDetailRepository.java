package com.erx.microservice.patientmanagement.repository;
/*
* created by Latha on 06-03-18
* */

import com.erx.microservice.patientmanagement.domain.PatientAdditionalDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAdditionalDetailRepository extends JpaRepository<PatientAdditionalDetail, Long> {

    @Query("select pad from PatientAdditionalDetail pad where pad.patient.id = ?1 and pad.recordStatus = 1")
    PatientAdditionalDetail findPatientTypeByPatient(Long patientId);
}
