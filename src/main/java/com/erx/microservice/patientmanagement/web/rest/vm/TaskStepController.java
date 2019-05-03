package com.erx.microservice.patientmanagement.web.rest.vm;

import com.codahale.metrics.annotation.Timed;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskDTO;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskStepDTO;
import com.erx.microservice.patientmanagement.service.taskmanagement.taskstep.TaskStepService;
import com.erx.microservice.patientmanagement.web.rest.util.HeaderUtil;
import com.erx.microservice.patientmanagement.web.rest.util.PaginationUtil;
import com.erx.microservice.patientmanagement.web.rest.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class TaskStepController {
    private final Logger log = LoggerFactory.getLogger(TaskStepController.class);

    private static final String ENTITY_NAME = "taskStep";

    private final TaskStepService taskStepService;

    public TaskStepController(TaskStepService taskStepService) {
        this.taskStepService = taskStepService;
    }

    /**
     * POST  /task-steps : Create a new taskStep.
     *
     * @param taskStepDTO the taskStepDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new taskStepDTO, or with status 400 (Bad Request) if the taskStep has already an ID
     *
     */
    @PostMapping("/task-steps")
    @Timed
    public ResponseEntity<TaskStepDTO> createTaskStep(@RequestBody TaskStepDTO taskStepDTO)  throws URISyntaxException  {
        log.debug("REST request to save TaskStep : {}", taskStepDTO);
        if (taskStepDTO == null) {
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        TaskStepDTO result = taskStepService.save(taskStepDTO);

        return ResponseEntity.created(new URI("/api/tasks/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /task-steps : Updates an existing taskStep.
     *
     * @param taskStepDTO the taskStepDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated taskStepDTO,
     * or with status 400 (Bad Request) if the taskStepDTO is not valid,
     * or with status 500 (Internal Server Error) if the taskStepDTO couldn't be updated
     *
     */
    @PutMapping("/task-steps")
    @Timed
    public ResponseEntity<TaskStepDTO> updateTaskStep(@RequestBody TaskStepDTO taskStepDTO) {
        log.debug("REST request to update TaskStep : {}", taskStepDTO);

        if (taskStepDTO == null) {
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        TaskStepDTO result = taskStepService.save(taskStepDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, taskStepDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET  /task-steps : get all the taskSteps.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of taskSteps in body
     */
//    @GetMapping("/task-steps")
//    @Timed
//    public ResponseEntity<List<TaskStepDTO>> getAllTaskSteps(Pageable pageable) {
//        log.debug("REST request to get a page of TaskSteps");
//        Page<TaskStepDTO> page = taskStepService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/task-steps");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }

    /**
     * GET  /task-steps/:id : get the "id" taskStep.
     *
     * @param taskId the Task id of the taskStepDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the taskStepDTO, or with status 404 (Not Found)
     */
//    @GetMapping("/task-steps/{id}")
//    @Timed
//    public ResponseEntity<TaskStepDTO> getTaskStep(@PathVariable Long id) {
//        log.debug("REST request to get TaskStep : {}", id);
//        TaskStepDTO taskStepDTO = taskStepService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(taskStepDTO);
//    }
    @GetMapping("/task-steps/{taskId}")
    @Timed
    public ResponseEntity<List<TaskStepDTO>> getTaskStep(Pageable pageable, @PathVariable Long taskId){
        log.debug("REST request to get TaskStep for task: {}", taskId);

        if (taskId == null) {
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Page<TaskStepDTO> page = taskStepService.findStepsOfTask(pageable, taskId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/task-steps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * DELETE  /task-steps/:id : delete the "id" taskStep.
     *
     * @param id the id of the taskStepDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/task-steps/{id}")
    @Timed
    public ResponseEntity<Void> deleteTaskStep(@PathVariable Long id) {
        log.debug("REST request to delete TaskStep : {}", id);
        taskStepService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
