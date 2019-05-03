package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 17-09-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmInvestigationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmInvestigationDetailRepository extends JpaRepository<CmInvestigationDetail, Long>{

    @Query("select cmid from CmInvestigationDetail cmid where cmid.cmInvestigation.id = ?1 " +
            "and cmid.recordStatus = 1")
    List<CmInvestigationDetail> findByCmInvestigationId(Long cmInvestigationId);
}
