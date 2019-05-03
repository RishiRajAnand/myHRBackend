package com.erx.microservice.patientmanagement.repository;

/*
* created by Latha on 03-11-17
* */

import com.erx.microservice.patientmanagement.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
