package com.erx.microservice.patientmanagement.repository;

/*
* created by Latha on 03-11-17
* */

import com.erx.microservice.patientmanagement.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where c.name = ?1 and c.recordStatus = 1")
    City findByCityName(String city);
}
