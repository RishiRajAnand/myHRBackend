package com.erx.microservice.patientmanagement.repository;

/*
* created by Latha on 29-11-2017
* */


import com.erx.microservice.patientmanagement.domain.IpAdmissionBedMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IpAdmissionBedMovementRepository extends JpaRepository<IpAdmissionBedMovement, Long> {

    @Query("select ibm from IpAdmissionBedMovement ibm where ibm.ipAdmission.id = ?1 and ibm.recordStatus = 1")
    List<IpAdmissionBedMovement> getBedMovementDetails(Long ipAdmissionID);


    @Query("select ibm from IpAdmissionBedMovement ibm where ibm.ipAdmission.id = ?1 and ibm.recordStatus = 1 " +
            "and ibm.currentBed = 1")
    List<IpAdmissionBedMovement> getBedMovementByCurrentBed(Long ipAdmissionID);

    @Transactional
    @Modifying
    @Query("update IpAdmissionBedMovement ibm set ibm.currentBed=false where ibm in(?1) ")
    void updateCurrentBedStatusByipAdmissionBedMovementIds(List<IpAdmissionBedMovement> ipAdmissionBedMovements);
}
