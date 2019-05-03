package com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker;

/**
 * Created by latha on 06/12/18.
 */

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.service.dto.DischargeRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DischargeRequestTrackerFactory {

    public List<DischargeRequestDTO> dischargeRequestSearch(PatientSearchCriteria patientSearchCriteria, Pageable pageable);

}
