package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 25-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmTreatmentRepository extends JpaRepository<CmTreatment,Long>{

    @Query("select cmt from CmTreatment cmt " +
            "inner join cmt.cmMasterDetails cmd with cmd.cmMaster.id in ?1 " +
            "where cmd.cmMaster.id = ?1 " +
            "and cmt.recordStatus = 1")
    List<CmTreatment> findTreatmentByCmMasterId(Long caseId);

    @Query("select cmt from CmTreatment cmt where cmt.cmMasterDetails.id = ?1 " +
            "and cmt.recordStatus = 1")
    List<CmTreatment> findTreatmentByCmMasterDetailsId(Long cmMasterDetailsId);

}
