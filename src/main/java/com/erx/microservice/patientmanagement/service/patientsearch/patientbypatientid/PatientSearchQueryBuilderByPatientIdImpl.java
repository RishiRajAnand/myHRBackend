package com.erx.microservice.patientmanagement.service.patientsearch.patientbypatientid;

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.service.dto.PatientAppointmentDTO;
import com.erx.microservice.patientmanagement.service.dto.PatientSearchDTO;
import com.erx.microservice.patientmanagement.service.patientsearch.patientbymrn.PatientSearchQueryBuilderByMRNImpl;
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

@Service
public class PatientSearchQueryBuilderByPatientIdImpl implements PatientSearchQueryBuilderByPatientId {
    private final Logger log = LoggerFactory.getLogger(PatientSearchQueryBuilderByMRNImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserDepartmentRepository userDepartmentRepository;
    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;
    @Autowired
    private SpecialityRepository specialityRepository;
    @Autowired
    private PatientAppointmentRepository patientAppointmentRepository;
    @Autowired
    private PatientCampRegistrationRepository patientCampRegistrationRepository;


    @Override
    public List<PatientSearchDTO> getPatientSearchResults(PatientSearchCriteria patientSearchCriteria) {
        Patient patient = new Patient();
        IpAdmission ipAdmission = new IpAdmission();
        List<PatientSearchDTO> patientSearchDTOs = new ArrayList<>();
        IpAdmissionBedMovement ipAdmissionBedMovement = new IpAdmissionBedMovement();
        List<PatientAppointment> patientAppointments = new ArrayList<>();
        List<PatientAppointmentDTO> patientAppointmentDTOs = new ArrayList<>();
        List<UserDepartment> userDepartment = null;
        List<Object[]> patientFinalDetails = null;
        VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
        Speciality speciality = new Speciality();

        try {
            log.debug("Call to search patient by " + patientSearchCriteria.getSearchType() + " for the value "
                    + patientSearchCriteria.getSearchValue());
            String whereClause = new String();
            if (patientSearchCriteria.getClinicId() != null & patientSearchCriteria.getSearchType() != null
                    & patientSearchCriteria.getSearchValue() != null) {
                if (patientSearchCriteria.isFetchNonRegistered()) {
                    whereClause = " where p.recordStatus= 1 and p.clinic.id= " + patientSearchCriteria.getClinicId()
                            + " and p.id = '" + patientSearchCriteria.getSearchValue() + "'";
                } else {
                    whereClause = " where p.recordStatus= 1 and (p.registered = TRUE or p.patientIdExternal is null) " +
                            "and p.clinic.id= " + patientSearchCriteria.getClinicId()
                            + " and p.id = '" + patientSearchCriteria.getSearchValue() + "'";
                }
                List<Object[]> patientResults = entityManager.createQuery(PATIENT_DETAIL_BASE_QUERY + " " + whereClause).getResultList();

                for (Object[] ipAdmissionDetail : patientResults) {
                    //create patientSearchDTO object
                    PatientSearchDTO patientSearchDTO = new PatientSearchDTO();
                    patient = (Patient) ipAdmissionDetail[0];
                    if (ipAdmissionDetail[1] != null) {
                        ipAdmission = (IpAdmission) ipAdmissionDetail[1];
                        patientSearchDTO.setIpAdmissionId(ipAdmission.getId());
                        patientSearchDTO.setIpAdmissionNumber(ipAdmission.getIpAdmissionNumber());
                        patientSearchDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                        patientSearchDTO.setIpAdmissionNumber(ipAdmission.getIpAdmissionNumber());
                        patientSearchDTO.setDischargedOn(ipAdmission.getDischargedOn());
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
                        patientSearchDTO.setDayCare(ipAdmission.isDayCare());
                    }
                    //set the values
                    patientSearchDTO.setPatientId(patient.getId());
                    patientSearchDTO.setPatientMRN(patient.getPatientIdExternal());
                    patientSearchDTO.setPatientName(patient.getPatientName());
                    patientSearchDTO.setGender(patient.getGender());
                    patientSearchDTO.setMobileNumber(patient.getMobileNumber());
                    if (patient.getEmail() != null)
                        patientSearchDTO.setEmail(patient.getEmail());
                    if (patient.getAddressInfo() != null)
                        patientSearchDTO.setAddress(patient.getAddressInfo().getContactAddress());
                    if (patient.getClinic() != null)
                        patientSearchDTO.setPatientClinicId(patient.getClinic().getId());
                    if (patient.getPatientSalutation() != null)
                        patientSearchDTO.setPatientSalutation(patient.getPatientSalutation().getName());

                    if (patient.getPatientAdditionalDetail() != null && patient.getPatientAdditionalDetail().getPatientType() != null) {
                        patientSearchDTO.setPatientType(patient.getPatientAdditionalDetail().getPatientType().getPatientTypeName());
                        patientSearchDTO.setPatientTypeId(patient.getPatientAdditionalDetail().getPatientType().getId());
                    }
                    if (patient.getPatientAdditionalDetail() != null && patient.getPatientAdditionalDetail().getUserDetail() != null) {
                        String firstName = patient.getPatientAdditionalDetail().getUserDetail().getUserStaff().getFirstName();
                        String lastName = patient.getPatientAdditionalDetail().getUserDetail().getUserStaff().getLastName();
                        patientSearchDTO.setReferredBy(firstName + " " + lastName);
                        patientSearchDTO.setPatientTypeId(patient.getPatientAdditionalDetail().getPatientType().getId());
                    }
                    if (ipAdmissionDetail[1] == null) {
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
                    patientSearchDTO.setPatientRegisteredDate(patient.getPatientRegisteredDate());
                    //find and set crn
                    if (patientSearchCriteria.getPatientCampRegistrationId() != null) {
                        String CRN = patientCampRegistrationRepository.findCampRegistrationNumberById
                                (patientSearchCriteria.getPatientCampRegistrationId());
                        patientSearchDTO.setPatientCRN(CRN);
                    }
                    //add to list
                    patientSearchDTOs.add(patientSearchDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patientSearchDTOs;
    }

    public VisitTypeMaster getVisitTypeMaster(Long visitTypeMasterId) throws ServiceException {
        return visitTypeMasterRepository.findOne(visitTypeMasterId);
    }
}
