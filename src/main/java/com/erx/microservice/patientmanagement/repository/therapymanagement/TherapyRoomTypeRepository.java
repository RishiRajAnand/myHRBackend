package com.erx.microservice.patientmanagement.repository.therapymanagement;

/*
* created by Latha on 07-09-2018
* */

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyRoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TherapyRoomTypeRepository extends JpaRepository<TherapyRoomType,Long>{
}
