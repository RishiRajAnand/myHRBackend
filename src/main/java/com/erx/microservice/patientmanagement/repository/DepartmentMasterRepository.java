package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 29-11-17.
* */

import com.erx.microservice.patientmanagement.domain.DepartmentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentMasterRepository")
public interface DepartmentMasterRepository extends JpaRepository<DepartmentMaster, Long> {

    @Query("select d from DepartmentMaster d where d.clinicLocation.id = ?1 " +
            "and d.isSubDepartment = false and d.recordStatus = 1")
    List<DepartmentMaster> findDepartmentMasterByClinicLocation(Long clinicLocationId);


    @Query("select d from DepartmentMaster d where d.clinicLocation.id = ?1 " +
            "and d.parentDepartment.id = ?2 and d.recordStatus = 1 and d.isSubDepartment = true")
    List<DepartmentMaster> findSubDepartmentByDepartmentAndClinic
            (Long clinicLocationId, Long parentDepartmentId);

}