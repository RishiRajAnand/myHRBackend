package com.erx.microservice.patientmanagement.domain.taskmanagement;

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;
import java.util.Objects;

/**
 * A TaskStepComment.
 */
@Entity
@Table(name = "task_step_comment")
public class TaskStepComment extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_step_id", nullable = true)
    private TaskStep taskStep;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public TaskStepComment description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStep getTaskStep() {
        return taskStep;
    }

    public TaskStepComment taskStep(TaskStep taskStep) {
        this.taskStep = taskStep;
        return this;
    }

    public void setTaskStep(TaskStep taskStep) {
        this.taskStep = taskStep;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskStepComment taskStepComment = (TaskStepComment) o;
        if (taskStepComment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskStepComment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
