package com.erx.microservice.patientmanagement.repository.casemanagement;

import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.casemanagement.ClinicPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicPreferenceRepository extends JpaRepository<ClinicPreference,Long> {

    @Query("select CP.paymentPolicy from ClinicPreference CP where CP.clinic.id = ?1 " +
            "and CP.recordStatus = 1")
    String getPaymentPolicyByClinicId (Long clinicId);

    @Query("select CP.commonHoliday from ClinicPreference CP where CP.clinic.id = ?1 " +
            "and CP.recordStatus = 1")
    String getCommonHoliday(Long clinicId);

    @Query("select CP from ClinicPreference CP where CP.clinic.id = ?1 " +
            "and CP.recordStatus = 1")
    ClinicPreference findByClinicId(Long clinicId);

    @Query("select CP from ClinicPreference CP where CP.clinic.id = ?1 " +
            "and CP.recordStatus = 1")
    ClinicPreference getClinicPreferenceByClinic(Long clinicId);

}
