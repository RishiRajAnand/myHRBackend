package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.Vital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Latha on 17/08/2018.
 */

@Repository
public interface VitalRepository extends JpaRepository<Vital, Long> {

    @Query("select v from Vital v where v.patient.id = ?1 and v.recordStatus = 1")
    List<Vital> getVitalDetailsByPatientId(Long patientId);

    @Query("select v from Vital v where v.patient.id = ?1 and v.bmi = ?2 and v.bp = ?3 and v.head = ?4 and v.height = ?5 and v.pulse = ?6 and v.temperature = ?7 and v.weight = ?8 and v.recordStatus = 1")
    Vital findVitalByVitalData(Long patientId, String bmi, String bp, String head, String height, String pulse, String temperature, String weight);
}
