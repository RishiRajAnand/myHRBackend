package com.erx.microservice.patientmanagement.service.ipadmission.dischargeipadmission;

/*
* created by Brighty on Brighty on 21-06-2018
* */

import com.erx.microservice.patientmanagement.domain.BedConfigurationMaster;
import com.erx.microservice.patientmanagement.domain.IpAdmission;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.repository.BedConfigurationMasterRepository;
import com.erx.microservice.patientmanagement.repository.IpAdmissionRepository;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("dischargeIpAdmissionService")
public class DischargeIpAdmissionServiceImpl implements DischargeIpAdmissionService {

    private final Logger log = LoggerFactory.getLogger(DischargeIpAdmissionServiceImpl.class);

    @Autowired
    private IpAdmissionRepository ipAdmissionRepository;

    @Autowired
    private BedConfigurationMasterRepository bedConfigurationMasterRepository;

    @Override
    public DischargeIpAdmissionServiceResponse execute(DischargeIpAdmissionServiceRequest request) throws ServiceException {
        DischargeIpAdmissionServiceResponse response;
        log.debug("DischargeIpAdmissionServiceImpl ==> Call to discharge IpAdmission ");
        try {
            if (request.getIpAdmissionId() == null) {
                response = createResponse(false, PatientConstants.INVALID_INPUT);
                log.debug("DischargeIpAdmissionServiceImpl ==> INVALID INPUT");
                return response;
            }
            //retrieve IpAdmission
            IpAdmission ipAdmission = ipAdmissionRepository.findOne(request.getIpAdmissionId());

            if (ipAdmission == null) {
                response = createResponse(false, PatientConstants.INVALID_INPUT + " : IP admission is NULL");
                log.debug("DischargeIpAdmissionServiceImpl ==> INVALID INPUT : IP admission is NULL");
                return response;
            }

            //Update Discharged On
            if (ipAdmission.getDischargedOn() == null)
                ipAdmission.setDischargedOn(LocalDateTime.now());

            //Update IpAdmissionStatus
            if (!PatientConstants.IP_STATUS_DISCHARGED.toUpperCase().equalsIgnoreCase(ipAdmission.getIpAdmissionStatus()))
                ipAdmission.setIpAdmissionStatus(PatientConstants.IP_STATUS_DISCHARGED.toUpperCase());

            //save IpAdmission
            ipAdmissionRepository.save(ipAdmission);

            //Update BedConfigurationMaster AllocatedStatus
            BedConfigurationMaster bedConfigurationMaster = ipAdmission.getBedMaster();
            bedConfigurationMaster.setAllocatedStatus(PatientConstants.IP_BED_STATUS_AVAILABLE);

            //save Bed
            bedConfigurationMasterRepository.save(bedConfigurationMaster);

            response = createResponse(true, PatientConstants.IP_DISCHARGE_SUCCESS);
            log.debug("DischargeIpAdmissionServiceImpl ==> SUCCESS");
        } catch (Exception e) {
            response = createResponse(false, PatientConstants.IP_DISCHARGE_FAILURE + " : " + e.getMessage());
            log.error("DischargeIpAdmissionServiceImpl ==> FAILURE : " + e.getMessage());
        }
        return response;
    }

    private DischargeIpAdmissionServiceResponse createResponse(boolean successful, String message) {
        DischargeIpAdmissionServiceResponse response = new DischargeIpAdmissionServiceResponse();
        response.setMessage(message);
        response.SUCCESSFUL = successful;
        return response;
    }
}