package com.erx.microservice.patientmanagement.domain;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created by mkpatil on 28/12/17.
 */
public class PatientSearchCriteria extends SearchCriteria {

    private Long patientCampRegistrationId;

    private boolean fetchNonRegistered;

    public Long getPatientCampRegistrationId() {
        return patientCampRegistrationId;
    }

    public void setPatientCampRegistrationId(Long patientCampRegistrationId) {
        this.patientCampRegistrationId = patientCampRegistrationId;
    }

    public boolean isFetchNonRegistered() {
        return fetchNonRegistered;
    }

    public void setFetchNonRegistered(boolean fetchNonRegistered) {
        this.fetchNonRegistered = fetchNonRegistered;
    }
}
