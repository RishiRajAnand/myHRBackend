package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
created by Brighty on 19-06-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMasterReopenDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmMasterReopenDetailRepository extends JpaRepository<CmMasterReopenDetail,Long>{
}
