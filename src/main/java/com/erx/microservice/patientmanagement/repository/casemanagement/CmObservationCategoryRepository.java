package com.erx.microservice.patientmanagement.repository.casemanagement;

import com.erx.microservice.patientmanagement.domain.casemanagement.CmObservationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/*
* created by Latha on 20-08-2018
* */

@Repository
public interface CmObservationCategoryRepository extends JpaRepository<CmObservationCategory, Long> {

    @Query("select coc from CmObservationCategory coc where coc.lookupValue.id = ?1 and coc.recordStatus = 1")
    CmObservationCategory getCmObservationCategoryByLookupValueId(Long lookupValueId);
}
