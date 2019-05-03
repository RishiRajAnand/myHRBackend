package com.erx.microservice.patientmanagement.repository.therapymanagement;

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyInstanceState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TherapyInstanceStateRepository extends JpaRepository<TherapyInstanceState,Long> {
    @Query("select TIS from TherapyInstanceState TIS where TIS.paymentPolicy = ?1 and TIS.wellness=?2 " +
            "and TIS.recordStatus = 1")
    List<TherapyInstanceState> getInstanceStateByPaymentPolicyAndWellness(String paymentPolicy, boolean isWellness);
}
