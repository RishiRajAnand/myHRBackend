package com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker.ipadmissionrequesttrackerbycliniclocation;

/**
 * Created by Brighty on 11-06-2018.
 */

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IpAdmissionRequestTrackerByClinicLocation {

    List<IpAdmissionRequestDTO> getIpRequestSearch(PatientSearchCriteria patientSearchCriteria, Pageable pageable);
}
