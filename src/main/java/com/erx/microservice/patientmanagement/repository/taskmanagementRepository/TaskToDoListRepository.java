package com.erx.microservice.patientmanagement.repository.taskmanagementRepository;

import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskCategory;
import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskToDoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the TaskToDoList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskToDoListRepository extends JpaRepository<TaskToDoList, Long> {

    @Query("SELECT t from TaskToDoList t WHERE t.taskCategory =?1 AND t.recordStatus = 1 ")
    public TaskToDoList findByTaskCategoryAndRecordStatus(TaskCategory taskCategory);

    @Query("select t from TaskToDoList t where t.taskCategory.id=?1")
    Page<TaskToDoList> findByTaskCategory(Long categoryId, Pageable pageable);
}
