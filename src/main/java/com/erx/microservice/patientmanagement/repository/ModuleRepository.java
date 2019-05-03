package com.erx.microservice.patientmanagement.repository;

import com.erx.microservice.patientmanagement.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long>{

    @Query("SELECT m from Module m " +
            " where m.code=?1 and m.recordStatus=1")
    Module findByModuleCode(String caseModuleCode);
}
