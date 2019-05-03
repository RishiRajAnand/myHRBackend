package com.erx.microservice.patientmanagement.domain.taskmanagement;

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.LookupValue;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Task.
 */
@Entity
@Table(name = "task")
public class Task extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "task_date")
    private LocalDateTime taskDate;

    @Column(name = "jhi_sequence")
    private Integer sequence;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private Set<TaskComment> comments = new HashSet<>();
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private Set<TaskStep> steps = new HashSet<>();
    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<Task> children = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_type_id")
    private LookupValue statusType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private Task parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_to_do_list_id")
    private TaskToDoList taskToDoList;

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

    public Task description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public Task code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Task startTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Task endTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getTaskDate() {
        return taskDate;
    }

    public Task taskDate(LocalDateTime taskDate) {
        this.taskDate = taskDate;
        return this;
    }

    public void setTaskDate(LocalDateTime taskDate) {
        this.taskDate = taskDate;
    }

    public Integer getSequence() {
        return sequence;
    }

    public Task sequence(Integer sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Set<TaskComment> getComments() {
        return comments;
    }

    public Task comments(Set<TaskComment> taskComments) {
        this.comments = taskComments;
        return this;
    }

    public Task addComment(TaskComment taskComment) {
        this.comments.add(taskComment);
        taskComment.setTask(this);
        return this;
    }

    public Task removeComment(TaskComment taskComment) {
        this.comments.remove(taskComment);
        taskComment.setTask(null);
        return this;
    }

    public void setComments(Set<TaskComment> taskComments) {
        this.comments = taskComments;
    }

    public Set<TaskStep> getSteps() {
        return steps;
    }

    public Task steps(Set<TaskStep> taskSteps) {
        this.steps = taskSteps;
        return this;
    }

    public Task addStep(TaskStep taskStep) {
        this.steps.add(taskStep);
        taskStep.setTask(this);
        return this;
    }

    public Task removeStep(TaskStep taskStep) {
        this.steps.remove(taskStep);
        taskStep.setTask(null);
        return this;
    }

    public void setSteps(Set<TaskStep> taskSteps) {
        this.steps = taskSteps;
    }

    public Set<Task> getChildren() {
        return children;
    }

    public Task children(Set<Task> tasks) {
        this.children = tasks;
        return this;
    }

    public Task addChildren(Task task) {
        this.children.add(task);
        task.setParent(this);
        return this;
    }

    public Task removeChildren(Task task) {
        this.children.remove(task);
        task.setParent(null);
        return this;
    }

    public void setChildren(Set<Task> tasks) {
        this.children = tasks;
    }

    public LookupValue getStatusType() {
        return statusType;
    }

    public Task statusType(LookupValue lookupValue) {
        this.statusType = lookupValue;
        return this;
    }

    public void setStatusType(LookupValue lookupValue) {
        this.statusType = lookupValue;
    }

    public Task getParent() {
        return parent;
    }

    public Task parent(Task task) {
        this.parent = task;
        return this;
    }

    public void setParent(Task task) {
        this.parent = task;
    }

    public TaskToDoList getTaskToDoList() {
        return taskToDoList;
    }

    public Task taskToDoList(TaskToDoList taskToDoList) {
        this.taskToDoList = taskToDoList;
        return this;
    }

    public void setTaskToDoList(TaskToDoList taskToDoList) {
        this.taskToDoList = taskToDoList;
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
        Task task = (Task) o;
        if (task.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
