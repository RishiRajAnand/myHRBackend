package com.erx.microservice.patientmanagement.repository;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.domain.WardMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardMasterRepository extends JpaRepository<WardMaster, Long> {

    @Query("select w from WardMaster w where w.status = true and w.clinicLocationId = ?1 and w.recordStatus = 1")
    List<WardMaster> findAllActiveWards(Long clinicLocationId);

    WardMaster findByIdAndClinicLocationIdAndStatusAndRecordStatus(Long wardId, Long clinicLocationId, boolean status,
                                                                   int recordStatus);

    List<WardMaster> findByClinicLocationIdAndStatusAndRecordStatus(Long clinicLocationId, boolean status, int recordStatus);

    @Query("select wm from WardMaster wm where wm.clinicLocationId = ?1 and wm.recordStatus = 1")
    List<WardMaster> getWardMastersByClinicLocation(Long clinicLocationId);

    List<WardMaster> findByClinicLocationIdAndStatusAndRecordStatus(Long clinicLocationId, Boolean activeStatus, int recordStatus);

    @Query("select wm from WardMaster wm where wm.clinicLocationId = ?2 and wm.department.id = ?1 " +
            "and wm.recordStatus = 1")
    List<WardMaster> getWardMasterByDepartment(Long departmentId, Long clinicLocationId);

    @Query("select wm from WardMaster wm where wm.clinicLocationId = ?1 and wm.wardName = ?2 " +
            "and wm.recordStatus = 1")
    WardMaster validateDepartmentName(Long clinicLocationId, String wardName);
}

