package com.erx.microservice.patientmanagement.repository;

/*
* created by Latha on 03-11-17
* */

import com.erx.microservice.patientmanagement.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query("select s from State s where s.name = ?1 and s.recordStatus = 1")
    State findByName(String state);
}
