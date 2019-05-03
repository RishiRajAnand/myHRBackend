package com.erx.microservice.patientmanagement.repository;

/*
* created by Latha on 05-11-17
* */

import com.erx.microservice.patientmanagement.domain.UserStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStaffRepository extends JpaRepository<UserStaff, Long> {

    @Query(" SELECT u FROM UserStaff u " +
            " INNER JOIN u.clinic c " +
            " where u.email = ?1 and c.name = ?2 ")
    List<UserStaff> clinicAdminExist(String clinicEmailId, String clinicName);

    @Query("select CONCAT(us.firstName,' ',us.lastName)  from UserStaff us where us.id=?1 and us.recordStatus=1")
    String findUserNameById(Long userId);
}
