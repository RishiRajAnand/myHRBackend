package com.erx.microservice.patientmanagement.service.ipadmission.ippatientsearch;

/*
 * created by Brighty on 30-11-17
 * */

import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.service.dto.IpAdmissionTrackerDTO;
import com.erx.microservice.patientmanagement.service.dto.IpDischargeDetailDTO;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("ipPatientSearchService")
public class IpPatientSearchServiceImpl implements IpPatientSearchService {

    private final Logger log = LoggerFactory.getLogger(IpPatientSearchServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private IpAdmissionBedMovementRepository ipAdmissionBedMovementRepository;

    @Autowired
    private UserDepartmentRepository userDepartmentRepository;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;
    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;
    @Autowired
    private IpAdmissionBedTransferRepository ipAdmissionBedTransferRepository;

    @Override
    public IpPatientSearchServiceResponse execute(IpPatientSearchServiceRequest request) throws ServiceException {

        IpPatientSearchServiceResponse response = null;
        Page<Object[]> ipAdmissions = null;
        List<IpAdmissionTrackerDTO> patientDTOs = new ArrayList<>();
        IpAdmissionBedMovement ipAdmissionBedMovement = null;
        List<UserDepartment> userDepartment = null;
        Page<IpAdmissionTrackerDTO> ipAdmissionTrackerDTOs = null;
        request.setPageable(new PageRequest(request.getPageable().getPageNumber(), 50));
        try {
            log.debug("Call to search patient by " + request.getSearchParam() + " for the value " + request.getSearchValue());
            if (request.getClinicId() != null & request.getSearchParam() != null & request.getSearchValue() != null) {
                //retrieve the searchParam from request
                String searchParam = request.getSearchParam();
                switch (searchParam) {
                    case "phoneNumber":
                        //retrieve objects
                        ipAdmissions = patientRepository.getIpAdmissionByPhoneNumber
                                (request.getClinicId(), request.getSearchValue(), request.getPageable());
                        //set the list of PatientDTO
                        for (Object[] ipAdmissionDetail : ipAdmissions.getContent()) {
                            //create PatientDTO object
                            IpAdmissionTrackerDTO patientDTO = new IpAdmissionTrackerDTO();
                            IpAdmission ipAdmission = null;
                            Patient patient = null;
                            patient = (Patient) ipAdmissionDetail[0];

                            if (ipAdmissionDetail[1] != null) {
                                ipAdmission = (IpAdmission) ipAdmissionDetail[1];

                                patientDTO.setiPAdmissionId(ipAdmission.getId());
                                patientDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                                if (ipAdmission.getUserDetail() != null) {
                                    if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                        patientDTO.setDoctorId(ipAdmission.getUserDetail().getId());
                                        patientDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName()
                                                + " " + ipAdmission.getUserDetail().getUserStaff().getLastName());
                                        if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                            userDepartment = userDepartmentRepository.getDepartmentByDoctor
                                                    (ipAdmission.getUserDetail().getUserStaff().getId());
                                        }
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
                                if (ipAdmission.getVisitTypeMasterId() != null) {
                                    String visitType = visitTypeMasterRepository.findOne(ipAdmission.getVisitTypeMasterId()).getVisitType();
                                    patientDTO.setVisitType(visitType);
                                }
                                patientDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                                patientDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                                patientDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                                patientDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                                patientDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                                List<IpAdmissionBedMovement> ipAdmissionBedMovements = ipAdmissionBedMovementRepository
                                        .getBedMovementByCurrentBed(ipAdmission.getId());
                                if (!ipAdmissionBedMovements.isEmpty())
                                    ipAdmissionBedMovement = ipAdmissionBedMovements.get(ipAdmissionBedMovements.size() - 1);
                                else ipAdmissionBedMovement = null;
                                if (ipAdmissionBedMovement != null) {
                                    patientDTO.setIpAdmissionBedMovementId(ipAdmissionBedMovement.getId());
                                    patientDTO.setBedMovedToId(ipAdmissionBedMovement.getBedMovedTo().getId());
                                    //call method to check moved bed is same as actual bed
                                    checkMovedBed(ipAdmission, ipAdmissionBedMovement, patientDTO);
                                } else
                                    //call method to check transferred bed is same as actual bed
                                    checkBedTransferredBed(ipAdmission, patientDTO);
                                patientDTO.setIpAdmissionNotes(ipAdmission.getIpAdmissionNotes());
                                patientDTO.setIpAdmissionStatus(PatientConstants.IP_STATUS_ADMITTED);
                            }

                            //set the values

                            patientDTO.setPatientId(patient.getId());
                            patientDTO.setPatientMRN(patient.getPatientIdExternal());
                            patientDTO.setPatientName(patient.getPatientName());
                            patientDTO.setGender(patient.getGender());

                            //set DisChargeDetails
                            List<IpDischargeDetailDTO> ipDischargeDetailDTOs = getDischargeDetails(patientDTO, patient);
                            patientDTO.setIpDischargeDetailDTOs(ipDischargeDetailDTOs);


                            //add to list
                            patientDTOs.add(patientDTO);
                        }
                        ipAdmissionTrackerDTOs = createPageObject(patientDTOs, ipAdmissions);
                        break;

                    case "MRN":
                        //retrieve objects
                        ipAdmissions = patientRepository.getIpAdmissionByMRN
                                (request.getClinicId(), request.getSearchValue(), request.getPageable());
                        //set the list of PatientDTO
                        for (Object[] ipAdmissionDetail : ipAdmissions.getContent()) {
                            //create PatientDTO object
                            IpAdmissionTrackerDTO patientDTO = new IpAdmissionTrackerDTO();
                            IpAdmission ipAdmission = null;
                            Patient patient = null;
                            patient = (Patient) ipAdmissionDetail[0];

                            if (ipAdmissionDetail[1] != null) {
                                ipAdmission = (IpAdmission) ipAdmissionDetail[1];

                                patientDTO.setiPAdmissionId(ipAdmission.getId());
                                patientDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                                if (ipAdmission.getUserDetail() != null) {
                                    if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                        patientDTO.setDoctorId(ipAdmission.getUserDetail().getId());
                                        patientDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName()
                                                + " " + ipAdmission.getUserDetail().getUserStaff().getLastName());
                                        if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                            userDepartment = userDepartmentRepository.getDepartmentByDoctor
                                                    (ipAdmission.getUserDetail().getUserStaff().getId());
                                        }
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
                                if (ipAdmission.getVisitTypeMasterId() != null) {
                                    String visitType = visitTypeMasterRepository.findOne(ipAdmission.getVisitTypeMasterId()).getVisitType();
                                    patientDTO.setVisitType(visitType);
                                }
                                patientDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                                patientDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                                patientDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                                patientDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                                patientDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                                List<IpAdmissionBedMovement> ipAdmissionBedMovements = ipAdmissionBedMovementRepository
                                        .getBedMovementByCurrentBed(ipAdmission.getId());
                                if (!ipAdmissionBedMovements.isEmpty())
                                    ipAdmissionBedMovement = ipAdmissionBedMovements.get(ipAdmissionBedMovements.size() - 1);
                                else ipAdmissionBedMovement = null;
                                if (ipAdmissionBedMovement != null) {
                                    patientDTO.setIpAdmissionBedMovementId(ipAdmissionBedMovement.getId());
                                    patientDTO.setBedMovedToId(ipAdmissionBedMovement.getBedMovedTo().getId());
                                    //call method to check moved bed is same as actual bed
                                    checkMovedBed(ipAdmission, ipAdmissionBedMovement, patientDTO);
                                } else
                                    //call method to check transferred bed is same as actual bed
                                    checkBedTransferredBed(ipAdmission, patientDTO);
                                patientDTO.setIpAdmissionNotes(ipAdmission.getIpAdmissionNotes());
                                patientDTO.setIpAdmissionStatus(PatientConstants.IP_STATUS_ADMITTED);
                            }

                            //set the values

                            patientDTO.setPatientId(patient.getId());
                            patientDTO.setPatientMRN(patient.getPatientIdExternal());
                            patientDTO.setPatientName(patient.getPatientName());
                            patientDTO.setGender(patient.getGender());

                            //set DisChargeDetails
                            List<IpDischargeDetailDTO> ipDischargeDetailDTOs = getDischargeDetails(patientDTO, patient);
                            patientDTO.setIpDischargeDetailDTOs(ipDischargeDetailDTOs);

                            //add to list
                            patientDTOs.add(patientDTO);
                        }
                        ipAdmissionTrackerDTOs = createPageObject(patientDTOs, ipAdmissions);
                        break;

                    case "ipAdmissionNumber":
                        //retrieve objects
                        ipAdmissions = patientRepository.getIpAdmissionByIpAdmissionNumber
                                (request.getClinicId(), request.getSearchValue(), request.getPageable());
                        //set the list of PatientDTO
                        for (Object[] ipAdmissionDetail : ipAdmissions.getContent()) {
                            //create PatientDTO object
                            IpAdmissionTrackerDTO patientDTO = new IpAdmissionTrackerDTO();
                            IpAdmission ipAdmission = null;
                            Patient patient = null;
                            patient = (Patient) ipAdmissionDetail[0];

                            if (ipAdmissionDetail[1] != null) {
                                ipAdmission = (IpAdmission) ipAdmissionDetail[1];

                                patientDTO.setiPAdmissionId(ipAdmission.getId());
                                patientDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                                if (ipAdmission.getUserDetail() != null) {
                                    if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                        patientDTO.setDoctorId(ipAdmission.getUserDetail().getId());
                                        patientDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName()
                                                + " " + ipAdmission.getUserDetail().getUserStaff().getLastName());
                                        if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                            userDepartment = userDepartmentRepository.getDepartmentByDoctor
                                                    (ipAdmission.getUserDetail().getUserStaff().getId());
                                        }
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
                                if (ipAdmission.getVisitTypeMasterId() != null) {
                                    String visitType = visitTypeMasterRepository.findOne(ipAdmission.getVisitTypeMasterId()).getVisitType();
                                    patientDTO.setVisitType(visitType);
                                }
                                patientDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                                patientDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                                patientDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                                patientDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                                patientDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                                List<IpAdmissionBedMovement> ipAdmissionBedMovements = ipAdmissionBedMovementRepository
                                        .getBedMovementByCurrentBed(ipAdmission.getId());
                                if (!ipAdmissionBedMovements.isEmpty())
                                    ipAdmissionBedMovement = ipAdmissionBedMovements.get(ipAdmissionBedMovements.size() - 1);
                                else ipAdmissionBedMovement = null;
                                if (ipAdmissionBedMovement != null) {
                                    patientDTO.setIpAdmissionBedMovementId(ipAdmissionBedMovement.getId());
                                    patientDTO.setBedMovedToId(ipAdmissionBedMovement.getBedMovedTo().getId());
                                    //call method to check moved bed is same as actual bed
                                    checkMovedBed(ipAdmission, ipAdmissionBedMovement, patientDTO);
                                } else
                                    //call method to check transferred bed is same as actual bed
                                    checkBedTransferredBed(ipAdmission, patientDTO);
                                patientDTO.setIpAdmissionNotes(ipAdmission.getIpAdmissionNotes());
                                patientDTO.setIpAdmissionStatus(PatientConstants.IP_STATUS_ADMITTED);
                            }

                            //set the values
                            patientDTO.setPatientId(patient.getId());
                            patientDTO.setPatientMRN(patient.getPatientIdExternal());
                            patientDTO.setPatientName(patient.getPatientName());
                            patientDTO.setGender(patient.getGender());

                            //set DisChargeDetails
                            List<IpDischargeDetailDTO> ipDischargeDetailDTOs = getDischargeDetails(patientDTO, patient);
                            patientDTO.setIpDischargeDetailDTOs(ipDischargeDetailDTOs);

                            //add to list
                            patientDTOs.add(patientDTO);
                        }
                        ipAdmissionTrackerDTOs = createPageObject(patientDTOs, ipAdmissions);
                        break;

                    case "dayCareAdmissionNumber":
                        //retrieve objects
                        ipAdmissions = patientRepository.getIpAdmissionByDayCareAdmissionNumber
                                (request.getClinicId(), request.getSearchValue(), request.getPageable());
                        //set the list of PatientDTO
                        for (Object[] ipAdmissionDetail : ipAdmissions.getContent()) {
                            //create PatientDTO object
                            IpAdmissionTrackerDTO patientDTO = new IpAdmissionTrackerDTO();
                            IpAdmission ipAdmission = null;
                            Patient patient = null;
                            patient = (Patient) ipAdmissionDetail[0];

                            if (ipAdmissionDetail[1] != null) {
                                ipAdmission = (IpAdmission) ipAdmissionDetail[1];

                                patientDTO.setiPAdmissionId(ipAdmission.getId());
                                patientDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                                if (ipAdmission.getUserDetail() != null) {
                                    if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                        patientDTO.setDoctorId(ipAdmission.getUserDetail().getId());
                                        patientDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName()
                                                + " " + ipAdmission.getUserDetail().getUserStaff().getLastName());
                                        if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                            userDepartment = userDepartmentRepository.getDepartmentByDoctor
                                                    (ipAdmission.getUserDetail().getUserStaff().getId());
                                        }
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
                                if (ipAdmission.getVisitTypeMasterId() != null) {
                                    String visitType = visitTypeMasterRepository.findOne(ipAdmission.getVisitTypeMasterId()).getVisitType();
                                    patientDTO.setVisitType(visitType);
                                }
                                patientDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                                patientDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                                patientDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                                patientDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                                patientDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                                List<IpAdmissionBedMovement> ipAdmissionBedMovements = ipAdmissionBedMovementRepository
                                        .getBedMovementByCurrentBed(ipAdmission.getId());
                                if (!ipAdmissionBedMovements.isEmpty())
                                    ipAdmissionBedMovement = ipAdmissionBedMovements.get(ipAdmissionBedMovements.size() - 1);
                                else ipAdmissionBedMovement = null;
                                if (ipAdmissionBedMovement != null) {
                                    patientDTO.setIpAdmissionBedMovementId(ipAdmissionBedMovement.getId());
                                    patientDTO.setBedMovedToId(ipAdmissionBedMovement.getBedMovedTo().getId());
                                    //call method to check moved bed is same as actual bed
                                    checkMovedBed(ipAdmission, ipAdmissionBedMovement, patientDTO);
                                } else
                                    //call method to check transferred bed is same as actual bed
                                    checkBedTransferredBed(ipAdmission, patientDTO);
                                patientDTO.setIpAdmissionNotes(ipAdmission.getIpAdmissionNotes());
                                patientDTO.setIpAdmissionStatus(PatientConstants.IP_STATUS_ADMITTED);
                            }

                            //set the values

                            patientDTO.setPatientId(patient.getId());
                            patientDTO.setPatientMRN(patient.getPatientIdExternal());
                            patientDTO.setPatientName(patient.getPatientName());
                            patientDTO.setGender(patient.getGender());

                            //set DisChargeDetails
                            List<IpDischargeDetailDTO> ipDischargeDetailDTOs = getDischargeDetails(patientDTO, patient);
                            patientDTO.setIpDischargeDetailDTOs(ipDischargeDetailDTOs);

                            //add to list
                            patientDTOs.add(patientDTO);
                        }
                        ipAdmissionTrackerDTOs = createPageObject(patientDTOs, ipAdmissions);
                        break;

                    case "name":
                        //retrieve objects
                        ipAdmissions = patientRepository.getIpAdmissionByName
                                (request.getClinicId(), request.getSearchValue(), request.getPageable());
                        //set the list of PatientDTO
                        for (Object[] ipAdmissionDetail : ipAdmissions.getContent()) {
                            //create PatientDTO object
                            IpAdmissionTrackerDTO patientDTO = new IpAdmissionTrackerDTO();
                            IpAdmission ipAdmission = null;
                            Patient patient = null;
                            patient = (Patient) ipAdmissionDetail[0];

                            if (ipAdmissionDetail[1] != null) {
                                ipAdmission = (IpAdmission) ipAdmissionDetail[1];

                                patientDTO.setiPAdmissionId(ipAdmission.getId());
                                patientDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                                if (ipAdmission.getUserDetail() != null) {
                                    if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                        patientDTO.setDoctorId(ipAdmission.getUserDetail().getId());
                                        patientDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName()
                                                + " " + ipAdmission.getUserDetail().getUserStaff().getLastName());
                                        if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                            userDepartment = userDepartmentRepository.getDepartmentByDoctor
                                                    (ipAdmission.getUserDetail().getUserStaff().getId());
                                        }
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
                                if (ipAdmission.getVisitTypeMasterId() != null) {
                                    String visitType = visitTypeMasterRepository.findOne(ipAdmission.getVisitTypeMasterId()).getVisitType();
                                    patientDTO.setVisitType(visitType);
                                }
                                patientDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                                patientDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                                patientDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                                patientDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                                patientDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                                List<IpAdmissionBedMovement> ipAdmissionBedMovements = ipAdmissionBedMovementRepository
                                        .getBedMovementByCurrentBed(ipAdmission.getId());
                                if (!ipAdmissionBedMovements.isEmpty())
                                    ipAdmissionBedMovement = ipAdmissionBedMovements.get(ipAdmissionBedMovements.size() - 1);
                                else ipAdmissionBedMovement = null;
                                if (ipAdmissionBedMovement != null) {
                                    patientDTO.setIpAdmissionBedMovementId(ipAdmissionBedMovement.getId());
                                    patientDTO.setBedMovedToId(ipAdmissionBedMovement.getBedMovedTo().getId());
                                    //call method to check moved bed is same as actual bed
                                    checkMovedBed(ipAdmission, ipAdmissionBedMovement, patientDTO);
                                } else
                                    //call method to check transferred bed is same as actual bed
                                    checkBedTransferredBed(ipAdmission, patientDTO);
                                patientDTO.setIpAdmissionNotes(ipAdmission.getIpAdmissionNotes());
                                patientDTO.setIpAdmissionStatus(PatientConstants.IP_STATUS_ADMITTED);
                            }

                            //set the values

                            patientDTO.setPatientId(patient.getId());
                            patientDTO.setPatientMRN(patient.getPatientIdExternal());
                            patientDTO.setPatientName(patient.getPatientName());
                            patientDTO.setGender(patient.getGender());

                            //set DisChargeDetails
                            List<IpDischargeDetailDTO> ipDischargeDetailDTOs = getDischargeDetails(patientDTO, patient);
                            patientDTO.setIpDischargeDetailDTOs(ipDischargeDetailDTOs);

                            //add to list
                            patientDTOs.add(patientDTO);
                        }
                        ipAdmissionTrackerDTOs = createPageObject(patientDTOs, ipAdmissions);
                        break;
                    case "admitted":
                        //retrieve the ipAdmissions
                        Page<IpAdmission> ipAdmissionPage = ipAdmissionRepository.
                                findByClinic(request.getClinicId(), request.getPageable());
                        //set the list of PatientDTO
                        for (IpAdmission ipAdmission : ipAdmissionPage.getContent()) {
                            //create PatientDTO object
                            IpAdmissionTrackerDTO patientDTO = new IpAdmissionTrackerDTO();
                            //IpAdmission ipAdmission = null;
                            Patient patient = null;
                            patient = ipAdmission.getPatient();

                            if (ipAdmission != null) {
                                //ipAdmission = (IpAdmission) ipAdmissionDetail[1];

                                patientDTO.setiPAdmissionId(ipAdmission.getId());
                                patientDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                                if (ipAdmission.getUserDetail() != null) {
                                    if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                        patientDTO.setDoctorId(ipAdmission.getUserDetail().getId());
                                        patientDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName()
                                                + " " + ipAdmission.getUserDetail().getUserStaff().getLastName());
                                        if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                            userDepartment = userDepartmentRepository.getDepartmentByDoctor
                                                    (ipAdmission.getUserDetail().getUserStaff().getId());
                                        }
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
                                if (ipAdmission.getVisitTypeMasterId() != null) {
                                    String visitType = visitTypeMasterRepository.findOne(ipAdmission.getVisitTypeMasterId()).getVisitType();
                                    patientDTO.setVisitType(visitType);
                                }
                                patientDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                                patientDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                                patientDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                                patientDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                                patientDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                                List<IpAdmissionBedMovement> ipAdmissionBedMovements = ipAdmissionBedMovementRepository
                                        .getBedMovementByCurrentBed(ipAdmission.getId());
                                if (!ipAdmissionBedMovements.isEmpty())
                                    ipAdmissionBedMovement = ipAdmissionBedMovements.get(ipAdmissionBedMovements.size() - 1);
                                else ipAdmissionBedMovement = null;
                                if (ipAdmissionBedMovement != null) {
                                    patientDTO.setIpAdmissionBedMovementId(ipAdmissionBedMovement.getId());
                                    patientDTO.setBedMovedToId(ipAdmissionBedMovement.getBedMovedTo().getId());
                                    //call method to check moved bed is same as actual bed
                                    checkMovedBed(ipAdmission, ipAdmissionBedMovement, patientDTO);
                                } else
                                    //call method to check transferred bed is same as actual bed
                                    checkBedTransferredBed(ipAdmission, patientDTO);
                                patientDTO.setIpAdmissionNotes(ipAdmission.getIpAdmissionNotes());
                                patientDTO.setIpAdmissionStatus(PatientConstants.IP_STATUS_ADMITTED);
                            }

