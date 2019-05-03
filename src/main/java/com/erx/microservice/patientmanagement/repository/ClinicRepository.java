package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
* created by Brighty on 28-10-17
* */

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
