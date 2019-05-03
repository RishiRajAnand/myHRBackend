package com.erx.microservice.patientmanagement.service.patientsearch.patientbyvisitid;

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
 * Created by Latha on 03/01/18.
 */

@Service("patientSearchQueryBuilderByVisit")
public class PatientSearchQueryBuilderByVisitImpl implements PatientSearchQueryBuilderByVisit {

    private final Logger log = LoggerFactory.getLogger(PatientSearchQueryBuilderByVisitImpl.class);

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
        List<Object[]> patientFinalDetails = null;
        List<PatientAppointment> patientAppointments = new ArrayList<>();
        List<PatientAppointmentDTO> patientAppointmentDTOs = new ArrayList<>();
        try {
            log.debug("Call to search patient by " + patientSearchCriteria.getSearchType() + " for the value " + patientSearchCriteria.getSearchValue());
            String whereClause = new String();
            //create patientSearchDTO object
            PatientSearchDTO patientSearchDTO = new PatientSearchDTO();
            if (patientSearchCriteria.getClinicId() != null & patientSearchCriteria.getSearchType() != null & patientSearchCriteria.getSearchValue() != null) {
                patientAppointments = entityManager.createQuery("select pa from PatientAppointment pa where pa.generatedPatId = '" + patientSearchCriteria.getSearchValue() + "' and pa.recordStatus = 1").getResultList();
                //set the values
                if (patientAppointments.size() != 0) {
                    for (PatientAppointment patientAppointmentDetails : patientAppointments) {
                        patientSearchDTO.setPatientId(patientAppointmentDetails.getPatient().getId());
                        patientSearchDTO.setPatientMRN(patientAppointmentDetails.getPatient().getPatientIdExternal());
                        patientSearchDTO.setPatientName(patientAppointmentDetails.getPatient().getPatientName());
                        patientSearchDTO.setGender(patientAppointmentDetails.getPatient().getGender());
                        if (patient.getPatientSalutation()!=null)
                            patientSearchDTO.setPatientSalutation(patient.getPatientSalutation().getName());
                        PatientAppointmentDTO patientAppointmentDTO = new PatientAppointmentDTO();
                        patientAppointmentDTO.setVisitId(patientAppointmentDetails.getGeneratedPatId());
                        patientAppointmentDTO.setVistedDate(patientAppointmentDetails.getUpdatedOn());
                        //add to list
                        patientAppointmentDTOs.add(patientAppointmentDTO);
                        patientSearchDTO.setPatientAppointmentDTOs(patientAppointmentDTOs);
                    }
                    //add to list
                    patientSearchDTOs.add(patientSearchDTO);
                } else {
                    whereClause = " where p.recordStatus= 1 and NOT p.patientIdExternal=null and p.clinic.id= " + patientSearchCriteria.getClinicId() + " and ip.ipAdmissionNumber = '" + patientSearchCriteria.getSearchValue() + "'";
                    List<Object[]> patientIPResults = entityManager.createQuery(PATIENT_DETAIL_BASE_QUERY + " " + whereClause).getResultList();
                    if (patientIPResults.size() != 0) {
                        patientSearchDTO = getPatientResults(patientIPResults, patientSearchCriteria);
                        //add to list
                        patientSearchDTOs.add(patientSearchDTO);
                    } else {
                        whereClause = " where p.recordStatus= 1 and NOT p.patientIdExternal=null and p.clinic.id= " + patientSearchCriteria.getClinicId() + " and ip.dayCareAdmissionNumber = '" + patientSearchCriteria.getSearchValue() + "'";
                        List<Object[]> patientDCResults = entityManager.createQuery(PATIENT_DETAIL_BASE_QUERY + " " + whereClause).getResultList();
                        if (patientDCResults.size() != 0) {
                            patientSearchDTO = getPatientResults(patientDCResults, patientSearchCriteria);
                            //add to list
                            patientSearchDTOs.add(patientSearchDTO);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientSearchDTOs;
    }

    // method to from PatientSearchDTO
    private PatientSearchDTO getPatientResults(List<Object[]> patientIPResults, PatientSearchCriteria patientSearchCriteria) {
        Patient patient = new Patient();
        VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
        IpAdmission ipAdmission = new IpAdmission();
        List<UserDepartment> userDepartment = null;
        IpAdmissionBedMovement ipAdmissionBedMovement = new IpAdmissionBedMovement();
        PatientAdditionalDetail patientAdditionalDetail = new PatientAdditionalDetail();
        //create patientSearchDTO object
        PatientSearchDTO patientSearchDTO = new PatientSearchDTO();
        for (Object[] ipAdmissionDetail : patientIPResults) {
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
            patientSearchDTO.setPatientMRN(patient.getPatientIdExternal());
            patientSearchDTO.setPatientName(patient.getPatientName());
            patientSearchDTO.setGender(patient.getGender());
            patientSearchDTO.setMobileNumber(patient.getMobileNumber());
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
        }
        return patientSearchDTO;
    }

    private VisitTypeMaster getVisitTypeMaster(Long visitTypeMasterId) {
        return visitTypeMasterRepository.findOne(visitTypeMasterId);
    }
}
