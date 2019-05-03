package com.erx.microservice.patientmanagement.service.taskmanagement.taskstep;

import com.erx.microservice.patientmanagement.domain.Lookup;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.domain.taskmanagement.Task;
import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskStep;
import com.erx.microservice.patientmanagement.repository.LookupValueRepository;
import com.erx.microservice.patientmanagement.repository.taskmanagementRepository.TaskRepository;
import com.erx.microservice.patientmanagement.repository.taskmanagementRepository.TaskStepRepository;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskStepDTO;
import com.erx.microservice.patientmanagement.service.mapper.taskmanagement.TaskStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class TaskStepServiceImpl implements TaskStepService {

    private final Logger log = LoggerFactory.getLogger(TaskStepServiceImpl.class);

    private final TaskStepRepository taskStepRepository;

    private final TaskRepository taskRepository;

    private final LookupValueRepository lookupValueRepository;

    private final TaskStepMapper taskStepMapper;

    public TaskStepServiceImpl(TaskStepRepository taskStepRepository, TaskStepMapper taskStepMapper, LookupValueRepository lookupValueRepository,
                               TaskRepository taskRepository) {
        this.taskStepRepository = taskStepRepository;
        this.taskStepMapper = taskStepMapper;
        this.lookupValueRepository = lookupValueRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Save a taskStep.
     *
     * @param taskStepDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TaskStepDTO save(TaskStepDTO taskStepDTO) {
        log.debug("Request to save/update TaskStep : {}", taskStepDTO);

        LookupValue status = null;
        TaskStep taskStep = taskStepMapper.taskStepDTOToTaskStep(taskStepDTO);
        if(taskStepDTO.getStatusTypeId() == null ) {
            Lookup lookupObj = new Lookup();
            lookupObj.setId(89L);
            status = lookupValueRepository.findLookUpByNameAndLookUpId("Not Started", lookupObj);
        }else{
            status = lookupValueRepository.findOne(taskStepDTO.getStatusTypeId());
        }

        taskStep.setStatusType(status);
        taskStep = taskStepRepository.save(taskStep);
        return taskStepMapper.taskStepToTaskStepDTO(taskStep);
    }

    /**
     * Get all the taskSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaskStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaskSteps");
        return taskStepRepository.findAll(pageable)
                .map(taskStepMapper::taskStepToTaskStepDTO);
    }


    /**
     * Get one taskStep by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TaskStepDTO findOne(Long id) {
        log.debug("Request to get TaskStep : {}", id);
        TaskStep taskStep =  taskStepRepository.findOne(id);
        return taskStepMapper.taskStepToTaskStepDTO(taskStep);
    }

    /**
     * Delete the taskStep by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskStep : {}", id);
        taskStepRepository.delete(id);
    }

    /**
     *
     * @param taskId the task Id
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<TaskStepDTO> findStepsOfTask( Pageable pageable, Long taskId){
        log.debug("Request to Fetch TaskSteps for task : {}", taskId);

        return taskStepRepository.findByTask(pageable,taskRepository.getOne(taskId))
                .map(taskStepMapper::taskStepToTaskStepDTO);
    }
}
