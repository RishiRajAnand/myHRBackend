package com.erx.microservice.patientmanagement.repository.taskmanagementRepository;


import com.erx.microservice.patientmanagement.domain.taskmanagement.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;


/**
 * Spring Data  repository for the Task entity.
 */

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

   // String findByCatQuery = "SELECT t from Task t WHERE t.taskToDoList.id =?2 AND t.startTime >= :startDate AND t.endTime <= :endDate ORDER BY t.startTime ";

   // @Query(findByCatQuery)
    @Query("SELECT t from Task t WHERE t.taskToDoList.id =?1 AND t.startTime >=?2 AND t.endTime <=?3 ORDER BY t.startTime")
    Page<Task> findByToDoListAndDate(Long toDoListId,LocalDateTime startDate,LocalDateTime endDate,Pageable pageable);


}
