package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 06-10-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmExamntnAsthaVidhaPareeksha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmExamntnAsthaVidhaPareekshaRepository extends JpaRepository<CmExamntnAsthaVidhaPareeksha,Long>{
}
