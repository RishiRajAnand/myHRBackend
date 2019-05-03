package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 29-11-2017
* */

import com.erx.microservice.patientmanagement.domain.IpAdmissionCaseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IpAdmissionCaseMappingRepository extends JpaRepository<IpAdmissionCaseMapping, Long> {

    @Query("select ipcm from IpAdmissionCaseMapping ipcm where ipcm.ipAdmission.id = ?1 and ipcm.recordStatus = 1")
    IpAdmissionCaseMapping getIpCaseDetails(Long ipAdmissionId);

    @Query("select ipcm from IpAdmissionCaseMapping ipcm where ipcm.ipAdmission.id = ?1 and ipcm.cmMaster.id = ?2 and ipcm.recordStatus = 1")
    IpAdmissionCaseMapping getIpCaseDetailsByCaseId(Long ipAdmissionId, Long cmMasterId);

    @Query("select ipcm from IpAdmissionCaseMapping ipcm where ipcm.cmMaster.id = ?1 and ipcm.recordStatus = 1")
    IpAdmissionCaseMapping getIpDetailsByCaseId(Long cmMasterId);
}
