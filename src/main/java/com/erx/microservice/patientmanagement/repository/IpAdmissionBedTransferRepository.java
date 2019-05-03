package com.erx.microservice.patientmanagement.repository;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.domain.IpAdmissionBedTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IpAdmissionBedTransferRepository extends JpaRepository<IpAdmissionBedTransfer, Long> {

    @Query("select ibt from IpAdmissionBedTransfer ibt where ibt.ipAdmission.id = ?1 and ibt.recordStatus = 1 ORDER BY createdOn ASC")
    List<IpAdmissionBedTransfer> getBedTransferDetails(Long ipAdmissionID);

    @Query("select ibt from IpAdmissionBedTransfer ibt where ibt.ipAdmission.id = ?1 " +
            " and ibt.createdOn=(SELECT MAX(ibt.createdOn) FROM IpAdmissionBedTransfer ibt )")
            IpAdmissionBedTransfer getBedTransferDetailsIpAdmissionId(Long ipAdmissionID);

    @Query("select ibt from IpAdmissionBedTransfer ibt  " +
            "where ibt.ipAdmission.id = ?1 and ibt.createdOn>?2 and  ibt.createdOn<?3 and ibt.recordStatus = 1 ORDER BY createdOn ASC")
    List<IpAdmissionBedTransfer> getIpAdmissionBedTransfersByIpAdmissionIdAndByCreatedOn(Long ipAdmissionID, LocalDateTime lastCutOffTime,LocalDateTime cutOffTime);


    
}
