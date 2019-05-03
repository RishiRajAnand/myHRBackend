package com.erx.microservice.patientmanagement.service.dto.taskmanagementdto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Task entity.
 */
public class TaskDTO implements Serializable {

    private Long id;

    private String description;

    private String code;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime taskDate;

    private Integer sequence;

    private Long statusTypeId;

    private Long parentId;

    private Long taskToDoListId;

    private Long categoryTypeId;

    private Set<TaskDTO> children = new HashSet<>();

    private Set<TaskCommentDTO> comments = new HashSet<>();

    private Set<TaskStepDTO> steps = new HashSet<>();

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDateTime taskDate) {
        this.taskDate = taskDate;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long taskId) {
        this.parentId = taskId;
    }

    public Long getTaskToDoListId() {
        return taskToDoListId;
    }

    public void setTaskToDoListId(Long taskToDoListId) {
        this.taskToDoListId = taskToDoListId;
    }

    public Set<TaskDTO> getChildren() {
        return children;
    }

    public void setChildren(Set<TaskDTO> children) {
        this.children = children;
    }

    public Set<TaskCommentDTO> getComments() {
        return comments;
    }

    public void setComments(Set<TaskCommentDTO> comments) {
        this.comments = comments;
    }

    public Set<TaskStepDTO> getSteps() {
        return steps;
    }

    public void setSteps(Set<TaskStepDTO> steps) {
        this.steps = steps;
    }

    public Long getCategoryTypeId() {
        return categoryTypeId;
    }

    public void setCategoryTypeId(Long categoryTypeId) {
        this.categoryTypeId = categoryTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskDTO taskDTO = (TaskDTO) o;
        if (taskDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
