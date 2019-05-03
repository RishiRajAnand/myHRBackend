package com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearchbydate;

/*
* created by Brighty on 30-11-17
* */

import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.domain.IpAdmissionBedMovement;
import com.erx.microservice.patientmanagement.domain.Patient;
import com.erx.microservice.patientmanagement.domain.UserDepartment;
import com.erx.microservice.patientmanagement.repository.IpAdmissionBedMovementRepository;
import com.erx.microservice.patientmanagement.repository.PatientRepository;
import com.erx.microservice.patientmanagement.repository.UserDepartmentRepository;
import com.erx.microservice.patientmanagement.service.dto.PatientDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("ipPatientSearchByDateService")
public class IpPatientSearchByDateServiceImpl implements IpPatientSearchByDateService {

    private final Logger log = LoggerFactory.getLogger(IpPatientSearchByDateServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository;

    @Autowired
    private UserDepartmentRepository userDepartmentRepository;

    @Override
    public IpPatientSearchByDateServiceResponse execute(IpPatientSearchByDateServiceRequest request) throws ServiceException {

        IpPatientSearchByDateServiceResponse response = null;
        List<Object[]> ipAdmissions = null;
        List<PatientDTO> patientDTOs = new ArrayList<>();
        IpAdmissionBedMovement ipAdmissionBedMovement = null;
        List<UserDepartment> userDepartment = null;
        Patient patient = new Patient();
        IpAdmission ipAdmission = new IpAdmission();
        try {
            log.debug("Call to retrieve Patients on dateRange");
            if (request.getClinicId() != null & request.getStartDate() != null & request.getEndDate() != null) {
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");
                LocalDateTime startDate = LocalDateTime.parse(request.getStartDate());
                LocalDateTime endDate = LocalDateTime.parse(request.getEndDate());

                //retrieve the objects
                ipAdmissions = patientRepository.getIpAdmissionByDateRange(request.getClinicId(), startDate, endDate);
                //set the list of PatientDTO
                for (Object[] ipAdmissionDetail : ipAdmissions) {
                    //create PatientDTO object
                    PatientDTO patientDTO = new PatientDTO();

                    patient = (Patient) ipAdmissionDetail[0];

                    if (ipAdmissionDetail[1] != null) {
                        ipAdmission = (IpAdmission) ipAdmissionDetail[1];

                        patientDTO.setiPAdmissionId(ipAdmission.getId());
                        patientDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                        if (ipAdmission.getUserDetail() != null) {
                            if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                patientDTO.setDoctorId(ipAdmission.getUserDetail().getId());
                                patientDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName() + " " + ipAdmission.getUserDetail().getUserStaff().getLastName());
                                userDepartment = userDepartmentRepository.getDepartmentByDoctor(ipAdmission.getUserDetail().getUserStaff().getId());
                                if (userDepartment != null) {
                                    for (UserDepartment userDepartment1 : userDepartment) {
                                        patientDTO.setDepartmentId(userDepartment1.getDepartment().getId());
                                        patientDTO.setDepartmentName(userDepartment1.getDepartment().getDepartmentName());
                                    }
                                }
                            }
                        } else {
                            patientDTO.setDoctorName("-");
                        }
                        if (!(ipAdmission.getIpAdmissionNumber().equals(""))) {
                            patientDTO.setVisitId(ipAdmission.getIpAdmissionNumber());
                        } else if (ipAdmission.getDayCareAdmissionNumber() != null) {
                            patientDTO.setVisitId(ipAdmission.getDayCareAdmissionNumber());
                        } else {
                            patientDTO.setVisitId("-");
                        }
                        patientDTO.setVisitType("-");
                        patientDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                        patientDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                        patientDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                        patientDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                        patientDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                        List<IpAdmissionBedMovement> ipAdmissionBedMovements = ipAdmissionBedMovementRepository.getBedMovementByCurrentBed(ipAdmission.getId());
                        if (!ipAdmissionBedMovements.isEmpty())
                            ipAdmissionBedMovement = ipAdmissionBedMovements.get(ipAdmissionBedMovements.size() - 1);
                        else ipAdmissionBedMovement = null;
                        if (ipAdmissionBedMovement != null) {
                            patientDTO.setIpAdmissionBedMovementId(ipAdmissionBedMovement.getId());
                            patientDTO.setBedMovedToId(ipAdmissionBedMovement.getBedMovedTo().getId());
                        }
                        patientDTO.setIpAdmissionNotes(ipAdmission.getIpAdmissionNotes());
                    }

                    //set the values

                    patientDTO.setPatientId(patient.getId());
                    patientDTO.setPatientMRN(patient.getPatientIdExternal());
                    patientDTO.setPatientName(patient.getPatientName());
                    patientDTO.setGender(patient.getGender());


                    //add to list
                    patientDTOs.add(patientDTO);
                }
                response = new IpPatientSearchByDateServiceResponse(patientDTOs);
                response.SUCCESSFUL = true;
                response.setMessage("Retrieved Patients Successfully");
                log.debug("Retrieved Patients Successfully");
            }
        } catch (Exception e) {
            response = new IpPatientSearchByDateServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Patients");
            log.error("Failed to retrieve Patients");
        }
        return response;
    }
}
