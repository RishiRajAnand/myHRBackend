package com.erx.microservice.patientmanagement.service.dto.taskmanagementdto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TaskStepComment entity.
 */
public class TaskStepCommentDTO implements Serializable {

    private Long id;

    private String description;

    private Long taskStepId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTaskStepId() {
        return taskStepId;
    }

    public void setTaskStepId(Long taskStepId) {
        this.taskStepId = taskStepId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskStepCommentDTO taskStepCommentDTO = (TaskStepCommentDTO) o;
        if (taskStepCommentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskStepCommentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
