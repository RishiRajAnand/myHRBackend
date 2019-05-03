package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.Lookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookupRepository extends JpaRepository<Lookup, Long> {
}
