package com.erx.microservice.patientmanagement.repository.taskmanagementRepository;


import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskToDoListComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TaskToDoListComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskToDoListCommentRepository extends JpaRepository<TaskToDoListComment, Long> {

}
