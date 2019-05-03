package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.Clinic;
import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
* created by Brighty on 09-11-2017
* */

@Repository
public interface ClinicLocationRepository extends JpaRepository<ClinicLocation, Long> {

    @Query("select c from ClinicLocation c where c.clinic = ?1")
    List<ClinicLocation> findAllClinicLocationByClinic(Clinic clinic);

    @Query("select c.clinic.id from ClinicLocation c where c.id = ?1")
    Long findClinicIdByClinicLocationId(Long clinicLocationId);

}
