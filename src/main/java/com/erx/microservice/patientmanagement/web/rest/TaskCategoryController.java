package com.erx.microservice.patientmanagement.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskCategoryDTO;
import com.erx.microservice.patientmanagement.service.taskmanagement.taskcategory.TaskCategoryService;
import com.erx.microservice.patientmanagement.web.rest.util.PaginationUtil;
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
import java.util.Optional;

/**
 * REST controller for managing TaskCategory.
 */
@RestController
@RequestMapping("/api")
public class TaskCategoryController {

    private final Logger log = LoggerFactory.getLogger(TaskCategoryController.class);

    private static final String ENTITY_NAME = "taskCategory";

    private final TaskCategoryService taskCategoryService;

    public TaskCategoryController(TaskCategoryService taskCategoryService) {
        this.taskCategoryService = taskCategoryService;
    }

//    /**
//     * POST  /task-categories : Create a new taskCategory.
//     *
//     * @param taskCategoryDTO the taskCategoryDTO to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new taskCategoryDTO, or with status 400 (Bad Request) if the taskCategory has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/task-categories")
//    @Timed
//    public ResponseEntity<TaskCategoryDTO> createTaskCategory(@RequestBody TaskCategoryDTO taskCategoryDTO) throws URISyntaxException {
//        log.debug("REST request to save TaskCategory : {}", taskCategoryDTO);
//        if (taskCategoryDTO.getId() != null)
//            throw new BadRequestAlertException("A new taskCategory cannot already have an ID", ENTITY_NAME, "idexists");
//        TaskCategoryDTO result = taskCategoryService.save(taskCategoryDTO);
//        return ResponseEntity.created(new URI("/api/task-categories/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }

//    /**
//     * PUT  /task-categories : Updates an existing taskCategory.
//     *
//     * @param taskCategoryDTO the taskCategoryDTO to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated taskCategoryDTO,
//     * or with status 400 (Bad Request) if the taskCategoryDTO is not valid,
//     * or with status 500 (Internal Server Error) if the taskCategoryDTO couldn't be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/task-categories")
//    @Timed
//    public ResponseEntity<TaskCategoryDTO> updateTaskCategory(@RequestBody TaskCategoryDTO taskCategoryDTO) throws URISyntaxException {
//        log.debug("REST request to update TaskCategory : {}", taskCategoryDTO);
//        if (taskCategoryDTO.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        TaskCategoryDTO result = taskCategoryService.save(taskCategoryDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, taskCategoryDTO.getId().toString()))
//            .body(result);
//    }

//    /**
//     * GET  /task-categories : get all the taskCategories.
//     *
//     * @param pageable the pagination information
//     * @return the ResponseEntity with status 200 (OK) and the list of taskCategories in body
//     */
//    @GetMapping("/task-categories")
//    @Timed
//    public ResponseEntity<List<TaskCategoryDTO>> getAllTaskCategories(Pageable pageable) {
//        log.debug("REST request to get a page of TaskCategories");
//        Page<TaskCategoryDTO> page = taskCategoryService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/task-categories");
//        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
//    }

//    /**
//     * GET  /task-categories/:id : get the "id" taskCategory.
//     *
//     * @param id the id of the taskCategoryDTO to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the taskCategoryDTO, or with status 404 (Not Found)
//     */
//    @GetMapping("/task-categories/{id}")
//    @Timed
//    public ResponseEntity<TaskCategoryDTO> getTaskCategory(@PathVariable Long id) {
//        log.debug("REST request to get TaskCategory : {}", id);
//        Optional<TaskCategoryDTO> taskCategoryDTO = taskCategoryService. findOne(id);
//        return ResponseUtil.wrapOrNotFound(taskCategoryDTO);
//    }

//    /**
//     * DELETE  /task-categories/:id : delete the "id" taskCategory.
//     *
//     * @param id the id of the taskCategoryDTO to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/task-categories/{id}")
//    @Timed
//    public ResponseEntity<Void> deleteTaskCategory(@PathVariable Long id) {
//        log.debug("REST request to delete TaskCategory : {}", id);
//        taskCategoryService.delete(id);
//        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
//    }



    @GetMapping("/task-categories/location/{locId}/context/{context}")
    @Timed
    public ResponseEntity<List<TaskCategoryDTO>> getAllTaskCategories(Pageable pageable, @PathVariable Long locId, @PathVariable String context  ) {
        log.debug("REST request to get a page of TaskCategories");
        Page<TaskCategoryDTO> page = taskCategoryService.findByClinicLocationAndContextType(pageable, locId, context);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/task-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
