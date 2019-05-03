package com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.domain.PatientSearchTypes;
import com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker.dischargerequesttrackerbycliniclocation.DischargeRequestTrackerByClinicLocation;
import com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker.dischargerequesttrackerbymobile.DischargeRequestTrackerByMobile;
import com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker.dischargerequesttrackerbymrn.DischargeRequestTrackerByMRN;
import com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker.dischargerequesttrackerbyname.DischargeRequestTrackerByName;
import com.erx.microservice.patientmanagement.service.dto.DischargeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by latha on 06/12/18.
 */

@Service("dischargeRequestTrackerFactory")
public class DischargeRequestTrackerFactoryImpl implements DischargeRequestTrackerFactory {

    @Autowired
    private DischargeRequestTrackerByMRN dischargeRequestTrackerByMRN;

    @Autowired
    private DischargeRequestTrackerByMobile dischargeRequestTrackerByMobile;

    @Autowired
    private DischargeRequestTrackerByName dischargeRequestTrackerByName;

    @Autowired
    private DischargeRequestTrackerByClinicLocation dischargeRequestTrackerByClinicLocation;

    //use searchType to get respective query builders

    @Override
    public List<DischargeRequestDTO> dischargeRequestSearch(PatientSearchCriteria patientSearchCriteria, Pageable pageable) {

        switch (PatientSearchTypes.valueOf(patientSearchCriteria.getSearchType())) {
            case MRN:
                return dischargeRequestTrackerByMRN.getDischargeRequestSearch(patientSearchCriteria, pageable);
            case MOBILE:
                return dischargeRequestTrackerByMobile.getDischargeRequestSearch(patientSearchCriteria, pageable);
            case NAME:
                return dischargeRequestTrackerByName.getDischargeRequestSearch(patientSearchCriteria, pageable);
            case ALL:
                return dischargeRequestTrackerByClinicLocation.getDischargeRequestSearch(patientSearchCriteria, pageable);
            default:
                return dischargeRequestTrackerByClinicLocation.getDischargeRequestSearch(patientSearchCriteria, pageable);
        }

    }
}
