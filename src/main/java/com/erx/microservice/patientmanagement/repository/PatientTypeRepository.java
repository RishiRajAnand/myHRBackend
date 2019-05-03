package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.PatientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientTypeRepository extends JpaRepository<PatientType, Long> {

    @Query("select PT from PatientType PT where PT.patientTypeName = ?1 and PT.clinicLocationId =?2 ")
    PatientType findPatientTypeByPatient(String patientTypeName, Long clinicLocationId);

    @Query("select PT from PatientType PT where PT.patientTypeName = ?1")
    PatientType findByPatientTypeName(String patientType);

    @Query("select ptm from PatientType ptm where ptm.clinicLocationId = ?1 and ptm.recordStatus = 1 " +
            "and ptm.status = true")
    List<PatientType> getActivePatientTypesByClinicLocation(Long clinicLocationId);

    @Query("select ptm from PatientType ptm where ptm.clinicLocationId = ?1 and ptm.recordStatus = 1")
    List<PatientType> getPatientTypesByClinicLocation(Long clinicLocationId);

    @Query("select ptm from PatientType ptm where ptm.clinicLocationId = ?1 " +
            "and ptm.patientTypeName = ?2 and ptm.recordStatus = 1")
    PatientType validatePatientTypeName(Long clinicLocationId, String patientTypeName);
}

