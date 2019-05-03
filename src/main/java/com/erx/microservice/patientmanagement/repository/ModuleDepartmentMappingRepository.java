package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.ModuleDepartmentMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleDepartmentMappingRepository extends JpaRepository<ModuleDepartmentMapping, Long>{

    @Query("SELECT mp from ModuleDepartmentMapping mp " +
            " where mp.module.id=?1 and mp.recordStatus=1")
    List<ModuleDepartmentMapping> findDepartmentByModuleId(Long moduleId);
}
