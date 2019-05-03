package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 10-10-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmNextVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmNextVisitRepository extends JpaRepository<CmNextVisit,Long>{
}
