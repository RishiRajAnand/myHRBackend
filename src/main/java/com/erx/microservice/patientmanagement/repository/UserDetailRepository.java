package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.domain.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    @Query("select ud from UserDetail ud where ud.userStaff.id = ?1 and ud.clinic.id = ?2 and ud.isDoctor=1")
    UserDetail findDoctorByUserId(Long userId, Long clinicId);


    @Query("select ud from UserDetail ud where ud.id = ?1 and ud.recordStatus=1")
    UserDetail findById(Long userDetailId);
}
