package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 07-12-17
* */

import com.erx.microservice.patientmanagement.domain.AddressInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressInfoRepository extends JpaRepository<AddressInfo, Long> {
}
