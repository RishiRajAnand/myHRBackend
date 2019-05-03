package com.erx.microservice.patientmanagement.service.iprequest.ipadmissionrequesttracker.ipadmissionrequesttrackerbycliniclocation;

/**
 * Created by Brighty on 11-06-2018.
 * updated by Manisha on 19-09-2018
 */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.repository.UserStaffRepository;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchUtil.IP_ADMISSION_REQUEST_BASE_QUERY;

@Service("ipAdmissionRequestTrackerByClinicLocation")
public class IpAdmissionRequestTrackerByClinicLocationImpl implements IpAdmissionRequestTrackerByClinicLocation {

    private final Logger log = LoggerFactory.getLogger(IpAdmissionRequestTrackerByClinicLocationImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserStaffRepository userStaffRepository;

    @Override
    public List<IpAdmissionRequestDTO> getIpRequestSearch(PatientSearchCriteria patientSearchCriteria, Pageable pageable) {
        List<IpAdmissionRequestDTO> ipAdmissionRequestDTOs = new ArrayList<>();
        try {
            log.debug("IpAdmissionRequestTrackerByClinicLocationImpl ==> Call to retrieve IpAdmissionRequest by Clinic Location");
            //retrieve the IpRequest
            String whereClause = new String();
            if (patientSearchCriteria.getClinicId() != null & patientSearchCriteria.getSearchType() != null
                    & patientSearchCriteria.getSearchValue() != null) {
                whereClause = " where ipr.recordStatus = 1 and p.registered = TRUE and cl.id = " + patientSearchCriteria.getClinicLocationId()
                        + " ORDER BY ipr.createdOn DESC";
                Query query = entityManager.createQuery(IP_ADMISSION_REQUEST_BASE_QUERY + " " + whereClause);
                List<Object> results = query.getResultList();
                for (Object ipRequestObject : results) {
                    IpAdmissionRequestDTO ipAdmissionRequestDTO = new IpAdmissionRequestDTO();
                    IpAdmissionRequest ipAdmissionRequest = (IpAdmissionRequest) ipRequestObject;
                    Patient patient = ipAdmissionRequest.getCmMaster().getPatient();
                    IpAdmission ipAdmission = ipAdmissionRequest.getIpAdmission();
                    ipAdmissionRequestDTO.setIpAdmissionRequestId(ipAdmissionRequest.getId());
                    ipAdmissionRequestDTO.setPatientId(patient.getId());
                    ipAdmissionRequestDTO.setPatientName(patient.getPatientName());
                    ipAdmissionRequestDTO.setPatientMrn(patient.getPatientIdExternal());
                    ipAdmissionRequestDTO.setIpRequestNumber(ipAdmissionRequest.getIpRequestNumber());
                    ipAdmissionRequestDTO.setCmMasterId(ipAdmissionRequest.getCmMaster().getId());
                    if (ipAdmission != null) {
                        ipAdmissionRequestDTO.setIpAdmissionId(ipAdmission.getId());
                        if (ipAdmission.getIpAdmissionNumber() != null)
                            ipAdmissionRequestDTO.setIpAdmissionNumber(ipAdmission.getIpAdmissionNumber());
                        else if (ipAdmission.getDayCareAdmissionNumber() != null)
                            ipAdmissionRequestDTO.setIpAdmissionNumber(ipAdmission.getDayCareAdmissionNumber());
                    }
                    ipAdmissionRequestDTO.setRequestCreatedDate(String.valueOf(ipAdmissionRequest.getCreatedOn()));
                    ipAdmissionRequestDTO.setIpAdmissionDate(String.valueOf(ipAdmissionRequest.getIpAdmissionDate()));
                    UserStaff userStaff = userStaffRepository.findOne(ipAdmissionRequest.getCreatedBy());
                    if (userStaff != null) {
                        ipAdmissionRequestDTO.setRequestedBy(userStaff.getFirstName() + " " + userStaff.getLastName());
                    }
                    //ad to list
                    ipAdmissionRequestDTOs.add(ipAdmissionRequestDTO);
                }
                log.debug("IpAdmissionRequestTrackerByClinicLocationImpl ==> SUCCESS");
            }
        } catch (Exception e) {
            log.error("IpAdmissionRequestTrackerByClinicLocationImpl ==> FAILURE : " + e.getMessage());
        }
        return ipAdmissionRequestDTOs;
    }
}
