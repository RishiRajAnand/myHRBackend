package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
 * created by Latha on 06-10-2018
 * */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmExamntnDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmExamntnDetailRepository extends JpaRepository<CmExamntnDetail,Long>{

    @Query("select ced from CmExamntnDetail ced where ced.cmMasterDetails.id = ?1 and ced.recordStatus = 1")
    List<CmExamntnDetail> getExamDetailsByCmMasterDetails(Long cmMasterDetailId);
}
