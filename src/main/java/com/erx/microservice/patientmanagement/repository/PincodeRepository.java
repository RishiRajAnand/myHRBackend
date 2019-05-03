package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.Pincode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
* created by Latha on 03-11-17
* */
@Repository
public interface PincodeRepository extends JpaRepository<Pincode, Long> {

    @Query("select p from Pincode p where p.pin = ?1 and p.recordStatus = 1")
    Pincode findByCode(String postalCode);
}
