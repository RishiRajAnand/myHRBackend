package com.erx.microservice.patientmanagement.domain.taskmanagement;

import com.erx.microservice.patientmanagement.domain.BaseModel;
import com.erx.microservice.patientmanagement.domain.ClinicLocation;
import com.erx.microservice.patientmanagement.domain.LookupValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
/**
 * A TaskCategory.
 */
@Entity
@Table(name = "task_category")
public class TaskCategory extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "taskCategory", fetch = FetchType.EAGER)
    private Set<TaskToDoList> toDoLists = new HashSet<>();

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<TaskCategory> children = new HashSet<>();
//    @ManyToOne
//    @JsonIgnoreProperties("")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_type_id", nullable = false)
    private LookupValue categoryType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "context_type_id", nullable = false)
    private LookupValue contextType;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TaskCategory parent;

    @ManyToOne
    @JsonIgnoreProperties("taskCategories")
    private ClinicLocation clinicLocation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public TaskCategory code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public TaskCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TaskToDoList> getToDoLists() {
        return toDoLists;
    }

    public TaskCategory toDoLists(Set<TaskToDoList> taskToDoLists) {
        this.toDoLists = taskToDoLists;
        return this;
    }

    public TaskCategory addToDoList(TaskToDoList taskToDoList) {
        this.toDoLists.add(taskToDoList);
        taskToDoList.setTaskCategory(this);
        return this;
    }

    public TaskCategory removeToDoList(TaskToDoList taskToDoList) {
        this.toDoLists.remove(taskToDoList);
        taskToDoList.setTaskCategory(null);
        return this;
    }

    public void setToDoLists(Set<TaskToDoList> taskToDoLists) {
        this.toDoLists = taskToDoLists;
    }

    public Set<TaskCategory> getChildren() {
        return children;
    }

    public TaskCategory children(Set<TaskCategory> taskCategories) {
        this.children = taskCategories;
        return this;
    }

    public TaskCategory addChildren(TaskCategory taskCategory) {
        this.children.add(taskCategory);
        taskCategory.setParent(this);
        return this;
    }

    public TaskCategory removeChildren(TaskCategory taskCategory) {
        this.children.remove(taskCategory);
        taskCategory.setParent(null);
        return this;
    }

    public void setChildren(Set<TaskCategory> taskCategories) {
        this.children = taskCategories;
    }

    public LookupValue getCategoryType() {
        return categoryType;
    }

    public TaskCategory categoryType(LookupValue lookupValue) {
        this.categoryType = lookupValue;
        return this;
    }

    public void setCategoryType(LookupValue lookupValue) {
        this.categoryType = lookupValue;
    }

    public LookupValue getContextType() {
        return contextType;
    }

    public TaskCategory contextType(LookupValue lookupValue) {
        this.contextType = lookupValue;
        return this;
    }

    public void setContextType(LookupValue lookupValue) {
        this.contextType = lookupValue;
    }

    public TaskCategory getParent() {
        return parent;
    }

    public TaskCategory parent(TaskCategory taskCategory) {
        this.parent = taskCategory;
        return this;
    }

    public void setParent(TaskCategory taskCategory) {
        this.parent = taskCategory;
    }

    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }

    public TaskCategory clinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
        return this;
    }

    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
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
        TaskCategory taskCategory = (TaskCategory) o;
        if (taskCategory.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskCategory.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
