package com.erx.microservice.patientmanagement.service.dischargerequest.dischargerequesttracker.dischargerequesttrackerbymrn;

/**
 * Created by latha on 06/12/18.
 */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmDischargeRequest;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.repository.UserStaffRepository;
import com.erx.microservice.patientmanagement.repository.VisitTypeMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.DischargeRequestDTO;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
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

import static com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchUtil.DISCHARGE_REQUEST_BASE_QUERY;

@Service("dischargeRequestTrackerByMRN")
public class DischargeRequestTrackerByMRNImpl implements DischargeRequestTrackerByMRN {

    private final Logger log = LoggerFactory.getLogger(DischargeRequestTrackerByMRNImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;

    @Override
    public List<DischargeRequestDTO> getDischargeRequestSearch(PatientSearchCriteria patientSearchCriteria, Pageable pageable) {
        List<DischargeRequestDTO> dischargeRequestDTOs = new ArrayList<>();
        try {
            log.debug("DischargeRequestTrackerByClinicLocationImpl ==> Call to retrieve DischargeRequest by MRN");
            //retrieve the Discharge Request
            String whereClause = new String();
            if (patientSearchCriteria.getClinicId() != null & patientSearchCriteria.getSearchType() != null
                    & patientSearchCriteria.getSearchValue() != null) {
                whereClause = " where dr.recordStatus = 1 and cl.id= " + patientSearchCriteria.getClinicLocationId()
                        + " and p.patientIdExternal = '" + patientSearchCriteria.getSearchValue() + "' ORDER BY dr.createdOn DESC";
                Query query = entityManager.createQuery(DISCHARGE_REQUEST_BASE_QUERY + " " + whereClause);
                List<Object> results = query.getResultList();
                for (Object dischargeRequestObject : results) {
                    DischargeRequestDTO dischargeRequestDTO = new DischargeRequestDTO();
                    CmDischargeRequest cmDischargeRequest = (CmDischargeRequest) dischargeRequestObject;
                    Patient patient = cmDischargeRequest.getCmMaster().getPatient();
                    IpAdmission ipAdmission = ipAdmissionRepository.findByPatientId(patient.getId());
                    dischargeRequestDTO.setDischargeRequestId(cmDischargeRequest.getId());
                    dischargeRequestDTO.setDischargeRequestDate(cmDischargeRequest.getDischargeRequestDate());
                    cmDischargeRequest.setDischargeRequestTime(cmDischargeRequest.getDischargeRequestTime());
                    dischargeRequestDTO.setPatientId(patient.getId());
                    dischargeRequestDTO.setPatientName(patient.getPatientName());
                    dischargeRequestDTO.setPatientMRN(patient.getPatientIdExternal());
                    dischargeRequestDTO.setMobileNumber(patient.getMobileNumber());
                    dischargeRequestDTO.setGender(patient.getGender());
                    dischargeRequestDTO.setDischargeRequestNumber(cmDischargeRequest.getDischargeRequestNumber());
                    dischargeRequestDTO.setCmMasterId(cmDischargeRequest.getCmMaster().getId());
                    if (ipAdmission != null) {
                        dischargeRequestDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                        dischargeRequestDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                        dischargeRequestDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                        dischargeRequestDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                        dischargeRequestDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                        dischargeRequestDTO.setIpAdmissionId(ipAdmission.getId());
                        dischargeRequestDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                        if (ipAdmission.getIpAdmissionNumber() != null)
                            dischargeRequestDTO.setIpAdmissionNumber(ipAdmission.getIpAdmissionNumber());
                        else if (ipAdmission.getDayCareAdmissionNumber() != null)
                            dischargeRequestDTO.setIpAdmissionNumber(ipAdmission.getDayCareAdmissionNumber());
                        if (ipAdmission.getVisitTypeMasterId() != null) {
                            String visitType = visitTypeMasterRepository.findOne(ipAdmission.getVisitTypeMasterId()).getVisitType();
                            dischargeRequestDTO.setVisitType(visitType);
                        }
                        dischargeRequestDTO.setIpAdmissionStatus(PatientConstants.IP_STATUS_ADMITTED);
                    }
                    //add to list
                    dischargeRequestDTOs.add(dischargeRequestDTO);
                }
                log.debug("DischargeRequestTrackerByClinicLocationImpl ==> SUCCESS");
            }
        } catch (Exception e) {
            log.error("DischargeRequestTrackerByClinicLocationImpl ==> FAILURE : " + e.getMessage());
        }
        return dischargeRequestDTOs;
    }
}
