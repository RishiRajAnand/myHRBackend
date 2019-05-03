package com.erx.microservice.patientmanagement.service.mapper.taskmanagement;

import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskToDoList;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskToDoListDTO;
import com.erx.microservice.patientmanagement.service.mapper.LookupValueMapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity TaskToDoList and its DTO TaskToDoListDTO.
 */
@Mapper(componentModel = "spring", uses = {LookupValueMapper.class, TaskCategoryMapper.class,TaskToDoListCommentMapper.class})
public interface TaskToDoListMapper  {

    TaskToDoListDTO taskToDoListToTaskToDoListDTO (TaskToDoList taskToDoList);

    TaskToDoList taskToDoListDTOToTaskToDoList(TaskToDoListDTO taskToDoListDTO);

    List<TaskToDoListDTO> taskToDoListsToTaskToDoListDTOs(List<TaskToDoList> taskToDoLists);

    List<TaskToDoList> taskToDoListDTOsToTaskToDoLists(List<TaskToDoListDTO> taskToDoListDTOs);

}
