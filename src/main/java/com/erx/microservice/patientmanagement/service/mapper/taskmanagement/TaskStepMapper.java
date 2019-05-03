package com.erx.microservice.patientmanagement.service.mapper.taskmanagement;

import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskStep;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskStepDTO;
import com.erx.microservice.patientmanagement.service.mapper.LookupValueMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper for the entity TaskStep and its DTO TaskStepDTO.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class, LookupValueMapper.class,TaskStepCommentMapper.class})
public interface TaskStepMapper {

    @Mappings({
            @Mapping(source = "task.id",target = "taskId"),
            @Mapping(source = "statusType.id",target = "statusTypeId")
    })
    TaskStepDTO taskStepToTaskStepDTO (TaskStep taskStep);

    @Mappings({
            @Mapping(target = "task.id",source = "taskId"),
            @Mapping(target = "statusType.id",source = "statusTypeId")
    })
    TaskStep taskStepDTOToTaskStep(TaskStepDTO taskStepDTO);

    List<TaskStepDTO> taskStepsToTaskStepDTOs(List<TaskStep> taskSteps);

    List<TaskStep> taskStepDTOsToTaskSteps(List<TaskStepDTO> taskStepDTOs);

}
