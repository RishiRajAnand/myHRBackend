package com.erx.microservice.patientmanagement.service.patientappointment.getservicesbypatientappointment;

/*
* created by Latha on 21-04-2018.
* */

import com.erx.microservice.patientmanagement.domain.PatientAppointment;
import com.erx.microservice.patientmanagement.repository.PatientAppointmentRepository;
import com.erx.microservice.patientmanagement.service.dto.PatientAppointmentDTO;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("getServicesByPatientAppointmentService")
public class GetServicesByPatientAppointmentServiceImpl implements GetServicesByPatientAppointmentService{

    private final Logger log = LoggerFactory.getLogger(GetServicesByPatientAppointmentServiceImpl.class);

    @Autowired
    PatientAppointmentRepository patientAppointmentRepository;

    @Override
    public GetServicesByPatientAppointmentResponse execute(GetServicesByPatientAppointmentRequest request) throws ServiceException {
        GetServicesByPatientAppointmentResponse response = null;
        PatientAppointmentDTO patientAppointmentDTO = new PatientAppointmentDTO();
        PatientAppointment patientAppointment = new PatientAppointment();
        try {
            log.debug("Call to get service catalogue details for the given patient appointment id");
            // retrieve the services by patient appointment
            if(request.getPatientAppointmentID() != null){
                patientAppointment = patientAppointmentRepository.findOne(request.getPatientAppointmentID());
                if(patientAppointment != null){
                    patientAppointmentDTO.setServiceCatalogueId(patientAppointment.getServiceCatalogueId());
                }
            }
            response = new GetServicesByPatientAppointmentResponse(patientAppointmentDTO);
            response.SUCCESSFUL = true;
            response.setMessage("Retrieved service catalogue details by patient appointment Successfully");
            log.debug("Retrieved service catalogue details by patient appointment Successfully");
        } catch (Exception e) {response = new GetServicesByPatientAppointmentResponse();
            response.SUCCESSFUL = false;
            response.setMessage("Failed to retrieve service catalogue details by patient appointment");
            log.error("Failed to retrieve service catalogue details by patient appointment");
        }
        return response;
    }
}
