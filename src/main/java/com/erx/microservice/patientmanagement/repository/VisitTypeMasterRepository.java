package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 08-01-2018
* */

import com.erx.microservice.patientmanagement.domain.VisitTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitTypeMasterRepository extends JpaRepository<VisitTypeMaster, Long> {
    @Query("select vtm from VisitTypeMaster vtm  where vtm.id =?1 ")
    VisitTypeMaster findByAccountNameId(Long accountNameId);

    @Query("select vtm from VisitTypeMaster vtm where (vtm.clinicLocationId = 0 or vtm.clinicLocationId = ?1) and vtm.recordStatus = 1")
    List<VisitTypeMaster> getVisitTypeMasterByClinicLocation(Long clinicLocationId);
}
