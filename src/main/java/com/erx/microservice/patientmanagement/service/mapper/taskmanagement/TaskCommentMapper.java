package com.erx.microservice.patientmanagement.service.mapper.taskmanagement;


import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskComment;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskCommentDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity TaskComment and its DTO TaskCommentDTO.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface TaskCommentMapper {


    TaskCommentDTO taskCommentToTaskCommentDTO(TaskComment taskComment);

    TaskComment taskCommentDTOToTaskComment(TaskCommentDTO taskCommentDTO);

    List<TaskCommentDTO> taskCommentsToTaskCommentDTO(List<TaskComment> taskComments);

    List<TaskComment> taskCommentDTOsToTaskComments(List<TaskCommentDTO> taskCommentDTOS);



}
