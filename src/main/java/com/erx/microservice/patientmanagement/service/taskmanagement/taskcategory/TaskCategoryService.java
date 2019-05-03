package com.erx.microservice.patientmanagement.service.taskmanagement.taskcategory;

import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing TaskCategory.
 */
public interface TaskCategoryService {

    /**
     * Save a taskCategory.
     *
     * @param taskCategoryDTO the entity to save
     * @return the persisted entity
     */
    TaskCategoryDTO save(TaskCategoryDTO taskCategoryDTO);

    /**
     * Get all the taskCategories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TaskCategoryDTO> findAll(Pageable pageable);




    /**
     * Delete the "id" taskCategory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * get the entity for given clinic location and context type
     *
     * @param pageable the pagination information
     * @param location the location
     * @param context
     * @return the list of entities
     */
    Page<TaskCategoryDTO> findByClinicLocationAndContextType(Pageable pageable, Long location, String context);
}
