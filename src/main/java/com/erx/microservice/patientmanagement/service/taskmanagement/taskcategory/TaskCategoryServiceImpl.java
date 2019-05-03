package com.erx.microservice.patientmanagement.service.taskmanagement.taskcategory;

import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskCategory;
import com.erx.microservice.patientmanagement.repository.ClinicLocationRepository;
import com.erx.microservice.patientmanagement.repository.LookupValueRepository;
import com.erx.microservice.patientmanagement.repository.taskmanagementRepository.TaskCategoryRepository;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskCategoryDTO;
import com.erx.microservice.patientmanagement.service.mapper.taskmanagement.TaskCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Implementation for managing TaskCategory.
 */
@Service
@Transactional
public class TaskCategoryServiceImpl implements TaskCategoryService {

    private final Logger log = LoggerFactory.getLogger(TaskCategoryServiceImpl.class);

  //  @Autowired
    private final TaskCategoryRepository taskCategoryRepository;

   // @Autowired
    private final TaskCategoryMapper taskCategoryMapper;

 //   @Autowired
    private final LookupValueRepository lookupValueRepository;

  //  @Autowired
    private final ClinicLocationRepository clinicLocationRepository;

    public TaskCategoryServiceImpl(TaskCategoryRepository taskCategoryRepository, TaskCategoryMapper taskCategoryMapper, LookupValueRepository lookupValueRepository, ClinicLocationRepository clinicLocationRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
        this.taskCategoryMapper = taskCategoryMapper;
        this.lookupValueRepository = lookupValueRepository;
        this.clinicLocationRepository = clinicLocationRepository;
    }

    /**
     * Save a taskCategory.
     *
     * @param taskCategoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TaskCategoryDTO save(TaskCategoryDTO taskCategoryDTO) {
        log.debug("Request to save TaskCategory : {}", taskCategoryDTO);

        TaskCategory taskCategory = taskCategoryMapper.toEntity(taskCategoryDTO);
        taskCategory = taskCategoryRepository.save(taskCategory);
        return taskCategoryMapper.toDto(taskCategory);
    }

    /**
     * Get all the taskCategories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaskCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaskCategories");
        return taskCategoryRepository.findAll(pageable)
                .map(taskCategoryMapper::toDto);
        }




    /**
     * Delete the taskCategory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskCategory : {}", id);
        taskCategoryRepository.delete(id);
    }


    /**
     * get the entity for given clinic location and context type
     *
     * @param pageable the pagination information
     * @param location the location
     * @param context
     * @return the list of entities
     */
    @Override
    public Page<TaskCategoryDTO> findByClinicLocationAndContextType(Pageable pageable, Long location, String context) {
        log.debug("Request to get all PmTaskCategories for given context and location");
        ClinicLocation clinicLocation = clinicLocationRepository.findOne(location);
        LookupValue contextType = lookupValueRepository.findLookUpByName(context);
        Page<TaskCategory> d = taskCategoryRepository.findByClinicLocationAndContextType(pageable,clinicLocation,contextType);
        Page<TaskCategoryDTO> e = d.map(taskCategoryMapper::toDto);
        return e;
    }


}
