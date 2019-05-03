package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmMasterComplaintRepository  extends JpaRepository<CmMasterComplaint,Long> {

    @Query("select cmc from CmMasterComplaint cmc where cmc.cmMaster.id = ?1 and cmc.recordStatus = 1")
    List<CmMasterComplaint> getCmMasterComplaintByCmMaster(Long cmMasterId);
}
