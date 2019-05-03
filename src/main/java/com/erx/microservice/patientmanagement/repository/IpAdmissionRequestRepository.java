package com.erx.microservice.patientmanagement.repository;

/*
* created by Brighty on 08-06-2018
* */

import com.erx.microservice.patientmanagement.domain.IpAdmissionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpAdmissionRequestRepository extends JpaRepository<IpAdmissionRequest, Long> {
}
