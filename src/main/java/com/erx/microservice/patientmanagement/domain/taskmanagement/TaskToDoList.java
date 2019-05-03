package com.erx.microservice.patientmanagement.domain.taskmanagement;

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.LookupValue;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A TaskToDoList.
 */
@Entity
@Table(name = "task_to_do_list")
public class TaskToDoList extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "jhi_sequence")
    private Integer sequence;

//    @OneToMany(mappedBy = "taskToDoList")
    @OneToMany(mappedBy = "taskToDoList", fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();
    @OneToMany(mappedBy = "taskToDoList", fetch = FetchType.EAGER)
    private Set<TaskToDoListComment> comments = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_type_id")
    private LookupValue statusType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_category_id")
    private TaskCategory taskCategory;

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

    public TaskToDoList description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSequence() {
        return sequence;
    }

    public TaskToDoList sequence(Integer sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public TaskToDoList tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public TaskToDoList addTask(Task task) {
        this.tasks.add(task);
        task.setTaskToDoList(this);
        return this;
    }

    public TaskToDoList removeTask(Task task) {
        this.tasks.remove(task);
        task.setTaskToDoList(null);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<TaskToDoListComment> getComments() {
        return comments;
    }

    public TaskToDoList comments(Set<TaskToDoListComment> taskToDoListComments) {
        this.comments = taskToDoListComments;
        return this;
    }

    public TaskToDoList addComment(TaskToDoListComment taskToDoListComment) {
        this.comments.add(taskToDoListComment);
        taskToDoListComment.setTaskToDoList(this);
        return this;
    }

    public TaskToDoList removeComment(TaskToDoListComment taskToDoListComment) {
        this.comments.remove(taskToDoListComment);
        taskToDoListComment.setTaskToDoList(null);
        return this;
    }

    public void setComments(Set<TaskToDoListComment> taskToDoListComments) {
        this.comments = taskToDoListComments;
    }

    public LookupValue getStatusType() {
        return statusType;
    }

    public TaskToDoList statusType(LookupValue lookupValue) {
        this.statusType = lookupValue;
        return this;
    }

    public void setStatusType(LookupValue lookupValue) {
        this.statusType = lookupValue;
    }

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public TaskToDoList taskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
        return this;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
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
        TaskToDoList taskToDoList = (TaskToDoList) o;
        if (taskToDoList.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskToDoList.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
