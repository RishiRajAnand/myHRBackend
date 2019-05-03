package com.erx.microservice.patientmanagement.repository.casemanagement;

/*
* created by Latha on 17-09-2018
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.UploadedDocumentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedDocumentDetailRepository extends JpaRepository<UploadedDocumentDetail, Long>{
}
