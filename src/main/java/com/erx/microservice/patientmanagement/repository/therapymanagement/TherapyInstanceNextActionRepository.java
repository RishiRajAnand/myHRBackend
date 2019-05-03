package com.erx.microservice.patientmanagement.repository.therapymanagement;

import com.erx.microservice.patientmanagement.domain.therapymanagement.TherapyInstanceNextAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TherapyInstanceNextActionRepository extends JpaRepository<TherapyInstanceNextAction,Long> {
}
