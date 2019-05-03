package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 27-03-2018
* */

import com.erx.microservice.patientmanagement.domain.PatientUhIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientUhIdentifierRepository extends JpaRepository<PatientUhIdentifier, Long> {
}
