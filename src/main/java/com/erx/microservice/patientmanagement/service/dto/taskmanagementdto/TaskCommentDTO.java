package com.erx.microservice.patientmanagement.service.dto.taskmanagementdto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TaskComment entity.
 */
public class TaskCommentDTO implements Serializable {

    private Long id;

    private String description;

    private Long taskId;

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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskCommentDTO taskCommentDTO = (TaskCommentDTO) o;
        if (taskCommentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskCommentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
