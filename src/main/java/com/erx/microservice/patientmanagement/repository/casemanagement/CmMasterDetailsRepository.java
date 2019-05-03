package com.erx.microservice.patientmanagement.repository.casemanagement;

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
created by Latha on 20-08-2018
* */

@Repository
public interface CmMasterDetailsRepository extends JpaRepository<CmMasterDetails,Long> {

    @Query("select cmd from CmMasterDetails cmd where cmd.cmMaster.id = ?1 and cmd.recordStatus = 1")
    List<CmMasterDetails> getCmMasterDetailsByCmMaster(Long cmMasterId);

    @Query("select cmd.cmMaster.id from CmMasterDetails cmd where cmd.bmOrderId = ?1 and cmd.recordStatus = 1")
    Long getCmMasterIdByOrderId(Long orderId);

    @Query("select cmd.bmOrderId from CmMasterDetails cmd Join cmd.cmMaster cm where cm.id = ?1 and cmd.recordStatus = 1")
    Long getOrderIdByCaseId(Long caseId);


}
