package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
* created by Latha on 03-11-17
* */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("select c from Country c where c.name = ?1 and c.recordStatus = 1")
    Country findByName(String country);
}
