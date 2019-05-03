package com.erx.microservice.patientmanagement.service.taskmanagement.taskstep;

import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskStepDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskStepService {

    /**
     * Save a taskStep.
     *
     * @param taskStepDTO the entity to save
     * @return the persisted entity
     */
    TaskStepDTO save(TaskStepDTO taskStepDTO);

    /**
     * Get all the taskSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TaskStepDTO> findAll(Pageable pageable);


    /**
     * Get the "id" taskStep.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TaskStepDTO findOne(Long id);

    /**
     * Delete the "id" taskStep.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Page<TaskStepDTO> findStepsOfTask( Pageable pageable, Long taskId);


}
