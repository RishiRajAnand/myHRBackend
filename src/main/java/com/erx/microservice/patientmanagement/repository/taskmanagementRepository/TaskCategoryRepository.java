package com.erx.microservice.patientmanagement.repository.taskmanagementRepository;

import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TaskCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {

    public Page<TaskCategory> findByClinicLocationAndContextType(Pageable pageable, ClinicLocation clinicLocation, LookupValue contextType);
}
