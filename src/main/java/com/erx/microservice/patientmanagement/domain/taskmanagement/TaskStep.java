package com.erx.microservice.patientmanagement.domain.taskmanagement;

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A TaskStep.
 */
@Entity
@Table(name = "task_step")
public class TaskStep extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "jhi_sequence")
    private Integer sequence;

    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @OneToMany(mappedBy = "taskStep", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TaskStepComment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_type_id")
    private LookupValue statusType;

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

    public TaskStep description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSequence() {
        return sequence;
    }

    public TaskStep sequence(Integer sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getCode() {
        return code;
    }

    public TaskStep code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Task getTask() {
        return task;
    }

    public TaskStep task(Task task) {
        this.task = task;
        return this;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Set<TaskStepComment> getComments() {
        return comments;
    }

    public TaskStep comments(Set<TaskStepComment> taskStepComments) {
        this.comments = taskStepComments;
        return this;
    }

    public TaskStep addComment(TaskStepComment taskStepComment) {
        this.comments.add(taskStepComment);
        taskStepComment.setTaskStep(this);
        return this;
    }

    public TaskStep removeComment(TaskStepComment taskStepComment) {
        this.comments.remove(taskStepComment);
        taskStepComment.setTaskStep(null);
        return this;
    }

    public void setComments(Set<TaskStepComment> taskStepComments) {
        this.comments = taskStepComments;
    }

    public LookupValue getStatusType() {
        return statusType;
    }

    public TaskStep statusType(LookupValue lookupValue) {
        this.statusType = lookupValue;
        return this;
    }

    public void setStatusType(LookupValue lookupValue) {
        this.statusType = lookupValue;
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
        TaskStep taskStep = (TaskStep) o;
        if (taskStep.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskStep.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
