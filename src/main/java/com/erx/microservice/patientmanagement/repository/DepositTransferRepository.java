package com.erx.microservice.patientmanagement.repository;

/**
 * Created by brighty on 13-12-17.
 */



import com.erx.microservice.patientmanagement.domain.DepositTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositTransferRepository extends JpaRepository<DepositTransfer, Long> {

    @Query("select dt from DepositTransfer dt where dt.fromAccount.id = ?2 " +
            "and dt.patient.id = ?1")
    List<DepositTransfer> getDepositTransferByPatientAndAccountName(Long patientId, Long accountNameId);


    @Query("select dt from DepositTransfer dt " +
            "where dt.patient.id = ?1 and dt.fromAccount.visitType = ?2 and dt.recordStatus = 1 " +
            "or dt.patient.id = ?1 and dt.userStaff.firstName like %?2% and dt.recordStatus = 1 " +
            "or dt.patient.id = ?1 and dt.transferCode = ?2 and dt.recordStatus = 1 " +
            "or dt.patient.id = ?1 and dt.userStaff.lastName like %?2% and dt.recordStatus = 1 ORDER BY dt.updatedOn DESC")
    List<DepositTransfer> searchDepositTransfer(Long patientId, String searchValue);
}
