package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.domain.UserDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDepartmentRepository extends JpaRepository<UserDepartment, Long> {

    @Query("select ud from UserDepartment ud where ud.department.id = ?1 and " +
            "ud.recordStatus = 1 and ud.userStaff.userDetail.isDoctor = true")
    List<UserDepartment> getDoctorForDepartment(Long departmentId);


    @Query("select ud from UserDepartment ud where ud.userStaff.id = ?1 and " +
            "ud.recordStatus = 1 and ud.userStaff.userDetail.isDoctor = true")
    List<UserDepartment> getDepartmentByDoctor(Long doctorId);
}
