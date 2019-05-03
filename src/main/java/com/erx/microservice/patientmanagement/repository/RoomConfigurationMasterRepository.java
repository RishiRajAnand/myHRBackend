package com.erx.microservice.patientmanagement.repository;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.domain.RoomConfigurationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomConfigurationMasterRepository extends JpaRepository<RoomConfigurationMaster, Long> {

    @Query("select rcm from RoomConfigurationMaster rcm where rcm.clinicLocationId = ?1 and rcm.recordStatus = 1")
    List<RoomConfigurationMaster> getRoomConfigurationMastersByClinicLocation(Long clinicLocationId);

    List<RoomConfigurationMaster> findRoomConfigurationMastersByClinicLocationIdAndStatus(Long clinicLocationId, Boolean activeStatus);
    @Query("select rcm from RoomConfigurationMaster rcm where rcm.clinicLocationId = ?1 " +
            "and rcm.roomNumber = ?2 and rcm.recordStatus = 1")
    RoomConfigurationMaster validateRoomNumber(Long clinicLocationId, String roomNumber);

    Optional<RoomConfigurationMaster> findFirst1ByClinicLocationIdAndRoomNumber(Long clinicLocationId, String roomNumber);
}

