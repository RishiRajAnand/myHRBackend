package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 26-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmGroupMedicineMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CmGroupMedicineMasterRepository extends JpaRepository<CmGroupMedicineMaster,Long>{

    @Query("SELECT distinct cgm from CmGroupMedicineMaster cgm where  cgm.medicineName = ?1 and cgm.clinic.id=?2")
    CmGroupMedicineMaster findByGroupMedicineName(String medicineName, Long clinicId);

    @Query("select cgm from CmGroupMedicineMaster cgm where cgm.clinic.id=?1 and cgm.recordStatus = 1")
    Page<CmGroupMedicineMaster> findByGroupMedicineClinic(Long clinicId, Pageable pageable);
}
