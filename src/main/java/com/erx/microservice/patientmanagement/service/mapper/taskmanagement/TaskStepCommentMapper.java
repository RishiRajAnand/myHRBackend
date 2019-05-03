package com.erx.microservice.patientmanagement.service.mapper.taskmanagement;

import com.erx.microservice.patientmanagement.domain.taskmanagement.TaskStepComment;
import com.erx.microservice.patientmanagement.service.dto.taskmanagementdto.TaskStepCommentDTO;
import org.mapstruct.*;

import java.util.List;

/**
 * Mapper for the entity TaskStepComment and its DTO TaskStepCommentDTO.
 */
@Mapper(componentModel = "spring", uses = {TaskStepMapper.class})
public interface TaskStepCommentMapper {

    TaskStepCommentDTO taskStepCommentToTaskStepCommentDTO (TaskStepComment taskStepComment);

    TaskStepComment taskStepCommentDTOToTaskStepComment(TaskStepCommentDTO taskStepCommentDTO);

    List<TaskStepCommentDTO> taskStepCommentsToTaskStepCommentDTOs(List<TaskStepComment> taskStepComments);

    List<TaskStepComment> taskStepCommentDTOsToTaskStepComments(List<TaskStepCommentDTO> taskStepCommentDTOs);

}
