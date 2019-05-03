package com.erx.microservice.patientmanagement.repository.taskmanagementRepository;


import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskStepComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TaskStepComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskStepCommentRepository extends JpaRepository<TaskStepComment, Long> {

}