                            //set the values

                            patientDTO.setPatientId(patient.getId());
                            patientDTO.setPatientMRN(patient.getPatientIdExternal());
                            patientDTO.setPatientName(patient.getPatientName());
                            patientDTO.setGender(patient.getGender());

                            //set DisChargeDetails
                            List<IpDischargeDetailDTO> ipDischargeDetailDTOs = getDischargeDetails(patientDTO, patient);
                            patientDTO.setIpDischargeDetailDTOs(ipDischargeDetailDTOs);

                            //add to list
                            patientDTOs.add(patientDTO);
                        }
                        ipAdmissionTrackerDTOs = createPageObjectForAdmitted(patientDTOs, ipAdmissionPage);
                        break;
                    case "all":
                        //retrieve objects
                        ipAdmissions = patientRepository.getIpAdmissionByClinic(request.getClinicId(), request.getPageable());
                        //set the list of PatientDTO
                        for (Object[] ipAdmissionDetail : ipAdmissions.getContent()) {
                            //create PatientDTO object
                            IpAdmissionTrackerDTO patientDTO = new IpAdmissionTrackerDTO();
                            IpAdmission ipAdmission = null;
                            Patient patient = null;
                            patient = (Patient) ipAdmissionDetail[0];

                            if (ipAdmissionDetail[1] != null) {
                                ipAdmission = (IpAdmission) ipAdmissionDetail[1];

                                patientDTO.setiPAdmissionId(ipAdmission.getId());
                                patientDTO.setDateOfAdmission(ipAdmission.getAdmissionOn());
                                if (ipAdmission.getUserDetail() != null) {
                                    if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                        patientDTO.setDoctorId(ipAdmission.getUserDetail().getId());
                                        patientDTO.setDoctorName(ipAdmission.getUserDetail().getUserStaff().getFirstName()
                                                + " " + ipAdmission.getUserDetail().getUserStaff().getLastName());
                                        if (ipAdmission.getUserDetail().getUserStaff() != null) {
                                            userDepartment = userDepartmentRepository.getDepartmentByDoctor
                                                    (ipAdmission.getUserDetail().getUserStaff().getId());
                                        }
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
                                if (ipAdmission.getVisitTypeMasterId() != null) {
                                    String visitType = visitTypeMasterRepository.findOne(ipAdmission.getVisitTypeMasterId()).getVisitType();
                                    patientDTO.setVisitType(visitType);
                                }
                                patientDTO.setActualBedId(ipAdmission.getBedMaster().getId());
                                patientDTO.setBedNumber(ipAdmission.getBedMaster().getBedNumber());
                                patientDTO.setBedType(ipAdmission.getBedMaster().getBedTypeMaster().getBedTypeName());
                                patientDTO.setRoomNumber(ipAdmission.getBedMaster().getRoomConfigurationMaster().getRoomNumber());
                                patientDTO.setWardName(ipAdmission.getBedMaster().getWardMaster().getWardName());
                                List<IpAdmissionBedMovement> ipAdmissionBedMovements = ipAdmissionBedMovementRepository
                                        .getBedMovementByCurrentBed(ipAdmission.getId());
                                if (!ipAdmissionBedMovements.isEmpty())
                                    ipAdmissionBedMovement = ipAdmissionBedMovements.get(ipAdmissionBedMovements.size() - 1);
                                else ipAdmissionBedMovement = null;
                                if (ipAdmissionBedMovement != null) {
                                    patientDTO.setIpAdmissionBedMovementId(ipAdmissionBedMovement.getId());
                                    patientDTO.setBedMovedToId(ipAdmissionBedMovement.getBedMovedTo().getId());
                                    //call method to check moved bed is same as actual bed
                                    checkMovedBed(ipAdmission, ipAdmissionBedMovement, patientDTO);
                                } else
                                    //call method to check transferred bed is same as actual bed
                                    checkBedTransferredBed(ipAdmission, patientDTO);
                                patientDTO.setIpAdmissionNotes(ipAdmission.getIpAdmissionNotes());
                                patientDTO.setIpAdmissionStatus(PatientConstants.IP_STATUS_ADMITTED);
                            }

                            //set the values

                            patientDTO.setPatientId(patient.getId());
                            patientDTO.setPatientMRN(patient.getPatientIdExternal());
                            patientDTO.setPatientName(patient.getPatientName());
                            patientDTO.setGender(patient.getGender());

                            //set DisChargeDetails
                            List<IpDischargeDetailDTO> ipDischargeDetailDTOs = getDischargeDetails(patientDTO, patient);
                            patientDTO.setIpDischargeDetailDTOs(ipDischargeDetailDTOs);

                            //add to list
                            patientDTOs.add(patientDTO);
                        }
                        ipAdmissionTrackerDTOs = createPageObject(patientDTOs, ipAdmissions);
                        break;
                    default:
                        log.debug("No Patient details Found");
                }
                response = new IpPatientSearchServiceResponse(ipAdmissionTrackerDTOs);
                response.SUCCESSFUL = true;
                response.setMessage("Retrieved Patient details Successfully");
                log.debug("Retrieved Patient details Successfully");
            }

        } catch (Exception e) {
            response = new IpPatientSearchServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Patient details");
            log.error("Failed to retrieve Patient details");
        }
        return response;
    }

    //method to check transferred bed is same as actual bed
    private void checkBedTransferredBed(IpAdmission ipAdmission, IpAdmissionTrackerDTO patientDTO) throws ServiceException {
        IpAdmissionBedTransfer ipAdmissionBedTransfer = ipAdmissionBedTransferRepository.
                getBedTransferDetailsIpAdmissionId(ipAdmission.getId());
        if (ipAdmissionBedTransfer != null) {
            if (ipAdmission.getBedMaster().getId().equals(ipAdmissionBedTransfer.getBedTransferredTo().getId()))
                patientDTO.setOriginalBed(true);
            else
                patientDTO.setOriginalBed(false);
        } else {
            patientDTO.setOriginalBed(true);
        }
    }

    //method to check moved bed is same as actual bed
    private void checkMovedBed(IpAdmission ipAdmission, IpAdmissionBedMovement ipAdmissionBedMovement,
                               IpAdmissionTrackerDTO patientDTO) throws ServiceException {
        if (ipAdmission.getBedMaster().getId().equals(ipAdmissionBedMovement.getBedMovedTo().getId()))
            patientDTO.setOriginalBed(true);
        else
            patientDTO.setOriginalBed(false);
    }

    private List<IpDischargeDetailDTO> getDischargeDetails(IpAdmissionTrackerDTO patientDTO, Patient patient) {

        List<IpDischargeDetailDTO> ipDischargeDetailDTOs = null;
        List<IpAdmission> dischargedIpAdmissions = ipAdmissionRepository
                .findDischargedIpDetailsByPatient(patient.getId(), patient.getClinic().getId());
        if (!dischargedIpAdmissions.isEmpty()) {
            ipDischargeDetailDTOs = new ArrayList<>();
            for (IpAdmission dischargeIpDetail : dischargedIpAdmissions) {
                IpDischargeDetailDTO ipDischargeDetailDTO = new IpDischargeDetailDTO();
                ipDischargeDetailDTO.setIpAdmissionId(dischargeIpDetail.getId());
                if (dischargeIpDetail.getIpAdmissionNumber() != null)
                    ipDischargeDetailDTO.setIpAdmissionNumber(dischargeIpDetail.getIpAdmissionNumber());
                else if (dischargeIpDetail.getDayCareAdmissionNumber() != null)
                    ipDischargeDetailDTO.setIpAdmissionNumber(dischargeIpDetail.getDayCareAdmissionNumber());
                ipDischargeDetailDTO.setPatientName(patientDTO.getPatientName());
                ipDischargeDetailDTO.setMrn(patientDTO.getPatientMRN());
                ipDischargeDetailDTO.setDateOfAdmission(dischargeIpDetail.getAdmissionOn());
                ipDischargeDetailDTO.setDateOfDischarge(dischargeIpDetail.getDischargedOn());
                if (dischargeIpDetail.getUserDetail() != null)
                    ipDischargeDetailDTO.setDoctorName(dischargeIpDetail.getUserDetail().getUserStaff().getFirstName() +
                            " " + dischargeIpDetail.getUserDetail().getUserStaff().getLastName());
                if (dischargeIpDetail.getIpAdmissionCaseMapping() != null) {
                    if (dischargeIpDetail.getIpAdmissionCaseMapping().getCmMaster() != null) {
                        ipDischargeDetailDTO.setChiefComplaint(dischargeIpDetail.getIpAdmissionCaseMapping()
                                .getCmMaster().getChiefComplaint());
                    }
                }
                ipDischargeDetailDTO.setStatus(PatientConstants.IP_STATUS_DISCHARGED);
                //add to List
                ipDischargeDetailDTOs.add(ipDischargeDetailDTO);
            }

        }
        return ipDischargeDetailDTOs;
    }

    private Page<IpAdmissionTrackerDTO> createPageObject(List<IpAdmissionTrackerDTO> patientDTOs, Page<Object[]> ipAdmissions) {
        Page<IpAdmissionTrackerDTO> ipAdmissionTrackerDTOs = new Page<IpAdmissionTrackerDTO>() {
            @Override
            public int getTotalPages() {
                return ipAdmissions.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return ipAdmissions.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super IpAdmissionTrackerDTO, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return ipAdmissions.getNumber();
            }

            @Override
            public int getSize() {
                return ipAdmissions.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return ipAdmissions.getNumberOfElements();
            }

            @Override
            public List<IpAdmissionTrackerDTO> getContent() {
                return patientDTOs;
            }

            @Override
            public boolean hasContent() {
                return ipAdmissions.hasContent();
            }

            @Override
            public Sort getSort() {
                return ipAdmissions.getSort();
            }

            @Override
            public boolean isFirst() {
                return ipAdmissions.isFirst();
            }

            @Override
            public boolean isLast() {
                return ipAdmissions.isLast();
            }

            @Override
            public boolean hasNext() {
                return ipAdmissions.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return ipAdmissions.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return ipAdmissions.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return ipAdmissions.previousPageable();
            }

            @Override
            public Iterator<IpAdmissionTrackerDTO> iterator() {
                return null;
            }
        };
        return ipAdmissionTrackerDTOs;
    }

    private Page<IpAdmissionTrackerDTO> createPageObjectForAdmitted
            (List<IpAdmissionTrackerDTO> patientDTOs, Page<IpAdmission> ipAdmissions) {
        Page<IpAdmissionTrackerDTO> ipAdmissionTrackerDTOs = new Page<IpAdmissionTrackerDTO>() {
            @Override
            public int getTotalPages() {
                return ipAdmissions.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return ipAdmissions.getTotalElements();
            }

            @Override
            public <S> Page<S> map(Converter<? super IpAdmissionTrackerDTO, ? extends S> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return ipAdmissions.getNumber();
            }

            @Override
            public int getSize() {
                return ipAdmissions.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return ipAdmissions.getNumberOfElements();
            }

            @Override
            public List<IpAdmissionTrackerDTO> getContent() {
                return patientDTOs;
            }

            @Override
            public boolean hasContent() {
                return ipAdmissions.hasContent();
            }

            @Override
            public Sort getSort() {
                return ipAdmissions.getSort();
            }

            @Override
            public boolean isFirst() {
                return ipAdmissions.isFirst();
            }

            @Override
            public boolean isLast() {
                return ipAdmissions.isLast();
            }

            @Override
            public boolean hasNext() {
                return ipAdmissions.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return ipAdmissions.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return ipAdmissions.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return ipAdmissions.previousPageable();
            }

            @Override
            public Iterator<IpAdmissionTrackerDTO> iterator() {
                return null;
            }
        };
        return ipAdmissionTrackerDTOs;
    }
}
