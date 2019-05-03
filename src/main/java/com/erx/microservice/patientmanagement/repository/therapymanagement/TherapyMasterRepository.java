package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 04-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyMasterRepository extends JpaRepository<TherapyMaster,Long>{

    @Query("select tm from TherapyMaster tm where tm.clinic.id=?1 and tm.recordStatus = 1")
    Page<TherapyMaster> findByClinic(Long clinicId, Pageable pageable);

    @Query("select tm from TherapyMaster tm where tm.serviceCatalogueId=?1 and tm.clinic.id=?2 and tm.recordStatus = 1")
    TherapyMaster findByClinicAndService(Long serviceCatalogueId, Long clinicId);

    @Query("select tm from TherapyMaster tm where tm.clinic.id=?1 and tm.id=?2 and tm.recordStatus = 1")
    TherapyMaster findByClinicAndId(Long clinicId, Long therapyMasterId);

    @Query("select tm from TherapyMaster tm where tm.clinic.id=?1 and tm.recordStatus = 1")
    List<TherapyMaster> findTherapiesByClinic(Long clinicId);

    @Query("select tm from TherapyMaster tm  left join Fetch tm.therapyMasterTherapistDetails tmt " +
            "where tm.clinic.id=?1 and tm.id=?2 and tm.recordStatus = 1")
    TherapyMaster findTherapyAndTherapistDetailByClinicAndTherapyMasterId(Long clinicId, Long therapyMasterId);

    @Query("select tm.id from TherapyMaster tm where tm.serviceCatalogueId=?1 and tm.clinic.id=?2 and tm.recordStatus = 1")
    Long findTherapyMasterIdByServiceCatalogueIdAndClinicId(Long serviceCatalogueId, Long clinicId);


}
