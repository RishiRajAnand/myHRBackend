package com.erx.microservice.patientmanagement.service.patientvitals;


import com.erx.microservice.patientmanagement.domain.*;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.*;
import com.erx.microservice.patientmanagement.service.dto.casemanagementdto.PatientVitalsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
* created by Latha on 17-08-2018
* */

@Service("getPatientVitalsByPatientId")
public class GetPatientVitalsByPatientIdServiceImpl implements GetPatientVitalsByPatientIdService {

    private final Logger log = LoggerFactory.getLogger(GetPatientVitalsByPatientIdServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VitalRepository vitalRepository;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    private VisitTypeMasterRepository visitTypeMasterRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public GetPatientVitalsByPatientIdServiceResponse execute(GetPatientVitalsByPatientIdServiceRequest request) throws ServiceException {

        log.debug("call to retrieve patient and patient vitals with id : " + request.getPatientId());
        GetPatientVitalsByPatientIdServiceResponse response = null;
        Patient patient = null;
        PatientVitalsDTO patientVitalsDTO = new PatientVitalsDTO();
        List<Vital> vitalList = new ArrayList<Vital>();
        List<IpAdmission> ipAdmissions = null;
        VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
        UserDetail userDetail = new UserDetail();
        try {
            if (request.getPatientId() != 0) {
                //find doctor by user and clinic
                userDetail = userDetailRepository.findDoctorByUserId(request.getUserId(),request.getClinicId());
                if(userDetail != null){
                    patientVitalsDTO.setConsultantDoctorName(userDetail.getUserStaff().getFirstName());
                    patientVitalsDTO.setDoctorRegistrationNo(userDetail.getRegistration_No());
                }
                //get the patient details
                patient = patientRepository.findOne(request.getPatientId());
                //get the patient vital details
                vitalList = vitalRepository.getVitalDetailsByPatientId(request.getPatientId());
                //get ip admission by patientId
                ipAdmissions = ipAdmissionRepository.findIpByPatientId(request.getPatientId());
                    if(patient != null)
                        patientVitalsDTO.setPatientId(patient.getId());
                        patientVitalsDTO.setPatientMRN(patient.getPatientIdExternal());
                        patientVitalsDTO.setPatientDateOfBirth(patient.getDateOfBirth());
                        if(patient.getPatientSalutation() != null) {
                            patientVitalsDTO.setPatientSalutation(patient.getPatientSalutation().getName());
                        }
                        patientVitalsDTO.setPatientName(patient.getPatientName());
                        patientVitalsDTO.setMobileNumber(patient.getMobileNumber());
                        patientVitalsDTO.setGender(patient.getGender());
                        if(patient.getBloodGroup() != null){
                            patientVitalsDTO.setBloodGroupId(patient.getBloodGroup().getId());
                            patientVitalsDTO.setBloodGroupName(patient.getBloodGroup().getName());
                        }
                        if(patient.getMaritalStatus() != null){
                            patientVitalsDTO.setMaritalStatusId(patient.getMaritalStatus().getId());
                            patientVitalsDTO.setMaritalStatusName(patient.getMaritalStatus().getName());
                        }
                        if(patient.getOccupation() != null){
                            patientVitalsDTO.setOccupationId(patient.getOccupation().getId());
                            patientVitalsDTO.setOccupationName(patient.getOccupation().getName());
                        }
                        patientVitalsDTO.setAllergic(patient.getAllergies());
                        if(vitalList.size() != 0){
                            for (Vital vital : vitalList) {
                                int n = vitalList.size();
                                if (vitalList.get(n - 1) != null) {
                                    patientVitalsDTO.setPatientVitalId(vital.getId());
                                    patientVitalsDTO.setWeight(vital.getWeight());
                                    patientVitalsDTO.setHeight(vital.getHeight());
                                    patientVitalsDTO.setBmi(vital.getBmi());
                                    patientVitalsDTO.setBp(vital.getBp());
                                    patientVitalsDTO.setHeadCircumference(vital.getHead());
                                    patientVitalsDTO.setTemperature(vital.getTemperature());
                                    patientVitalsDTO.setPulse(vital.getPulse());
                                }
                            }
                        }
                List<VisitTypeMaster> visitTypeMasters = visitTypeMasterRepository.getVisitTypeMasterByClinicLocation(request.getClinicLocationId());
                if (visitTypeMasters != null) {
                    for (VisitTypeMaster typeMaster : visitTypeMasters) {
                        if (typeMaster.getVisitType().equalsIgnoreCase("OP")) {
                            patientVitalsDTO.setVisitTypeMasterId(typeMaster.getId());
                            patientVitalsDTO.setVisitType(typeMaster.getVisitType());
                        }
                    }
                }
                //setting ip admission details
                if(ipAdmissions != null) {
                    for(IpAdmission ipAdmission : ipAdmissions){
                        patientVitalsDTO.setIpDcAdmissionNo(ipAdmission.getIpAdmissionNumber());
                        patientVitalsDTO.setIpAdmissionId(ipAdmission.getId());
                        if (ipAdmission.getVisitTypeMasterId() != null) {
                            visitTypeMaster = getVisitTypeMaster(ipAdmission.getVisitTypeMasterId());
                            patientVitalsDTO.setVisitType(visitTypeMaster.getVisitType());
                            patientVitalsDTO.setVisitTypeMasterId(visitTypeMaster.getId());
                        } else if (ipAdmission.getDayCareAdmissionNumber() != null) {
                            patientVitalsDTO.setIpDcAdmissionNo(ipAdmission.getDayCareAdmissionNumber());
                            if (ipAdmission.getVisitTypeMasterId() != null) {
                                visitTypeMaster = getVisitTypeMaster(ipAdmission.getVisitTypeMasterId());
                                patientVitalsDTO.setVisitType(visitTypeMaster.getVisitType());
                                patientVitalsDTO.setVisitTypeMasterId(visitTypeMaster.getId());
                            }
                        }
                    }
                }

                //create response
                response = new GetPatientVitalsByPatientIdServiceResponse(patientVitalsDTO);
                response.SUCCESSFUL = true;
                response.setMessage("Patient and patient vital details retrieved successfully");
                log.debug("Patient and patient vital details retrieved successfully");
            }
        } catch (Exception e) {
            //create response
            response = new GetPatientVitalsByPatientIdServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Patient and patient vital details not retrieved successfully");
            log.error("Patient and patient vital details not retrieved successfully");
        }
        return response;
    }

    private VisitTypeMaster getVisitTypeMaster(Long visitTypeMasterId) {
        return visitTypeMasterRepository.findOne(visitTypeMasterId);
    }
}
