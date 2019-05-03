package com.erx.microservice.patientmanagement.repository.casemanagement;

import com.erx.microservice.patientmanagement.domain.casemanagement.CmDischargeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Latha on 06/10/18.
 */

@Repository
public interface CmDischargeRequestRepository extends JpaRepository<CmDischargeRequest,Long>{

    @Query("select cdr from CmDischargeRequest cdr where cdr.cmMaster.id = ?1 and cdr.recordStatus = 1")
    CmDischargeRequest findDischargeDetailsByCaseId(Long caseId);
}
