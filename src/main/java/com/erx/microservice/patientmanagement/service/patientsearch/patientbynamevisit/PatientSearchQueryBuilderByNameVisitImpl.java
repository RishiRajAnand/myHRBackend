package com.erx.microservice.patientmanagement.service.patientsearch.patientbynamevisit;

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.repository.PatientAdditionalDetailRepository;
import com.erx.microservice.patientmanagement.repository.PatientAppointmentRepository;
import com.erx.microservice.patientmanagement.repository.UserDepartmentRepository;
import com.erx.microservice.patientmanagement.repository.VisitTypeMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.PatientAppointmentDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientSearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static com.erx.microservice.patientmanagement.service.patientsearch.PatientSearchUtil.PATIENT_DETAIL_BASE_QUERY;

/**
 * Created by raushan on 14/02/18.
 */

@Service("patientSearchQueryBuilderByNameVisit")
public class PatientSearchQueryBuilderByNameVisitImpl implements PatientSearchQueryBuilderByNameVisit {

    private final Logger log = LoggerFactory.getLogger(PatientSearchQueryBuilderByNameVisitImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserDepartmentRepository userDepartmentRepository;

    @Autowired
    private PatientAppointmentRepository patientAppointmentRepository;

    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;

    @Autowired
    private PatientAdditionalDetailRepository patientAdditionalDetailRepository;

    @Override
    public List<PatientSearchDTO> getPatientSearchResults(PatientSearchCriteria patientSearchCriteria) {
        Patient patient = new Patient();
        IpAdmission ipAdmission = new IpAdmission();
        List<PatientSearchDTO> patientSearchDTOs = new ArrayList<>();
        IpAdmissionBedMovement ipAdmissionBedMovement = new IpAdmissionBedMovement();
        List<UserDepartment> userDepartment = null;
        List<Object[]> patientFinalDetails = null;
        List<PatientAppointment> patientAppointments = new ArrayList<>();
        List<PatientAppointmentDTO> patientAppointmentDTOs = new ArrayList<>();
        VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
        PatientAdditionalDetail patientAdditionalDetail = new PatientAdditionalDetail();
        try {
            log.debug("Call to search patient by " + patientSearchCriteria.getSearchType() + " for the value " + patientSearchCriteria.getSearchValue());
            String whereClause = new String();
            if (patientSearchCriteria.getClinicId() != null & patientSearchCriteria.getSearchType() != null & patientSearchCriteria.getSearchValue() != null) {
                whereClause = " where p.recordStatus= 1 and p.registered = TRUE and NOT p.patientIdExternal=null and p.clinic.id= " +
                        patientSearchCriteria.getClinicId() + " and p.patientName like '%" + patientSearchCriteria.getSearchValue() + "%' ";
                List<Object[]> patientResults = entityManager.createQuery(PATIENT_DETAIL_BASE_QUERY + " " + whereClause).getResultList();

                for (Object[] ipAdmissionDetail : patientResults) {
                    //create patientSearchDTO object
                    PatientSearchDTO patientSearchDTO = new PatientSearchDTO();
                    patient = (Patient) ipAdmissionDetail[0];
                    patientAdditionalDetail = patientAdditionalDetailRepository.findPatientTypeByPatient(patient.getId());
                    if (ipAdmissionDetail[1] != null) {
                        ipAdmission = (IpAdmission) ipAdmissionDetail[1];

                        patientSearchDTO.setIpAdmissionId(ipAdmission.getId());
                        patientSearchDTO.setIpAdmissionNumber(ipAdmission.getIpAdmissionNumber());
                        patientSearchDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                        if (ipAdmission.getUserDetail() != null) {
                            if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                patientSearchDTO.setDoctorId(ipAdmission.getUserDetail().getId());
                                patientSearchDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName() + " " + ipAdmission.getUserDetail().getUserStaff().getLastName());
                                userDepartment = userDepartmentRepository.getDepartmentByDoctor(ipAdmission.getUserDetail().getUserStaff().getId());
                                if (userDepartment != null) {
                                    for (UserDepartment userDepartment1 : userDepartment) {
                                        patientSearchDTO.setDepartmentId(userDepartment1.getDepartment().getId());
                                        patientSearchDTO.setDepartmentName(userDepartment1.getDepartment().getDepartmentName());
                                    }
                                }
                            }
                        } else {
                            patientSearchDTO.setDoctorName("-");
                        }
                        if (!(ipAdmission.getIpAdmissionNumber().equals(""))) {
                            patientSearchDTO.setVisitId(ipAdmission.getIpAdmissionNumber());
                            if (ipAdmission.getVisitTypeMasterId() != null) {
                                visitTypeMaster = getVisitTypeMaster(ipAdmission.getVisitTypeMasterId());
                                patientSearchDTO.setVisitType(visitTypeMaster.getVisitType());
                                patientSearchDTO.setVisitTypeMasterId(visitTypeMaster.getId());
                            }
                        } else if (ipAdmission.getDayCareAdmissionNumber() != null) {
                            patientSearchDTO.setVisitId(ipAdmission.getDayCareAdmissionNumber());
                            if (ipAdmission.getVisitTypeMasterId() != null) {
                                visitTypeMaster = getVisitTypeMaster(ipAdmission.getVisitTypeMasterId());
                                patientSearchDTO.setVisitType(visitTypeMaster.getVisitType());
                                patientSearchDTO.setVisitTypeMasterId(visitTypeMaster.getId());
                            }
                        } else {
                            patientSearchDTO.setVisitId("_");
                            patientSearchDTO.setVisitType("_");
                        }
                        patientSearchDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                        patientSearchDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                        patientSearchDTO.setBedTypeId(ipAdmission.getBedMaster().getBedTypeMaster().getId());
                        patientSearchDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                        patientSearchDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                        patientSearchDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                        if (ipAdmissionDetail[2] != null) {
                            ipAdmissionBedMovement = (IpAdmissionBedMovement) ipAdmissionDetail[2];
                            patientSearchDTO.setIpAdmissionBedMovementId(ipAdmissionBedMovement.getId());
                            patientSearchDTO.setBedMovedToId(ipAdmissionBedMovement.getBedMovedTo().getId());
                        }
                        patientSearchDTO.setIpAdmissionNotes(ipAdmission.getIpAdmissionNotes());
                    }
                    //set the values
                    patientSearchDTO.setPatientId(patient.getId());
                    patientAppointments = patientAppointmentRepository.findAllAppointmentsByPatient(patient.getId());
                    if (patientAppointments != null) {
                        for (PatientAppointment patientAppointmentDetails : patientAppointments) {
                            PatientAppointmentDTO patientAppointmentDTO = new PatientAppointmentDTO();
                            patientAppointmentDTO.setVisitId(patientAppointmentDetails.getGeneratedPatId());
                            patientAppointmentDTO.setVistedDate(patientAppointmentDetails.getUpdatedOn());
                            //add to list
                            patientAppointmentDTOs.add(patientAppointmentDTO);
                            patientSearchDTO.setPatientAppointmentDTOs(patientAppointmentDTOs);
                        }

                    }
                    patientSearchDTO.setPatientMRN(patient.getPatientIdExternal());
                    patientSearchDTO.setPatientName(patient.getPatientName());
                    patientSearchDTO.setGender(patient.getGender());
                    patientSearchDTO.setMobileNumber(patient.getMobileNumber());
                    if (patient.getPatientSalutation()!=null)
                        patientSearchDTO.setPatientSalutation(patient.getPatientSalutation().getName());
                    if (patientAdditionalDetail != null && patientAdditionalDetail.getPatientType() != null) {
                        patientSearchDTO.setPatientTypeId(patientAdditionalDetail.getPatientType().getId());
                        patientSearchDTO.setPatientType(patientAdditionalDetail.getPatientType().getPatientTypeName());
                    }
                    if(ipAdmissionDetail[1] == null) {
                        List<VisitTypeMaster> visitTypeMasters = visitTypeMasterRepository.getVisitTypeMasterByClinicLocation(patientSearchCriteria.getClinicLocationId());
                        if (visitTypeMasters != null) {
                            for (VisitTypeMaster typeMaster : visitTypeMasters) {
                                if (typeMaster.getVisitType().equalsIgnoreCase("OP")) {
                                    patientSearchDTO.setVisitTypeMasterId(typeMaster.getId());
                                    patientSearchDTO.setVisitType(typeMaster.getVisitType());
                                }
                            }
                        }
                    }
                    LocalDateTime now = LocalDateTime.now();
                    int age = Period.between(patient.getDateOfBirth(), now.toLocalDate()).getYears();
                    patientSearchDTO.setAge(age);
                    //add to list
                    patientSearchDTOs.add(patientSearchDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientSearchDTOs;
    }

    private VisitTypeMaster getVisitTypeMaster(Long visitTypeMasterId) {
        return visitTypeMasterRepository.findOne(visitTypeMasterId);
    }
}
