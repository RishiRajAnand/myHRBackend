package com.erx.microservice.patientmanagement.service.dto.taskmanagementdto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TaskToDoListComment entity.
 */
public class TaskToDoListCommentDTO implements Serializable {

    private Long id;

    private String description;

    private Long taskToDoListId;

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

    public Long getTaskToDoListId() {
        return taskToDoListId;
    }

    public void setTaskToDoListId(Long taskToDoListId) {
        this.taskToDoListId = taskToDoListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskToDoListCommentDTO taskToDoListCommentDTO = (TaskToDoListCommentDTO) o;
        if (taskToDoListCommentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskToDoListCommentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
