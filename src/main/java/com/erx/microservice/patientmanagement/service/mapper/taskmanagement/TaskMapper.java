package com.erx.microservice.patientmanagement.service.mapper.taskmanagement;

import com.erx.microservice.patientmanagement.domain.taskmanagement.Task;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskDTO;
import com.erx.microservice.patientmanagement.service.mapper.LookupValueMapper;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity Task and its DTO TaskDTO.
 */
@Mapper(componentModel = "spring", uses = {TaskStepMapper.class, LookupValueMapper.class, TaskToDoListMapper.class,TaskCommentMapper.class})
public interface TaskMapper {

    @Mappings({
            @Mapping(source = "taskToDoList.id",target = "taskToDoListId"),
            @Mapping(source = "statusType.id",target = "statusTypeId")
    })
    TaskDTO taskToTaskDTO(Task task);

    @Mappings({
            @Mapping(source = "taskToDoListId",target = "taskToDoList.id"),
            @Mapping(source = "statusTypeId",target = "statusType.id")

    })
    Task taskDTOToTask(TaskDTO taskDTO);

    List<TaskDTO> tasksToTaskDTOs(List<Task> tasks);

    List<Task> taskDTOsToTasks(List<TaskDTO> taskDTOs);

}
