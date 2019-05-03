package com.erx.microservice.patientmanagement.repository.taskmanagementRepository;

import com.erx.microservice.patientmanagement.domain.taskmanagement.Task;
import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskStep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TaskStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskStepRepository extends JpaRepository<TaskStep, Long> {

    @Query("SELECT ts FROM TaskStep ts WHERE ts.task = ?1 ORDER BY ts.updatedOn DESC")
    public Page<TaskStep> findByTask(Pageable pageable, Task task);

}
