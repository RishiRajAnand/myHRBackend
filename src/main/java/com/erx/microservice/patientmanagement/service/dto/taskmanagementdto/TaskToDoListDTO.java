package com.erx.microservice.patientmanagement.service.dto.taskmanagementdto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TaskToDoList entity.
 */
public class TaskToDoListDTO implements Serializable {

    private Long id;

    private String description;

    private Integer sequence;

    private Long statusTypeId;

    private Long taskCategoryId;

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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Long getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Long lookupValueId) {
        this.statusTypeId = lookupValueId;
    }

    public Long getTaskCategoryId() {
        return taskCategoryId;
    }

    public void setTaskCategoryId(Long taskCategoryId) {
        this.taskCategoryId = taskCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskToDoListDTO taskToDoListDTO = (TaskToDoListDTO) o;
        if (taskToDoListDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskToDoListDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
