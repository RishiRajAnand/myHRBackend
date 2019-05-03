package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 16-10-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmCaseTransferHistory;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CmCaseTransferHistoryRepository extends JpaRepository<CmCaseTransferHistory,Long>{

    @Query("select distinct cm from CmCaseTransferHistory cmct " +
            "inner join cmct.cmMaster cm " +
            "inner join cm.clinicLocation cl " +
            "inner join cm.patient p " +
            "where cl.id = ?1 and p.id = ?2 " +
            "and cmct.caseTransferredTo.id = ?3 and " +
            "cmct.referralTypeLookupValId = ?4 ")

    Page<CmMaster> getAllPatientCases(Long clinicLocationId, Long patientId, Long id, Long internalReferralTypeLookupValId, Pageable pageable);
}
