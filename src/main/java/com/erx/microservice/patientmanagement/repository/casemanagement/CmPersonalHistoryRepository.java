package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmPersonalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmPersonalHistoryRepository extends JpaRepository<CmPersonalHistory,Long>{

    @Query("select cmp from CmPersonalHistory cmp where cmp.cmMasterDetails.id = ?1 and cmp.recordStatus = 1")
    List<CmPersonalHistory> getCmPersonalHistoryByCmMasterDetailId(Long cmMasterDetailsId);
}
