package com.erx.microservice.patientmanagement.service.dto.taskmanagementdto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the TaskStep entity.
 */
public class TaskStepDTO implements Serializable {

    private Long id;

    private String description;

    private Integer sequence;

    private String code;

    private Long taskId;

    private Long statusTypeId;

    private Set<TaskStepCommentDTO> comments = new HashSet<>();

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(Long lookupValueId) {
        this.statusTypeId = lookupValueId;
    }


    public Set<TaskStepCommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<TaskStepCommentDTO> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskStepDTO taskStepDTO = (TaskStepDTO) o;
        if (taskStepDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskStepDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
