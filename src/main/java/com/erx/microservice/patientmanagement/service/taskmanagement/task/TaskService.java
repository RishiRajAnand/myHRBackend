package com.erx.microservice.patientmanagement.service.taskmanagement.task;

import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskToDoList;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskDTO;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskToDoListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Task.
 */
public interface TaskService {

    /**
     * Save a task.
     *
     * @param taskDTO the entity to save
     * @return the persisted entity
     */
    TaskDTO save(TaskDTO taskDTO);

    /**
     * Get all the tasks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TaskDTO> findAll(Pageable pageable);


    /**
     * Delete the "id" task.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Page<TaskDTO> findByToDoListAndDate(Pageable pageable, Long catId, String startDate, String endDate);

    Page<TaskToDoListDTO> getToDoList(Long categoryId,Pageable pageable);
}
