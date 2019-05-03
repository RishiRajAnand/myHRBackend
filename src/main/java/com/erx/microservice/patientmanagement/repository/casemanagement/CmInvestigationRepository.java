package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 17-09-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmInvestigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmInvestigationRepository extends JpaRepository<CmInvestigation,Long>{

    @Query("select cmi from CmInvestigation cmi where cmi.cmMasterDetails.id = ?1 " +
            "and cmi.recordStatus = 1")
    CmInvestigation findInvestigationByCmMasterDetailsId(Long cmMasterDetailId);
}
