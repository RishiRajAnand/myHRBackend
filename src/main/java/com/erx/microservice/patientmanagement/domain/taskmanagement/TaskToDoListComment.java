package com.erx.microservice.patientmanagement.domain.taskmanagement;

import com.erx.microservice.patientmanagement.domain.BaseModel;

import javax.persistence.*;
import java.util.Objects;

/**
 * A TaskToDoListComment.
 */
@Entity
@Table(name = "task_to_do_list_comment")
public class TaskToDoListComment extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

//    @ManyToOne
//    @JsonIgnoreProperties("comments")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_to_do_list_id", nullable = true)
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

    public TaskToDoListComment description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskToDoList getTaskToDoList() {
        return taskToDoList;
    }

    public TaskToDoListComment taskToDoList(TaskToDoList taskToDoList) {
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
        TaskToDoListComment taskToDoListComment = (TaskToDoListComment) o;
        if (taskToDoListComment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskToDoListComment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
