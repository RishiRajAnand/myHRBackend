package com.erx.microservice.patientmanagement.repository;

/**
 * Created by brighty on 22-11-17.
 */

import com.erx.microservice.patientmanagement.domain.PatientRefund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRefundRepository extends JpaRepository<PatientRefund, Long> {

    @Query("select r from PatientRefund r where r.clinicLocation.id = ?1 and r.recordStatus = 1 " +
            "ORDER BY r.updatedOn DESC")
    List<PatientRefund> getRefundByClinicLocation(Long clinicLocationId);

    @Query("select r from PatientRefund r where r.recordStatus = 1 " +
            "and r.patient.id = ?1 and r.refundNumber like %?2% " +
            "or r.recordStatus = 1 and r.patient.id = ?1 and r.refundType.name like %?2% ORDER BY r.updatedOn DESC")
    List<PatientRefund> findByRefundNumberOrRefundType(Long patientId, String searchValue);

    @Query("select r from PatientRefund r where r.recordStatus = 1 and r.patient.id = ?1 and r.accountName.id = ?2   ORDER BY r.updatedOn DESC")
    List<PatientRefund> findPatientRefundByPatientIdAndVisitId(Long patientId, Long visitId);
}
