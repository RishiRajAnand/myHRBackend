package com.erx.microservice.patientmanagement.domain;

/*
* created by Brighty on 29-11-2017.
* */

import javax.persistence.*;


@Entity
@Table(name = "user_department")
public class UserDepartment extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentMaster department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserStaff userStaff;

    //getters and setters
    public UserStaff getUserStaff() {
        return userStaff;
    }

    public void setUserStaff(UserStaff userStaff) {
        this.userStaff = userStaff;
    }

    public DepartmentMaster getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentMaster department) {
        this.department = department;
    }

}
