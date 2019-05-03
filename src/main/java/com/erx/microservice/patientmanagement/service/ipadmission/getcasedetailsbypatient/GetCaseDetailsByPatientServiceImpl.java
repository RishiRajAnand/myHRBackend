package com.erx.microservice.patientmanagement.service.ipadmission.getcasedetailsbypatient;

/*
* created by Latha on 29-11-2017.
* */

import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.PatientCaseDTO;
import com.erx.microservice.patientmanagement.service.mapper.casemanagement.CmMasterMapper;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("getCaseDetailsByPatientService")
public class GetCaseDetailsByPatientServiceImpl implements GetCaseDetailsByPatientService {

    private final Logger log = LoggerFactory.getLogger(GetCaseDetailsByPatientServiceImpl.class);

    @Autowired
    CmMasterRepository cmMasterRepository;

    @Autowired
    CmMasterMapper cmMasterMapper;

    @Override
    public GetCaseDetailsByPatientServiceResponse execute(GetCaseDetailsByPatientServiceRequest request) throws ServiceException {

        GetCaseDetailsByPatientServiceResponse response = null;
        List<CmMaster> cmMasters = null;
        List<PatientCaseDTO> patientCaseDTOs = new ArrayList<>();
        try {
            log.debug("Call to get case details for the given patient");
            if (request.getPatientId() != null) {
                //retrieve case data
                cmMasters = cmMasterRepository.getCaseDetailsByPatient(request.getPatientId(), request.getClinicLocationId());
                //convert domain to DTO
                for (CmMaster cmMaster : cmMasters) {
                    PatientCaseDTO patientCaseDTO = new PatientCaseDTO();
                    //convert bedConfigurationMaster to bedConfigurationDTO
                    patientCaseDTO = cmMasterMapper.cmMasterToPatientCaseDTO(cmMaster);
                    //set the values
                    patientCaseDTO.setPatientID(cmMaster.getPatient().getId());
                    patientCaseDTO.setCaseID(cmMaster.getId());
                    patientCaseDTO.setCaseCreatedDate(cmMaster.getCaseCreatedDate());
                    patientCaseDTO.setCaseCompletedDate(cmMaster.getCaseCompletedDate());
                    patientCaseDTO.setCaseChiefComplaint(cmMaster.getChiefComplaint());
                    patientCaseDTO.setCaseStatus(cmMaster.getCaseStatus());
                    patientCaseDTO.setCaseClinicID(cmMaster.getClinicCaseNumber());
                    if (cmMaster.getUserDetail() != null) {
                        patientCaseDTO.setDoctorID(cmMaster.getUserDetail().getId());
                        patientCaseDTO.setDoctorName("Dr." + cmMaster.getUserDetail().getUserStaff().getFirstName() + " " + cmMaster.getUserDetail().getUserStaff().getLastName());
                    }
                    // add details to list
                    patientCaseDTOs.add(patientCaseDTO);
                }
            }
            response = new GetCaseDetailsByPatientServiceResponse(patientCaseDTOs);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved Case details Successfully");
            log.debug("Retrieved Case Details Successfully");
        } catch (Exception e) {
            response = new GetCaseDetailsByPatientServiceResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve Case details");
            log.error("Failed to retrieve Case Details");
        }
        return response;
    }
}
