package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 27-03-2018
* */

import com.erx.microservice.patientmanagement.domain.UhIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UhIdentifierRepository extends JpaRepository<UhIdentifier, Long> {

    @Query("select u from UhIdentifier u where u.name = ?1 and u.recordStatus = 1")
    UhIdentifier findByIdentificationType(String universalIdentificationType);
}
