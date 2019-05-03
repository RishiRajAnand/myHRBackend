package com.erx.microservice.patientmanagement.service.mapper.taskmanagement;

import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskCategory;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskCategoryDTO;
import com.erx.microservice.patientmanagement.service.mapper.ClinicLocationMapper;
import com.erx.microservice.patientmanagement.service.mapper.EntityMapper;
import com.erx.microservice.patientmanagement.service.mapper.LookupValueMapper;
import org.mapstruct.*;


import java.util.List;

/**
 * Mapper for the entity TaskCategory and its DTO TaskCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskCategoryMapper {

    @Mappings({
            @Mapping(source = "parent.id", target = "parentId"),
            @Mapping(source = "categoryType.id", target = "categoryTypeId"),
            @Mapping(source = "contextType.id", target = "contextTypeId"),
            @Mapping(source = "clinicLocation.clinic.name", target = "clinicLocation")
    })
    TaskCategoryDTO toDto(TaskCategory taskCategory);

    @Mappings({
            @Mapping(source = "parentId", target = "parent.id"),
            @Mapping(source = "categoryTypeId", target = "categoryType.id"),
            @Mapping(source = "contextTypeId", target = "contextType.id"),
            @Mapping(source = "clinicLocation", target = "clinicLocation.clinic.name")
    })
    TaskCategory toEntity(TaskCategoryDTO taskCategoryDTO);

    List<TaskCategoryDTO> toDto(List<TaskCategory> taskCategorys);

    List<TaskCategory> toEntity(List<TaskCategoryDTO> taskCategoryDTOs);


}
