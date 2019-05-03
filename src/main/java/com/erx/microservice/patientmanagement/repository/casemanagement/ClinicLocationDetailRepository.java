package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
created by Latha on 20-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.ClinicLocationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicLocationDetailRepository extends JpaRepository<ClinicLocationDetail,Long>{

    @Query("select cld from ClinicLocationDetail cld where cld.clinicLocation.id = ?1 and cld.recordStatus = 1")
    ClinicLocationDetail getClinicLocationDetail(Long clinicLocationId);
}
