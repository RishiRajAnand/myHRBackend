package com.erx.microservice.patientmanagement.service.taskmanagement.task;

import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.domain.taskmanagement.*;
import com.erx.microservice.patientmanagement.repository.LookupValueRepository;
import com.erx.microservice.patientmanagement.repository.taskmanagementRepository.*;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.*;
import com.erx.microservice.patientmanagement.service.mapper.taskmanagement.TaskMapper;
import com.erx.microservice.patientmanagement.service.mapper.taskmanagement.TaskToDoListMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service Implementation for managing Task.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    private final TaskCategoryRepository taskCategoryRepository;

    private final TaskToDoListRepository taskToDoListRepository;

    private final TaskCommentRepository taskCommentRepository;

    private final TaskStepCommentRepository taskStepCommentRepository;

    private final LookupValueRepository lookupValueRepository;

    private final TaskStepRepository taskStepRepository;

    private final TaskMapper taskMapper;
    private final TaskToDoListMapper taskToDoListMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, TaskCategoryRepository taskCategoryRepository,
                           TaskToDoListRepository taskToDoListRepository, TaskCommentRepository taskCommentRepository, TaskStepCommentRepository taskStepCommentRepository, LookupValueRepository lookupValueRepository, TaskStepRepository taskStepRepository, TaskToDoListMapper taskToDoListMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.taskCategoryRepository = taskCategoryRepository;
        this.taskToDoListRepository = taskToDoListRepository;
        this.taskCommentRepository = taskCommentRepository;
        this.taskStepCommentRepository = taskStepCommentRepository;
        this.lookupValueRepository = lookupValueRepository;
        this.taskStepRepository = taskStepRepository;
        this.taskToDoListMapper = taskToDoListMapper;
    }

    /**
     * Save a task.
     *
     * @param taskDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        log.debug("Request to save Task : {}", taskDTO);

        Task task = null;
        if( null != taskDTO.getId() ){

             task =  taskRepository.findOne(taskDTO.getId());

        }
        if(task == null) {
            task = new Task();
        }
            task.setDescription(taskDTO.getDescription());
            task.setCode(taskDTO.getCode());
            task.setStartTime(taskDTO.getStartTime());
            task.setEndTime(taskDTO.getEndTime());
            task.setTaskDate(taskDTO.getTaskDate());
            task.setSequence(taskDTO.getSequence());
            LookupValue lookUpValue = lookupValueRepository.findById(taskDTO.getStatusTypeId());
            if(lookUpValue !=null) {
                task.setStatusType(lookUpValue);
            }

        /**************************************************/
        /**************Saving Task comments************/
        /**************************************************/
            Set<TaskComment> taskComments = new HashSet<>();
            if(!taskDTO.getComments().isEmpty()){
                //List<Long> taskDTOS = new ArrayList<>();
                //taskDTOS.add(taskDTO.getComments().iterator().next().getId());
                List<TaskCommentDTO> taskCommentList = new ArrayList<>();;
                for(TaskCommentDTO tc : taskDTO.getComments()){

                    taskCommentList.add(tc);
                }
                //for(int i=0;i<taskDTO.getSteps().size();i++)
                for(TaskCommentDTO taskCommentDTO : taskCommentList)
                {
                    TaskComment taskComment = taskCommentRepository.findOne(taskCommentDTO.getId());
                    if(taskComment == null) { //save
                        taskComment = new TaskComment();
                    }
                        if(taskComment.getTask() == null){
                            taskComment.setTask(task);
                        }
                        if(taskCommentDTO.getDescription() != null){
                            taskComment.setDescription(taskCommentDTO.getDescription());
                        }


                    taskComments.add(taskComment);
                }
                task.setComments(taskComments);
            }

        /**************************************************/
        /**************Saving TaskSteps************/
        /**************************************************/
            Set<TaskStep> setOfTaskSteps = new HashSet<>();

            if(!taskDTO.getSteps().isEmpty()) {
                List<TaskStepDTO> taskStepList   = new ArrayList<>();;
                for(TaskStepDTO tc : taskDTO.getSteps()){

                    taskStepList.add(tc);
                }
                for(TaskStepDTO taskStepDTO : taskStepList)
                {
                    TaskStep   taskStep = taskStepRepository.findOne(taskStepDTO.getId());
                    if(taskStep == null) {
                        taskStep = new TaskStep();

                    }

                    LookupValue taskSteplookUpValue = lookupValueRepository.findById(taskStepDTO.getStatusTypeId());

                        taskStep.setDescription(taskStepDTO.getDescription());
                        taskStep.setSequence(taskStepDTO.getSequence());
                        taskStep.setCode(taskStepDTO.getCode());

                        taskStep.setStatusType(taskSteplookUpValue);


                    //set taskstepcomment here
                    Set<TaskStepComment> taskStepComments = new HashSet<>();
                    if(!taskDTO.getSteps().iterator().next().getComments().isEmpty()){
                        List<TaskStepCommentDTO> taskStepCommentList =  new ArrayList<>();
                        for(TaskStepCommentDTO tc : taskStepDTO.getComments()){

                            taskStepCommentList.add(tc);
                        }

                        for(TaskStepCommentDTO taskStepCommentDTO : taskStepCommentList)
                        {
                            TaskStepComment taskStepComment = taskStepCommentRepository.findOne(taskStepCommentDTO.getId());
                            if(taskStepComment == null) {
                                taskStepComment = new TaskStepComment();
                            }
                                if(taskStepComment.getTaskStep() == null){
                                    taskStepComment.setTaskStep(taskStep);
                                }
                                if(taskStepCommentDTO.getDescription() != null){
                                    taskStepComment.setDescription(taskStepCommentDTO.getDescription());
                                }

                            taskStepComments.add(taskStepComment);
                        }
                        taskStep.setComments(taskStepComments);
                    }
                    setOfTaskSteps.add(taskStep);
                    //set ToDoList and Status
                }
                task.setSteps(setOfTaskSteps);
            }
        Task   savedTask= taskRepository.save(task);

        TaskDTO taskDTOSaved =  taskMapper.taskToTaskDTO(savedTask);

        return taskDTOSaved;

    }

    /**
     * Get all the tasks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tasks");
        return taskRepository.findAll(pageable)
            .map(taskMapper::taskToTaskDTO);



    }




    /**
     * Delete the task by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Task : {}", id);
        taskRepository.delete(id);
    }

    @Transactional(readOnly = true)
    public Page<TaskDTO> findByToDoListAndDate(Pageable pageable, Long toDoListId, String startDate, String endDate) {
        log.debug("Request to get all Tasks");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        LocalDateTime dateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime dateTime1 = LocalDateTime.parse(endDate, formatter);
        return taskRepository.findByToDoListAndDate(toDoListId, dateTime, dateTime1,pageable)
        .map(taskMapper::taskToTaskDTO);
//        return taskRepository.findByToDoListAndDate(pageable, catId, dateTime, dateTime1)
//            .map(taskMapper::tasksToTaskDTOs());
    //   return null;
}

    @Override
    public Page<TaskToDoListDTO> getToDoList(Long categoryId,Pageable pageable) {
        log.debug("Request to get all ToDoList by category ID: ");
        return taskToDoListRepository.findByTaskCategory(categoryId,pageable)
                .map(taskToDoListMapper::taskToDoListToTaskToDoListDTO);
    }
}
