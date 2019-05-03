package com.erx.microservice.patientmanagement.service.patientappointment.getlastappointmentbypatientanddoctor;

/*
* created by Brighty on 02-07-2018
* */

import com.erx.microservice.patientmanagement.domain.PatientAppointment;
import com.erx.microservice.patientmanagement.exception.ServiceException;
import com.erx.microservice.patientmanagement.gateway.ServiceGateway;
import com.erx.microservice.patientmanagement.repository.PatientAppointmentRepository;
import com.erx.microservice.patientmanagement.service.dto.GetLastAppointmentByPatientAndDoctorDTO;
import com.erx.microservice.patientmanagement.service.util.PatientConstants;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service("getLastAppointmentByPatientAndDoctorService")
public class GetLastAppointmentByPatientAndDoctorServiceImpl implements GetLastAppointmentByPatientAndDoctorService {

    private final Logger log = LoggerFactory.getLogger(GetLastAppointmentByPatientAndDoctorServiceImpl.class);

    @Autowired
    private PatientAppointmentRepository patientAppointmentRepository;

    @Autowired
    private ServiceGateway serviceGateway;

    @Override
    public GetLastAppointmentByPatientAndDoctorServiceResponse execute
            (GetLastAppointmentByPatientAndDoctorServiceRequest request) throws ServiceException {
        log.debug("GetLastAppointmentByPatientAndDoctorServiceImpl ==> call to get last patient Appointment for Patient " +
                request.getPatientId() + " and for the doctor " + request.getDoctorId());
        GetLastAppointmentByPatientAndDoctorServiceResponse response;
        List<PatientAppointment> patientAppointments;
        GetLastAppointmentByPatientAndDoctorDTO getLastAppointmentByPatientAndDoctorDTO = null;
        try {
            if (request.getPatientId() != null && request.getDoctorId() != null) {
                //retrieve patientAppointments
                patientAppointments = patientAppointmentRepository.findByPatientAndDoctor(request.getPatientId(), request.getDoctorId());
                if (!patientAppointments.isEmpty()) {
                    //set the DTO
                    getLastAppointmentByPatientAndDoctorDTO = getLastAppointmentDTO(patientAppointments);
                }
                response = createResponse(getLastAppointmentByPatientAndDoctorDTO, true,
                        PatientConstants.PATIENT_APPOINTMENT_SUCCESS);
                log.debug("GetLastAppointmentByPatientAndDoctorServiceImpl ==> SUCCESS");
                return response;
            }
            response = createResponse(getLastAppointmentByPatientAndDoctorDTO, false, PatientConstants.INVALID_INPUT);
            log.debug("GetLastAppointmentByPatientAndDoctorServiceImpl ==> INVALID INPUT ");
        } catch (Exception e) {
            response = createResponse(getLastAppointmentByPatientAndDoctorDTO, false,
                    PatientConstants.PATIENT_APPOINTMENT_FAILURE);
            log.debug("GetLastAppointmentByPatientAndDoctorServiceImpl ==> FAILURE : " + e.getMessage());
        }
        return response;
    }

    private GetLastAppointmentByPatientAndDoctorDTO getLastAppointmentDTO(List<PatientAppointment> patientAppointments) throws Exception {
        GetLastAppointmentByPatientAndDoctorDTO getLastAppointmentByPatientAndDoctorDTO = new GetLastAppointmentByPatientAndDoctorDTO();
        PatientAppointment patientAppointment = patientAppointments.get(0);
        getLastAppointmentByPatientAndDoctorDTO.setPatientAppointmentId(patientAppointment.getId());
        if (patientAppointment.getDoctorSlot() != null) {
            if (patientAppointment.getDoctorSlot().getSchedule() != null) {
                getLastAppointmentByPatientAndDoctorDTO.setSlotDate(
                        String.valueOf(patientAppointment.getDoctorSlot().getSchedule().getScheduleDate()));
            }
        }
        getLastAppointmentByPatientAndDoctorDTO.setServiceId(patientAppointment.getServiceCatalogueId());
        //retrieve service Details
        JSONObject jsonObject = serviceGateway.getServiceCatalogueById(patientAppointment.getServiceCatalogueId());

        //retrieve serviceCatalogueObject
        String serviceCatalogueObject = null;
        if (jsonObject != null)
            serviceCatalogueObject = String.valueOf(jsonObject.get("serviceName"));

        getLastAppointmentByPatientAndDoctorDTO.setServiceName(serviceCatalogueObject);
        if (patientAppointment.getDoctorSlot() != null) {
            if (patientAppointment.getDoctorSlot().getSchedule() != null) {
                LocalDate slotDate = patientAppointment.getDoctorSlot().getSchedule().getScheduleDate();
                long daysBetween = Duration.between(slotDate.atStartOfDay(), LocalDate.now().atStartOfDay()).toDays();
                getLastAppointmentByPatientAndDoctorDTO.setDuration(daysBetween);
            }
        }

        return getLastAppointmentByPatientAndDoctorDTO;
    }

    private GetLastAppointmentByPatientAndDoctorServiceResponse createResponse
            (GetLastAppointmentByPatientAndDoctorDTO getLastAppointmentByPatientAndDoctorDTO, boolean successful, String message) {
        GetLastAppointmentByPatientAndDoctorServiceResponse response = new GetLastAppointmentByPatientAndDoctorServiceResponse();
        response.setGetLastAppointmentByPatientAndDoctorDTO(getLastAppointmentByPatientAndDoctorDTO);
        response.SUCCESSFUL = successful;
        response.setMessage(message);
        return response;
    }
}
