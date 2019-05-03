package com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker;

import com.erx.microservice.patientmanagement.domain.PatientSearchCriteria;
import com.erx.microservice.patientmanagement.domain.PatientSearchTypes;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionRequestDTO;
import com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker.ipadmissionrequesttrackerbycliniclocation.IpAdmissionRequestTrackerByClinicLocation;
import com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker.ipadmissionrequesttrackerbymobile.IpAdmissionRequestTrackerByMobile;
import com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker.ipadmissionrequesttrackerbymrn.IpAdmissionRequestTrackerByMRN;
import com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker.ipadmissionrequesttrackerbyname.IpAdmissionRequestTrackerByName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Brighty on 11-06-2018.
 */

@Service("ipAdmissionRequestTrackerFactory")
public class IpAdmissionRequestTrackerFactoryImpl implements IpAdmissionRequestTrackerFactory {

    @Autowired
    private IpAdmissionRequestTrackerByMRN ipAdmissionRequestTrackerByMRN;

    @Autowired
    private IpAdmissionRequestTrackerByMobile ipAdmissionRequestTrackerByMobile;

    @Autowired
    private IpAdmissionRequestTrackerByName ipAdmissionRequestTrackerByName;

    @Autowired
    private IpAdmissionRequestTrackerByClinicLocation ipAdmissionRequestTrackerByClinicLocation;

    //use searchType to get respective query builders
    @Override
    public List<IpAdmissionRequestDTO> IpRequestSearch(PatientSearchCriteria patientSearchCriteria, Pageable pageable) {

        switch (PatientSearchTypes.valueOf(patientSearchCriteria.getSearchType())) {
            case MRN:
                return ipAdmissionRequestTrackerByMRN.getIpRequestSearch(patientSearchCriteria, pageable);
            case MOBILE:
                return ipAdmissionRequestTrackerByMobile.getIpRequestSearch(patientSearchCriteria, pageable);
            case NAME:
                return ipAdmissionRequestTrackerByName.getIpRequestSearch(patientSearchCriteria, pageable);
            case ALL:
                return ipAdmissionRequestTrackerByClinicLocation.getIpRequestSearch(patientSearchCriteria, pageable);

            default:
                return ipAdmissionRequestTrackerByClinicLocation.getIpRequestSearch(patientSearchCriteria, pageable);
        }

    }
}
