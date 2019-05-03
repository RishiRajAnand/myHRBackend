package com.erx.microservice.patientmanagement.service.dischargerequest.savedischargerequest;

/*
* created by Brighty on 08-06-2018
* */

import com.erx.microservice.patientmanagement.domain.IpAdmissionRequest;
import com.erx.microservice.patientmanagement.domain.casemanagement.CmMaster;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRequestRepository;
import com.erx.microservice.patientmanagement.repository.casemanagement.CmMasterRepository;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDClinicDTO;
import com.erx.microservice.patientmanagement.service.dto.GenerateUniqueIDDTO;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import com.erx.microservice.patientmanagement.util.ErxConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("saveDischargeRequestService")
public class SaveDischargeRequestServiceImpl implements SaveDischargeRequestService {

    private final Logger log = LoggerFactory.getLogger(SaveDischargeRequestServiceImpl.class);

    @Autowired
    private IpAdmissionRequestRepository ipAdmissionRequestRepository;

    @Autowired
    private CmMasterRepository cmMasterRepository;

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public SaveDischargeRequestServiceResponse execute(SaveDischargeRequestServiceRequest request) throws ServiceException {
        SaveDischargeRequestServiceResponse response;
        log.debug("SaveDischargeRequestServiceImpl ==> Call to save IP Admission Request");
        IpAdmissionRequest ipAdmissionRequest;
        LocalDateTime ipAdmissionDate;
        CmMaster cmMaster;

        try {

            //validate input
            response = validateInput(request);
            if (!response.SUCCESSFUL) {
                log.debug("SaveDischargeRequestServiceImpl ==> FAILURE : Invalid input ");
                return response;
            }
            //set the IpAdmissionRequest object
            if (request.getIpAdmissionRequestId() != null)
                ipAdmissionRequest = ipAdmissionRequestRepository.findOne(request.getIpAdmissionRequestId());
            else {
                ipAdmissionRequest = new IpAdmissionRequest();
                ipAdmissionRequest.setCreatedBy(request.getUserId());
            }
           // ipAdmissionDate = LocalDateTime.parse(request.getIpAdmissionDate());
            ipAdmissionRequest.setIpAdmissionDate(request.getIpAdmissionDate());
            //retrieve all the foreign keys
            cmMaster = cmMasterRepository.findOne(request.getCmMasterId());
            ipAdmissionRequest.setCmMaster(cmMaster);
            ipAdmissionRequest.setUpdatedBy(request.getUserId());

            //Generate IpRequestNumber
            //retrieve and set clinicId,CurrentTblName and setCurrentClmName to DTO
            if (request.getIpAdmissionRequestId() == null) {
                Long clinicId = cmMaster.getClinicLocation().getClinic().getId();
                GenerateUniqueIDDTO generateUniqueIDDTO = new GenerateUniqueIDDTO();
                GenerateUniqueIDClinicDTO generateUniqueIDClinicDTO = new GenerateUniqueIDClinicDTO();
                generateUniqueIDDTO.setCurrentTableName(ErxConstants.IPR_CURRENT_TABLE_NAME);
                generateUniqueIDDTO.setCurrentColumnName(ErxConstants.IPR_CURRENT_COLUMN_NAME);
                generateUniqueIDClinicDTO.setId(clinicId);
                generateUniqueIDDTO.setGenerateUniqueIDClinicDTO(generateUniqueIDClinicDTO);
                //call generateUniqueID
                String generatedUniqueId = serviceGateway.generateUniqueID(generateUniqueIDDTO);
                //SET THE UNIQUE ID
                if (generatedUniqueId != null)
                    ipAdmissionRequest.setIpRequestNumber(generatedUniqueId);
            } else {
                ipAdmissionRequest.setIpRequestNumber(request.getIpRequestNumber());
            }

            //save IpAdmissionRequest
            IpAdmissionRequest savedIpAdmissionRequest = ipAdmissionRequestRepository.save(ipAdmissionRequest);

            response = createResponse(savedIpAdmissionRequest.getId(), true,
                    PatientConstants.IP_ADMISSION_REQUEST_SAVE_SUCCESS);
            log.debug("SaveDischargeRequestServiceImpl ==> SUCCESS ==> IpAdmissionRequest saved successfully");

        } catch (Exception e) {
            response = createResponse(null, true,
                    PatientConstants.IP_ADMISSION_REQUEST_SAVE_FAILURE + e.getMessage());
            log.error("SaveDischargeRequestServiceImpl ==> FAILURE ==> " + e.getMessage());
        }
        return response;
    }

    private SaveDischargeRequestServiceResponse validateInput(SaveDischargeRequestServiceRequest request) throws Exception {
        SaveDischargeRequestServiceResponse response = new SaveDischargeRequestServiceResponse();
        response.SUCCESSFUL = false;
        if (request.getUserId() == null)
            response.setMessage(PatientConstants.INVALID_INPUT);
        else if (request.getCmMasterId() == null)
            response.setMessage(PatientConstants.INVALID_INPUT);
        else if (request.getIpAdmissionDate() == null)
            response.setMessage(PatientConstants.INVALID_INPUT);
        else {
            response.SUCCESSFUL = true;
            return response;
        }
        return response;
    }

    private SaveDischargeRequestServiceResponse createResponse(Long id, boolean successful, String message) {
        SaveDischargeRequestServiceResponse response = new SaveDischargeRequestServiceResponse();
        response.SUCCESSFUL = successful;
        response.setIpAdmissionRequestId(id);
        response.setMessage(message);
        return response;
    }
}
