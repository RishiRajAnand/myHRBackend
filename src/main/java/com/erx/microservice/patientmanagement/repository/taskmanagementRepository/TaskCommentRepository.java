package com.erx.microservice.patientmanagement.repository.taskmanagementRepository;

import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TaskComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {

}
