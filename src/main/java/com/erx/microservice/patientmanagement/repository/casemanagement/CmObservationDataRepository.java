package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CmObservationDataRepository extends JpaRepository<CmObservationData, Long> {

    @Query("select cod from CmObservationData cod where cod.dataName = ?1 and cod.recordStatus = 1")
    CmObservationData getCmObservationDataByUserInput(String dataName);
}
