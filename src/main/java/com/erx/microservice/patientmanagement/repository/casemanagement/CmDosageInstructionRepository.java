package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
created by Latha on 24-08-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmDosageInstruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmDosageInstructionRepository extends JpaRepository<CmDosageInstruction,Long> {
}
