package com.erx.microservice.patientmanagement.service.mapper.taskmanagement;

import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskToDoListComment;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskToDoListCommentDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity TaskToDoListComment and its DTO TaskToDoListCommentDTO.
 */
@Mapper(componentModel = "spring", uses = {TaskToDoListMapper.class})
public interface TaskToDoListCommentMapper  {


    TaskToDoListCommentDTO taskToDoListCommentToTaskToDoListCommentDTO (TaskToDoListComment taskToDoListComment);

    TaskToDoListComment taskToDoListCommentDTOToTaskToDoListComment(TaskToDoListCommentDTO taskToDoListCommentDTO);

    List<TaskToDoListCommentDTO> taskToDoListCommentsToTaskToDoListCommentDTOs(List<TaskToDoListComment> taskToDoListComments);

    List<TaskToDoListComment> taskToDoListCommentDTOsToTaskToDoListComments(List<TaskToDoListCommentDTO> taskToDoListCommentDTOs);

}
